package client.util;

import net.minecraft.item.DiamondFragment;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.IChunkProvider;

public class EndManager extends SecondaryFloatArrayEntry {
   @Override
   public boolean checkIndex(int count, int count2) {
      return this.world.resolveBlock(new BlockPos(count, 0, count2)).getMaterial().isActiveAsFlag();
   }

   @Override
   public void updateState() {
      this.invalidBiomeIdManager = new DiamondFragment(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager3, 0.0F);
      this.count = 1;
      this.enabled = true;
   }

   @Override
   public String getText() {
      return "_end";
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public IChunkProvider getIChunkProvider() {
      return new InternalRandomEntry(this.world, this.world.getTimestamp());
   }

   @Override
   public float getFactor() {
      return 8.0F;
   }

   @Override
   public boolean isActive2() {
      return false;
   }

   @Override
   public float[] resolveFloatArray(float factor, float factor2) {
      return null;
   }

   @Override
   public int getCount() {
      return 50;
   }

   @Override
   public Vec3 resolveVec3(float factor, float factor2) {
      float factor3 = MathHelper.clamp_float(MathHelper.cos(factor * (float) Math.PI * 2.0F) * 2.0F + 0.5F, 0.0F, 1.0F);
      float factor4 = (float)160 / 255.0F;
      float factor5 = (float)128 / 255.0F;
      float factor6 = (float)160 / 255.0F;
      float factor7 = factor4 * (factor3 * 0.0F + 0.15F);
      float factor8 = factor5 * (factor3 * 0.0F + 0.15F);
      float factor9 = factor6 * (factor3 * 0.0F + 0.15F);
      return new Vec3((double)factor7, (double)factor8, (double)factor9);
   }

   @Override
   public float computeFactor(long value, float factor) {
      return 0.0F;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public String getTextAsText() {
      return "The End";
   }

   @Override
   public boolean checkIndexByIndex(int count, int count2) {
      return true;
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(100, 50, 0);
   }
}
