import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmidgeThing extends JavaPlugin {
    @Override
   public void onEnable(){
        System.out.println(ChatColor.YELLOW+"SmidgeThing is starting up!");
        getServer().getPluginManager().registerEvents(new EndDisable(),this);

    }
}
