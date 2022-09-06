import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EndDisable implements Listener {
    @EventHandler
    public void onEnd(EntityInteractEvent e) {
        if (e.getBlock().getType().equals(Material.END_PORTAL_FRAME) || e.getBlock().getType().equals(Material.END_PORTAL_FRAME)) {
            e.setCancelled(true);
        }
    }
    public void onTeleport(PlayerTeleportEvent e){
        Player player = e.getPlayer();
        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)){
            e.setCancelled(true);
            player.kickPlayer("Please do not enter the End!");

        }
    }
}
