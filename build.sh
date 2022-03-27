#!/bin/sh

TOOLS_DIR=tools/

# Check if Python3 is installed

if ! which python3 &> /dev/null
then
  echo "ERROR: Python3 not found !"
fi

# Check if Java is installed

if ! which java &> /dev/null
then
  echo "ERROR: Java not found !"
fi

# Check if Apache Maven is installed

if ! which mvn &> /dev/null
then
  echo "ERROR: Apache Maven not found !"
fi

# Launch the build process
rm -rvf target/
python3 $TOOLS_DIR/prebuild-nightly.py $(git rev-parse --short HEAD) && \
mvn -f ./nightly-pom.xml clean install && \
rm -rf nightly-pom.xml
