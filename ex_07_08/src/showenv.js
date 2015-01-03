#!/usr/bin/jrunscript -f

for(var name in $ENV){
    print(name + ' = ' + $ENV[name]);
}