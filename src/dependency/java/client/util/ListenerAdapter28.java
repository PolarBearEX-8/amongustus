package client.util;

import client.setting.CombatConfigProperty;
import client.setting.CombatGroupProperty;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ListenerAdapter28 implements IBlockAccess {
   public int count2;
   public int count;
   public AlertNotificationSound[][] alertNotificationSoundArray;
   public boolean enabled;
   public World world;

   @Override
   public IBlockState getBlockState(BlockPos blockPos) {
      if (blockPos.getY() >= 0 && blockPos.getY() < 256) {
         int count3 = (blockPos.getX() >> 4) - this.count2;
         int count4 = (blockPos.getZ() >> 4) - this.count;
         if (count3 >= 0 && count3 < this.alertNotificationSoundArray.length && count4 >= 0 && count4 < this.alertNotificationSoundArray[count3].length) {
            AlertNotificationSound alertNotificationSound = this.alertNotificationSoundArray[count3][count4];
            if (alertNotificationSound != null) {
               return alertNotificationSound.resolveIBlockState(blockPos);
            }
         }
      }

      return Blocks.air.getDefaultState();
   }

   @Override
   public int computeCount(BlockPos blockPos, EnumFacing enumFacing) {
      IBlockState blockState = this.getBlockState(blockPos);
      return blockState.getBlock().processAction2(this, blockPos, blockState, enumFacing);
   }

   @Override
   public TileEntity resolveTileEntity(BlockPos blockPos) {
      int count3 = (blockPos.getX() >> 4) - this.count2;
      int count4 = (blockPos.getZ() >> 4) - this.count;
      return this.alertNotificationSoundArray[count3][count4].resolveTileEntity(blockPos, CombatGroupProperty.IMMEDIATE);
   }

   private int computeCount(CombatConfigProperty combatConfigProperty, int count3, int count4, int count5) {
      if (combatConfigProperty == CombatConfigProperty.SKY && this.world.secondaryFloatArrayEntry.isEnabled()) {
         return 0;
      } else if (count4 >= 0 && count4 < 256) {
         if (this.getIBlockState(count3, count4, count5).getBlock().isState25()) {
            int count6 = 0;

            for (EnumFacing enumFacing : EnumFacing.getEntries()) {
               int count7 = this.computeCountByCombatConfigProp(combatConfigProperty, count3 + enumFacing.getInt2(), count4 + enumFacing.getInt3(), count5 + enumFacing.getInt());
               if (count7 > count6) {
                  count6 = count7;
               }

               if (count6 >= 15) {
                  return count6;
               }
            }

            return count6;
         } else {
            int count8 = (count3 >> 4) - this.count2;
            int count9 = (count5 >> 4) - this.count;
            return this.alertNotificationSoundArray[count8][count9].computeCount(combatConfigProperty, count3, count4, count5);
         }
      } else {
         return combatConfigProperty.defaultLightValue;
      }
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public int computeCount(int count3, int count4, int count5, int count6) {
      int count7 = this.computeCount(CombatConfigProperty.SKY, count3, count4, count5);
      int count8 = this.computeCount(CombatConfigProperty.BLOCK, count3, count4, count5);
      if (count8 < count6) {
         count8 = count6;
      }

      return count7 << 20 | count8 << 4;
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(BlockPos blockPos) {
      return this.world.resolveBiomeIDIsOutOfBoundsManager(blockPos);
   }

   public int computeCountByCombatConfigProp(CombatConfigProperty combatConfigProperty, int count3, int count4, int count5) {
      if (count4 >= 0 && count4 < 256) {
         int count6 = (count3 >> 4) - this.count2;
         int count7 = (count5 >> 4) - this.count;
         return this.alertNotificationSoundArray[count6][count7].computeCount(combatConfigProperty, count3, count4, count5);
      } else {
         return combatConfigProperty.defaultLightValue;
      }
   }

   @Override
   public InfoManager getInfoManager() {
      return this.world.getInfoManager();
   }

   @Override
   public boolean checkCondition(int count3, int count4, int count5) {
      return this.getIBlockState(count3, count4, count5).getBlock().getMaterial() == Material.coreItemEntry;
   }

   @Override
   public IBlockState getIBlockState(int count3, int count4, int count5) {
      if (count4 >= 0 && count4 < 256) {
         int count6 = (count3 >> 4) - this.count2;
         int count7 = (count5 >> 4) - this.count;
         if (count6 >= 0 && count6 < this.alertNotificationSoundArray.length && count7 >= 0 && count7 < this.alertNotificationSoundArray[count6].length) {
            AlertNotificationSound alertNotificationSound = this.alertNotificationSoundArray[count6][count7];
            if (alertNotificationSound != null) {
               return alertNotificationSound.resolveIBlockState(count3, count4, count5);
            }
         }
      }

      return Blocks.air.getDefaultState();
   }

   @Override
   public IBlockState getIBlockState2(double posX, double posY, double posZ) {
      int count3 = MathHelper.ceiling_double_int(posX);
      int count4 = MathHelper.ceiling_double_int(posY);
      int count5 = MathHelper.ceiling_double_int(posZ);
      if (count4 >= 0 && count4 < 256) {
         int count6 = (count3 >> 4) - this.count2;
         int count7 = (count5 >> 4) - this.count;
         if (count6 >= 0 && count6 < this.alertNotificationSoundArray.length && count7 >= 0 && count7 < this.alertNotificationSoundArray[count6].length) {
            AlertNotificationSound alertNotificationSound = this.alertNotificationSoundArray[count6][count7];
            if (alertNotificationSound != null) {
               return alertNotificationSound.resolveIBlockState(count3, count4, count5);
            }
         }
      }

      return Blocks.air.getDefaultState();
   }

   @Override
   public boolean isAirBlock(BlockPos blockPos) {
      return this.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry;
   }

   public ListenerAdapter28(World world2, int count3, int count4, int count5, int count6, int count7, int count8, int count9) {
      this.world = world2;
      this.count2 = count3 - count9 >> 4;
      this.count = count5 - count9 >> 4;
      int count10 = count6 + count9 >> 4;
      int count11 = count8 + count9 >> 4;
      this.alertNotificationSoundArray = new AlertNotificationSound[count10 - this.count2 + 1][count11 - this.count + 1];
      this.enabled = true;

      for (int index = this.count2; index <= count10; index++) {
         for (int index2 = this.count; index2 <= count11; index2++) {
            this.alertNotificationSoundArray[index - this.count2][index2 - this.count] = world2.getLoggerEntry2(index, index2);
         }
      }

      for (int index3 = count3 >> 4; index3 <= count6 >> 4; index3++) {
         for (int index4 = count5 >> 4; index4 <= count8 >> 4; index4++) {
            AlertNotificationSound alertNotificationSound = this.alertNotificationSoundArray[index3 - this.count2][index4 - this.count];
            if (alertNotificationSound != null && !alertNotificationSound.checkIndex(count4, count7)) {
               this.enabled = false;
            }
         }
      }
   }
}
