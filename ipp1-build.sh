#!/bin/sh

if [ $# -lt 3 ]; then
    echo "Usage: $0 <jar_file> <main_class> <path_to_src> [out_path] [src_copy_target1] [src_copy_target2] ..."
    exit 1
fi

DOCKER_IMG=eclipse-temurin:17
JAR=$1
MAIN=$2
SRC_PATH=$3
OUT_VOL=""
OUT_PATH=/app
if [ $# -gt 3 ]; then
    OUT_VOL="-v $(realpath $4):/out"
    OUT_PATH=/out
fi
CP_TARGETS="*"
if [ $# -gt 4 ]; then
    CP_TARGETS=${@:5}
fi

docker run --rm -it \
    ${OUT_VOL} \
    -v $PWD:/app \
    -v $(realpath ${SRC_PATH}):/src \
    -w /work \
    ${DOCKER_IMG} \
    sh -c "cd /src && 
    cp -r --parents ${CP_TARGETS} /work/ &&
    cd /work &&
    javac **/*.java &&
    jar cvf ${OUT_PATH}/${JAR} ./ &&
    echo Main-Class: ${MAIN} > main.mf &&
    jar uvfm ${OUT_PATH}/${JAR} main.mf || /bin/bash"