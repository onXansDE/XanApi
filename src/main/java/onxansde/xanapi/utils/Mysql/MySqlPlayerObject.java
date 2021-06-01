package onxansde.xanapi.utils.Mysql;

import onxansde.xanapi.utils.StringProccessing;
import org.bukkit.command.CommandSender;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    public void sendPlayerInfoMessage(CommandSender sender) {
        sender.sendMessage(StringProccessing.CenterChatMessage("§7§m---------------------------------------------------"));
        sender.sendMessage(StringProccessing.CenterChatMessage("§b§lPlayerinformation"));
        sender.sendMessage(" ");
        sender.sendMessage("   §7Userid: §a" + userid);
        sender.sendMessage("   §7Username: §a" + name);
        sender.sendMessage("   §7UUID: §a" + uuid.toString());
        sender.sendMessage("   §7IP: §a" + ipaddress);
        sender.sendMessage("   §7First Join: §a" +  new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(firstJoinTime));
        sender.sendMessage("   §7Last Join: §a" +  new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(lastJoinTime));
        sender.sendMessage("   §7Data: §a" + data.size() + " Values");
        sender.sendMessage(" ");
        sender.sendMessage(StringProccessing.CenterChatMessage("§7§m---------------------------------------------------"));
    }
}
