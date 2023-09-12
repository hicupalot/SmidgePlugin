import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Config {
        public static final HashMap<UUID, Boolean> staffToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID,Boolean>  adminToggle = new HashMap<UUID,Boolean>();
        public static final HashMap<UUID, Boolean> streamerToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID, Boolean> petDeathToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID, Boolean> muteChat = new HashMap<>();
        public static final HashMap<UUID, Location> originalLocation = new HashMap<>();
        public static final HashMap<UUID, Boolean> hideAllToggle = new HashMap<>();
        public static final Player smidgeUser = Bukkit.getPlayer("34ac28eb-7372-44dc-9ef0-71bb0ca7885c");

        //DEFAULT MESSAGES
        public static final String noPermission = ChatColor.translateAlternateColorCodes('&',
                "&c[Smidge] You do not have permission");
        public static final String notPlayer = ChatColor.translateAlternateColorCodes('&',
                "&c[Smidge] You must be a player to do this");
        public static final String notConsole = ChatColor.translateAlternateColorCodes('&',
                "&c[Smidge] You must perform this command on the Console");
        public static final String notPlayerOrConsole = ChatColor.translateAlternateColorCodes('&',
                "&c[Smidge] Not sure how you are doing this, but you aren't able to!");
        public static final String staffRemove = ChatColor.translateAlternateColorCodes('&',
                "&c[&eSTAFFCHAT&c] &eDISABLED");
        public static final String streamerRemove = ChatColor.translateAlternateColorCodes('&',
            "&e[&9STREAMER&e] &9DISABLED");
        public static final String adminRemove = ChatColor.translateAlternateColorCodes('&',
            "&e[&4ADMINCHAT&e] &4DISABLED");
        public static final String staffAdd = ChatColor.translateAlternateColorCodes('&',
            "&c[&eSTAFFCHAT&c] &eENABLED");
        public static final String streamerAdd = ChatColor.translateAlternateColorCodes('&',
            "&e[&9STREAMER&e] &9ENABLED");
        public static final String adminAdd = ChatColor.translateAlternateColorCodes('&',
            "&e[&4ADMINCHAT&e] &4ENABLED");

        //

    //Formatting
    public static final String staffPrefix = ChatColor.translateAlternateColorCodes('&',"&e[&cSTAFF&e]");
    public static final String adminPrefix = ChatColor.translateAlternateColorCodes('&',"&c[&4ADMIN&c]");
    public static final String streamerPrefix = ChatColor.translateAlternateColorCodes('&',"&e[&9STREAMER&e]");

}
