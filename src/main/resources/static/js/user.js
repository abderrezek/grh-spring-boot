flatpickr("#dateNaissance", {
	dateFormat: "d/m/Y",
	maxDate: "today",
});


function form() {
	return {
		nom: { errorMessage: '', blurred: false },
		prenom: { errorMessage: '', blurred: false },
		dateNaissance: { errorMessage: '', blurred: false },
		lieuNaissance: { errorMessage: '', blurred: false },
		sexe: { errorMessage: '', blurred: false },
		email: { errorMessage: '', blurred: false },
		telephone: { errorMessage: '', blurred: false },
	};
}
Alpine.data("form", form);
Alpine.start();