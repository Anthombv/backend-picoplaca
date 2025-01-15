# Usa una imagen oficial de OpenJDK como base
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto
COPY . .

# Construye el proyecto usando Maven Wrapper
RUN ./mvnw clean package

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar el JAR
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
