package io.cft.internship;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private List<String> fileDataList(String fileName){
        List<String> fileDelayList = new ArrayList<>();
        try(FileReader fileReader = new FileReader(fileName))
        {
            try(BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String stringValue = bufferedReader.readLine();
                while (stringValue!=null){
                    fileDelayList.add(stringValue);
                    stringValue = bufferedReader.readLine();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return fileDelayList;
    }

    private static void deleteDataInFile(String fileName){
        try(FileWriter fileWriter = new FileWriter(fileName)) {
           fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void deleteDelayInCheckedFile(){
        deleteDataInFile("src/test/resources/checkedFileTest1.txt");
        deleteDataInFile("src/test/resources/checkedFileTest2.txt");
        deleteDataInFile("src/test/resources/checkedFileTest3.txt");
        deleteDataInFile("src/test/resources/checkedFileTest4.txt");
        deleteDataInFile("src/test/resources/checkedFileTest5.txt");
        deleteDataInFile("src/test/resources/checkedFileTest6.txt");
        deleteDataInFile("src/test/resources/checkedFileTest7.txt");
        deleteDataInFile("src/test/resources/checkedFileTest8.txt");
        deleteDataInFile("src/test/resources/checkedFileTest9.txt");
    }

    @Test
    void testMain1(){
        String[] args = new String[5];
        args[0] = "-a";
        args[1] = "-i";
        args[2] = "src/test/resources/checkedFileTest1.txt";
        args[3] = "src/test/resources/test12_1.txt";
        args[4] = "src/test/resources/test12_2.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest12.txt"), fileDataList(args[2]));
    }

    @Test
    void testMain2(){
        String[] args = new String[4];
        args[0] = "-i";
        args[1] = "src/test/resources/checkedFileTest2.txt";
        args[2] = "src/test/resources/test12_1.txt";
        args[3] = "src/test/resources/test12_2.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest12.txt"), fileDataList(args[1]));
    }

    @Test
    void testMain3(){
        String[] args = new String[5];
        args[0] = "-d";
        args[1] = "-i";
        args[2] = "src/test/resources/checkedFileTest3.txt";
        args[3] = "src/test/resources/test3_1.txt";
        args[4] = "src/test/resources/test3_2.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest3.txt"),
                fileDataList(args[2]));
    }

    @Test
    void testMain4(){
        String[] args = new String[5];
        args[0] = "-a";
        args[1] = "-s";
        args[2] = "src/test/resources/checkedFileTest4.txt";
        args[3] = "src/test/resources/test45_1.txt";
        args[4] = "src/test/resources/test45_2.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest45.txt"),
                fileDataList(args[2]));
    }

    @Test
    void testMain5(){
        String[] args = new String[4];
        args[0] = "-s";
        args[1] = "src/test/resources/checkedFileTest5.txt";
        args[2] = "src/test/resources/test45_1.txt";
        args[3] = "src/test/resources/test45_2.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest45.txt"),
                fileDataList(args[1]));
    }

    @Test
    void testMain6(){
        String[] args = new String[5];
        args[0] = "-d";
        args[1] = "-s";
        args[2] = "src/test/resources/checkedFileTest6.txt";
        args[3] = "src/test/resources/test6_1.txt";
        args[4] = "src/test/resources/test6_2.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest6.txt"),
                fileDataList(args[2]));
    }

    @Test
    void testMain7(){
        String[] args = new String[5];
        args[0] = "-i";
        args[1] = "src/test/resources/checkedFileTest7.txt";
        args[2] = "src/test/resources/test7_1.txt";
        args[3] = "src/test/resources/test7_2.txt";
        args[4] = "src/test/resources/test7_3.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest7.txt"),
                fileDataList(args[1]));
    }

    @Test
    void testMain8(){
        String[] args = new String[6];
        args[0] = "-d";
        args[1] = "-i";
        args[2] = "src/test/resources/checkedFileTest8.txt";
        args[3] = "src/test/resources/test8_1.txt";
        args[4] = "src/test/resources/test8_2.txt";
        args[5] = "src/test/resources/test8_3.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest8.txt"),
                fileDataList(args[2]));
    }

    @Test
    void testMain9(){
        String[] args = new String[5];
        args[0] = "-s";
        args[1] = "src/test/resources/checkedFileTest9.txt";
        args[2] = "src/test/resources/test9_1.txt";
        args[3] = "src/test/resources/test9_2.txt";
        args[4] = "src/test/resources/test9_3.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest9.txt"),
                fileDataList(args[1]));
    }

    @Test
    void testMain10(){
        String[] args = new String[5];
        args[0] = "-s";
        args[1] = "src/test/resources/checkedFileTest10.txt";
        args[2] = "src/test/resources/sfsfs.txt";
        args[3] = "src/test/resources/tsfsf.txt";
        args[4] = "src/test/resources/tesfsfs.txt";
        Main.main(args);
        assertEquals(fileDataList("src/test/resources/correctFileTest10.txt"),
                fileDataList(args[1]));
    }
}
