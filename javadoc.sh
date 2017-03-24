#!/bin/bash

./gradlew clean javadoc
cp -R build/docs/javadoc/ docs/
