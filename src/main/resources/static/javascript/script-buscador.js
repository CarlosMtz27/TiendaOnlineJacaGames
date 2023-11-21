
function cargarDatos() {
    var juegos = [
        "Caza Leyendas",
        "The Legend of Zelda: Breath of the Wild",
        "Super Mario Odyssey",
        "Red Dead Redemption 2",
        "The Witcher 3: Wild Hunt",
        "Grand Theft Auto V",
        "Dark Souls III",
        "Bloodborne",
        "Sekiro: Shadows Die Twice",
        "Horizon Zero Dawn",
        "God of War",
        "Uncharted 4: A Thief's End",
        "The Last of Us Part II",
        "Minecraft",
        "Fortnite",
        "Call of Duty: Warzone",
        "Overwatch",
        "Apex Legends",
        "Valorant",
        "League of Legends",
        "Among Us",
        "Cyberpunk 2077",
        "Assassin's Creed Valhalla",
        "Final Fantasy VII Remake",
        "Doom Eternal",
        "Animal Crossing: New Horizons",
        "Super Smash Bros. Ultimate",
        "Resident Evil Village",
        "FIFA 21",
        "NBA 2K21",
        "The Elder Scrolls V: Skyrim",
        "Stardew Valley",
        "Terraria",
        "Celeste",
        "Hades",
        "Persona 5 Royal",
        "Hollow Knight",
        "Monster Hunter: World",
        "Genshin Impact",
        "Rocket League",
        "Among Us",
        "Subnautica",
        "Death Stranding",
        "The Outer Worlds",
        "Control",
        "Cuphead",
        "Slay the Spire",
        "Rainbow Six Siege",
        "Warframe"
    ];
    return juegos;
}

// Función para buscar productos en tiempo real
function buscarProductos() {
    var query = document.getElementById("query").value.trim().toLowerCase();
    var resultadosList = document.getElementById("resultados-list");
    var juegos = cargarDatos();

    // Filtra los juegos que coinciden con la consulta
    var juegosFiltrados = juegos.filter(function (juego) {
        return juego.toLowerCase().includes(query);
    });

    // Limpia el datalist
    resultadosList.innerHTML = "";

    // Agrega las opciones al datalist
    juegosFiltrados.forEach(function (juego) {
        var option = document.createElement("option");
        option.value = juego;
        resultadosList.appendChild(option);
    });
}

// Evento que se dispara cuando se carga la página
window.onload = function () {
    // Asigna la función buscarProductos al evento "input" del campo de búsqueda
    var inputElement = document.getElementById("query");
    inputElement.addEventListener("input", buscarProductos);
};
