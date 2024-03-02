import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatMuted implements Listener {
    @EventHandler
    public void onChatMutedMessageSend(AsyncPlayerChatEvent e) {
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (config.getBoolean("chatMuted", true)) {
            Player player = e.getPlayer();
            if (!player.hasPermission("smidge.staff")) {
                e.setCancelled(true);
                String reason = ChatColor.translateAlternateColorCodes('&',
                        "&e[&cSMIDGE&e] &cYou cannot send messages as the chat is currently muted");
                e.getPlayer().sendMessage(reason);
            }
        }
    }
}