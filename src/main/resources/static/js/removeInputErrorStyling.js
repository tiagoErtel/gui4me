document.addEventListener('DOMContentLoaded', function () {
	const inputs = document.querySelectorAll('input.input-error');

	inputs.forEach(input => {
		input.addEventListener('input', () => {
			input.classList.remove('input-error');
		});
	});
});