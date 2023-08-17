import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmidgeThing extends JavaPlugin {
    private static SmidgeThing instance;
    public static SmidgeThing getInstance() {
        return instance;
    }
    @Override
   public void onEnable(){
        instance = this;
        String startup = ChatColor.translateAlternateColorCodes('&',"&6[SmidgeCode] is starting");
        getLogger().info(startup);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EndDisable(),this);
        getServer().getPluginManager().registerEvents(new AdminChat(),this);
        getServer().getPluginManager().registerEvents(new StaffChat(),this);
        getServer().getPluginManager().registerEvents(new StreamerChat(),this);
        getServer().getPluginManager().registerEvents(new PetDeathPreventer(),this);
        getServer().getPluginManager().registerEvents(new ChatMuted(),this);
        getServer().getPluginManager().registerEvents(new maintenance(), this);
        getServer().getPluginManager().registerEvents(new DeathMessageManager(), this);
        getServer().getPluginManager().registerEvents(new Filter(),this);
        /*This Seperates out the Events and Commands
        ----------------------------------------------------------------------------------------
         */
        getCommand("ToggleAdminChat").setExecutor(new ToggleAdminChat());
        getCommand("ToggleStaffChat").setExecutor(new ToggleStaffChat());
        getCommand("ToggleStreamerChat").setExecutor(new ToggleStreamerChat());
        getCommand("follower").setExecutor(new Follower());
        getCommand("TogglePetKiller").setExecutor(new TogglePetKiller());
        getCommand("clearchat").setExecutor(new clearchat());
        getCommand("mutechat").setExecutor(new mutechat());
        getCommand("kickall").setExecutor(new kickall());
        getCommand("maintenancetoggle").setExecutor(new maintenancetoggle());
        getCommand("About").setExecutor(new About());
        getCommand("PlayerRaffle").setExecutor(new PlayerRaffle());
        getCommand("DeFollow").setExecutor(new DeFollow());
        getCommand("stats").setExecutor(new stats());
        getCommand("timer").setExecutor(new Timer());
        getCommand("convert").setExecutor(new TimeConvert());
        getCommand("stoptimer").setExecutor(new StopTimer());
        getCommand("togglestreaming").setExecutor(new ToggleStreaming());
        getCommand("filteradd").setExecutor(new FilterAdd());
        getCommand("filterremove").setExecutor(new FilterRemove());
        getCommand("coinflip").setExecutor(new CoinFlip());
    }
    @Override
    public void onDisable() {
        String shutdown = ChatColor.translateAlternateColorCodes('&',"&c[SmidgeCode] Shutting Down!");
        getLogger().info(shutdown);
    }
}

