package lab3.StringsPractice02.src.com.example.strings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindText {

    private String fileName = "lab3/StringsPractice02/gettys.html";
    private Pattern pattern; 
    private Matcher matcher; 

    public static void main(String[] args) {
        FindText find = new FindText();
        find.run();
    }

    public void run() {
        pattern = Pattern.compile("<h4>.*?</h4>"); 

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int c = 1;
            while ((line = reader.readLine()) != null) {
                matcher = pattern.matcher(line); 

                if (matcher.find()) { 
                    System.out.println(c + ": " + line);
                }
                c++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
