package com.example.CommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.example.Plugin;
import com.example.Timer;
import com.example.Utils;

import net.md_5.bungee.api.ChatColor;

public class CmdExecutor implements CommandExecutor {

    private Timer timer = new Timer();
    private boolean timerRunning = false;
    private Plugin plugin;
    private final int delay = 5;
    private int period = 60;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("start_randomize") && !timerRunning){
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + sender.getName() + " запустил randomize! GO! GO! GO!");
            if (args.length == 1 && Utils.isNumeric(args[0]))
                period = Integer.parseInt(args[0]);
            timerRunning = true;
            timer.runTaskTimer(plugin, delay * 20L, period * 20L);
            return true;
        }
        else if(label.equalsIgnoreCase("start_randomize")){
            sender.sendMessage(ChatColor.RED + "Randomizer уже запущен!");
            return true;
        }

        if(label.equalsIgnoreCase("stop_randomize") && timerRunning){
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "Randomizer отключен!");
            timerRunning = false;
            timer.cancel();
            timer = new Timer();
            return true;
        }
        else if(label.equalsIgnoreCase("stop_randomize"))
        {
            sender.sendMessage(ChatColor.RED + "Randomizer уже отключен!");
            return true;
        }

        return false;
    }

}
