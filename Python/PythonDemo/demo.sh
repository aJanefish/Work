#!/bin/bash
file="/dev/ttyUSB0"
if [ -c "$file" ]
then
 echo "$file found."
else
 echo "$file not found."
fi
