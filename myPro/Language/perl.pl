# perl program
# compile = perl -cw filename.pl
# Run = perl filename.pl
# ( Run need not compile ) 

use strict;
use warnings;
# (Run) This is Program Error & Warning Check Header

# Code Setting
use utf8;

=pod
This is long commnet
=cut

#comment

print "Hello World!\n";
print 'single dash ok!'; # \n cannot
print "\nHappy"." New "."Year\n"; # . connect

# my = this block
my $num = 10;
print $num;
print "\n";

print 123;
print "\n";
print 45.7;
print "\n";
print 0xA;  # 0x=16 ... A=10
print "\n";
print 0b101; # 0b=2 ... 101=5
print "\n";

my $str = "Good Night";
print "$str\n";

# $=sukara
# @=hairetu
# %=hash

my ($a,$b);
$a=99;
$b=$a++;
print $a,$b;
print "\n";

print "This is $a\n";     #Normal
print "This is ".$a."\n";
print 'This is $a\n"';
print "This is ".$a."\n";

my $c = "def";
print "abc$c ghi\n";
print "abc${c}ghi\n";

print 2**10;  # ** = 2^10
print "\n";

my $d="Hi ";
print $d x 3;

my $point=75;

# need { 1bun }
if($point>=60){
    print "OK!";
}
else{
    print "NG!";
}

print $point>=60? "ok!":"ng!";

unless($point<60){  # unless == !(if)
    print "OK!\n";
}
else{
    print "NG!\n";
}

my $name="tat";

if($name eq "tat"){
    print "name eq tat!\n";
}

=pod

moji hikaku
==  eq
!=  ne
>   gt
>=  ge
<   lt
<=  le

=cut

# this (if) is ok
my $e=7;
print $e if $e>5;
print $e if $e>9;
print "\n";

my $cnt=0;

while($cnt<3){           # until == !(while)
    print "cnt=$cnt\n";
    $cnt++;
}

for($cnt=0;$cnt<10;$cnt++){
    # redo label
    if($cnt==3){
	next;     # next = continue(C)
    }
    print $cnt;
    if($cnt==5){
	$cnt+=2;
	redo;     # jump
    }
    if($cnt==8){
	last;     # last = break(C)  / label is OK
    }
}
print "\n";

my @fruit = ("apple","melon","banana");

foreach my $tmp (@fruit){
    print "$tmp\n";
}

$fruit[1]="melomelon";

for(my $i=0;$i<3;$i++){
    print "$fruit[$i]\n";
}

foreach (@fruit){
    print "$_\n";
}

my ($ver1,$ver2,$ver3);
($ver1,$ver2,$ver3)=("Mon","Tue","Wed");

my ($num1,$num2);
$num1=30;
$num2=20;
($num1,$num2)=($num2,$num1);  # exchange
print "$num1 $num2\n";

my @num;
$num[0]=10;
$num[1]=9;
$num[2]=24;
@num=(3,7);  # init / delete 24

foreach (@num){
    print "$_\n";
}
@num=();  # init

my @perf1 = qw/ Tokyo Osaka Nagoya /;
# / equal
my @perf2 = qw< Tokyo Osaka Nagoya >;
# () {} [] <> equal too.

my @count = (1..3,5,7..9);
foreach (@count){
    print "$_";
}
print "\n";

my $length=@count;        # count.length() (java)
print "length=$length\n";

my @str = ("1st","2nd","5th");
$str[++$#str]="3rd";
#  $#str ==> final
foreach (@str){
    print "$_ ";
}
print "\n";

print "Rank: @str\n";
$"=",";     # special kugiri
print "Rank: @str\n";

$#str=1;            # element index
foreach (@str){
    print "$_ ";
}
print "\n";

my %address; #hash
$address{'katou'}="tokyo";
my $name1="katou";
print "$address{'katou'}\n";
print "$address{$name1}\n";

%address=("katou","tokyo","koike","yokohama");
print "$address{'koike'}\n";

%address=(
    "kanagawa" => "yokohama",
    "mie" => "tsu"
    );
print "$address{'mie'}\n";

print "%address\n"; # cannot
foreach my $key (keys(%address)){
    print "$address{$key}\n";
}
while (my ($key, $value)=each(%address)){
    print "key=$key, value=$value\n";
}

# subroutine
sub hello{
    print "hello ";
}
&hello;
&hello;

#void
&add1(10,8);
&add1(5,17);

sub add1{
    my $total1=$_[0]+$_[1];  # hikusu
    my ($first,$second) = @_;
    my $total2=$first+$second;

    if($first==5){
	return;
    }

    print "$total1\n";
    print "$total2\n";
}

#int
my $sum=&add2(10,8);
print "$sum\n";

sub add2{
    my ($num1,$num2)=@_;
    $num1+$num2;
}

printf("%03d\n",24);

# command line ... $ARGV[0],$ARGV[1] --> ex. @ARGV=2

my $line = <STDIN>;
chomp($line);           # \n delete
print "line=$line\n";

while($line=<STDIN>){
    chomp($line);
    print "loop line=$line\n";
}

# end
