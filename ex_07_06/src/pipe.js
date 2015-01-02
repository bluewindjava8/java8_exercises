#!/usr/bin/jrunscript



function pipe(){
    if( arguments.length < 2){
        print("At least two arguments are necessary.");
        return;
    }
    
    $EXEC(arguments[0]);
    for(var i = 1; i < arguments.length; i++){
        //print(i);
        $EXEC(arguments[i], $OUT);
    }
    print($OUT)
    
}

pipe('find /Users/bluewind/java8_exercises/ex_07_06/src', 'grep -v class', 'sort');

