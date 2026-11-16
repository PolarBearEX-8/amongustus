package net.minecraft.block;

import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;

public final class AuxiliaryBlockPosAdapter extends BlockPos {
   public int count9;
   public int count11;
   public int count10;

   public static int computeCountByAuxiliaryBlockPo(AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter, int count) {
      return auxiliaryBlockPosAdapter.count11 = count;
   }

   public static int computeCount(AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter, int count) {
      return auxiliaryBlockPosAdapter.count10 = count;
   }

   @Override
   public int getX() {
      return this.count9;
   }

   @Override
   public Vec3i resolveVec3i(Vec3i vec) {
      return super.resolveBlockPos(vec);
   }

   @Override
   public int getZ() {
      return this.count10;
   }

   public static int computeCount2(AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter, int count) {
      return auxiliaryBlockPosAdapter.count9 = count;
   }

   public AuxiliaryBlockPosAdapter resolveAuxiliaryBlockPosAdapter(int count, int count2, int count3) {
      this.count9 = count;
      this.count11 = count2;
      this.count10 = count3;
      return this;
   }

   @Override
   public int getY() {
      return this.count11;
   }

   public AuxiliaryBlockPosAdapter() {
      this(0, 0, 0);
   }

   public AuxiliaryBlockPosAdapter(int count, int count2, int count3) {
      super(0, 0, 0);
      this.count9 = count;
      this.count11 = count2;
      this.count10 = count3;
   }
}
