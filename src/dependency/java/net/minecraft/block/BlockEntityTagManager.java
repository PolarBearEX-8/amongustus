package net.minecraft.block;

import client.event.PreMotionProcessor;
import client.util.AngleAccumulator;
import client.util.ContainerCraftingIInventory;
import client.util.StandardCustom;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.world.World;

public class BlockEntityTagManager implements PreMotionProcessor {
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
      ItemStack stack = null;

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack2 = containerCraftingIInventory.processAction3(index);
         if (stack2 != null && stack2.getItem() == Items.banner) {
            stack = stack2.getItemStack();
            stack.animationsToGo = 1;
            break;
         }
      }

      AngleAccumulator angleAccumulator = this.resolveAngleAccumulator(containerCraftingIInventory);
      if (angleAccumulator != null) {
         int count = 0;

         for (int index2 = 0; index2 < containerCraftingIInventory.processAction4(); index2++) {
            ItemStack stack3 = containerCraftingIInventory.processAction3(index2);
            if (stack3 != null && stack3.getItem() == Items.dye) {
               count = stack3.getIndexOffset();
               break;
            }
         }

         NBTTagCompound tagCompound = stack.resolveNBTTagCompound("BlockEntityTag", true);
         NBTTagList nBTTagList;
         if (tagCompound.matchesText("Patterns", 9)) {
            nBTTagList = tagCompound.getExtendedLoggerEntry("Patterns", 10);
         } else {
            nBTTagList = new NBTTagList();
            tagCompound.applyText("Patterns", nBTTagList);
         }

         NBTTagCompound tagCompound2 = new NBTTagCompound();
         tagCompound2.put("Pattern", angleAccumulator.getLabelText2());
         tagCompound2.put("Color", count);
         nBTTagList.applyLISTManager(tagCompound2);
      }

      return stack;
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      boolean flag = false;

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null && stack.getItem() == Items.banner) {
            if (flag) {
               return false;
            }

            if (TileEntityBanner.computeCount(stack) >= 6) {
               return false;
            }

            flag = true;
         }
      }

      return !flag ? false : this.resolveAngleAccumulator(containerCraftingIInventory) != null;
   }

   private AngleAccumulator resolveAngleAccumulator(ContainerCraftingIInventory containerCraftingIInventory) {
      for (AngleAccumulator angleAccumulator : AngleAccumulator.values()) {
         if (angleAccumulator.isActiveAsFlag()) {
            boolean flag = true;
            if (angleAccumulator.isActive()) {
               boolean flag2 = false;
               boolean flag3 = false;

               for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
                  ItemStack stack = containerCraftingIInventory.processAction3(index);
                  if (stack != null && stack.getItem() != Items.banner) {
                     if (stack.getItem() == Items.dye) {
                        if (flag3) {
                           flag = false;
                           break;
                        }

                        flag3 = true;
                     } else {
                        if (flag2 || !stack.matchesItemStack(angleAccumulator.getItemStack())) {
                           flag = false;
                           break;
                        }

                        flag2 = true;
                     }
                  }
               }

               if (!flag2) {
                  flag = false;
               }
            } else if (containerCraftingIInventory.processAction4() == angleAccumulator.getStringArray().length * angleAccumulator.getStringArray()[0].length()) {
               int count = -1;

               for (int index2 = 0; index2 < containerCraftingIInventory.processAction4(); index2++) {
                  int count2 = index2 / 3;
                  int count3 = index2 % 3;
                  ItemStack stack2 = containerCraftingIInventory.processAction3(index2);
                  if (stack2 != null && stack2.getItem() != Items.banner) {
                     if (stack2.getItem() != Items.dye) {
                        flag = false;
                        break;
                     }

                     if (count != -1 && count != stack2.getIndexOffset()) {
                        flag = false;
                        break;
                     }

                     if (angleAccumulator.getStringArray()[count2].charAt(count3) == ' ') {
                        flag = false;
                        break;
                     }

                     count = stack2.getIndexOffset();
                  } else if (angleAccumulator.getStringArray()[count2].charAt(count3) != ' ') {
                     flag = false;
                     break;
                  }
               }
            } else {
               flag = false;
            }

            if (flag) {
               return angleAccumulator;
            }
         }
      }

      return null;
   }

   @Override
   public ItemStack getItemStack() {
      return null;
   }

   private BlockEntityTagManager() {
   }

   public BlockEntityTagManager(StandardCustom standardCustom) {
      this();
   }
}
