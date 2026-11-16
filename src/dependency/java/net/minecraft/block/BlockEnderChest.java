package net.minecraft.block;

import augustus.modes.movement.MovementContainerEnderchestMode;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderchest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEnderChest extends AdvancedBlockAdapter {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2().getEnumFacing5()), 2);
   }

   @Override
   public IBlockState processAction(int count) {
      EnumFacing enumFacing = EnumFacing.getEnumFacing6(count);
      if (enumFacing.getEnumFacingXType() == EnumFacingXType.Y) {
         enumFacing = EnumFacing.NORTH;
      }

      return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing);
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(Blocks.block113);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      MovementContainerEnderchestMode movementContainerEnderchestMode = player.getMovementContainerEnderchestMode();
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (movementContainerEnderchestMode == null || !(tileEntity instanceof TileEntityEnderchest)) {
         return true;
      } else if (world.getBlockState(blockPos.up()).getBlock().isActive2()) {
         return true;
      } else if (world.state) {
         return true;
      } else {
         movementContainerEnderchestMode.setTileEntityEnderchest((TileEntityEnderchest)tileEntity);
         player.applyIInventory(movementContainerEnderchestMode);
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager32);
         return true;
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2().getEnumFacing5());
   }

   @Override
   public int processAction(Random random) {
      return 8;
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityEnderchest();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath});
   }

   @Override
   public int processAction13() {
      return 2;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      for (int index = 0; index < 3; index++) {
         int count = random.nextInt(2) * 2 - 1;
         int count2 = random.nextInt(2) * 2 - 1;
         double offset = (double)blockPos.getX() + 0.5 + 0.25 * (double)count;
         double offset2 = (double)((float)blockPos.getY() + random.nextFloat());
         double offset3 = (double)blockPos.getZ() + 0.5 + 0.25 * (double)count2;
         double offset4 = (double)(random.nextFloat() * (float)count);
         double offset5 = ((double)random.nextFloat() - 0.5) * 0.125;
         double offset6 = (double)(random.nextFloat() * (float)count2);
         world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.PORTAL, offset, offset2, offset3, offset4, offset5, offset6, new int[0]);
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

   public BlockEnderChest() {
      super(Material.material4);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH));
      this.processAction(ItemGroupManager.itemGroupManager8);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }
}
