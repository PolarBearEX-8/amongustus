package net.minecraft.util;

import client.util.ExtendedIterableAdapter;
import client.util.InternalIterableAdapter;
import net.minecraft.entity.Entity;

public class BlockPos extends Vec3i {
   public static final BlockPos origin = new BlockPos(0, 0, 0);
   public static final int count5 = 1 + MathHelper.computeCountByIndex(MathHelper.processAction(30000000));
   public static final int count6 = count5;
   public static final int count4 = 64 - count5 - count6;
   public static final int count8 = 0 + count6;
   public static final int count7 = count8 + count4;
   public static final long timestamp = (1L << count5) - 1L;
   public static final long timestamp3 = (1L << count4) - 1L;
   public static final long timestamp2 = (1L << count6) - 1L;

   public BlockPos resolveBlockPos4(int count) {
      return count == 0 ? this : new BlockPos(this.getX(), this.getY() - count, this.getZ());
   }

   public BlockPos getBlockPos6() {
      return new BlockPos(this.getX() - 1, this.getY() + 1, this.getZ());
   }

   public BlockPos add(int count, int count2, int count3) {
      return count == 0 && count2 == 0 && count3 == 0 ? this : new BlockPos(this.getX() + count, this.getY() + count2, this.getZ() + count3);
   }

   public boolean checkBlockPos(BlockPos blockPos) {
      return this.getX() == blockPos.getX() && this.getZ() == blockPos.getZ();
   }

   public BlockPos resolveBlockPos(Vec3i vec) {
      return new BlockPos(
         this.getY() * vec.getZ() - this.getZ() * vec.getY(),
         this.getZ() * vec.getX() - this.getX() * vec.getZ(),
         this.getX() * vec.getY() - this.getY() * vec.getX()
      );
   }

   public BlockPos getBlockPos() {
      return new BlockPos(this.getX() - 1, this.getY(), this.getZ());
   }

   public BlockPos resolveBlockPosByIndex(int count) {
      return count == 0 ? this : new BlockPos(this.getX() + count, this.getY(), this.getZ());
   }

   public static BlockPos resolveBlockPos(long value) {
      int count = (int)(value << 64 - count7 - count5 >> 64 - count5);
      int count2 = (int)(value << 64 - count8 - count4 >> 64 - count4);
      int count3 = (int)(value << 64 - count6 >> 64 - count6);
      return new BlockPos(count, count2, count3);
   }

   public BlockPos getBlockPos4() {
      return new BlockPos(this.getX(), this.getY() + 1, this.getZ() + 1);
   }

   public BlockPos offset(EnumFacing enumFacing) {
      return new BlockPos(this.getX() + enumFacing.getInt2(), this.getY() + enumFacing.getInt3(), this.getZ() + enumFacing.getInt());
   }

   public BlockPos getBlockPos(Vec3i vec) {
      return vec.getX() == 0 && vec.getY() == 0 && vec.getZ() == 0
         ? this
         : new BlockPos(this.getX() - vec.getX(), this.getY() - vec.getY(), this.getZ() - vec.getZ());
   }

   public BlockPos resolveBlockPos(int count) {
      return count == 0 ? this : new BlockPos(this.getX(), this.getY(), this.getZ() + count);
   }

   public boolean checkBlockPos2(BlockPos blockPos) {
      return this.getX() == blockPos.getX() && this.getY() == blockPos.getY() && this.getZ() == blockPos.getZ();
   }

   public BlockPos getBlockPos2(Vec3i vec) {
      return vec.getX() == 0 && vec.getY() == 0 && vec.getZ() == 0
         ? this
         : new BlockPos(this.getX() + vec.getX(), this.getY() + vec.getY(), this.getZ() + vec.getZ());
   }

   public BlockPos up() {
      return new BlockPos(this.getX(), this.getY() + 1, this.getZ());
   }

   public BlockPos getBlockPos5() {
      return new BlockPos(this.getX() + 1, this.getY() + 1, this.getZ());
   }

   public BlockPos resolveBlockPos3(int count) {
      return count == 0 ? this : new BlockPos(this.getX(), this.getY(), this.getZ() - count);
   }

   public BlockPos getBlockPos7() {
      return new BlockPos(this.getX(), this.getY() + 1, this.getZ() - 1);
   }

   @Override
   public Vec3i resolveVec3i(Vec3i vec) {
      return this.resolveBlockPos(vec);
   }

   public static Iterable resolveIterableByBlockPos(BlockPos blockPos, BlockPos blockPos2) {
      BlockPos blockPos3 = new BlockPos(Math.min(blockPos.getX(), blockPos2.getX()), Math.min(blockPos.getY(), blockPos2.getY()), Math.min(blockPos.getZ(), blockPos2.getZ()));
      BlockPos blockPos4 = new BlockPos(Math.max(blockPos.getX(), blockPos2.getX()), Math.max(blockPos.getY(), blockPos2.getY()), Math.max(blockPos.getZ(), blockPos2.getZ()));
      return new InternalIterableAdapter(blockPos3, blockPos4);
   }

   public BlockPos getBlockPos3() {
      return new BlockPos(this.getX() + 1, this.getY(), this.getZ());
   }

   public BlockPos resolveBlockPos5(int count) {
      return count == 0 ? this : new BlockPos(this.getX() - count, this.getY(), this.getZ());
   }

   public long getTimestamp() {
      return ((long)this.getX() & timestamp) << count7 | ((long)this.getY() & timestamp3) << count8 | ((long)this.getZ() & timestamp2) << 0;
   }

   public BlockPos getBlockPos(EnumFacing enumFacing, int count) {
      return count == 0 ? this : new BlockPos(this.getX() + enumFacing.getInt2() * count, this.getY() + enumFacing.getInt3() * count, this.getZ() + enumFacing.getInt() * count);
   }

   public BlockPos down() {
      return new BlockPos(this.getX(), this.getY() - 1, this.getZ());
   }

   public BlockPos getBlockPos2() {
      return new BlockPos(this.getX(), this.getY(), this.getZ() + 1);
   }

   public BlockPos resolveBlockPos(double posX, double posY, double posZ) {
      return posX == 0.0 && posY == 0.0 && posZ == 0.0
         ? this
         : new BlockPos((double)this.getX() + posX, (double)this.getY() + posY, (double)this.getZ() + posZ);
   }

   public BlockPos getBlockPosAsBlockPos() {
      return new BlockPos(this.getX(), this.getY(), this.getZ() - 1);
   }

   public static Iterable resolveIterable(BlockPos blockPos, BlockPos blockPos2) {
      BlockPos blockPos3 = new BlockPos(Math.min(blockPos.getX(), blockPos2.getX()), Math.min(blockPos.getY(), blockPos2.getY()), Math.min(blockPos.getZ(), blockPos2.getZ()));
      BlockPos blockPos4 = new BlockPos(Math.max(blockPos.getX(), blockPos2.getX()), Math.max(blockPos.getY(), blockPos2.getY()), Math.max(blockPos.getZ(), blockPos2.getZ()));
      return new ExtendedIterableAdapter(blockPos3, blockPos4);
   }

   public BlockPos resolveBlockPos2(int count) {
      return count == 0 ? this : new BlockPos(this.getX(), this.getY() + count, this.getZ());
   }

   public BlockPos(Entity entity) {
      this(entity.posX, entity.posY, entity.posZ);
   }

   public BlockPos(double posX, double posY, double posZ) {
      super(posX, posY, posZ);
   }

   public BlockPos(Vec3i vec) {
      this(vec.getX(), vec.getY(), vec.getZ());
   }

   public BlockPos(Vec3 vec) {
      this(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public BlockPos(int count, int count2, int count3) {
      super(count, count2, count3);
   }
}
