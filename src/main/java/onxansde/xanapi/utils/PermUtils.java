package onxansde.xanapi.utils;

import net.luckperms.api.model.group.Group;
import onxansde.xanapi.XanApi;

public class PermUtils {

    public static Group getGroup(String name) {
        return XanApi.instance.perms.getGroupManager().getGroup(name);
    }
}
