package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileToString {


    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    static double getSimilarity(File[] files, int i, int j) throws Exception {


        double percent = 0;
        int wordcount = 0;
        double wordpercent;
       String text1 = readFileAsString("C:\\Users\\Malcolm Co\\Desktop\\File\\"+files[i].getName());
       String text2 = readFileAsString("C:\\Users\\Malcolm Co\\Desktop\\File\\"+files[j].getName());

        String[] words1 = text1.split("\\W+");
        String[] words2 = text2.split("\\W+");



        wordpercent = (100.0/(((double)words2.length+(double) words1.length)/2));

        for(i =0; i< words2.length;i++){
            for (j=0; j<words1.length;j++) {
                if (words1[j].equalsIgnoreCase(words2[i])) {
                    words1[j]="";
                    percent = percent + wordpercent;
                }
            }
        }

        return percent;
    }

    public static void main(String[] args) throws Exception
    {

        int filecount = 0;
        File f = new File("C:\\Users\\Malcolm Co\\Desktop\\File");
        File[] files = f.listFiles();
        try {

            System.out.println("Files are:");
            filecount = files.length;
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String[][] percentmatrix = new String[filecount][filecount];

        for (int i =0; i <filecount;i++){
            for (int j=0; j<filecount;j++){
                String formatedString = String.format("%.2f",getSimilarity(files, i, j));
                percentmatrix[i][j] = formatedString;
            }
        }

        for (int i =0; i <filecount;i++) {
            for (int j = 0; j < filecount; j++) {
                System.out.print(percentmatrix[i][j] + "         ");
            }
            System.out.println();
        }

    }


}
