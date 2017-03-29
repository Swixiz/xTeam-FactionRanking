package fr.swixiz.facrank.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: xTeam - FactionRanking
 * Date: 25/03/2017
 * Copyright 2017 - Agent_Aqua_
 */
public class ScoreboardManager {

    public static Map<Player, ScoreboardManager> facBoard = new HashMap<>();
    public static Map<Player, ScoreboardManager> otherBoard = new HashMap<>();

    private Player player;
    private String objectiveName;
    private Map<Player, ScoreboardManager> board;

    private Scoreboard sb;
    private Objective objective;

    public ScoreboardManager(Player player, String objectiveName, Map<Player, ScoreboardManager> board){
        this.player = player;
        this.objectiveName = objectiveName;
        this.board = board;
    }

    public ScoreboardManager create(){
        if(board.containsKey(player))
            return board.get(player);

        sb = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = sb.registerNewObjective("obj", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(objectiveName);

        board.put(player, this);
        return this;
    }

    public void destroy(){
        sb.clearSlot(DisplaySlot.SIDEBAR);
        board.remove(player);
    }

    public void setLine(int slot, String line){
        Score score = objective.getScore(getOrCreateNewTeam(line));
        score.setScore(slot);
    }

    public void clearLines(){
        sb.resetScores(objectiveName);
    }

    public OfflinePlayer getOrCreateNewTeam(String name){
        Team team = null;
        OfflinePlayer player = null;
        if(name.length() < 16){
            player = Bukkit.getOfflinePlayer(name);
        }else{
            if(name.length() < 32){
                String[] chars = new String[]{name.substring(0, 16), name.substring(16)};
                if(sb.getTeams().contains(sb.getTeam(chars[0])))
                    team =  sb.getTeam(chars[0]);
                else {
                    team = sb.registerNewTeam(chars[0]);
                }
                team.setSuffix(chars[1]);
                player = Bukkit.getOfflinePlayer(chars[0]);
                team.addPlayer(player);
            }else{
                String[] chars = new String[]{name.substring(0, 16), name.substring(16, 32), name.substring(32)};
                if(sb.getTeams().contains(sb.getTeam(chars[1])))
                    team =  sb.getTeam(chars[1]);
                else {
                    team = sb.registerNewTeam(chars[1]);
                }
                team.setPrefix(chars[0]);
                team.setSuffix(chars[2]);
                player = Bukkit.getOfflinePlayer(chars[1]);
                team.addPlayer(player);
            }
        }
        return player;
    }

    public void send(){
        player.setScoreboard(sb);
    }
}
