package wdl.api;

import net.minecraft.client.multiplayer.WorldClient;

public interface IWorldLoadListener extends IWDLMod {
   void onWorldLoad(WorldClient world, boolean flag);
}
