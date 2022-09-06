import net.luckperms.api.event.LuckPermsEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.UUID;

public class StaffChat implements Listener {
    @SuppressWarnings("deprecated")
    @EventHandler
    public void onStaffSpeak(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        UUID playerUUID = e.getPlayer().getUniqueId();
        String chatMessage = e.getMessage();
        if (Config.staffToggle.containsKey(playerUUID)){
            Bukkit.broadcast(ChatColor.RED+"["+ChatColor.YELLOW+"STAFF"+ChatColor.RED+"] "+ player.getDisplayName() +": " + chatMessage, "smidge.staffChat");
        }
    }
}
