package client.util;

import client.setting.GlobalOptionType;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenHoe;
import net.minecraft.item.ItemGoldenPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Items;

public class AuxiliarySlotAdapter extends Slot {
   public final ContainerCraftingIInventory containerCraftingIInventory;
   public final EntityPlayer entityPlayer;
   public int count;

   @Override
   public void processAction(ItemStack stack, int count2) {
      this.count += count2;
      this.processAction4(stack);
   }

   @Override
   public void processAction(EntityPlayer player, ItemStack stack) {
      this.processAction4(stack);
      ItemStack[] stackArray = ExtendedManager.getExtendedManager().resolveItemStackArrayByContainerCraftin(this.containerCraftingIInventory, player.worldObj);

      for (int index = 0; index < stackArray.length; index++) {
         ItemStack stack2 = this.containerCraftingIInventory.processAction3(index);
         ItemStack stack3 = stackArray[index];
         if (stack2 != null) {
            this.containerCraftingIInventory.getItemStack(index, 1);
         }

         if (stack3 != null) {
            if (this.containerCraftingIInventory.processAction3(index) == null) {
               this.containerCraftingIInventory.processAction(index, stack3);
            } else if (!this.entityPlayer.inventoryPlayer.checkItemStack3(stack3)) {
               this.entityPlayer.resolveEntityItem(stack3, false);
            }
         }
      }
   }

   @Override
   public ItemStack decrStackSize(int count2) {
      if (this.isActive()) {
         this.count = this.count + Math.min(count2, this.getStack().animationsToGo);
      }

      return super.decrStackSize(count2);
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return false;
   }

   @Override
   public void processAction4(ItemStack stack) {
      if (this.count > 0) {
         stack.processAction2(this.entityPlayer.worldObj, this.entityPlayer, this.count);
      }

      this.count = 0;
      if (stack.getItem() == Item.processAction2(Blocks.block46)) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter29);
      }

      if (stack.getItem() instanceof ItemGoldenPickaxe) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter33);
      }

      if (stack.getItem() == Item.processAction2(Blocks.block39)) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter10);
      }

      if (stack.getItem() instanceof ItemGoldenHoe) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter15);
      }

      if (stack.getItem() == Items.bread) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter17);
      }

      if (stack.getItem() == Items.cake) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter19);
      }

      if (stack.getItem() instanceof ItemGoldenPickaxe && ((ItemGoldenPickaxe)stack.getItem()).getGlobalOptionType() != GlobalOptionType.WOOD) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter16);
      }

      if (stack.getItem() instanceof ItemSword) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter23);
      }

      if (stack.getItem() == Item.processAction2(Blocks.block102)) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter7);
      }

      if (stack.getItem() == Item.processAction2(Blocks.block8)) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter9);
      }

      if (stack.getItem() == Items.goldenApple && stack.getIndexOffset() == 1) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter2);
      }
   }

   public AuxiliarySlotAdapter(EntityPlayer player, ContainerCraftingIInventory containerCraftingIInventory2, IInventory iInventory, int count2, int count3, int count4) {
      super(iInventory, count2, count3, count4);
      this.entityPlayer = player;
      this.containerCraftingIInventory = containerCraftingIInventory2;
   }
}
