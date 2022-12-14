import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ForceShow implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /forceshow (player)");
        } else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "[Smidge] This is not an online player");
                return false;
            }
            if (((Player) sender).getUniqueId().equals(target.getUniqueId())) {
                sender.sendMessage(ChatColor.RED + "[Smidge] You cannot make yourself see yourself!");
                return false;
            }
            if (!Config.hideAllToggle.containsKey(target.getUniqueId())) {
                String notValid = ChatColor.translateAlternateColorCodes('&', "&c[Smidge] This player isn't hiding all players");
                sender.sendMessage(notValid);
                return false;
            }
            else {
                target.showPlayer(Config.plugin, (Player) sender);
                String showMessage = ChatColor.translateAlternateColorCodes('&',"&c[Smidge] Forced &6"+target
                + " &c to see you!");
                sender.sendMessage(showMessage);
                return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++) {
                playerNames.add(players[i].getName());
            }

            return playerNames;
        }
        return null;
    }
}

