package lab3.StringsPractice03.src.com.example.strings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchReplace {

    private String fileName = "lab3/StringsPractice03/gettys.html";

    public static void main(String[] args) {
        SearchReplace sr = new SearchReplace();
        sr.run();
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String targetTag = "p";
            String replaceTag = "span";
            String attribute = "class";
            String value = "sentence";
            String line;
            int c = 1;

            Pattern pattern = Pattern.compile("(<" + targetTag + ".*?>)(.*?)(</" + targetTag + ">)");
            
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String newStart = replaceTag(matcher.group(1), targetTag, replaceTag);
                    newStart = replaceAttribute(newStart, attribute, value);
                    String newEnd = replaceTag(matcher.group(3), targetTag, replaceTag);
                    String newLine = newStart + matcher.group(2) + newEnd;
                    System.out.printf("%3d %s\n", c, newLine);
                } else {
                    System.out.printf("%3d %s\n", c, line);
                }
                c++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String replaceTag(String tag, String targetTag, String replaceTag) {
        return tag.replaceFirst(targetTag, replaceTag);
    }

    public String replaceAttribute(String tag, String attribute, String value) {
        Pattern pattern = Pattern.compile(attribute + "=\".*?\"");
        Matcher matcher = pattern.matcher(tag);
        if (matcher.find()) {
            return matcher.replaceFirst(attribute + "=\"" + value + "\"");
        } else {
            return tag.replaceFirst(">", " " + attribute + "=\"" + value + "\">");
        }
    }
}
