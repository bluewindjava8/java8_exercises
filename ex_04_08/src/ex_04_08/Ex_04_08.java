package ex_04_08;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ex_04_08 extends Application {

    @Override
    public void start(Stage primaryStage) {

        Element labelElem = getLabelElement();
        String text = labelElem.getAttribute("text");
        boolean underline = Boolean.parseBoolean(labelElem.getAttribute("underline"));
        
        Label label = new Label();
        label.textProperty().set(text);
        label.underlineProperty().set(underline);
        

        Pane root = new Pane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Element getLabelElement(){
        try {

            File fXmlFile = new File("src/ex_04_08/FXML.fxml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Label");
            Element elem = (Element) nList.item(0);
            return elem;

        } catch (Exception e) {
            throw new RuntimeException("Parse error.");
        }
    }

}
