package tokyo.ramune.blockhunt.game;

import java.util.ArrayList;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.item.GameItemBuilder;
import tokyo.ramune.blockhunt.game.item.GameItemResource;
import tokyo.ramune.blockhunt.game.player.HuntPlayer;
import tokyo.ramune.blockhunt.game.player.HuntPlayerRole;

public class GameManager {
    private GameStatus status;
    private int daemonCount = 1;
    private GameTimer waitingRunnerHideTimer;
    private GameTimer gameTimer;
    private GameTimer endingTimer;

    public GameManager() {
        this.status = GameStatus.WAITING;
        this.waitingRunnerHideTimer = new GameTimer(30, () -> {
            this.setStatus(GameStatus.WAITING_RUNNER_HIDE);
            BlockHunt.getPlayerManager().getHuntPlayers().forEach((huntPlayer) -> {
                huntPlayer.getPlayer().sendTitle(ChatColor.AQUA.toString(), ChatColor.YELLOW + "あと" + this.waitingRunnerHideTimer.getMaxTime() + "秒で鬼が動き始めます!");
                huntPlayer.getPlayer().playSound(huntPlayer.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 0.0F);
                switch (huntPlayer.getRole()) {
                    case RUNNER:
                        huntPlayer.getPlayer().teleport(BlockHunt.getConfigFile().START_LOCATION);
                        huntPlayer.getPlayer().sendMessage(ChatColor.AQUA + "あなたの役職は逃げです! 鬼に見つかりにくいところに隠れよう!");
                        break;
                    case DAEMON:
                        huntPlayer.getPlayer().sendMessage(ChatColor.RED + "あなたの役職は鬼です! あと" + this.waitingRunnerHideTimer.getMaxTime() + "秒で捕まえに行けるよ!");
                        break;
                    case SPECTATOR:
                        huntPlayer.getPlayer().teleport(BlockHunt.getConfigFile().SPECTATOR_LOCATION);
                        huntPlayer.getPlayer().sendMessage(ChatColor.GRAY + "あなたの役職はスペクテイターです! プレイヤーを観戦できます");
                        break;
                    default:
                        BlockHunt.getPlugin().getLogger().warning("スタートにロールが指定されていないがいます。 " + huntPlayer.getPlayer().getName());
                }

            });
        }, () -> {
            this.gameTimer.startTimer(true);
        });
        this.gameTimer = new GameTimer(300, () -> {
            BlockHunt.getPlayerManager().getHuntPlayers().forEach((huntPlayer) -> {
                huntPlayer.getPlayer().playSound(huntPlayer.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);
                switch (huntPlayer.getRole()) {
                    case RUNNER:
                        huntPlayer.getPlayer().sendTitle(ChatColor.AQUA.toString(), ChatColor.YELLOW + "⚠ 鬼が動き始めました ⚠");
                        break;
                    case DAEMON:
                        huntPlayer.getPlayer().teleport(BlockHunt.getConfigFile().START_LOCATION);
                        huntPlayer.getPlayer().sendMessage(ChatColor.RED + "鬼が動けるようになったよ! 捕まえに行こう!");
                        break;
                    case SPECTATOR:
                        huntPlayer.getPlayer().teleport(BlockHunt.getConfigFile().SPECTATOR_LOCATION);
                        huntPlayer.getPlayer().sendMessage(ChatColor.GRAY + "あなたの役職はスペクテイターです! プレイヤーを観戦できます");
                        break;
                    default:
                        BlockHunt.getPlugin().getLogger().warning("スタートにロールが指定されていないがいます。 " + huntPlayer.getPlayer().getName());
                }

            });
            this.setStatus(GameStatus.PLAYING);
        }, () -> {
            this.endingTimer.startTimer(true);
        });
        this.endingTimer = new GameTimer(10, () -> {
            BlockHunt.getPlayerManager().getHuntPlayers().forEach((huntPlayer) -> {
                huntPlayer.setRole(HuntPlayerRole.AWAIT);
                huntPlayer.getPlayer().teleport(BlockHunt.getConfigFile().SPAWN_LOCATION);
                switch (huntPlayer.getRole()) {
                    case RUNNER:
                        huntPlayer.getPlayer().sendTitle(ChatColor.AQUA.toString(), ChatColor.YELLOW + "おめでとう!! あなたは最後まで逃げ切れました!");
                        break;
                    case DAEMON:
                        huntPlayer.getPlayer().sendMessage(ChatColor.RED + "しゅう～りょう～! 逃げの人を全員捕まえることはできたかな?");
                        break;
                    case SPECTATOR:
                        huntPlayer.getPlayer().sendMessage(ChatColor.GRAY + "あなたの役職はスペクテイターです! プレイヤーを観戦できます");
                        break;
                    default:
                        BlockHunt.getPlugin().getLogger().warning("スタートにロールが指定されていないがいます。 " + huntPlayer.getPlayer().getName());
                }

            });
            this.setStatus(GameStatus.ENDING);
        }, () -> {
            BlockHunt.getPlayerManager().getHuntPlayers().forEach((huntPlayer) -> {
                switch (huntPlayer.getRole()) {
                    case RUNNER:
                        huntPlayer.getPlayer().sendTitle(ChatColor.AQUA.toString(), ChatColor.YELLOW + "おめでとう!! あなたは最後まで逃げ切れました!");
                        break;
                    case DAEMON:
                        huntPlayer.getPlayer().sendMessage(ChatColor.RED + "しゅう～りょう～! 逃げの人を全員捕まえることはできたかな?");
                        break;
                    case SPECTATOR:
                        huntPlayer.getPlayer().teleport(BlockHunt.getConfigFile().SPECTATOR_LOCATION);
                        huntPlayer.getPlayer().sendMessage(ChatColor.GRAY + "あなたの役職はスペクテイターです! プレイヤーを観戦できます");
                        break;
                    default:
                        BlockHunt.getPlugin().getLogger().warning("スタートにロールが指定されていないがいます。 " + huntPlayer.getPlayer().getName());
                }

            });
            this.setStatus(GameStatus.WAITING);
        });
    }

    public void start() {
        if (!this.isStarted()) {
            if (BlockHunt.getPlayerManager().getRunnerPlayers().size() + BlockHunt.getPlayerManager().getDaemonPlayers().size() < 2) {
                Bukkit.getOnlinePlayers().forEach((player) -> {

                    player.sendMessage("ゲームが開始できません! 逃げと鬼の人数は最低でも2人は必要です!");
                });
            } else {
                // プレイヤーのいろんな初期化
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.getInventory().clear();

                    HuntPlayerRole role = BlockHunt.getPlayerManager().getHuntPlayer(player).getRole();
                    switch (role) {
                        case RUNNER:
                            player.getInventory().addItem(
                                    GameItemResource.getHide().getItemStack(),
                                    GameItemResource.getSelectHideBlock().getItemStack());
                        case DAEMON:
                            player.getInventory().addItem(GameItemResource.getGoldStick().getItemStack());
                    }
                });
                this.waitingRunnerHideTimer.startTimer(true);
            }
        }
    }

    public void stop() {
        if (this.isStarted()) {
            this.waitingRunnerHideTimer.resetTimer();
            this.gameTimer.resetTimer();
            this.endingTimer.startTimer(true);
        }
    }

    public boolean isStarted() {
        return !this.status.equals(GameStatus.WAITING);
    }

    @Nonnull
    public GameStatus getStatus() {
        return this.status;
    }

    private void setStatus(GameStatus status) {
        this.status = status;
    }

    public int getDaemonCount() {
        return this.daemonCount;
    }

    public void setDaemonCount(int daemonCount) {
        this.daemonCount = daemonCount;
    }

    public GameTimer getWaitingRunnerHideTimer() {
        return this.waitingRunnerHideTimer;
    }

    public GameTimer getGameTimer() {
        return this.gameTimer;
    }

    public GameTimer getEndingTimer() {
        return this.endingTimer;
    }

    public ArrayList<HuntPlayer> getDaemonPlayers() {
        ArrayList<HuntPlayer> daemonPlayers = new ArrayList();
        BlockHunt.getPlayerManager().getHuntPlayers().forEach((huntPlayer) -> {
            if (huntPlayer.getRole().equals(HuntPlayerRole.DAEMON)) {
                daemonPlayers.add(huntPlayer);
            }

        });
        return daemonPlayers;
    }

    public ArrayList<HuntPlayer> getRunnerPlayers() {
        ArrayList<HuntPlayer> runnerPlayers = new ArrayList();
        BlockHunt.getPlayerManager().getHuntPlayers().forEach((huntPlayer) -> {
            if (huntPlayer.getRole().equals(HuntPlayerRole.RUNNER)) {
                runnerPlayers.add(huntPlayer);
            }

        });
        return runnerPlayers;
    }

    public ArrayList<HuntPlayer> getSpectatorPlayers() {
        ArrayList<HuntPlayer> spectatorPlayers = new ArrayList();
        BlockHunt.getPlayerManager().getHuntPlayers().forEach((huntPlayer) -> {
            if (huntPlayer.getRole().equals(HuntPlayerRole.SPECTATOR)) {
                spectatorPlayers.add(huntPlayer);
            }

        });
        return spectatorPlayers;
    }

    public ArrayList<HuntPlayer> getAwaitPlayers() {
        ArrayList<HuntPlayer> awaitPlayers = new ArrayList();
        BlockHunt.getPlayerManager().getHuntPlayers().forEach((huntPlayer) -> {
            if (huntPlayer.getRole().equals(HuntPlayerRole.AWAIT)) {
                awaitPlayers.add(huntPlayer);
            }

        });
        return awaitPlayers;
    }
}
