import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class PlayerRaffle implements CommandExecutor {
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
        if (Bukkit.getOnlinePlayers().isEmpty()) {
            String noOnlinePlayers = ChatColor.translateAlternateColorCodes('&', "&cNo One Is Online");
            sender.sendMessage(noOnlinePlayers);
        }
        //BOOO MATHS PART
        int playersOnline = Bukkit.getOnlinePlayers().size();
        int playerRandomInt = playersOnline + 1;
        int randomPlayer = new Random().nextInt(Bukkit.getOnlinePlayers().size());
        List<? extends Player> playerList = Bukkit.getOnlinePlayers().stream().toList();
        Player raffleWinner = playerList.get(randomPlayer);
        //Maths over YIPPEE
        String raffleWinnerAlert = ChatColor.translateAlternateColorCodes('&',
                "&cThe Winner of the Raffle Is...");
        String rafflePlayerWinnerAlert = ChatColor.translateAlternateColorCodes('&',
                "&d" + raffleWinner.getDisplayName() + "&6 Congratulate Them!");
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(raffleWinnerAlert);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(rafflePlayerWinnerAlert);
        }
        return true;
    }
}