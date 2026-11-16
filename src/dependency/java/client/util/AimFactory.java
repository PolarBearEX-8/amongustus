package client.util;

import client.setting.GameSettings;
import net.minecraft.block.BlockAir;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class AimFactory {
   public static void applyGameSettingsByGameSettings(GameSettings gameSettings, World world) {
      if (gameSettings != null) {
         byte byteValue = 3;
         if (gameSettings.state45) {
            byteValue = 1;
         }

         BlockAir.applyBlock(Blocks.blockLava2, byteValue);
         BlockAir.applyBlock(Blocks.blockFlowingLava, byteValue);
      }

      if (world != null) {
         IChunkProvider commonListener = world.getCommonListener();
         if (commonListener != null) {
            Entity renderViewEntity = BuildManager.getMc().getRenderViewEntity();
            if (renderViewEntity != null) {
               int count = (int)renderViewEntity.posX / 16;
               int count2 = (int)renderViewEntity.posZ / 16;
               int count3 = count - 512;
               int count4 = count + 512;
               int count5 = count2 - 512;
               int count6 = count2 + 512;

               int count7;
               for (count7 = 0; count3 < count4; count3++) {
                  for (int index = count5; index < count6; index++) {
                     if (commonListener.checkIndex(count3, index)) {
                        AlertNotificationSound alertNotificationSound = commonListener.resolveAlertNotificationSound(count3, index);
                        if (alertNotificationSound != null && !(alertNotificationSound instanceof LoggerEntryAdapter)) {
                           int count8 = count3 << 4;
                           int count9 = index << 4;
                           int count10 = count8 + 16;
                           int count11 = count9 + 16;
                           ExtendedBlockPosAdapter extendedBlockPosAdapter = new ExtendedBlockPosAdapter(0, 0, 0);

                           for (ExtendedBlockPosAdapter extendedBlockPosAdapter2 = new ExtendedBlockPosAdapter(0, 0, 0); count8 < count10; count8++) {
                              for (int index2 = count9; index2 < count11; index2++) {
                                 extendedBlockPosAdapter.applyIndex(count8, 0, index2);
                                 BlockPos blockPos = world.resolveBlockPosByBlockPos(extendedBlockPosAdapter);

                                 for (int index3 = 0; index3 < blockPos.getY(); index3++) {
                                    extendedBlockPosAdapter2.applyIndex(count8, index3, index2);
                                    if (world.getBlockState(extendedBlockPosAdapter2).getBlock().getMaterial() == Material.coreItemEntry1) {
                                       world.applyIndex(count8, index2, extendedBlockPosAdapter2.getY(), blockPos.getY());
                                       count7++;
                                       break;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (count7 > 0) {
                  String text = "server";
                  if (BuildManager.currentThread()) {
                     text = "client";
                  }

                  BuildManager.applyText4("ClearWater (" + text + ") relighted " + count7 + " chunks");
               }
            }
         }
      }
   }
}
