package me.petulikan1.PearlFix;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Loader extends JavaPlugin {


    private EventListener listener = null;
    @Getter
    protected static Loader main;

    @Override
    public void onEnable() {
        main = this;
        Bukkit.getPluginManager().registerEvents(listener = new EventListener(), this);
        saveDefaultConfig();
    }


    @Override
    public void onDisable() {
        if (listener != null)
            HandlerList.unregisterAll(listener);
    }
}
