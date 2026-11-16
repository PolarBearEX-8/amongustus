package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchanttable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEnchantingTable extends AdvancedBlockAdapter {
   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      super.processAction(world, blockPos, blockState, livingEntity, stack);
      if (stack.isActive()) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityEnchanttable) {
            ((TileEntityEnchanttable)tileEntity).setLabelText(stack.getString());
         }
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityEnchanttable();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityEnchanttable) {
            player.applyListener63Extension((TileEntityEnchanttable)tileEntity);
         }
      }

      return true;
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      super.processAction3(world, blockPos, blockState, random);

      for (int index = -2; index <= 2; index++) {
         for (int index2 = -2; index2 <= 2; index2++) {
            if (index > -2 && index < 2 && index2 == -1) {
               index2 = 2;
            }

            if (random.nextInt(16) == 0) {
               for (int index3 = 0; index3 <= 1; index3++) {
                  BlockPos blockPos2 = blockPos.add(index, index3, index2);
                  if (world.getBlockState(blockPos2).getBlock() == Blocks.block8) {
                     if (!world.isAirBlock(blockPos.add(index / 2, 0, index2 / 2))) {
                        break;
                     }

                     world.applyAttackEffectsExplosionNorm(
                        AttackEffectsExplosionNormalType.ENCHANTMENT_TABLE,
                        (double)blockPos.getX() + 0.5,
                        (double)blockPos.getY() + 2.0,
                        (double)blockPos.getZ() + 0.5,
                        (double)((float)index + random.nextFloat()) - 0.5,
                        (double)((float)index3 - random.nextFloat() - 1.0F),
                        (double)((float)index2 + random.nextFloat()) - 0.5,
                        new int[0]
                     );
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockEnchantingTable() {
      super(Material.material4, MapColor.mapColor5);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
      this.getBlockById(0);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
