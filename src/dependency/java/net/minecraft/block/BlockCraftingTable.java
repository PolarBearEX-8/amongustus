package net.minecraft.block;

import client.util.GeometryTracker;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCraftingTable extends Block {
   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         player.applyListener63Extension(new GeometryTracker(world, blockPos));
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager39);
      }

      return true;
   }

   public BlockCraftingTable() {
      super(Material.material);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
