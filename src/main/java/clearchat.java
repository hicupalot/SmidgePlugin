import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class clearchat implements CommandExecutor {
    @Override
    @SuppressWarnings("deprecated")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("smidge.staff") || Config.streamMode.containsKey(player.getUniqueId())) {
                for (int i = 0; i<200; i++){
                    Component emptyMessage = Component.text("");
                    player.sendMessage(emptyMessage);
                }
            }

        }
        String StaffBroadcast = ChatColor.translateAlternateColorCodes('&', "&c[&eSTAFF&c] &eChat was cleared by " + sender.getName())
                + " but you are immune, YIPPEE";
        String NonStaffBroadcast = ChatColor.translateAlternateColorCodes('&', "&cThe chat was cleared by Staff");

        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission("smidge.staff") && !Config.streamMode.containsKey(staff.getUniqueId())) {
                staff.sendMessage(StaffBroadcast);
            }
            for (Player nonStaff : Bukkit.getOnlinePlayers()) {
                if (!nonStaff.hasPermission("smidge.staff") || Config.streamMode.containsKey(nonStaff.getUniqueId())) {
                    nonStaff.sendMessage(NonStaffBroadcast);
                }
            }
        }
        return true;
    }
}
