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
public class InsertionSort extends AbstractSort implements Runnable{
    
    private ArrayList<Integer> data;
    private Sorting s;
    private int i;
    private int j;
    private boolean inner = false;
    public boolean running = false;
    private boolean finished = false;
    private boolean foundsomething = false;
    
    public InsertionSort(ArrayList<Integer> data_input, Sorting parent){
        data = data_input;
        s = parent;
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

    @Override
    public void stepSort() {
       if(data.size() > 1 && i != data.size()){
           if(j == 0){
               i++;
               j = i;
           }
           else{
                sort();
           }
       } 
       else{
           paintToParent();
       }
       
    }
    private void sort(){
        if(data.get(j) < data.get(j-1)){
            Collections.swap(data,j,j-1);
            j--;
            paintToParent(j);
        }
        else{
            j = 0;
            paintToParent();
        }
        
    }
    @Override
    public void start() {
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }
    private void paintToParent(int i){
        Platform.runLater(new Runnable(){
            @Override public void run(){
                s.repaint(i, i-1);
            }
        });
    }
    private void paintToParent(){
        Platform.runLater(new Runnable(){
            @Override public void run(){
                s.repaint();
            }
        });
    }

}
