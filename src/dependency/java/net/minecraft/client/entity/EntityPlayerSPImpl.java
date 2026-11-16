package net.minecraft.client.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.world.World;

public class EntityPlayerSPImpl extends EntityPlayerSP {
   public EntityPlayerSPImpl(Minecraft mc, World world, NetHandlerPlayClient netHandlerPlayClient, StatFileWriter statFileWriter) {
      super(mc, world, netHandlerPlayClient, statFileWriter);
   }
}
