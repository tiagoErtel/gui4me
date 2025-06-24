document.addEventListener('click', function(event) {
    document.querySelectorAll('details.dropdown').forEach(dropdown => {
        if (!dropdown.contains(event.target)) {
            dropdown.removeAttribute('open');
        }
    });
});

