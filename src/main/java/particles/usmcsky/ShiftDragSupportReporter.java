package particles.usmcsky;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

final class ShiftDragSupportReporter {
    private ShiftDragSupportReporter() {
    }

    static void logStatus(JavaPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        Plugin protocolLib = pluginManager.getPlugin("ProtocolLib");

        if (protocolLib == null || !protocolLib.isEnabled()) {
            plugin.getLogger().warning(
                "Shift-drag transfer is unavailable: ProtocolLib is not installed, and stock Paper/Bukkit "
                    + "does not expose that gesture from vanilla clients."
            );
            return;
        }

        plugin.getLogger().warning(
            "Shift-drag transfer is unavailable: ProtocolLib is installed, but vanilla inventory packets still "
                + "do not expose a distinct shift-drag transfer gesture."
        );
    }
}
