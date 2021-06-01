package onxansde.xanapi;

import onxansde.xanapi.utils.Broadcast;

public class Logger {

    private static Logger instance;

    public String prefix = "XanApi";

    public boolean debug = true;

    public Logger() {
        log("Initialized");
    }

    public static Logger get() {
        if(instance == null) instance = new Logger();
        return instance;
    }

    public void log(String message) {
        System.out.println("["+prefix+"] " + "["+getCallerClassName()+"/"+getCallerFunctionName()+"] > " + message);
    }

    public void debug(String message) {
        if(debug) {
            System.out.println("["+prefix+"/Debug] " + "["+getCallerClassName()+"] > " + message);
            Broadcast.broadcastRaw("["+prefix+"/Debug] " + "["+getCallerClassName()+"] §8> §7" + message);
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
