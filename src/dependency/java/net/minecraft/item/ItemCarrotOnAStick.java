package net.minecraft.item;

import client.util.StatCraftItemManager;
import net.minecraft.entity.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemCarrotOnAStick extends Item {
   @Override
   public boolean isState4() {
      return true;
   }

   @Override
   public boolean processAction3() {
      return true;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (player.isSneaking() && player.ridingEntity instanceof EntityPig) {
         EntityPig entity = (EntityPig)player.ridingEntity;
         if (entity.getSimpleItemEntry().isActive3() && stack.processAction() - stack.getIndexOffset() >= 7) {
            entity.getSimpleItemEntry().updateState4();
            stack.applyEntityLivingBaseByEntityLivingBase(7, player);
            if (stack.animationsToGo == 0) {
               ItemStack stack2 = new ItemStack(Items.fishingRod);
               stack2.processAction(stack.getStackTagCompound());
               return stack2;
            }
         }
      }

      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
      return stack;
   }

   public ItemCarrotOnAStick() {
      this.processAction(ItemGroupManager.itemGroupManager12);
      this.processAction4(1);
      this.processAction3(25);
   }
}
