import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Config {
    public static SmidgeThing plugin;

    public Config(SmidgeThing instance) {
        plugin = instance;
    }
        public static final HashMap<UUID, Boolean> staffToggle = new HashMap<UUID, Boolean>();
        public static final HashMap<UUID,Boolean>  adminToggle = new HashMap<UUID,Boolean>();
}
