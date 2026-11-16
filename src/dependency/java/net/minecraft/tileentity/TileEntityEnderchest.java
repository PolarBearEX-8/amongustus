package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;

public class TileEntityEnderchest extends TileEntity implements PreUpdateConsumer {
   public float factor2;
   public float factor;
   public int count2;
   public int count3;

   @Override
   public void updateState3() {
      this.updateState4();
      super.updateState3();
   }

   public void updateState2() {
      this.count2++;
      this.world.applyBlockPosByBlockPos(this.blockPos, Blocks.block60, 1, this.count2);
   }

   @Override
   public boolean checkIndex(int count, int count4) {
      if (count == 1) {
         this.count2 = count4;
         return true;
      } else {
         return super.checkIndex(count, count4);
      }
   }

   public void updateState6() {
      this.count2--;
      this.world.applyBlockPosByBlockPos(this.blockPos, Blocks.block60, 1, this.count2);
   }

   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      return this.world.resolveTileEntity(this.blockPos) != this
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void updateState() {
      if (++this.count3 % 20 * 4 == 0) {
         this.world.applyBlockPosByBlockPos(this.blockPos, Blocks.block60, 1, this.count2);
      }

      this.factor = this.factor2;
      int coordX = this.blockPos.getX();
      int coordY = this.blockPos.getY();
      int coordZ = this.blockPos.getZ();
      float factor3 = 0.1F;
      if (this.count2 > 0 && this.factor2 == 0.0F) {
         double offset = (double)coordX + 0.5;
         double offset2 = (double)coordZ + 0.5;
         this.world.applyText(offset, (double)coordY + 0.5, offset2, "random.chestopen", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
      }

      if (this.count2 == 0 && this.factor2 > 0.0F || this.count2 > 0 && this.factor2 < 1.0F) {
         float factor4 = this.factor2;
         if (this.count2 > 0) {
            this.factor2 += factor3;
         } else {
            this.factor2 -= factor3;
         }

         if (this.factor2 > 1.0F) {
            this.factor2 = 1.0F;
         }

         float factor5 = 0.5F;
         if (this.factor2 < factor5 && factor4 >= factor5) {
            double offset3 = (double)coordX + 0.5;
            double offset4 = (double)coordZ + 0.5;
            this.world.applyText(offset3, (double)coordY + 0.5, offset4, "random.chestclosed", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
         }

         if (this.factor2 < 0.0F) {
            this.factor2 = 0.0F;
         }
      }
   }
}
