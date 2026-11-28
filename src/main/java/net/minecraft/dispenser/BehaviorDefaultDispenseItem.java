package net.minecraft.dispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BehaviorDefaultDispenseItem implements IBehaviorDispenseItem {
   public static void doDispense(World world, ItemStack stack, int n, EnumFacing facing, IPosition position) {
      double x = position.getX();
      double y = position.getY();
      double z = position.getZ();
      if (facing.getAxis() == EnumFacing.Axis.Y) {
         y -= 0.125;
      } else {
         y -= 0.15625;
      }

      EntityItem entityItem = new EntityItem(world, x, y, z, stack);
      double d = world.rand.nextDouble() * 0.1 + 0.2;
      entityItem.motionX = (double)facing.getFrontOffsetX() * d;
      entityItem.motionY = 0.2F;
      entityItem.motionZ = (double)facing.getFrontOffsetZ() * d;
      entityItem.motionX = entityItem.motionX + world.rand.nextGaussian() * 0.0075F * (double)n;
      entityItem.motionY = entityItem.motionY + world.rand.nextGaussian() * 0.0075F * (double)n;
      entityItem.motionZ = entityItem.motionZ + world.rand.nextGaussian() * 0.0075F * (double)n;
      world.spawnEntityInWorld(entityItem);
   }

   public void playDispenseSound(IBlockSource blockSource) {
      blockSource.getWorld().playAuxSFX(1000, blockSource.getBlockPos(), 0);
   }

   public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
      EnumFacing facing = BlockDispenser.getFacing(blockSource.getBlockMetadata());
      IPosition dispensePosition = BlockDispenser.getDispensePosition(blockSource);
      ItemStack stack2 = stack.splitStack(1);
      doDispense(blockSource.getWorld(), stack2, 6, facing, dispensePosition);
      return stack;
   }

   public void spawnDispenseParticles(IBlockSource blockSource, EnumFacing facing) {
      blockSource.getWorld().playAuxSFX(2000, blockSource.getBlockPos(), this.getWorldEventDataFrom(facing));
   }

   @Override
   public final ItemStack dispense(IBlockSource blockSource, ItemStack stack) {
      ItemStack stack2 = this.dispenseStack(blockSource, stack);
      this.playDispenseSound(blockSource);
      this.spawnDispenseParticles(blockSource, BlockDispenser.getFacing(blockSource.getBlockMetadata()));
      return stack2;
   }

   private int getWorldEventDataFrom(EnumFacing facing) {
      return facing.getFrontOffsetX() + 1 + (facing.getFrontOffsetZ() + 1) * 3;
   }
}
