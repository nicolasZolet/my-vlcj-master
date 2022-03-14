package com.dimelthoz.dygi.interfaceapplication.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManager {

    public static final String PATH_TEMPORARY_DOWNLOADS = "src/downloadsTemp/";
    public static final String PATH_MEDIA = "src/media/";

    public static String readFile(String filePath) {

        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder();

        if (doesFileExists(filePath)) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String content;
                while ((content = br.readLine()) != null) {
                    fileContent.append(content);
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e);
            }
        }

        return fileContent.toString();
    }

    public static ArrayList<String> getFolderContent(String folderPath){
        File f = new File(folderPath);
        return new ArrayList<String>(Arrays.asList(f.list()));
    }

    public static void writeTxtFile(String filePath, String content) {
        if (!doesFileExists(filePath)) {
            createNewFile(filePath);
        }

        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public static void createNewFile(String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            FileWriter writer = new FileWriter(file);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public static boolean doesFileExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(LogHelper.TEXT_RED + FileManager.class.getName() + " -> " + "File '" + filePath + "' does not exists." + LogHelper.TEXT_RESET);
        }
        return file.exists();
    }

    public static void moveFile(String pathFile, String toPath){
        System.out.println("\nMove file from -> " + pathFile + " to -> " + toPath);
        File file = new File(pathFile);
        file.renameTo(new File(toPath));
    }

    public static boolean createNewFolder(String folderPath){
        return new File(folderPath).mkdirs();
    }

    public static void deleFile(String folderPath){
        File file = new File(folderPath);
        if(file.delete()){
            System.out.println(LogHelper.TEXT_RED + "\nFile deleted -> " + folderPath + LogHelper.TEXT_RESET);
        }
    }

    public static String getAbsolutePath(File file) {
        if(file.exists()){
            return file.getAbsolutePath();
        }
        return "";
    }

    public static String getNameFileFromPath(String pathFile){
        return pathFile.substring(pathFile.lastIndexOf('/')+1);
    }
}
