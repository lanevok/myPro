#!/bin/bash

log_file=/var/www/html/moniter.txt
host_list=/var/www/html/ping.list

echo $(date +%F" "%T" "%A) > $log_file

for host in `cat "$host_list"`
do
    /bin/ping $host -c 1 > /dev/null
    case `echo $?` in
        0)    echo $(/bin/echo "OK" && /bin/echo $host) >> $log_file;;
        1)    echo $(/bin/echo "NG" && /bin/echo $host) >> $log_file;;
    esac
done
