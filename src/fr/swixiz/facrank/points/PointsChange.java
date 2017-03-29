package fr.swixiz.facrank.points;

import com.massivecraft.factions.entity.Faction;
import fr.swixiz.facrank.FactionRanking;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Agent_Aqua_ on 19/03/2017.
 */
public enum PointsChange {

    ADDED_BY_GIVE("messages.reason.added_give", "", Type.ADD),
    REMOVED_BY_GIVE("messages.reason.removed_give", "", Type.REMOVE),
    KILLED("messages.reason.kill", "points.kill", Type.ADD),
    DEATH_BY_PLAYER("messages.reason.death_by_player", "points.death_by_player", Type.REMOVE),
    OTHER_DEATH("messages.reason.death_other", "points.death_other", Type.REMOVE),
    SURCLAIM("messages.reason.surclaim", "points.surclaim", Type.ADD),
    SURCLAIM_BY_OTHER("messages.reason.surclaim_by_other", "points.surclaim_by_other", Type.REMOVE),
    AP("messages.reason.ap", "points.ap", Type.ADD),
    AP_SURCLAIM_BY_OTHER("messages.reason.ap_unclaim_surclaim", "points.ap_unclaim_surclaim", Type.REMOVE),
    PLAYER_JOIN("messages.reason.player_join", "points.member_join", Type.ADD),
    PLAYER_LEAVE("messages.reason.player_leave", "points.member_leave", Type.REMOVE),
    POWERUP("messages.reason.power_up", "points.powerup", Type.ADD),
    POWERLOOSE("messages.reason.power_loose", "points.powerlost", Type.REMOVE),
    BOSS("messages.reason.boss", "points.boss", Type.ADD);

    String reasonPath, pointsPath;
    Type pointsType;
    private static FactionRanking plugin = FactionRanking.getInstance();

    PointsChange(String reasonPath, String pointsPath, Type pointsType) {
        this.reasonPath = reasonPath;
        this.pointsPath = pointsPath;
        this.pointsType = pointsType;
    }

    public enum Type{
        ADD, REMOVE;
    }

    public String getReason() {
        return plugin.getConfig().getString(reasonPath);
    }

    public int getPoints() {
        return plugin.getConfig().getInt(pointsPath);
    }

    public Type getPointsType(){ return pointsType;}

    public static void sendMessage(Player player, Faction faction, PointsChange pointsChange){
        String path = null;
        if(pointsChange.getPointsType() == Type.ADD){
            path = "messages.win_points";
        }else{
            path = "messages.loose_points";
        }

        String message = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.prefix") +
                plugin.getConfig().getString(path)).replace("<joueur>", player.getName())
                .replace("<pts>", String.valueOf(pointsChange.getPoints())).replace("<raison>", pointsChange.getReason());

        for(Player pls : faction.getOnlinePlayers()){
            pls.sendMessage(message);
        }

    }

}
