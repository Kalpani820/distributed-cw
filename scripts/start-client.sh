#!/bin/bash
# Run the demonstration client.  This script assumes that the cluster of
# replicas has already been started (see start-partition*.sh).  The demo
# will create a few accounts and perform a series of transfers.

JAR=target/ewallet-cw2-1.0-SNAPSHOT-jar-with-dependencies.jar
java -jar $JAR