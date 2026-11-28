package net.minecraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ITickable;

public class TileEntityEnderChest extends TileEntity implements ITickable {
   public float lidAngle;
   public float prevLidAngle;
   public int numPlayersUsing;
   private int ticksSinceSync;

   @Override
   public boolean receiveClientEvent(int n, int n2) {
      if (n == 1) {
         this.numPlayersUsing = n2;
         return true;
      } else {
         return super.receiveClientEvent(n, n2);
      }
   }

   public void openChest() {
      this.numPlayersUsing++;
      this.worldObj.addBlockEvent(this.pos, Blocks.ender_chest, 1, this.numPlayersUsing);
   }

   @Override
   public void invalidate() {
      this.updateContainingBlockInfo();
      super.invalidate();
   }

   public boolean canBeUsed(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.pos) != this
         ? false
         : player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void update() {
      if (++this.ticksSinceSync % 20 * 4 == 0) {
         this.worldObj.addBlockEvent(this.pos, Blocks.ender_chest, 1, this.numPlayersUsing);
      }

      this.prevLidAngle = this.lidAngle;
      int x = this.pos.getX();
      int y = this.pos.getY();
      int z = this.pos.getZ();
      float f = 0.1F;
      if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
         double d = (double)x + 0.5;
         double d2 = (double)z + 0.5;
         this.worldObj.playSoundEffect(d, (double)y + 0.5, d2, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
      }

      if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
         float lidAngle2 = this.lidAngle;
         if (this.numPlayersUsing > 0) {
            this.lidAngle += f;
         } else {
            this.lidAngle -= f;
         }

         if (this.lidAngle > 1.0F) {
            this.lidAngle = 1.0F;
         }

         float f2 = 0.5F;
         if (this.lidAngle < f2 && lidAngle2 >= f2) {
            double d3 = (double)x + 0.5;
            double d4 = (double)z + 0.5;
            this.worldObj.playSoundEffect(d3, (double)y + 0.5, d4, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
         }

         if (this.lidAngle < 0.0F) {
            this.lidAngle = 0.0F;
         }
      }
   }

   public void closeChest() {
      this.numPlayersUsing--;
      this.worldObj.addBlockEvent(this.pos, Blocks.ender_chest, 1, this.numPlayersUsing);
   }
}
