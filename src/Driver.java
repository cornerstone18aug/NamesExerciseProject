import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {

    static BufferedReader inputReader = null;
    static PrintWriter outputWriter = null;

    public static List<String> extractNames(String filename){

        ArrayList<String> outputList = new ArrayList<>();
        String yearRegex = "<h3 align=\"center\">Popularity\\sin\\s(\\d\\d\\d\\d)</h3>";
        String namesRegex = "<tr align=\"right\"><td>(\\d+)</td><td>(\\w+)</td><td>(\\w+)</td>";

        try{

            inputReader = new BufferedReader(new FileReader(filename));
            //outputWriter = new PrintWriter(new FileWriter("TestFile"));

            String line;
            Pattern pattern;
            Matcher matcher;

            while ((line = inputReader.readLine()) != null){
                pattern = Pattern.compile(namesRegex);
                matcher = pattern.matcher(line);
                if(matcher.matches()){
                    outputList.add(matcher.group(2) + " " + matcher.group(1));
                    outputList.add(matcher.group(3) + " " + matcher.group(1));
                } else{
                    pattern = Pattern.compile(yearRegex);
                    matcher = pattern.matcher(line);
                    if(matcher.matches()){
                        outputList.add(matcher.group(1));
                    }
                }
            }

        } catch (FileNotFoundException e){
            System.out.println(filename + " not found.");
        } catch (IOException e){
            System.out.println("IO Exception");
        } finally {
            try{
                inputReader.close();
            } catch (IOException e){
                System.out.println("Not close");
            }
        }

        outputList.sort(String::compareTo);
        //System.out.println(Arrays.toString(outputList.toArray()));
        return outputList;
    }

    public static void main(String[] args) {
        extractNames("baby1998.html");
    }
}
