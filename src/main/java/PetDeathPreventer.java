import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public class PetDeathPreventer implements Listener {
    @EventHandler
    public void onPetHurt(EntityDamageByEntityEvent e) {
        UUID playerUUID = e.getDamager().getUniqueId();
        if (!e.getDamager().getType().equals(EntityType.PLAYER)){
            e.setCancelled(true);
            return;
        }
        if (!e.getEntity().getType().equals(EntityType.PLAYER) && e.getEntity().getType().isAlive()) {
            if (e.getEntity().getCustomName() != null) {
                if (!Config.petDeathToggle.containsKey(playerUUID)) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onPetLeave(PlayerQuitEvent e){
        UUID playerUUID = e.getPlayer().getUniqueId();
        Config.petDeathToggle.remove(playerUUID);
    }
}