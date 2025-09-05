import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Random;

public class CoinFlip implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        if (!sender.hasPermission("smidge.admin")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        int headsOrTails = new Random().nextInt(2);
        if (headsOrTails == 1) {
            Component heads = Component.text("The coin landed on heads!").color(TextColor.color(254,63,63));
            sender.sendMessage(heads);
            return false;
        }
           else {
              Component tails = Component.text("The coin landed on tails!").color(TextColor.color(254,63,63));
                sender.sendMessage(tails);
                return true;

            }
    }
}