package fr.swixiz.facrank.listeners.faction;

import com.massivecraft.factions.event.EventFactionsNameChange;
import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.FactionRanking;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Agent_Aqua_ on 18/03/2017.
 */
public class FactionNameChangeListener implements Listener{

    @EventHandler
    public void nameChange(EventFactionsNameChange e){
        new Classement(FactionRanking.getInstance().getSqlConnection()).changeAccountName(e.getFaction().getName(), e.getNewName());
        System.out.println("CHANGE SQL NAME ACCOUNT");
    }

}
