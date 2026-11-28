package net.minecraft.client.renderer;

import net.minecraft.util.BlockPos;

public class DestroyBlockProgress {
   private final int miningPlayerEntId;
   private final BlockPos position;
   private int partialBlockDamage;
   private int creationCloudUpdateTick;

   public void setCloudUpdateTick(int n) {
      this.creationCloudUpdateTick = n;
   }

   public DestroyBlockProgress(int n, BlockPos pos) {
      this.miningPlayerEntId = n;
      this.position = pos;
   }

   public int getCreationCloudUpdateTick() {
      return this.creationCloudUpdateTick;
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public void setPartialBlockDamage(int n) {
      if (n > 10) {
         n = 10;
      }

      this.partialBlockDamage = n;
   }

   public int getPartialBlockDamage() {
      return this.partialBlockDamage;
   }
}
