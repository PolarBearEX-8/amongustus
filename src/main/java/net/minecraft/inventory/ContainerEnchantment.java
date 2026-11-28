package net.minecraft.inventory;

import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerEnchantment extends Container {
   public IInventory tableInventory = new ContainerEnchantment.DefaultInventoryBasic(this, "Enchant", true, 2);
   private World worldPointer;
   private BlockPos position;
   private Random rand = new Random();
   public int xpSeed;
   public int[] enchantLevels = new int[3];
   public int[] enchantClue = new int[]{-1, -1, -1};

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n == 0) {
            if (!this.mergeItemStack(stack2, 2, 38, true)) {
               return null;
            }
         } else if (n == 1) {
            if (!this.mergeItemStack(stack2, 2, 38, true)) {
               return null;
            }
         } else if (stack2.getItem() == Items.dye && EnumDyeColor.byDyeDamage(stack2.getMetadata()) == EnumDyeColor.BLUE) {
            if (!this.mergeItemStack(stack2, 1, 2, true)) {
               return null;
            }
         } else {
            if (((Slot)this.inventorySlots.get(0)).getHasStack() || !((Slot)this.inventorySlots.get(0)).isItemValid(stack2)) {
               return null;
            }

            if (stack2.hasTagCompound() && stack2.stackSize == 1) {
               ((Slot)this.inventorySlots.get(0)).putStack(stack2.copy());
               stack2.stackSize = 0;
            } else if (stack2.stackSize >= 1) {
               ((Slot)this.inventorySlots.get(0)).putStack(new ItemStack(stack2.getItem(), 1, stack2.getMetadata()));
               stack2.stackSize--;
            }
         }

         if (stack2.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if (stack2.stackSize == stack.stackSize) {
            return null;
         }

         slot.onPickupFromSlot(player, stack2);
      }

      return stack;
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.worldPointer.getBlockState(this.position).getBlock() != Blocks.enchanting_table
         ? false
         : player.getDistanceSq((double)this.position.getX() + 0.5, (double)this.position.getY() + 0.5, (double)this.position.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      if (!this.worldPointer.isRemote) {
         for (int i = 0; i < this.tableInventory.getSizeInventory(); i++) {
            ItemStack stack = this.tableInventory.removeStackFromSlot(i);
            if (stack != null) {
               player.dropPlayerItemWithRandomChoice(stack, false);
            }
         }
      }
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int i = 0; i < this.crafters.size(); i++) {
         ICrafting crafting = (ICrafting)this.crafters.get(i);
         crafting.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
         crafting.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
         crafting.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
         crafting.sendProgressBarUpdate(this, 3, this.xpSeed & -16);
         crafting.sendProgressBarUpdate(this, 4, this.enchantClue[0]);
         crafting.sendProgressBarUpdate(this, 5, this.enchantClue[1]);
         crafting.sendProgressBarUpdate(this, 6, this.enchantClue[2]);
      }
   }

   public ContainerEnchantment(InventoryPlayer inventoryPlayer, World world, BlockPos pos) {
      this.worldPointer = world;
      this.position = pos;
      this.xpSeed = inventoryPlayer.player.getXPSeed();
      this.addSlotToContainer(new ContainerEnchantment.DefaultSlot(this, this.tableInventory, 0, 15, 47));
      this.addSlotToContainer(new ContainerEnchantment.DefaultSlot2(this, this.tableInventory, 1, 35, 47));

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 9; j++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for (int k = 0; k < 9; k++) {
         this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 142));
      }
   }

   public ContainerEnchantment(InventoryPlayer inventoryPlayer, World world) {
      this(inventoryPlayer, world, BlockPos.ORIGIN);
   }

   @Override
   public void updateProgressBar(int n, int n2) {
      if (n >= 0 && n <= 2) {
         this.enchantLevels[n] = n2;
      } else if (n == 3) {
         this.xpSeed = n2;
      } else if (n >= 4 && n <= 6) {
         this.enchantClue[n - 4] = n2;
      } else {
         super.updateProgressBar(n, n2);
      }
   }

   public int getLapisAmount() {
      ItemStack stackInSlot = this.tableInventory.getStackInSlot(1);
      return stackInSlot == null ? 0 : stackInSlot.stackSize;
   }

   @Override
   public void onCraftGuiOpened(ICrafting crafting) {
      super.onCraftGuiOpened(crafting);
      crafting.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
      crafting.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
      crafting.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
      crafting.sendProgressBarUpdate(this, 3, this.xpSeed & -16);
      crafting.sendProgressBarUpdate(this, 4, this.enchantClue[0]);
      crafting.sendProgressBarUpdate(this, 5, this.enchantClue[1]);
      crafting.sendProgressBarUpdate(this, 6, this.enchantClue[2]);
   }

   @Override
   public boolean enchantItem(EntityPlayer player, int n) {
      ItemStack stackInSlot = this.tableInventory.getStackInSlot(0);
      ItemStack stackInSlot2 = this.tableInventory.getStackInSlot(1);
      int n2 = n + 1;
      if ((stackInSlot2 == null || stackInSlot2.stackSize < n2) && !player.capabilities.isCreativeMode) {
         return false;
      } else if (this.enchantLevels[n] > 0
         && stackInSlot != null
         && (player.experienceLevel >= n2 && player.experienceLevel >= this.enchantLevels[n] || player.capabilities.isCreativeMode)) {
         if (!this.worldPointer.isRemote) {
            List enchantmentList = this.getEnchantmentList(stackInSlot, n, this.enchantLevels[n]);
            boolean flag = stackInSlot.getItem() == Items.book;
            if (enchantmentList != null) {
               player.removeExperienceLevel(n2);
               if (flag) {
                  stackInSlot.setItem(Items.enchanted_book);
               }

               for (int i = 0; i < enchantmentList.size(); i++) {
                  EnchantmentData enchantmentData = (EnchantmentData)enchantmentList.get(i);
                  if (flag) {
                     Items.enchanted_book.addEnchantment(stackInSlot, enchantmentData);
                  } else {
                     stackInSlot.addEnchantment(enchantmentData.enchantmentobj, enchantmentData.enchantmentLevel);
                  }
               }

               if (!player.capabilities.isCreativeMode) {
                  stackInSlot2.stackSize -= n2;
                  if (stackInSlot2.stackSize <= 0) {
                     this.tableInventory.setInventorySlotContents(1, (ItemStack)null);
                  }
               }

               player.triggerAchievement(StatList.itemEnchanted);
               this.tableInventory.markDirty();
               this.xpSeed = player.getXPSeed();
               this.onCraftMatrixChanged(this.tableInventory);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void onCraftMatrixChanged(IInventory inventory) {
      if (inventory == this.tableInventory) {
         ItemStack stackInSlot = inventory.getStackInSlot(0);
         if (stackInSlot != null && stackInSlot.isItemEnchantable()) {
            if (!this.worldPointer.isRemote) {
               int n = 0;

               for (int i = -1; i <= 1; i++) {
                  for (int j = -1; j <= 1; j++) {
                     if ((i != 0 || j != 0)
                        && this.worldPointer.isAirBlock(this.position.add(j, 0, i))
                        && this.worldPointer.isAirBlock(this.position.add(j, 1, i))) {
                        if (this.worldPointer.getBlockState(this.position.add(j * 2, 0, i * 2)).getBlock() == Blocks.bookshelf) {
                           n++;
                        }

                        if (this.worldPointer.getBlockState(this.position.add(j * 2, 1, i * 2)).getBlock() == Blocks.bookshelf) {
                           n++;
                        }

                        if (j != 0 && i != 0) {
                           if (this.worldPointer.getBlockState(this.position.add(j * 2, 0, i)).getBlock() == Blocks.bookshelf) {
                              n++;
                           }

                           if (this.worldPointer.getBlockState(this.position.add(j * 2, 1, i)).getBlock() == Blocks.bookshelf) {
                              n++;
                           }

                           if (this.worldPointer.getBlockState(this.position.add(j, 0, i * 2)).getBlock() == Blocks.bookshelf) {
                              n++;
                           }

                           if (this.worldPointer.getBlockState(this.position.add(j, 1, i * 2)).getBlock() == Blocks.bookshelf) {
                              n++;
                           }
                        }
                     }
                  }
               }

               this.rand.setSeed((long)this.xpSeed);

               for (int k = 0; k < 3; k++) {
                  this.enchantLevels[k] = EnchantmentHelper.calcItemStackEnchantability(this.rand, k, n, stackInSlot);
                  this.enchantClue[k] = -1;
                  if (this.enchantLevels[k] < k + 1) {
                     this.enchantLevels[k] = 0;
                  }
               }

               for (int m = 0; m < 3; m++) {
                  if (this.enchantLevels[m] > 0) {
                     List enchantmentList = this.getEnchantmentList(stackInSlot, m, this.enchantLevels[m]);
                     if (enchantmentList != null && !enchantmentList.isEmpty()) {
                        EnchantmentData enchantmentData = (EnchantmentData)enchantmentList.get(this.rand.nextInt(enchantmentList.size()));
                        this.enchantClue[m] = enchantmentData.enchantmentobj.effectId | enchantmentData.enchantmentLevel << 8;
                     }
                  }
               }

               this.detectAndSendChanges();
            }
         } else {
            for (int idx = 0; idx < 3; idx++) {
               this.enchantLevels[idx] = 0;
               this.enchantClue[idx] = -1;
            }
         }
      }
   }

   private List getEnchantmentList(ItemStack stack, int n, int n2) {
      this.rand.setSeed((long)(this.xpSeed + n));
      List list = EnchantmentHelper.buildEnchantmentList(this.rand, stack, n2);
      if (stack.getItem() == Items.book && list != null && list.size() > 1) {
         list.remove(this.rand.nextInt(list.size()));
      }

      return list;
   }


   public static class DefaultInventoryBasic extends InventoryBasic {
      public final ContainerEnchantment containerEnchantment;

      @Override
      public void markDirty() {
         super.markDirty();
         this.containerEnchantment.onCraftMatrixChanged(this);
      }

      public DefaultInventoryBasic(ContainerEnchantment containerEnchantment2, String str, boolean flag, int n) {
         super(str, flag, n);
         this.containerEnchantment = containerEnchantment2;
      }

      @Override
      public int getInventoryStackLimit() {
         return 64;
      }
   }

   public static class DefaultSlot extends Slot {
      public final ContainerEnchantment containerEnchantment;

      @Override
      public boolean isItemValid(ItemStack stack) {
         return true;
      }

      public DefaultSlot(ContainerEnchantment containerEnchantment2, IInventory inventory2, int n, int n2, int n3) {
         super(inventory2, n, n2, n3);
         this.containerEnchantment = containerEnchantment2;
      }

      @Override
      public int getSlotStackLimit() {
         return 1;
      }
   }

   public static class DefaultSlot2 extends Slot {
      public final ContainerEnchantment containerEnchantment;

      @Override
      public boolean isItemValid(ItemStack stack) {
         return stack.getItem() == Items.dye && EnumDyeColor.byDyeDamage(stack.getMetadata()) == EnumDyeColor.BLUE;
      }

      public DefaultSlot2(ContainerEnchantment containerEnchantment2, IInventory inventory2, int n, int n2, int n3) {
         super(inventory2, n, n2, n3);
         this.containerEnchantment = containerEnchantment2;
      }
   }
}
