import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class mutechat implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("You may not do this!");
                return false;
            }
            if (!sender.hasPermission("smidge.staff")) {
                sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
                return false;
            }
            UUID playerUUID = player.getUniqueId();
            if (Config.muteChat.isEmpty()) {
                        Config.muteChat.put(playerUUID, true);
                        String mutealert = ChatColor.translateAlternateColorCodes('&',
                                "&cThe Chat Has Been Muted");
                        Bukkit.broadcastMessage(mutealert);

                    } else {
                            Config.muteChat.clear();
                            String alert = ChatColor.translateAlternateColorCodes('&', "&bThe Chat Has Been Unmuted");
                            Bukkit.broadcastMessage(alert);
                        }
                    return true;
                }
            }
