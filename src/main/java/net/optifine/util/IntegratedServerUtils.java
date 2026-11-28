package net.optifine.util;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.optifine.config.Config;

public class IntegratedServerUtils {
   public static TileEntity getTileEntity(BlockPos pos) {
      WorldServer worldServer = getWorldServer();
      if (worldServer == null) {
         return null;
      } else {
         Chunk chunk = worldServer.getChunkProvider().provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
         return chunk == null ? null : chunk.getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK);
      }
   }

   public static WorldServer getWorldServer() {
      Minecraft mc = Config.getMc();
      WorldClient theWorld = mc.theWorld;
      if (theWorld == null) {
         return null;
      } else if (!mc.isIntegratedServerRunning()) {
         return null;
      } else {
         IntegratedServer integratedServer = mc.getIntegratedServer();
         if (integratedServer == null) {
            return null;
         } else {
            WorldProvider provider = theWorld.provider;
            if (provider == null) {
               return null;
            } else {
               int dimensionId = provider.getDimensionId();

               try {
                  return integratedServer.worldServerForDimension(dimensionId);
               } catch (NullPointerException e) {
                  return null;
               }
            }
         }
      }
   }

   public static Entity getEntity(UUID uuid) {
      WorldServer worldServer = getWorldServer();
      return worldServer == null ? null : worldServer.getEntityFromUuid(uuid);
   }
}
