package particles.usmcsky;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class SortListener implements Listener {
    private static final int[] PLAYER_STORAGE_VISUAL_ORDER = {
        9, 10, 11, 12, 13, 14, 15, 16, 17,
        18, 19, 20, 21, 22, 23, 24, 25, 26,
        27, 28, 29, 30, 31, 32, 33, 34, 35,
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };

    private final Sort plugin;

    public SortListener(Sort plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClick() != ClickType.SWAP_OFFHAND) {
            return;
        }
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }
        if (!player.hasPermission("sort.use")) {
            return;
        }
        if (plugin.getConfig().getBoolean("require-shift", false) && !player.isSneaking()) {
            return;
        }
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getCursor() != null && event.getCursor().getType() != Material.AIR) {
            player.sendMessage(ChatColor.RED + "Clear your cursor item before sorting.");
            return;
        }

        event.setCancelled(true);
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory instanceof PlayerInventory playerInventory) {
            sortPlayerStorage(playerInventory);
            player.sendMessage(ChatColor.GREEN + "Sorted your inventory.");
            return;
        }

        sortSlots(clickedInventory, buildSequentialSlots(clickedInventory.getSize()));
        player.sendMessage(ChatColor.GREEN + "Sorted this container.");
    }

    private static void sortPlayerStorage(PlayerInventory inventory) {
        sortSlots(inventory, PLAYER_STORAGE_VISUAL_ORDER);
    }

    private static int[] buildSequentialSlots(int size) {
        int[] slots = new int[size];
        for (int i = 0; i < size; i++) {
            slots[i] = i;
        }
        return slots;
    }

    private static void sortSlots(Inventory inventory, int[] targetSlots) {
        List<ItemStack> merged = new ArrayList<>();
        for (int slot : targetSlots) {
            ItemStack item = inventory.getItem(slot);
            if (item == null || item.getType().isAir()) {
                continue;
            }
            addMerged(merged, item);
        }

        merged.sort(
            Comparator.comparing((ItemStack item) -> item.getType().name())
                .thenComparing(SortListener::metaKey)
                .thenComparing(Comparator.comparingInt(ItemStack::getAmount).reversed())
        );

        int slotIndex = 0;
        for (; slotIndex < merged.size() && slotIndex < targetSlots.length; slotIndex++) {
            inventory.setItem(targetSlots[slotIndex], merged.get(slotIndex));
        }
        for (; slotIndex < targetSlots.length; slotIndex++) {
            inventory.setItem(targetSlots[slotIndex], null);
        }
    }

    private static void addMerged(List<ItemStack> merged, ItemStack item) {
        int remaining = item.getAmount();
        for (ItemStack existing : merged) {
            if (!existing.isSimilar(item)) {
                continue;
            }
            int max = existing.getMaxStackSize();
            int canMove = max - existing.getAmount();
            if (canMove <= 0) {
                continue;
            }
            int moved = Math.min(canMove, remaining);
            existing.setAmount(existing.getAmount() + moved);
            remaining -= moved;
            if (remaining == 0) {
                return;
            }
        }

        while (remaining > 0) {
            ItemStack clone = item.clone();
            int max = clone.getMaxStackSize();
            int amount = Math.min(max, remaining);
            clone.setAmount(amount);
            merged.add(clone);
            remaining -= amount;
        }
    }

    private static String metaKey(ItemStack item) {
        if (!item.hasItemMeta()) {
            return "";
        }
        return String.valueOf(item.getItemMeta().serialize());
    }
}
