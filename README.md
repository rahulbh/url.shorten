# Welcome to Gov Tech's URL Shortener

This app was developed on Mac OS 11.4 using java 8.

Java Version: 8
Dependency Management: maven

How to build?
run 'mvn clean package'
It should build a url.shorten-0.0.1-SNAPSHOT.jar in target folder.

How to run?
You can either run the jar directly or host it on Docker.

Docker Commands: \
$ docker build -t url-shorten . \
$ docker run -p 8080:8080 -t url-shorten

The app is hosted on heroku: https://yourl-shortnr.herokuapp.com/

