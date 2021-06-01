package onxansde.xanapi.utils;

import onxansde.xanapi.XanApi;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ConfigManager {

    File dataFolder;

    public ConfigManager() {
        this.dataFolder = XanApi.instance.getDataFolder();
    }

    public void setupModuleFolder(String module) {
        File folder = new File(dataFolder, module);
        if(!folder.exists()) {
            folder.mkdirs();
            XanApi.instance.logger.log("Creating Module Folder > " + module);
        }
    }

    private File getModuleFolder(String module) {
        return new File(dataFolder,module);
    }

    public String getFileAsString(String module,String name) {
        File file = new File(getModuleFolder(module), name);
        if(file.exists()) {
            try {
                XanApi.instance.logger.log("Reading File as String > " + module+"/"+name);
                return StringProccessing.readFromInputStream(new FileInputStream(file));
            } catch (IOException e) {
                XanApi.instance.logger.log("Failed reading File as String > " + module+"/"+name);
                e.printStackTrace();
            }
        }
        XanApi.instance.logger.log("File not Found > " + module+"/"+name);
        return null;
    }

    public FileConfiguration getFileAsConfig(String module, String name) {
        File file = new File(getModuleFolder(module), name);
        if(file.exists()) {
            XanApi.instance.logger.log("Reading File as Config > " + module+"/"+name);
            return YamlConfiguration.loadConfiguration(file);
        }

        XanApi.instance.logger.log("Config not Found > " + module+"/"+name);
        return null;
    }

    public void saveFile(String module, String name, InputStream stream) {
        File target = new File(getModuleFolder(module), name);

        try {
            if(!target.exists()){
                target.createNewFile();
                XanApi.instance.logger.log("File created > " + module+"/"+name);
            }
            OutputStream outputStream = new FileOutputStream(target);
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            outputStream.write(buffer);

        } catch (IOException e) {
            XanApi.instance.logger.log("Faild saving File > " + module+"/"+name);
            e.printStackTrace();
        }
    }

    public void saveConfig(String module, String name, FileConfiguration config) {
        File target = new File(getModuleFolder(module), name);

        try {
            if(!target.exists()){
                target.createNewFile();
                XanApi.instance.logger.log("Config created > " + module+"/"+name);
            }
            config.save(target);

        } catch (IOException e) {
            XanApi.instance.logger.log("Faild saving Config > " + module+"/"+name);
            e.printStackTrace();
        }
    }

    public void saveDefaultFile(String module, String name, InputStream stream) {
        File target = new File(getModuleFolder(module), name);
        if(target.exists()) return;
        try {
            XanApi.instance.logger.log("Saving default File > " + module+"/"+name);
            OutputStream outputStream = new FileOutputStream(target);
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            outputStream.write(buffer);
            XanApi.instance.logger.log("Saved default File > " + module+"/"+name);

        } catch (IOException e) {
            XanApi.instance.logger.log("Failed saving default File > " + module+"/"+name);
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig(String module, String name, FileConfiguration config) {
        File target = new File(getModuleFolder(module), name);

        if(target.exists()) return;
        try {
            XanApi.instance.logger.log("Saving default Config > " + module+"/"+name);
            config.save(target);
            XanApi.instance.logger.log("Saved default Config > " + module+"/"+name);

        } catch (IOException e) {
            XanApi.instance.logger.log("Failed saving default Config > " + module+"/"+name);
            e.printStackTrace();
        }
    }
}
