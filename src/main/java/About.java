import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class About implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String pluginAuthor = ChatColor.translateAlternateColorCodes('&', " &6hicupalot ");
        String pluginName = ChatColor.translateAlternateColorCodes('&', " &cSmidgeCode ");
        String pluginVersion = ChatColor.translateAlternateColorCodes('&', " &bv " + "1.2.5");
        String pluginDescription = ChatColor.translateAlternateColorCodes('&', "&6This is a custom plugin for Smidge");
        //Variables Above, Message Sends Below
        sender.sendMessage(pluginName + pluginVersion + ":");
        sender.sendMessage(ChatColor.GOLD + "Description: " + pluginDescription);
        sender.sendMessage(ChatColor.GOLD + "Author:" + pluginAuthor);
  return true;
    }
}
