package br.com.pvp.manager;

import br.com.pvp.BukkitMain;
import br.com.pvp.utils.ClassGetter;
import br.com.pvp.command.CommandBase;
import br.com.pvp.utils.TimeSecond;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Manager {

    private BukkitMain main;
    private Loader loader;

    public Manager(BukkitMain main) {
        this.main = main;
        this.loader = new Loader();
    }

    public Plugin getPlugin() {
        return main;
    }

    public FileConfiguration getConfig() {
        return main.getConfig();
    }

    public void setup() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(BukkitMain.class), new Runnable() {
            public void run() {
                Bukkit.getPluginManager().callEvent(new TimeSecond());
            }
        }, 20L, 0L);

        this.loader.loadCommands();
        this.loader.loadEvents();
    }

    public void unSetup() {
        HandlerList.unregisterAll();

        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            all.kickPlayer("§eServidor desligado ou reiniciado!");
        }
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                entity.remove();
            }
        }
    }

    class Loader {

        private void loadCommands() {
            for (Class<?> classes : ClassGetter.getClassesForPackage(BukkitMain.getPlugin(BukkitMain.class),
                    "br.com.pvp")) {
                if (CommandBase.class.isAssignableFrom(classes) && classes != CommandBase.class) {
                    try {
                        CommandBase classCmd = (CommandBase) classes.newInstance();
                        ((CraftServer) Bukkit.getServer()).getCommandMap().register(classCmd.getName().toLowerCase(),
                                classCmd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void loadEvents() {
            for (Class<?> classes : ClassGetter.getClassesForPackage(BukkitMain.getPlugin(BukkitMain.class),
                    "br.com.pvp")) {
                if (Listener.class.isAssignableFrom(classes)) {
                    try {
                        Listener classEvents = (Listener) classes.newInstance();
                        Bukkit.getPluginManager().registerEvents(classEvents, BukkitMain.getPlugin(BukkitMain.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}