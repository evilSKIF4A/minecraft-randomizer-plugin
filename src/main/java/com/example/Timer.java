package com.example;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.example.Event.Event;

public class Timer extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            Event.DoExecute(player);
        }
    }
}
