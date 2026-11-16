package cc.baritone.utils;

import net.minecraft.client.multiplayer.WorldClient;

public interface WorldClientSpecialListener extends SpecialListener {
   void applyWorldClient(WorldClient world, String text, byte[] byteValueArray);
}
