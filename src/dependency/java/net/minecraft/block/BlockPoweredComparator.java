package net.minecraft.block;

import client.event.ServiceListener;
import client.setting.RenderColorProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import client.util.RayTraceReader;
import client.util.VectorRanker;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityComparator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPoweredComparator extends VectorRanker implements ServiceListener {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("powered");
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("mode", RenderColorProperty.class);

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (this.enabled) {
         world.matchesBlockPos(blockPos, this.resolveIBlockState(blockState).resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 4);
      }

      this.applyWorld14(world, blockPos, blockState);
   }

   @Override
   public int computeCount(World world, BlockPos blockPos, IBlockState blockState) {
      int count = super.computeCount(world, blockPos, blockState);
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      BlockPos blockPos2 = blockPos.offset(comparable);
      Block block = world.getBlockState(blockPos2).getBlock();
      if (block.processAction16()) {
         count = block.processAction5(world, blockPos2);
      } else if (count < 15 && block.isActive2()) {
         BlockPos blockPos3 = blockPos2.offset(comparable);
         Block block2 = world.getBlockState(blockPos3).getBlock();
         if (block2.processAction16()) {
            count = block2.processAction5(world, blockPos3);
         } else if (block2.getMaterial() == Material.coreItemEntry) {
            EntityItemframe entityItemframe = this.resolveEntityItemframe(world, comparable, blockPos3);
            if (entityItemframe != null) {
               count = entityItemframe.getCount7();
            }
         }
      }

      return count;
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      int count = this.computeCount(world, blockPos, blockState);
      if (count >= 15) {
         return true;
      } else if (count == 0) {
         return false;
      } else {
         int count2 = this.computeCount(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState);
         return count2 == 0 ? true : count >= count2;
      }
   }

   private EntityItemframe resolveEntityItemframe(World world, EnumFacing enumFacing, BlockPos blockPos) {
      List list = world.resolveList(
         EntityItemframe.class,
         new AxisAlignedBB(
            (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)
         ),
         new RayTraceReader(this, enumFacing)
      );
      return list.size() == 1 ? (EntityItemframe)list.get(0) : null;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return player.invulnerableManager.state1;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count))
         .resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0)
         .resolveIBlockState(immutableSetEntry, (count & 4) > 0 ? RenderColorProperty.SUBTRACT : RenderColorProperty.COMPARE);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.comparator;
   }

   @Override
   public IBlockState resolveIBlockState(IBlockState blockState) {
      Boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      RenderColorProperty comparable2 = (RenderColorProperty)blockState.getComparable(immutableSetEntry);
      EnumFacing comparable3 = (EnumFacing)blockState.getComparable(autoWalkPath);
      return Blocks.blockPoweredComparator2
         .getDefaultState()
         .resolveIBlockState(autoWalkPath, comparable3)
         .resolveIBlockState(internalImmutableSetEntry, comparable)
         .resolveIBlockState(immutableSetEntry, comparable2);
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      super.onBlockAdded(world, blockPos, blockState);
      world.applyBlockPosByBlockPos(blockPos);
      this.applyWorld7(world, blockPos, blockState);
   }

   @Override
   public int computeCount(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState) {
      TileEntity tileEntity = iBlockAccess.resolveTileEntity(blockPos);
      return tileEntity instanceof TileEntityComparator ? ((TileEntityComparator)tileEntity).getCount2() : 0;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.comparator;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!player.invulnerableManager.state1) {
         return false;
      } else {
         IBlockState blockState2 = blockState.resolveIBlockState(immutableSetEntry);
         world.applyText(
            (double)blockPos.getX() + 0.5,
            (double)blockPos.getY() + 0.5,
            (double)blockPos.getZ() + 0.5,
            "random.click",
            0.3F,
            blockState2.getComparable(immutableSetEntry) == RenderColorProperty.SUBTRACT ? 0.55F : 0.5F
         );
         world.matchesBlockPos(blockPos, blockState2, 2);
         this.applyWorld14(world, blockPos, blockState2);
         return true;
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityComparator();
   }

   @Override
   public int computeCount(IBlockState blockState) {
      return 2;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2().getEnumFacing5())
         .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
         .resolveIBlockState(immutableSetEntry, RenderColorProperty.COMPARE);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      super.processAction3(world, blockPos, blockState);
      world.applyBlockPos(blockPos, this.resolveTileEntityByWorld(world, 0));
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, immutableSetEntry, internalImmutableSetEntry});
   }

   private void applyWorld14(World world, BlockPos blockPos, IBlockState blockState) {
      int count = this.computeCount4(world, blockPos, blockState);
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      int count2 = 0;
      if (tileEntity instanceof TileEntityComparator) {
         TileEntityComparator tileEntityComparator = (TileEntityComparator)tileEntity;
         count2 = tileEntityComparator.getCount2();
         tileEntityComparator.setCount2(count);
      }

      if (count2 != count || blockState.getComparable(immutableSetEntry) == RenderColorProperty.COMPARE) {
         boolean flag = this.matchesWorld(world, blockPos, blockState);
         boolean flag2 = this.matchesIBlockState(blockState);
         if (flag2 && !flag) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE), 2);
         } else if (!flag2 && flag) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 2);
         }

         this.applyWorld7(world, blockPos, blockState);
      }
   }

   @Override
   public boolean matchesIBlockState(IBlockState blockState) {
      return this.enabled || (Boolean)blockState.getComparable(internalImmutableSetEntry);
   }

   @Override
   public void applyWorld5(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.contains(blockPos, this)) {
         int count = this.computeCount4(world, blockPos, blockState);
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         int count2 = tileEntity instanceof TileEntityComparator ? ((TileEntityComparator)tileEntity).getCount2() : 0;
         if (count != count2 || this.matchesIBlockState(blockState) != this.matchesWorld(world, blockPos, blockState)) {
            if (this.matchesWorld2(world, blockPos, blockState)) {
               world.applyBlockPos2(blockPos, this, 2, -1);
            } else {
               world.applyBlockPos2(blockPos, this, 2, 0);
            }
         }
      }
   }

   private int computeCount4(World world, BlockPos blockPos, IBlockState blockState) {
      return blockState.getComparable(immutableSetEntry) == RenderColorProperty.SUBTRACT
         ? Math.max(this.computeCount(world, blockPos, blockState) - this.computeCount(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState), 0)
         : this.computeCount(world, blockPos, blockState);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      if (blockState.getComparable(immutableSetEntry) == RenderColorProperty.SUBTRACT) {
         count |= 4;
      }

      return count;
   }

   @Override
   public IBlockState resolveIBlockStateByIBlockState(IBlockState blockState) {
      Boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      RenderColorProperty comparable2 = (RenderColorProperty)blockState.getComparable(immutableSetEntry);
      EnumFacing comparable3 = (EnumFacing)blockState.getComparable(autoWalkPath);
      return Blocks.blockPoweredComparator
         .getDefaultState()
         .resolveIBlockState(autoWalkPath, comparable3)
         .resolveIBlockState(internalImmutableSetEntry, comparable)
         .resolveIBlockState(immutableSetEntry, comparable2);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, int count, int count2) {
      super.processAction(world, blockPos, blockState, count, count2);
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      return tileEntity == null ? false : tileEntity.checkIndex(count, count2);
   }

   @Override
   public String getText() {
      return I18n.getString2("item.comparator.name");
   }

   public BlockPoweredComparator(boolean flag) {
      super(flag);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(autoWalkPath, EnumFacing.NORTH)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(immutableSetEntry, RenderColorProperty.COMPARE)
      );
      this.state8 = true;
   }
}
