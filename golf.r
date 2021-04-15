options(scipen = 999)
a=strtoi(commandArgs()[6])
x=0
y=1
while(a){y=y+x;x=y-x;a=a-1}
floor(lchoose(y,x)/log(2))