package net.augustus.utils.simulation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.world.World;

public class SimulatedPlayerEntity extends EntityPlayerSP {
   public SimulatedPlayerEntity(Minecraft mc2, World world, NetHandlerPlayClient netHandlerPlayClient, StatFileWriter statFileWriter) {
      super(mc2, world, netHandlerPlayClient, statFileWriter);
   }
}
