/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting.sortingmethods;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Platform;
import sorting.Sorting;

/**
 *
 * @author Wouter
 */
public class BubbleSort extends AbstractSort implements Runnable{
    private int i = 0;
    private ArrayList<Integer> data;
    private Sorting s;
    public boolean running = false;
    private boolean finished = false;
    private boolean foundsomething = false;
    
    public BubbleSort(ArrayList<Integer> input_data, Sorting p){
        data = input_data;
        s = p;        
    }
    @Override
    public void run() {
        boolean interrupted = false;
        while(!interrupted){
            try {
                if(running){
                    stepSort();                    
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Bye!");
                interrupted = true;
            }
        }
    }
    public void stepSort(){
        if(i >= data.size() - 1){
            if(!foundsomething)
                finished = true;
            i = 0;
            foundsomething = false;
        }
        if(!finished){
            if(data.get(i) > data.get(i+1)){
                Collections.swap(data, i, i+1);
                foundsomething = true;
            }
            paintToParent(i);
            i++;
        }
        else{
            paintToParent();
        }
        
    }
    private void paintToParent(){
        Platform.runLater(() -> {
            s.repaint();
        });
    }
    private void paintToParent(int i){
        Platform.runLater(() -> {
            s.repaint(i, i+1);
        });
    }

    @Override
    public void start() {
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }
    
}
