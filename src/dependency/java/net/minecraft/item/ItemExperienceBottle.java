package net.minecraft.item;

import client.util.StatCraftItemManager;
import net.minecraft.entity.EntityThrownexpbottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemExperienceBottle extends Item {
   @Override
   public boolean processAction2(ItemStack stack) {
      return true;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (!player.invulnerableManager.enabled2) {
         stack.animationsToGo--;
      }

      world.applyEntityByEntity(player, "random.bow", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
      if (!world.state) {
         world.matchesEntity(new EntityThrownexpbottle(world, player));
      }

      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
      return stack;
   }

   public ItemExperienceBottle() {
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}
