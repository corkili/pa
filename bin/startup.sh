#!/usr/bin/env bash
bin=`dirname "$0"`
PA_HOME=`cd "$bin/.."; pwd`

echo $PA_HOME

nohup java -jar $PA_HOME/lib/personal-assistant-1.0.0.jar > ../personal-assistant.log 2>&1 &

PID=`ps aux | grep $PA_HOME | grep -v grep | awk '{print $2}'`

echo "start successfully, pid: $PID"
