package net.minecraft.item;

import client.setting.ItemStackCommonType;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemRecordWait extends Item {
   public static final Map map = Maps.newHashMap();
   public final String labelText;

   @Override
   public ItemStackCommonType getItemStackCommonType(ItemStack stack) {
      return ItemStackCommonType.RARE;
   }

   public String getText() {
      return I18n.getString2("item.record." + this.labelText + ".desc");
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (blockState.getBlock() != Blocks.block114 || (Boolean)blockState.getComparable(BlockJukebox.internalImmutableSetEntry)) {
         return false;
      } else if (world.state) {
         return true;
      } else {
         ((BlockJukebox)Blocks.block114).applyWorld(world, blockPos, blockState, stack);
         world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1005, blockPos, Item.getIndex(this));
         stack.animationsToGo--;
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager31);
         return true;
      }
   }

   public static ItemRecordWait get(String text) {
      return (ItemRecordWait)map.get(text);
   }

   @Override
   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      list.add(this.getText());
   }

   public ItemRecordWait(String text) {
      this.labelText = text;
      this.indexOffset1 = 1;
      this.processAction(ItemGroupManager.itemGroupManager5);
      map.put("records." + text, this);
   }
}
