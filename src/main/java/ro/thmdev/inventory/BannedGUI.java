package ro.thmdev.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ro.thmdev.RCLBListener;
import ro.thmdev.player.ListenedPlayer;
import ro.thmdev.player.ListenedPlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BannedGUI implements InventoryProvider {

    public static final SmartInventory gui = SmartInventory.builder()
            .id("rclblistener-gui")
            .provider(new BannedGUI())
            .size(5, 9)
            .title(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(RCLBListener.cfg.getString("gui.title"))))
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(RCLBListener.cfg.getString("gui.book-name"))));
        List<String> lore = RCLBListener.cfg.getStringList("gui.book-lore");
        lore = color(lore);
        lore = applyPlaceholders(ListenedPlayerManager.getListenedPlayer(player), lore);
        meta.setLore(lore);
        book.setItemMeta(meta);
        contents.set(2, 4, ClickableItem.of(book, e -> e.getWhoClicked().closeInventory()));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }

    private String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private List<String> color(List<String> lore){
        List<String> clore = new ArrayList<>();
        for(String s : lore){
            clore.add(color(s));
        }
        return clore;
    }

    private List<String> applyPlaceholders(ListenedPlayer listenedPlayer, List<String> lore) {
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            if (s.contains("%reason%"))
                s = s.replace("%reason%", listenedPlayer.getReason());
            newLore.add(s);
        }
        return newLore;
    }
}
