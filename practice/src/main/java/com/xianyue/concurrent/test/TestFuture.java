package com.xianyue.concurrent.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestFuture{
  public static void main(String[] args){
    final FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {
      public String call() {
        try{
          Thread.sleep(10000);
        }catch(Exception e){
          e.printStackTrace();
        }
        return "[future task finished]"; 
      }
    });
    Thread waiter = new Thread(new Runnable(){
      public void run(){
        System.out.println("Waiter started its work");
        try{
          Thread.sleep(5000);
        }catch(Exception e){
          e.printStackTrace();
        }
        System.out.println("Waiter finished work and start waiting future task to return");
        
        String ret = null;
        for(int i = 0; i < 10; i++){
          try{
            ret = ft.get(1000, TimeUnit.MILLISECONDS);
            break;
          }
          catch(TimeoutException e){
            //e.printStackTrace();
            System.out.println("Waiter waited for "+(i+1)+" second(s), but not returned yet. ret=["+ret+"].");
          }
          catch(Exception e){
            e.printStackTrace();
          }
        }
        System.out.println("Future task returned with:"+ret);
      }
    });
    waiter.start();
    ft.run();

  }
}
