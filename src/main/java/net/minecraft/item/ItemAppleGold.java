package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAppleGold extends ItemFood {
   @Override
   public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
      if (!world.isRemote) {
         player.addPotionEffect(new PotionEffect(Potion.absorption.id, 2400, 0));
      }

      if (stack.getMetadata() > 0) {
         if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 4));
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
         }
      } else {
         super.onFoodEaten(stack, world, player);
      }
   }

   @Override
   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
   }

   public ItemAppleGold(int n, float f, boolean flag) {
      super(n, f, flag);
      this.setHasSubtypes(true);
   }

   @Override
   public boolean hasEffect(ItemStack stack) {
      return stack.getMetadata() > 0;
   }

   @Override
   public EnumRarity getRarity(ItemStack stack) {
      return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
   }
}
