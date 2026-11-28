package net.minecraft.inventory;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

public class SlotFurnaceOutput extends Slot {
   private EntityPlayer thePlayer;
   private int removeCount;

   @Override
   public void onCrafting(ItemStack stack) {
      stack.onCrafting(this.thePlayer.entityWorld, this.thePlayer, this.removeCount);
      if (!this.thePlayer.entityWorld.isRemote) {
         int removeCount2 = this.removeCount;
         float smeltingExperience = FurnaceRecipes.instance().getSmeltingExperience(stack);
         if (smeltingExperience == 0.0F) {
            removeCount2 = 0;
         } else if (smeltingExperience < 1.0F) {
            int n = MathHelper.floor_float((float)removeCount2 * smeltingExperience);
            if (n < MathHelper.ceiling_float_int((float)removeCount2 * smeltingExperience) && Math.random() < (double)((float)removeCount2 * smeltingExperience - (float)n)) {
               n++;
            }

            removeCount2 = n;
         }

         while (removeCount2 > 0) {
            int xpSplit = EntityXPOrb.getXPSplit(removeCount2);
            removeCount2 -= xpSplit;
            this.thePlayer
               .entityWorld
               .spawnEntityInWorld(new EntityXPOrb(this.thePlayer.entityWorld, this.thePlayer.posX, this.thePlayer.posY + 0.5, this.thePlayer.posZ + 0.5, xpSplit));
         }
      }

      this.removeCount = 0;
      if (stack.getItem() == Items.iron_ingot) {
         this.thePlayer.triggerAchievement(AchievementList.acquireIron);
      }

      if (stack.getItem() == Items.cooked_fish) {
         this.thePlayer.triggerAchievement(AchievementList.cookFish);
      }
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return false;
   }

   @Override
   public ItemStack decrStackSize(int n) {
      if (this.getHasStack()) {
         this.removeCount = this.removeCount + Math.min(n, this.getStack().stackSize);
      }

      return super.decrStackSize(n);
   }

   @Override
   public void onCrafting(ItemStack stack, int n) {
      this.removeCount += n;
      this.onCrafting(stack);
   }

   public SlotFurnaceOutput(EntityPlayer player, IInventory inventory2, int n, int n2, int n3) {
      super(inventory2, n, n2, n3);
      this.thePlayer = player;
   }

   @Override
   public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
      this.onCrafting(stack);
      super.onPickupFromSlot(player, stack);
   }
}
