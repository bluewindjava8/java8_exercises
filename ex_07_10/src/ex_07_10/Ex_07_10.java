

package ex_07_10;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


//このプルグラムの開発にjavaを使おうかjavascriptを使おうか、難しさはあまり変わらないと思う。
public class Ex_07_10 extends Application {
    
    @Override
    public void start(Stage stage) {

        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);
        
        Map<String, Integer> chartData = getDataFromFile("input.txt");

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(Entry<String, Integer> entry : chartData.entrySet()){
           pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue())); 
        }
                

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        Scene scene = new Scene(chart);        
        stage.setScene(scene);
        stage.show();

    }
    
    
    private Map<String, Integer> getDataFromFile(String path){
        Properties prop = new Properties();  
          
        try {  
            prop.load(new FileInputStream(path));  
              
            //System.out.println(prop.entrySet());
        } catch(IOException e) {  
            e.printStackTrace();  
        }  
        
        Set<Entry<Object, Object>> entrySet = prop.entrySet();
        Map<String, Integer> map = new HashMap<>();
        for(Entry<Object, Object> entry : entrySet){
            String key = (String) entry.getKey();
            Integer value = Integer.valueOf((String)entry.getValue());
            map.put(key, value);
        }
        
        return map;
    }
    
}
