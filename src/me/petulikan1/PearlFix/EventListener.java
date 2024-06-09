package me.petulikan1.PearlFix;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL || e.getCause() == PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT) {
            Location to = e.getTo();
            World world = to.getWorld();
            if (!world.getWorldBorder().isInside(to)) {
                e.setCancelled(true);
                Utils.msg("Console", Bukkit.getConsoleSender(), e.getPlayer().getName(), e.getCause());
                Utils.msg("Player", e.getPlayer(), e.getCause());
                return;
            }
        }
    }


}
