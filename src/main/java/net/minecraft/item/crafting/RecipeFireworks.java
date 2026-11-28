package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RecipeFireworks implements IRecipe {
   private ItemStack recipeOutput;

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
   public ItemStack getRecipeOutput() {
      return this.recipeOutput;
   }

   @Override
   public boolean matches(InventoryCrafting inventoryCrafting, World world) {
      this.recipeOutput = null;
      int n = 0;
      int n2 = 0;
      int n3 = 0;
      int n4 = 0;
      int n5 = 0;
      int n6 = 0;

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            if (stackInSlot.getItem() == Items.gunpowder) {
               n2++;
            } else if (stackInSlot.getItem() == Items.firework_charge) {
               n4++;
            } else if (stackInSlot.getItem() == Items.dye) {
               n3++;
            } else if (stackInSlot.getItem() == Items.paper) {
               n++;
            } else if (stackInSlot.getItem() == Items.glowstone_dust) {
               n5++;
            } else if (stackInSlot.getItem() == Items.diamond) {
               n5++;
            } else if (stackInSlot.getItem() == Items.fire_charge) {
               n6++;
            } else if (stackInSlot.getItem() == Items.feather) {
               n6++;
            } else if (stackInSlot.getItem() == Items.gold_nugget) {
               n6++;
            } else {
               if (stackInSlot.getItem() != Items.skull) {
                  return false;
               }

               n6++;
            }
         }
      }

      n5 = n5 + n3 + n6;
      if (n2 > 3 || n > 1) {
         return false;
      } else if (n2 >= 1 && n == 1 && n5 == 0) {
         this.recipeOutput = new ItemStack(Items.fireworks);
         if (n4 > 0) {
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagCompound tag2 = new NBTTagCompound();
            NBTTagList tagList = new NBTTagList();

            for (int j = 0; j < inventoryCrafting.getSizeInventory(); j++) {
               ItemStack stackInSlot2 = inventoryCrafting.getStackInSlot(j);
               if (stackInSlot2 != null && stackInSlot2.getItem() == Items.firework_charge && stackInSlot2.hasTagCompound() && stackInSlot2.getTagCompound().hasKey("Explosion", 10)) {
                  tagList.appendTag(stackInSlot2.getTagCompound().getCompoundTag("Explosion"));
               }
            }

            tag2.setTag("Explosions", tagList);
            tag2.setByte("Flight", (byte)n2);
            tag.setTag("Fireworks", tag2);
            this.recipeOutput.setTagCompound(tag);
         }

         return true;
      } else if (n2 == 1 && n == 0 && n4 == 0 && n3 > 0 && n6 <= 1) {
         this.recipeOutput = new ItemStack(Items.firework_charge);
         NBTTagCompound tag3 = new NBTTagCompound();
         NBTTagCompound tag4 = new NBTTagCompound();
         byte b = 0;
         ArrayList list = Lists.newArrayList();

         for (int k = 0; k < inventoryCrafting.getSizeInventory(); k++) {
            ItemStack stackInSlot3 = inventoryCrafting.getStackInSlot(k);
            if (stackInSlot3 != null) {
               if (stackInSlot3.getItem() == Items.dye) {
                  list.add(ItemDye.dyeColors[stackInSlot3.getMetadata() & 15]);
               } else if (stackInSlot3.getItem() == Items.glowstone_dust) {
                  tag4.setBoolean("Flicker", true);
               } else if (stackInSlot3.getItem() == Items.diamond) {
                  tag4.setBoolean("Trail", true);
               } else if (stackInSlot3.getItem() == Items.fire_charge) {
                  b = 1;
               } else if (stackInSlot3.getItem() == Items.feather) {
                  b = 4;
               } else if (stackInSlot3.getItem() == Items.gold_nugget) {
                  b = 2;
               } else if (stackInSlot3.getItem() == Items.skull) {
                  b = 3;
               }
            }
         }

         int[] ints = new int[list.size()];

         for (int m = 0; m < ints.length; m++) {
            ints[m] = (Integer)list.get(m);
         }

         tag4.setIntArray("Colors", ints);
         tag4.setByte("Type", b);
         tag3.setTag("Explosion", tag4);
         this.recipeOutput.setTagCompound(tag3);
         return true;
      } else if (n2 == 0 && n == 0 && n4 == 1 && n3 > 0 && n3 == n5) {
         ArrayList list2 = Lists.newArrayList();

         for (int idx = 0; idx < inventoryCrafting.getSizeInventory(); idx++) {
            ItemStack stackInSlot4 = inventoryCrafting.getStackInSlot(idx);
            if (stackInSlot4 != null) {
               if (stackInSlot4.getItem() == Items.dye) {
                  list2.add(ItemDye.dyeColors[stackInSlot4.getMetadata() & 15]);
               } else if (stackInSlot4.getItem() == Items.firework_charge) {
                  this.recipeOutput = stackInSlot4.copy();
                  this.recipeOutput.stackSize = 1;
               }
            }
         }

         int[] ints2 = new int[list2.size()];

         for (int index = 0; index < ints2.length; index++) {
            ints2[index] = (Integer)list2.get(index);
         }

         if (this.recipeOutput != null && this.recipeOutput.hasTagCompound()) {
            NBTTagCompound compoundTag = this.recipeOutput.getTagCompound().getCompoundTag("Explosion");
            if (compoundTag == null) {
               return false;
            } else {
               compoundTag.setIntArray("FadeColors", ints2);
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public int getRecipeSize() {
      return 10;
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
      return this.recipeOutput.copy();
   }
}
