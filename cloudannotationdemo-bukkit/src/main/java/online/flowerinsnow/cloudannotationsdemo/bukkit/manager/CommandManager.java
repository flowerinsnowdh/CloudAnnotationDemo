package online.flowerinsnow.cloudannotationsdemo.bukkit.manager;

import online.flowerinsnow.cloudannotationsdemo.bukkit.CloudAnnotationsDemoBukkit;
import online.flowerinsnow.cloudannotationsdemo.bukkit.command.FlyCommand;
import online.flowerinsnow.cloudannotationsdemo.bukkit.command.WeatherCommand;
import online.flowerinsnow.cloudannotationsdemo.bukkit.util.TextUtils;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.SenderMapper;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.exception.InvalidCommandSenderException;
import org.incendo.cloud.exception.InvalidSyntaxException;
import org.incendo.cloud.exception.NoPermissionException;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

public class CommandManager {
    private final CloudAnnotationsDemoBukkit plugin;
    private AnnotationParser<CommandSender> annotationParser;
    private PaperCommandManager<CommandSender> manager;
    public CommandManager(CloudAnnotationsDemoBukkit plugin) {
        this.plugin = plugin;
    }

    public void init() {
        this.manager = new PaperCommandManager<>(
                plugin, ExecutionCoordinator.simpleCoordinator(), SenderMapper.identity()
        );

        annotationParser = new AnnotationParser<>(
                manager, CommandSender.class
        );

        registerCommands();
        registerExceptionHandlers();
    }

    private void registerCommands() {
        this.annotationParser.parse(new FlyCommand());
        this.annotationParser.parse(new WeatherCommand());
    }

    private void registerExceptionHandlers() {
        this.manager.exceptionController().registerHandler(InvalidSyntaxException.class, context -> {
            context.context().sender().sendMessage(TextUtils.parseColour("&7[&c!&7]&cInvalid syntax! Correct syntax: &e" + context.exception().correctSyntax()));
        });

        this.manager.exceptionController().registerHandler(NoPermissionException.class, context -> {
            context.context().sender().sendMessage(TextUtils.parseColour("&7[&c!&7]&cYou don't have permission (&e" + context.exception().missingPermission().permissionString() + "&c) to execute this command."));
        });

        this.manager.exceptionController().registerHandler(InvalidCommandSenderException.class, context -> {
            context.context().sender().sendMessage(TextUtils.parseColour("&7[&c!&7]&cTo use this command, you must be &e" + context.exception().requiredSender().getTypeName()));
        });
    }
}
