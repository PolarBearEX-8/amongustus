package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.stats.AchievementList;

public class SlotCrafting extends Slot {
   private final InventoryCrafting craftMatrix;
   private final EntityPlayer thePlayer;
   private int amountCrafted;

   public SlotCrafting(EntityPlayer player, InventoryCrafting inventoryCrafting, IInventory inventory2, int n, int n2, int n3) {
      super(inventory2, n, n2, n3);
      this.thePlayer = player;
      this.craftMatrix = inventoryCrafting;
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return false;
   }

   @Override
   public void onCrafting(ItemStack stack, int n) {
      this.amountCrafted += n;
      this.onCrafting(stack);
   }

   @Override
   public void onCrafting(ItemStack stack) {
      if (this.amountCrafted > 0) {
         stack.onCrafting(this.thePlayer.entityWorld, this.thePlayer, this.amountCrafted);
      }

      this.amountCrafted = 0;
      if (stack.getItem() == Item.getItemFromBlock(Blocks.crafting_table)) {
         this.thePlayer.triggerAchievement(AchievementList.buildWorkBench);
      }

      if (stack.getItem() instanceof ItemPickaxe) {
         this.thePlayer.triggerAchievement(AchievementList.buildPickaxe);
      }

      if (stack.getItem() == Item.getItemFromBlock(Blocks.furnace)) {
         this.thePlayer.triggerAchievement(AchievementList.buildFurnace);
      }

      if (stack.getItem() instanceof ItemHoe) {
         this.thePlayer.triggerAchievement(AchievementList.buildHoe);
      }

      if (stack.getItem() == Items.bread) {
         this.thePlayer.triggerAchievement(AchievementList.makeBread);
      }

      if (stack.getItem() == Items.cake) {
         this.thePlayer.triggerAchievement(AchievementList.bakeCake);
      }

      if (stack.getItem() instanceof ItemPickaxe && ((ItemPickaxe)stack.getItem()).getToolMaterial() != Item.ToolMaterial.WOOD) {
         this.thePlayer.triggerAchievement(AchievementList.buildBetterPickaxe);
      }

      if (stack.getItem() instanceof ItemSword) {
         this.thePlayer.triggerAchievement(AchievementList.buildSword);
      }

      if (stack.getItem() == Item.getItemFromBlock(Blocks.enchanting_table)) {
         this.thePlayer.triggerAchievement(AchievementList.enchantments);
      }

      if (stack.getItem() == Item.getItemFromBlock(Blocks.bookshelf)) {
         this.thePlayer.triggerAchievement(AchievementList.bookcase);
      }

      if (stack.getItem() == Items.golden_apple && stack.getMetadata() == 1) {
         this.thePlayer.triggerAchievement(AchievementList.overpowered);
      }
   }

   @Override
   public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
      this.onCrafting(stack);
      ItemStack[] remainingItems = CraftingManager.getInstance().getRemainingItems(this.craftMatrix, player.entityWorld);

      for (int i = 0; i < remainingItems.length; i++) {
         ItemStack stackInSlot = this.craftMatrix.getStackInSlot(i);
         ItemStack stack2 = remainingItems[i];
         if (stackInSlot != null) {
            this.craftMatrix.decrStackSize(i, 1);
         }

         if (stack2 != null) {
            if (this.craftMatrix.getStackInSlot(i) == null) {
               this.craftMatrix.setInventorySlotContents(i, stack2);
            } else if (!this.thePlayer.inventory.addItemStackToInventory(stack2)) {
               this.thePlayer.dropPlayerItemWithRandomChoice(stack2, false);
            }
         }
      }
   }

   @Override
   public ItemStack decrStackSize(int n) {
      if (this.getHasStack()) {
         this.amountCrafted = this.amountCrafted + Math.min(n, this.getStack().stackSize);
      }

      return super.decrStackSize(n);
   }
}
