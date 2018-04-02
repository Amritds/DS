#!/bin/bash
# This script will be used to launch simulations 

##  Usage Information:
## ./run.sh compile : Builds binaries\n
## ./run.sh q1 <graph_edge_list_file> <starting_node_id> : Runs Q1\n
## ./run.sh q2 <graph_edge_list_file> <starting_node_id> : Runs Q2\n
## ./run.sh q3 <graph_edge_list_file> <starting_node_id> : Runs Q3\n
## 


#Capture usage information.
help=$(grep "^## " "${BASH_SOURCE[0]}" |cut -c 5-)

#Display usage information if the number of arguments is 0. 
if [ "$#" -eq 0 ]; then
	echo -e $help

elif [ "$1" = "compile" ]
then
   source ./compile.sh   	
else

#Copy the graph_edge_list file to a location easily accessible by the java programs
cat $2 >./bin/graph.txt

#Change working directory and launch java program
cd ./bin
java -cp "gs-algo.jar:gs-core.jar:gs-ui.jar:." Simulation $1 $3

#Change working directory back 
cd ../

fi

