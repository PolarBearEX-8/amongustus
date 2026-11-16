package net.minecraft.block;

import client.util.IBlockState;
import client.util.StatCraftItemManager;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockDeadbush extends SecondaryBlockAdapter {
   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor18;
   }

   @Override
   public boolean matchesBlockByBlock(Block block) {
      return block == Blocks.blockSand || block == Blocks.block98 || block == Blocks.block107 || block == Blocks.block115;
   }

   @Override
   public boolean processAction6(World world, BlockPos blockPos) {
      return true;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      if (!world.state && player.getHeldItem() != null && player.getHeldItem().getItem() == Items.shears) {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray4[Block.getIdFromBlock(this)]);
         applyWorld(world, blockPos, new ItemStack(Blocks.blockDeadbush, 1, 0));
      } else {
         super.applyWorld(world, player, blockPos, blockState, tileEntity);
      }
   }

   public BlockDeadbush() {
      super(Material.material7);
      float factor = 0.4F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, 0.8F, 0.5F + factor);
   }
}
