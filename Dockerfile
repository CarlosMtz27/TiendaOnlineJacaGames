# Usa la imagen oficial de OpenJDK 17
FROM eclipse-temurin:17-jdk

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por Maven al contenedor
COPY target/*.jar app.jar

# Exponer el puerto que usa tu aplicación
EXPOSE 8082

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
