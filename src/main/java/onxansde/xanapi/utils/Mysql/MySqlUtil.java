package onxansde.xanapi.utils.Mysql;

import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySqlUtil {

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
                MySqlPlayerObject object = new MySqlPlayerObject(uid, UUID.fromString(set.getString(2)), set.getString(uid),set.getString(4), set.getTimestamp(5),set.getTimestamp(6));
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
            statement.setString(1, object.uuid.toString());
            statement.setString(2, object.ipaddress);
            statement.setTimestamp(3, object.lastJoinTime);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
