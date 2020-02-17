#!/usr/bin/env bash

echo "***********************************"
echo "PACKAGING"
echo "***********************************"

mvn clean package

echo "***********************************"
echo "STARTING CLEAN INSTANCE"
echo "***********************************"

cd quickstart/target && java -jar $(find . -name io.slingcms*[^sources].jar) -Dorg.osgi.service.http.port=8081 && cd -
