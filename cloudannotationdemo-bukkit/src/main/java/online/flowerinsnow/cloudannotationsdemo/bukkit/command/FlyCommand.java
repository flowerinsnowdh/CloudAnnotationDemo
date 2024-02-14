package online.flowerinsnow.cloudannotationsdemo.bukkit.command;

import online.flowerinsnow.cloudannotationsdemo.bukkit.util.TextUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class FlyCommand {
    @Command(value = "fly", requiredSender = Player.class)
    @Permission("cloudannotationsdemo.fly.self")
    @CommandDescription("Toggle flight for yourself")
    public void flySelf(Player sender) {
        if (sender.getAllowFlight()) {
            sender.setAllowFlight(false);
            sender.sendMessage(TextUtils.parseColour("&7[&b!&7]&bFly &cdisabled"));
        } else {
            sender.setAllowFlight(true);
            sender.sendMessage(TextUtils.parseColour("&7[&b!&7]&bFly &aenabled"));
        }
    }

    @Command(value = "fly <target>")
    @Permission("cloudannotationsdemo.fly.other")
    @CommandDescription("Toggle flight for target")
    public void flyOther(CommandSender sender, @Argument("target") Player target) {
        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            sender.sendMessage(TextUtils.parseColour("&7[&b!&7]&bSet &e" + target.getName() + " fly &cdisabled"));
        } else {
            target.setAllowFlight(true);
            sender.sendMessage(TextUtils.parseColour("&7[&b!&7]&bSet &e" + target.getName() + " fly &aenabled"));
        }
    }
}
