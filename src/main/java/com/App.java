package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        
        List<ObjectOne> list = new ArrayList<>();
        list.add(new ObjectOne());
        list.add(new ObjectOne());
        list.add(new ObjectOne());
        
    }
    
    
    
}

class ObjectOne{
    private int x;
    private int y;
}
