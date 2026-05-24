#!/bin/bash
# Samrawi Ride — compile and run
set -e

cd "$(dirname "$0")"
mkdir -p out

echo "Compiling Samrawi Ride..."
javac -d out \
  src/models/*.java \
  src/persons/*.java \
  src/rides/*.java \
  src/system/*.java \
  src/Main.java

echo "Done. Running..."
echo ""
java -cp out Main
