FROM maven:3.6.3-adoptopenjdk-8

RUN apt-get update \
    && apt-get install -y gpg \
    && apt-get clean

RUN mkdir /home/gpg && chown 1000:1000 /home/gpg

