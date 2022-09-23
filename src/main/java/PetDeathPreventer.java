import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PetDeathPreventer implements Listener {
    @EventHandler
    public void onPetHurt(EntityDamageByEntityEvent e) {
        Player damagingPlayer= (((Player)e.getDamager()));
        if (!e.getEntity().getType().equals(EntityType.PLAYER) && e.getEntity().getType().isAlive()) {
            if (e.getEntity().getCustomName() != null) {
                if (damagingPlayer.getInventory().getItemInMainHand()==null){
                    return;
                }
                if (!damagingPlayer.getInventory().getItem(EquipmentSlot.HAND)
                        .getItemMeta().getDisplayName().equals("PETKILLER")){
                    e.setCancelled(true);
                }
            }
        }
    }
}
