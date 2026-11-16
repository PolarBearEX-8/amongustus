package net.minecraft.block;

import client.setting.MovementToggleProperty;
import client.util.AngleSynchronizer;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonHead extends Block {
   public static final AutoWalkPath autoWalkPath;
   public static final ImmutableSetEntry immutableSetEntry;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      if (player.invulnerableManager.enabled2) {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         if (comparable != null) {
            BlockPos blockPos2 = blockPos.offset(comparable.getEnumFacing5());
            Block block = world.getBlockState(blockPos2).getBlock();
            if (block == Blocks.blockPiston2 || block == Blocks.blockPiston) {
               world.matchesBlockPos8(blockPos2);
            }
         }
      }

      super.processAction(world, blockPos, blockState, player);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.applyIBlockState(iBlockAccess.getIBlockState(count, count2, count3));
   }

   public void applyIBlockState(IBlockState blockState) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      if (comparable != null) {
         if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_13)) {
            switch (AngleSynchronizer.countArray[comparable.ordinal()]) {
               case 1:
                  this.processAction(0.375, 0.25, 0.375, 0.625, 1.25, 0.625);
                  break;
               case 2:
                  this.processAction(0.375, -0.25, 0.375, 0.625, 0.75, 0.625);
                  break;
               case 3:
                  this.processAction(0.375, 0.375, 0.25, 0.625, 0.625, 1.25);
                  break;
               case 4:
                  this.processAction(0.375, 0.375, -0.25, 0.625, 0.625, 0.75);
                  break;
               case 5:
                  this.processAction(0.25, 0.375, 0.375, 1.25, 0.625, 0.625);
                  break;
               case 6:
                  this.processAction(-0.25, 0.375, 0.375, 0.75, 0.625, 0.625);
            }
         } else {
            switch (AngleSynchronizer.countArray[comparable.ordinal()]) {
               case 1:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
                  break;
               case 2:
                  this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case 3:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
                  break;
               case 4:
                  this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
                  break;
               case 5:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
                  break;
               case 6:
                  this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, resolveEnumFacing(count))
         .resolveIBlockState(immutableSetEntry, (count & 8) > 0 ? MovementToggleProperty.STICKY : MovementToggleProperty.DEFAULT);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos).getComparable(immutableSetEntry) == MovementToggleProperty.STICKY
         ? Item.processAction2(Blocks.blockPiston)
         : Item.processAction2(Blocks.blockPiston2);
   }

   public static EnumFacing resolveEnumFacing(int count) {
      int count2 = count & 7;
      return count2 > 5 ? null : EnumFacing.getEnumFacing6(count2);
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      super.onBlockAdded(world, blockPos, blockState);
      EnumFacing enumFacing = ((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacing5();
      BlockPos blockPos2 = blockPos.offset(enumFacing);
      IBlockState blockState2 = world.getBlockState(blockPos2);
      if ((blockState2.getBlock() == Blocks.blockPiston2 || blockState2.getBlock() == Blocks.blockPiston)
         && (Boolean)blockState2.getComparable(BlockPiston.internalImmutableSetEntry)) {
         blockState2.getBlock().processAction(world, blockPos2, blockState2, 0);
         world.matchesBlockPos8(blockPos2);
      }
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      this.applyIBlockState(blockState);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.applyIBlockStateByIBlockState(blockState);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      BlockPos blockPos2 = blockPos.offset(comparable.getEnumFacing5());
      IBlockState blockState2 = world.getBlockState(blockPos2);
      if (blockState2.getBlock() != Blocks.blockPiston2 && blockState2.getBlock() != Blocks.blockPiston) {
         world.matchesBlockPos8(blockPos);
      } else {
         blockState2.getBlock().processAction(world, blockPos2, blockState2, block);
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return false;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return false;
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return true;
   }

   private void applyIBlockStateByIBlockState(IBlockState blockState) {
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         switch (AngleSynchronizer.countArray[((EnumFacing)blockState.getComparable(autoWalkPath)).ordinal()]) {
            case 1:
               this.processAction(0.0, 0.0, 0.0, 1.0, 0.25, 1.0);
               break;
            case 2:
               this.processAction(0.0, 0.75, 0.0, 1.0, 1.0, 1.0);
               break;
            case 3:
               this.processAction(0.0, 0.0, 0.0, 1.0, 1.0, 0.25);
               break;
            case 4:
               this.processAction(0.0, 0.0, 0.75, 1.0, 1.0, 1.0);
               break;
            case 5:
               this.processAction(0.0, 0.0, 0.0, 0.25, 1.0, 1.0);
               break;
            case 6:
               this.processAction(0.75, 0.0, 0.0, 1.0, 1.0, 1.0);
         }
      } else {
         switch (AngleSynchronizer.countArray[((EnumFacing)blockState.getComparable(autoWalkPath)).ordinal()]) {
            case 1:
               this.setBlockBounds(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
               break;
            case 2:
               this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
               break;
            case 3:
               this.setBlockBounds(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
               break;
            case 4:
               this.setBlockBounds(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
               break;
            case 5:
               this.setBlockBounds(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
               break;
            case 6:
               this.setBlockBounds(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
         }
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, immutableSetEntry, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
      if (blockState.getComparable(immutableSetEntry) == MovementToggleProperty.STICKY) {
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

   public BlockPistonHead() {
      super(Material.material33);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(autoWalkPath, EnumFacing.NORTH)
            .resolveIBlockState(immutableSetEntry, MovementToggleProperty.DEFAULT)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.processAction(stepManager10);
      this.getBlock5(0.5F);
   }

   static {
      String[] textArray = new String[]{"short", "type", "facing"};
      autoWalkPath = AutoWalkPath.resolveAutoWalkPath(textArray[2]);
      immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(textArray[1], MovementToggleProperty.class);
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[0]);
   }
}
