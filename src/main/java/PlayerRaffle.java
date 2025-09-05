import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class PlayerRaffle implements CommandExecutor {
    private static final Logger log = LogManager.getLogger(PlayerRaffle.class);

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
            Component noOnlinePlayers = Component.text("No One Is Online").color(TextColor.color(254,63,63));
            sender.sendMessage(noOnlinePlayers);
        }
        //BOOO MATHS PART
        int playersOnline = Bukkit.getOnlinePlayers().size();
        int playerRandomInt = playersOnline + 1;
        int randomPlayer = new Random().nextInt(Bukkit.getOnlinePlayers().size());
        List<? extends Player> playerList = Bukkit.getOnlinePlayers().stream().toList();
        Player raffleWinner = playerList.get(randomPlayer);
        //Maths over YIPPEE
        Component raffleWinnerAlert = Component.text("The Winner of the Raffle Is...").color(TextColor.color(254,63,63));
        TextComponent.Builder rafflePlayerWinnerAlert = Component.text().append(Component.text(raffleWinner.getName()).color(TextColor.color(254,63,254))
                .append(Component.text(" Congratulate Them!").color(TextColor.color(217,163,52))));

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(raffleWinnerAlert);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.log(Level.WARN, e.getMessage(), e);
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(rafflePlayerWinnerAlert);
        }
        return true;
    }
}