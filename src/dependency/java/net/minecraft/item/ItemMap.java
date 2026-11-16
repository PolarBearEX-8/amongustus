package net.minecraft.item;

import client.util.DimensionStringEntry;
import client.util.StatCraftItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemMap extends ItemMapBase {
   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      ItemStack stack2 = new ItemStack(Items.filledMap, 1, world.computeCount("map"));
      String text = "map_" + stack2.getIndexOffset();
      DimensionStringEntry dimensionStringEntry = new DimensionStringEntry(text);
      world.applyText(text, dimensionStringEntry);
      dimensionStringEntry.byteFlag2 = 0;
      dimensionStringEntry.applyDouble(player.posX, player.posZ, dimensionStringEntry.byteFlag2);
      dimensionStringEntry.byteFlag = (byte)world.secondaryFloatArrayEntry.getCountAsIndex();
      dimensionStringEntry.updateState();
      stack.animationsToGo--;
      if (stack.animationsToGo <= 0) {
         return stack2;
      } else {
         if (!player.inventoryPlayer.checkItemStack3(stack2.getItemStack())) {
            player.resolveEntityItem(stack2, false);
         }

         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
         return stack;
      }
   }

   public ItemMap() {
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}
