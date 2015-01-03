#!/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/jre/bin/jjs
var age;
if(arguments[0] != undefined){
    age = Number(arguments[0]);
}else if($ENV.AGE != undefined){
    age = Number($ENV.AGE);
}else{
    age = Number(readLine('Age : '));
}
if(isNaN(age)){
    print("Input age is not a number!");
    exit(-1);
}
print('Next year, you will be ' + (1 + age));