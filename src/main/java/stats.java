import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.admin")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        String intoMessage = ChatColor.translateAlternateColorCodes('&',"&cServer Stats:");
        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        String onlinePlayersMessage = ChatColor.translateAlternateColorCodes('&',"&cCurrently there are &6"+
                onlinePlayers +" &conline");
        String verInfo = Bukkit.getBukkitVersion();
        String verMessage = ChatColor.translateAlternateColorCodes('&',"&cBukkit Version: " +verInfo);
        long worldSize = Bukkit.getWorldContainer().getTotalSpace()/1000000000;
        String worldData = ChatColor.translateAlternateColorCodes('&',"&cThe World Is Currently: &6"+worldSize+"&cgb" );
        int totalPlayers = Bukkit.getOfflinePlayers().length;
        String divider = ChatColor.translateAlternateColorCodes('&',"&c-----------------------------");
        String totalPlayersMessage = ChatColor.translateAlternateColorCodes('&', "&cIn total &6"+totalPlayers+" &cplayers have played on this server");
        int banAmount = Bukkit.getBannedPlayers().size();
        int whiteListAmount = Bukkit.getWhitelistedPlayers().size();
        String whitelistedSize = ChatColor.translateAlternateColorCodes('&',"&cIn total &6"+whiteListAmount+" &cplayers have been whitelisted");
        String banList = ChatColor.translateAlternateColorCodes('&',"&cIn total &6"+banAmount+" &cplayers have been banned");
        //------------------------------------------Messages Start------------------------------------------------------//
        sender.sendMessage(divider);
        sender.sendMessage(intoMessage);
        sender.sendMessage(onlinePlayersMessage);
        sender.sendMessage(totalPlayersMessage);
        sender.sendMessage(whitelistedSize);
        sender.sendMessage(banList);
        sender.sendMessage(verMessage);
        sender.sendMessage(worldData);
        sender.sendMessage(divider);
        //------------------------------------------Messages End------------------------------------------------------//

        return true;
    }
}