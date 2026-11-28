package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockPistonStructureHelper;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonBase extends Block {
   public static final PropertyDirection FACING = PropertyDirection.create("facing");
   public static final PropertyBool EXTENDED = PropertyBool.create("extended");
   private final boolean isSticky;

   public BlockPistonBase(boolean flag) {
      super(Material.piston);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EXTENDED, Boolean.FALSE));
      this.isSticky = flag;
      this.setStepSound(soundTypePiston);
      this.setHardness(0.5F);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public static EnumFacing getFacingFromEntity(World world, BlockPos pos, EntityLivingBase entity) {
      if (MathHelper.abs((float)entity.posX - (float)pos.getX()) < 2.0F && MathHelper.abs((float)entity.posZ - (float)pos.getZ()) < 2.0F) {
         double d = entity.posY + (double)entity.getEyeHeight();
         if (d - (double)pos.getY() > 2.0) {
            return EnumFacing.UP;
         }

         if ((double)pos.getY() - d > 0.0) {
            return EnumFacing.DOWN;
         }
      }

      return entity.getHorizontalFacing().getOpposite();
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   private void checkForMove(World world, BlockPos pos, IBlockState state) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      boolean flag = this.shouldBeExtended(world, pos, value);
      if (flag && !(Boolean)state.getValue(EXTENDED)) {
         if (new BlockPistonStructureHelper(world, pos, value, true).canMove()) {
            world.addBlockEvent(pos, this, 0, value.getIndex());
         }
      } else if (!flag && (Boolean)state.getValue(EXTENDED)) {
         world.setBlockState(pos, state.withProperty(EXTENDED, Boolean.FALSE), 2);
         world.addBlockEvent(pos, this, 1, value.getIndex());
      }
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote && world.getTileEntity(pos) == null) {
         this.checkForMove(world, pos, state);
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.getCollisionBoundingBox(world, state, n, n2, n3);
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.isRemote) {
         this.checkForMove(world, pos, state);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, EXTENDED});
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if ((Boolean)state.getValue(EXTENDED)) {
         n |= 8;
      }

      return n;
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      world.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(world, pos, entity)), 2);
      if (!world.isRemote) {
         this.checkForMove(world, pos, state);
      }
   }

   @Override
   public IBlockState getStateForEntityRender(IBlockState state) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.UP);
   }

   public static boolean canPush(Block block, World world, BlockPos pos, EnumFacing facing, boolean flag) {
      if (block == Blocks.obsidian) {
         return false;
      } else if (!world.getWorldBorder().contains(pos)) {
         return false;
      } else if (pos.getY() >= 0 && (facing != EnumFacing.DOWN || pos.getY() != 0)) {
         if (pos.getY() <= world.getHeight() - 1 && (facing != EnumFacing.UP || pos.getY() != world.getHeight() - 1)) {
            if (block != Blocks.piston && block != Blocks.sticky_piston) {
               if (block.getBlockHardness(world, pos) == -1.0F) {
                  return false;
               }

               if (block.getMobilityFlag() == 2) {
                  return false;
               }

               if (block.getMobilityFlag() == 1) {
                  if (!flag) {
                     return false;
                  }

                  return true;
               }
            } else if ((Boolean)world.getBlockState(pos).getValue(EXTENDED)) {
               return false;
            }

            return !(block instanceof ITileEntityProvider);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean shouldBeExtended(World world, BlockPos pos, EnumFacing facing) {
      for (EnumFacing facing2 : EnumFacing.getValues()) {
         if (facing2 != facing && world.isSidePowered(pos.offset(facing2), facing2)) {
            return true;
         }
      }

      if (world.isSidePowered(pos, EnumFacing.DOWN)) {
         return true;
      } else {
         BlockPos pos2 = pos.up();

         for (EnumFacing facing3 : EnumFacing.getValues()) {
            if (facing3 != EnumFacing.DOWN && world.isSidePowered(pos2.offset(facing3), facing3)) {
               return true;
            }
         }

         return false;
      }
   }

   public static EnumFacing getFacing(int n) {
      int n2 = n & 7;
      return n2 > 5 ? null : EnumFacing.getFront(n2);
   }

   @Override
   public boolean onBlockEventReceived(World world, BlockPos pos, IBlockState state, int n, int n2) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      if (!world.isRemote) {
         boolean flag = this.shouldBeExtended(world, pos, value);
         if (flag && n == 1) {
            world.setBlockState(pos, state.withProperty(EXTENDED, Boolean.TRUE), 2);
            return false;
         }

         if (!flag && n == 0) {
            return false;
         }
      }

      if (n == 0) {
         if (!this.doMove(world, pos, value, true)) {
            return false;
         }

         world.setBlockState(pos, state.withProperty(EXTENDED, Boolean.TRUE), 2);
         world.playSoundEffect(
            (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "tile.piston.out", 0.5F, world.rand.nextFloat() * 0.25F + 0.6F
         );
      } else if (n == 1) {
         TileEntity tileEntity = world.getTileEntity(pos.offset(value));
         if (tileEntity instanceof TileEntityPiston) {
            ((TileEntityPiston)tileEntity).clearPistonTileEntity();
         }

         world.setBlockState(
            pos,
            Blocks.piston_extension
               .getDefaultState()
               .withProperty(BlockPistonMoving.FACING, value)
               .withProperty(BlockPistonMoving.TYPE, this.isSticky ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT),
            3
         );
         world.setTileEntity(pos, BlockPistonMoving.newTileEntity(this.getStateFromMeta(n2), value, false, true));
         if (this.isSticky) {
            BlockPos pos2 = pos.add(value.getFrontOffsetX() * 2, value.getFrontOffsetY() * 2, value.getFrontOffsetZ() * 2);
            Block block = world.getBlockState(pos2).getBlock();
            boolean flag2 = false;
            if (block == Blocks.piston_extension) {
               TileEntity tileEntity2 = world.getTileEntity(pos2);
               if (tileEntity2 instanceof TileEntityPiston) {
                  TileEntityPiston tileEntityPiston = (TileEntityPiston)tileEntity2;
                  if (tileEntityPiston.getFacing() == value && tileEntityPiston.isExtending()) {
                     tileEntityPiston.clearPistonTileEntity();
                     flag2 = true;
                  }
               }
            }

            if (!flag2
               && block.getMaterial() != Material.air
               && canPush(block, world, pos2, value.getOpposite(), false)
               && (block.getMobilityFlag() == 0 || block == Blocks.piston || block == Blocks.sticky_piston)) {
               this.doMove(world, pos, value, false);
            }
         } else {
            world.setBlockToAir(pos.offset(value));
         }

         world.playSoundEffect(
            (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "tile.piston.in", 0.5F, world.rand.nextFloat() * 0.15F + 0.6F
         );
      }

      return true;
   }

   private boolean doMove(World world, BlockPos pos, EnumFacing facing, boolean flag) {
      if (!flag) {
         world.setBlockToAir(pos.offset(facing));
      }

      BlockPistonStructureHelper helper = new BlockPistonStructureHelper(world, pos, facing, flag);
      List blocksToMove = helper.getBlocksToMove();
      List blocksToDestroy = helper.getBlocksToDestroy();
      if (!helper.canMove()) {
         return false;
      } else {
         int n = blocksToMove.size() + blocksToDestroy.size();
         Block[] blocks = new Block[n];
         EnumFacing facing2 = flag ? facing : facing.getOpposite();

         for (int i = blocksToDestroy.size() - 1; i >= 0; i--) {
            BlockPos pos2 = (BlockPos)blocksToDestroy.get(i);
            Block block = world.getBlockState(pos2).getBlock();
            block.dropBlockAsItem(world, pos2, world.getBlockState(pos2), 0);
            world.setBlockToAir(pos2);
            n--;
            blocks[n] = block;
         }

         for (int j = blocksToMove.size() - 1; j >= 0; j--) {
            BlockPos pos3 = (BlockPos)blocksToMove.get(j);
            IBlockState blockState2 = world.getBlockState(pos3);
            Block block2 = blockState2.getBlock();
            block2.getMetaFromState(blockState2);
            world.setBlockToAir(pos3);
            pos3 = pos3.offset(facing2);
            world.setBlockState(pos3, Blocks.piston_extension.getDefaultState().withProperty(FACING, facing), 4);
            world.setTileEntity(pos3, BlockPistonMoving.newTileEntity(blockState2, facing, flag, false));
            n--;
            blocks[n] = block2;
         }

         BlockPos pos4 = pos.offset(facing);
         if (flag) {
            BlockPistonExtension.EnumPistonType enumPistonType = this.isSticky
               ? BlockPistonExtension.EnumPistonType.STICKY
               : BlockPistonExtension.EnumPistonType.DEFAULT;
            IBlockState state = Blocks.piston_head
               .getDefaultState()
               .withProperty(BlockPistonExtension.FACING, facing)
               .withProperty(BlockPistonExtension.TYPE, enumPistonType);
            IBlockState state2 = Blocks.piston_extension
               .getDefaultState()
               .withProperty(BlockPistonMoving.FACING, facing)
               .withProperty(BlockPistonMoving.TYPE, this.isSticky ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
            world.setBlockState(pos4, state2, 4);
            world.setTileEntity(pos4, BlockPistonMoving.newTileEntity(state, facing, true, false));
         }

         for (int k = blocksToDestroy.size() - 1; k >= 0; k--) {
            world.notifyNeighborsOfStateChange((BlockPos)blocksToDestroy.get(k), blocks[n++]);
         }

         for (int m = blocksToMove.size() - 1; m >= 0; m--) {
            world.notifyNeighborsOfStateChange((BlockPos)blocksToMove.get(m), blocks[n++]);
         }

         if (flag) {
            world.notifyNeighborsOfStateChange(pos4, Blocks.piston_head);
            world.notifyNeighborsOfStateChange(pos, this);
         }

         return true;
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(FACING, getFacingFromEntity(world, pos, entity)).withProperty(EXTENDED, Boolean.FALSE);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(FACING, getFacing(n)).withProperty(EXTENDED, (n & 8) > 0);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      if (blockState2.getBlock() == this && (Boolean)blockState2.getValue(EXTENDED)) {
         float f = 0.25F;
         EnumFacing value = (EnumFacing)blockState2.getValue(FACING);
         if (value != null) {
            switch (value) {
               case DOWN:
                  this.setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case UP:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                  break;
               case NORTH:
                  this.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                  break;
               case SOUTH:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                  break;
               case WEST:
                  this.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case EAST:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
         }
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
   }

}
