import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.EventListener;

public class Filter implements Listener {
    @EventHandler
    public void onChatMessage(PlayerChatEvent e) {
        String chatMessage = e.getMessage().toLowerCase();
        Player player = e.getPlayer();
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        String[] words = e.getMessage().toLowerCase().split(" ");
        for (String word : words)
            if (config.getList("general").contains(word)) {
                e.setCancelled(true);
                String staffBroadcast = ChatColor.translateAlternateColorCodes('&', "&c[&ESMIDGEFILTER&c] "
                        + player.getName() + "said " + word + " this word is in the general filter!");
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (player1.hasPermission("smidge.staff")) {
                        player1.sendMessage(staffBroadcast);
                        //Possibly could use DiscordSRV
                    }
                }
            } else if (config.getBoolean("isStreaming")) {
                if (config.getList("stream").contains(word)) {
                    e.setCancelled(true);
                    String staffBroadcast = ChatColor.translateAlternateColorCodes('&', "&c[&ESMIDGEFILTER&c] "
                            + player.getName() + "said " + word + " this word is in the streaming filter!");
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (player1.hasPermission("smidge.staff")) {
                            player1.sendMessage(staffBroadcast);

                        }
                    }
                }
            }
    }
}