package net.minecraft.block;

import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.DampenerReader;
import client.util.IBlockState;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAnvil extends CustomBlockAdapter {
   public static final AutoWalkPath autoWalkPath;
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry;

   @Override
   public IBlockState processAction(IBlockState blockState) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, EnumFacing.SOUTH);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      if (((EnumFacing)iBlockAccess.getIBlockState(count, count2, count3).getComparable(autoWalkPath)).getEnumFacingXType() == EnumFacingXType.X) {
         this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
      } else {
         this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count & 3))
         .resolveIBlockState(auxiliaryImmutableSetEntry, (count & 15) >> 2);
   }

   @Override
   public void applyEntityFallingsand(EntityFallingsand entityFallingsand) {
      entityFallingsand.setEnabled14(true);
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
      list.add(new ItemStack(item, 1, 2));
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         player.applyListener63Extension(new DampenerReader(world, blockPos));
      }

      return true;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      EnumFacing enumFacingAsEnumFacing = livingEntity.getEnumFacing2().getEnumFacingAsEnumFacing();
      return super.onBlockPlaced(world, blockPos, enumFacing, posX, posY, posZ, count, livingEntity)
         .resolveIBlockState(autoWalkPath, enumFacingAsEnumFacing)
         .resolveIBlockState(auxiliaryImmutableSetEntry, count >> 2);
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return true;
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, auxiliaryImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset() | (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) << 2;
   }

   @Override
   public void applyWorld4(World world, BlockPos blockPos) {
      world.applyBlockPosByBlockPos(1022, blockPos, 0);
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockAnvil() {
      super(Material.material31);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH).resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.getBlockById(0);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }

   static {
      String[] textArray = new String[]{"damage", "facing"};
      autoWalkPath = AutoWalkPath.resolveAutoWalkPath(textArray[1], MovementFactorProperty.HORIZONTAL);
      auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry(textArray[0], 0, 2);
   }
}
