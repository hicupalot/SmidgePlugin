import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class EndDisable implements Listener {
    @EventHandler
    public void onEnd(EntityInteractEvent e){
        if (e.getBlock().getType().equals(Material.END_PORTAL_FRAME) || e.getBlock().getType().equals(Material.END_PORTAL_FRAME)){
            e.setCancelled(true);
        }
    }
}
