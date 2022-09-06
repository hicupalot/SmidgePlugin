import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmidgeThing extends JavaPlugin {
    @Override
   public void onEnable(){
        System.out.println(ChatColor.YELLOW+"[SmidgeCode] is starting up!");
        getServer().getPluginManager().registerEvents(new EndDisable(),this);
    }
    @Override
    public void onDisable() {
        System.out.println(ChatColor.GOLD + "[SmidgeCode] Shutting Down!");
    }
}

