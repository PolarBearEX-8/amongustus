package net.minecraft.inventory;

import client.event.CoreListener;
import client.util.EnchantmentManager;
import client.util.LvlManager;
import client.util.ResultIInventory;
import java.util.Map;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepairContainer extends Container {
   public static final Logger logger = LogManager.getLogger();
   public IInventory iInventory = new ResultIInventory();
   public IInventory iInventory2 = new InventoryLargeChestImpl(this, "Repair", true, 2);
   public World world;
   public BlockPos blockPos;
   public int count;
   public int count2;
   public String labelText;
   public final EntityPlayer entityPlayer;

   public void applyText(String text) {
      this.labelText = text;
      if (this.getSlot(2).isActive()) {
         ItemStack stack = this.getSlot(2).getStack();
         if (StringUtils.isBlank(text)) {
            stack.updateState();
         } else {
            stack.resolveItemStack(this.labelText);
         }
      }

      this.updateState();
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.world.getBlockState(this.blockPos).getBlock() != Blocks.block51
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   public void updateState() {
      ItemStack stack = this.iInventory2.processAction3(0);
      this.count = 1;
      int count3 = 0;
      byte byteValue = 0;
      byte byteValue2 = 0;
      if (stack == null) {
         this.iInventory.processAction(0, (ItemStack)null);
         this.count = 0;
      } else {
         ItemStack stack2 = stack.getItemStack();
         ItemStack stack3 = this.iInventory2.processAction3(1);
         Map map = LvlManager.resolveMap(stack2);
         int count4 = byteValue + stack.getCount() + (stack3 == null ? 0 : stack3.getCount());
         this.count2 = 0;
         if (stack3 != null) {
            boolean flag = stack3.getItem() == Items.enchantedBook && Items.enchantedBook.resolveNBTTagList(stack3).getInt() > 0;
            if (stack2.isActive3() && stack2.getItem().processAction(stack, stack3)) {
               int count5 = Math.min(stack2.getIndexOffset2(), stack2.processAction() / 4);
               if (count5 <= 0) {
                  this.iInventory.processAction(0, (ItemStack)null);
                  this.count = 0;
                  return;
               }

               int count6;
               for (count6 = 0; count5 > 0 && count6 < stack3.animationsToGo; count6++) {
                  int count7 = stack2.getIndexOffset2() - count5;
                  stack2.applyIndexByIndex(count7);
                  count3++;
                  count5 = Math.min(stack2.getIndexOffset2(), stack2.processAction() / 4);
               }

               this.count2 = count6;
            } else {
               if (!flag && (stack2.getItem() != stack3.getItem() || !stack2.isActive3())) {
                  this.iInventory.processAction(0, (ItemStack)null);
                  this.count = 0;
                  return;
               }

               if (stack2.isActive3() && !flag) {
                  int count8 = stack.processAction() - stack.getIndexOffset2();
                  int count9 = stack3.processAction() - stack3.getIndexOffset2() + stack2.processAction() * 12 / 100;
                  int count10 = count8 + count9;
                  int count11 = stack2.processAction() - count10;
                  if (count11 < 0) {
                     count11 = 0;
                  }

                  if (count11 < stack2.getIndexOffset()) {
                     stack2.applyIndexByIndex(count11);
                     count3 += 2;
                  }
               }

               Map map2 = LvlManager.resolveMap(stack3);

               for (int count12 : (Iterable<Integer>)(Iterable)map2.keySet()) {
                  EnchantmentManager enchantmentManager = EnchantmentManager.resolveEnchantmentManager(count12);
                  if (enchantmentManager != null) {
                     int count13 = map.containsKey(count12) ? (Integer)map.get(count12) : 0;
                     int count14 = (Integer)map2.get(count12);
                     if (count13 == count14) {
                        count14++;
                     } else {
                        count14 = Math.max(count14, count13);
                     }

                     boolean flag2 = enchantmentManager.matchesItemStack(stack);
                     if (this.entityPlayer.invulnerableManager.enabled2 || stack.getItem() == Items.enchantedBook) {
                        flag2 = true;
                     }

                     for (int count15 : (Iterable<Integer>)(Iterable)map.keySet()) {
                        if (count15 != count12 && !enchantmentManager.matchesEnchantmentManager(EnchantmentManager.resolveEnchantmentManager(count15))) {
                           flag2 = false;
                           count3++;
                        }
                     }

                     if (flag2) {
                        if (count14 > enchantmentManager.getCount()) {
                           count14 = enchantmentManager.getCount();
                        }

                        map.put(count12, count14);
                        int count16 = 0;
                        switch (enchantmentManager.getCountAsIndex()) {
                           case 1:
                              count16 = 8;
                              break;
                           case 2:
                              count16 = 4;
                           case 3:
                           case 4:
                           case 6:
                           case 7:
                           case 8:
                           case 9:
                           default:
                              break;
                           case 5:
                              count16 = 2;
                              break;
                           case 10:
                              count16 = 1;
                        }

                        if (flag) {
                           count16 = Math.max(1, count16 / 2);
                        }

                        count3 += count16 * count14;
                     }
                  }
               }
            }
         }

         if (StringUtils.isBlank(this.labelText)) {
            if (stack.isActive()) {
               byteValue2 = 1;
               count3++;
               stack2.updateState();
            }
         } else if (!this.labelText.equals(stack.getString())) {
            byteValue2 = 1;
            count3++;
            stack2.resolveItemStack(this.labelText);
         }

         this.count = count4 + count3;
         if (count3 <= 0) {
            stack2 = null;
         }

         if (byteValue2 == count3 && byteValue2 > 0 && this.count >= 40) {
            this.count = 39;
         }

         if (this.count >= 40 && !this.entityPlayer.invulnerableManager.enabled2) {
            stack2 = null;
         }

         if (stack2 != null) {
            int count17 = stack2.getCount();
            if (stack3 != null && count17 < stack3.getCount()) {
               count17 = stack3.getCount();
            }

            int count18 = count17 * 2 + 1;
            stack2.applyIndex(count18);
            LvlManager.applyMap(map, stack2);
         }

         this.iInventory.processAction(0, stack2);
         this.detectAndSendChanges();
      }
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);
      if (!this.world.state) {
         for (int index = 0; index < this.iInventory2.processAction4(); index++) {
            ItemStack stackInSlot = this.iInventory2.getStackInSlot(index);
            if (stackInSlot != null) {
               player.resolveEntityItem(stackInSlot, false);
            }
         }
      }
   }

   public RepairContainer(InventoryPlayer inventoryPlayer, World world2, BlockPos blockPos2, EntityPlayer player) {
      this.blockPos = blockPos2;
      this.world = world2;
      this.entityPlayer = player;
      this.resolveSlot(new Slot(this.iInventory2, 0, 27, 47));
      this.resolveSlot(new Slot(this.iInventory2, 1, 76, 47));
      this.resolveSlot(new WorldSlot(this, this.iInventory, 2, 134, 47, world2, blockPos2));

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 9; index2++) {
            this.resolveSlot(new Slot(inventoryPlayer, index2 + index * 9 + 9, 8 + index2 * 18, 84 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 9; index3++) {
         this.resolveSlot(new Slot(inventoryPlayer, index3, 8 + index3 * 18, 142));
      }
   }

   public static int computeCount(RepairContainer repairContainer) {
      return repairContainer.count2;
   }

   @Override
   public void processAction2(int count3, int count4) {
      if (count3 == 0) {
         this.count = count4;
      }
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count3) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count3);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count3 == 2) {
            if (!this.matchesItemStack(stack2, 3, 39, true)) {
               return null;
            }

            slot.applyItemStack(stack2, stack);
         } else if (count3 != 0 && count3 != 1) {
            if (count3 >= 3 && count3 < 39 && !this.matchesItemStack(stack2, 0, 2, false)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 3, 39, false)) {
            return null;
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

   public RepairContainer(InventoryPlayer inventoryPlayer, World world2, EntityPlayer player) {
      this(inventoryPlayer, world2, BlockPos.origin, player);
   }

   @Override
   public void processAction(IInventory iInventory3) {
      super.processAction(iInventory3);
      if (iInventory3 == this.iInventory2) {
         this.updateState();
      }
   }

   public static IInventory resolveIInventory(RepairContainer repairContainer) {
      return repairContainer.iInventory2;
   }

   @Override
   public void applyCoreListener(CoreListener coreListener) {
      super.applyCoreListener(coreListener);
      coreListener.applyContainer(this, 0, this.count);
   }
}
