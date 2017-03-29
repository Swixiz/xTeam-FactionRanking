package fr.swixiz.facrank.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Agent_Aqua_ on 15/03/2017.
 */
public class SqlConnection {

    private String prefix = "jdbc:mysql://";
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String database;
    private Connection connection;

    public SqlConnection(String host, Integer port, String username, String password, String database) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
    }
    public void connect() {
        try {
            if (!isConnected()) {
                connection = DriverManager.getConnection(this.prefix + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public boolean isConnected() {
        try {
            return !(connection == null || connection.isClosed());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public void disconnect() {
        try {
            if (isConnected()) {
                connection.close();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }

}
