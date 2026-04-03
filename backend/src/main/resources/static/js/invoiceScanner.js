const html5QrCode = new Html5Qrcode("qrcode-reader");
const cameraSelect = document.getElementById('cameraSelect');

async function onScanSuccess(decodedText, decodedResult) {
    try {
        await html5QrCode.stop();
        html5QrCode.clear();
    } catch (err) {
        console.error("Failed to stop scanner:", err);
    }

    document.getElementById('loadingOverlay').classList.add('active');

    const invoiceInput = document.getElementById('invoiceUrl');
    invoiceInput.value = decodedText;

    document.getElementById('invoiceForm').submit();
}

function onScanFailure(error) {
    console.log(error);
}

let currentCameraId = null;

async function startCamera(cameraId) {
    try {
        if (html5QrCode.getState() === Html5QrcodeScannerState.SCANNING ||
            html5QrCode.getState() === Html5QrcodeScannerState.PAUSED) {
            await html5QrCode.stop().then(() => {
                html5QrCode.clear();
            }).catch(err => {
                console.error("Error stopping camera:", err);
            });
        }

        await html5QrCode.start(
            cameraId,
            {
                fps: 10,
                qrbox: document.getElementById('qrcode-reader').clientWidth * 0.6
            },
            onScanSuccess,
            onScanFailure
        );
    } catch (err) {
        console.error("Error switching camera:", err);
    }
}

Html5Qrcode.getCameras().then(cameras => {
    if (cameras.length === 0) {
        console.error("No cameras found.");
        return;
    }

    cameras.forEach((camera, index) => {
        const option = document.createElement("option");
        option.value = camera.id;
        option.text = camera.label || `Camera ${index + 1}`;
        cameraSelect.appendChild(option);
    });

    // Select last camera (often back camera on mobile)
    const preferredCameraId = cameras[cameras.length - 1].id;
    cameraSelect.value = preferredCameraId;
    currentCameraId = preferredCameraId;
    startCamera(currentCameraId);
});

cameraSelect.addEventListener('change', (e) => {
    const selectedId = e.target.value;
    if (selectedId !== currentCameraId) {
        currentCameraId = selectedId;
        startCamera(currentCameraId);
    }
});
