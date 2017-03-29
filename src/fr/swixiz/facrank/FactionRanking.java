package fr.swixiz.facrank;

import fr.swixiz.facrank.commands.ClassementCommand;
import fr.swixiz.facrank.commands.PointsCommand;
import fr.swixiz.facrank.listeners.Listeners;
import fr.swixiz.facrank.points.Classement;
import fr.swixiz.facrank.sql.SqlConnection;
import fr.swixiz.facrank.sql.SqlRequest;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Agent_Aqua_ on 15/03/2017.
 */
public class FactionRanking extends JavaPlugin{

    private static FactionRanking instance;
    private ConsoleCommandSender console;
    private Configuration configuration;
    private SqlConnection connection;

    @Override
    public void onLoad() {
        instance = this;
        console = getServer().getConsoleSender();
        configuration = new Configuration(this);
    }

    @Override
    public void onEnable() {
        console.sendMessage("§b****************************************");
        console.sendMessage("§3xTeam FactionRanking by Swixiz - 1.0");
        console.sendMessage("§b****************************************");

        configuration.init();
        connection = new SqlConnection(getConfig().getString("sql.host"), getConfig().getInt("sql.port"), getConfig().getString("sql.username"),
                getConfig().getString("sql.password"), getConfig().getString("sql.database"));
        connection.connect();
        new BukkitRunnable() {

            @Override
            public void run() {
                if(!connection.isConnected()){
                    connection.connect();
                }
            }
        }.runTaskTimer(this, 0L, 20L*10);

        new SqlRequest().createFactionTable();

        new PointsCommand();
        new ClassementCommand();
        new Listeners(this).registerEvents();

        System.out.println(new Classement(getSqlConnection()).getFactionsPoints());
        System.out.println(new Classement(getSqlConnection()).sortFactions());
    }

    public static FactionRanking getInstance(){
        return instance;
    }

    public SqlConnection getSqlConnection(){
        return connection;
    }
    public Classement getClassement(){return new Classement(getSqlConnection());}
}
