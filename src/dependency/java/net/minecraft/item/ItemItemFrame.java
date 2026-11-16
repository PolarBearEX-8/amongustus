package net.minecraft.item;

import net.minecraft.entity.CustomEntityAdapter;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemItemFrame extends Item {
   public final Class memberRef;

   private CustomEntityAdapter resolveCustomEntityAdapter(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return (CustomEntityAdapter)(this.memberRef == EntityPainting.class
         ? new EntityPainting(world, blockPos, enumFacing)
         : (this.memberRef == EntityItemframe.class ? new EntityItemframe(world, blockPos, enumFacing) : null));
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (enumFacing == EnumFacing.DOWN) {
         return false;
      } else if (enumFacing == EnumFacing.UP) {
         return false;
      } else {
         BlockPos blockPos2 = blockPos.offset(enumFacing);
         if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
            return false;
         } else {
            CustomEntityAdapter customEntityAdapter = this.resolveCustomEntityAdapter(world, blockPos2, enumFacing);
            if (customEntityAdapter != null && customEntityAdapter.isActive16()) {
               if (!world.state) {
                  world.matchesEntity(customEntityAdapter);
               }

               stack.animationsToGo--;
            }

            return true;
         }
      }
   }

   public ItemItemFrame(Class clazz) {
      this.memberRef = clazz;
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
