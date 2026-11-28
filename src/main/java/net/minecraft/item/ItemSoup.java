package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class ItemSoup extends ItemFood {
   public ItemSoup(int n) {
      super(n, false);
      this.setMaxStackSize(1);
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
      super.onItemUseFinish(stack, world, player);
      return new ItemStack(Items.bowl);
   }
}
