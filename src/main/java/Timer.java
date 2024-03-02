import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Timer implements CommandExecutor, TabCompleter {
    int time;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.admin")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /starttimer (seconds)");
            return false;
        }
        if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /starttimer (seconds)");
            return false;
        }
        if (config.getBoolean("timer",true)){
            sender.sendMessage(ChatColor.RED+"[Smidge] There is currently a timer running!");
            return false;
        }
        time = Integer.parseInt(args[0]);
        config.set("timer", true);
        SmidgeThing.getInstance().saveConfig();
        Bukkit.getScheduler().runTaskTimer(SmidgeThing.getInstance(), new Runnable() {
            //------------------------------------------------------------------------------//
            @Override
            public void run() {
                int timeleft = time;
                int seconds = timeleft;
                int S = seconds % 60;
                int H = seconds / 60;
                int M = H % 60;
                H = H / 60;
                String timerUp = ChatColor.translateAlternateColorCodes('&', "&6&l[Smidge] " +
                        "&6The Timer Is Up!");
                if (time == 0) {
                    Bukkit.getScheduler().cancelTasks(SmidgeThing.getInstance());
                    Bukkit.broadcastMessage(timerUp);
                    config.set("timer",false);
                    SmidgeThing.getInstance().saveConfig();
                    return;
                }
                time--;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (H!=0) {
                        player.sendActionBar("Time Remaining: " + H + " Hours " + M + " Minutes " + S + " Seconds");
                    }
                    else if (M!=0){
                        player.sendActionBar("Time Remaining: " + M + " Minutes " + S + " Seconds");
                    }
                    else if (time>=1) {
                        player.sendActionBar("Time Remaining: " + S+ " Seconds");
                    }
                    if (S==1){
                        player.sendActionBar("Time Remaining: " + S+ " Second");
                    }
                }
                // player.sendMessage(String.valueOf(timeleft)); //Debug
            }
        }, 0L, 20L);
        return false;
    }

    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            List<String> time = new ArrayList<>();
            time.add("Time");
            return time;
        }
        return null;
    }
}