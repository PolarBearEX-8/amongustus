package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.util.IStringSerializable;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityComparator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneComparator extends BlockRedstoneDiode implements ITileEntityProvider {
   public static final PropertyBool POWERED = PropertyBool.create("powered");
   public static final PropertyEnum MODE = PropertyEnum.create("mode", BlockRedstoneComparator.Mode.class);

   @Override
   public int getDelay(IBlockState state) {
      return 2;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!player.capabilities.allowEdit) {
         return false;
      } else {
         state = state.cycleProperty(MODE);
         world.playSoundEffect(
            (double)pos.getX() + 0.5,
            (double)pos.getY() + 0.5,
            (double)pos.getZ() + 0.5,
            "random.click",
            0.3F,
            state.getValue(MODE) == BlockRedstoneComparator.Mode.SUBTRACT ? 0.55F : 0.5F
         );
         world.setBlockState(pos, state, 2);
         this.onStateChange(world, pos, state);
         return true;
      }
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return player.capabilities.allowEdit;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.comparator;
   }

   private void onStateChange(World world, BlockPos pos, IBlockState state) {
      int n = this.calculateOutput(world, pos, state);
      TileEntity tileEntity = world.getTileEntity(pos);
      int n2 = 0;
      if (tileEntity instanceof TileEntityComparator) {
         TileEntityComparator tileEntityComparator = (TileEntityComparator)tileEntity;
         n2 = tileEntityComparator.getOutputSignal();
         tileEntityComparator.setOutputSignal(n);
      }

      if (n2 != n || state.getValue(MODE) == BlockRedstoneComparator.Mode.COMPARE) {
         boolean flag = this.shouldBePowered(world, pos, state);
         boolean isPowered = this.isPowered(state);
         if (isPowered && !flag) {
            world.setBlockState(pos, state.withProperty(POWERED, Boolean.FALSE), 2);
         } else if (!isPowered && flag) {
            world.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE), 2);
         }

         this.notifyNeighbors(world, pos, state);
      }
   }

   @Override
   public void updateState(World world, BlockPos pos, IBlockState state) {
      if (!world.isBlockTickPending(pos, this)) {
         int n = this.calculateOutput(world, pos, state);
         TileEntity tileEntity = world.getTileEntity(pos);
         if (n != (tileEntity instanceof TileEntityComparator ? ((TileEntityComparator)tileEntity).getOutputSignal() : 0) || this.isPowered(state) != this.shouldBePowered(world, pos, state)) {
            if (this.isFacingTowardsRepeater(world, pos, state)) {
               world.updateBlockTick(pos, this, 2, -1);
            } else {
               world.updateBlockTick(pos, this, 2, 0);
            }
         }
      }
   }

   private EntityItemFrame findItemFrame(World world, EnumFacing facing, BlockPos pos) {
      List entitiesWithinAABB = world.getEntitiesWithinAABB(
         EntityItemFrame.class,
         new AxisAlignedBB(
            (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 1), (double)(pos.getZ() + 1)
         ),
         new BlockRedstoneComparator.FilterPredicate(this, facing)
      );
      return entitiesWithinAABB.size() == 1 ? (EntityItemFrame)entitiesWithinAABB.get(0) : null;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityComparator();
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.comparator;
   }

   @Override
   public IBlockState getPoweredState(IBlockState state) {
      Boolean value = (Boolean)state.getValue(POWERED);
      BlockRedstoneComparator.Mode value2 = (BlockRedstoneComparator.Mode)state.getValue(MODE);
      EnumFacing value3 = (EnumFacing)state.getValue(FACING);
      return Blocks.powered_comparator.getDefaultState().withProperty(FACING, value3).withProperty(POWERED, value).withProperty(MODE, value2);
   }

   @Override
   public IBlockState getUnpoweredState(IBlockState state) {
      Boolean value = (Boolean)state.getValue(POWERED);
      BlockRedstoneComparator.Mode value2 = (BlockRedstoneComparator.Mode)state.getValue(MODE);
      EnumFacing value3 = (EnumFacing)state.getValue(FACING);
      return Blocks.unpowered_comparator.getDefaultState().withProperty(FACING, value3).withProperty(POWERED, value).withProperty(MODE, value2);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (this.isRepeaterPowered) {
         world.setBlockState(pos, this.getUnpoweredState(state).withProperty(POWERED, Boolean.TRUE), 4);
      }

      this.onStateChange(world, pos, state);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if ((Boolean)state.getValue(POWERED)) {
         n |= 8;
      }

      if (state.getValue(MODE) == BlockRedstoneComparator.Mode.SUBTRACT) {
         n |= 4;
      }

      return n;
   }

   @Override
   public boolean shouldBePowered(World world, BlockPos pos, IBlockState state) {
      int n = this.calculateInputStrength(world, pos, state);
      if (n >= 15) {
         return true;
      } else if (n == 0) {
         return false;
      } else {
         int powerOnSides = this.getPowerOnSides(world, pos.getX(), pos.getY(), pos.getZ(), state);
         return powerOnSides == 0 ? true : n >= powerOnSides;
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState()
         .withProperty(FACING, entity.getHorizontalFacing().getOpposite())
         .withProperty(POWERED, Boolean.FALSE)
         .withProperty(MODE, BlockRedstoneComparator.Mode.COMPARE);
   }

   public BlockRedstoneComparator(boolean flag) {
      super(flag);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(POWERED, Boolean.FALSE)
            .withProperty(MODE, BlockRedstoneComparator.Mode.COMPARE)
      );
      this.isBlockContainer = true;
   }

   @Override
   public int calculateInputStrength(World world, BlockPos pos, IBlockState state) {
      int n = super.calculateInputStrength(world, pos, state);
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      BlockPos pos2 = pos.offset(value);
      Block block = world.getBlockState(pos2).getBlock();
      if (block.hasComparatorInputOverride()) {
         n = block.getComparatorInputOverride(world, pos2);
      } else if (n < 15 && block.isNormalCube()) {
         pos2 = pos2.offset(value);
         block = world.getBlockState(pos2).getBlock();
         if (block.hasComparatorInputOverride()) {
            n = block.getComparatorInputOverride(world, pos2);
         } else if (block.getMaterial() == Material.air) {
            EntityItemFrame entityItemFrame = this.findItemFrame(world, value, pos2);
            if (entityItemFrame != null) {
               n = entityItemFrame.getAnalogOutput();
            }
         }
      }

      return n;
   }

   @Override
   public boolean isPowered(IBlockState state) {
      return this.isRepeaterPowered || (Boolean)state.getValue(POWERED);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, MODE, POWERED});
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(FACING, EnumFacing.getHorizontal(n))
         .withProperty(POWERED, (n & 8) > 0)
         .withProperty(MODE, (n & 4) > 0 ? BlockRedstoneComparator.Mode.SUBTRACT : BlockRedstoneComparator.Mode.COMPARE);
   }

   private int calculateOutput(World world, BlockPos pos, IBlockState state) {
      return state.getValue(MODE) == BlockRedstoneComparator.Mode.SUBTRACT
         ? Math.max(this.calculateInputStrength(world, pos, state) - this.getPowerOnSides(world, pos.getX(), pos.getY(), pos.getZ(), state), 0)
         : this.calculateInputStrength(world, pos, state);
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("item.comparator.name");
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      super.onBlockAdded(world, pos, state);
      world.setTileEntity(pos, this.createNewTileEntity(world, 0));
   }

   @Override
   public int getActiveSignal(IBlockAccess world, BlockPos pos, IBlockState state) {
      TileEntity tileEntity = world.getTileEntity(pos);
      return tileEntity instanceof TileEntityComparator ? ((TileEntityComparator)tileEntity).getOutputSignal() : 0;
   }

   @Override
   public boolean onBlockEventReceived(World world, BlockPos pos, IBlockState state, int n, int n2) {
      super.onBlockEventReceived(world, pos, state, n, n2);
      TileEntity tileEntity = world.getTileEntity(pos);
      return tileEntity == null ? false : tileEntity.receiveClientEvent(n, n2);
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      super.breakBlock(world, pos, state);
      world.removeTileEntity(pos);
      this.notifyNeighbors(world, pos, state);
   }


   public static class FilterPredicate implements Predicate {
      public final EnumFacing facing;
      public final BlockRedstoneComparator comparator;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         return entity != null && entity.getHorizontalFacing() == this.facing;
      }

      public FilterPredicate(BlockRedstoneComparator blockRedstoneComparator, EnumFacing facing2) {
         this.comparator = blockRedstoneComparator;
         this.facing = facing2;
      }
   }

   public enum Mode implements IStringSerializable {
      COMPARE("compare"),
      SUBTRACT("subtract");

   private final String name;
   private Mode(String str) {
         this.name = str;
      }

      @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }
   }
}
