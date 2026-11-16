package net.minecraft.item;

import augustus.modules.render.XRay;
import client.render.WorldRenderer;
import client.setting.XRayHideType;
import client.util.BaseIBlockStateEntry;
import client.util.BuildManager;
import client.util.IBlockState;
import client.util.InternalLongArrayEntry;
import client.util.ListenerAdapter27;
import client.util.LoadingManager;
import client.util.ServiceStringEntry;
import client.util.SystemFloatEntry;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class ExtendedItemArrayEntry {
   public ServiceStringEntry[] serviceStringEntryArray = new ServiceStringEntry[2];
   public ServiceStringEntry[] serviceStringEntryArray2 = new ServiceStringEntry[2];

   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, WorldRenderer worldRenderer) {
      if (XRay.activeState3 && XRay.getXRayHideType(blockState.getBlock(), blockState, blockPos) == XRayHideType.HIDE) {
         return false;
      } else {
         try {
            if (BuildManager.canExecute()) {
               InternalLongArrayEntry.applyIBlockState(blockState, blockPos, iBlockAccess, worldRenderer);
            }

            int coordX = blockPos.getX();
            int coordY = blockPos.getY();
            int coordZ = blockPos.getZ();
            BlockLiquid block = (BlockLiquid)blockState.getBlock();
            block.processAction(iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            ServiceStringEntry[] serviceStringEntryArray3 = block.getMaterial() == Material.material29 ? this.serviceStringEntryArray : this.serviceStringEntryArray2;
            BaseIBlockStateEntry baseIBlockStateEntry = worldRenderer.resolveBaseIBlockStateEntry(blockState, blockPos);
            int count = LoadingManager.computeCount(iBlockAccess, blockState, blockPos, baseIBlockStateEntry);
            float factor = (float)(count >> 16 & 0xFF) / 255.0F;
            float factor2 = (float)(count >> 8 & 0xFF) / 255.0F;
            float factor3 = (float)(count & 0xFF) / 255.0F;
            boolean flag = block.matchesIBlockAccess(iBlockAccess, coordX, coordY + 1, coordZ, EnumFacing.UP);
            boolean flag2 = block.matchesIBlockAccess(iBlockAccess, coordX, coordY - 1, coordZ, EnumFacing.DOWN);
            boolean[] flagArrayAsFlagArray = baseIBlockStateEntry.getFlagArrayAsFlagArray();
            flagArrayAsFlagArray[0] = block.matchesIBlockAccess(iBlockAccess, coordX, coordY, coordZ - 1, EnumFacing.NORTH);
            flagArrayAsFlagArray[1] = block.matchesIBlockAccess(iBlockAccess, coordX, coordY, coordZ + 1, EnumFacing.SOUTH);
            flagArrayAsFlagArray[2] = block.matchesIBlockAccess(iBlockAccess, coordX - 1, coordY, coordZ, EnumFacing.WEST);
            flagArrayAsFlagArray[3] = block.matchesIBlockAccess(iBlockAccess, coordX + 1, coordY, coordZ, EnumFacing.EAST);
            if (flag || flag2 || flagArrayAsFlagArray[0] || flagArrayAsFlagArray[1] || flagArrayAsFlagArray[2] || flagArrayAsFlagArray[3]) {
               boolean flag3 = false;
               float factor4 = 1.0F;
               Material material = block.getMaterial();
               float factor5 = this.computeFactor(iBlockAccess, coordX, coordY, coordZ, material);
               float factor6 = this.computeFactor(iBlockAccess, coordX, coordY, coordZ + 1, material);
               float factor7 = this.computeFactor(iBlockAccess, coordX + 1, coordY, coordZ + 1, material);
               float factor8 = this.computeFactor(iBlockAccess, coordX + 1, coordY, coordZ, material);
               double coordX2 = (double)blockPos.getX();
               double coordY2 = (double)blockPos.getY();
               double coordZ2 = (double)blockPos.getZ();
               float factor9 = 0.001F;
               if (flag) {
                  flag3 = true;
                  ServiceStringEntry serviceStringEntry = serviceStringEntryArray3[0];
                  float factor10 = (float)BlockLiquid.computeValue(iBlockAccess, blockPos, material);
                  if (factor10 > -999.0F) {
                     serviceStringEntry = serviceStringEntryArray3[1];
                  }

                  worldRenderer.applyServiceStringEntryByServiceStringEnt(serviceStringEntry);
                  factor5 -= factor9;
                  factor6 -= factor9;
                  factor7 -= factor9;
                  factor8 -= factor9;
                  float factor11;
                  float factor12;
                  float factor13;
                  float factor14;
                  float factor15;
                  float factor16;
                  float factor17;
                  float factor18;
                  if (factor10 < -999.0F) {
                     factor11 = serviceStringEntry.computeFactorByDouble(0.0);
                     factor18 = serviceStringEntry.computeFactor(0.0);
                     factor12 = factor11;
                     factor13 = serviceStringEntry.computeFactor(16.0);
                     factor14 = serviceStringEntry.computeFactorByDouble(16.0);
                     factor15 = factor13;
                     factor16 = factor14;
                     factor17 = factor18;
                  } else {
                     float factor19 = MathHelper.abs(factor10) * 0.25F;
                     float factor20 = MathHelper.cos(factor10) * 0.25F;
                     factor11 = serviceStringEntry.computeFactorByDouble((double)(8.0F + (-factor20 - factor19) * 16.0F));
                     factor18 = serviceStringEntry.computeFactor((double)(8.0F + (-factor20 + factor19) * 16.0F));
                     factor12 = serviceStringEntry.computeFactorByDouble((double)(8.0F + (-factor20 + factor19) * 16.0F));
                     factor13 = serviceStringEntry.computeFactor((double)(8.0F + (factor20 + factor19) * 16.0F));
                     factor14 = serviceStringEntry.computeFactorByDouble((double)(8.0F + (factor20 + factor19) * 16.0F));
                     factor15 = serviceStringEntry.computeFactor((double)(8.0F + (factor20 - factor19) * 16.0F));
                     factor16 = serviceStringEntry.computeFactorByDouble((double)(8.0F + (factor20 - factor19) * 16.0F));
                     factor17 = serviceStringEntry.computeFactor((double)(8.0F + (-factor20 - factor19) * 16.0F));
                  }

                  int index = block.getIndex(iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                  int count2 = index >> 16 & 65535;
                  int count3 = index & 65535;
                  float factor21 = factor4 * factor;
                  float factor22 = factor4 * factor2;
                  float factor23 = factor4 * factor3;
                  worldRenderer.getWorldRenderer(coordX2 + 0.0, coordY2 + (double)factor5, coordZ2 + 0.0)
                     .resolveWorldRenderer(factor21, factor22, factor23, 1.0F)
                     .getWorldRenderer6((double)factor11, (double)factor18)
                     .resolveWorldRenderer(count2, count3)
                     .updateState();
                  worldRenderer.getWorldRenderer(coordX2 + 0.0, coordY2 + (double)factor6, coordZ2 + 1.0)
                     .resolveWorldRenderer(factor21, factor22, factor23, 1.0F)
                     .getWorldRenderer6((double)factor12, (double)factor13)
                     .resolveWorldRenderer(count2, count3)
                     .updateState();
                  worldRenderer.getWorldRenderer(coordX2 + 1.0, coordY2 + (double)factor7, coordZ2 + 1.0)
                     .resolveWorldRenderer(factor21, factor22, factor23, 1.0F)
                     .getWorldRenderer6((double)factor14, (double)factor15)
                     .resolveWorldRenderer(count2, count3)
                     .updateState();
                  worldRenderer.getWorldRenderer(coordX2 + 1.0, coordY2 + (double)factor8, coordZ2 + 0.0)
                     .resolveWorldRenderer(factor21, factor22, factor23, 1.0F)
                     .getWorldRenderer6((double)factor16, (double)factor17)
                     .resolveWorldRenderer(count2, count3)
                     .updateState();
                  if (block.matchesIBlockAccessByIBlockAccess(iBlockAccess, blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())) {
                     worldRenderer.getWorldRenderer(coordX2 + 0.0, coordY2 + (double)factor5, coordZ2 + 0.0)
                        .resolveWorldRenderer(factor21, factor22, factor23, 1.0F)
                        .getWorldRenderer6((double)factor11, (double)factor18)
                        .resolveWorldRenderer(count2, count3)
                        .updateState();
                     worldRenderer.getWorldRenderer(coordX2 + 1.0, coordY2 + (double)factor8, coordZ2 + 0.0)
                        .resolveWorldRenderer(factor21, factor22, factor23, 1.0F)
                        .getWorldRenderer6((double)factor16, (double)factor17)
                        .resolveWorldRenderer(count2, count3)
                        .updateState();
                     worldRenderer.getWorldRenderer(coordX2 + 1.0, coordY2 + (double)factor7, coordZ2 + 1.0)
                        .resolveWorldRenderer(factor21, factor22, factor23, 1.0F)
                        .getWorldRenderer6((double)factor14, (double)factor15)
                        .resolveWorldRenderer(count2, count3)
                        .updateState();
                     worldRenderer.getWorldRenderer(coordX2 + 0.0, coordY2 + (double)factor6, coordZ2 + 1.0)
                        .resolveWorldRenderer(factor21, factor22, factor23, 1.0F)
                        .getWorldRenderer6((double)factor12, (double)factor13)
                        .resolveWorldRenderer(count2, count3)
                        .updateState();
                  }
               }

               if (flag2) {
                  worldRenderer.applyServiceStringEntryByServiceStringEnt(serviceStringEntryArray3[0]);
                  float factor24 = serviceStringEntryArray3[0].getFactor5();
                  float factor25 = serviceStringEntryArray3[0].getFactor2();
                  float factor26 = serviceStringEntryArray3[0].getFactor();
                  float factor27 = serviceStringEntryArray3[0].getFactor6();
                  int index2 = block.getIndex(iBlockAccess, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
                  int count4 = index2 >> 16 & 65535;
                  int count5 = index2 & 65535;
                  float factor28 = SystemFloatEntry.computeFactor(EnumFacing.DOWN);
                  worldRenderer.getWorldRenderer(coordX2, coordY2, coordZ2 + 1.0)
                     .resolveWorldRenderer(factor * factor28, factor2 * factor28, factor3 * factor28, 1.0F)
                     .getWorldRenderer6((double)factor24, (double)factor27)
                     .resolveWorldRenderer(count4, count5)
                     .updateState();
                  worldRenderer.getWorldRenderer(coordX2, coordY2, coordZ2)
                     .resolveWorldRenderer(factor * factor28, factor2 * factor28, factor3 * factor28, 1.0F)
                     .getWorldRenderer6((double)factor24, (double)factor26)
                     .resolveWorldRenderer(count4, count5)
                     .updateState();
                  worldRenderer.getWorldRenderer(coordX2 + 1.0, coordY2, coordZ2)
                     .resolveWorldRenderer(factor * factor28, factor2 * factor28, factor3 * factor28, 1.0F)
                     .getWorldRenderer6((double)factor25, (double)factor26)
                     .resolveWorldRenderer(count4, count5)
                     .updateState();
                  worldRenderer.getWorldRenderer(coordX2 + 1.0, coordY2, coordZ2 + 1.0)
                     .resolveWorldRenderer(factor * factor28, factor2 * factor28, factor3 * factor28, 1.0F)
                     .getWorldRenderer6((double)factor25, (double)factor27)
                     .resolveWorldRenderer(count4, count5)
                     .updateState();
                  flag3 = true;
               }

               for (int index3 = 0; index3 < 4; index3++) {
                  int count6 = 0;
                  int count7 = 0;
                  if (index3 == 0) {
                     count7--;
                  }

                  if (index3 == 1) {
                     count7++;
                  }

                  if (index3 == 2) {
                     count6--;
                  }

                  if (index3 == 3) {
                     count6++;
                  }

                  BlockPos blockPos2 = blockPos.add(count6, 0, count7);
                  ServiceStringEntry serviceStringEntry2 = serviceStringEntryArray3[1];
                  worldRenderer.applyServiceStringEntryByServiceStringEnt(serviceStringEntry2);
                  if (flagArrayAsFlagArray[index3]) {
                     double offset;
                     double offset2;
                     double offset3;
                     double offset4;
                     float factor29;
                     float factor30;
                     if (index3 == 0) {
                        factor30 = factor5;
                        factor29 = factor8;
                        offset = coordX2;
                        offset2 = coordX2 + 1.0;
                        offset3 = coordZ2 + (double)factor9;
                        offset4 = coordZ2 + (double)factor9;
                     } else if (index3 == 1) {
                        factor30 = factor7;
                        factor29 = factor6;
                        offset = coordX2 + 1.0;
                        offset2 = coordX2;
                        offset3 = coordZ2 + 1.0 - (double)factor9;
                        offset4 = coordZ2 + 1.0 - (double)factor9;
                     } else if (index3 == 2) {
                        factor30 = factor6;
                        factor29 = factor5;
                        offset = coordX2 + (double)factor9;
                        offset2 = coordX2 + (double)factor9;
                        offset3 = coordZ2 + 1.0;
                        offset4 = coordZ2;
                     } else {
                        factor30 = factor8;
                        factor29 = factor7;
                        offset = coordX2 + 1.0 - (double)factor9;
                        offset2 = coordX2 + 1.0 - (double)factor9;
                        offset3 = coordZ2;
                        offset4 = coordZ2 + 1.0;
                     }

                     flag3 = true;
                     float factor31 = serviceStringEntry2.computeFactorByDouble(0.0);
                     float factor32 = serviceStringEntry2.computeFactorByDouble(8.0);
                     float factor33 = serviceStringEntry2.computeFactor((double)((1.0F - factor30) * 16.0F * 0.5F));
                     float factor34 = serviceStringEntry2.computeFactor((double)((1.0F - factor29) * 16.0F * 0.5F));
                     float factor35 = serviceStringEntry2.computeFactor(8.0);
                     int index4 = block.getIndex(iBlockAccess, blockPos2.getX(), blockPos2.getY(), blockPos2.getZ());
                     int count8 = index4 >> 16 & 65535;
                     int count9 = index4 & 65535;
                     float factor36 = index3 < 2 ? SystemFloatEntry.computeFactor(EnumFacing.NORTH) : SystemFloatEntry.computeFactor(EnumFacing.WEST);
                     float factor37 = factor4 * factor36 * factor;
                     float factor38 = factor4 * factor36 * factor2;
                     float factor39 = factor4 * factor36 * factor3;
                     worldRenderer.getWorldRenderer(offset, coordY2 + (double)factor30, offset3)
                        .resolveWorldRenderer(factor37, factor38, factor39, 1.0F)
                        .getWorldRenderer6((double)factor31, (double)factor33)
                        .resolveWorldRenderer(count8, count9)
                        .updateState();
                     worldRenderer.getWorldRenderer(offset2, coordY2 + (double)factor29, offset4)
                        .resolveWorldRenderer(factor37, factor38, factor39, 1.0F)
                        .getWorldRenderer6((double)factor32, (double)factor34)
                        .resolveWorldRenderer(count8, count9)
                        .updateState();
                     worldRenderer.getWorldRenderer(offset2, coordY2 + 0.0, offset4)
                        .resolveWorldRenderer(factor37, factor38, factor39, 1.0F)
                        .getWorldRenderer6((double)factor32, (double)factor35)
                        .resolveWorldRenderer(count8, count9)
                        .updateState();
                     worldRenderer.getWorldRenderer(offset, coordY2 + 0.0, offset3)
                        .resolveWorldRenderer(factor37, factor38, factor39, 1.0F)
                        .getWorldRenderer6((double)factor31, (double)factor35)
                        .resolveWorldRenderer(count8, count9)
                        .updateState();
                     worldRenderer.getWorldRenderer(offset, coordY2 + 0.0, offset3)
                        .resolveWorldRenderer(factor37, factor38, factor39, 1.0F)
                        .getWorldRenderer6((double)factor31, (double)factor35)
                        .resolveWorldRenderer(count8, count9)
                        .updateState();
                     worldRenderer.getWorldRenderer(offset2, coordY2 + 0.0, offset4)
                        .resolveWorldRenderer(factor37, factor38, factor39, 1.0F)
                        .getWorldRenderer6((double)factor32, (double)factor35)
                        .resolveWorldRenderer(count8, count9)
                        .updateState();
                     worldRenderer.getWorldRenderer(offset2, coordY2 + (double)factor29, offset4)
                        .resolveWorldRenderer(factor37, factor38, factor39, 1.0F)
                        .getWorldRenderer6((double)factor32, (double)factor34)
                        .resolveWorldRenderer(count8, count9)
                        .updateState();
                     worldRenderer.getWorldRenderer(offset, coordY2 + (double)factor30, offset3)
                        .resolveWorldRenderer(factor37, factor38, factor39, 1.0F)
                        .getWorldRenderer6((double)factor31, (double)factor33)
                        .resolveWorldRenderer(count8, count9)
                        .updateState();
                  }
               }

               worldRenderer.applyServiceStringEntryByServiceStringEnt(null);
               return flag3;
            }
         } finally {
            if (BuildManager.canExecute()) {
               InternalLongArrayEntry.applyWorldRenderer(worldRenderer);
            }
         }

         return false;
      }
   }

   public void updateState() {
      ListenerAdapter27 listenerAdapter = Minecraft.getMinecraft().getListenerAdapter27();
      this.serviceStringEntryArray[0] = listenerAdapter.get("minecraft:blocks/lava_still");
      this.serviceStringEntryArray[1] = listenerAdapter.get("minecraft:blocks/lava_flow");
      this.serviceStringEntryArray2[0] = listenerAdapter.get("minecraft:blocks/water_still");
      this.serviceStringEntryArray2[1] = listenerAdapter.get("minecraft:blocks/water_flow");
   }

   private float computeFactor(IBlockAccess iBlockAccess, int count, int count2, int count3, Material material) {
      int count4 = 0;
      float factor = 0.0F;

      for (int index = 0; index < 4; index++) {
         int count5 = count - (index & 1);
         int count6 = count3 - (index >> 1 & 1);
         if (iBlockAccess.getIBlockState(count5, count2 + 1, count6).getBlock().getMaterial() == material) {
            return 1.0F;
         }

         IBlockState iBlockState = iBlockAccess.getIBlockState(count5, count2, count6);
         Material material2 = iBlockState.getBlock().getMaterial();
         if (material2 != material) {
            if (!material2.canExecute2()) {
               factor++;
               count4++;
            }
         } else {
            int comparable = (Integer)iBlockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry);
            if (comparable >= 8 || comparable == 0) {
               factor += BlockLiquid.computeFactor(comparable) * 10.0F;
               count4 += 10;
            }

            factor += BlockLiquid.computeFactor(comparable);
            count4++;
         }
      }

      return 1.0F - factor / (float)count4;
   }

   public ExtendedItemArrayEntry() {
      this.updateState();
   }
}
