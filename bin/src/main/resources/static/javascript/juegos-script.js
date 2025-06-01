
document.addEventListener('DOMContentLoaded', function() {
	const btnLista = document.getElementById('btnLista');
	const btnTarjetas = document.getElementById('btnTarjetas');
	const listaJuegos = document.querySelector('.lista-juegos');
	const juegoCards = document.querySelectorAll('.juego-card');

	// Función para mostrar la vista de lista
	btnLista.addEventListener('click', function() {
		listaJuegos.style.display = 'block';
		juegoCards.forEach(card => card.style.display = 'none');
	});

	// Función para mostrar la vista de tarjetas
	btnTarjetas.addEventListener('click', function() {
		listaJuegos.style.display = 'none';
		juegoCards.forEach(card => card.style.display = 'block');
	});

	// Inicializar con la vista de tarjetas ocultando la lista al cargar la página
	listaJuegos.style.display = 'none';
	juegoCards.forEach(card => card.style.display = 'block');
});
