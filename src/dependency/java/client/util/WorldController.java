package client.util;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldController {
   public double value;
   public double value2;

   public WorldController(double offset, double offset2) {
      this.value = offset;
      this.value2 = offset2;
   }

   public boolean matchesWorld(World world) {
      BlockPos blockPos = new BlockPos(this.value, 256.0, this.value2);

      while (blockPos.getY() > 0) {
         blockPos = blockPos.down();
         Material material = world.getBlockState(blockPos).getBlock().getMaterial();
         if (material != Material.coreItemEntry) {
            return !material.isActive2() && material != Material.material6;
         }
      }

      return false;
   }

   public void applyRandom(Random random, double posX, double posX2, double posY, double posZ) {
      this.value = MathHelper.nextDouble(random, posX, posY);
      this.value2 = MathHelper.nextDouble(random, posX2, posZ);
   }

   public boolean checkDouble(double posX, double posX2, double posY, double posZ) {
      boolean flag = false;
      if (this.value < posX) {
         this.value = posX;
         flag = true;
      } else if (this.value > posY) {
         this.value = posY;
         flag = true;
      }

      if (this.value2 < posX2) {
         this.value2 = posX2;
         flag = true;
      } else if (this.value2 > posZ) {
         this.value2 = posZ;
         flag = true;
      }

      return flag;
   }

   public void updateState() {
      double factor = (double)this.getFactor();
      this.value /= factor;
      this.value2 /= factor;
   }

   public WorldController() {
   }

   public float getFactor() {
      return MathHelper.sqrt_double(this.value * this.value + this.value2 * this.value2);
   }

   public void applyWorldController(WorldController worldController) {
      this.value = this.value - worldController.value;
      this.value2 = this.value2 - worldController.value2;
   }

   public double computeValue(WorldController worldController) {
      double offset = this.value - worldController.value;
      double offset2 = this.value2 - worldController.value2;
      return Math.sqrt(offset * offset + offset2 * offset2);
   }

   public int computeCount(World world) {
      BlockPos blockPos = new BlockPos(this.value, 256.0, this.value2);

      while (blockPos.getY() > 0) {
         blockPos = blockPos.down();
         if (world.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry) {
            return blockPos.getY() + 1;
         }
      }

      return 257;
   }
}
