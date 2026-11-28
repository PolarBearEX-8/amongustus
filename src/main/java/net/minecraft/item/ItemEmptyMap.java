package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class ItemEmptyMap extends ItemMapBase {

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      ItemStack stack2 = new ItemStack(Items.filled_map, 1, world.getUniqueDataId("map"));
      String str = "map_" + stack2.getMetadata();
      MapData mapData = new MapData(str);
      world.setItemData(str, mapData);
      mapData.scale = 0;
      mapData.calculateMapCenter(player.posX, player.posZ, mapData.scale);
      mapData.dimension = (byte)world.provider.getDimensionId();
      mapData.markDirty();
      stack.stackSize--;
      if (stack.stackSize <= 0) {
         return stack2;
      } else {
         if (!player.inventory.addItemStackToInventory(stack2.copy())) {
            player.dropPlayerItemWithRandomChoice(stack2, false);
         }

         player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
         return stack;
      }
   }

   public ItemEmptyMap() {
      this.setCreativeTab(CreativeTabs.tabMisc);
   }
}
