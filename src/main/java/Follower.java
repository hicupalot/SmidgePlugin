import de.myzelyam.api.vanish.VanishAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
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

public class Follower implements CommandExecutor, TabCompleter {
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
            Component noFollow = Component.text("[Smidge] Command Usage /spy (player)").color(TextColor.color(254,63,63));
            sender.sendMessage(noFollow);
        } else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                Component notOnline = Component.text("[Smidge] This is not a online player!").color(TextColor.color(254,63,63));
                sender.sendMessage(notOnline);
                return false;
            }
            if (((Player) sender).getUniqueId().equals(target.getUniqueId())) {
                Component notYourself = Component.text("[Smidge] You cannot spy on yourself!!").color(TextColor.color(254,63,63));
                sender.sendMessage(notYourself);
                return false;
            } else {
                Config.originalLocation.put(((Player) sender).getUniqueId(), ((Player) sender).getLocation());
                if(VanishAPI.isInvisible(((Player) sender).getPlayer())){
                    Config.vanishState.put(((Player) sender).getUniqueId(), true);
                }
                else if (!VanishAPI.isInvisible(((Player) sender).getPlayer())){
                    return true;
                }
                VanishAPI.hidePlayer((Player) sender);
                ((Player) sender).setGameMode(GameMode.SPECTATOR);
                ((Player) sender).teleport(target);
                Component monitoring = Component.text("[Smidge] You are now monitoring " + target.getName()).color(TextColor.color(217,163,52));
                sender.sendMessage(monitoring);
            }
            return true;

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (Player player : players) {
                playerNames.add(player.getName());
            }

            return playerNames;
        }
 return null;
    }
}