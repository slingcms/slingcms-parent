#!/bin/bash

script="$0"
basename="$(dirname $script)"
cd $basename

# port used for accessing the app
if [ -z "$APP_PORT" ]; then
    APP_PORT=8080
fi

if [ $APP_PORT ]; then
    START_OPTS="${START_OPTS} -p ${APP_PORT}"
fi

# default JVM options
if [ -z "$APP_JVM_OPTS" ]; then
    APP_JVM_OPTS='-server -Xmx1024m -XX:MaxPermSize=256M -Djava.awt.headless=true'
fi

# debugging support
if [ -n "${APP_DEBUG_PORT}" ]; then
    APP_JVM_OPTS="${APP_JVM_OPTS} -agentlib:jdwp=transport=dt_socket,server=y,address=${APP_DEBUG_PORT},suspend=n"
fi

#host
if [ $APP_HOST ]; then
    APP_JVM_OPTS="${APP_JVM_OPTS} -Dorg.apache.felix.http.host=${APP_HOST}"
    START_OPTS="${START_OPTS} -a ${APP_HOST}"
fi

#mongo
if [ $APP_MONGO_HOST ]; then
    START_OPTS="${START_OPTS} -Doak.mongo.host=${APP_MONGO_HOST}"
fi
if [ $APP_MONGO_PORT ]; then
    START_OPTS="${START_OPTS} -Doak.mongo.port=${APP_MONGO_PORT}"
fi
if [ $APP_MONGO_DB ]; then
    START_OPTS="${START_OPTS} -Doak.mongo.db=${APP_MONGO_DB}"
fi

# timezone
if [ "$APP_TZ" ]; then
     APP_JVM_OPTS="${APP_JVM_OPTS} -Duser.timezone=${APP_TZ}"
fi

# verbose
if [ $APP_VERBOSE ]; then
    START_OPTS="${START_OPTS} -verbose"
fi

# nofork
if [ $APP_NOFORK ]; then
    START_OPTS="${START_OPTS} -nofork"
fi

# fork
if [ $APP_FORK ]; then
    START_OPTS="${START_OPTS} -fork"
fi
if [ $APP_FORKARGS ]; then
    START_OPTS="${START_OPTS} -forkargs ${APP_FORKARGS}"
fi

# low mem
if [ $APP_LOWMEMACTION ]; then
    START_OPTS="${START_OPTS} -low-mem-action ${APP_LOWMEMACTION}"
fi

# runmode
if [ $APP_RUNMODE ]; then
    APP_JVM_OPTS="${APP_JVM_OPTS} -Dsling.run.modes=${APP_RUNMODE}"
fi

# ------------------------------------------------------------------------------
# do not configure below this point
# ------------------------------------------------------------------------------

START_OPTS="${START_OPTS}"

JARFILE=`ls *quickstart*.jar | head -1`
if [ -z "$JARFILE" ]; then
  echo "No CMS JAR file found."
  exit 1
fi
mkdir -p sling/logs
(
  (
    java $APP_JVM_OPTS -jar $JARFILE $START_OPTS &
    echo $! > app.pid
  ) >> sling/logs/stdout.log 2>&1
) &
echo "Application started on port ${APP_PORT}!"
