import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PetDeathPreventer implements Listener {
    @EventHandler
    public void onPetHurt(EntityDamageByEntityEvent e) {
        if (!e.getEntity().getType().equals(EntityType.PLAYER) || e.getEntity().getType().equals(EntityType.ARMOR_STAND))
        if (e.getEntity().getCustomName() != null) {
            if (!((Player) e.getDamager()).getInventory().getItem(EquipmentSlot.HAND)
                    .getItemMeta().getDisplayName().equals("PETKILLER")) {
                e.setCancelled(true);
            }
        }
    }
}
