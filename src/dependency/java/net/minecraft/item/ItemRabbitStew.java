package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemRabbitStew extends ItemFood {
   @Override
   public ItemStack processAction2(ItemStack stack, World world, EntityPlayer player) {
      super.processAction2(stack, world, player);
      return new ItemStack(Items.bowl);
   }

   public ItemRabbitStew(int count) {
      super(count, false);
      this.processAction4(1);
   }
}
