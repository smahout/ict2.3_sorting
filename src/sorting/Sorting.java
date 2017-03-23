/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.ArrayList;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Wouter
 */
public class Sorting extends Application {
    private final int n = 50;
    private final int min = 1;
    private final int max = 100;
    private final int width = 600;
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
        for(Rectangle r: histogram){
            System.out.println(r);
            root.getChildren().add(r);
        }
        
        primaryStage.setTitle("Sorting algorithms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateNewHistogram(){
        for(Rectangle r : histogram){
            root.getChildren().remove(r);
        }
        histogram = new ArrayList();
        for(Integer i : data){
            // Max height = 80% of height
            // Offset = 10% from bottom, 10% from top
            int offset_height = height / 10;    // 10%
            int offset_width = width / 20;      // 5%
            double pixel_height = (height / 5 * 4) / (max - min);
            double rectangle_width = (width - offset_width * 2) / n;
            double rectangle_height = pixel_height * i;
            double x = offset_width + histogram.size() * rectangle_width;
            double y = (height / 5 * 4) + offset_height - rectangle_height;
            Rectangle r = new Rectangle(x,y,rectangle_width, rectangle_height);
            r.setFill(Color.BLUE);
            histogram.add(r);            
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        launch(args);
    }
    
}
