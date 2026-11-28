package wdl.api;

import net.minecraft.client.multiplayer.WorldClient;

public interface IPluginChannelListener extends IWDLMod {
   void onPluginChannelPacket(WorldClient world, String str, byte[] bytes);
}
