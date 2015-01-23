#!/bin/bash
#cd `pwd`;cd ..
export SERVER_HOME="/var/app/rjra/rdb/rdb-server"

SERVER_CONF_DIR=$SERVER_HOME/conf
SERVER_LOG_DIR=$SERVER_HOME/../logs

#file
SERVER_ERROR_FILE=$SERVER_LOG_DIR/error.out
SERVER_PID_FILE=$SERVER_LOG_DIR/server.pid

###启动
function func_start(){

    MAINCLASS=com.hdg.rjra.rdb.server.Rdb

    LIB=$SERVER_HOME/lib
    for jar in $LIB/*
        do
            CLASSPATH=$CLASSPATH:$jar
    done
    CLASSPATH=$CLASSPATH:$SERVER_HOME/conf

    #Djava.ext.dirs=%CLASSPATH%
    JAVA_OPS="-Xmx2048m -Xms2048m -server -cp $CLASSPATH -DSERVER_LOG_DIR=$SERVER_LOG_DIR -DSERVER_HOME=$SERVER_HOME"

    nohup java $JAVA_OPS $MAINCLASS_rjra >/dev/null 2>$SERVER_ERROR_FILE &
    echo $! > $SERVER_PID_FILE
}

###停止
function func_stop(){
     [ ! -f $SERVER_PID_FILE ] && exit
     kill `cat $SERVER_PID_FILE`
     rm -f $SERVER_PID_FILE
}

function func_help()
{
    echo "    Usage: $0 [start|stop]"
}


if [ "$1" = 'start' ] ; then
    func_start
elif [ "$1" = 'stop' ] ; then
    func_stop
else
    func_help
fi
