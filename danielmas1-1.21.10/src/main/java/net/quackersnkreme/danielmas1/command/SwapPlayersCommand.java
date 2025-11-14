package net.quackersnkreme.danielmas1.command;

import com.mojang.brigadier.CommandDispatcher;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.quackersnkreme.danielmas1.core.SwapManager;

import static net.minecraft.text.Text.literal;


public class SwapPlayersCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(

                //registering the name of the command, /time has the name time etc

                CommandManager.literal("swapPlayers")

                        //registering the various subcommands (/swapPlayers start, /swapPlayers stop, /swapPlayers interval (time in seconds))

                        .then(CommandManager.literal("start").executes(ctx -> {

                            //ctx is a shorthand for the command context, aka...if you don't understand lookup context in the dictionary or something

                            SwapManager.startSwapping(ctx.getSource());
                            return 1;
                        }))
                        .then(CommandManager.literal("stop").executes(ctx -> {
                            SwapManager.stopSwapping(ctx.getSource());
                            return 1;
                        }))
                        .then(CommandManager.literal("interval")

                                //argument is used for creating a command argument

                                .then(CommandManager.argument("seconds", IntegerArgumentType.integer(5))
                                        .executes(ctx -> {
                                            SwapManager.setInterval(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "seconds"));
                                            return 1;
                                        })))
                        .executes(ctx -> {
                            ctx.getSource().sendFeedback(() -> literal("This is the swap command"), false);
                            ctx.getSource().sendFeedback(() -> literal("type /swapPlayers start to start swapping"), false);
                            ctx.getSource().sendFeedback(() -> literal("type /swapPlayers stop to stop swapping"), false);
                            ctx.getSource().sendFeedback(() -> literal("type /swapPlayer interval to change the time between swaps"), false);
                            return 1;
                        })
        );
    }
}
