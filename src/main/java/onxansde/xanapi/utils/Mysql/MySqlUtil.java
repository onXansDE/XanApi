package onxansde.xanapi.utils.Mysql;

import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;
import onxansde.xanapi.utils.StringProccessing;
import org.bukkit.command.CommandSender;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.UUID;

public class MySqlUtil {

    public int playeramount = 0;

    public double databasesize = 0;
    public int tablecount = 0;
    public String version = "0";

    public MySqlPlayerObject getMySqlPlayerByUUID(String uuid) {
        try {
            PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("SELECT * FROM `xanapi_players` WHERE `uuid` = ?");
            statement.setString(1, uuid);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                MySqlPlayerObject object = new MySqlPlayerObject(set.getInt(1), UUID.fromString(uuid), set.getString(3),set.getString(4), set.getTimestamp(5),set.getTimestamp(6));
                return object;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public MySqlPlayerObject getMySqlPlayerByName(String name) {
        try {
            PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("SELECT * FROM `xanapi_players` WHERE `name` = ?");
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                MySqlPlayerObject object = new MySqlPlayerObject(set.getInt(1), UUID.fromString(set.getString(2)), name,set.getString(4), set.getTimestamp(5),set.getTimestamp(6));
                return object;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public MySqlPlayerObject getMySqlPlayerByUid(int uid) {
        try {
            PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("SELECT * FROM `xanapi_players` WHERE `uid` = ?");
            statement.setInt(1, uid);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                MySqlPlayerObject object = new MySqlPlayerObject(uid, UUID.fromString(set.getString(2)), set.getString(3),set.getString(4), set.getTimestamp(5),set.getTimestamp(6));
                return object;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void saveMysqlPlayer(AdvancedPlayer object) {
        try {
            PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("INSERT INTO `xanapi_players` (`uuid`, `name`, `ipaddress`) VALUES (?, ?, ?)");
            statement.setString(1, object.getUuid().toString());
            statement.setString(2, object.getName());
            statement.setString(3, object.getIpAddress());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateMysqlPlayer(MySqlPlayerObject object) {
        try {
            PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("UPDATE `xanapi_players` SET `name` = ?, `ipaddress` = ?, `lastJoinTime` = ? WHERE `uuid` = ?");
            statement.setString(1, object.name);
            statement.setString(2, object.ipaddress);
            statement.setTimestamp(3, object.lastJoinTime);
            statement.setString(4,object.uuid.toString());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateInfos() {
        try {
            {
                PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("SELECT COUNT(*) FROM `xanapi_players`");
                ResultSet set = statement.executeQuery();
                if (set.next()) {
                    playeramount = set.getInt(1);
                }
            }
            {
                PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("SELECT `table_schema` \"DB Name\",\n" +
                        "        ROUND(SUM(data_length + index_length) / 1024 / 1024, 1) \"DB Size in MB\" \n" +
                        "FROM `information_schema`.`tables` \n" +
                        "GROUP BY `table_schema`; ");
                ResultSet set = statement.executeQuery();
                double size = 0d;
                while (set.next()) {
                    size += set.getBigDecimal(2).doubleValue();
                }
                databasesize = size;
            }
            {
                PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("SELECT COUNT(*) AS TOTALNUMBEROFTABLES FROM INFORMATION_SCHEMA.TABLES;");
                ResultSet set = statement.executeQuery();
                if (set.next()) {
                    tablecount = set.getInt(1);
                }
            }
            {
                PreparedStatement statement = XanApi.getInstance().mysql.getConnection().prepareStatement("SHOW VARIABLES LIKE 'version'");
                ResultSet set = statement.executeQuery();
                if (set.next()) {
                    version = set.getString(2);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void sendMySqlStatus(CommandSender sender) {
        DecimalFormat df = new DecimalFormat("###.#");
        sender.sendMessage(StringProccessing.CenterChatMessage("§7§m---------------------------------------------------"));
        sender.sendMessage(StringProccessing.CenterChatMessage("§b§lDatabaseinformation"));
        sender.sendMessage(" ");
        sender.sendMessage("   §7Version: §a" + version);
        sender.sendMessage("   §7Total Size: §a" + df.format(databasesize) +" MB");
        sender.sendMessage("   §7Tablecount: §a"+tablecount);
        sender.sendMessage(" ");
        sender.sendMessage("   §7Playercount: §a"+playeramount);
        sender.sendMessage(" ");
        sender.sendMessage(StringProccessing.CenterChatMessage("§7§m---------------------------------------------------"));
    }
}
