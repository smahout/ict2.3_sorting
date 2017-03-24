/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting.sortingmethods;

/**
 *
 * @author Wouter
 */
public abstract class AbstractSort implements Runnable{
    @Override
    public abstract void run();
    public abstract void stepSort();   
    public abstract void start();
    public abstract void stop();
}
