package net.minecraft.block;

import client.event.HandlerListener;
import client.event.PreRenderListener;
import client.util.AddingDuplicateKeyManagerAdapter;
import client.util.AngleSelector;
import client.util.AutoWalkPath;
import client.util.DampenerFilter;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.RotationAggregator;
import client.util.StatCraftItemManager;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.CustomItemCallback;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityTrap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDispenser extends AdvancedBlockAdapter {
   public static final AutoWalkPath autoWalkPath;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;
   public static final AddingDuplicateKeyManagerAdapter addingDuplicateKeyManagerAdapter = new AddingDuplicateKeyManagerAdapter(new ItemCallbackAdapter());
   public Random random = new Random();

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random2) {
      if (!world.state) {
         this.applyWorld2(world, blockPos);
      }
   }

   @Override
   public IBlockState processAction(IBlockState blockState) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, EnumFacing.SOUTH);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(autoWalkPath, BlockPiston.resolveEnumFacingByWorld(world, blockPos, livingEntity)), 2);
      if (stack.isActive()) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityTrap) {
            ((TileEntityTrap)tileEntity).setLabelText(stack.getString());
         }
      }
   }

   public CustomItemCallback resolveCustomItemCallback(ItemStack stack) {
      return (CustomItemCallback)addingDuplicateKeyManagerAdapter.getObject(stack == null ? null : stack.getItem());
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, resolveEnumFacing(count)).resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0);
   }

   @Override
   public int processAction(World world) {
      return 4;
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      return Container.computeCount(world.resolveTileEntity(blockPos));
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityTrap) {
         DampenerFilter.applyWorldByWorld(world, blockPos, (TileEntityTrap)tileEntity);
         world.applyBlockPos(blockPos, this);
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   private void applyWorld6(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         boolean state = world.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock().isState18();
         boolean state2 = world.getBlockState(blockPos.getBlockPos2()).getBlock().isState18();
         if (comparable == EnumFacing.NORTH && state && !state2) {
            comparable = EnumFacing.SOUTH;
         } else if (comparable == EnumFacing.SOUTH && state2 && !state) {
            comparable = EnumFacing.NORTH;
         } else {
            boolean state3 = world.getBlockState(blockPos.getBlockPos()).getBlock().isState18();
            boolean state4 = world.getBlockState(blockPos.getBlockPos3()).getBlock().isState18();
            if (comparable == EnumFacing.WEST && state3 && !state4) {
               comparable = EnumFacing.EAST;
            } else if (comparable == EnumFacing.EAST && state4 && !state3) {
               comparable = EnumFacing.WEST;
            }
         }

         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(autoWalkPath, comparable).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE), 2);
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      boolean flag = world.matchesBlockPos7(blockPos) || world.matchesBlockPos7(blockPos.up());
      boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      if (flag && !comparable) {
         world.applyBlockPos(blockPos, this, this.processAction(world));
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 4);
      } else if (!flag && comparable) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE), 4);
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityTrap();
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, BlockPiston.resolveEnumFacingByWorld(world, blockPos, livingEntity))
         .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityTrap) {
            player.applyIInventory((TileEntityTrap)tileEntity);
            if (tileEntity instanceof TileEntityDropper) {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager3);
            } else {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager33);
            }
         }
      }

      return true;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      super.processAction3(world, blockPos, blockState);
      this.applyWorld6(world, blockPos, blockState);
   }

   @Override
   public int processAction13() {
      return 3;
   }

   public static EnumFacing resolveEnumFacing(int count) {
      return EnumFacing.getEnumFacing6(count & 7);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry});
   }

   public static HandlerListener resolveHandlerListener(PreRenderListener preRenderListener) {
      EnumFacing enumFacing = resolveEnumFacing(preRenderListener.getCount());
      double offset = preRenderListener.getValue() + 0.7 * (double)enumFacing.getInt2();
      double offset2 = preRenderListener.getValue2() + 0.7 * (double)enumFacing.getInt3();
      double offset3 = preRenderListener.getValue3() + 0.7 * (double)enumFacing.getInt();
      return new AngleSelector(offset, offset2, offset3);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      return count;
   }

   public void applyWorld2(World world, BlockPos blockPos) {
      RotationAggregator rotationAggregator = new RotationAggregator(world, blockPos);
      TileEntityTrap tileEntity = (TileEntityTrap)rotationAggregator.getTileEntity();
      if (tileEntity != null) {
         int countAsIndex = tileEntity.getCountAsIndex();
         if (countAsIndex < 0) {
            world.applyBlockPosByBlockPos(1001, blockPos, 0);
         } else {
            ItemStack stack = tileEntity.processAction3(countAsIndex);
            CustomItemCallback customItemCallback = this.resolveCustomItemCallback(stack);
            if (customItemCallback != CustomItemCallback.customItemCallback) {
               ItemStack stack2 = customItemCallback.resolveItemStack(rotationAggregator, stack);
               tileEntity.processAction(countAsIndex, stack2.animationsToGo <= 0 ? null : stack2);
            }
         }
      }
   }

   @Override
   public boolean processAction16() {
      return true;
   }

   public BlockDispenser() {
      super(Material.material4);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.processAction(ItemGroupManager.itemGroupManager9);
   }

   static {
      String[] textArray = new String[]{"facing", "triggered"};
      autoWalkPath = AutoWalkPath.resolveAutoWalkPath(textArray[0]);
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
   }
}
