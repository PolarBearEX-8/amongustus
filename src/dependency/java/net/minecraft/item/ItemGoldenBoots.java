package net.minecraft.item;

import client.setting.CrafterLeatherType;
import client.util.CurveFactory;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemGoldenBoots extends Item {
   public static final int[] countArray = new int[]{11, 16, 15, 13};
   public static final String[] stringArray = new String[]{
      "minecraft:items/empty_armor_slot_helmet",
      "minecraft:items/empty_armor_slot_chestplate",
      "minecraft:items/empty_armor_slot_leggings",
      "minecraft:items/empty_armor_slot_boots"
   };
   public static final CustomItemCallback customItemCallback = new CurveFactory();
   public final int indexOffset3;
   public final int indexOffset4;
   public final int count2;
   public final CrafterLeatherType crafterLeatherType;

   public void applyItemStack(ItemStack stack, int count) {
      if (this.crafterLeatherType != CrafterLeatherType.LEATHER) {
         throw new UnsupportedOperationException("Can't dye non-leather!");
      } else {
         NBTTagCompound stackTagCompound = stack.getStackTagCompound();
         if (stackTagCompound == null) {
            stackTagCompound = new NBTTagCompound();
            stack.processAction(stackTagCompound);
         }

         NBTTagCompound nBTTagCompound = stackTagCompound.getNBTTagCompound("display");
         if (!stackTagCompound.matchesText("display", 10)) {
            stackTagCompound.applyText("display", nBTTagCompound);
         }

         nBTTagCompound.put("color", count);
      }
   }

   public boolean matchesItemStack2(ItemStack stack) {
      return this.crafterLeatherType != CrafterLeatherType.LEATHER
         ? false
         : (
            !stack.canExecute()
               ? false
               : (
                  !stack.getStackTagCompound().matchesText("display", 10)
                     ? false
                     : stack.getStackTagCompound().getNBTTagCompound("display").matchesText("color", 3)
               )
         );
   }

   public CrafterLeatherType getCrafterLeatherType() {
      return this.crafterLeatherType;
   }

   @Override
   public int processAction2() {
      return this.crafterLeatherType.getCount();
   }

   @Override
   public boolean processAction(ItemStack stack, ItemStack stack2) {
      return this.crafterLeatherType.getItem() == stack2.getItem() ? true : super.processAction(stack, stack2);
   }

   public int computeCount(ItemStack stack) {
      if (this.crafterLeatherType != CrafterLeatherType.LEATHER) {
         return -1;
      } else {
         NBTTagCompound stackTagCompound = stack.getStackTagCompound();
         if (stackTagCompound != null) {
            NBTTagCompound nBTTagCompound = stackTagCompound.getNBTTagCompound("display");
            if (nBTTagCompound != null && nBTTagCompound.matchesText("color", 3)) {
               return nBTTagCompound.computeCount("color");
            }
         }

         return 10511680;
      }
   }

   public void applyItemStack(ItemStack stack) {
      if (this.crafterLeatherType == CrafterLeatherType.LEATHER) {
         NBTTagCompound stackTagCompound = stack.getStackTagCompound();
         if (stackTagCompound != null) {
            NBTTagCompound nBTTagCompound = stackTagCompound.getNBTTagCompound("display");
            if (nBTTagCompound.containsKey("color")) {
               nBTTagCompound.remove("color");
            }
         }
      }
   }

   @Override
   public int processAction(ItemStack stack, int count) {
      if (count > 0) {
         return 16777215;
      } else {
         int count3 = this.computeCount(stack);
         if (count3 < 0) {
            count3 = 16777215;
         }

         return count3;
      }
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      int count = EntityMob.computeCount(stack) - 1;
      if (player.getItemStack3(count) == null) {
         player.applyItemStack(count, stack.getItemStack());
         stack.animationsToGo = 0;
      }

      return stack;
   }

   public static int[] getCountArray() {
      return countArray;
   }

   public ItemGoldenBoots(CrafterLeatherType crafterLeatherType2, int count, int count3) {
      this.crafterLeatherType = crafterLeatherType2;
      this.indexOffset3 = count3;
      this.count2 = count;
      this.indexOffset4 = crafterLeatherType2.computeCount(count3);
      this.processAction3(crafterLeatherType2.computeCountByIndex(count3));
      this.indexOffset1 = 1;
      this.processAction(ItemGroupManager.itemGroupManager4);
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(this, customItemCallback);
   }
}
