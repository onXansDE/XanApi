package onxans.xanapi.utils;

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
}
