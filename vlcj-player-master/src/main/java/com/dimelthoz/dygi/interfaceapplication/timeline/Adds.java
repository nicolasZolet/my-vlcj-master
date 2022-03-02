package com.dimelthoz.dygi.interfaceapplication.timeline;

public class Adds {
    private static int currentContainer=0;
    private static int currentClientVideo =0;
    private static long timerStartVideo=System.currentTimeMillis();

    public static void nextContainer(){
        currentClientVideo = 0;
        currentContainer++;
        if (currentContainer >= Timeline.getClientContainers().size()){
            currentContainer = 0;
        }
    }

    public static int getIndexClientVideo(){
        return currentClientVideo;
    }

    public static void refreshCurrentAdds(){
        currentClientVideo = 0;
        currentContainer = 0;
    }

    public static void setContainer(int container){
        currentContainer = container;
    }

    public static int getContainer(){
        return currentContainer;
    }

    public static void nextClientVideo(){
        currentClientVideo++;
        if (currentClientVideo >= Timeline.getClientContainers().get(currentContainer).getLength()){
            currentClientVideo = 0;
        }
    }

    public static String getClientVideo(){
        return Timeline.getClientContainers().get(currentContainer).getClient(currentClientVideo).getName();
    }

    public static void updateStartTimer(){
        timerStartVideo = System.currentTimeMillis();
    }

    public static int getVideoLength(){
        return Timeline.getClientContainers().get(currentContainer).getClient(currentClientVideo).getLength();
    }

    public static boolean hasVideoEnd(){
        return (System.currentTimeMillis()/1000) - (timerStartVideo/1000) > getVideoLength();
    }

}
