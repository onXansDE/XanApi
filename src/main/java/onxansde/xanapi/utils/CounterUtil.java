package onxansde.xanapi.utils;

import onxansde.xanapi.XanApi;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class CounterUtil {

    public int state = 0;
    public int max, increment, delay, multiplier = 1;

    private BukkitTask task;

    public CounterUtil(int max, int increment, int delay) {
        this.max = max;
        this.increment = increment;
        this.delay = delay;
    }

    public CounterUtil(int max, int increment, int delay, int multiplier) {
        this.max = max;
        this.increment = increment;
        this.delay = delay;
        this.multiplier = multiplier;
    }

    public BukkitTask startTask() {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(XanApi.getInstance(), () -> {
            if(state >= max) {
                state = 0;
            } else {
                state += increment;
            }
        }, 0, delay);
    }

    public void stopTimer() {
        if(task != null) {
            if(!task.isCancelled())task.cancel();
            task = null;
        }
    }

    public float getStateScale(int offset) {
        return shortOffset((state + offset * increment * multiplier)) * (1f/max);
    }

    public float getStateScale() {
        return (state) * (1f/max);
    }

    public int getStateOffset(int offset) {
        int offsetstate = state + (offset * increment * multiplier);
        return shortOffset(offsetstate);
    }

    public int shortOffset(int offset) {
        if(offset > max) return shortOffset(offset - max);
        return offset;
    }

    public void resetTimer() {
        state = 0;
        if(task != null) {
            if(!task.isCancelled())task.cancel();
            task = null;
        }
    }

    public void startTimer() {
        if(task == null) {
            task = startTask();
        }
    }
}
