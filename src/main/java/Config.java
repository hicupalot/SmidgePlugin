import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import net.kyori.adventure.text.Component;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class Config {
        public static final HashMap<UUID, Boolean> staffToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID,Boolean>  adminToggle = new HashMap<UUID,Boolean>();
        public static final HashMap<UUID, Boolean> streamerToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID, Boolean> streamMode = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID, Boolean> petDeathToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID, Location> originalLocation = new HashMap<>();
        public static final HashMap<UUID,Boolean> vanishState = new HashMap<>();
        //DEFAULT MESSAGES
        public static final Component noPermission = Component.text("[Smidge] You do not have permission").color(TextColor.color(254,63,63));
        public static final Component notPlayer = Component.text("[Smidge] You must be a player to do this").color(TextColor.color(254,63,63));
        public static final Component notConsole = Component.text("[Smidge] You must perform this command on the Console").color(TextColor.color(254,63,63));
        public static final Component notPlayerOrConsole = Component.text("[Smidge] Not sure how you are doing this, but you aren't able to!").color(TextColor.color(254,63,63));
        public static final Component staffRemove = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STAFFCHAT").color(TextColor.color(254, 63,63)).append(Component.text("]").color(TextColor.color(254,254,63)).append(
                    Component.text(" DISABLED").color(TextColor.color(254,63,63)))))).build();
        public static final Component streamerRemove = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STREAMERCHAT").color(TextColor.color(63, 63,254)).append(Component.text("]").color(TextColor.color(254,254,63))
                    .append(Component.text(" DISABLED").color(TextColor.color(63,63,254)))))).build();
        public static final Component adminRemove = Component.text().append(Component.text("[").color(TextColor.color(254, 63, 63 )).
            append(Component.text("ADMINCHAT").color(TextColor.color(190, 0,0)).append(Component.text("]").color(TextColor.color(254,254,63))
                    .append(Component.text(" DISABLED").color(TextColor.color(190,0,0)))))).build();
        public static final Component staffAdd = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STAFFCHAT").color(TextColor.color(254, 63,63)).append(Component.text("]").color(TextColor.color(254,254,63)).append(
                    Component.text(" ENABLED").color(TextColor.color(254,63,63)))))).build();
        public static final Component streamerAdd = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STREAMERCHAT").color(TextColor.color(63, 63,254)).append(Component.text("]").color(TextColor.color(254,254,63))
                    .append(Component.text(" ENABLED").color(TextColor.color(63,63,254)))))).build();
        public static final Component adminAdd = Component.text().append(Component.text("[").color(TextColor.color(254, 63, 63 )).
            append(Component.text("ADMINCHAT").color(TextColor.color(190, 0,0)).append(Component.text("]").color(TextColor.color(254,254,63))
                    .append(Component.text(" ENABLED").color(TextColor.color(190,0,0)))))).build();
    public static final Component streamAdd = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STREAM MODE").color(TextColor.color(63, 254,63)).append(Component.text("]").color(TextColor.color(254,254,63)).append(
                    Component.text(" ENABLED").color(TextColor.color(63,254,63)))))).build();
    public static final Component streamRemove = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STREAM MODE").color(TextColor.color(63, 254,63)).append(Component.text("]").color(TextColor.color(254,254,63)).append(
                    Component.text(" DISABLED").color(TextColor.color(63,254,63)))))).build();
        //

    //Formatting
    public static final Component staffPrefix = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STAFF").color(TextColor.color(254, 63,63)).append(Component.text("]").color(TextColor.color(254,254,63))))).build();
    public static final Component adminPrefix = Component.text().append(Component.text("[").color(TextColor.color(254, 63, 63 )).
            append(Component.text("ADMIN").color(TextColor.color(190, 0,0)).append(Component.text("]").color(TextColor.color(254,254,63))))).build();
    public static final Component streamerPrefix = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STREAMER").color(TextColor.color(63, 63,254)).append(Component.text("]").color(TextColor.color(254,254,63))))).build();
}
