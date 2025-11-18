# Primeira etapa: gerar o jar com man package dentro do container
# A imagem maven jdk esta deprecated, eclipse temurin apresenta vulnerabilidade. Usar amazon corretto
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

COPY . .

RUN mvn clean package -DskipTests

# Segunda etapa: acessar o jar gerado na primeira etapa
FROM amazoncorretto:21-alpine

# Para acessar a primeira etapa: --from=build
# O nome daht.jar vem da tag finalName do pom.xml que adicionei
COPY --from=build target/*.jar app.jar

EXPOSE 8412

CMD ["java", "-jar", "/app.jar"]