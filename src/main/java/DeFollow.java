import de.myzelyam.api.vanish.VanishAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class DeFollow implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Component teleportConfirmation = Component.text("[Smidge] You were teleported back to your previous position").color(TextColor.color(217,163,52));
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (!Config.originalLocation.containsKey(((Player) sender).getUniqueId())){
            Component noFollow = Component.text("You aren't currently following anyone!").color(TextColor.color(254,63,63));
            sender.sendMessage(noFollow);
            return false;
        } else {
            Location playerLoc = Config.originalLocation.get(((Player) sender).getUniqueId());
            ((Player) sender).teleport(playerLoc);
            ((Player) sender).setGameMode(GameMode.CREATIVE);
            sender.sendMessage(teleportConfirmation);
        }
        if (!Config.vanishState.containsKey(((Player) sender).getUniqueId())) {
            VanishAPI.showPlayer(((Player) sender));
            Config.vanishState.remove(((Player) sender).getUniqueId(), true);
        }
        else if (Config.vanishState.get(((Player) sender).getUniqueId())){
            VanishAPI.hidePlayer(((Player) sender));
        }
        return true;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Location playerLoc = Config.originalLocation.get(e.getPlayer().getUniqueId());
        e.getPlayer().teleport(playerLoc);
        e.getPlayer().setGameMode(GameMode.CREATIVE);
    }
}
