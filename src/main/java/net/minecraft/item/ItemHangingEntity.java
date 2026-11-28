package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemHangingEntity extends Item {
   private final Class hangingEntityClass;

   private EntityHanging createEntity(World world, BlockPos pos, EnumFacing facing) {
      return (EntityHanging)(this.hangingEntityClass == EntityPainting.class
         ? new EntityPainting(world, pos, facing)
         : (this.hangingEntityClass == EntityItemFrame.class ? new EntityItemFrame(world, pos, facing) : null));
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (facing == EnumFacing.DOWN) {
         return false;
      } else if (facing == EnumFacing.UP) {
         return false;
      } else {
         BlockPos pos2 = pos.offset(facing);
         if (!player.canPlayerEdit(pos2, facing, stack)) {
            return false;
         } else {
            EntityHanging entityHanging = this.createEntity(world, pos2, facing);
            if (entityHanging != null && entityHanging.onValidSurface()) {
               if (!world.isRemote) {
                  world.spawnEntityInWorld(entityHanging);
               }

               stack.stackSize--;
            }

            return true;
         }
      }
   }

   public ItemHangingEntity(Class clazz) {
      this.hangingEntityClass = clazz;
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }
}
