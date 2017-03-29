package fr.swixiz.facrank.commands;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;

import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.FactionRanking;

import fr.swixiz.facrank.points.PointsChange;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Agent_Aqua_ on 18/03/2017.
 */
public class PointsCommand implements CommandExecutor{

    public PointsCommand(){
        FactionRanking.getInstance().getCommand("points").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(!(p.isOp() || p.hasPermission("xteam.points"))){
                p.sendMessage("§cVous n'avez pas accès à cette commande !");
                return false;
            }
        }

        if(args.length < 3){
            s.sendMessage("§c/points <add|remove> <faction> <points>");
            return false;
        }

        Faction f = FactionColl.get().getByName(args[1]);
        int points = Integer.valueOf(args[2]);

        if(f == null){
            s.sendMessage("§cFaction introuvable");
            return false;
        }

        Classement classement = new Classement(FactionRanking.getInstance().getSqlConnection());

        if(args[0].equalsIgnoreCase("add")){
            classement.addValue(f, "points", PointsChange.ADDED_BY_GIVE.getPoints());
            s.sendMessage("POINTS ADDED");
        }

        if(args[0].equalsIgnoreCase("remove")){
            classement.removeValue(f, "points", PointsChange.REMOVED_BY_GIVE.getPoints());
            s.sendMessage("POINTS REMOVED");
        }

        return false;
    }
}
