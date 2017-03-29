package fr.swixiz.facrank.points;

import com.massivecraft.factions.entity.Faction;
import fr.swixiz.facrank.points.PointsChange;
import fr.swixiz.facrank.scoreboard.ScoreboardManager;
import fr.swixiz.facrank.sql.SqlConnection;
import fr.swixiz.facrank.utils.MapComparator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Agent_Aqua_ on 17/03/2017.
 */
public class Classement {

    private SqlConnection sql;
    private Connection connection;

    public Classement(SqlConnection sql){
        this.sql = sql;
        this.connection = sql.getConnection();
    }

    public void createAccount(String factionName){
        try {
            PreparedStatement sts = connection.prepareStatement("INSERT INTO faction(factionName, points, kills, deaths, surclaim, ap) VALUES (?, 0, 0, 0, 0, 0);");
            sts.setString(1, factionName);
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String factionName){
        try {
            PreparedStatement sts = connection.prepareStatement("DELETE FROM faction WHERE factionName = '" + factionName +"';");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeAccountName(String oldName, String newName){
        try {
            PreparedStatement sts = connection.prepareStatement("UPDATE faction SET factionName = '"+newName+"' WHERE factionName = '"+oldName+"';");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addValue(Faction faction, String obj, int value){
        try {
            PreparedStatement sts = connection.prepareStatement("UPDATE faction SET " + obj + " = " + obj +" + "+value+" WHERE factionName = '" + faction.getName()+"';");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeValue(Faction faction, String obj,  int value){
        try {
            PreparedStatement sts = connection.prepareStatement("UPDATE faction SET " + obj + " = " + obj +" - "+value+" WHERE factionName = '" + faction.getName()+"';");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getFactionsPoints(){
        Map<String, Integer> map = new HashMap<>();
        try {
            PreparedStatement sts = connection.prepareStatement("SELECT factionName,points FROM faction;");
            ResultSet rs = sts.executeQuery();
            while (rs.next()){
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, Integer> sortFactions(){
        MapComparator mc = new MapComparator(getFactionsPoints());
        TreeMap sortedMap = new TreeMap(mc);
        for(Map.Entry entry : getFactionsPoints().entrySet()){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public void setScoreboard(ScoreboardManager sm){
        sm.clearLines();
        sm.setLine(0, "§0 ");
        if(sortFactions().size() == 0)
            sm.setLine(-1, "§7Aucune faction n'est enregistrée");
        else{
            int max = 0;
            if(sortFactions().size() < 10) max = sortFactions().size();
            else max = 10;

            int index = 1;
            for(Map.Entry entry : sortFactions().entrySet()){
                sm.setLine(-index, "§b"+index+": " + entry.getKey()+ " (§e"+entry.getValue() +" pts§b)");
                if(index >= max)
                    break;
                else index++;
            }
        }
    }

}
