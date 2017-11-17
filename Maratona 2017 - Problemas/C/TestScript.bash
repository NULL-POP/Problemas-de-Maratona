#!/bin/bash

PROBLEM="C"
AUX="./aux.txt"

for i in {1..55}
do
    INPUT=`java $PROBLEM < ./input/${PROBLEM}_$i > $AUX`
    OUTPUT="./output/${PROBLEM}_$i"
    RESULT=`diff $AUX $OUTPUT`

    if [ -z "$RESULT" ]
    then
        echo "Passed $i"
    else
        echo "CASE #$i\n"
        echo "$RESULT"
    fi
done

rm $AUX