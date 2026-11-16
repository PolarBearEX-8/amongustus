package net.minecraft.block;

import client.setting.AdapterOptionType;
import client.setting.MovementFactorProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import client.util.SmoothConverter;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLever extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("facing", AdapterOptionType.class);
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("powered");

   public static boolean matchesWorld2(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return InternalBlockAdapter.matchesWorld2(world, blockPos, enumFacing);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      float factor = 0.1875F;
      switch (SmoothConverter.countArray[((AdapterOptionType)iBlockAccess.getIBlockState(count, count2, count3).getComparable(immutableSetEntry)).ordinal()]) {
         case 1:
            this.setBlockBounds(0.0F, 0.2F, 0.5F - factor, factor * 2.0F, 0.8F, 0.5F + factor);
            break;
         case 2:
            this.setBlockBounds(1.0F - factor * 2.0F, 0.2F, 0.5F - factor, 1.0F, 0.8F, 0.5F + factor);
            break;
         case 3:
            this.setBlockBounds(0.5F - factor, 0.2F, 0.0F, 0.5F + factor, 0.8F, factor * 2.0F);
            break;
         case 4:
            this.setBlockBounds(0.5F - factor, 0.2F, 1.0F - factor * 2.0F, 0.5F + factor, 0.8F, 1.0F);
            break;
         case 5:
         case 6:
            float factor2 = 0.25F;
            this.setBlockBounds(0.5F - factor2, 0.0F, 0.5F - factor2, 0.5F + factor2, 0.6F, 0.5F + factor2);
            break;
         case 7:
         case 8:
            float factor3 = 0.25F;
            this.setBlockBounds(0.5F - factor3, 0.4F, 0.5F - factor3, 0.5F + factor3, 1.0F, 0.5F + factor3);
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public boolean processAction7() {
      return true;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(immutableSetEntry, AdapterOptionType.resolveAdapterOptionType(count & 7))
         .resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0);
   }

   private boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.processAction7(world, blockPos)) {
         return true;
      } else {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
         return false;
      }
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         world.applyBlockPosByBlockPos(blockPos, this);
         EnumFacing enumFacing = ((AdapterOptionType)blockState.getComparable(immutableSetEntry)).getEnumFacing();
         world.applyBlockPosByBlockPos(blockPos.offset(enumFacing.getEnumFacing5()), this);
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return ((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 15 : 0;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (this.matchesWorld(world, blockPos, blockState)
         && !matchesWorld2(world, blockPos, ((AdapterOptionType)blockState.getComparable(immutableSetEntry)).getEnumFacing().getEnumFacing5())) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         if (matchesWorld2(world, blockPos, enumFacing)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return matchesWorld2(world, blockPos, enumFacing.getEnumFacing5());
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      IBlockState blockState = this.getDefaultState().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
      if (matchesWorld2(world, blockPos, enumFacing.getEnumFacing5())) {
         return blockState.resolveIBlockState(immutableSetEntry, AdapterOptionType.resolveAdapterOptionType(enumFacing, livingEntity.getEnumFacing2()));
      } else {
         for (Object enumFacing2Obj : MovementFactorProperty.HORIZONTAL) {
            EnumFacing enumFacing2 = (EnumFacing)enumFacing2Obj;
            if (enumFacing2 != enumFacing && matchesWorld2(world, blockPos, enumFacing2.getEnumFacing5())) {
               return blockState.resolveIBlockState(immutableSetEntry, AdapterOptionType.resolveAdapterOptionType(enumFacing2, livingEntity.getEnumFacing2()));
            }
         }

         return World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())
            ? blockState.resolveIBlockState(immutableSetEntry, AdapterOptionType.resolveAdapterOptionType(EnumFacing.UP, livingEntity.getEnumFacing2()))
            : blockState;
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         IBlockState blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry);
         world.matchesBlockPos(blockPos, blockState2, 3);
         world.applyText(
            (double)blockPos.getX() + 0.5,
            (double)blockPos.getY() + 0.5,
            (double)blockPos.getZ() + 0.5,
            "random.click",
            0.3F,
            (Boolean)blockState2.getComparable(internalImmutableSetEntry) ? 0.6F : 0.5F
         );
         world.applyBlockPosByBlockPos(blockPos, this);
         EnumFacing enumFacing2 = ((AdapterOptionType)blockState2.getComparable(immutableSetEntry)).getEnumFacing();
         world.applyBlockPosByBlockPos(blockPos.offset(enumFacing2.getEnumFacing5()), this);
      }

      return true;
   }

   public static int computeCount(EnumFacing enumFacing) {
      switch (SmoothConverter.countArray2[enumFacing.ordinal()]) {
         case 1:
            return 0;
         case 2:
            return 5;
         case 3:
            return 4;
         case 4:
            return 3;
         case 5:
            return 2;
         case 6:
            return 1;
         default:
            return -1;
      }
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return !(Boolean)blockState.getComparable(internalImmutableSetEntry) ? 0 : (((AdapterOptionType)blockState.getComparable(immutableSetEntry)).getEnumFacing() == enumFacing ? 15 : 0);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry, internalImmutableSetEntry});
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((AdapterOptionType)blockState.getComparable(immutableSetEntry)).getCount();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      return count;
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
   public boolean processAction17() {
      return false;
   }

   public BlockLever() {
      super(Material.material22);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(immutableSetEntry, AdapterOptionType.NORTH).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.processAction(ItemGroupManager.itemGroupManager9);
   }
}
