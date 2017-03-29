package fr.swixiz.facrank.listeners.bukkit;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.MPlayer;
import fr.swixiz.facrank.FactionRanking;
import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.points.PointsChange;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Project: xTeam - FactionRanking
 * Date: 25/03/2017
 * Copyright 2017 - Agent_Aqua_
 */
public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        MPlayer mp = MPlayer.get(e.getEntity());
        Classement classement = FactionRanking.getInstance().getClassement();

        if(mp.getFaction() == null || mp.getFaction().getName().equalsIgnoreCase(Factions.NAME_NONE_DEFAULT))return;

        if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || e.getEntity().getLastDamageCause().getCause()== EntityDamageEvent.DamageCause.PROJECTILE){
            Entity damager = ((EntityDamageByEntityEvent)p.getLastDamageCause()).getDamager();
            if(damager == null)return;
            if(damager instanceof Player){
                Player killer = (Player) damager;
                MPlayer mk = MPlayer.get(killer);
                classement.addValue(mk.getFaction(), "points", PointsChange.KILLED.getPoints());
                classement.addValue(mk.getFaction(), "kills", 1);
                PointsChange.sendMessage(killer, mk.getFaction(), PointsChange.KILLED);

                classement.removeValue(mp.getFaction(), "points", PointsChange.DEATH_BY_PLAYER.getPoints());
                PointsChange.sendMessage(p, mp.getFaction(), PointsChange.DEATH_BY_PLAYER);
            }
        }else{
            classement.removeValue(mp.getFaction(), "points", PointsChange.OTHER_DEATH.getPoints());
            PointsChange.sendMessage(p, mp.getFaction(), PointsChange.OTHER_DEATH);
        }
        classement.addValue(mp.getFaction(), "deaths", 1);
    }

}
