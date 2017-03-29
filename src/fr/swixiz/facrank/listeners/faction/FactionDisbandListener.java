package fr.swixiz.facrank.listeners.faction;

import com.massivecraft.factions.event.EventFactionsDisband;
import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.FactionRanking;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Agent_Aqua_ on 18/03/2017.
 */
public class FactionDisbandListener implements Listener{

    @EventHandler
    public void disband(EventFactionsDisband e){
        new Classement(FactionRanking.getInstance().getSqlConnection()).deleteAccount(e.getFaction().getName());
        System.out.println("DELETED SQL ACCOUNT");
    }

}
