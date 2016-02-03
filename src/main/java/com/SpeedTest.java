package com;

import java.util.ArrayList;
import java.util.List;

import com.ecyrd.speed4j.StopWatch;


public class SpeedTest {
    
    public static void method(){
        StopWatch sw = new StopWatch("method");

        // Do the busy thing
        List<Integer> nums = new ArrayList<Integer>();
        for(int i=0;i<1000000;i++){
            nums.add(i);
        }
        
        sw.stop();
        System.out.println(sw);
        
    }
    
    public static void main(String[] args) {
        method();
    }

}
