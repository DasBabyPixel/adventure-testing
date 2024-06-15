package de.dasbabypixel.adventuretesting;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.ClickEvent.suggestCommand;
import static net.kyori.adventure.text.event.HoverEvent.showText;

public class AdventureTesting extends JavaPlugin implements Listener {

    private BukkitAudiences provider;

    @Override
    public void onEnable() {
        provider = BukkitAudiences.create(this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void handle(PlayerToggleSneakEvent event) {
        provider
                .player(event.getPlayer())
                .sendMessage(text("Test").clickEvent(suggestCommand("Suggestion")).hoverEvent(showText(text("Hover"))));
    }
}
