package onxansde.xanapi;

import org.bukkit.Bukkit;

public class Logger {

    public String prefix = "XanApi";

    public boolean debug = false;

    public Logger() {
        log("Initialized");
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage("["+prefix+"] " + "["+getCallerClassName()+"] > " + message);
        if(debug) {
            XanApi.instance.broadcast.broadcastRaw("["+prefix+"/Debug] " + "["+getCallerClassName()+"] §8> §7" + message,"xanapi.debug.read");
        }
    }

    public void logerror(String message) {
        Bukkit.getConsoleSender().sendMessage("["+prefix+"/Error] " + "["+getCallerClassName()+"/"+getCallerFunctionName()+"] > §c" + message);
        if(debug) {
            XanApi.instance.broadcast.broadcastRaw("["+prefix+"/Error] " + "["+getCallerClassName()+"] §8> §c" + message,"xanapi.debug.read");
        }
    }

    public void debug(String message) {
        if(debug) {
            Bukkit.getConsoleSender().sendMessage("["+prefix+"/Debug] " + "["+getCallerClassName()+"] > " + message);
            XanApi.instance.broadcast.broadcastRaw("["+prefix+"/Debug] " + "["+getCallerClassName()+"] §8> §7" + message,"xanapi.debug.read");
        }
    }

    public static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(onxansde.xanapi.Logger.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getClassName();
            }
        }
        return null;
    }

    public static String getCallerFunctionName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(onxansde.xanapi.Logger.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getMethodName();
            }
        }
        return null;
    }
}
