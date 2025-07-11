# Imagem base com Java 17 e Maven
FROM maven:3.9.6-eclipse-temurin-17 as build

WORKDIR /app
COPY . .

# Executa testes
RUN mvn clean test
# Roda os testes com cobertura Jacoco
RUN mvn clean verify

# Cria imagem final para execução da API
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
