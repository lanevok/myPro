# TATchaN's .bashrc

# ----- .bash_profile ----- 
#
# if [ -f ~/.bashrc ] ; then
# . ~/.bashrc
# fi
# -------------------------

HISTSIZE=50000
HISTTIMEFORMAT='%Y/%m/%d %H:%M:%S '
export HISTCONTROL=ignorespace 
export LANG='ja_JP.UTF-8'
export LC_ALL='ja_JP.UTF-8'
export PS1="[\u@\h \W]\\$ "

# Permission 644(022)
umask 022

# alias
alias ls='ls --color=auto --sort=extension'
alias la='ls -la --color=auto --sort=extension'
alias ll='ls -l --color=auto --sort=extension'
alias scc='gcc -g -O2 -Wall -Wextra -std=c89 -pedantic'
alias emacs='emacs -nw'
alias e='emacs -nw'
alias rm='rm -i'
alias rmf='rm -f'
alias md='mkdir'
alias rd='rmdir'
alias cp='cp -i'
alias mv='mv -i'
alias grep='grep --color=auto'
alias df='df -h'
alias diff='colordiff'
alias a='./a.out'
alias less='less -iM'
alias yum='yum -y'
alias upbrc='wget https://raw.githubusercontent.com/lanevok/myPro/master/myPro/Template/.bashrc -O ~/.bashrc'

function ping4(){
    ping $1 -c 4 
}
alias ping='ping4'

if [ "$SSH_TTY" != "" ]; then
    echo ''
    echo 'hello TATchaN bash ! [version: 1.4]'
    echo ''
fi
