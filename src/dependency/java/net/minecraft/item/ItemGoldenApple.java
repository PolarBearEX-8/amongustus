package net.minecraft.item;

import client.setting.ItemStackCommonType;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGoldenApple extends ItemFood {
   @Override
   public ItemStackCommonType getItemStackCommonType(ItemStack stack) {
      return stack.getIndexOffset() == 0 ? ItemStackCommonType.RARE : ItemStackCommonType.EPIC;
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
   }

   @Override
   public boolean processAction2(ItemStack stack) {
      return stack.getIndexOffset() > 0;
   }

   @Override
   public void applyItemStack2(ItemStack stack, World world, EntityPlayer player) {
      if (!world.state) {
         player.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion23.indexOffset3, 2400, 0));
      }

      if (stack.getIndexOffset() > 0) {
         if (!world.state) {
            player.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion11.indexOffset3, 600, 4));
            player.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion12.indexOffset3, 6000, 0));
            player.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion13.indexOffset3, 6000, 0));
         }
      } else {
         super.applyItemStack2(stack, world, player);
      }
   }

   public ItemGoldenApple(int count, float factor, boolean flag) {
      super(count, factor, flag);
      this.processAction(true);
   }
}
