package net.minecraft.item;

import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.WeightedRandomChestContent;

public class ItemEnchantedBook extends Item {

   public void getAll(Enchantment enchantment, List list) {
      for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); i++) {
         list.add(this.getEnchantedItemStack(new EnchantmentData(enchantment, i)));
      }
   }

   public void addEnchantment(ItemStack stack, EnchantmentData enchantmentData) {
      NBTTagList enchantments = this.getEnchantments(stack);
      boolean flag = true;

      for (int i = 0; i < enchantments.tagCount(); i++) {
         NBTTagCompound compoundTagAt = enchantments.getCompoundTagAt(i);
         if (compoundTagAt.getShort("id") == enchantmentData.enchantmentobj.effectId) {
            if (compoundTagAt.getShort("lvl") < enchantmentData.enchantmentLevel) {
               compoundTagAt.setShort("lvl", (short)enchantmentData.enchantmentLevel);
            }

            flag = false;
            break;
         }
      }

      if (flag) {
         NBTTagCompound tag = new NBTTagCompound();
         tag.setShort("id", (short)enchantmentData.enchantmentobj.effectId);
         tag.setShort("lvl", (short)enchantmentData.enchantmentLevel);
         enchantments.appendTag(tag);
      }

      if (!stack.hasTagCompound()) {
         stack.setTagCompound(new NBTTagCompound());
      }

      stack.getTagCompound().setTag("StoredEnchantments", enchantments);
   }

   @Override
   public boolean hasEffect(ItemStack stack) {
      return true;
   }

   public ItemStack getEnchantedItemStack(EnchantmentData enchantmentData) {
      ItemStack stack = new ItemStack(this);
      this.addEnchantment(stack, enchantmentData);
      return stack;
   }

   public NBTTagList getEnchantments(ItemStack stack) {
      NBTTagCompound tagCompound = stack.getTagCompound();
      return tagCompound != null && tagCompound.hasKey("StoredEnchantments", 9) ? (NBTTagList)tagCompound.getTag("StoredEnchantments") : new NBTTagList();
   }

   @Override
   public EnumRarity getRarity(ItemStack stack) {
      return this.getEnchantments(stack).tagCount() > 0 ? EnumRarity.UNCOMMON : super.getRarity(stack);
   }

   @Override
   public boolean isItemTool(ItemStack stack) {
      return false;
   }

   public WeightedRandomChestContent getRandom(Random rand, int n, int n2, int n3) {
      ItemStack stack = new ItemStack(Items.book, 1, 0);
      EnchantmentHelper.addRandomEnchantment(rand, stack, 30);
      return new WeightedRandomChestContent(stack, n, n2, n3);
   }

   public WeightedRandomChestContent getRandom(Random rand) {
      return this.getRandom(rand, 1, 1, 1);
   }

   @Override
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      super.addInformation(stack, player, list, flag);
      NBTTagList enchantments = this.getEnchantments(stack);
      if (enchantments != null) {
         for (int i = 0; i < enchantments.tagCount(); i++) {
            short s = enchantments.getCompoundTagAt(i).getShort("id");
            short s2 = enchantments.getCompoundTagAt(i).getShort("lvl");
            if (Enchantment.getEnchantmentById(s) != null) {
               list.add(Enchantment.getEnchantmentById(s).getTranslatedName(s2));
            }
         }
      }
   }
}
