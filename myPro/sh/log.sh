#!/bin/bash

# e.g.
# 2015年 12月 19日 土曜日 21:24:05 JST lanevok 

date | tr '\n' ' '  && w -h | cut -d ' ' -f 1 | tr '\n' ' '
echo ""
