package fr.swixiz.facrank.listeners.faction;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsPowerChange;
import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.FactionRanking;
import fr.swixiz.facrank.points.PointsChange;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by Agent_Aqua_ on 19/03/2017.
 */
public class FactionPowerChangeListener implements Listener{

    @EventHandler
    public void powerChange(EventFactionsPowerChange e){
        MPlayer mp = e.getMPlayer();
        Classement classement = new Classement(FactionRanking.getInstance().getSqlConnection());
        Double power = e.getNewPower();
        long intPart = (long) power.doubleValue();

        if(mp.getFaction() == null || mp.getFaction().getName().equalsIgnoreCase(Factions.NAME_NONE_DEFAULT)){return;}

        if(e.getReason() == EventFactionsPowerChange.PowerChangeReason.DEATH){
            classement.removeValue(mp.getFaction(), "points", PointsChange.POWERLOOSE.getPoints());
            PointsChange.sendMessage(mp.getPlayer(), mp.getFaction(), PointsChange.POWERLOOSE);
        }

        if(e.getReason() == EventFactionsPowerChange.PowerChangeReason.TIME){
            Double result = power - intPart;
            BigDecimal decimal = new BigDecimal(result.toString()).round(new MathContext(1, RoundingMode.HALF_UP));
            if(decimal.floatValue() != 1.0f){
                System.out.println(decimal.floatValue());
                return;
            }
            classement.addValue(mp.getFaction(), "points", PointsChange.POWERUP.getPoints());
            PointsChange.sendMessage(mp.getPlayer(), mp.getFaction(), PointsChange.POWERUP);
        }

    }

}
