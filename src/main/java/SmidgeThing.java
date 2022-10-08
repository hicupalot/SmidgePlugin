import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmidgeThing extends JavaPlugin {
    @Override
   public void onEnable(){
        System.out.println(ChatColor.YELLOW+"[SmidgeCode] is starting up!");
        getServer().getPluginManager().registerEvents(new EndDisable(),this);
        getServer().getPluginManager().registerEvents(new AdminChat(),this);
        getServer().getPluginManager().registerEvents(new StaffChat(),this);
        getServer().getPluginManager().registerEvents(new StreamerChat(),this);
        getServer().getPluginManager().registerEvents(new PetDeathPreventer(),this);
        getCommand("ToggleAdminChat").setExecutor(new ToggleAdminChat());
        getCommand("ToggleStaffChat").setExecutor(new ToggleStaffChat());
        getCommand("ToggleStreamerChat").setExecutor(new ToggleStreamerChat());
        getCommand("Follower").setExecutor(new Follower());
        getCommand("TogglePetKiller").setExecutor(new TogglePetKiller());
        getCommand("clearchat").setExecutor(new clearchat());
    }
    @Override
    public void onDisable() {
        System.out.println(ChatColor.GOLD + "[SmidgeCode] Shutting Down!");
    }
}

