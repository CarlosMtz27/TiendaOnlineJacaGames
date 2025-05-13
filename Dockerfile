# Etapa 1: Construcción
FROM eclipse-temurin:17-jdk AS builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos de tu proyecto
COPY . .

# Construir el proyecto y generar el archivo .jar
RUN ./mvnw clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo .jar generado en la primera etapa
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8082

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
