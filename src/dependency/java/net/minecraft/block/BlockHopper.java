package net.minecraft.block;

import client.setting.BlockSolidType;
import client.util.AutoWalkPath;
import client.util.BasePredicateAdapter;
import client.util.DampenerFilter;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.StatCraftItemManager;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHopper extends AdvancedBlockAdapter {
   public static final AutoWalkPath autoWalkPath;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT_MIPPED;
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   private void applyWorld6(World world, BlockPos blockPos, IBlockState blockState) {
      boolean flag = !world.matchesBlockPos7(blockPos);
      if (flag != (Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, flag), 4);
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      super.processAction(world, blockPos, blockState, livingEntity, stack);
      if (stack.isActive()) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityHopper) {
            ((TileEntityHopper)tileEntity).setLabelText(stack.getString());
         }
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, resolveEnumFacing(count)).resolveIBlockState(internalImmutableSetEntry, checkIndex(count));
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      return Container.computeCount(world.resolveTileEntity(blockPos));
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityHopper) {
         DampenerFilter.applyWorldByWorld(world, blockPos, (TileEntityHopper)tileEntity);
         world.applyBlockPos(blockPos, this);
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      this.applyWorld6(world, blockPos, blockState);
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      float factor = 0.125F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, factor, 1.0F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, factor);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.setBlockBounds(1.0F - factor, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 1.0F - factor, 1.0F, 1.0F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityHopper) {
            player.applyIInventory((TileEntityHopper)tileEntity);
            player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager);
         }
      }

      return true;
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityHopper();
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      EnumFacing enumFacing2 = enumFacing.getEnumFacing5();
      if (enumFacing2 == EnumFacing.UP) {
         enumFacing2 = EnumFacing.DOWN;
      }

      return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing2).resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE);
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return true;
   }

   public static EnumFacing resolveEnumFacing(int count) {
      return EnumFacing.getEnumFacing6(count & 7);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld6(world, blockPos, blockState);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry});
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
      if (!(Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      return count;
   }

   public static boolean checkIndex(int count) {
      return (count & 8) != 8;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction16() {
      return true;
   }

   public BlockHopper() {
      super(Material.material5, MapColor.mapColor10);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.DOWN).resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE)
      );
      this.processAction(ItemGroupManager.itemGroupManager9);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   static {
      String[] textArray = new String[]{"facing", "enabled"};
      autoWalkPath = AutoWalkPath.resolveAutoWalkPath(textArray[0], new BasePredicateAdapter());
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
   }
}
