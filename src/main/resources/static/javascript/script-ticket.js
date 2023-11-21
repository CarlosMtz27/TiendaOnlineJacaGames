function imprimirTicket() {
    //window.print();
    $("#ticketButton").text("Pixel Palace - Tu Portal al Mundo de la Diversión Virtual");
    // Redirect to the home page
    var button = document.getElementById("ticketButton");
    button.style.backgroundColor = "transparent";
    button.style.border = "none";
    button.style.color = "#007bff"; // Change the text color to your desired color
    var button = document.getElementById("graciasAlert");
    button.style.backgroundColor = "transparent";
    button.style.border = "none";
    button.style.color = "black";
    // window.location.href = "@{/}";
    window.print();
}

var qrcode = new QRCode(document.getElementById("qrcode"), {
    text: "https://www.tu-sitio-web/producto",
    width: 100,
    height: 100
});

JsBarcode("#barcode", "0123456789", {
    format: "CODE128",
    displayValue: true,
    fontSize: 12
});

// Ocultar alerta después de 5 segundos
setTimeout(function () {
    $("#graciasAlert").fadeOut();
}, 5000); // 5000 milliseconds for 5 seconds
/**
 * 
 */