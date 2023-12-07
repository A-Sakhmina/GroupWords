package org.example.words;

import java.util.*;

public class WordGroups {
    public static List<List<String>> findGroups(List<String> lines)
    {
        List<Map<String, Integer>> wordsToGroupsNumbers = new ArrayList<>(); //[word_position:{word:group number}]
        List<List<String>> linesGroups = new ArrayList<>(); //[group number:[group lines]]
        Map<Integer, Integer> mergedGroupNumberToFinalGroupNumber = new HashMap<>();
        for (String line : lines)
        {
            String[] words = line.split(";");
            TreeSet<Integer> foundInGroups = new TreeSet<>();
            List<NewWord> newWords = new ArrayList<>();
            for (int i = 0; i < words.length; i++)
            {
                String word = words[i];

                if (wordsToGroupsNumbers.size() == i)
                    wordsToGroupsNumbers.add(new HashMap<>());

                if (word.equals(""))
                    continue;

                //find group for word or add new word+position
                Map<String, Integer> wordToGroupNumber = wordsToGroupsNumbers.get(i);
                Integer wordGroupNumber = wordToGroupNumber.get(word);
                if (wordGroupNumber != null)
                {
                    while (mergedGroupNumberToFinalGroupNumber.containsKey(wordGroupNumber))
                        wordGroupNumber = mergedGroupNumberToFinalGroupNumber.get(wordGroupNumber);
                    foundInGroups.add(wordGroupNumber);
                }
                else
                {
                    newWords.add(new NewWord(word, i));
                }
            }
            int groupNumber;
            if (foundInGroups.isEmpty())
            {
                groupNumber = linesGroups.size();
                linesGroups.add(new ArrayList<>());
            }
            else
            {
                groupNumber = foundInGroups.first();
            }

            // add position+word
            for (NewWord newWord : newWords)
            {
                wordsToGroupsNumbers.get(newWord.position).put(newWord.value, groupNumber);
            }
            //regroup lines
            for (int mergeGroupNumber : foundInGroups)
            {
                if (mergeGroupNumber != groupNumber)
                {
                    mergedGroupNumberToFinalGroupNumber.put(mergeGroupNumber, groupNumber);
                    //move lines in another group
                    linesGroups.get(groupNumber).addAll(linesGroups.get(mergeGroupNumber));
                    //remove unnecessary group number
                    linesGroups.set(mergeGroupNumber, null);
                }
            }
            // add line to group
            linesGroups.get(groupNumber).add(line);
        }
        linesGroups.removeAll(Collections.singleton(null));
        return linesGroups;
    }

    public static int countGroupsWithFewStrings(List<List<String>> wordsGroups){
        int groupsQty = 0;
        for (List<String> group: wordsGroups){
            if(group.size()>1) groupsQty ++;
        }
        return groupsQty;
    }

}
