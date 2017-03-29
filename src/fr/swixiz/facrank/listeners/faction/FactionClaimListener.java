package fr.swixiz.facrank.listeners.faction;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.event.EventFactionsChunkChangeType;
import com.massivecraft.factions.event.EventFactionsChunksChange;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Project: xTeam - FactionRanking
 * Date: 27/03/2017
 * Copyright 2017 - Swixiz
 */
public class FactionClaimListener implements Listener{

    //SURCLAIM = CONQUER

    @EventHandler
    public void claim(EventFactionsChunksChange e){
        for(PS claim : e.getChunks()){
            if(e.getChunkType().get(claim) == EventFactionsChunkChangeType.CONQUER){
                Faction faction = e.getNewFaction();
                Faction old = e.getOldChunkFaction().get(claim);
            }
        }
        /*
        for(PS claim : e.getOldFactionChunks().get(e.getMSender().getFaction())){

        }
         */
    }

}
