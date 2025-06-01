/**
 * 
 */
 function validatePassword() {
  var newPassword = document.getElementById('newPassword').value;
  var confirmPassword = document.getElementById('confirmPassword').value;
  var mismatchMessage = document.getElementById('passwordMismatch');
  var submitButton = document.getElementById('submitButton');
  
  if (newPassword === confirmPassword) {
    // Contraseñas coinciden, ocultar mensaje de error y habilitar el botón de enviar.
    mismatchMessage.style.display = 'none';
    submitButton.disabled = false;
  } else {
    //Contraseñas no coinciden, mostrar mensaje de error y deshabilitar el botón de enviar.
    mismatchMessage.style.display = 'block';
    submitButton.disabled = true;
  }
}