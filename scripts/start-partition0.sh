#!/bin/bash
# Start three replicas for partition 0.  Assumes the packaged jar file is in
# target/ewallet-cw2-1.0-SNAPSHOT-jar-with-dependencies.jar.  Adjust paths as
# necessary.  Each replica is started in the background and logs are
# redirected to individual files.

JAR=target/ewallet-cw2-1.0-SNAPSHOT-jar-with-dependencies.jar

# RAFT peer addresses for partition 0
RAFT_PEERS="localhost:6000,localhost:6001,localhost:6002"

# Start replica 0 (service port 5000, raft port 6000)
java -jar $JAR server 0 0 5000 6000 $RAFT_PEERS > logs/partition0-replica0.log 2>&1 &
echo "Started partition 0 replica 0 on ports 5000/6000"

# Start replica 1 (service port 5001, raft port 6001)
java -jar $JAR server 0 1 5001 6001 $RAFT_PEERS > logs/partition0-replica1.log 2>&1 &
echo "Started partition 0 replica 1 on ports 5001/6001"

# Start replica 2 (service port 5002, raft port 6002)
java -jar $JAR server 0 2 5002 6002 $RAFT_PEERS > logs/partition0-replica2.log 2>&1 &
echo "Started partition 0 replica 2 on ports 5002/6002"