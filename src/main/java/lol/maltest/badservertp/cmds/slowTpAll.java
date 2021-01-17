package lol.maltest.badservertp.cmds;

import lol.maltest.badservertp.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class slowTpAll implements CommandExecutor {

    FileConfiguration config = Main.plugin.getConfig();
    int n = 0;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission(config.getString("strings.permission"))) {
                Location loc = player.getLocation();

                for(Player players : Bukkit.getOnlinePlayers()) {
                    new BukkitRunnable(){
                        public void run() {
                            players.teleport(loc);
                            players.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("strings.tped").replaceAll("%p%", player.getName())));
                            n++;
                        }
                    }.runTaskLater(Main.plugin, config.getLong("settings.delay"));
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("strings.done").replaceAll("%n%", String.valueOf(n))));
            } else {
                player.sendMessage(ChatColor.RED + "No permission");
            }
        } else {
            System.out.println("Need to be a player.");
        }
        return false;
    }
}
