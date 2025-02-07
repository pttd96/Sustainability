#!/bin/bash
echo Enter SQL file name
read string
string2=` echo ${string// /_}`
timestamp=`date -u +'%Y%m%d%H%M'`
cd db/migration
touch V$timestamp\_\_$string2.sql