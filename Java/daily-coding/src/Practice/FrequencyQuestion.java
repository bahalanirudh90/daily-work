package Practice;
import java.util.HashMap;
import java.util.Scanner;

public class FrequencyQuestion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String cleaned = input.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = cleaned.split("\\s+");

        HashMap<String,Integer> frequencyMap = new HashMap<>();
        for(String word : words){
            word = word.toLowerCase();
            if(frequencyMap.containsKey(word)){
                frequencyMap.put(word,frequencyMap.get(word) + 1);
            }else{
                frequencyMap.put(word, 1);
            }
        }

        for(String word : frequencyMap.keySet()) {
            System.out.println(word + " " + frequencyMap.get(word));
        }
        String testWord = sc.nextLine().toLowerCase();
        if(frequencyMap.containsKey(testWord)){
            System.out.println(frequencyMap.get(testWord));
        } else {
            System.out.println("0");
        }
    }
}
