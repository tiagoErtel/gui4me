function onScanSuccess(decodedText, decodedResult) {
    const invoiceInput = document.getElementById('invoiceUrl');
    invoiceInput.value = decodedText;
    document.getElementById('invoiceForm').submit();
}

function onScanFailure(error) {
    // optionally log scan failure for debugging
}

const html5QrCode = new Html5Qrcode("reader");

Html5Qrcode.getCameras().then(cameras => {
    if (cameras && cameras.length) {
        html5QrCode.start(
            cameras[0].id,
            {
                fps: 10,    // frames per second
                qrbox: 250  // square area to scan
            },
            onScanSuccess,
            onScanFailure
        );
    } else {
        console.error("No cameras found.");
    }
}).catch(err => {
    // handle error
});
