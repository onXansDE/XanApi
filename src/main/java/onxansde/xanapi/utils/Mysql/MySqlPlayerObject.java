package onxansde.xanapi.utils.Mysql;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

public class MySqlPlayerObject {

    public int userid;

    public UUID uuid;

    public String name, ipaddress;

    public Timestamp firstJoinTime, lastJoinTime;

    public HashMap<String, String> data = new HashMap<>();

    public MySqlPlayerObject(int userid, UUID uuid, String name, String ipaddress, Timestamp firstJoinTime, Timestamp lastJoinTime) {
        this.userid = userid;
        this.uuid = uuid;
        this.name = name;
        this.ipaddress = ipaddress;
        this.firstJoinTime = firstJoinTime;
        this.lastJoinTime = lastJoinTime;
    }

    public MySqlPlayerObject(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
