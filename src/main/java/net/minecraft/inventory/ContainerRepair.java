package net.minecraft.inventory;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.state.IBlockState;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContainerRepair extends Container {
   private static final Logger LOGGER = LogManager.getLogger();
   private IInventory outputSlot = new InventoryCraftResult();
   private IInventory inputSlots = new ContainerRepair.DefaultInventoryBasic(this, "Repair", true, 2);
   private World theWorld;
   private BlockPos selfPosition;
   public int maximumCost;
   private int materialCost;
   private String repairedItemName;
   private final EntityPlayer thePlayer;

   public void updateItemName(String str) {
      this.repairedItemName = str;
      if (this.getSlot(2).getHasStack()) {
         ItemStack stack = this.getSlot(2).getStack();
         if (StringUtils.isBlank(str)) {
            stack.clearCustomName();
         } else {
            stack.setStackDisplayName(this.repairedItemName);
         }
      }

      this.updateRepairOutput();
   }

   @Override
   public void onCraftMatrixChanged(IInventory inventory) {
      super.onCraftMatrixChanged(inventory);
      if (inventory == this.inputSlots) {
         this.updateRepairOutput();
      }
   }

   @Override
   public void onCraftGuiOpened(ICrafting crafting) {
      super.onCraftGuiOpened(crafting);
      crafting.sendProgressBarUpdate(this, 0, this.maximumCost);
   }

   public static int accessMaterialCost(ContainerRepair containerRepair) {
      return containerRepair.materialCost;
   }

   public void updateRepairOutput() {
      ItemStack stackInSlot = this.inputSlots.getStackInSlot(0);
      this.maximumCost = 1;
      int n = 0;
      int n2 = 0;
      byte b = 0;
      if (stackInSlot == null) {
         this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
         this.maximumCost = 0;
      } else {
         ItemStack stack = stackInSlot.copy();
         ItemStack stackInSlot2 = this.inputSlots.getStackInSlot(1);
         Map enchantments = EnchantmentHelper.getEnchantments(stack);
         boolean flag = false;
         n2 = n2 + stackInSlot.getRepairCost() + (stackInSlot2 == null ? 0 : stackInSlot2.getRepairCost());
         this.materialCost = 0;
         if (stackInSlot2 != null) {
            flag = stackInSlot2.getItem() == Items.enchanted_book && Items.enchanted_book.getEnchantments(stackInSlot2).tagCount() > 0;
            if (stack.isItemStackDamageable() && stack.getItem().getIsRepairable(stackInSlot, stackInSlot2)) {
               int n3 = Math.min(stack.getItemDamage(), stack.getMaxDamage() / 4);
               if (n3 <= 0) {
                  this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
                  this.maximumCost = 0;
                  return;
               }

               int n4;
               for (n4 = 0; n3 > 0 && n4 < stackInSlot2.stackSize; n4++) {
                  stack.setItemDamage(stack.getItemDamage() - n3);
                  n++;
                  n3 = Math.min(stack.getItemDamage(), stack.getMaxDamage() / 4);
               }

               this.materialCost = n4;
            } else {
               if (!flag && (stack.getItem() != stackInSlot2.getItem() || !stack.isItemStackDamageable())) {
                  this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
                  this.maximumCost = 0;
                  return;
               }

               if (stack.isItemStackDamageable() && !flag) {
                  int n9 = (stackInSlot.getMaxDamage() - stackInSlot.getItemDamage()) + ((stackInSlot2.getMaxDamage() - stackInSlot2.getItemDamage()) + stack.getMaxDamage() * 12 / 100);
                  int n10 = stack.getMaxDamage() - n9;
                  if (n10 < 0) {
                     n10 = 0;
                  }

                  if (n10 < stack.getMetadata()) {
                     stack.setItemDamage(n10);
                     n += 2;
                  }
               }

               Map enchantments2 = EnchantmentHelper.getEnchantments(stackInSlot2);

               for (int n11 : (Iterable<Integer>)(enchantments2.keySet())) {
                  Enchantment enchantmentById = Enchantment.getEnchantmentById(n11);
                  if (enchantmentById != null) {
                     int n12 = enchantments.containsKey(n11) ? (Integer)enchantments.get(n11) : 0;
                     int n13 = (Integer)enchantments2.get(n11);
                     int n14;
                     if (n12 == n13) {
                        n14 = ++n13;
                     } else {
                        n14 = Math.max(n13, n12);
                     }

                     n13 = n14;
                     boolean flag2 = enchantmentById.canApply(stackInSlot);
                     if (this.thePlayer.capabilities.isCreativeMode || stackInSlot.getItem() == Items.enchanted_book) {
                        flag2 = true;
                     }

                     for (int n15 : (Iterable<Integer>)(enchantments.keySet())) {
                        if (n15 != n11 && !enchantmentById.canApplyTogether(Enchantment.getEnchantmentById(n15))) {
                           flag2 = false;
                           n++;
                        }
                     }

                     if (flag2) {
                        if (n14 > enchantmentById.getMaxLevel()) {
                           n13 = enchantmentById.getMaxLevel();
                        }

                        enchantments.put(n11, n13);
                        int n16 = 0;
                        switch (enchantmentById.getWeight()) {
                           case 1:
                              n16 = 8;
                              break;
                           case 2:
                              n16 = 4;
                           case 3:
                           case 4:
                           case 6:
                           case 7:
                           case 8:
                           case 9:
                           default:
                              break;
                           case 5:
                              n16 = 2;
                              break;
                           case 10:
                              n16 = 1;
                        }

                        if (flag) {
                           n16 = Math.max(1, n16 / 2);
                        }

                        n += n16 * n13;
                     }
                  }
               }
            }
         }

         if (StringUtils.isBlank(this.repairedItemName)) {
            if (stackInSlot.hasDisplayName()) {
               b = 1;
               n += b;
               stack.clearCustomName();
            }
         } else if (!this.repairedItemName.equals(stackInSlot.getDisplayName())) {
            b = 1;
            n += b;
            stack.setStackDisplayName(this.repairedItemName);
         }

         this.maximumCost = n2 + n;
         if (n <= 0) {
            stack = null;
         }

         if (b == n && b > 0 && this.maximumCost >= 40) {
            this.maximumCost = 39;
         }

         if (this.maximumCost >= 40 && !this.thePlayer.capabilities.isCreativeMode) {
            stack = null;
         }

         if (stack != null) {
            int repairCost = stack.getRepairCost();
            if (stackInSlot2 != null && repairCost < stackInSlot2.getRepairCost()) {
               repairCost = stackInSlot2.getRepairCost();
            }

            repairCost = repairCost * 2 + 1;
            stack.setRepairCost(repairCost);
            EnchantmentHelper.setEnchantments(enchantments, stack);
         }

         this.outputSlot.setInventorySlotContents(0, stack);
         this.detectAndSendChanges();
      }
   }

   public ContainerRepair(InventoryPlayer inventoryPlayer, World world, BlockPos pos, EntityPlayer player) {
      this.selfPosition = pos;
      this.theWorld = world;
      this.thePlayer = player;
      this.addSlotToContainer(new Slot(this.inputSlots, 0, 27, 47));
      this.addSlotToContainer(new Slot(this.inputSlots, 1, 76, 47));
      this.addSlotToContainer(new ContainerRepair.DefaultSlot(this, this.outputSlot, 2, 134, 47, world, pos));

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 9; j++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for (int k = 0; k < 9; k++) {
         this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 142));
      }
   }

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      if (!this.theWorld.isRemote) {
         for (int i = 0; i < this.inputSlots.getSizeInventory(); i++) {
            ItemStack stack = this.inputSlots.removeStackFromSlot(i);
            if (stack != null) {
               player.dropPlayerItemWithRandomChoice(stack, false);
            }
         }
      }
   }

   public ContainerRepair(InventoryPlayer inventoryPlayer, World world, EntityPlayer player) {
      this(inventoryPlayer, world, BlockPos.ORIGIN, player);
   }

   public static IInventory accessInputSlots(ContainerRepair containerRepair) {
      return containerRepair.inputSlots;
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.theWorld.getBlockState(this.selfPosition).getBlock() != Blocks.anvil
         ? false
         : player.getDistanceSq((double)this.selfPosition.getX() + 0.5, (double)this.selfPosition.getY() + 0.5, (double)this.selfPosition.getZ() + 0.5) <= 64.0;
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n == 2) {
            if (!this.mergeItemStack(stack2, 3, 39, true)) {
               return null;
            }

            slot.onSlotChange(stack2, stack);
         } else if (n != 0 && n != 1) {
            if (n >= 3 && n < 39 && !this.mergeItemStack(stack2, 0, 2, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(stack2, 3, 39, false)) {
            return null;
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
   public void updateProgressBar(int n, int n2) {
      if (n == 0) {
         this.maximumCost = n2;
      }
   }


   public static class DefaultInventoryBasic extends InventoryBasic {
      public final ContainerRepair containerRepair;

      public DefaultInventoryBasic(ContainerRepair containerRepair2, String str, boolean flag, int n) {
         super(str, flag, n);
         this.containerRepair = containerRepair2;
      }

      @Override
      public void markDirty() {
         super.markDirty();
         this.containerRepair.onCraftMatrixChanged(this);
      }
   }

   public static class DefaultSlot extends Slot {
      public final World world;
      public final BlockPos blockPos;
      public final ContainerRepair containerRepair;

      public DefaultSlot(ContainerRepair containerRepair2, IInventory inventory2, int n, int n2, int n3, World world2, BlockPos pos) {
         super(inventory2, n, n2, n3);
         this.containerRepair = containerRepair2;
         this.world = world2;
         this.blockPos = pos;
      }

      @Override
      public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
         if (!player.capabilities.isCreativeMode) {
            player.addExperienceLevel(-this.containerRepair.maximumCost);
         }

         ContainerRepair.accessInputSlots(this.containerRepair).setInventorySlotContents(0, (ItemStack)null);
         if (ContainerRepair.accessMaterialCost(this.containerRepair) > 0) {
            ItemStack stackInSlot = ContainerRepair.accessInputSlots(this.containerRepair).getStackInSlot(1);
            if (stackInSlot != null && stackInSlot.stackSize > ContainerRepair.accessMaterialCost(this.containerRepair)) {
               stackInSlot.stackSize = stackInSlot.stackSize - ContainerRepair.accessMaterialCost(this.containerRepair);
               ContainerRepair.accessInputSlots(this.containerRepair).setInventorySlotContents(1, stackInSlot);
            } else {
               ContainerRepair.accessInputSlots(this.containerRepair).setInventorySlotContents(1, (ItemStack)null);
            }
         } else {
            ContainerRepair.accessInputSlots(this.containerRepair).setInventorySlotContents(1, (ItemStack)null);
         }

         this.containerRepair.maximumCost = 0;
         IBlockState blockState = this.world.getBlockState(this.blockPos);
         if (!player.capabilities.isCreativeMode && !this.world.isRemote && blockState.getBlock() == Blocks.anvil && player.getRNG().nextFloat() < 0.12F) {
            int value = (Integer)blockState.getValue(BlockAnvil.DAMAGE);
            if (++value > 2) {
               this.world.setBlockToAir(this.blockPos);
               this.world.playAuxSFX(1020, this.blockPos, 0);
            } else {
               this.world.setBlockState(this.blockPos, blockState.withProperty(BlockAnvil.DAMAGE, value), 2);
               this.world.playAuxSFX(1021, this.blockPos, 0);
            }
         } else if (!this.world.isRemote) {
            this.world.playAuxSFX(1021, this.blockPos, 0);
         }
      }

      @Override
      public boolean canTakeStack(EntityPlayer player) {
         return (player.capabilities.isCreativeMode || player.experienceLevel >= this.containerRepair.maximumCost) && this.containerRepair.maximumCost > 0 && this.getHasStack();
      }

      @Override
      public boolean isItemValid(ItemStack stack) {
         return false;
      }
   }
}
