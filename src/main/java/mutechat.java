import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class mutechat implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            FileConfiguration config = SmidgeThing.getInstance().getConfig();
            if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
                sender.sendMessage(Config.notPlayerOrConsole);
                return false;
            }
            if (!sender.hasPermission("smidge.staff")) {
                sender.sendMessage(Config.noPermission);
                return false;
            }
            if (!config.getBoolean("chatMuted")) {
                String mutealert = ChatColor.translateAlternateColorCodes('&',
                        "&cThe Chat Has Been Muted");
                Bukkit.broadcastMessage(mutealert);
                config.set("chatMuted", true);
                SmidgeThing.getInstance().saveConfig();
            }
            else if (config.getBoolean("chatMuted")){
                            config.set("chatMuted",false);
                SmidgeThing.getInstance().saveConfig();
                            String alert = ChatColor.translateAlternateColorCodes('&', "&bThe Chat Has Been Unmuted");
                            Bukkit.broadcastMessage(alert);
                        }
                    return true;
                }
            }
