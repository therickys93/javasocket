#!/bin/bash

set -x
rm -Rf docs/
./gradlew clean javadoc
cp -R build/docs/javadoc/ docs/
