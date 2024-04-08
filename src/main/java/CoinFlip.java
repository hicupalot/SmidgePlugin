import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class CoinFlip implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }


        int headsOrTails = new Random().nextInt(2);
        if (headsOrTails == 1) {
            String heads = ChatColor.translateAlternateColorCodes('&',
                    "&cThe coin landed on heads!");
            sender.sendMessage(heads);
            return false;
        }
           else if (headsOrTails == 0) {
               String tails = ChatColor.translateAlternateColorCodes('&',
                        "&cThe coin landed on tails!");
                sender.sendMessage(tails);
                return true;

            }
 return true;
    }
}