package tokyo.ramune.blockhunt.game;

public enum GameStatus {
    WAITING,
    WAITING_RUNNER_HIDE,
    PLAYING,
    ENDING;

    private GameStatus() {
    }

    public String toString() {
        if (super.equals(WAITING)) {
            return "待機中";
        } else if (super.equals(WAITING_RUNNER_HIDE)) {
            return "逃げが隠れています";
        } else if (super.equals(PLAYING)) {
            return "進行中";
        } else {
            return super.equals(ENDING) ? "終了～" : "Invalid Status";
        }
    }
}
