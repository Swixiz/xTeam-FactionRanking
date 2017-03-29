package fr.swixiz.facrank.commands;

import fr.swixiz.facrank.FactionRanking;
import fr.swixiz.facrank.points.Classement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Project: xTeam - FactionRanking
 * Date: 25/03/2017
 * Copyright 2017 - Agent_Aqua_
 */
public class ClassementCommand implements CommandExecutor{

    public ClassementCommand(){
        FactionRanking.getInstance().getCommand("classement").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(args.length == 0){
            s.sendMessage("§c/classement help");
            return false;
        }

        if(args[0].equalsIgnoreCase("help")){
            s.sendMessage("§3---------------------[ §bClassement §3]---------------------");
            s.sendMessage("§b/classement show <faction> §7Affiche la place de la faction dans le classement");
            s.sendMessage("§b/classement history <joueur> §7Afficher l'historique des points du joueur");
            if(s.isOp()){
                s.sendMessage("§b/classement reset §7Réinitialise le classement");
            }
            s.sendMessage("§ePlugin réalisé par Swixiz, développeur chez Wispe.eu");
        }

        if(args[0].equalsIgnoreCase("show")){
            if(args.length < 2){
                s.sendMessage("§c/classement show <faction>");
                return false;
            }

            String fac = args[1];
            Map<String, Integer> classement = new Classement(FactionRanking.getInstance().getSqlConnection()).sortFactions();

            for(Map.Entry entry : classement.entrySet()){
                System.out.println(entry.getKey() + " " + entry.getValue());
            }


            int index = 1;

            for(Map.Entry entry : classement.entrySet()){
                if(!entry.getKey().equals(fac))
                    index++;
                else {
                    s.sendMessage("§3---------------------[ §bClassement §3]---------------------");
                    s.sendMessage("§bLa faction §e" + fac + "§b est à la place §e" + index + " §b du classement avec §e" + entry.getValue()+ "§b points");
                    break;
                }
            }
        }

        return false;
    }
}
