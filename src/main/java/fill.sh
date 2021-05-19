#!/bin/csh

java -jar -Dhectest.propertyfile=../resources/HECTest.properties -Dlog4j.configurationFile=../resources/log4j2.priv.xml ../../../target/HECTest-1.0-SNAPSHOT-jar-with-dependencies.jar 
