package client.util;

import client.event.PreMotionProcessor;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ExplosionManager implements PreMotionProcessor {
   public ItemStack itemStack;

   @Override
   public int size() {
      return 10;
   }

   @Override
   public ItemStack[] resolveItemStackArray(ContainerCraftingIInventory containerCraftingIInventory) {
      ItemStack[] stackArray = new ItemStack[containerCraftingIInventory.processAction4()];

      for (int index = 0; index < stackArray.length; index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null && stack.getItem().isActiveAsFlag()) {
            stackArray[index] = new ItemStack(stack.getItem().getItem());
         }
      }

      return stackArray;
   }

   @Override
   public ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory) {
      return this.itemStack.getItemStack();
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      this.itemStack = null;
      int count = 0;
      int count2 = 0;
      int count3 = 0;
      int count4 = 0;
      int count5 = 0;
      int count6 = 0;

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null) {
            if (stack.getItem() == Items.gunpowder) {
               count2++;
            } else if (stack.getItem() == Items.fireworkCharge) {
               count4++;
            } else if (stack.getItem() == Items.dye) {
               count3++;
            } else if (stack.getItem() == Items.paper) {
               count++;
            } else if (stack.getItem() == Items.glowstoneDust) {
               count5++;
            } else if (stack.getItem() == Items.diamond) {
               count5++;
            } else if (stack.getItem() == Items.fireCharge) {
               count6++;
            } else if (stack.getItem() == Items.feather) {
               count6++;
            } else if (stack.getItem() == Items.goldNugget) {
               count6++;
            } else {
               if (stack.getItem() != Items.skull) {
                  return false;
               }

               count6++;
            }
         }
      }

      int count7 = count5 + count3 + count6;
      if (count2 > 3 || count > 1) {
         return false;
      } else if (count2 >= 1 && count == 1 && count7 == 0) {
         this.itemStack = new ItemStack(Items.fireworks);
         if (count4 > 0) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            NBTTagCompound tagCompound2 = new NBTTagCompound();
            NBTTagList nBTTagList = new NBTTagList();

            for (int index2 = 0; index2 < containerCraftingIInventory.processAction4(); index2++) {
               ItemStack stack2 = containerCraftingIInventory.processAction3(index2);
               if (stack2 != null && stack2.getItem() == Items.fireworkCharge && stack2.canExecute() && stack2.getStackTagCompound().matchesText("Explosion", 10)) {
                  nBTTagList.applyLISTManager(stack2.getStackTagCompound().getNBTTagCompound("Explosion"));
               }
            }

            tagCompound2.applyText("Explosions", nBTTagList);
            tagCompound2.put("Flight", (byte)count2);
            tagCompound.applyText("Fireworks", tagCompound2);
            this.itemStack.processAction(tagCompound);
         }

         return true;
      } else if (count2 == 1 && count == 0 && count4 == 0 && count3 > 0 && count6 <= 1) {
         this.itemStack = new ItemStack(Items.fireworkCharge);
         NBTTagCompound tagCompound3 = new NBTTagCompound();
         NBTTagCompound tagCompound4 = new NBTTagCompound();
         byte byteValue = 0;
         ArrayList list = Lists.newArrayList();

         for (int index3 = 0; index3 < containerCraftingIInventory.processAction4(); index3++) {
            ItemStack stack3 = containerCraftingIInventory.processAction3(index3);
            if (stack3 != null) {
               if (stack3.getItem() == Items.dye) {
                  list.add(ItemDye.countArray[stack3.getIndexOffset() & 15]);
               } else if (stack3.getItem() == Items.glowstoneDust) {
                  tagCompound4.applyText("Flicker", true);
               } else if (stack3.getItem() == Items.diamond) {
                  tagCompound4.applyText("Trail", true);
               } else if (stack3.getItem() == Items.fireCharge) {
                  byteValue = 1;
               } else if (stack3.getItem() == Items.feather) {
                  byteValue = 4;
               } else if (stack3.getItem() == Items.goldNugget) {
                  byteValue = 2;
               } else if (stack3.getItem() == Items.skull) {
                  byteValue = 3;
               }
            }
         }

         int[] countArray = new int[list.size()];

         for (int index4 = 0; index4 < countArray.length; index4++) {
            countArray[index4] = (Integer)list.get(index4);
         }

         tagCompound4.put("Colors", countArray);
         tagCompound4.put("Type", byteValue);
         tagCompound3.applyText("Explosion", tagCompound4);
         this.itemStack.processAction(tagCompound3);
         return true;
      } else if (count2 == 0 && count == 0 && count4 == 1 && count3 > 0 && count3 == count7) {
         ArrayList list2 = Lists.newArrayList();

         for (int index5 = 0; index5 < containerCraftingIInventory.processAction4(); index5++) {
            ItemStack stack4 = containerCraftingIInventory.processAction3(index5);
            if (stack4 != null) {
               if (stack4.getItem() == Items.dye) {
                  list2.add(ItemDye.countArray[stack4.getIndexOffset() & 15]);
               } else if (stack4.getItem() == Items.fireworkCharge) {
                  this.itemStack = stack4.getItemStack();
                  this.itemStack.animationsToGo = 1;
               }
            }
         }

         int[] countArray2 = new int[list2.size()];

         for (int index6 = 0; index6 < countArray2.length; index6++) {
            countArray2[index6] = (Integer)list2.get(index6);
         }

         if (this.itemStack != null && this.itemStack.canExecute()) {
            NBTTagCompound nBTTagCompound = this.itemStack.getStackTagCompound().getNBTTagCompound("Explosion");
            if (nBTTagCompound == null) {
               return false;
            } else {
               nBTTagCompound.put("FadeColors", countArray2);
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
   public ItemStack getItemStack() {
      return this.itemStack;
   }
}
