package lol.maltest.badservertp;

import lol.maltest.badservertp.cmds.slowTpAll;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();

        this.getCommand("slowtpall").setExecutor(new slowTpAll());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
