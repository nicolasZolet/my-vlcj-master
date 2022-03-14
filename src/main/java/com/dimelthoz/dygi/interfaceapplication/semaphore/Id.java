package com.dimelthoz.dygi.interfaceapplication.semaphore;

public class Id {
    private static int ID = 1;

    public static int getId(){
        return ID;
    }

    public static void setId(int ID){
        Id.ID = ID;
    }
}
