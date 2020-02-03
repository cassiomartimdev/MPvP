package br.com.pvp.event;

import br.com.pvp.utils.user.Selector;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        Selector.spawnSelector(p);
        return;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        return;
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("pvp.color")) {
            e.setFormat(p.getDisplayName() + "§e: §7" + e.getMessage().replace("&", "§"));
        } else {
            e.setFormat(p.getDisplayName() + "§e: §7" + e.getMessage());
        }
    }
}
