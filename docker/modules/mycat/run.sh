#!/bin/bash

cd `dirname $0`/../
BASEDIR=`pwd`
echo "    Mycat path: $BASEDIR"

if [ -z "$JAVACMD" ]; then
    if [ -n "$JAVA_HOME" ]; then
        JAVACMD="$JAVA_HOME/bin/java"
    else
        JAVACMD=`which java`
    fi
fi

if [ ! -x "$JAVACMD" ] ; then
  echo "Error: JAVA_HOME is not defined correctly."
  echo "  We cannot execute $JAVACMD"
  exit 1
fi
echo " Using JAVACMD: $JAVACMD"

CLASSPATH=$(JARS=(lib/*.jar); IFS=:; echo "${JARS[*]}")

JAVA_OPTS="$JAVA_OPTS -server -Xms128m -Xmx2048m -XX:+AggressiveOpts -XX:MaxDirectMemorySize=2G -Dfile.encoding=utf-8"

exec "$JAVACMD" ${JAVA_OPTS} -DMYCAT_HOME="$BASEDIR" -classpath "conf:$CLASSPATH" org.opencloudb.MycatStartup "$@"
