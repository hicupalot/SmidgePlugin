import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public class Config {
    public static SmidgeThing plugin;

    public Config(SmidgeThing instance) {
        plugin = instance;
    }
        public static final HashMap<UUID, Boolean> staffToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID,Boolean>  adminToggle = new HashMap<UUID,Boolean>();
        public static final HashMap<UUID, Boolean> streamerToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID, Boolean> petDeathToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID, Boolean> muteChat = new HashMap<>();
        public static final HashMap<UUID,Boolean> maintenaceToggle = new HashMap<>();
        public static final HashMap<UUID, Location> originalLocation = new HashMap<>();

        //DEFAULT MESSAGES
        public static final String noPermission = ChatColor.translateAlternateColorCodes('&',
                "&c[Smidge] You do not have permission");
        public static final String notPlayer = ChatColor.translateAlternateColorCodes('&',
                "&c[Smidge] You must be a player to do this");
}
