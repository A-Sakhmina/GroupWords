package org.example.files;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileRW {

    public static List<String> readFileToList(String fileName) {

        Set<String> uniqueLines = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                // add line to list
                if (isCorrectLine(line) && !line.isEmpty())
//                if (isCorrectLine(line) && !line.isEmpty())
                    uniqueLines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> resultList = new ArrayList<>(uniqueLines);
        return resultList;
    }

    private static boolean isCorrectLine(String line){
        boolean isCorrect = true;
        long wordsQty = line.split(";").length;
        long quotaQty = line.chars().filter(ch -> ch == '\"').count();
        if (quotaQty != 2*wordsQty) isCorrect = false ;
        return isCorrect;

    }

    public static void printGroupsInFile(List<List<String>> wordsGroups, FileOutputStream outputStream) throws IOException {

        byte[] strToBytes;
        //print groups
        for (int i = 0; i < wordsGroups.size(); i++) {
            strToBytes = ("Группа " + (i+1)).getBytes();
            outputStream.write(strToBytes);
            outputStream.write(System.lineSeparator().getBytes());
            // print strings with words
            for (String word: wordsGroups.get(i)){
                strToBytes = word.getBytes();
                outputStream.write(strToBytes);
                outputStream.write(System.lineSeparator().getBytes());
            }

        }
    }

}


