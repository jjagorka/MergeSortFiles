package io.cft.internship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    private static boolean firstParameter(String arg){
        return Objects.equals(arg, "-a");
    }

    private static List<String> fourthParameter (int start,String[] args){
        List<String> inputFilesNames = new ArrayList<>();
        for (int i = start;i<args.length;i++){
            inputFilesNames.add(args[i]);
        }
        return inputFilesNames;
    }

    private static boolean secondParameter(String arg){
        return Objects.equals(arg, "-s");
    }

    public static void main(String[] args) {
        try {
            if ((Objects.equals(args[0], "-a"))||("-d".equals(args[0]))){
                boolean isAscending = firstParameter(args[0]);
                boolean isString = secondParameter(args[1]);
                String outputFileName = args[2];
                List<String> inputFilesNames = fourthParameter(3,args);
                Solution.createSortFile(outputFileName,inputFilesNames,isAscending,isString);
                System.out.println("File created successfully.");
            }
            else {
                if (("-s".equals(args[0]))||("-i".equals(args[0]))){
                    boolean isAscending =  true;
                    boolean isString = secondParameter(args[0]);
                    String outputFileName = args[1];
                    List<String> inputFilesNames = fourthParameter(2,args);
                    Solution.createSortFile(outputFileName,inputFilesNames,isAscending,isString);
                    System.out.println("File created successfully.");
                }
            else{
                System.out.println("Arguments passed incorrectly!");
            }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments passed incorrectly!");
        }



    }

}
