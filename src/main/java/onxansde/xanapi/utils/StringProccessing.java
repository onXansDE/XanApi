package onxansde.xanapi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringProccessing {

    public static String SpaceWords(String string) {
        StringBuilder temp = new StringBuilder(string.toLowerCase());
        String[] temps = temp.toString().split("_");
        temp = new StringBuilder();
        for(String s : temps) {
            temp.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
        }
        return temp.toString();
    }

    public static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
