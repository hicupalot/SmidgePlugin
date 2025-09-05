import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Formatter;

public class stats implements CommandExecutor {
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
        Component divider = Component.text("-----------------------------").color(TextColor.color(254,63,63));
        Component introMessage = Component.text("Server Stats:").color(TextColor.color(254,63,63));

        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        Component onlinePlayersMessage = Component.text().append(Component.text("Currently there are ").color(TextColor.color(254,63,63)).
                append(Component.text(onlinePlayers).color(TextColor.color(217,163,52)).append(Component.text(" players online")).color(TextColor.color(254,63,63)))).build();

        String verInfo = Bukkit.getVersion();
        Component verMessage = Component.text().append(Component.text(("Bukkit Version: ")).color(TextColor.color(254,63,63)).append(Component.text(verInfo).color(TextColor.color(254,63,63)))).build();
        long totalSize = Bukkit.getWorldContainer().getTotalSpace()/1000000000; //With commas 1,000,000,000
        long freeSpace = Bukkit.getWorldContainer().getUsableSpace()/1000000000;
        long worldSize = totalSize-freeSpace;
        Component worldData = Component.text().append(Component.text("The World Is Currently: ").color(TextColor.color(254,63,63)).
                append(Component.text(worldSize)).color(TextColor.color(217,163,52)).append(Component.text("gb").color(TextColor.color(254,63,63)))).build();
        int totalPlayers = Bukkit.getOfflinePlayers().length;
        Component totalPlayersMessage = Component.text().append(Component.text("In total ").color(TextColor.color(254,63,63)).
                append(Component.text(totalPlayers).color(TextColor.color(217,163,52)).append(Component.text(" players have played on this server")).color(TextColor.color(254,63,63)))).build();
        int banAmount = Bukkit.getBannedPlayers().size();
        int whiteListAmount = Bukkit.getWhitelistedPlayers().size();
        double[] tpsCount = Bukkit.getTPS();
        Formatter cleanedUpTPS = new Formatter();
        cleanedUpTPS.format("%.2f", tpsCount[0]);
        int totalTicks = Bukkit.getCurrentTick();
        int seconds = totalTicks/20;
        int S = seconds % 60;
        int H = seconds / 60;
        int M = H % 60;
        Component whitelistedSize = Component.text().append(Component.text("In total ").color(TextColor.color(254,63,63)).
                append(Component.text(whiteListAmount).color(TextColor.color(217,163,52)).append(Component.text(" players have been whitelisted")).color(TextColor.color(254,63,63)))).build();
        Component banList = Component.text().append(Component.text("In total ").color(TextColor.color(254,63,63)).
                append(Component.text(banAmount).color(TextColor.color(217,163,52)).append(Component.text(" players have been banned")).color(TextColor.color(254,63,63)))).build();
        Component tps = Component.text().append(Component.text("Current Server TPS is: ").color(TextColor.color(254,63,63)).append(Component.text(String.valueOf(cleanedUpTPS))).color(TextColor.color(217,163,52))).build();
        Component totalT = Component.text().append(Component.text("Time Since Last Restart: ").color(TextColor.color(254,63,63)).
                append(Component.text(H).color(TextColor.color(254,63,63)).append(Component.text(" Hours ")).color(TextColor.color(254,63,63))
                        .append(Component.text(M)).color(TextColor.color(254,63,63)).append(Component.text(" Minutes ")).color(TextColor.color(254,63,63))
                        .append(Component.text(S)).color(TextColor.color(254,63,63)).append(Component.text(" Seconds")).color(TextColor.color(254,63,63)))).build();
        //------------------------------------------Messages Start------------------------------------------------------//
        sender.sendMessage(divider);
        sender.sendMessage(introMessage);
        sender.sendMessage(onlinePlayersMessage);
        sender.sendMessage(totalPlayersMessage);
        sender.sendMessage(whitelistedSize);
        sender.sendMessage(banList);
        sender.sendMessage(verMessage);
        sender.sendMessage(worldData);
        sender.sendMessage(tps);
        sender.sendMessage(totalT);
        sender.sendMessage(divider);
        //------------------------------------------Messages End------------------------------------------------------//

        return true;
    }
}