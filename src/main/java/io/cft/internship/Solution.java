package io.cft.internship;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private static File createNewFile(String fileName)  {
        return new File(fileName);
    }

    private static void closeAllFileReaders(List<FileReader> fileReaderList){
        for (FileReader fileReader : fileReaderList){
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Stream could not be closed");
            }
        }
    }

    private static String bufferedReaderReadLine(BufferedReader bufferedReader){
        try {
            return  bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Failed to read line");
        }
        return null;
    }

    private static FileReader createFileReader(String fileName) {
        try {
            return new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("The file with the name "+fileName + " is missing" +
                    " and will not participate in the sorting");
        }
        return null;
    }

    private static FileWriter createFileWriter(File file) {
        try {
            return new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void fileWriterClose(FileWriter fileWriter){
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Stream could not be closed");
        }
    }

    private static void writeInFile(FileWriter fileWriter, String string) {
        try {
            fileWriter.write(string);
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Can't write to file");
        }
    }

    private static void bufferReaderClose(BufferedReader bufferedReader) {
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("Stream could not be closed");
        }
    }

    private static void removeBufferedReaders(Map<BufferedReader,String> brList, List<BufferedReader> brForDelete){
        for (BufferedReader br : brForDelete){
            brList.remove(br);
            bufferReaderClose(br);
        }
    }

    private static int createExtremeValue (boolean isAscending){
        int extremeValue = Integer.MAX_VALUE;
        if (!isAscending){
            extremeValue = Integer.MIN_VALUE;
        }
        return extremeValue;
    }

    private static int stringLen(String str){
        int result = str.length();
        for (int i = 0;i<result;i++){
            if (str.charAt(i)==' '){
                throw new NumberFormatException();
            }
        }
        return result;
    }

    private static Map<BufferedReader,String> createHashMap(List<String>listOfIncomingFilesNames,
                                                           List<FileReader> fileReaderList)  {

        Map<BufferedReader,String> bufferedReaders = new HashMap<>();
        for (String fileName:listOfIncomingFilesNames){
            FileReader fileReader = createFileReader(fileName);
            if (fileReader!=null){
                fileReaderList.add(fileReader);
            }
            if (fileReader!=null){
                BufferedReader reader = new BufferedReader(fileReader);
                String value = bufferedReaderReadLine(reader);
                if (value!=null){
                    bufferedReaders.put(reader,value);
                }
                else {
                    bufferReaderClose(reader);
                }
            }
        }
        return bufferedReaders;
    }

    private static void mergeSortIntegersValues(Map<BufferedReader,String> bufferedReaders,
                                               FileWriter fileWriter, boolean isAscending) {

        int extremeValue = createExtremeValue(isAscending);
        int lastRecordedNumber = createExtremeValue(!isAscending);

        while (!bufferedReaders.isEmpty()) {
            int value = extremeValue;
            BufferedReader reader = null;
            List<BufferedReader> bufferedReadersForDelete = new ArrayList<>();
            for (BufferedReader br : bufferedReaders.keySet()) {
                try {
                    String stringValue = bufferedReaders.get(br);
                    if (stringValue==null){
                        bufferedReadersForDelete.add(br);
                    }
                    else{
                        int tempValue = Integer.parseInt(stringValue);
                        if ((tempValue < value)==isAscending) {
                            if ((tempValue > lastRecordedNumber)==isAscending){
                                value = tempValue;
                                reader = br;
                            }
                            else {
                                if ((tempValue==lastRecordedNumber)){
                                    value = tempValue;
                                    reader = br;
                                }
                                else {
                                    bufferedReaders.put(br, bufferedReaderReadLine(br));
                                    reader = null;
                                    break;
                                }
                            }
                        }
                    }
                }
                catch (NumberFormatException e){
                    bufferedReaders.put(br,bufferedReaderReadLine(br));
                    reader = null;
                    break;
                }
            }
            removeBufferedReaders(bufferedReaders,bufferedReadersForDelete);
            if (reader!=null){
                lastRecordedNumber = value;
                writeInFile(fileWriter, Integer.toString(value));
                bufferedReaders.put(reader, bufferedReaderReadLine(reader));
            }
        }
    }

    private static void mergeSortStringValues(Map<BufferedReader,String> bufferedReaders,
                                               FileWriter fileWriter, boolean isAscending) {

        int extremeValue = createExtremeValue(isAscending);
        int lastRecordedNumber = createExtremeValue(!isAscending);

        while (!bufferedReaders.isEmpty()) {
            int value = extremeValue;
            String stringToWrite = null;
            BufferedReader reader = null;
            List<BufferedReader> bufferedReadersForDelete = new ArrayList<>();
            for (BufferedReader br : bufferedReaders.keySet()) {
                try {
                    String stringValue = bufferedReaders.get(br);
                    if (stringValue==null){
                        bufferedReadersForDelete.add(br);
                    }
                    else{
                        int tempValue = stringLen(stringValue);
                        if ((tempValue < value)==isAscending) {
                            if ((tempValue > lastRecordedNumber)==isAscending){
                                value = tempValue;
                                reader = br;
                                stringToWrite = stringValue;
                            }
                            else {
                                if ((tempValue==lastRecordedNumber)){
                                    value = tempValue;
                                    reader = br;
                                    stringToWrite = stringValue;
                                }
                                else {
                                    bufferedReaders.put(br, bufferedReaderReadLine(br));
                                    reader = null;
                                    break;
                                }
                            }
                        }
                    }
                }
                catch (NumberFormatException e){
                    bufferedReaders.put(br,bufferedReaderReadLine(br));
                    reader = null;
                    break;
                }
            }
            removeBufferedReaders(bufferedReaders,bufferedReadersForDelete);
            if (reader!=null){
                lastRecordedNumber = value;
                writeInFile(fileWriter, stringToWrite);
                bufferedReaders.put(reader, bufferedReaderReadLine(reader));
            }
        }
    }

    public static void createSortFile(String outputFileName,
                                                  List<String> inputFilesNames,
                                                  boolean isAscending,boolean isStrings) {

        FileWriter fileWriter = createFileWriter(createNewFile(outputFileName));
        List<FileReader> fileReaderList = new ArrayList<>();
        Map<BufferedReader, String> fileReaders = createHashMap(inputFilesNames,fileReaderList);
        if (isStrings){
            mergeSortStringValues(fileReaders,fileWriter,isAscending);
        }
        else {
            mergeSortIntegersValues(fileReaders,fileWriter, isAscending);
        }
        closeAllFileReaders(fileReaderList);
        assert fileWriter != null;
        fileWriterClose(fileWriter);
    }
}

