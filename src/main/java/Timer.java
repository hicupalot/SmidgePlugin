import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.format.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Timer implements CommandExecutor, TabCompleter {
    int time;
    String unit;
    Component usage = Component.text("[Smidge] Command Usage /starttimer (amount) (timeunit)").color(TextColor.color(190,0,0));
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
        if (args.length < 2) {
            sender.sendMessage(usage);
            return false;
        }
        if (args.length > 2) {
            sender.sendMessage(usage);
            return false;
        }
        if (config.getBoolean("timer", true)) {
            Component timerAlreadyRunning = Component.text("[Smidge] There is currently a timer running!").color(TextColor.color(190,0,0));
            sender.sendMessage(timerAlreadyRunning);
            return false;
        }
            time = Integer.parseInt(args[0]);
            unit = args[1];

        if (unit.equalsIgnoreCase("seconds")) {
        }
       else if (unit.equalsIgnoreCase("minutes")) {
            time = time * 60;
        }
       else if (unit.equalsIgnoreCase("hours")) {
            time = time * 3600;
        }
        else{
            Component validUnits = Component.text("[Smidge] Please Use Valid Units").color(TextColor.color(190,0,0));
            sender.sendMessage(validUnits);
            return false;
        }
            config.set("timer", true);
            SmidgeThing.getInstance().saveConfig();
            Bukkit.getScheduler().runTaskTimer(SmidgeThing.getInstance(), new Runnable() {
                //------------------------------------------------------------------------------//
                @Override
                public void run() {
                    int seconds = time;
                    int S = seconds % 60;
                    int H = seconds / 60;
                    int M = H % 60;
                    H = H / 60;
                    Component timeUp = Component.text().append(Component.text("[Smidge] ").color(TextColor.color(217,163,52))
                            .append(Component.text("The Timer Is Up!").color(TextColor.color(217,163,52)))).build();

                    if (time == 0) {
                        Bukkit.getScheduler().cancelTasks(SmidgeThing.getInstance());
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage(timeUp);
                        }
                        config.set("timer", false);
                        SmidgeThing.getInstance().saveConfig();
                        return;
                    }
                    time--;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (H != 0) {
                            Component whenHoursLeft = Component.text().append(Component.text("Time Remaining: ").append(Component.text(H).
                                            append(Component.text(" Hours ").append(Component.text(M).append(Component.text(" Minutes "))
                                                    .append(Component.text(S)).append(Component.text(" Seconds")))))).build();
                            player.sendActionBar(whenHoursLeft);
                        } else if (M != 0) {
                            Component whenMinutesLeft = Component.text().append(Component.text("Time Remaining: ").
                                    append(Component.text(M).append(Component.text(" Minutes "))
                                            .append(Component.text(S)).append(Component.text(" Seconds")))).build();
                            player.sendActionBar(whenMinutesLeft);
                        } else if (time >= 1) {
                            Component whenSeconds = Component.text().append(Component.text("Time Remaining: ").append(Component.text(S)).append(Component.text(" Seconds"))).build();
                            player.sendActionBar(whenSeconds);
                        }
                        if (time == 0) {
                            Component whenSeconds = Component.text().append(Component.text("Time Remaining: ").append(Component.text(S)).append(Component.text(" Second"))).build();
                            player.sendActionBar(whenSeconds);}
                    }
                    // player.sendMessage(String.valueOf(timeleft)); //Debug
                }
            }, 0L, 20L);
            return true;
        }

        @Override
        public java.util.List<String> onTabComplete (CommandSender sender, Command command, String alias, String[]args){
            if (args.length == 2) {
                List<String> unit = new ArrayList<>();
                unit.add("seconds");
                unit.add("minutes");
                unit.add("hours");
                return unit;
            }
            return null;
        }
    }

