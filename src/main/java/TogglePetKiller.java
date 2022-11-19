import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TogglePetKiller implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You may not do this!");
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length>=1){
            sender.sendMessage(ChatColor.RED+"[Smidge] Please do not add any command arguments");
        }
        if (args.length < 1) {
            if (!Config.petDeathToggle.containsKey(playerUUID)) {
                Config.petDeathToggle.put(playerUUID, true);
                sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "PETKILLING" + ChatColor.YELLOW + "] "
                        + ChatColor.GOLD + "ENABLED");
                return true;
            } else Config.petDeathToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "PETKILLING" + ChatColor.YELLOW + "] "
                    + ChatColor.RED + "DISABLED");
            return true;
        }
  return true;
    }
}