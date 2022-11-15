import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

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
         //   Bukkit.broadcastMessage(e.getCause().toString());
            if (e.getEntity().getCustomName() != null) {
                if (!Config.petDeathToggle.containsKey(playerUUID)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPetTootsiesToasties(EntityDamageEvent e) {
        if (!e.getEntity().getType().equals(EntityType.PLAYER) && e.getEntity().getType().isAlive()) {
            if (e.getEntity().getCustomName() != null) {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.LAVA) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)
                        || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPetCrunchOrSquash(EntityDamageEvent e) {
        if (!e.getEntity().getType().equals(EntityType.PLAYER) && e.getEntity().getType().isAlive()) {
            if (e.getEntity().getCustomName() != null) {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL) || e.getCause().equals(EntityDamageEvent.DamageCause.FALLING_BLOCK)
                        || e.getCause().equals(EntityDamageEvent.DamageCause.HOT_FLOOR) || e.getCause().equals(EntityDamageEvent.DamageCause.CRAMMING)
                        || e.getCause().equals(EntityDamageEvent.DamageCause.THORNS) || e.getCause().equals(EntityDamageEvent.DamageCause.SONIC_BOOM)
                        || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.MELTING)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    //DISABLE THE HASHMAP CASES
    @EventHandler
    public void onPetLeave(PlayerQuitEvent e) {
        UUID playerUUID = e.getPlayer().getUniqueId();
        Config.petDeathToggle.remove(playerUUID);
    }

}