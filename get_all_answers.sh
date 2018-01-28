#!/bin/bash

ARGS=""
for i in {1..3999}
do
  ARGS="$ARGS $i";
done
java -jar "$(find build/libs/ -name '*.jar')" $ARGS
