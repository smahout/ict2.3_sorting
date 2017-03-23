package sorting;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Wouter
 */
public class Sorting extends Application {
    private final int n = 60;
    private final int min = 1;
    private final int max = 100;
    private final int width = 900;
    private final int height = 400;
   
    private ArrayList<Integer> data;
    private ArrayList<Rectangle> histogram;
    private Pane root;
    @Override
    public void start(Stage primaryStage) {
        // Create random list 
        data = new ArrayList();
        histogram = new ArrayList();
        int random;
        for(int i = 0; i < n; i++){
           random = new Double(Math.random() * new Integer(max - min).doubleValue()).intValue() + min;
           data.add(random);
        }
        root = new Pane();
        Scene scene = new Scene(root, width, height);
        
        generateNewHistogram();
        //drawHistogramOnPane(root);
        Button step = new Button("Sort step");
        step.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e){
                generateNewHistogram();
                drawHistogramOnPane(root);
            }
        });
        Button play = new Button("Play");
        step.setLayoutX(100);
        Button stop = new Button("Stop");
        root.getChildren().add(step);
        root.getChildren().add(play);
        primaryStage.setTitle("Sorting algorithms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateNewHistogram(){
        for(Rectangle r : histogram){
            root.getChildren().remove(r);
        }
        
        double offset_height = height * 0.15; 
        double offset_width = width * 0.05;      
        double pixel_height = (height / 5 * 4) / (max - min);
        double rectangle_width = ((width - offset_width * 2) - (n - 1) * 3) / n;
        histogram = new ArrayList();
      
        for(Integer i : data){
            double rectangle_height = pixel_height * i;
            double x = offset_width + histogram.size() * (rectangle_width + 3);
            double y = (height - offset_height * 2) + offset_height - rectangle_height;
            Rectangle r = new Rectangle(x,y,rectangle_width, rectangle_height);
            r.setFill(Color.WHITE);
            r.setStroke(Color.RED);
            histogram.add(r);            
        }
    }
    
    private void drawHistogramOnPane(Pane p){
        for(Rectangle r: histogram){
            
            p.getChildren().add(r);
        }
    }


    public static void main(String[] args) {        
        launch(args);
    }
    
}
