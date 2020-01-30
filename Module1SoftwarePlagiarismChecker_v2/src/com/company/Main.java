package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

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
        String text1 = readFileAsString("C:\\Users\\Jan Go\\Desktop\\Files\\"+files[i].getName());
        String text2 = readFileAsString("C:\\Users\\Jan Go\\Desktop\\Files\\"+files[j].getName());

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
        int pair1 = 0, pair2 = 0;
        Double percent1, percent2;
        File f = new File("C:\\Users\\Jan Go\\Desktop\\Files");
        File[] files = f.listFiles();
        getSimilarity sim = new getSimilarity();
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
        Double[][] percentmatrixdouble = new Double[filecount][filecount];

        for (int i =0; i <filecount;i++){
            for (int j=0; j<filecount;j++){
                String formatedString = String.format("%.1f",sim.getPercent(files, i, j));
                percentmatrixdouble[i][j] = sim.getPercent(files, i, j);
                percentmatrix[i][j] = formatedString;
            }
        }

        System.out.println("\n\nMatrix of Percentage Similarity (follow format from 'Files are:'): \n");
        System.out.format("%16s", "");
        for (int i=0; i<filecount; i++) {
            System.out.format("%16s",files[i].getName());
        }
        System.out.println();
        for (int i =0; i <filecount;i++) {
            System.out.format("%16s",files[i].getName());
            for (int j = 0; j < filecount; j++) {
                System.out.format("%16s", percentmatrix[i][j]);
            }
            System.out.println();
        }

        percent1 = 0.0;

        for (int i=0; i<filecount; i++) {
            for (int j=0; j<filecount; j++) {
                if(i==j) continue;
                if(percent1 < percentmatrixdouble[i][j]) {
                    percent1 = percentmatrixdouble[i][j];
                    pair1 = i;
                    pair2 = j;
                }
            }
        }

        String formatedString = String.format("%.1f", percent1);
        System.out.println("Highest word percentage similarity: " + formatedString);
        System.out.println("Pair: " + files[pair1].getName() + " & " + files[pair2].getName());

    }


}
