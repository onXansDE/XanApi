package onxansde.xanapi.utils;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class ColorUtil {

    public static Color mainc1 = new Color(5, 247, 255);
    public static Color mainc2 = new Color(5, 118, 255);

    public static Color getRGBColor(CounterUtil counter, float s, float b) {
        return Color.getHSBColor(counter.getStateScale(),s,b);
    }

    public static Color getRGBColorWave(CounterUtil counter, int offset, float s, float b) {
        return Color.getHSBColor(counter.getStateScale(offset), s, b);
    }

    public static Color interColor(CounterUtil counter, Color color1, Color color2) {
        float blending = counter.getStateScale() * 2;

        if(blending < 1) {
            float inverseblending = 1 - blending;
            float r = color1.getRed() * blending + color2.getRed() * inverseblending;
            float g = color1.getGreen() * blending + color2.getGreen() * inverseblending;
            float b = color1.getBlue() * blending + color2.getBlue() * inverseblending;
            return new Color(r/255, g/255, b/255);
        } else {
            blending = blending -1;
            float inverseblending = 1 - blending;
            float r = color1.getRed() * inverseblending + color2.getRed() * blending;
            float g = color1.getGreen() * inverseblending + color2.getGreen() * blending;
            float b = color1.getBlue() * inverseblending + color2.getBlue() * blending;
            return new Color(r/255, g/255, b/255);
        }
    }

    public static Color interColorWave(CounterUtil counter, Color color1, Color color2, int offset) {
        float blending = counter.getStateScale(offset) * 2;

        if(blending < 1) {
            float inverseblending = 1 - blending;
            float r = color1.getRed() * blending + color2.getRed() * inverseblending;
            float g = color1.getGreen() * blending + color2.getGreen() * inverseblending;
            float b = color1.getBlue() * blending + color2.getBlue() * inverseblending;
            return new Color(r/255, g/255, b/255);
        } else {
            blending = blending -1;
            float inverseblending = 1 - blending;
            float r = color1.getRed() * inverseblending + color2.getRed() * blending;
            float g = color1.getGreen() * inverseblending + color2.getGreen() * blending;
            float b = color1.getBlue() * inverseblending + color2.getBlue() * blending;
            return new Color(r/255, g/255, b/255);
        }
    }

    public static String RgbString(CounterUtil counter, String text, float s, float b) {
        return ChatColor.of(getRGBColor(counter, s, b)) + text;
    }

    public static String RgbWaveString(CounterUtil counter, String text, float s, float b, boolean bold, boolean italic, boolean underline, boolean striketrough) {
        StringBuilder output = new StringBuilder();
        int offset = 0;
        for (Character c : text.toCharArray()) {
            output.append(ChatColor.of(getRGBColorWave(counter, offset, s, b)));

            if(bold) output.append("§l");
            if(underline) output.append("§n");
            if(striketrough) output.append("§m");
            if(italic) output.append("§o");
            output.append(c);
            offset++;
        }
        return output.toString();
    }

    public static String RgbWaveString(CounterUtil counter, String text, float s, float b, boolean bold) {
        return RgbWaveString(counter, text, s, b, bold, false, false, false);
    }

    public static String CustomCycleString(CounterUtil counter, String text, Color color1, Color color2) {
        return ChatColor.of(interColor(counter,color1,color2)) + text;
    }

    public static String CustonWaveString(CounterUtil counter, String text, Color color1, Color color2, boolean bold, boolean italic, boolean underline, boolean striketrough) {
        StringBuilder output = new StringBuilder();
        int offset = 0;
        for (Character c : text.toCharArray()) {
            output.append(ChatColor.of(interColorWave(counter, color1, color2, offset)));

            if(bold) output.append("§l");
            if(underline) output.append("§n");
            if(striketrough) output.append("§m");
            if(italic) output.append("§o");
            output.append(c);
            offset++;
        }
        return output.toString();
    }

    public static String CustonWaveString(CounterUtil counter, String text, Color color1, Color color2, boolean bold) {
        return CustonWaveString(counter, text, color1, color2, bold, false, false,false);
    }
}
