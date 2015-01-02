function getArrayLists(n){
    var arrayLists = [];
    for(var i = 0; i < n ; i++){
        var arrayList =new ( Java.extend(java.util.ArrayList, {
           add : function(x){
               print('Adding ' + x);
               return Java.super(arrayList).add(x);
           } 
        }));
        
        arrayLists[i] = arrayList;
    }
    return arrayLists;
}

var arrayLists = getArrayLists(3);
print(arrayLists.length);
arrayLists[0].add("hello");
arrayLists[0].add("world");

arrayLists[2].add("my");
arrayLists[2].add("god");

//arrayLists[3].add("out");
