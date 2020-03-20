#!/usr/bin/env bash

echo "***********************************"
echo "PACKAGING"
echo "***********************************"

mvn clean package

echo "***********************************"
echo "STARTING CLEAN INSTANCE"
echo "***********************************"

JVM_OPTS="-Xms248m -Xmx1524m  -agentlib:jdwp=transport=dt_socket,address=58001,server=y,suspend=n"
#JVM_OPTS="-Xms248m -Xmx1524m -XX:MaxDirectMemorySize=256M -XX:+CMSClassUnloadingEnabled -Djava.awt.headless=true -Dorg.apache.felix.http.host=0.0.0.0  -agentlib:jdwp=transport=dt_socket,address=58001,server=y,suspend=n"

cd quickstart/target && java ${JVM_OPTS} -jar $(find . -name io.slingcms*[^sources].jar) -Dorg.osgi.service.http.port=8081 && cd -
