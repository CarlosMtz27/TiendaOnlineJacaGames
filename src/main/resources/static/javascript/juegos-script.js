document.addEventListener("DOMContentLoaded", function () {
    const btnLista    = document.getElementById('btnLista');
    const btnTarjetas = document.getElementById('btnTarjetas');
    const listView    = document.getElementById('lista-view');
    const cardView    = document.getElementById('cards-view');

    // Click en "Vista de Lista"
    btnLista.addEventListener('click', function() {
        btnLista.classList.add('active');
        btnTarjetas.classList.remove('active');
        
        // Mostrar lista, ocultar tarjetas
        listView.style.display = 'block';
        cardView.style.display = 'none';
    });

    // Click en "Vista de Tarjetas"
    btnTarjetas.addEventListener('click', function() {
        btnTarjetas.classList.add('active');
        btnLista.classList.remove('active');
        
        // Ocultar lista, mostrar tarjetas manteniendo display: flex
        listView.style.display = 'none';
        cardView.style.display = 'flex';
    });

    // Estado inicial (al cargar la página): ocultar lista, mostrar tarjetas
    btnLista.classList.remove('active');
    btnTarjetas.classList.add('active');
    listView.style.display = 'none';
    cardView.style.display = 'flex';
});