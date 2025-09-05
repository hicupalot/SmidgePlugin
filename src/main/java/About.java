import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class About implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        Component pluginName = Component.text("SmidgeCode ").color(TextColor.color(254,63,63));
        Component pluginVersion = Component.text().append(Component.text("v").color(TextColor.color(63,254,254)).append((Component.text("1.9").color(TextColor.color(63,254,254))))).build();
        Component pluginAuthor = Component.text("hicupalot").color(TextColor.color(217,163,52));
        Component pluginDescription = Component.text("This is a custom plugin for Smidge").color(TextColor.color(217,163,52));

        //Variables Above, Message Sends Below

        Component authVer = Component.text().append(pluginName).append(pluginVersion).append(Component.text(":")).build();
        sender.sendMessage(authVer);

        Component description = Component.text("Description: ").color(TextColor.color(217,163,52));
        Component descriptionBuilder = Component.text().append(description).append(pluginDescription).build();
        sender.sendMessage(descriptionBuilder);

        Component author = Component.text("Author: ").color(TextColor.color(217,163,52));
        Component authorBuilder = Component.text().append(author).append(pluginAuthor).build();
        sender.sendMessage(authorBuilder);
  return true;
    }
}
