package fr.swixiz.facrank.listeners.bukkit;

import fr.swixiz.facrank.FactionRanking;
import fr.swixiz.facrank.scoreboard.ScoreboardManager;
import fr.swixiz.facrank.scoreboard.ScoreboardTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

/**
 * Project: xTeam - FactionRanking
 * Date: 25/03/2017
 * Copyright 2017 - Agent_Aqua_
 */
public class PlayerJoinListener implements Listener{

    @EventHandler
    public void join(PlayerJoinEvent e){
        Map<String, Integer> factions = FactionRanking.getInstance().getClassement().sortFactions();

        ScoreboardManager smo = new ScoreboardManager(e.getPlayer(), "§f§lxTeam", ScoreboardManager.otherBoard).create();
        smo.setLine(2, "§6§m-|---------------------------|-");
        smo.setLine(0, "§0 ");
        smo.setLine(-1, "§7Bienvenue sur §fxTeam §7"+e.getPlayer().getName());
        smo.setLine(-2, "§1 ");
        smo.setLine(-3, "§e§lPage Facebook:");
        smo.setLine(-4, "§7facebook.com/xTeam");

        ScoreboardManager sm = new ScoreboardManager(e.getPlayer(), "§c§lClassement", ScoreboardManager.facBoard).create();
        sm.setLine(0, "§0 ");

        FactionRanking.getInstance().getClassement().setScoreboard(sm);
        sm.send();
        new ScoreboardTask(e.getPlayer());
    }

}
