#!/bin/bash

#向上取整
function ceil(){
  floor=`echo "scale=0;$1/1"|bc -l ` # 向下取整
  add=`awk -v num1=$floor -v num2=$1 'BEGIN{print(num1<num2)?"1":"0"}'`
  echo `expr $floor  + $add`
}

#定时清理root用户开辟的进程

#用户名
USERNAME="root"
THRESHOLD="85"
TIME=$(date "+%Y-%m-%d %H:%M:%S")

#获取root用户的所有进程号
PIDS=`ps -ef | grep $USERNAME | grep -v grep | awk '{print $2}'`

#循环PID
for PID in $PIDS; do
	#statements
	CPU_DATA=`pgrep java | xargs ps -u --pid $PID | awk '{print $3}'`
	for C in $CPU_DATA; do
		#如果CPU占用大于80%，杀死进程
		if [[ $C != '%CPU' ]]; then
			CEIL_C=`ceil $C`
			if [[ CEIL_C -gt $THRESHOLD ]]; then
                echo $TIME >> mining.log
				echo "root 用户的 $PID 已占用CPU $C" >> touch mining.log
				kill -9 $PID
				echo "已将 $PID 进程 killed" >> touch mining.log
			fi
		fi
	done
done