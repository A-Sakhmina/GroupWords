package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static org.example.files.FileRW.*;
import static org.example.words.WordGroups.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String filename = args[0];
        String outputFileName = "output.txt";
        FileOutputStream outputStream = new FileOutputStream(outputFileName);
        int groupsWithFewStringsQty;

        List<String> list = readFileToList(filename);
        List<List<String>> wordsList = findGroups(list);
        groupsWithFewStringsQty = countGroupsWithFewStrings(wordsList);

        outputStream.write(("Получившиееся число групп с более чем одним элементом: " + groupsWithFewStringsQty).getBytes());
        outputStream.write(System.lineSeparator().getBytes());
        printGroupsInFile(wordsList, outputStream);
        outputStream.close();
    }

}





