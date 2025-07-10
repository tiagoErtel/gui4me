function dismissMessage(id) {
    var container = document.getElementById('message-container-' + id);
    if (container) {
        container.classList.remove('show');
        container.classList.add('hide');

        container.addEventListener('animationend', () => {
            container.remove();
        });
    }
}

