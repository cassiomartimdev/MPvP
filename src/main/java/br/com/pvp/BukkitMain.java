package br.com.pvp;

import br.com.pvp.manager.Manager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMain extends JavaPlugin {

    public static Plugin plugin;
    public static Plugin getPlugin() { return plugin; }

    public static BukkitMain instance;
    public static BukkitMain getInstance() { return instance; }

    private static Manager m;

    @Override
    public void onEnable() {
        instance = this;
        plugin = this;
        m = new Manager(this);
        m.setup();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        m.unSetup();
        super.onDisable();
    }
}
