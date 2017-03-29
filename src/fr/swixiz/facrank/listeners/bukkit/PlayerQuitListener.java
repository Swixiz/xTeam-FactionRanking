package fr.swixiz.facrank.listeners.bukkit;

import fr.swixiz.facrank.scoreboard.ScoreboardManager;
import fr.swixiz.facrank.scoreboard.ScoreboardTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Project: xTeam - FactionRanking
 * Date: 26/03/2017
 * Copyright 2017 - Swixiz
 */
public class PlayerQuitListener implements Listener{

    @EventHandler
    public void quit(PlayerQuitEvent e){
        ScoreboardManager.facBoard.get(e.getPlayer()).destroy();
        ScoreboardManager.otherBoard.get(e.getPlayer()).destroy();
        ScoreboardTask.tasks.get(e.getPlayer()).cancel();
        ScoreboardTask.tasks.remove(e.getPlayer());
    }

}
