package cc.baritone.utils;

import cc.baritone.api.AuxiliaryItemEntry;
import cc.baritone.api.InternalItemEntry;
import client.util.IBlockState;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGoldenPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public final class RememberingToMoveManager extends AuxiliaryItemEntry implements DRNHandler {
   public int count;
   public int[] countArray;

   public boolean isActive() {
      for (Object itemObj : (List)BaritoneManager.getSettings().acceptableThrowawayItems.dataRef) {
         Item item = (Item)itemObj;
         if (this.matchesPredicate(false, stack -> RememberingToMoveManager.matchesItem(item, (ItemStack)stack))) {
            return true;
         }
      }

      return false;
   }

   private int computeCount(Block block, Class clazz) {
      int count2 = -1;
      double offset = -1.0;

      for (int index = 0; index < this.staticListener.getEntityPlayerSP().inventoryPlayer.mainInventory.length; index++) {
         ItemStack stack = this.staticListener.getEntityPlayerSP().inventoryPlayer.mainInventory[index];
         if (stack != null
            && (!(Boolean)BaritoneManager.getSettings().itemSaver.dataRef || stack.getIndexOffset2() < stack.processAction() || stack.processAction() <= 1)
            && clazz.isInstance(stack.getItem())) {
            double offset2 = DepthFirstPolicy.computeValue(stack, block.getDefaultState());
            if (offset2 > offset) {
               offset = offset2;
               count2 = index;
            }
         }
      }

      return count2;
   }

   private static boolean matchesItem(Item item, ItemStack stack) {
      return stack != null && item.equals(stack.getItem());
   }

   private static boolean matchesIBlockState(IBlockState blockState, ItemStack stack) {
      return stack.getItem() instanceof ItemBlock && ((ItemBlock)stack.getItem()).getBlock().equals(blockState.getBlock());
   }

   public boolean matchesPredicate(int count2, Predicate predicate) {
      OptionalInt optionalInt = this.resolveOptionalInt(predicate);
      return !optionalInt.isPresent() || this.checkIndex(count2, optionalInt.getAsInt());
   }

   public boolean checkFlag(boolean flag, int count2, int count3, int count4) {
      IBlockState blockState = this.baritoneManager
         .getBuilderPausedSchematicsHandler()
         .resolveIBlockState(count2, count3, count4, this.baritoneManager.heuristicManager.resolveIBlockState(count2, count3, count4));
      if (blockState != null && this.matchesPredicate(flag, stack -> this.matchesIBlockStateByIBlockState(blockState, (ItemStack)stack))) {
         return true;
      } else if (blockState != null && this.matchesPredicate(flag, stack -> RememberingToMoveManager.matchesIBlockState(blockState, (ItemStack)stack))) {
         return true;
      } else {
         for (Object itemObj : (List)BaritoneManager.getSettings().acceptableThrowawayItems.dataRef) {
            Item item = (Item)itemObj;
            if (this.matchesPredicate(flag, stack -> RememberingToMoveManager.matchesItemByItem(item, (ItemStack)stack))) {
               return true;
            }
         }

         return false;
      }
   }

   private int getCount() {
      for (int index = 0; index < this.staticListener.getEntityPlayerSP().inventoryPlayer.mainInventory.length; index++) {
         ItemStack stack = this.staticListener.getEntityPlayerSP().inventoryPlayer.mainInventory[index];
         if (stack != null && ((List)BaritoneManager.getSettings().acceptableThrowawayItems.dataRef).contains(stack.getItem())) {
            return index;
         }
      }

      return -1;
   }

   public boolean matchesPredicate(boolean flag, Predicate predicate, boolean flag2) {
      EntityPlayerSP entityPlayerSP = this.staticListener.getEntityPlayerSP();
      ItemStack[] mainInventory = entityPlayerSP.inventoryPlayer.mainInventory;

      for (int index = 0; index < 9; index++) {
         ItemStack stack = mainInventory[index];
         if (stack != null && stack.getItem() != null && predicate.test(stack)) {
            if (flag) {
               entityPlayerSP.inventoryPlayer.currentItem = index;
            }

            return true;
         }
      }

      if (flag2) {
         for (int index2 = 9; index2 < 36; index2++) {
            if (predicate.test(mainInventory[index2])) {
               if (flag) {
                  this.checkIndex(index2, 7);
                  entityPlayerSP.inventoryPlayer.currentItem = 7;
               }

               return true;
            }
         }
      }

      return false;
   }

   private boolean matchesIBlockStateByIBlockState(IBlockState blockState, ItemStack stack) {
      return stack.getItem() instanceof ItemBlock
         && blockState.equals(
            ((ItemBlock)stack.getItem())
               .getBlock()
               .onBlockPlaced(
                  this.staticListener.getWorld(),
                  this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS(),
                  EnumFacing.UP,
                  (float)this.staticListener.getEntityPlayerSP().posX,
                  (float)this.staticListener.getEntityPlayerSP().posY,
                  (float)this.staticListener.getEntityPlayerSP().posZ,
                  stack.getItem().processAction2(stack.getIndexOffset()),
                  this.staticListener.getEntityPlayerSP()
               )
         );
   }

   public boolean matchesPredicate(boolean flag, Predicate predicate) {
      return this.matchesPredicate(flag, predicate, (Boolean)BaritoneManager.getSettings().allowInventory.dataRef);
   }

   private boolean checkIndex(int count2, int count3) {
      this.countArray = new int[]{count2, count3};
      if (this.count < (Integer)BaritoneManager.getSettings().ticksBetweenInventoryMoves.dataRef) {
         this.applyTextByText("Inventory move requested but delaying " + this.count + " " + BaritoneManager.getSettings().ticksBetweenInventoryMoves.dataRef);
         return false;
      } else if ((Boolean)BaritoneManager.getSettings().inventoryMoveOnlyIfStationary.dataRef
         && !this.baritoneManager.getInventoryPauserINACTIVEHandlerAdapter().isActive2()) {
         this.applyTextByText("Inventory move requested but delaying until stationary");
         return false;
      } else {
         this.staticListener
            .getDefaultListener()
            .resolveItemStack(
               this.staticListener.getEntityPlayerSP().container.indexOffset2, count2 < 9 ? count2 + 36 : count2, count3, 2, this.staticListener.getEntityPlayerSP()
            );
         this.count = 0;
         this.countArray = null;
         return true;
      }
   }

   private static boolean matchesItemByItem(Item item, ItemStack stack) {
      return item.equals(stack.getItem());
   }

   public OptionalInt resolveOptionalInt(Predicate predicate) {
      java.util.ArrayList list = new java.util.ArrayList();

      for (int index = 1; index < 8; index++) {
         if (this.staticListener.getEntityPlayerSP().inventoryPlayer.mainInventory[index] == null && !predicate.test(index)) {
            list.add(index);
         }
      }

      if (list.isEmpty()) {
         for (int index2 = 1; index2 < 8; index2++) {
            if (!predicate.test(index2)) {
               list.add(index2);
            }
         }
      }

      return list.isEmpty() ? OptionalInt.empty() : OptionalInt.of((Integer)list.get(new Random().nextInt(list.size())));
   }

   @Override
   public void applyInternalItemEntry(InternalItemEntry internalItemEntry) {
      if ((Boolean)BaritoneManager.getSettings().allowInventory.dataRef) {
         if (internalItemEntry.getInternalOptionType() != InternalOptionType.OUT) {
            if (this.staticListener.getEntityPlayerSP().container2 == this.staticListener.getEntityPlayerSP().container) {
               this.count++;
               if (this.getCount() >= 9) {
                  this.checkIndex(this.getCount(), 8);
               }

               int count2 = this.computeCount(Blocks.stone, ItemGoldenPickaxe.class);
               if (count2 >= 9) {
                  this.checkIndex(count2, 0);
               }

               if (this.countArray != null) {
                  this.applyTextByText("Remembering to move " + this.countArray[0] + " " + this.countArray[1] + " from a previous tick");
                  this.checkIndex(this.countArray[0], this.countArray[1]);
               }
            }
         }
      }
   }

   public RememberingToMoveManager(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
