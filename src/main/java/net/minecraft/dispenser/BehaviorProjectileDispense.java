package net.minecraft.dispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BehaviorProjectileDispense extends BehaviorDefaultDispenseItem {
   @Override
   public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
      World world = blockSource.getWorld();
      IPosition dispensePosition = BlockDispenser.getDispensePosition(blockSource);
      EnumFacing facing = BlockDispenser.getFacing(blockSource.getBlockMetadata());
      IProjectile projectileEntity = this.getProjectileEntity(world, dispensePosition);
      projectileEntity.setThrowableHeading(
         (double)facing.getFrontOffsetX(), (double)((float)facing.getFrontOffsetY() + 0.1F), (double)facing.getFrontOffsetZ(), this.getProjectileVelocity(), this.getProjectileInaccuracy()
      );
      world.spawnEntityInWorld((Entity)projectileEntity);
      stack.splitStack(1);
      return stack;
   }

   public float getProjectileInaccuracy() {
      return 6.0F;
   }

   public abstract IProjectile getProjectileEntity(World world, IPosition position);

   public float getProjectileVelocity() {
      return 1.1F;
   }

   @Override
   public void playDispenseSound(IBlockSource blockSource) {
      blockSource.getWorld().playAuxSFX(1002, blockSource.getBlockPos(), 0);
   }
}
