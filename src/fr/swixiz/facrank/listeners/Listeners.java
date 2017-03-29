package fr.swixiz.facrank.listeners;

import fr.swixiz.facrank.FactionRanking;
import fr.swixiz.facrank.listeners.bukkit.PlayerDeathListener;
import fr.swixiz.facrank.listeners.bukkit.PlayerJoinListener;
import fr.swixiz.facrank.listeners.bukkit.PlayerQuitListener;
import fr.swixiz.facrank.listeners.faction.*;
import org.bukkit.Bukkit;

/**
 * Created by Agent_Aqua_ on 18/03/2017.
 */
public class Listeners {

    private FactionRanking plugin;

    public Listeners(FactionRanking plugin){
        this.plugin = plugin;
    }

    public void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new FactionCreateListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new FactionDisbandListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new FactionMemberChangeListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new FactionNameChangeListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new FactionPowerChangeListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new FactionClaimListener(), plugin);

        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), plugin);
    }

}
