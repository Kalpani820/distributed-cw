#!/bin/bash
# Start three replicas for partition 1.  This script launches each replica
# with appropriate service and raft ports.  Adjust JAR path and ports if
# necessary.

JAR=target/ewallet-cw2-1.0-SNAPSHOT-jar-with-dependencies.jar

RAFT_PEERS="localhost:6010,localhost:6011,localhost:6012"

java -jar $JAR server 1 0 5010 6010 $RAFT_PEERS > logs/partition1-replica0.log 2>&1 &
echo "Started partition 1 replica 0 on ports 5010/6010"

java -jar $JAR server 1 1 5011 6011 $RAFT_PEERS > logs/partition1-replica1.log 2>&1 &
echo "Started partition 1 replica 1 on ports 5011/6011"

java -jar $JAR server 1 2 5012 6012 $RAFT_PEERS > logs/partition1-replica2.log 2>&1 &
echo "Started partition 1 replica 2 on ports 5012/6012"