package particles.usmcsky;

import org.bukkit.plugin.java.JavaPlugin;

public final class Sort extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new SortListener(this), this);
    }
}
