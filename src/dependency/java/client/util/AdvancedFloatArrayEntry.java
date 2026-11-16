package client.util;

import client.setting.MovementGroupProperty;
import client.setting.RenderRangeProperty;
import java.util.BitSet;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class AdvancedFloatArrayEntry {
   public final float[] factorArray = new float[4];
   public final int[] countArray = new int[4];

   public static int[] resolveIndexArray(AdvancedFloatArrayEntry advancedFloatArrayEntry) {
      return advancedFloatArrayEntry.countArray;
   }

   private int computeCount(int count, int count2, int count3, int count4, float posX, float posX2, float posY, float posZ) {
      int count5 = (int)(
            (float)(count >> 16 & 0xFF) * posX + (float)(count2 >> 16 & 0xFF) * posX2 + (float)(count3 >> 16 & 0xFF) * posY + (float)(count4 >> 16 & 0xFF) * posZ
         )
         & 0xFF;
      int count6 = (int)((float)(count & 0xFF) * posX + (float)(count2 & 0xFF) * posX2 + (float)(count3 & 0xFF) * posY + (float)(count4 & 0xFF) * posZ) & 0xFF;
      return count5 << 16 | count6;
   }

   public void applyIBlockAccess(IBlockAccess iBlockAccess, Block block, BlockPos blockPos, EnumFacing enumFacing, float[] factorArray2, BitSet bitSet) {
      BlockPos blockPos2 = bitSet.get(0) ? blockPos.offset(enumFacing) : blockPos;
      MovementGroupProperty movementGroupProperty = MovementGroupProperty.resolveMovementGroupProperty(enumFacing);
      BlockPos blockPos3 = blockPos2.offset(movementGroupProperty.enumFacingArray[0]);
      BlockPos blockPos4 = blockPos2.offset(movementGroupProperty.enumFacingArray[1]);
      BlockPos blockPos5 = blockPos2.offset(movementGroupProperty.enumFacingArray[2]);
      BlockPos blockPos6 = blockPos2.offset(movementGroupProperty.enumFacingArray[3]);
      int count = block.processAction(iBlockAccess, blockPos3);
      int count2 = block.processAction(iBlockAccess, blockPos4);
      int count3 = block.processAction(iBlockAccess, blockPos5);
      int count4 = block.processAction(iBlockAccess, blockPos6);
      float factor = SharedFloatEntry.computeFactor(iBlockAccess.getBlockState(blockPos3).getBlock().getFactor());
      float factor2 = SharedFloatEntry.computeFactor(iBlockAccess.getBlockState(blockPos4).getBlock().getFactor());
      float factor3 = SharedFloatEntry.computeFactor(iBlockAccess.getBlockState(blockPos5).getBlock().getFactor());
      float factor4 = SharedFloatEntry.computeFactor(iBlockAccess.getBlockState(blockPos6).getBlock().getFactor());
      boolean state = iBlockAccess.getBlockState(blockPos3.offset(enumFacing)).getBlock().isState();
      boolean state2 = iBlockAccess.getBlockState(blockPos4.offset(enumFacing)).getBlock().isState();
      boolean state3 = iBlockAccess.getBlockState(blockPos5.offset(enumFacing)).getBlock().isState();
      boolean state4 = iBlockAccess.getBlockState(blockPos6.offset(enumFacing)).getBlock().isState();
      float factor5;
      int count5;
      if (!state3 && !state) {
         factor5 = factor;
         count5 = count;
      } else {
         BlockPos blockPos7 = blockPos3.offset(movementGroupProperty.enumFacingArray[2]);
         factor5 = SharedFloatEntry.computeFactor(iBlockAccess.getBlockState(blockPos7).getBlock().getFactor());
         count5 = block.processAction(iBlockAccess, blockPos7);
      }

      float factor6;
      int count6;
      if (!state4 && !state) {
         factor6 = factor;
         count6 = count;
      } else {
         BlockPos blockPos8 = blockPos3.offset(movementGroupProperty.enumFacingArray[3]);
         factor6 = SharedFloatEntry.computeFactor(iBlockAccess.getBlockState(blockPos8).getBlock().getFactor());
         count6 = block.processAction(iBlockAccess, blockPos8);
      }

      float factor7;
      int count7;
      if (!state3 && !state2) {
         factor7 = factor2;
         count7 = count2;
      } else {
         BlockPos blockPos9 = blockPos4.offset(movementGroupProperty.enumFacingArray[2]);
         factor7 = SharedFloatEntry.computeFactor(iBlockAccess.getBlockState(blockPos9).getBlock().getFactor());
         count7 = block.processAction(iBlockAccess, blockPos9);
      }

      float factor8;
      int count8;
      if (!state4 && !state2) {
         factor8 = factor2;
         count8 = count2;
      } else {
         BlockPos blockPos10 = blockPos4.offset(movementGroupProperty.enumFacingArray[3]);
         factor8 = SharedFloatEntry.computeFactor(iBlockAccess.getBlockState(blockPos10).getBlock().getFactor());
         count8 = block.processAction(iBlockAccess, blockPos10);
      }

      int count9 = block.processAction(iBlockAccess, blockPos);
      if (bitSet.get(0) || !iBlockAccess.getBlockState(blockPos.offset(enumFacing)).getBlock().processAction15()) {
         count9 = block.processAction(iBlockAccess, blockPos.offset(enumFacing));
      }

      float factor9 = SharedFloatEntry.computeFactor(
         bitSet.get(0) ? iBlockAccess.getBlockState(blockPos2).getBlock().getFactor() : iBlockAccess.getBlockState(blockPos).getBlock().getFactor()
      );
      RenderRangeProperty renderRangeProperty = RenderRangeProperty.resolveRenderRangeProperty(enumFacing);
      if (bitSet.get(1) && movementGroupProperty.enabled) {
         float factor10 = (factor4 + factor + factor6 + factor9) * 0.25F;
         float factor11 = (factor3 + factor + factor5 + factor9) * 0.25F;
         float factor12 = (factor3 + factor2 + factor7 + factor9) * 0.25F;
         float factor13 = (factor4 + factor2 + factor8 + factor9) * 0.25F;
         float factor14 = factorArray2[movementGroupProperty.combatColorPropertyArray4[0].count] * factorArray2[movementGroupProperty.combatColorPropertyArray4[1].count];
         float factor15 = factorArray2[movementGroupProperty.combatColorPropertyArray4[2].count] * factorArray2[movementGroupProperty.combatColorPropertyArray4[3].count];
         float factor16 = factorArray2[movementGroupProperty.combatColorPropertyArray4[4].count] * factorArray2[movementGroupProperty.combatColorPropertyArray4[5].count];
         float factor17 = factorArray2[movementGroupProperty.combatColorPropertyArray4[6].count] * factorArray2[movementGroupProperty.combatColorPropertyArray4[7].count];
         float factor18 = factorArray2[movementGroupProperty.combatColorPropertyArray[0].count] * factorArray2[movementGroupProperty.combatColorPropertyArray[1].count];
         float factor19 = factorArray2[movementGroupProperty.combatColorPropertyArray[2].count] * factorArray2[movementGroupProperty.combatColorPropertyArray[3].count];
         float factor20 = factorArray2[movementGroupProperty.combatColorPropertyArray[4].count] * factorArray2[movementGroupProperty.combatColorPropertyArray[5].count];
         float factor21 = factorArray2[movementGroupProperty.combatColorPropertyArray[6].count] * factorArray2[movementGroupProperty.combatColorPropertyArray[7].count];
         float factor22 = factorArray2[movementGroupProperty.combatColorPropertyArray2[0].count] * factorArray2[movementGroupProperty.combatColorPropertyArray2[1].count];
         float factor23 = factorArray2[movementGroupProperty.combatColorPropertyArray2[2].count] * factorArray2[movementGroupProperty.combatColorPropertyArray2[3].count];
         float factor24 = factorArray2[movementGroupProperty.combatColorPropertyArray2[4].count] * factorArray2[movementGroupProperty.combatColorPropertyArray2[5].count];
         float factor25 = factorArray2[movementGroupProperty.combatColorPropertyArray2[6].count] * factorArray2[movementGroupProperty.combatColorPropertyArray2[7].count];
         float factor26 = factorArray2[movementGroupProperty.combatColorPropertyArray3[0].count] * factorArray2[movementGroupProperty.combatColorPropertyArray3[1].count];
         float factor27 = factorArray2[movementGroupProperty.combatColorPropertyArray3[2].count] * factorArray2[movementGroupProperty.combatColorPropertyArray3[3].count];
         float factor28 = factorArray2[movementGroupProperty.combatColorPropertyArray3[4].count] * factorArray2[movementGroupProperty.combatColorPropertyArray3[5].count];
         float factor29 = factorArray2[movementGroupProperty.combatColorPropertyArray3[6].count] * factorArray2[movementGroupProperty.combatColorPropertyArray3[7].count];
         this.factorArray[RenderRangeProperty.computeCountByRenderRangePrope(renderRangeProperty)] = factor10 * factor14 + factor11 * factor15 + factor12 * factor16 + factor13 * factor17;
         this.factorArray[RenderRangeProperty.computeCount(renderRangeProperty)] = factor10 * factor18 + factor11 * factor19 + factor12 * factor20 + factor13 * factor21;
         this.factorArray[RenderRangeProperty.computeCount3(renderRangeProperty)] = factor10 * factor22 + factor11 * factor23 + factor12 * factor24 + factor13 * factor25;
         this.factorArray[RenderRangeProperty.computeCount2(renderRangeProperty)] = factor10 * factor26 + factor11 * factor27 + factor12 * factor28 + factor13 * factor29;
         int count10 = this.computeCount(count4, count, count6, count9);
         int count11 = this.computeCount(count3, count, count5, count9);
         int count12 = this.computeCount(count3, count2, count7, count9);
         int count13 = this.computeCount(count4, count2, count8, count9);
         this.countArray[RenderRangeProperty.computeCountByRenderRangePrope(renderRangeProperty)] = this.computeCount(count10, count11, count12, count13, factor14, factor15, factor16, factor17);
         this.countArray[RenderRangeProperty.computeCount(renderRangeProperty)] = this.computeCount(count10, count11, count12, count13, factor18, factor19, factor20, factor21);
         this.countArray[RenderRangeProperty.computeCount3(renderRangeProperty)] = this.computeCount(count10, count11, count12, count13, factor22, factor23, factor24, factor25);
         this.countArray[RenderRangeProperty.computeCount2(renderRangeProperty)] = this.computeCount(count10, count11, count12, count13, factor26, factor27, factor28, factor29);
      } else {
         float factor30 = (factor4 + factor + factor6 + factor9) * 0.25F;
         float factor31 = (factor3 + factor + factor5 + factor9) * 0.25F;
         float factor32 = (factor3 + factor2 + factor7 + factor9) * 0.25F;
         float factor33 = (factor4 + factor2 + factor8 + factor9) * 0.25F;
         this.countArray[RenderRangeProperty.computeCountByRenderRangePrope(renderRangeProperty)] = this.computeCount(count4, count, count6, count9);
         this.countArray[RenderRangeProperty.computeCount(renderRangeProperty)] = this.computeCount(count3, count, count5, count9);
         this.countArray[RenderRangeProperty.computeCount3(renderRangeProperty)] = this.computeCount(count3, count2, count7, count9);
         this.countArray[RenderRangeProperty.computeCount2(renderRangeProperty)] = this.computeCount(count4, count2, count8, count9);
         this.factorArray[RenderRangeProperty.computeCountByRenderRangePrope(renderRangeProperty)] = factor30;
         this.factorArray[RenderRangeProperty.computeCount(renderRangeProperty)] = factor31;
         this.factorArray[RenderRangeProperty.computeCount3(renderRangeProperty)] = factor32;
         this.factorArray[RenderRangeProperty.computeCount2(renderRangeProperty)] = factor33;
      }
   }

   public void updateState() {
      this.countArray[0] = this.countArray[0] | 240;
      this.countArray[1] = this.countArray[1] | 240;
      this.countArray[2] = this.countArray[2] | 240;
      this.countArray[3] = this.countArray[3] | 240;
      this.factorArray[0] = 1.0F;
      this.factorArray[1] = 1.0F;
      this.factorArray[2] = 1.0F;
      this.factorArray[3] = 1.0F;
   }

   private int computeCount(int count, int count2, int count3, int count4) {
      int count5 = count;
      int count6 = count2;
      int count7 = count3;
      if (count == 0) {
         count5 = count4;
      }

      if (count2 == 0) {
         count6 = count4;
      }

      if (count3 == 0) {
         count7 = count4;
      }

      return count5 + count6 + count7 + count4 >> 2 & 16711935;
   }

   public static float[] resolveFloatArray(AdvancedFloatArrayEntry advancedFloatArrayEntry) {
      return advancedFloatArrayEntry.factorArray;
   }

   public AdvancedFloatArrayEntry() {
      this((SharedFloatEntry)null);
   }

   public AdvancedFloatArrayEntry(SharedFloatEntry sharedFloatEntry) {
   }
}
