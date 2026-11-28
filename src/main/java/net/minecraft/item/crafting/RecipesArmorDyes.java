package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipesArmorDyes implements IRecipe {
   @Override
   public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
      ItemStack[] stacks = new ItemStack[inventoryCrafting.getSizeInventory()];

      for (int i = 0; i < stacks.length; i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null && stackInSlot.getItem().hasContainerItem()) {
            stacks[i] = new ItemStack(stackInSlot.getItem().getContainerItem());
         }
      }

      return stacks;
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
      ItemStack stack = null;
      int[] ints = new int[3];
      int n = 0;
      int n2 = 0;
      ItemArmor itemArmor = null;

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            if (stackInSlot.getItem() instanceof ItemArmor) {
               itemArmor = (ItemArmor)stackInSlot.getItem();
               if (itemArmor.getArmorMaterial() != ItemArmor.ArmorMaterial.LEATHER || stack != null) {
                  return null;
               }

               stack = stackInSlot.copy();
               stack.stackSize = 1;
               if (itemArmor.hasColor(stackInSlot)) {
                  int color = itemArmor.getColor(stack);
                  float f = (float)(color >> 16 & 0xFF) / 255.0F;
                  float f2 = (float)(color >> 8 & 0xFF) / 255.0F;
                  float f3 = (float)(color & 0xFF) / 255.0F;
                  n = (int)((float)n + Math.max(f, Math.max(f2, f3)) * 255.0F);
                  ints[0] = (int)((float)ints[0] + f * 255.0F);
                  ints[1] = (int)((float)ints[1] + f2 * 255.0F);
                  ints[2] = (int)((float)ints[2] + f3 * 255.0F);
                  n2++;
               }
            } else {
               if (stackInSlot.getItem() != Items.dye) {
                  return null;
               }

               float[] dyeRgb = EntitySheep.getDyeRgb(EnumDyeColor.byDyeDamage(stackInSlot.getMetadata()));
               int n3 = (int)(dyeRgb[0] * 255.0F);
               int n4 = (int)(dyeRgb[1] * 255.0F);
               int n5 = (int)(dyeRgb[2] * 255.0F);
               n += Math.max(n3, Math.max(n4, n5));
               ints[0] += n3;
               ints[1] += n4;
               ints[2] += n5;
               n2++;
            }
         }
      }

      if (itemArmor == null) {
         return null;
      } else {
         int n6 = ints[0] / n2;
         int n7 = ints[1] / n2;
         int n8 = ints[2] / n2;
         float f4 = (float)n / (float)n2;
         float f5 = (float)Math.max(n6, Math.max(n7, n8));
         n6 = (int)((float)n6 * f4 / f5);
         n7 = (int)((float)n7 * f4 / f5);
         n8 = (int)((float)n8 * f4 / f5);
         int n9 = (n6 << 8) + n7;
         n9 = (n9 << 8) + n8;
         itemArmor.setColor(stack, n9);
         return stack;
      }
   }

   @Override
   public int getRecipeSize() {
      return 10;
   }

   @Override
   public boolean matches(InventoryCrafting inventoryCrafting, World world) {
      ItemStack stack = null;
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            if (stackInSlot.getItem() instanceof ItemArmor) {
               if (((ItemArmor)stackInSlot.getItem()).getArmorMaterial() != ItemArmor.ArmorMaterial.LEATHER || stack != null) {
                  return false;
               }

               stack = stackInSlot;
            } else {
               if (stackInSlot.getItem() != Items.dye) {
                  return false;
               }

               list.add(stackInSlot);
            }
         }
      }

      return stack != null && !list.isEmpty();
   }

   @Override
   public ItemStack getRecipeOutput() {
      return null;
   }
}
