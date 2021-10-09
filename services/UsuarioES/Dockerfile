FROM openjdk:11
RUN mkdir /usr/src/UsuarioES
COPY /target/UsuarioES-1.0.0.jar /usr/src/UsuarioES
WORKDIR /usr/src/UsuarioES
EXPOSE 8101
CMD ["java", "-Djava.security.egd=file:/dev/.urandom", "-Duser.timezone=EC", "-jar", "UsuarioES-1.0.0.jar"]