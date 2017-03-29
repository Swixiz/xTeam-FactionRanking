package fr.swixiz.facrank.listeners.faction;

import com.massivecraft.factions.event.EventFactionsCreate;
import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.FactionRanking;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Agent_Aqua_ on 18/03/2017.
 */
public class FactionCreateListener implements Listener{

    @EventHandler
    public void create(EventFactionsCreate e){
        new Classement(FactionRanking.getInstance().getSqlConnection()).createAccount(e.getFactionName());
        System.out.println("CERATING SQL ACCOUNT");
    }

}
