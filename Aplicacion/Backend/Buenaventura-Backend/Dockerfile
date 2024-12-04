# Etapa 1: Construcción
FROM maven:3.9.4-amazoncorretto-17 AS builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY pom.xml .
COPY src ./src

# Ejecutar la compilación del proyecto
RUN mvn clean package -DskipTests

# Etapa 2: Generar imagen final
FROM amazoncorretto:17-alpine-jdk

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el .jar generado desde la etapa de construcción
COPY --from=builder /app/target/Buenaventura-Backend-0.0.1-SNAPSHOT.jar /app/app.jar

# Definir el comando de entrada
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
