package net.quackersnkreme.danielmas1;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class SwapPlayers {
    static int swapPlayers(CommandContext<ServerCommandSource> context) {
        int time = IntegerArgumentType.getInteger(context, "time between swaps");
        context.getSource().sendFeedback(() -> Text.literal("Called /swap_players with time = %s".formatted(time)), false);

        return 1;
    }
}
