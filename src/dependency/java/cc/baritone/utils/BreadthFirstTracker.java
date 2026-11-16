package cc.baritone.utils;

import net.minecraft.client.multiplayer.WorldClient;

public interface BreadthFirstTracker extends SpecialListener {
   void applyWorldClientByWorldClient(WorldClient world, String text);
}
