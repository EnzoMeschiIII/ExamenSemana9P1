#Dockerfile

FROM openjdk:21-ea-24-oracle

WORKDIR /app

#Nombre de Jar
COPY target/ExamenSemana9P1-0.0.1-SNAPSHOT.jar app.jar
#Ubicacion y nombre de la wallet 
COPY Wallet_VD5RI47RU558OB8X /app/oracle_wallet/
EXPOSE 8080

CMD ["java","-jar","app.jar"]