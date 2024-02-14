package online.flowerinsnow.cloudannotationsdemo.bukkit;

import online.flowerinsnow.cloudannotationsdemo.bukkit.manager.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudAnnotationsDemoBukkit extends JavaPlugin {
    @Override
    public void onEnable() {
        CommandManager commandManager = new CommandManager(this);
        commandManager.init();
    }
}
