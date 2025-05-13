# Etapa 1: Construcción
FROM eclipse-temurin:17-jdk AS builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos de tu proyecto
COPY . .

# Dar permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

# Construir el proyecto y generar el archivo .jar
RUN ./mvnw clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo .jar generado en la primera etapa
COPY --from=builder /app/target/*.jar app.jar
COPY src/main/resources/static /app/static

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
