package online.flowerinsnow.cloudannotationsdemo.bukkit.command;

import online.flowerinsnow.cloudannotationsdemo.bukkit.util.TextUtils;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;
import org.incendo.cloud.annotations.suggestion.Suggestions;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.context.CommandInput;

import java.util.Arrays;
import java.util.List;

public class WeatherCommand {
    @Command(value = "weather <weather>", requiredSender = Player.class)
    @CommandDescription("Set weather of the world")
    @Permission("cloudannotationsdemo.command.weather")
    public void setWeather(Player sender, @Argument(value = "weather", description = "weather type") String weather) {
        World world = sender.getWorld();
        switch (weather) {
            case "clear", "sunny", "sun": {
                world.setStorm(false);
                world.setThundering(false);
            }
            case "rain", "rainy": {
                world.setStorm(false);
                world.setThundering(true);
            }
            case "thunder", "storm": {
                world.setThundering(true);
                world.setStorm(true);
            }
            default: {
                sender.sendMessage(TextUtils.parseColour("&7[&c!&7]&cNo such weather"));
            }
        }
    }



    @Suggestions("weathers")
    public List<String> suggestionsEnableOrNot(CommandContext<Player> sender, CommandInput input) {
        return Arrays.asList("clear", "sunny", "sun", "rain", "rainy", "thunder", "storm");
    }
}
