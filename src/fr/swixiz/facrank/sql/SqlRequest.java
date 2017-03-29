package fr.swixiz.facrank.sql;

import fr.swixiz.facrank.FactionRanking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Agent_Aqua_ on 16/03/2017.
 */
public class SqlRequest {

    public enum Request{
        CERATE_FACTION_TABLE("CREATE TABLE `faction` ( `id` INT NOT NULL AUTO_INCREMENT , `factionName` VARCHAR(50) NOT NULL , `points` INT NOT NULL , `kills` INT NOT NULL , `deaths` INT NOT NULL , `surclaim` INT NOT NULL , `ap` INT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");

        String request;

        Request(String request){
            this.request = request;
        }

        public String getRequest(){
            return request;
        }
    }

    private Connection connection;

    public SqlRequest(){

        this.connection = FactionRanking.getInstance().getSqlConnection().getConnection();
        if(connection == null){
            System.out.println("NULL");
        }
    }

    public boolean isTablesExists(String tableName) throws SQLException {
        boolean tExists = false;
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    tExists = true;
                    break;
                }
            }
        }
        return tExists;
    }

    public void createFactionTable() {
        try {
            if (!isTablesExists("faction")) {
                PreparedStatement ps = connection.prepareStatement(Request.CERATE_FACTION_TABLE.getRequest());
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
