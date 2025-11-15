package net.quackersnkreme.danielmas1.core;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.minecraft.text.Text.literal;

public class PlayerSwapper {

    //The swap method

    public static void swap(MinecraftServer server) {

        //create list of players in world

        List<ServerPlayerEntity> randPlayers = new ArrayList<>(server.getPlayerManager().getPlayerList());
        List<ServerPlayerEntity> players = new ArrayList<>(server.getPlayerManager().getPlayerList());

        //don't do anything if there is one player

        if (server.getCurrentPlayerCount() < 2) return;

        //shuffle the list of players

        Collections.shuffle(randPlayers);

        //get all the player locations

        List<Vec3d> playerLocations = getLocations(randPlayers);

        float[] playerYaws = getYaw(randPlayers);

        float[] playerPitches = getPitch(randPlayers);

        //moves the players to the new location

        applySwap(playerLocations, playerYaws, playerPitches, players);
    }

    //method to get locations

    private static List<Vec3d> getLocations(List<ServerPlayerEntity> players) {

        /*initialisation of variables
        * vec3d is used for player locations, you can probably do this more efficiently because setPos takes the individual double values
        * */

        List<Vec3d> locations = new ArrayList<>();
        double x;
        double y;
        double z;
        Vec3d pos;

        //for each play in the list of players, get their x,y, and z coords, and store them as a vec3d and put them in the locations arraylist

        for (ServerPlayerEntity player : players) {
            x = player.getX();
            y = player.getY();
            z = player.getZ();

            pos = new Vec3d(x, y, z);

            locations.add(pos);
        }

        return locations;
    }

    private static float[] getYaw(List<ServerPlayerEntity> players) {

        float[] yaws = new float[players.size()];

        for(int i = 0; i < players.size(); i++) {
            yaws[i] = players.get(i).getYaw();
        }

        return yaws;
    }

    private static float[] getPitch(List<ServerPlayerEntity> players) {

        float[] pitches = new float[players.size()];

        for(int i = 0; i < players.size(); i++) {
            pitches[i] = players.get(i).getPitch();
        }

        return pitches;
    }

    //apply swap method

    private static void applySwap(List<Vec3d> locations, float[] yaws, float[] pitches, List<ServerPlayerEntity> players) {

        //teleports players to the list of locations, DO NOT USE setPos this is way easier, you don't have to override anything (this is before i tried look shenanigans)

        for (int i = 0; i < players.size(); i++) {
            int nextIndex = (i + 1) % players.size();
            players.get(i).teleport(locations.get(nextIndex).x, locations.get(nextIndex).y, locations.get(nextIndex).z, false);

        }
    }

}
