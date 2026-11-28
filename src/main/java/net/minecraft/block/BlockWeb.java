package net.minecraft.block;

import java.util.Random;
import net.augustus.Augustus;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockWeb extends Block {

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public BlockWeb() {
      super(Material.web);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!Augustus.getInstance().getModuleManager().noWeb.isToggled() || !Augustus.getInstance().getModuleManager().noWeb.modes.getSelectedValue().equals("Ignore")) {
         entity.setInWeb();
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.string;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean canSilkHarvest() {
      return true;
   }

}
