import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMessageManager implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        EntityDamageEvent.DamageCause deathReason = e.getEntity().getLastDamageCause().getCause();
        String playerName = e.getEntity().getDisplayName();
        if (deathReason.equals(EntityDamageEvent.DamageCause.FALL)){
            e.setDeathMessage(playerName + " went splat");
        }
        else if (deathReason.equals(EntityDamageEvent.DamageCause.VOID)){
            e.setDeathMessage("How did "+playerName+" even get here??");
        }
        else if (deathReason.equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)){
            e.setDeathMessage(playerName+" was exploded!");
        }
    }
}
