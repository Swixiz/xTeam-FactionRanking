package fr.swixiz.facrank;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by Agent_Aqua_ on 16/03/2017.
 */
public class Configuration {

    FactionRanking plugin;
    FileConfiguration conf;

    public Configuration(FactionRanking plugin){
        this.plugin = plugin;
        this.conf = plugin.getConfig();
    }

    public void init(){

        if(!conf.isSet("messages")){
            conf.set("messages.prefix", "&e[&9FactionRanking&e] ");
            conf.set("messages.win_points", "&a&o<joueur> a fait gagne <pts> points à votre faction. Raison: <raison>");
            conf.set("messages.loose_points", "&c&o<joueur> a fait perdre <pts> points à votre faction. Raison: <raison>");
        }

        if(!conf.isSet("messages.reason")){
            conf.set("messages.reason.added_give", "Ajout par un membre du staff");
            conf.set("messages.reason.removed_give", "Prelevement par un membre du staff");
            conf.set("messages.reason.kill", "Kill");
            conf.set("messages.reason.death_by_player", "Mort par un joueur");
            conf.set("messages.reason.death_other", "Mort");
            conf.set("messages.reason.surclaim", "Surclaim d'une zone");
            conf.set("messages.reason.surclaim_by_other", "Surclaim par une autre faction");
            conf.set("messages.reason.ap", "Claim d'un ap");
            conf.set("messages.reason.ap_unclaim_surclaim", "Unclaim/Surclaim d'un ap");
            conf.set("messages.reason.player_join", "Rejoint la faction");
            conf.set("messages.reason.player_leave", "Quitte la faction");
            conf.set("messages.reason.power_up", "Augmentation du power");
            conf.set("messages.reason.power_loose", "Perte de power");
            conf.set("messages.reason.boss", "Boss");
        }

        if(!conf.isSet("sql")){
            conf.set("sql.host", "localhost");
            conf.set("sql.port", 3306);
            conf.set("sql.database", "faction-ranking");
            conf.set("sql.username", "root");
            conf.set("sql.password", "123456789");
        }

        if(!conf.isSet("points")){
            conf.set("points.kill", 10);
            conf.set("points.ap", 10);
            conf.set("points.surclaim", 10);
            conf.set("points.member_join", 10);
            conf.set("points.powerup", 10);
            conf.set("points.boss", 10);

            conf.set("points.death_by_player", 10);
            conf.set("points.death_other", 10);
            conf.set("points.powerlost", 10);
            conf.set("points.surclaim_by_other", 10);
            conf.set("points.member_leave", 10);
            conf.set("points.ap_unclaim_surclaim", 10);
        }

        plugin.saveConfig();
    }

}
