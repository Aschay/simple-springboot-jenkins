#!/bin/sh
echo "step up environement for docker image build"
echo "preparing proper layers for docker"
echo "building ... "
mvn clean package  -DskipTests
cd target/
echo "layering ... "
java -Djarmode=layertools -jar *.jar list
java -Djarmode=layertools -jar *.jar extract 

