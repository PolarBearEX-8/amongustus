package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockEnchantmentTable extends BlockContainer {
   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityEnchantmentTable();
   }

   public BlockEnchantmentTable() {
      super(Material.rock, MapColor.redColor);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
      this.setLightOpacity(0);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      super.randomDisplayTick(world, pos, state, rand);

      for (int i = -2; i <= 2; i++) {
         for (int j = -2; j <= 2; j++) {
            if (i > -2 && i < 2 && j == -1) {
               j = 2;
            }

            if (rand.nextInt(16) == 0) {
               for (int k = 0; k <= 1; k++) {
                  if (world.getBlockState(pos.add(i, k, j)).getBlock() == Blocks.bookshelf) {
                     if (!world.isAirBlock(pos.add(i / 2, 0, j / 2))) {
                        break;
                     }

                     world.spawnParticle(
                        EnumParticleTypes.ENCHANTMENT_TABLE,
                        (double)pos.getX() + 0.5,
                        (double)pos.getY() + 2.0,
                        (double)pos.getZ() + 0.5,
                        (double)((float)i + rand.nextFloat()) - 0.5,
                        (double)((float)k - rand.nextFloat() - 1.0F),
                        (double)((float)j + rand.nextFloat()) - 0.5,
                        new int[0]
                     );
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityEnchantmentTable) {
            player.displayGui((TileEntityEnchantmentTable)tileEntity);
         }
      }

      return true;
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      super.onBlockPlacedBy(world, pos, state, entity, stack);
      if (stack.hasDisplayName()) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityEnchantmentTable) {
            ((TileEntityEnchantmentTable)tileEntity).setCustomName(stack.getDisplayName());
         }
      }
   }
}
