import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMessageManager implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        EntityDamageEvent.DamageCause deathReason = e.getEntity().getLastDamageCause().getCause();
        String playerName = e.getEntity().getName();
        if (deathReason.equals(EntityDamageEvent.DamageCause.FALL)){
            Component splat = Component.text(playerName + " went splat");
            e.deathMessage(splat);
        }
        else if (deathReason.equals(EntityDamageEvent.DamageCause.VOID)){
            Component voidDeath = Component.text(playerName + " died, how did they even get here?!");
            e.deathMessage(voidDeath);
        }
        else if (deathReason.equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)){
            Component exploded = Component.text(playerName + " was exploded!");
            e.deathMessage(exploded);
        }
    }
}
