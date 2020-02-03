package br.com.pvp.utils.user;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Selector {

    public static void spawnSelector(Player p) {

        ItemStack chest = new ItemStack(Material.CHEST);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setDisplayName("§6Kits §f- §7Clique");
        chest.setItemMeta(chestMeta);

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§6Warps §f- §7Clique");
        compass.setItemMeta(compassMeta);

        p.getInventory().setItem(3, chest);
        p.getInventory().setItem(5, compass);
        p.updateInventory();

    }


 }
