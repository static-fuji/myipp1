#!/bin/sh

if [ $# -lt 3 ]; then
    echo "Usage: $0 <jar_file> <main_class> <path_to_src>"
    exit 1
fi

DOCKER_IMG=openjdk
JAR=$1
MAIN=$2
SRC_PATH=$3

docker run --rm -it \
    -v $PWD:/app \
    -v $(realpath ${SRC_PATH}):/src \
    -w /work \
    ${DOCKER_IMG} \
    sh -c "cp -r /src/* /work/ &&
    javac **/*.java &&
    jar cvf /app/${JAR} ./ &&
    echo Main-Class: ${MAIN} > main.mf &&
    jar uvfm /app/${JAR} main.mf || /bin/bash"