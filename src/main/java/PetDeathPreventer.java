import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public class PetDeathPreventer implements Listener {
    @EventHandler
    public void onPetHurt(EntityDamageByEntityEvent e) {
        UUID playerUUID = e.getDamager().getUniqueId();
        //Damager = Entity  Doing Damaging
        //Entity = Entity being damaged
        if (!e.getDamager().getType().equals(EntityType.PLAYER)
                && !e.getEntity().getType().equals(EntityType.PLAYER)) {
            if (e.getEntity().getCustomName() != null) {
                e.setCancelled(true);
                return;
            }
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
    public void onPetTootsiesToasties(EntityDamageByBlockEvent e) {
        if (!e.getEntity().getType().equals(EntityType.PLAYER) && e.getEntity().getType().isAlive()) {
            if (e.getEntity().getCustomName() != null) {
                e.setCancelled(true);
            }
        }
    }

    //DISABLE THE HASHMAP CASES
    @EventHandler
    public void onPetLeave(PlayerQuitEvent e) {
        UUID playerUUID = e.getPlayer().getUniqueId();
        Config.petDeathToggle.remove(playerUUID);
    }

    @EventHandler
    public void onPetTeleport(PlayerTeleportEvent e) {
        UUID playerUUID = e.getPlayer().getUniqueId();
        Config.petDeathToggle.remove(playerUUID);
        e.getPlayer().sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "PETKILLING" + ChatColor.YELLOW + "] "
                + ChatColor.RED + "DISABLED");

    }
}