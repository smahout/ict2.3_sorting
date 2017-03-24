/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting.sortingmethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javafx.application.Platform;
import sorting.Sorting;

/**
 *
 * @author Wouter
 */
public class QuickSort extends AbstractSort implements Runnable{

    private boolean running = false;
    private int pivot_index;
    private ArrayList<Integer> data;
    private Sorting s;
    private Stack<int[]> sorting_steps;
    

    public QuickSort(ArrayList<Integer> data_input, Sorting parent){
        this.sorting_steps = new Stack();
        this.data = data_input;
        this.s = parent;
        int[] initial = new int[2];
        initial[0] = 0;
        initial[1] = data.size() - 1;
        this.sorting_steps.push(initial);
    }
    @Override
    public void run() {
        boolean interrupted = false;
        while(!interrupted){
            try {
                if(running){
                    stepSort();                    
                }
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Bye!");
                interrupted = true;
            }
        }
    }

    @Override
    public void stepSort() {
        // Couldn't figure out how to step this within an hour so I stopped.
        int[] this_step = sorting_steps.pop();
        qsort(this_step[0], this_step[1]);     
        paintToParent();
    }

    private void qsort(int low, int high){
        int i = low;
        int j = high;
        int pivot = data.get(low + (high - low)/2);
        while(i <= j){
            while(data.get(i) < pivot){
                i++;
            }
            while(data.get(j) > pivot){
                j--;
            }
            if(i <= j){
                Collections.swap(data,i,j);
                i++;
                j--;
            }
            if(low < j)
                addStep(low,j);
            if(i < high)
                addStep(i,high);
        }  
    }
    private void addStep(int l, int h){
        int[] step = new int[2];
        step[0] = l;
        step[1] = h;
        sorting_steps.push(step);
    }
    @Override
    public void start() {
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }
    private void paintToParent(){
        Platform.runLater(() -> {
            s.repaint();
        });
    }
}
