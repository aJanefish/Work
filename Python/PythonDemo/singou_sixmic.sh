#!/bin/bash
file="/dev/ttyUSB0"

ls_date=`date +%Y-%m-%d-%H-%M-%S`



if [ -c "$file" ]
then
 echo "${ls_date} $file found." >> ~/example.txt
else
 echo "${ls_date} $file not found." >> ~/example.txt 
fi
