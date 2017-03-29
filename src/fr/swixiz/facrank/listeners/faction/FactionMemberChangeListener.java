package fr.swixiz.facrank.listeners.faction;

import com.massivecraft.factions.event.EventFactionsMembershipChange;
import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.FactionRanking;
import fr.swixiz.facrank.points.PointsChange;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Agent_Aqua_ on 18/03/2017.
 */
public class FactionMemberChangeListener implements Listener{

    @EventHandler
    public void changeFac(EventFactionsMembershipChange e){
        Classement classement = FactionRanking.getInstance().getClassement();
        if(e.getReason() == EventFactionsMembershipChange.MembershipChangeReason.LEAVE || e.getReason() == EventFactionsMembershipChange.MembershipChangeReason.KICK){
            classement.removeValue(e.getMPlayer().getFaction(), "points", PointsChange.PLAYER_LEAVE.getPoints());
            PointsChange.sendMessage(e.getMPlayer().getPlayer(), e.getMPlayer().getFaction(), PointsChange.PLAYER_LEAVE);
        }

        if(e.getReason() == EventFactionsMembershipChange.MembershipChangeReason.JOIN){
            classement.addValue(e.getNewFaction(), "points", PointsChange.PLAYER_JOIN.getPoints());
            PointsChange.sendMessage(e.getMPlayer().getPlayer(), e.getNewFaction(), PointsChange.PLAYER_JOIN);
        }
    }

}
