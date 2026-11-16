package client.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class MapIsScalingListenerAdapter extends InternalListenerProvider {
   @Override
   public ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory) {
      ItemStack stack = null;

      for (int index = 0; index < containerCraftingIInventory.processAction4() && stack == null; index++) {
         ItemStack stack2 = containerCraftingIInventory.processAction3(index);
         if (stack2 != null && stack2.getItem() == Items.filledMap) {
            stack = stack2;
         }
      }

      ItemStack stack3 = stack.getItemStack();
      stack3.animationsToGo = 1;
      if (stack3.getStackTagCompound() == null) {
         stack3.processAction(new NBTTagCompound());
      }

      stack3.getStackTagCompound().applyText("map_is_scaling", true);
      return stack3;
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      if (!super.matchesContainerCraftingIInventor(containerCraftingIInventory, world)) {
         return false;
      } else {
         ItemStack stack = null;

         for (int index = 0; index < containerCraftingIInventory.processAction4() && stack == null; index++) {
            ItemStack stack2 = containerCraftingIInventory.processAction3(index);
            if (stack2 != null && stack2.getItem() == Items.filledMap) {
               stack = stack2;
            }
         }

         if (stack == null) {
            return false;
         } else {
            DimensionStringEntry dimensionStringEntry = Items.filledMap.resolveDimensionStringEntryByItemStack(stack, world);
            return dimensionStringEntry == null ? false : dimensionStringEntry.byteFlag2 < 4;
         }
      }
   }

   public MapIsScalingListenerAdapter() {
      super(
         3,
         3,
         new ItemStack[]{
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.filledMap, 0, 32767),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper)
         },
         new ItemStack(Items.map, 0, 0)
      );
   }
}
