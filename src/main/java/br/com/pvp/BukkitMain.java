package br.com.pvp;

import br.com.pvp.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMain extends JavaPlugin {

    private static BukkitMain instance;
    public static BukkitMain getInstance() { return instance; }

    private static Manager m;

    @Override
    public void onEnable() {
        instance = this;
        m = new Manager(this);
        m.setup();
        Bukkit.getConsoleSender().sendMessage("§e[MPvP] §7Iniciado com sucesso!");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        m.unSetup();
        Bukkit.getConsoleSender().sendMessage("§e[MPvP] §7Desligado com sucesso!");
        super.onDisable();
    }
}
