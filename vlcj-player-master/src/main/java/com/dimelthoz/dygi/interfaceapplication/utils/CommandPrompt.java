package com.dimelthoz.dygi.interfaceapplication.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandPrompt {
    public static String execute (String command){
        Process p = null;
        BufferedReader input = null;

        try{
            p = Runtime.getRuntime().exec(command);
            String line;
            StringBuilder output = new StringBuilder();
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((line = input.readLine()) != null){
                output.append(line);
            }
            input.close();
            return output.toString();
        } catch (IOException e){
//            System.out.println(LogHelper.TEXT_RED + "IOException -> " + e + LogHelper.TEXT_RESET);
        } finally {
            if(p!=null){
                p.destroy();
            }
            if(input!=null){
                try{
                    input.close();
                } catch (IOException e){
//                    System.out.println(LogHelper.TEXT_RED + "Error while closing the input stream" + LogHelper.TEXT_RESET);
                }
            }
        }
        return "";
    }
}
