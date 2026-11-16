package client.util;

import client.setting.CombatGroupProperty;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;

public class FovProvider {
   public static WorldServer getWorldServer() {
      Minecraft mc = BuildManager.getMc();
      WorldClient world = mc.theWorld;
      if (world == null) {
         return null;
      } else if (!mc.isGamePaused()) {
         return null;
      } else {
         IntegratedServer integratedServer = mc.getIntegratedServer();
         if (integratedServer == null) {
            return null;
         } else {
            SecondaryFloatArrayEntry secondaryFloatArrayEntry = world.secondaryFloatArrayEntry;
            if (secondaryFloatArrayEntry == null) {
               return null;
            } else {
               int countAsIndex = secondaryFloatArrayEntry.getCountAsIndex();

               try {
                  return integratedServer.resolveWorldServer(countAsIndex);
               } catch (NullPointerException nullPointerException) {
                  return null;
               }
            }
         }
      }
   }

   public static Entity resolveEntityByUUID(UUID uuid) {
      WorldServer worldServer = getWorldServer();
      return worldServer == null ? null : worldServer.get(uuid);
   }

   public static TileEntity resolveTileEntity(BlockPos blockPos) {
      WorldServer worldServer = getWorldServer();
      if (worldServer == null) {
         return null;
      } else {
         AlertNotificationSound alertNotificationSound = worldServer.getCommonListener().resolveAlertNotificationSound(blockPos.getX() >> 4, blockPos.getZ() >> 4);
         return alertNotificationSound == null ? null : alertNotificationSound.resolveTileEntity(blockPos, CombatGroupProperty.CHECK);
      }
   }
}
