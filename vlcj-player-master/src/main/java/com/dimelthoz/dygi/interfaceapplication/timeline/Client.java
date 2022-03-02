package com.dimelthoz.dygi.interfaceapplication.timeline;

import com.dimelthoz.dygi.interfaceapplication.utils.FileManager;

public class Client {
    private String videoName;
    private String videoUrl;
    private int videoLength;

    public Client(String videoUrl, int videoLength){
        this.videoUrl = videoUrl;
        this.videoLength = videoLength;
        this.videoName = FileManager.getNameFileFromPath(videoUrl);
    }

    public String getUrl(){
        return videoUrl;
    }

    public int getLength(){
        return videoLength;
    }

    public String getName(){
        return videoName;
    }

}
