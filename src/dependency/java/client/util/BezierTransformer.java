package client.util;

import client.event.CoreListener;
import client.setting.LocalOptionType;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.BezierTransformerInventoryLargeChest;
import net.minecraft.inventory.BezierTransformerSlot;
import net.minecraft.inventory.BezierTransformerSlotImpl;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BezierTransformer extends Container {
   public IInventory iInventory = new BezierTransformerInventoryLargeChest(this, "Enchant", true, 2);
   public World world;
   public BlockPos blockPos;
   public Random random = new Random();
   public int count;
   public int[] countArray = new int[3];
   public int[] countArray2 = new int[]{-1, -1, -1};

   private List resolveList(ItemStack stack, int count2, int count3) {
      this.random.setSeed((long)(this.count + count2));
      List list = LvlManager.resolveList(this.random, stack, count3);
      if (stack.getItem() == Items.book && list != null && list.size() > 1) {
         list.remove(this.random.nextInt(list.size()));
      }

      return list;
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);
      if (!this.world.state) {
         for (int index = 0; index < this.iInventory.processAction4(); index++) {
            ItemStack stackInSlot = this.iInventory.getStackInSlot(index);
            if (stackInSlot != null) {
               player.resolveEntityItem(stackInSlot, false);
            }
         }
      }
   }

   @Override
   public void processAction(IInventory iInventory2) {
      if (iInventory2 == this.iInventory) {
         ItemStack stack = iInventory2.processAction3(0);
         if (stack != null && stack.isActive2()) {
            if (!this.world.state) {
               int count2 = 0;

               for (int index = -1; index <= 1; index++) {
                  for (int index2 = -1; index2 <= 1; index2++) {
                     if ((index != 0 || index2 != 0)
                        && this.world.isAirBlock(this.blockPos.add(index2, 0, index))
                        && this.world.isAirBlock(this.blockPos.add(index2, 1, index))) {
                        if (this.world.getBlockState(this.blockPos.add(index2 * 2, 0, index * 2)).getBlock() == Blocks.block8) {
                           count2++;
                        }

                        if (this.world.getBlockState(this.blockPos.add(index2 * 2, 1, index * 2)).getBlock() == Blocks.block8) {
                           count2++;
                        }

                        if (index2 != 0 && index != 0) {
                           if (this.world.getBlockState(this.blockPos.add(index2 * 2, 0, index)).getBlock() == Blocks.block8) {
                              count2++;
                           }

                           if (this.world.getBlockState(this.blockPos.add(index2 * 2, 1, index)).getBlock() == Blocks.block8) {
                              count2++;
                           }

                           if (this.world.getBlockState(this.blockPos.add(index2, 0, index * 2)).getBlock() == Blocks.block8) {
                              count2++;
                           }

                           if (this.world.getBlockState(this.blockPos.add(index2, 1, index * 2)).getBlock() == Blocks.block8) {
                              count2++;
                           }
                        }
                     }
                  }
               }

               this.random.setSeed((long)this.count);

               for (int index3 = 0; index3 < 3; index3++) {
                  this.countArray[index3] = LvlManager.computeCount(this.random, index3, count2, stack);
                  this.countArray2[index3] = -1;
                  if (this.countArray[index3] < index3 + 1) {
                     this.countArray[index3] = 0;
                  }
               }

               for (int index4 = 0; index4 < 3; index4++) {
                  if (this.countArray[index4] > 0) {
                     List list = this.resolveList(stack, index4, this.countArray[index4]);
                     if (list != null && !list.isEmpty()) {
                        AuxiliaryClientManagerResolver auxiliaryClientManagerResolver = (AuxiliaryClientManagerResolver)list.get(this.random.nextInt(list.size()));
                        this.countArray2[index4] = auxiliaryClientManagerResolver.enchantmentManager.indexOffset | auxiliaryClientManagerResolver.count2 << 8;
                     }
                  }
               }

               this.detectAndSendChanges();
            }
         } else {
            for (int index5 = 0; index5 < 3; index5++) {
               this.countArray[index5] = 0;
               this.countArray2[index5] = -1;
            }
         }
      }
   }

   public BezierTransformer(InventoryPlayer inventoryPlayer, World world2, BlockPos blockPos2) {
      this.world = world2;
      this.blockPos = blockPos2;
      this.count = inventoryPlayer.targetPlayer.getIndexOffset6();
      this.resolveSlot(new BezierTransformerSlot(this, this.iInventory, 0, 15, 47));
      this.resolveSlot(new BezierTransformerSlotImpl(this, this.iInventory, 1, 35, 47));

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 9; index2++) {
            this.resolveSlot(new Slot(inventoryPlayer, index2 + index * 9 + 9, 8 + index2 * 18, 84 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 9; index3++) {
         this.resolveSlot(new Slot(inventoryPlayer, index3, 8 + index3 * 18, 142));
      }
   }

   @Override
   public boolean processAction(EntityPlayer player, int count2) {
      ItemStack stack = this.iInventory.processAction3(0);
      ItemStack stack2 = this.iInventory.processAction3(1);
      int count3 = count2 + 1;
      if ((stack2 == null || stack2.animationsToGo < count3) && !player.invulnerableManager.enabled2) {
         return false;
      } else if (this.countArray[count2] > 0
         && stack != null
         && (player.count25 >= count3 && player.count25 >= this.countArray[count2] || player.invulnerableManager.enabled2)) {
         if (!this.world.state) {
            List list = this.resolveList(stack, count2, this.countArray[count2]);
            boolean flag = stack.getItem() == Items.book;
            if (list != null) {
               player.applyIndex9(count3);
               if (flag) {
                  stack.setItem(Items.enchantedBook);
               }

               for (int index = 0; index < list.size(); index++) {
                  AuxiliaryClientManagerResolver auxiliaryClientManagerResolver = (AuxiliaryClientManagerResolver)list.get(index);
                  if (flag) {
                     Items.enchantedBook.applyItemStack(stack, auxiliaryClientManagerResolver);
                  } else {
                     stack.applyEnchantmentManager(auxiliaryClientManagerResolver.enchantmentManager, auxiliaryClientManagerResolver.count2);
                  }
               }

               if (!player.invulnerableManager.enabled2) {
                  stack2.animationsToGo -= count3;
                  if (stack2.animationsToGo <= 0) {
                     this.iInventory.processAction(1, (ItemStack)null);
                  }
               }

               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager22);
               this.iInventory.processAction5();
               this.count = player.getIndexOffset6();
               this.processAction(this.iInventory);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.world.getBlockState(this.blockPos).getBlock() != Blocks.block102
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void processAction2(int count2, int count3) {
      if (count2 >= 0 && count2 <= 2) {
         this.countArray[count2] = count3;
      } else if (count2 == 3) {
         this.count = count3;
      } else if (count2 >= 4 && count2 <= 6) {
         this.countArray2[count2 - 4] = count3;
      } else {
         super.processAction2(count2, count3);
      }
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count2) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count2);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count2 == 0) {
            if (!this.matchesItemStack(stack2, 2, 38, true)) {
               return null;
            }
         } else if (count2 == 1) {
            if (!this.matchesItemStack(stack2, 2, 38, true)) {
               return null;
            }
         } else if (stack2.getItem() == Items.dye && LocalOptionType.resolveLocalOptionTypeByIndex(stack2.getIndexOffset()) == LocalOptionType.BLUE) {
            if (!this.matchesItemStack(stack2, 1, 2, true)) {
               return null;
            }
         } else {
            if (((Slot)this.itemList2.get(0)).isActive() || !((Slot)this.itemList2.get(0)).isItemValid(stack2)) {
               return null;
            }

            if (stack2.canExecute() && stack2.animationsToGo == 1) {
               ((Slot)this.itemList2.get(0)).putStack(stack2.getItemStack());
               stack2.animationsToGo = 0;
            } else if (stack2.animationsToGo >= 1) {
               ((Slot)this.itemList2.get(0)).putStack(new ItemStack(stack2.getItem(), 1, stack2.getIndexOffset()));
               stack2.animationsToGo--;
            }
         }

         if (stack2.animationsToGo == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if (stack2.animationsToGo == stack.animationsToGo) {
            return null;
         }

         slot.processAction(player, stack2);
      }

      return stack;
   }

   @Override
   public void applyCoreListener(CoreListener coreListener) {
      super.applyCoreListener(coreListener);
      coreListener.applyContainer(this, 0, this.countArray[0]);
      coreListener.applyContainer(this, 1, this.countArray[1]);
      coreListener.applyContainer(this, 2, this.countArray[2]);
      coreListener.applyContainer(this, 3, this.count & -16);
      coreListener.applyContainer(this, 4, this.countArray2[0]);
      coreListener.applyContainer(this, 5, this.countArray2[1]);
      coreListener.applyContainer(this, 6, this.countArray2[2]);
   }

   public int getCount() {
      ItemStack stack = this.iInventory.processAction3(1);
      return stack == null ? 0 : stack.animationsToGo;
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int index = 0; index < this.itemList3.size(); index++) {
         CoreListener coreListener = (CoreListener)this.itemList3.get(index);
         coreListener.applyContainer(this, 0, this.countArray[0]);
         coreListener.applyContainer(this, 1, this.countArray[1]);
         coreListener.applyContainer(this, 2, this.countArray[2]);
         coreListener.applyContainer(this, 3, this.count & -16);
         coreListener.applyContainer(this, 4, this.countArray2[0]);
         coreListener.applyContainer(this, 5, this.countArray2[1]);
         coreListener.applyContainer(this, 6, this.countArray2[2]);
      }
   }

   public BezierTransformer(InventoryPlayer inventoryPlayer, World world2) {
      this(inventoryPlayer, world2, BlockPos.origin);
   }
}
