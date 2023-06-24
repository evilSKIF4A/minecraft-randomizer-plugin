package com.example.Player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GREEN + player.getName() + " врывается с двух ног");
        player.sendMessage(ChatColor.GOLD + "Добро пожаловать!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(ChatColor.RED + event.getPlayer().getName() + " ливнул с позором");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setMessage(ChatColor.WHITE + event.getMessage());
        event.setFormat(ChatColor.GOLD + event.getPlayer().getName() + " -> " + event.getMessage());
    }
}
