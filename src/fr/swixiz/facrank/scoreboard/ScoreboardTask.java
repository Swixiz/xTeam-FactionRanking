package fr.swixiz.facrank.scoreboard;

import fr.swixiz.facrank.FactionRanking;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: xTeam - FactionRanking
 * Date: 26/03/2017
 * Copyright 2017 - Agent_Aqua_
 */
public class ScoreboardTask extends BukkitRunnable{

    public static Map<Player, ScoreboardTask> tasks = new HashMap<>();

    private Player player;
    private int timer = 10;
    private boolean sb = false;

    public ScoreboardTask(Player player){
        this.player = player;
        tasks.put(player, this);
        runTaskTimer(FactionRanking.getInstance(), 0L, 20L);
    }

    @Override
    public void run() {
        if(timer == 0){
            if(!sb){
                ScoreboardManager.otherBoard.get(player).send();
                sb = true;
            }else{
                ScoreboardManager sm = ScoreboardManager.facBoard.get(player);
                sm.destroy();
                sm.create();
                FactionRanking.getInstance().getClassement().setScoreboard(sm);
                sm.send();
                sb = false;
            }
            timer = 10;
        }
        timer--;
    }
}
