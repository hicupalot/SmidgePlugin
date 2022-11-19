import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DebugWeapon implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "You Must Be a Player to Do This!");
            return false;
        }
        if (!sender.hasPermission("smidge.admin")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        int firstfreeslot = ((Player) sender).getInventory().firstEmpty();
        if (firstfreeslot ==-1){
            String noFreeSlot = ChatColor.translateAlternateColorCodes('&',"&c[Smidge] Your inventory is full!");
            sender.sendMessage(noFreeSlot);
            return false;
        }
        else {
            ItemStack debugWeapon = new ItemStack(Material.STICK, 100000);
            ((Player) sender).getInventory().setItem(firstfreeslot, debugWeapon);
        }
        return true;
    }
}
