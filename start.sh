#!/usr/bin/env bash

mvn clean package

cd quickstart && java -jar $(find ./target -name io.slingcms*[^sources].jar) -Dorg.osgi.service.http.port=8081 && cd -
