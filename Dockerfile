# Primeira etapa: gerar o jar com man package dentro do container
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

COPY . .

# Esta etapa utiliza o código do Jenkinsfile para mover o APK antes do package
# Como o package está sendo feito ANTES do docker build,
# o JAR já está pronto quando o Dockerfile é executado.
RUN mvn clean package -DskipTests

# Segunda etapa: acessar o jar gerado na primeira etapa
FROM amazoncorretto:21-alpine

# Copia o JAR que agora tem o index.html e o DahtApp.apk embutidos
COPY --from=build target/*.jar app.jar

EXPOSE 8412

CMD ["java", "-jar", "/app.jar"]