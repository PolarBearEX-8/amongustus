package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockIce extends BlockBreakable {
   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (world.getLightFor(EnumSkyBlock.BLOCK, pos.getX(), pos.getY(), pos.getZ()) > 11 - this.getLightOpacity()) {
         if (world.provider.doesWaterVaporize()) {
            world.setBlockToAir(pos);
         } else {
            this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
            world.setBlockState(pos, Blocks.water.getDefaultState());
         }
      }
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
      player.addExhaustion(0.025F);
      if (this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(player)) {
         ItemStack stack = this.createStackedBlock(state);
         if (stack != null) {
            spawnAsEntity(world, pos, stack);
         }
      } else {
         if (world.provider.doesWaterVaporize()) {
            world.setBlockToAir(pos);
            return;
         }

         this.dropBlockAsItem(world, pos, state, EnchantmentHelper.getFortuneModifier(player));
         Material material = world.getBlockState(pos.down()).getBlock().getMaterial();
         if (material.blocksMovement() || material.isLiquid()) {
            world.setBlockState(pos, Blocks.flowing_water.getDefaultState());
         }
      }
   }

   @Override
   public int getMobilityFlag() {
      return 0;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   public BlockIce() {
      super(Material.ice, false);
      this.slipperiness = 0.98F;
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }
}
