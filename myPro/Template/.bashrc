# TATchaN's .bashrc

# ----- .bash_profile ----- 
#
# if [ -f ~/.bashrc ] ; then
# . ~/.bashrc
# fi
# -------------------------

# alias
alias ls='ls --color=auto --sort=extension'
alias la='ls -a --color=auto --sort=extension'
alias ll='ls -l --color=auto --sort=extension'
alias scc='gcc -g O2 -Wall -Wextra -std=c89 -pedantic'
alias emacs='emacs -nw'
alias e='emacs -nw'
alias rm='rm -i'
alias md='mkdir'
alias rd='rmdir'
alias cp='cp -i'
alias mv='mv -i'
alias grep='grep --color=auto'
alias df='df -h'
alias diff='colordiff'
alias a='./a.out'

function ping4(){
    ping $1 -c 4 
}
alias ping='ping4'

echo ''
echo 'hello TATchaN bash !'
echo ''
