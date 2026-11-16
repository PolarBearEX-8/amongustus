package net.minecraft.item;

import client.setting.MovementNumberProperty;
import client.setting.MovementProfileProperty;
import client.util.IBlockState;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.SpecialBlockAdapter;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemCommandBlockMinecart extends Item {
   public static final CustomItemCallback customItemCallback = new IronStaff();
   public final MovementNumberProperty movementNumberProperty;

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (SpecialBlockAdapter.matchesIBlockState(blockState)) {
         if (!world.state) {
            MovementProfileProperty movementProfileProperty = blockState.getBlock() instanceof SpecialBlockAdapter
               ? (MovementProfileProperty)blockState.getComparable(((SpecialBlockAdapter)blockState.getBlock()).getIProperty())
               : MovementProfileProperty.NORTH_SOUTH;
            double offset = 0.0;
            if (movementProfileProperty.isActive()) {
               offset = 0.5;
            }

            EntityMinecart entityMinecart = EntityMinecart.resolveEntityMinecart(
               world, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.0625 + offset, (double)blockPos.getZ() + 0.5, this.movementNumberProperty
            );
            if (stack.isActive()) {
               entityMinecart.setLabelText(stack.getString());
            }

            world.matchesEntity(entityMinecart);
         }

         stack.animationsToGo--;
         return true;
      } else {
         return false;
      }
   }

   public static MovementNumberProperty resolveMovementNumberProperty(ItemCommandBlockMinecart itemCommandBlockMinecart) {
      return itemCommandBlockMinecart.movementNumberProperty;
   }

   public ItemCommandBlockMinecart(MovementNumberProperty movementNumberProperty2) {
      this.indexOffset1 = 1;
      this.movementNumberProperty = movementNumberProperty2;
      this.processAction(ItemGroupManager.itemGroupManager12);
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(this, customItemCallback);
   }
}
