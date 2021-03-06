import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {

    static BufferedReader inputReader = null;
    static PrintWriter outputWriter = null;

    public static List<String> extractNames(String filename){

        ArrayList<String> outputList = new ArrayList<>();
        String yearRegex = "<h3 align=\"center\">Popularity\\sin\\s(\\d\\d\\d\\d)</h3>";
        String namesRegex = "<tr align=\"right\"><td>(\\d+)</td><td>(\\w+)</td><td>(\\w+)</td>";

        try {

            //inputReader = new BufferedReader(new FileReader(filename));
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");
            outputWriter = new PrintWriter(new FileWriter(filename + "_Summary File"));

            //Old implementation

//            String line;
//            Pattern pattern;
//            Matcher matcher;
//
//            while ((line = inputReader.readLine()) != null){
//                pattern = Pattern.compile(namesRegex);
//                matcher = pattern.matcher(line);
//                if(matcher.matches()){
//                    outputList.add(matcher.group(2) + " " + matcher.group(1));
//                    outputList.add(matcher.group(3) + " " + matcher.group(1));
//                } else{
//                    pattern = Pattern.compile(yearRegex);
//                    matcher = pattern.matcher(line);
//                    if(matcher.matches()){
//                        outputList.add(matcher.group(1));
//                    }
//                }
//            }


            Pattern pattern = Pattern.compile(namesRegex);
            Pattern pattern1 = Pattern.compile(yearRegex);
            Matcher matcher = pattern.matcher(str);
            Matcher matcher1 = pattern1.matcher(str);

            if(matcher1.find()){
                outputList.add(matcher1.group(1));
            }
            while(matcher.find()){
                outputList.add(matcher.group(2) + " " + matcher.group(1));
                outputList.add(matcher.group(3) + " " + matcher.group(1));
            }


        } catch (FileNotFoundException e){
            System.out.println(filename + " not found.");
        } catch (IOException e){
            System.out.println("IO Exception");
        } finally {

        }

        outputList.sort(String::compareTo);
        for (String line : outputList) {
            outputWriter.println(line);
        }

        outputWriter.close();
        //System.out.println(Arrays.toString(outputList.toArray()));

        return outputList;
    }

    public static void main(String[] args) {
        String[] filenames = {"baby1990.html", "baby1992.html", "baby1994.html", "baby1996.html",
                "baby1998.html", "baby2000.html", "baby2002.html", "baby2004.html", "baby2006.html", "baby2008.html"};
        for (int i = 0; i < filenames.length; i++) {
            extractNames(filenames[i]);
        }

    }
}
