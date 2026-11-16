package net.minecraft.item;

import client.setting.ItemStackCommonType;
import client.util.AuxiliaryClientManagerResolver;
import client.util.ClientManagerAdapter2;
import client.util.EnchantmentManager;
import client.util.LvlManager;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemEnchantedBook extends Item {
   public ClientManagerAdapter2 resolveClientManagerAdapter2(Random random) {
      return this.resolveClientManagerAdapter2(random, 1, 1, 1);
   }

   @Override
   public ItemStackCommonType getItemStackCommonType(ItemStack stack) {
      return this.resolveNBTTagList(stack).getInt() > 0 ? ItemStackCommonType.UNCOMMON : super.getItemStackCommonType(stack);
   }

   @Override
   public boolean processAction2(ItemStack stack) {
      return true;
   }

   public ClientManagerAdapter2 resolveClientManagerAdapter2(Random random, int count, int count2, int count3) {
      ItemStack stack = new ItemStack(Items.book, 1, 0);
      LvlManager.resolveItemStack(random, stack, 30);
      return new ClientManagerAdapter2(stack, count, count2, count3);
   }

   public NBTTagList resolveNBTTagList(ItemStack stack) {
      NBTTagCompound stackTagCompound = stack.getStackTagCompound();
      return stackTagCompound != null && stackTagCompound.matchesText("StoredEnchantments", 9) ? (NBTTagList)stackTagCompound.get("StoredEnchantments") : new NBTTagList();
   }

   public void applyEnchantmentManager(EnchantmentManager enchantmentManager, List list) {
      for (int index = enchantmentManager.getCount2(); index <= enchantmentManager.getCount(); index++) {
         list.add(this.resolveItemStack(new AuxiliaryClientManagerResolver(enchantmentManager, index)));
      }
   }

   public ItemStack resolveItemStack(AuxiliaryClientManagerResolver auxiliaryClientManagerResolver) {
      ItemStack stack = new ItemStack(this);
      this.applyItemStack(stack, auxiliaryClientManagerResolver);
      return stack;
   }

   @Override
   public boolean matchesItemStack(ItemStack stack) {
      return false;
   }

   public void applyItemStack(ItemStack stack, AuxiliaryClientManagerResolver auxiliaryClientManagerResolver) {
      NBTTagList nBTTagList = this.resolveNBTTagList(stack);
      boolean flag = true;

      for (int index = 0; index < nBTTagList.getInt(); index++) {
         NBTTagCompound nBTTagCompound = nBTTagList.getNBTTagCompound(index);
         if (nBTTagCompound.processValue("id") == auxiliaryClientManagerResolver.enchantmentManager.indexOffset) {
            if (nBTTagCompound.processValue("lvl") < auxiliaryClientManagerResolver.count2) {
               nBTTagCompound.put("lvl", (short)auxiliaryClientManagerResolver.count2);
            }

            flag = false;
            break;
         }
      }

      if (flag) {
         NBTTagCompound tagCompound = new NBTTagCompound();
         tagCompound.put("id", (short)auxiliaryClientManagerResolver.enchantmentManager.indexOffset);
         tagCompound.put("lvl", (short)auxiliaryClientManagerResolver.count2);
         nBTTagList.applyLISTManager(tagCompound);
      }

      if (!stack.canExecute()) {
         stack.processAction(new NBTTagCompound());
      }

      stack.getStackTagCompound().applyText("StoredEnchantments", nBTTagList);
   }

   @Override
   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      super.processAction(stack, player, list, flag);
      NBTTagList nBTTagList = this.resolveNBTTagList(stack);
      if (nBTTagList != null) {
         for (int index = 0; index < nBTTagList.getInt(); index++) {
            short shortValue = nBTTagList.getNBTTagCompound(index).processValue("id");
            short shortValue2 = nBTTagList.getNBTTagCompound(index).processValue("lvl");
            if (EnchantmentManager.resolveEnchantmentManager(shortValue) != null) {
               list.add(EnchantmentManager.resolveEnchantmentManager(shortValue).resolveText(shortValue2));
            }
         }
      }
   }
}
