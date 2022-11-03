import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * File converter takes in a html and counts the frequency of each word.
 * @author Johnny Bienaime
 * @version 1.0
 *
 */
public class Fileconverter{
    /**
     * @param wordArray stores the file before splitting
     */
    public static ArrayList<String> wordArray;


    /**
     *
     * @param sortedWord
     * @return
     */
    public static List<String> sort(List<String> sortedWord) {
        Collections.sort(sortedWord, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return extractInt(o2) - extractInt(o1);
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
        return sortedWord;
    }

    /**
     *
     * @param poem
     * @param resultsRange
     * @throws FileNotFoundException
     */
    public static void textT(File poem, int resultsRange) throws FileNotFoundException {
        Scanner reader = new Scanner(poem);
        ArrayList<String> list = new ArrayList<String>();
        int line = 0;
        while (reader.hasNextLine()) {
            String reading = reader.nextLine();
            reading = reading.toLowerCase();
            if (line > 77 && line < 243) {
                reading = reading.replaceAll("\\<[^>]*>", "");
                reading = reading.replaceAll("&mdash;", " ");
                reading = reading.replaceAll("[^a-zA-Z0-9\\s+]", "");
                String broken[] = reading.split(" ");
                List<String> splitList = new ArrayList<String>();
                splitList = Arrays.asList(broken);

                for (String s : splitList) {
                    if (!s.isEmpty()) {
                        list.add(s);
                    }
                }
            }
            line++;
        }
        Collections.sort(list);
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(list);
        ArrayList<String> reference = new ArrayList<String>(hashSet);
        List<String> frequencyCount = new ArrayList<>();
        for (int i = 0; i < reference.size(); i++) {
            Integer num = Collections.frequency(list, reference.get(i));
            String numCount = num.toString();
            frequencyCount.add("\"" + reference.get(i) + "\"" + " " + numCount);
        }
        sort(frequencyCount);
        if (resultsRange > frequencyCount.size()) {
            resultsRange = frequencyCount.size();
        }
        if (resultsRange <= 0) {
            resultsRange = 1;
        }
        Integer counter = 1;
        System.out.println("Results of (range) in the poem: ");


        ArrayList<String> copy = new ArrayList<String>();
        for (int i = 0; i < resultsRange - 1; i++) {
            String counterString = counter.toString();
            System.out.println(counterString + ". " + frequencyCount.get(i));
            copy.add(counterString + ". " + frequencyCount.get(i));
            counter++;
        }
        wordArray = copy;
        reader.close();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}