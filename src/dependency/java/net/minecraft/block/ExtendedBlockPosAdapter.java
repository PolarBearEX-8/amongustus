package net.minecraft.block;

import client.util.SecondaryIterableAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;

public class ExtendedBlockPosAdapter extends BlockPos {
   public int count12;
   public int count9;
   public int count11;
   public int count10;
   public ExtendedBlockPosAdapter[] extendedBlockPosAdapterArray;
   public boolean enabled2;

   public void applyDouble(double posX, double posY, double posZ) {
      this.applyIndex(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY), MathHelper.ceiling_double_int(posZ));
   }

   @Override
   public BlockPos offset(EnumFacing enumFacing) {
      if (this.count10 <= 0) {
         return super.getBlockPos(enumFacing, 1);
      } else {
         if (this.extendedBlockPosAdapterArray == null) {
            this.extendedBlockPosAdapterArray = new ExtendedBlockPosAdapter[EnumFacing.VALUES.length];
         }

         if (this.enabled2) {
            this.updateState();
         }

         int indexOffset = enumFacing.getIndexOffset1();
         ExtendedBlockPosAdapter extendedBlockPosAdapter = this.extendedBlockPosAdapterArray[indexOffset];
         if (extendedBlockPosAdapter == null) {
            int count = this.count12 + enumFacing.getInt2();
            int count2 = this.count9 + enumFacing.getInt3();
            int count3 = this.count11 + enumFacing.getInt();
            extendedBlockPosAdapter = new ExtendedBlockPosAdapter(count, count2, count3, this.count10 - 1);
            this.extendedBlockPosAdapterArray[indexOffset] = extendedBlockPosAdapter;
         }

         return extendedBlockPosAdapter;
      }
   }

   @Override
   public int getX() {
      return this.count12;
   }

   private void updateState() {
      for (int index = 0; index < 6; index++) {
         ExtendedBlockPosAdapter extendedBlockPosAdapter = this.extendedBlockPosAdapterArray[index];
         if (extendedBlockPosAdapter != null) {
            EnumFacing enumFacing = EnumFacing.VALUES[index];
            int count = this.count12 + enumFacing.getInt2();
            int count2 = this.count9 + enumFacing.getInt3();
            int count3 = this.count11 + enumFacing.getInt();
            extendedBlockPosAdapter.applyIndex(count, count2, count3);
         }
      }

      this.enabled2 = false;
   }

   public static Iterable resolveIterable(int count, int count2, int count3, int count4, int count5, int count6) {
      BlockPos blockPos = new BlockPos(Math.min(count, count4), Math.min(count2, count5), Math.min(count3, count6));
      BlockPos blockPos2 = new BlockPos(Math.max(count, count4), Math.max(count2, count5), Math.max(count3, count6));
      return new SecondaryIterableAdapter(blockPos, blockPos2);
   }

   public ExtendedBlockPosAdapter resolveExtendedBlockPosAdapter(Vec3i vec) {
      this.applyIndex(vec.getX(), vec.getY(), vec.getZ());
      return this;
   }

   public BlockPos resolveBlockPos(EnumFacing enumFacing) {
      return this.offset(enumFacing);
   }

   @Override
   public BlockPos getBlockPos(EnumFacing enumFacing, int count) {
      return count == 1 ? this.offset(enumFacing) : super.getBlockPos(enumFacing, count);
   }

   public ExtendedBlockPosAdapter resolveExtendedBlockPosAdapter(int count, int count2, int count3) {
      this.applyIndex(count, count2, count3);
      return this;
   }

   @Override
   public int getZ() {
      return this.count11;
   }

   @Override
   public int getY() {
      return this.count9;
   }

   public void applyIndex(int count, int count2, int count3) {
      this.count12 = count;
      this.count9 = count2;
      this.count11 = count3;
      this.enabled2 = true;
   }

   public BlockPos getBlockPos8() {
      return new BlockPos(this.count12, this.count9, this.count11);
   }

   public ExtendedBlockPosAdapter(int count, int count2, int count3) {
      this(count, count2, count3, 0);
   }

   public ExtendedBlockPosAdapter(double posX, double posY, double posZ) {
      this(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY), MathHelper.ceiling_double_int(posZ));
   }

   public ExtendedBlockPosAdapter(int count, int count2, int count3, int count4) {
      super(0, 0, 0);
      this.count12 = count;
      this.count9 = count2;
      this.count11 = count3;
      this.count10 = count4;
   }
}
