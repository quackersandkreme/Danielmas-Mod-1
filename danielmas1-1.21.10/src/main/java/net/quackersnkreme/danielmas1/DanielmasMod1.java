package net.quackersnkreme.danielmas1;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DanielmasMod1 implements ModInitializer {
	public static final String MOD_ID = "danielmas1";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//test_command
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment)
				-> {dispatcher.register(CommandManager.literal("test_command").executes(context
				-> {context.getSource().sendFeedback(()
				-> Text.literal("Zakk is a pixel pooper."), false);
					return 1;}));});

		//start swap command
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("swap_players")
					.then(CommandManager.argument("time between swaps (secs)", IntegerArgumentType.integer())
							.requires(source -> source.hasPermissionLevel(1))
							.executes(SwapPlayers::swapPlayers)));
		});
	}
}