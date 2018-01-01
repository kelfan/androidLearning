package com.kelfan.fantexteditorv2.Util;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 31/12/2017.
 * @notice: require permission to manipulate files
 * @result: 1 means success, 0 means fail
 */

public class FileWorker {

    /**
     * read file into string
     * @param sPath path of file
     * @param sFileName filename
     * @param iLocation 0 for data, 1 for external SD storage, 2 for secondary SD storage
     * @return String or "fail" if read file fail
     */
    public static String readFileToString(String sPath, String sFileName, int iLocation){
        try{
            File root = getStoragePath(iLocation,sPath);
            if(!root.exists()){
                throw new IOException();
            }
            File file = new File(root, sFileName);
            if (!file.exists()) {
                throw new IOException();
            }
            StringBuilder text = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine())!=null){
                text.append(line);
                text.append('\n');
            }
            br.close();
            return text.toString();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    public static String readStringFromData(String sPath, String sFileName) {
        return readFileToString(sPath, sFileName, 0);
    }
    public static String readStringFromSD(String sPath, String sFileName) {
        return readFileToString( sPath, sFileName, 1);
    }
    public static String readStringFromSecondSD(String sPath, String sFileName) {
        return readFileToString( sPath, sFileName, 2);
    }

    /**
     * read file into arraylist
     * @param sPath path of file
     * @param sFileName filename
     * @param iLocation 0 for data, 1 for external SD storage, 2 for secondary SD storage
     * @return String or "fail" if read file fail
     */
    public static ArrayList readFileToArrayList(String sPath, String sFileName, int iLocation){
        try{
            File root = getStoragePath(iLocation, sPath);
            if(!root.exists()){
                throw new IOException();
            }
            File file = new File(root, sFileName);
            ArrayList aList = new ArrayList();
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;
            if (!input.ready()){
                throw new IOException();
            }
            while ((line = input.readLine()) != null) {
                aList.add(line);
            }
            input.close();
            return aList;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList readListFromData(String sPath, String sFileName) {
        return readFileToArrayList(sPath, sFileName, 0);
    }
    public static ArrayList readListFromSD(String sPath, String sFileName) {
        return readFileToArrayList( sPath, sFileName, 1);
    }
    public static ArrayList readListFromSecondSD(String sPath, String sFileName) {
        return readFileToArrayList( sPath, sFileName, 2);
    }

    /**
     * function to save string into a file
     * @param sPath path to storage
     * @param sFileName filename
     * @param sBody string to storage
     * @param iLocation 0 for data, 1 for external SD storage, 2 for secondary SD storage
     * @param mode 0 for append, 1 for write
     */
    public static int saveFile(String sPath, String sFileName, String sBody, int iLocation, int mode){
        try{
            File root = getStoragePath(iLocation, sPath);
            if(!root.exists()){
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            if (!gpxfile.exists()) {
                gpxfile.createNewFile();
            }
            FileWriter writer;
            switch (mode) {
                case 0:
                    writer = new FileWriter(gpxfile,true);
                    writer.append(sBody);
                    break;
                case 1:
                    writer = new FileWriter(gpxfile,false);
                    writer.write(sBody);
                    break;
                default:
                    writer = new FileWriter(gpxfile,true);
                    writer.append(sBody);
                    break;
            }
            writer.flush();
            writer.close();
            return 1;
        }catch (IOException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static int writeToData(String sPath, String sFileName, String sBody) {
        return saveFile(sPath, sFileName, sBody, 0, 1);
    }
    public static int writeToSD(String sPath, String sFileName, String sBody) {
        return saveFile( sPath, sFileName, sBody, 1, 1);
    }
    public static int writeToSecondSD(String sPath, String sFileName, String sBody) {
        return saveFile( sPath, sFileName, sBody, 2, 1);
    }
    public static int appendToData(String sPath, String sFileName, String sBody) {
        return saveFile( sPath, sFileName, sBody, 0, 0);
    }
    public static int appendToSD(String sPath, String sFileName, String sBody) {
        return saveFile( sPath, sFileName, sBody, 1, 0);
    }
    public static int appendToSecondSD(String sPath, String sFileName, String sBody) {
        return saveFile( sPath, sFileName, sBody, 2, 0);
    }

    /**
     * get the Storage Path
     * @param iLocation 0 for data, 1 for external SD storage, 2 for secondary SD storage
     * @return File
     */
    public static File getStoragePath(int iLocation, String sPath){
        File root;
        switch (iLocation){
            case 0:
                root = new File(Environment.getDataDirectory(), sPath);
                break;
            case 1:
                root = new File(Environment.getExternalStorageDirectory(), sPath);
                break;
            case 2:
                root = new File(System.getenv("SECONDARY_STORAGE"), sPath);
                break;
            default:
                root = new File(Environment.getExternalStorageDirectory(), sPath);
                break;
        }
        return root;
    }
}
