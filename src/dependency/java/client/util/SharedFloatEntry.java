package client.util;

import augustus.modules.render.XRay;
import client.render.WorldRenderer;
import client.render.WorldRendererArrayEntry;
import client.setting.BlockNoneType;
import client.setting.BlockSolidType;
import client.setting.XRayHideType;
import java.util.BitSet;
import java.util.List;
import net.zzzz47r4Customizer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.item.ContextItemEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;

public class SharedFloatEntry {
   public static float factor = 0.2F;
   public static boolean enabled = false;
   public static final BlockSolidType[] blockSolidTypeArray = new BlockSolidType[]{
      BlockSolidType.CUTOUT, BlockSolidType.CUTOUT_MIPPED, BlockSolidType.TRANSLUCENT
   };

   public static void updateState() {
      factor = 1.0F - BuildManager.getFactor() * 0.8F;
      enabled = BuildManager.canExecute() && PreEndHandManager.isActive15();
   }

   public boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, IBakedModel iBakedModel, IBlockState blockState, BlockPos blockPos, WorldRenderer worldRenderer, boolean flag) {
      IBakedModel iBakedModel2 = iBakedModel;
      if (worldRenderer == null) {
         return false;
      } else {
         boolean flag2 = Minecraft.isActive() && blockState.getBlock().getIndexOffset1() == 0 && iBakedModel.isEnabled();

         try {
            if (BuildManager.canExecute()) {
               InternalLongArrayEntry.applyIBlockState(blockState, blockPos, iBlockAccess, worldRenderer);
            }

            BaseIBlockStateEntry baseIBlockStateEntry = worldRenderer.resolveBaseIBlockStateEntry(blockState, blockPos);
            iBakedModel2 = zzzz47r4Customizer.resolveIBakedModel(iBakedModel2, blockState, baseIBlockStateEntry);
            boolean flag3 = flag2
               ? this.matchesIBlockAccess(iBlockAccess, iBakedModel2, blockState, blockPos, worldRenderer, flag)
               : this.matchesIBlockAccess2(iBlockAccess, iBakedModel2, blockState, blockPos, worldRenderer, flag);
            if (flag3) {
               this.applyIBlockAccess(iBlockAccess, iBakedModel2, blockState, blockPos, worldRenderer, flag, baseIBlockStateEntry, flag2);
            }

            if (BuildManager.canExecute()) {
               InternalLongArrayEntry.applyWorldRenderer(worldRenderer);
            }

            return flag3;
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Tesselating block model");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Block model being tesselated");
            CrashReportCategory.applyCrashReportCategory(crashReportCategory, blockPos, blockState);
            crashReportCategory.add("Using AO", flag2);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }
   }

   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, IBakedModel iBakedModel, Block block, BlockPos blockPos, WorldRenderer worldRenderer, boolean flag) {
      IBlockState blockState = iBlockAccess.getBlockState(blockPos);
      return this.matchesIBlockAccess2(iBlockAccess, iBakedModel, blockState, blockPos, worldRenderer, flag);
   }

   public void applyIBakedModel(IBakedModel iBakedModel, IBlockState blockState, float factor2, boolean flag) {
      Block block = blockState.getBlock();
      block.processAction2();
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      int count = block.processAction2(block.processAction(blockState));
      if (EntityRenderer.enabled5) {
         count = SpecialLoggerEntry.computeCount(count);
      }

      float factor3 = (float)(count >> 16 & 0xFF) / 255.0F;
      float factor4 = (float)(count >> 8 & 0xFF) / 255.0F;
      float factor5 = (float)(count & 0xFF) / 255.0F;
      if (!flag) {
         GlStateManager.color4f2(factor2, factor2, factor2, 1.0F);
      }

      this.applyIBakedModel(iBakedModel, factor2, factor3, factor4, factor5);
   }

   private void applyIBlockAccess(
      IBlockAccess iBlockAccess, IBakedModel iBakedModel, IBlockState blockState, BlockPos blockPos, WorldRenderer worldRenderer, boolean flag, BaseIBlockStateEntry baseIBlockStateEntry, boolean flag2
   ) {
      if (baseIBlockStateEntry.isEnabled()) {
         for (int index = 0; index < blockSolidTypeArray.length; index++) {
            BlockSolidType blockSolidType = blockSolidTypeArray[index];
            ManagedListEntry managedListEntry = baseIBlockStateEntry.resolveManagedListEntry(blockSolidType);
            if (managedListEntry.size() > 0) {
               WorldRendererArrayEntry worldRendererArrayEntry = baseIBlockStateEntry.getWorldRendererArrayEntry();
               if (worldRendererArrayEntry != null) {
                  WorldRenderer ordinal = worldRendererArrayEntry.ordinal(blockSolidType);
                  if (!ordinal.isEnabled3()) {
                     ordinal.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry5);
                     ordinal.applyDouble(worldRenderer.getValue2(), worldRenderer.getValue(), worldRenderer.getValue3());
                  }

                  for (int index2 = 0; index2 < managedListEntry.size(); index2++) {
                     LocalIntArrayEntry localIntArrayEntry = managedListEntry.get(index2);
                     List list = managedListEntry.set(localIntArrayEntry);
                     IBlockState blockState2 = managedListEntry.resolveIBlockState(index2);
                     if (localIntArrayEntry.getLocalIntArrayEntry() != null) {
                        managedListEntry.applyLocalIntArrayEntry(localIntArrayEntry.getLocalIntArrayEntry(), blockState2);
                     }

                     baseIBlockStateEntry.applyIBlockState(blockState2, blockPos);
                     if (flag2) {
                        this.applyIBlockAccess(iBlockAccess, blockState2, blockPos, ordinal, list, baseIBlockStateEntry);
                     } else {
                        int count = blockState2.getBlock().processAction(iBlockAccess, blockPos.offset(localIntArrayEntry.getEnumFacing()));
                        this.applyIBlockAccess(iBlockAccess, blockState2, blockPos, localIntArrayEntry.getEnumFacing(), count, false, ordinal, list, baseIBlockStateEntry);
                     }
                  }
               }

               managedListEntry.updateState();
            }
         }
      }

      if (BuildManager.isActive62() && !baseIBlockStateEntry.isActive() && ContextItemEntry.matchesIBlockAccess(iBlockAccess, blockState, blockPos)) {
         IBakedModel iBakedModel2 = ContextItemEntry.getIBakedModel();
         IBlockState iBlockState = ContextItemEntry.getIBlockState();
         this.matchesIBlockAccessByIBlockAccess(iBlockAccess, iBakedModel2, iBlockState, blockPos, worldRenderer, flag);
      }
   }

   private void applyList(float posX, float posX2, float posY, float posZ, List list) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();

      for (LocalIntArrayEntry localIntArrayEntry : (Iterable<LocalIntArrayEntry>)(Iterable)list) {
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry11);
         worldRenderer.applyIndexArray(localIntArrayEntry.getIndexArray());
         worldRenderer.applyServiceStringEntry(localIntArrayEntry.getServiceStringEntry());
         if (localIntArrayEntry.isActive2()) {
            worldRenderer.applyFloat2(posX2 * posX, posY * posX, posZ * posX);
         } else {
            worldRenderer.applyFloat2(posX, posX, posX);
         }

         Vec3i vec3i = localIntArrayEntry.getEnumFacing().getVec3i();
         worldRenderer.applyFloat((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
         tessellator.updateState();
      }
   }

   private boolean matchesIBlockAccess(IBlockAccess iBlockAccess, IBakedModel iBakedModel, IBlockState blockState, BlockPos blockPos, WorldRenderer worldRenderer, boolean flag) {
      boolean flag2 = false;
      Block block = blockState.getBlock();
      boolean flag3 = false;
      if (XRay.activeState3) {
         XRayHideType xRayHideType = XRay.getXRayHideType(block, blockState, blockPos);
         if (xRayHideType == XRayHideType.HIDE) {
            return false;
         }

         flag3 = xRayHideType == XRayHideType.FORCE_VISIBLE;
      }

      BaseIBlockStateEntry baseIBlockStateEntry = worldRenderer.resolveBaseIBlockStateEntry(blockState, blockPos);
      BlockSolidType blockSolidType = worldRenderer.getBlockSolidType();

      for (EnumFacing enumFacing : EnumFacing.VALUES) {
         List list = iBakedModel.resolveList(enumFacing);
         if (!list.isEmpty()
            && (
               flag3
                  || !flag
                  || block.matchesIBlockAccess(iBlockAccess, blockPos.getX() + enumFacing.getInt2(), blockPos.getY() + enumFacing.getInt3(), blockPos.getZ() + enumFacing.getInt(), enumFacing)
            )) {
            List list2 = zzzz47r4Customizer.resolveList(list, iBlockAccess, blockState, blockPos, enumFacing, blockSolidType, 0L, baseIBlockStateEntry);
            this.applyIBlockAccess(iBlockAccess, blockState, blockPos, worldRenderer, list2, baseIBlockStateEntry);
            flag2 = true;
         }
      }

      List list3 = iBakedModel.getList();
      if (!list3.isEmpty()) {
         List list4 = zzzz47r4Customizer.resolveList(list3, iBlockAccess, blockState, blockPos, (EnumFacing)null, blockSolidType, 0L, baseIBlockStateEntry);
         this.applyIBlockAccess(iBlockAccess, blockState, blockPos, worldRenderer, list4, baseIBlockStateEntry);
         flag2 = true;
      }

      return flag2;
   }

   private void applyBlock(Block block, int[] countArray, EnumFacing enumFacing, float[] factorArray, BitSet bitSet) {
      float factor2 = 32.0F;
      float factor3 = 32.0F;
      float factor4 = 32.0F;
      float factor5 = -32.0F;
      float factor6 = -32.0F;
      float factor7 = -32.0F;
      int count = countArray.length / 4;

      for (int index = 0; index < 4; index++) {
         float factor8 = Float.intBitsToFloat(countArray[index * count]);
         float factor9 = Float.intBitsToFloat(countArray[index * count + 1]);
         float factor10 = Float.intBitsToFloat(countArray[index * count + 2]);
         factor2 = Math.min(factor2, factor8);
         factor3 = Math.min(factor3, factor9);
         factor4 = Math.min(factor4, factor10);
         factor5 = Math.max(factor5, factor8);
         factor6 = Math.max(factor6, factor9);
         factor7 = Math.max(factor7, factor10);
      }

      if (factorArray != null) {
         factorArray[EnumFacing.WEST.getIndexOffset1()] = factor2;
         factorArray[EnumFacing.EAST.getIndexOffset1()] = factor5;
         factorArray[EnumFacing.DOWN.getIndexOffset1()] = factor3;
         factorArray[EnumFacing.UP.getIndexOffset1()] = factor6;
         factorArray[EnumFacing.NORTH.getIndexOffset1()] = factor4;
         factorArray[EnumFacing.SOUTH.getIndexOffset1()] = factor7;
         int length = EnumFacing.VALUES.length;
         factorArray[EnumFacing.WEST.getIndexOffset1() + length] = 1.0F - factor2;
         factorArray[EnumFacing.EAST.getIndexOffset1() + length] = 1.0F - factor5;
         factorArray[EnumFacing.DOWN.getIndexOffset1() + length] = 1.0F - factor3;
         factorArray[EnumFacing.UP.getIndexOffset1() + length] = 1.0F - factor6;
         factorArray[EnumFacing.NORTH.getIndexOffset1() + length] = 1.0F - factor4;
         factorArray[EnumFacing.SOUTH.getIndexOffset1() + length] = 1.0F - factor7;
      }

      switch (SplineProvider.countArray[enumFacing.ordinal()]) {
         case 1:
            bitSet.set(1, factor2 >= 1.0E-4F || factor4 >= 1.0E-4F || factor5 <= 0.9999F || factor7 <= 0.9999F);
            bitSet.set(0, (factor3 < 1.0E-4F || block.processAction14()) && factor3 == factor6);
            break;
         case 2:
            bitSet.set(1, factor2 >= 1.0E-4F || factor4 >= 1.0E-4F || factor5 <= 0.9999F || factor7 <= 0.9999F);
            bitSet.set(0, (factor6 > 0.9999F || block.processAction14()) && factor3 == factor6);
            break;
         case 3:
            bitSet.set(1, factor2 >= 1.0E-4F || factor3 >= 1.0E-4F || factor5 <= 0.9999F || factor6 <= 0.9999F);
            bitSet.set(0, (factor4 < 1.0E-4F || block.processAction14()) && factor4 == factor7);
            break;
         case 4:
            bitSet.set(1, factor2 >= 1.0E-4F || factor3 >= 1.0E-4F || factor5 <= 0.9999F || factor6 <= 0.9999F);
            bitSet.set(0, (factor7 > 0.9999F || block.processAction14()) && factor4 == factor7);
            break;
         case 5:
            bitSet.set(1, factor3 >= 1.0E-4F || factor4 >= 1.0E-4F || factor6 <= 0.9999F || factor7 <= 0.9999F);
            bitSet.set(0, (factor2 < 1.0E-4F || block.processAction14()) && factor2 == factor5);
            break;
         case 6:
            bitSet.set(1, factor3 >= 1.0E-4F || factor4 >= 1.0E-4F || factor6 <= 0.9999F || factor7 <= 0.9999F);
            bitSet.set(0, (factor5 > 0.9999F || block.processAction14()) && factor2 == factor5);
      }
   }

   public void applyIBakedModel(IBakedModel iBakedModel, float posX, float posX2, float posY, float posZ) {
      for (EnumFacing enumFacing : EnumFacing.VALUES) {
         this.applyList(posX, posX2, posY, posZ, iBakedModel.resolveList(enumFacing));
      }

      this.applyList(posX, posX2, posY, posZ, iBakedModel.getList());
   }

   private void applyIBlockAccess(
      IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, int count, boolean flag, WorldRenderer worldRenderer, List list, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      int count2 = count;
      Block block = blockState.getBlock();
      BitSet bitSet = baseIBlockStateEntry.getBitSet();
      double coordX = (double)blockPos.getX();
      double coordY = (double)blockPos.getY();
      double coordZ = (double)blockPos.getZ();
      BlockNoneType blockNoneType = block.processAction9();
      if (blockNoneType != BlockNoneType.NONE) {
         int coordX2 = blockPos.getX();
         int coordZ2 = blockPos.getZ();
         long keySalt = (long)(coordX2 * 3129871) ^ (long)coordZ2 * 116129781L;
         long value = keySalt * keySalt * 42317861L + keySalt * 11L;
         coordX += ((double)((float)(value >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
         coordZ += ((double)((float)(value >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
         if (blockNoneType == BlockNoneType.XYZ) {
            coordY += ((double)((float)(value >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
         }
      }

      for (LocalIntArrayEntry localIntArrayEntry : (Iterable<LocalIntArrayEntry>)(Iterable)list) {
         if (flag) {
            this.applyBlock(block, localIntArrayEntry.getIndexArray(), localIntArrayEntry.getEnumFacing(), (float[])null, bitSet);
            count2 = bitSet.get(0) ? block.processAction(iBlockAccess, blockPos.offset(localIntArrayEntry.getEnumFacing())) : block.processAction(iBlockAccess, blockPos);
         }

         if (localIntArrayEntry.getServiceStringEntry().enabled4) {
            count2 |= 240;
         }

         if (worldRenderer.isActive()) {
            worldRenderer.applyIndexArray(localIntArrayEntry.getIndexArrayAsIndexArray());
         } else {
            worldRenderer.applyIndexArray(localIntArrayEntry.getIndexArray());
         }

         worldRenderer.applyServiceStringEntry(localIntArrayEntry.getServiceStringEntry());
         worldRenderer.applyIndex(count2, count2, count2, count2);
         int count3 = LoadingManager.computeCount(localIntArrayEntry, blockState, iBlockAccess, blockPos, baseIBlockStateEntry);
         if (localIntArrayEntry.isActive2() || count3 != -1) {
            if (count3 == -1) {
               count3 = block.processAction(iBlockAccess, blockPos, localIntArrayEntry.getCount());
            }

            if (EntityRenderer.enabled5) {
               count3 = SpecialLoggerEntry.computeCount(count3);
            }

            float factor2 = (float)(count3 >> 16 & 0xFF) / 255.0F;
            float factor3 = (float)(count3 >> 8 & 0xFF) / 255.0F;
            float factor4 = (float)(count3 & 0xFF) / 255.0F;
            worldRenderer.applyFloatByFloat(factor2, factor3, factor4, 4);
            worldRenderer.applyFloatByFloat(factor2, factor3, factor4, 3);
            worldRenderer.applyFloatByFloat(factor2, factor3, factor4, 2);
            worldRenderer.applyFloatByFloat(factor2, factor3, factor4, 1);
         }

         worldRenderer.applyDoubleByDouble(coordX, coordY, coordZ);
      }
   }

   public boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, IBakedModel iBakedModel, Block block, BlockPos blockPos, WorldRenderer worldRenderer, boolean flag) {
      IBlockState blockState = iBlockAccess.getBlockState(blockPos);
      return this.matchesIBlockAccess(iBlockAccess, iBakedModel, blockState, blockPos, worldRenderer, flag);
   }

   private void applyIBlockAccess(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, WorldRenderer worldRenderer, List list, BaseIBlockStateEntry baseIBlockStateEntry) {
      Block block = blockState.getBlock();
      float[] factorArray = baseIBlockStateEntry.getFactorArray();
      BitSet bitSet = baseIBlockStateEntry.getBitSet();
      AdvancedFloatArrayEntry advancedFloatArrayEntry = baseIBlockStateEntry.getAdvancedFloatArrayEntry();
      double coordX = (double)blockPos.getX();
      double coordY = (double)blockPos.getY();
      double coordZ = (double)blockPos.getZ();
      BlockNoneType blockNoneType = block.processAction9();
      if (blockNoneType != BlockNoneType.NONE) {
         long value = MathHelper.processAction(blockPos);
         coordX += ((double)((float)(value >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
         coordZ += ((double)((float)(value >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
         if (blockNoneType == BlockNoneType.XYZ) {
            coordY += ((double)((float)(value >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
         }
      }

      for (int index = 0; index < list.size(); index++) {
         LocalIntArrayEntry localIntArrayEntry = (LocalIntArrayEntry)list.get(index);
         this.applyBlock(block, localIntArrayEntry.getIndexArray(), localIntArrayEntry.getEnumFacing(), factorArray, bitSet);
         advancedFloatArrayEntry.applyIBlockAccess(iBlockAccess, block, blockPos, localIntArrayEntry.getEnumFacing(), factorArray, bitSet);
         if (localIntArrayEntry.getServiceStringEntry().enabled4) {
            advancedFloatArrayEntry.updateState();
         }

         if (worldRenderer.isActive()) {
            worldRenderer.applyIndexArray(localIntArrayEntry.getIndexArrayAsIndexArray());
         } else {
            worldRenderer.applyIndexArray(localIntArrayEntry.getIndexArray());
         }

         worldRenderer.applyServiceStringEntry(localIntArrayEntry.getServiceStringEntry());
         worldRenderer.applyIndex(
            AdvancedFloatArrayEntry.resolveIndexArray(advancedFloatArrayEntry)[0],
            AdvancedFloatArrayEntry.resolveIndexArray(advancedFloatArrayEntry)[1],
            AdvancedFloatArrayEntry.resolveIndexArray(advancedFloatArrayEntry)[2],
            AdvancedFloatArrayEntry.resolveIndexArray(advancedFloatArrayEntry)[3]
         );
         int count = LoadingManager.computeCount(localIntArrayEntry, blockState, iBlockAccess, blockPos, baseIBlockStateEntry);
         if (!localIntArrayEntry.isActive2() && count == -1) {
            if (enabled) {
               worldRenderer.applyFloat(1.0F, 1.0F, 1.0F, AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[0], 4);
               worldRenderer.applyFloat(1.0F, 1.0F, 1.0F, AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[1], 3);
               worldRenderer.applyFloat(1.0F, 1.0F, 1.0F, AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[2], 2);
               worldRenderer.applyFloat(1.0F, 1.0F, 1.0F, AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[3], 1);
            } else {
               worldRenderer.applyFloatByFloat(
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[0],
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[0],
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[0],
                  4
               );
               worldRenderer.applyFloatByFloat(
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[1],
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[1],
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[1],
                  3
               );
               worldRenderer.applyFloatByFloat(
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[2],
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[2],
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[2],
                  2
               );
               worldRenderer.applyFloatByFloat(
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[3],
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[3],
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[3],
                  1
               );
            }
         } else {
            if (count == -1) {
               count = block.processAction(iBlockAccess, blockPos, localIntArrayEntry.getCount());
            }

            if (EntityRenderer.enabled5) {
               count = SpecialLoggerEntry.computeCount(count);
            }

            float factor2 = (float)(count >> 16 & 0xFF) / 255.0F;
            float factor3 = (float)(count >> 8 & 0xFF) / 255.0F;
            float factor4 = (float)(count & 0xFF) / 255.0F;
            if (enabled) {
               worldRenderer.applyFloat(factor2, factor3, factor4, AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[0], 4);
               worldRenderer.applyFloat(factor2, factor3, factor4, AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[1], 3);
               worldRenderer.applyFloat(factor2, factor3, factor4, AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[2], 2);
               worldRenderer.applyFloat(factor2, factor3, factor4, AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[3], 1);
            } else {
               worldRenderer.applyFloatByFloat(
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[0] * factor2,
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[0] * factor3,
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[0] * factor4,
                  4
               );
               worldRenderer.applyFloatByFloat(
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[1] * factor2,
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[1] * factor3,
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[1] * factor4,
                  3
               );
               worldRenderer.applyFloatByFloat(
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[2] * factor2,
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[2] * factor3,
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[2] * factor4,
                  2
               );
               worldRenderer.applyFloatByFloat(
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[3] * factor2,
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[3] * factor3,
                  AdvancedFloatArrayEntry.resolveFloatArray(advancedFloatArrayEntry)[3] * factor4,
                  1
               );
            }
         }

         worldRenderer.applyDoubleByDouble(coordX, coordY, coordZ);
      }
   }

   public boolean matchesIBlockAccess2(IBlockAccess iBlockAccess, IBakedModel iBakedModel, IBlockState blockState, BlockPos blockPos, WorldRenderer worldRenderer, boolean flag) {
      boolean flag2 = false;
      Block block = blockState.getBlock();
      boolean flag3 = false;
      if (XRay.activeState3) {
         XRayHideType xRayHideType = XRay.getXRayHideType(block, blockState, blockPos);
         if (xRayHideType == XRayHideType.HIDE) {
            return false;
         }

         flag3 = xRayHideType == XRayHideType.FORCE_VISIBLE;
      }

      BaseIBlockStateEntry baseIBlockStateEntry = worldRenderer.resolveBaseIBlockStateEntry(blockState, blockPos);
      BlockSolidType blockSolidType = worldRenderer.getBlockSolidType();

      for (EnumFacing enumFacing : EnumFacing.VALUES) {
         List list = iBakedModel.resolveList(enumFacing);
         if (!list.isEmpty()) {
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (flag3
               || !flag
               || block.matchesIBlockAccess(iBlockAccess, blockPos.getX() + enumFacing.getInt2(), blockPos.getY() + enumFacing.getInt3(), blockPos.getZ() + enumFacing.getInt(), enumFacing)) {
               int count = block.processAction(iBlockAccess, blockPos2);
               List list2 = zzzz47r4Customizer.resolveList(list, iBlockAccess, blockState, blockPos, enumFacing, blockSolidType, 0L, baseIBlockStateEntry);
               this.applyIBlockAccess(iBlockAccess, blockState, blockPos, enumFacing, count, false, worldRenderer, list2, baseIBlockStateEntry);
               flag2 = true;
            }
         }
      }

      List list3 = iBakedModel.getList();
      if (!list3.isEmpty()) {
         List list4 = zzzz47r4Customizer.resolveList(list3, iBlockAccess, blockState, blockPos, (EnumFacing)null, blockSolidType, 0L, baseIBlockStateEntry);
         this.applyIBlockAccess(iBlockAccess, blockState, blockPos, (EnumFacing)null, -1, true, worldRenderer, list4, baseIBlockStateEntry);
         flag2 = true;
      }

      return flag2;
   }

   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, IBakedModel iBakedModel, IBlockState blockState, BlockPos blockPos, WorldRenderer worldRenderer) {
      blockState.getBlock().processAction(iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return this.matchesIBlockAccessByIBlockAccess(iBlockAccess, iBakedModel, blockState, blockPos, worldRenderer, true);
   }

   public static float computeFactor(float factor2) {
      return factor2 == 0.2F ? factor : factor2;
   }
}
