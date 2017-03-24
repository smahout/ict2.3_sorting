package sorting;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import sorting.sortingmethods.BubbleSort;
import sorting.sortingmethods.AbstractSort;
import sorting.sortingmethods.InsertionSort;


/**
 *
 * @author Wouter
 */
public class Sorting extends Application{
    private final int n = 10;
    private final int min = 1;
    private final int max = 100;
    private final int width = 600;
    private final int height = 430;
    private final double offset_height = height * 0.15; 
    private final double offset_width = width * 0.05;      
    private final double pixel_height = (height / 5 * 4) / (max - min + 1);
    private final double rectangle_width = ((width - offset_width * 2) - (n - 1) * 3) / n;
   
    private ArrayList<Integer> data;
    private ArrayList<Rectangle> histogram;
    private Pane root;
    private Thread t;
    
    @Override
    public void start(Stage primaryStage) {
        // Create random list 
        data = new ArrayList();
        histogram = new ArrayList();
        int random;
        for(int i = 0; i < n; i++){
           random = (int) Math.round(new Double(Math.random() * new Integer(max - min).doubleValue())) + min;
           data.add(random);
        }
        root = new Pane();
        Scene scene = new Scene(root, width, height);
        AbstractSort sort = new InsertionSort(data, this);
        repaint();
        
        Button step = new Button("Sort step");
        
        t = new Thread(sort);
        t.start();
        step.setOnAction((ActionEvent e) -> {
            sort.stepSort();
        });
        Button play = new Button("Play");
        
        step.setLayoutX(100);
        Button stop = new Button("Stop");
        stop.setOnAction((ActionEvent e) -> {
            sort.stop();
            root.getChildren().remove(stop);
            root.getChildren().add(play);
        });
        play.setOnAction((ActionEvent e) -> {            
            sort.start();
            root.getChildren().remove(play);
            root.getChildren().add(stop);
        });
        root.getChildren().add(step);
        root.getChildren().add(play);
        primaryStage.setTitle("Sorting algorithms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void stop(){
        t.interrupt();
    }
    private void generateNewHistogram(){
        removeRectanglesFromPane(root);
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
    private void generateNewHistogram(int index_one, int index_two){
        removeRectanglesFromPane(root);
        histogram = new ArrayList();
        int count = 0;
        for(Integer i : data){
            
            double rectangle_height = pixel_height * i;
            double x = offset_width + histogram.size() * (rectangle_width + 3);
            double y = (height - offset_height * 2) + offset_height - rectangle_height;
            Rectangle r = new Rectangle(x,y,rectangle_width, rectangle_height);
            if(count == index_one || count == index_two){
                r.setFill(Color.RED);
            }
            else{
                r.setFill(Color.WHITE);
            }
            r.setStroke(Color.RED);
            histogram.add(r);
            count++;
        }
    }
    private void removeRectanglesFromPane(Pane p){
        for(Rectangle r : histogram){
            p.getChildren().remove(r);
        }
    }
    
    private void drawHistogramOnPane(Pane p){
        for(Rectangle r: histogram){
            p.getChildren().add(r);
        }
    }

    public void repaint(){
        generateNewHistogram();
        drawHistogramOnPane(root);
    }
    public void repaint(int i, int j){
        generateNewHistogram(i, j);
        drawHistogramOnPane(root);
    }
    public static void main(String[] args) {        
        launch(args);
    }

    
    
}
