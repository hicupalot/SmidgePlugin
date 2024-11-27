import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class StaffChat implements Listener {
    @SuppressWarnings("deprecated")
    @EventHandler
    public void onStaffSpeak(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = e.getPlayer().getUniqueId();
        String chatMessage = e.getMessage();
        if (Config.staffToggle.containsKey(playerUUID)) {
            String colorMessage = ChatColor.translateAlternateColorCodes('&', chatMessage);
            String name = ChatColor.translateAlternateColorCodes('&', "&6"+e.getPlayer().getDisplayName());
            String StaffChatMessages = ChatColor.translateAlternateColorCodes('&',"&e[&cSTAFF&e] " + name + ": " + ChatColor.GOLD + colorMessage);
            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (staff.hasPermission("smidge.staff") && !Config.streamMode.containsKey(staff.getUniqueId())) {
                    staff.sendMessage(StaffChatMessages);
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onStaffQuit(PlayerQuitEvent e) {
        UUID playerUUID = e.getPlayer().getUniqueId();
        Config.staffToggle.remove(playerUUID);
    }
}
