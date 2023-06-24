package com.example;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.example.CommandExecutor.CmdExecutor;
import com.example.Player.PlayerListener;

public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER = Logger.getLogger("Randomizer");
  private CmdExecutor startExecutor = new CmdExecutor();

  public void onEnable()
  {
    LOGGER.info("Randomizer enabled!");

    getCommand("start_randomize").setExecutor(startExecutor);
    getCommand("stop_randomize").setExecutor(startExecutor);

    Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

  }

  public void onDisable()
  {
    LOGGER.info("Randomizer disabled!");
  }
}
