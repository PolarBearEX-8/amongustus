package net.minecraft.item;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRecord extends Item {
   private static final Map RECORDS = Maps.newHashMap();
   public final String recordName;

   public static ItemRecord getRecord(String str) {
      return (ItemRecord)RECORDS.get(str);
   }

   public String getRecordNameLocal() {
      return StatCollector.translateToLocal("item.record." + this.recordName + ".desc");
   }

   @Override
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      list.add(this.getRecordNameLocal());
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      IBlockState blockState = world.getBlockState(pos);
      if (blockState.getBlock() != Blocks.jukebox || (Boolean)blockState.getValue(BlockJukebox.HAS_RECORD)) {
         return false;
      } else if (world.isRemote) {
         return true;
      } else {
         ((BlockJukebox)Blocks.jukebox).insertRecord(world, pos, blockState, stack);
         world.playAuxSFXAtEntity((EntityPlayer)null, 1005, pos, Item.getIdFromItem(this));
         stack.stackSize--;
         player.triggerAchievement(StatList.recordPlayed);
         return true;
      }
   }

   public ItemRecord(String str) {
      this.recordName = str;
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabMisc);
      RECORDS.put("records." + str, this);
   }

   @Override
   public EnumRarity getRarity(ItemStack stack) {
      return EnumRarity.RARE;
   }
}
