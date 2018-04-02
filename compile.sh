#!/bin/bash
# This script will be used to compile the simulator application

#Create a bin directory and add jars.
mkdir -p bin
cp -r lib/* bin/

#Create log directory
mkdir -p log 
touch log/output.txt

#Change directory to the folder containing source files.
cd src

#Store all java filenames in a file
find -name "*.java" > sources.txt

#Compile all java programs, and store them in the bin directory. 
javac -cp "gs-algo.jar:gs-core.jar:gs-ui.jar:." -d ../bin @sources.txt

#Return to original directory.
cd ../
