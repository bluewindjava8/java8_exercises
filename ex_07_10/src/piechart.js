#!/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/jre/bin/jjs
function getDataFromFile(path){
    var prop = new java.util.Properties();
    prop.load(new java.io.FileInputStream(path));

    var map = new java.util.HashMap();
    
    for each(var key in prop.keySet().toArray()){
        //print(key + "=" + prop.getProperty(key));
        map.put(key, prop.getProperty(key));
    }
    
    return map;

}

$STAGE.title = "Imported Fruits by javascript";
$STAGE.width = 500;
$STAGE.height = 500;
var chartData = getDataFromFile("/Users/bluewind/java8_exercises/ex_07_10/input.txt");

         

pieChartData = javafx.collections.FXCollections.observableArrayList();
for each(var entry in chartData.entrySet().toArray()){
    pieChartData.add(new javafx.scene.chart.PieChart.Data(entry.getKey(), entry.getValue())); 
}
        
var chart = new javafx.scene.chart.PieChart(pieChartData);
chart.title = "Imported Fruits by javascript.";
var scene = new javafx.scene.Scene(chart);
$STAGE.scene = scene;