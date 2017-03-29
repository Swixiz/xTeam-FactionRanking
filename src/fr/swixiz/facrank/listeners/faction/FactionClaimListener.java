package fr.swixiz.facrank.listeners.faction;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.event.EventFactionsChunkChangeType;
import com.massivecraft.factions.event.EventFactionsChunksChange;
import com.massivecraft.massivecore.ps.PS;
import fr.swixiz.facrank.FactionRanking;
import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.points.PointsChange;
import org.bukkit.Chunk;
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
        Faction newFaction = e.getNewFaction();
        Classement classement = FactionRanking.getInstance().getClassement();
        for(PS claim : e.getChunks()){
            Faction old = e.getOldChunkFaction().get(claim);
            if(e.getChunkType().get(claim) == EventFactionsChunkChangeType.CONQUER){

                classement.addValue(newFaction, "points", PointsChange.SURCLAIM.getPoints());
                classement.addValue(newFaction, "surclaim", 1);
                PointsChange.sendMessage(e.getMSender().getPlayer(), newFaction, PointsChange.SURCLAIM);

                classement.removeValue(old, "points", PointsChange.SURCLAIM_BY_OTHER.getPoints());
                classement.removeValue(old, "surclaim", 1);
                PointsChange.sendMessage(e.getMSender().getPlayer(), old, PointsChange.SURCLAIM_BY_OTHER);
            }
            if(e.getChunkType().get(claim) == EventFactionsChunkChangeType.BUY || e.getChunkType().get(claim) == EventFactionsChunkChangeType.CONQUER){
                if(isNearFromWarzone(claim)){
                    classement.addValue(newFaction, "points", PointsChange.AP.getPoints());
                    classement.addValue(newFaction, "ap", 1);
                    PointsChange.sendMessage(e.getMSender().getPlayer(), newFaction, PointsChange.AP);
                    if(e.getChunkType().get(claim) == EventFactionsChunkChangeType.CONQUER){

                    }
                }
            }
        }
    }

    public boolean isNearFromWarzone(PS claim){
        Chunk c = claim.asBukkitChunk();
        for(int x = -1; x <= 1; x++){
            for(int z = -1; z <= 1; z++){
                Chunk near = c.getWorld().getChunkAt(c.getX()+x, c.getZ()+z);
                System.out.println("x: " + near.getX() + " z: " + near.getZ());
                if(BoardColl.get().getFactionAt(PS.valueOf(near)).getId().equalsIgnoreCase("warzone"))
                    return true;
            }
        }
        return false;
    }

}
