package onxansde.xanapi.utils.Mysql;

import onxansde.xanapi.XanApi;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
    private Connection con;
    private String host;
    private int port;
    private String database;
    private String user;
    private String password;

    public MySql(String host, int port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;

        connect();
    }

    public void connect() {
        Bukkit.getScheduler().runTaskAsynchronously(XanApi.getInstance(), () -> {
            XanApi.getInstance().logger.log("Connecting to database at "+host+ "...");
            try {
                con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/" + database + "?autoReconnect=true", user, password);
                if(isConnected()) {
                    XanApi.getInstance().logger.log("Connected to database!");
                }
            } catch (SQLException throwables) {
                XanApi.getInstance().logger.logerror("Mysql: Failed to connect!");
                throwables.printStackTrace();
            }
        });
    }

    public void disconnect() {
        try {
            this.con.close();
            XanApi.getInstance().logger.log("Disconected Mysql");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isConnected() {
        try {
            return !con.isClosed();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Connection getConnection() {
        return this.con;
    }
}
