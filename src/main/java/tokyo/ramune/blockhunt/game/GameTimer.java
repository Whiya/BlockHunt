package tokyo.ramune.blockhunt.game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.blockhunt.BlockHunt;

public class GameTimer {
    private BukkitTask timerTask;
    private Runnable onStart;
    private Runnable onStop;
    private int time;
    private int maxTime;
    private boolean isStarted;

    public GameTimer(int maxTime, Runnable onStart, Runnable onStop) {
        if (maxTime > -1) {
            this.maxTime = maxTime;
        }

        this.onStart = onStart;
        this.onStop = onStop;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Runnable getOnStart() {
        return this.onStart;
    }

    public Runnable getOnStop() {
        return this.onStop;
    }

    public void setOnStart(Runnable onStart) {
        this.onStart = onStart;
    }

    public void setOnStop(Runnable onStop) {
        this.onStop = onStop;
    }

    public int getMaxTime() {
        return this.maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void startTimer(boolean runOnStartTask) {
        if (!this.isStarted) {
            if (this.time <= 0) {
                this.resetTimer();
            }

            BlockHunt.getBossBarManager().setMaxTime(this.maxTime);
            this.timerTask = Bukkit.getScheduler().runTaskTimerAsynchronously(BlockHunt.getPlugin(), () -> {
                if (this.time <= 0) {
                    this.stopTimer(true);
                } else {
                    --this.time;
                    BlockHunt.getBossBarManager().setTime(this.time);
                    BlockHunt.getBossBarManager().updateProgress();
                }
            }, 20L, 20L);
            this.isStarted = true;
            if (runOnStartTask) {
                Bukkit.getScheduler().runTask(BlockHunt.getPlugin(), () -> {
                    this.onStart.run();
                });
            }

        }
    }

    public void stopTimer(boolean runOnStopTask) {
        if (this.isStarted) {
            this.timerTask.cancel();
            this.timerTask = null;
            this.isStarted = false;
            if (runOnStopTask) {
                Bukkit.getScheduler().runTask(BlockHunt.getPlugin(), () -> {
                    this.onStop.run();
                });
            }

        }
    }

    public void resetTimer() {
        if (this.isStarted) {
            this.stopTimer(false);
        }

        this.time = this.maxTime;
    }

    public boolean isStarted() {
        return this.isStarted;
    }
}
