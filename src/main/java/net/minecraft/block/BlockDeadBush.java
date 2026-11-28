package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockDeadBush extends BlockBush {
   @Override
   public boolean canPlaceBlockOn(Block block) {
      return block == Blocks.sand || block == Blocks.hardened_clay || block == Blocks.stained_hardened_clay || block == Blocks.dirt;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return null;
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return MapColor.woodColor;
   }

   public BlockDeadBush() {
      super(Material.vine);
      float f = 0.4F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
         player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         spawnAsEntity(world, pos, new ItemStack(Blocks.deadbush, 1, 0));
      } else {
         super.harvestBlock(world, player, pos, state, tileEntity);
      }
   }

   @Override
   public boolean isReplaceable(World world, BlockPos pos) {
      return true;
   }
}
