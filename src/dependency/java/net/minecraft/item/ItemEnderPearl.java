package net.minecraft.item;

import client.util.StatCraftItemManager;
import net.minecraft.entity.EntityThrownenderpearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemEnderPearl extends Item {
   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (player.invulnerableManager.enabled2) {
         return stack;
      } else {
         stack.animationsToGo--;
         world.applyEntityByEntity(player, "random.bow", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
         if (!world.state) {
            world.matchesEntity(new EntityThrownenderpearl(world, player));
         }

         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
         return stack;
      }
   }

   public ItemEnderPearl() {
      this.indexOffset1 = 16;
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}
