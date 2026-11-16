package net.minecraft.item;

import client.setting.ItemNoneType;
import client.util.EnchantmentManager;
import client.util.LvlManager;
import client.util.StatCraftItemManager;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemBow extends Item {
   public static final String[] stringArray = new String[]{"pulling_0", "pulling_1", "pulling_2"};

   @Override
   public int processAction(ItemStack stack) {
      return 72000;
   }

   @Override
   public int processAction2() {
      return 1;
   }

   @Override
   public ItemStack processAction2(ItemStack stack, World world, EntityPlayer player) {
      return stack;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (player.invulnerableManager.enabled2 || player.inventoryPlayer.checkItem2(Items.arrow)) {
         player.applyItemStack(stack, this.processAction(stack));
      }

      return stack;
   }

   @Override
   public ItemNoneType processAction4(ItemStack stack) {
      return ItemNoneType.BOW;
   }

   @Override
   public void processAction(ItemStack stack, World world, EntityPlayer player, int count) {
      boolean flag = player.invulnerableManager.enabled2 || LvlManager.getIndex(EnchantmentManager.enchantmentManager24.indexOffset, stack) > 0;
      if (flag || player.inventoryPlayer.checkItem2(Items.arrow)) {
         float factor = (float)(this.processAction(stack) - count) / 20.0F;
         float factor2 = (factor * factor + factor * 2.0F) / 3.0F;
         if ((double)factor2 < 0.1) {
            return;
         }

         if (factor2 > 1.0F) {
            factor2 = 1.0F;
         }

         EntityArrow entityArrow = new EntityArrow(world, player, factor2 * 2.0F);
         if (factor2 == 1.0F) {
            entityArrow.setEnabled5(true);
         }

         int index = LvlManager.getIndex(EnchantmentManager.enchantmentManager10.indexOffset, stack);
         if (index > 0) {
            entityArrow.setValue6(entityArrow.getValue6() + (double)index * 0.5 + 0.5);
         }

         int index2 = LvlManager.getIndex(EnchantmentManager.enchantmentManager8.indexOffset, stack);
         if (index2 > 0) {
            entityArrow.setCount13(index2);
         }

         if (LvlManager.getIndex(EnchantmentManager.enchantmentManager17.indexOffset, stack) > 0) {
            entityArrow.applyIndexByIndex(100);
         }

         stack.applyEntityLivingBaseByEntityLivingBase(1, player);
         world.applyEntityByEntity(player, "random.bow", 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + factor2 * 0.5F);
         if (flag) {
            entityArrow.count12 = 2;
         } else {
            player.inventoryPlayer.checkItem(Items.arrow);
         }

         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
         if (!world.state) {
            world.matchesEntity(entityArrow);
         }
      }
   }

   public ItemBow() {
      this.indexOffset1 = 1;
      this.processAction3(384);
      this.processAction(ItemGroupManager.itemGroupManager4);
   }
}
