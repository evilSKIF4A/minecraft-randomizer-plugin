package com.example.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;

public class Event {
    
    public static List<String> eventsNamesList = new ArrayList<>(Arrays.asList("RandomHeart", "JumpBoost", "ShitToInventory", "DropActiveItem", "GiveDiamonds", 
                                "Hunger", "SpawnZombies", "BigBoom", "SetDay", "IncreaseDamage", "SetNight", "HealthMax",
                                "GiveDiamondSword", "SpawnTamedCat", "Deceleration", "GiveDiamondPick", "RemoveBlocks",
                                "Kick", "SpawnTamedDog", "LightningStrike", "TakeOffBlock", "BlindnessAndConfusion",
                                "SpeedUp", "GiveIronSet", "Levitation", "BigBoom", "SpawnCreeper"));

    private static final int tick = 20;

    public static void DoExecute(Player player){
        boolean canContinue = true;
        if(player == null || player.isDead())
            canContinue = false;

        if (!canContinue) return;

        switch(eventsNamesList.get(new Random().nextInt(eventsNamesList.size()))){
            case ("GiveBread"): giveBread(player); break;
            case ("RandomHeart"): randomHeart(player); break;
            case ("ShitToInventory"): shitToInventory(player); break;
            case ("DropActiveItem"): dropActiveItem(player); break;
            case ("GiveDiamonds"): giveDiamonds(player); break;
            case ("SpawnCreeper"): spawnCreeper(player); break;
            case ("SpawnZombies"): spawnZombies(player); break;
            case ("BigBoom"): bigBoom(player); break;
            case ("SetDay"): setDay(player); break;
            case ("SetNight"): setNight(player); break;
            case ("GiveIronSet"): giveIronSet(player); break;
            case ("GiveDiamondSword"): giveDiamondSword(player); break;
            case ("GiveDiamondPick"): giveDiamondPick(player); break;
            case ("SpawnTamedCat"): spawnTamedCat(player); break;
            case ("RemoveBlocks"): removeBlocks(player); break;
            case ("Kick"): kick(player); break;
            case ("SpawnTamedDog"): spawnTamedDog(player); break;
            case ("LightningStrike"): lightningStrike(player); break;
            case ("Deceleration"): deceleration(player); break;
            case ("BlindnessAndConfusion"): blindnessAndConfusion(player); break;
            case ("IncreaseDamage"): increaseDamage(player); break;
            case ("SpeedUp"): speedUp(player); break;
            case ("HealthMax"): healthMax(player); break;
            case ("Levitation"): levitation(player); break;
            case ("Hunger"): hunger(player); break;
            case ("JumpBoost"): jumpBoost(player); break;
        }
    }

    public static void giveToPlayer(Player player, Material itemType, int amount, int repetitions, ChatColor color,String displayName){
        ItemStack itemStack = new ItemStack(itemType, amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(color + displayName);
        itemStack.setItemMeta(meta);
        
        if (repetitions > 0){
            for(int i = 0; i < repetitions; i++)
                player.getInventory().addItem(itemStack);   
        }else{
            player.getInventory().addItem(itemStack);
        }
    }

    public static void giveBread(Player player){
        Bukkit.getServer().broadcastMessage("Игроку " + player.getName() + " дали хлебушка. Приятного аппетита!");
        giveToPlayer(player, Material.BREAD, 16, 0, ChatColor.DARK_RED, "Палка");
    }

    public static void randomHeart(Player player){
        double heart = new Random().nextDouble(20.0) + 1.0;
        Bukkit.getServer().broadcastMessage("Игроку " + player.getName() + " было установлено " + (int)heart + " ХП");
        player.setHealth(heart);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT, 1, 1);
    }

    public static void kick(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " получил смачного пинка");
        Vector direction = player.getLocation().getDirection();
        direction.setY(0);
        direction.normalize();
        direction.setY(0.5);
        player.setVelocity(direction.multiply(3));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT, 1, 1);
    }

    public static void shitToInventory(Player player){
        Bukkit.getServer().broadcastMessage("Игроку " + player.getName() + " насрали в инвентарь");
        int dirtAmount = 10;
        Material itemType = Material.DIRT;
        giveToPlayer(player, itemType, 64, dirtAmount, ChatColor.DARK_PURPLE, "Дерьмицо");
    }

    public static void dropActiveItem(Player player){
        if(player.getEquipment().getItemInMainHand().getType() == Material.AIR){
            Bukkit.getServer().broadcastMessage("У игрока " + player.getName() + " попытались удалить предмет из рук");

        }else{
            Bukkit.getServer().broadcastMessage("У игрока " + player.getName() + " удалили предмет из рук");
            player.getInventory().setItemInMainHand(null);
        }
    }

    public static void giveDiamonds(Player player){
        Bukkit.getServer().broadcastMessage("Игроку " + player.getName() + " дали от души голубые камушки");
        int diamondAmount = 16;
        Material itemType = Material.DIAMOND;
        giveToPlayer(player, itemType, diamondAmount, 0, ChatColor.BLUE, "Голубой камушек");
    }

    public static void spawnCreeper(Player player){
        Vector direction = player.getLocation().getDirection();
        Bukkit.getServer().broadcastMessage("Игроку " + player.getName() + " прислали в подарок крипера");
        direction.setY(0);
        direction.normalize();
        player.getWorld().spawnEntity(player.getLocation().clone().subtract(direction.multiply(1)), EntityType.CREEPER);
    }

    public static void spawnZombies(Player player){
        Bukkit.getServer().broadcastMessage("За игроком " + player.getName() + " пришли спортики");
        int zombieAmount = 3;
        Vector direction = player.getLocation().getDirection();
        LivingEntity zombie;
        direction.setY(0);
        direction.normalize();
        for (int i = 1; i <= zombieAmount; i++) {
            Location newloc = player.getLocation().clone();
            Vector newdir = direction.clone();
            newdir = newdir.rotateAroundY(1.5708 * i).multiply(2);
            newloc.add(newdir);
            zombie = (LivingEntity) player.getWorld().spawnEntity(newloc, EntityType.ZOMBIE);
            zombie.setCustomName("Спортик");
            Objects.requireNonNull(zombie.getEquipment()).setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
            if (!((Zombie) zombie).isBaby()) {
                Objects.requireNonNull(zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.28);
            }
        }
    }

    public static void bigBoom(Player player){
        float radius = 0.5f;
        Bukkit.getServer().broadcastMessage("Игрок " + player.getName() + " наступил на мину");
        player.getWorld().createExplosion(player.getLocation(), radius, true);
    }

    public static void setDay(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Да будет ДЕНЬ!");
        player.getWorld().setTime(6000);
    }

    public static void setNight(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Да будет НОЧЬ!");
        player.getWorld().setTime(20000);
    }

    public static void giveIronSet(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Игроку " + player.getName() + " подогнали железный сет брони");
        giveToPlayer(player, Material.IRON_HELMET, 1, 0, ChatColor.DARK_GREEN, "ШЛЯПА");
        giveToPlayer(player, Material.IRON_BOOTS, 1, 0, ChatColor.DARK_GREEN, "ПОДКРАДУЛИ");
        giveToPlayer(player, Material.IRON_CHESTPLATE, 1, 0, ChatColor.DARK_GREEN, "КУРТОЧКА");
        giveToPlayer(player, Material.IRON_LEGGINGS, 1, 0, ChatColor.DARK_GREEN, "ШАРАВАРЫ");
    }

    public static void giveDiamondSword(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Игроку " + player.getName() + " подогнали нож");
        giveToPlayer(player, Material.DIAMOND_SWORD, 1, 0, ChatColor.BLUE, "Нож");
    }

    public static void giveDiamondPick(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Игроку " + player.getName() + " подогнали кирочку");
        giveToPlayer(player, Material.DIAMOND_PICKAXE, 1, 0, ChatColor.BLUE, "Долбилка блоков");

    }

    public static void spawnTamedCat(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Игроку " + player.getName() + " подарили котейку");
        Entity cat = player.getWorld().spawnEntity(player.getLocation(), EntityType.CAT);
        ((Cat) cat).setTamed(true);
        ((Cat) cat).setOwner(player);
        ((Cat) cat).setRemoveWhenFarAway(false);
        cat.setCustomName(ChatColor.DARK_PURPLE + "Хали Кашемири");
    }

    public static void spawnTamedDog(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Игроку " + player.getName() + " подарили догича");
        Entity dog = player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        ((Wolf) dog).setTamed(true);
        ((Wolf) dog).setOwner(player);
        ((Wolf) dog).setRemoveWhenFarAway(false);
        dog.setCustomName(ChatColor.DARK_PURPLE + "Чебурек");
    }

    public static void removeBlocks (Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Игроку " + player.getName() + " удалил под ногами блоки аххахаха");
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(0,1,0)).setType(Material.AIR);
        
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(1,1,0)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(0,1,1)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(1,1,1)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(1,1,-1)).setType(Material.AIR);

        player.getWorld().getBlockAt(player.getLocation().clone().subtract(-1,1,0)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(0,1,-1)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(-1,1,1)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(-1,1,-1)).setType(Material.AIR);
    }

    public static void lightningStrike (Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "На " + player.getName() + " разозлилась тучка");
        int strikeAmount = 1;
        Location loc = player.getLocation();
        World world = player.getWorld();
        while(strikeAmount-- != 0)
            world.strikeLightning(loc);
    }

    public static void deceleration(Player player){
        int duration = 5;
        int amplifier = 100;
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " сломал ногу");
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration * tick, amplifier));
    }

    public static void blindnessAndConfusion(Player player){
        int duration = 10;
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " похавал испорченный данар");
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration * tick, 100));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, duration * tick, 1));
    }

    public static void increaseDamage(Player player){
        int duration = 10;
        int amplifier = 20;
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " сходил в качалку и расскачался жееесть...");
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration * tick, amplifier));
    }

    public static void speedUp(Player player){
        int duration = 5;
        int amplifier = 30;
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " решил заспидранить");
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration * tick, amplifier));
    }

    public static void healthMax(Player player){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " приложил подорожник");
        player.setHealth(20.0);
    }

    public static void levitation(Player player){
        int duration = 10;
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Держите " + player.getName() + " за ноги! Он начал взлетать");
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, duration * tick, 1));
    }

    public static void hunger(Player player){
        int duration = 5;
        int amplifier = 100;
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " начал голодать");
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, duration * tick, amplifier));
    }

    public static void jumpBoost(Player player){
        int duration = 7;
        if(new Random().nextInt(10) % 2 == 0){
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " теперь может допрыгнуть до небес");
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, duration * tick, 10000));
        }
        else{
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " теперь может высоко прыгать");
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, duration * tick, 5));
        }
    }

}
