package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public abstract class StructureComponent {
   public StructureBoundingBox boundingBox;
   public EnumFacing coordBaseMode;
   public int componentType;

   public BlockPos getBoundingBoxCenter() {
      return new BlockPos(this.boundingBox.getCenter());
   }

   public int getXWithOffset(int n, int n2) {
      if (this.coordBaseMode == null) {
         return n;
      } else {
         switch (this.coordBaseMode) {
            case NORTH:
            case SOUTH:
               return this.boundingBox.minX + n;
            case WEST:
               return this.boundingBox.maxX - n2;
            case EAST:
               return this.boundingBox.minX + n2;
            default:
               return n;
         }
      }
   }

   public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
   }

   public boolean generateChestContents(World world, StructureBoundingBox structureBoundingBox, Random rand, int n, int n2, int n3, List list, int n4) {
      BlockPos pos = new BlockPos(this.getXWithOffset(n, n3), this.getYWithOffset(n2), this.getZWithOffset(n, n3));
      if (structureBoundingBox.isVecInside(pos) && world.getBlockState(pos).getBlock() != Blocks.chest) {
         IBlockState defaultState = Blocks.chest.getDefaultState();
         world.setBlockState(pos, Blocks.chest.correctFacing(world, pos, defaultState), 2);
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityChest) {
            WeightedRandomChestContent.generateChestContents(rand, list, (TileEntityChest)tileEntity, n4);
         }

         return true;
      } else {
         return false;
      }
   }

   public abstract void readStructureFromNBT(NBTTagCompound tag);

   public void clearCurrentPositionBlocksUpwards(World world, int n, int n2, int n3, StructureBoundingBox structureBoundingBox) {
      BlockPos pos = new BlockPos(this.getXWithOffset(n, n3), this.getYWithOffset(n2), this.getZWithOffset(n, n3));
      if (structureBoundingBox.isVecInside(pos)) {
         while (!world.isAirBlock(pos) && pos.getY() < 255) {
            world.setBlockState(pos, Blocks.air.getDefaultState(), 2);
            pos = pos.up();
         }
      }
   }

   public abstract void writeStructureToNBT(NBTTagCompound tag);

   public NBTTagCompound createStructureBaseNBT() {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setString("id", MapGenStructureIO.getStructureComponentName(this));
      tag.setTag("BB", this.boundingBox.toNBTTagIntArray());
      tag.setInteger("O", this.coordBaseMode == null ? -1 : this.coordBaseMode.getHorizontalIndex());
      tag.setInteger("GD", this.componentType);
      this.writeStructureToNBT(tag);
      return tag;
   }

   public IBlockState getBlockStateFromPos(World world, int n, int n2, int n3, StructureBoundingBox structureBoundingBox) {
      int xWithOffset = this.getXWithOffset(n, n3);
      int yWithOffset = this.getYWithOffset(n2);
      int zWithOffset = this.getZWithOffset(n, n3);
      BlockPos pos = new BlockPos(xWithOffset, yWithOffset, zWithOffset);
      return !structureBoundingBox.isVecInside(pos) ? Blocks.air.getDefaultState() : world.getBlockState(pos);
   }

   public StructureComponent(int n) {
      this.componentType = n;
   }

   public boolean generateDispenserContents(World world, StructureBoundingBox structureBoundingBox, Random rand, int n, int n2, int n3, int n4, List list, int n5) {
      BlockPos pos = new BlockPos(this.getXWithOffset(n, n3), this.getYWithOffset(n2), this.getZWithOffset(n, n3));
      if (structureBoundingBox.isVecInside(pos) && world.getBlockState(pos).getBlock() != Blocks.dispenser) {
         world.setBlockState(pos, Blocks.dispenser.getStateFromMeta(this.getMetadataWithOffset(Blocks.dispenser, n4)), 2);
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityDispenser) {
            WeightedRandomChestContent.generateDispenserContents(rand, list, (TileEntityDispenser)tileEntity, n5);
         }

         return true;
      } else {
         return false;
      }
   }

   public void fillWithAir(World world, StructureBoundingBox structureBoundingBox, int n, int n2, int n3, int n4, int n5, int n6) {
      for (int i = n2; i <= n5; i++) {
         for (int j = n; j <= n4; j++) {
            for (int k = n3; k <= n6; k++) {
               this.setBlockState(world, Blocks.air.getDefaultState(), j, i, k, structureBoundingBox);
            }
         }
      }
   }

   public void randomlyPlaceBlock(World world, StructureBoundingBox structureBoundingBox, Random rand, float f, int n, int n2, int n3, IBlockState state) {
      if (rand.nextFloat() < f) {
         this.setBlockState(world, state, n, n2, n3, structureBoundingBox);
      }
   }

   public static StructureComponent findIntersecting(List list, StructureBoundingBox structureBoundingBox) {
      for (StructureComponent structureComponent : (Iterable<StructureComponent>)(list)) {
         if (structureComponent.getBoundingBox() != null && structureComponent.getBoundingBox().intersectsWith(structureBoundingBox)) {
            return structureComponent;
         }
      }

      return null;
   }

   public void readStructureBaseNBT(World world, NBTTagCompound tag) {
      if (tag.hasKey("BB")) {
         this.boundingBox = new StructureBoundingBox(tag.getIntArray("BB"));
      }

      int integer = tag.getInteger("O");
      this.coordBaseMode = integer == -1 ? null : EnumFacing.getHorizontal(integer);
      this.componentType = tag.getInteger("GD");
      this.readStructureFromNBT(tag);
   }

   public StructureBoundingBox getBoundingBox() {
      return this.boundingBox;
   }

   public void fillWithRandomizedBlocks(
      World world,
      StructureBoundingBox structureBoundingBox,
      int n,
      int n2,
      int n3,
      int n4,
      int n5,
      int n6,
      boolean flag,
      Random rand,
      StructureComponent.BlockSelector blockSelector
   ) {
      for (int i = n2; i <= n5; i++) {
         for (int j = n; j <= n4; j++) {
            for (int k = n3; k <= n6; k++) {
               if (!flag || this.getBlockStateFromPos(world, j, i, k, structureBoundingBox).getBlock().getMaterial() != Material.air) {
                  blockSelector.selectBlocks(
                     rand, j, i, k, i == n2 || i == n5 || j == n || j == n4 || k == n3 || k == n6
                  );
                  this.setBlockState(world, blockSelector.getBlockState(), j, i, k, structureBoundingBox);
               }
            }
         }
      }
   }

   public void fillWithBlocks(
      World world, StructureBoundingBox structureBoundingBox, int n, int n2, int n3, int n4, int n5, int n6, IBlockState state, IBlockState state2, boolean flag
   ) {
      for (int i = n2; i <= n5; i++) {
         for (int j = n; j <= n4; j++) {
            for (int k = n3; k <= n6; k++) {
               if (!flag || this.getBlockStateFromPos(world, j, i, k, structureBoundingBox).getBlock().getMaterial() != Material.air) {
                  if (i != n2 && i != n5 && j != n && j != n4 && k != n3 && k != n6) {
                     this.setBlockState(world, state2, j, i, k, structureBoundingBox);
                  } else {
                     this.setBlockState(world, state, j, i, k, structureBoundingBox);
                  }
               }
            }
         }
      }
   }

   public int getComponentType() {
      return this.componentType;
   }

   public void placeDoorCurrentPosition(World world, StructureBoundingBox structureBoundingBox, Random rand, int n, int n2, int n3, EnumFacing facing) {
      BlockPos pos = new BlockPos(this.getXWithOffset(n, n3), this.getYWithOffset(n2), this.getZWithOffset(n, n3));
      if (structureBoundingBox.isVecInside(pos)) {
         ItemDoor.placeDoor(world, pos, facing.rotateYCCW(), Blocks.oak_door);
      }
   }

   public int getZWithOffset(int n, int n2) {
      if (this.coordBaseMode == null) {
         return n2;
      } else {
         switch (this.coordBaseMode) {
            case NORTH:
               return this.boundingBox.maxZ - n2;
            case SOUTH:
               return this.boundingBox.minZ + n2;
            case WEST:
            case EAST:
               return this.boundingBox.minZ + n;
            default:
               return n2;
         }
      }
   }

   public void offset(int n, int n2, int n3) {
      this.boundingBox.offset(n, n2, n3);
   }

   public StructureComponent() {
   }

   public boolean isLiquidInStructureBoundingBox(World world, StructureBoundingBox structureBoundingBox) {
      int n = Math.max(this.boundingBox.minX - 1, structureBoundingBox.minX);
      int n2 = Math.max(this.boundingBox.minY - 1, structureBoundingBox.minY);
      int n3 = Math.max(this.boundingBox.minZ - 1, structureBoundingBox.minZ);
      int n4 = Math.min(this.boundingBox.maxX + 1, structureBoundingBox.maxX);
      int n5 = Math.min(this.boundingBox.maxY + 1, structureBoundingBox.maxY);
      int n6 = Math.min(this.boundingBox.maxZ + 1, structureBoundingBox.maxZ);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i <= n4; i++) {
         for (int j = n3; j <= n6; j++) {
            if (world.getBlockState(pos.set(i, n2, j)).getBlock().getMaterial().isLiquid()) {
               return true;
            }

            if (world.getBlockState(pos.set(i, n5, j)).getBlock().getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      for (int k = n; k <= n4; k++) {
         for (int m = n2; m <= n5; m++) {
            if (world.getBlockState(pos.set(k, m, n3)).getBlock().getMaterial().isLiquid()) {
               return true;
            }

            if (world.getBlockState(pos.set(k, m, n6)).getBlock().getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      for (int idx = n3; idx <= n6; idx++) {
         for (int index = n2; index <= n5; index++) {
            if (world.getBlockState(pos.set(n, index, idx)).getBlock().getMaterial().isLiquid()) {
               return true;
            }

            if (world.getBlockState(pos.set(n4, index, idx)).getBlock().getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      return false;
   }

   public abstract boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox);

   public void setBlockState(World world, IBlockState state, int n, int n2, int n3, StructureBoundingBox structureBoundingBox) {
      BlockPos pos = new BlockPos(this.getXWithOffset(n, n3), this.getYWithOffset(n2), this.getZWithOffset(n, n3));
      if (structureBoundingBox.isVecInside(pos)) {
         world.setBlockState(pos, state, 2);
      }
   }

   public int getMetadataWithOffset(Block block, int n) {
      if (block == Blocks.rail) {
         if (this.coordBaseMode == EnumFacing.WEST || this.coordBaseMode == EnumFacing.EAST) {
            if (n == 1) {
               return 0;
            }

            return 1;
         }
      } else if (block instanceof BlockDoor) {
         if (this.coordBaseMode == EnumFacing.SOUTH) {
            if (n == 0) {
               return 2;
            }

            if (n == 2) {
               return 0;
            }
         } else {
            if (this.coordBaseMode == EnumFacing.WEST) {
               return n + 1 & 3;
            }

            if (this.coordBaseMode == EnumFacing.EAST) {
               return n + 3 & 3;
            }
         }
      } else if (block != Blocks.stone_stairs
         && block != Blocks.oak_stairs
         && block != Blocks.nether_brick_stairs
         && block != Blocks.stone_brick_stairs
         && block != Blocks.sandstone_stairs) {
         if (block == Blocks.ladder) {
            if (this.coordBaseMode == EnumFacing.SOUTH) {
               if (n == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }

               if (n == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }
            } else if (this.coordBaseMode == EnumFacing.WEST) {
               if (n == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.WEST.getIndex();
               }

               if (n == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.EAST.getIndex();
               }

               if (n == EnumFacing.WEST.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }

               if (n == EnumFacing.EAST.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }
            } else if (this.coordBaseMode == EnumFacing.EAST) {
               if (n == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.EAST.getIndex();
               }

               if (n == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.WEST.getIndex();
               }

               if (n == EnumFacing.WEST.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }

               if (n == EnumFacing.EAST.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }
            }
         } else if (block == Blocks.stone_button) {
            if (this.coordBaseMode == EnumFacing.SOUTH) {
               if (n == 3) {
                  return 4;
               }

               if (n == 4) {
                  return 3;
               }
            } else if (this.coordBaseMode == EnumFacing.WEST) {
               if (n == 3) {
                  return 1;
               }

               if (n == 4) {
                  return 2;
               }

               if (n == 2) {
                  return 3;
               }

               if (n == 1) {
                  return 4;
               }
            } else if (this.coordBaseMode == EnumFacing.EAST) {
               if (n == 3) {
                  return 2;
               }

               if (n == 4) {
                  return 1;
               }

               if (n == 2) {
                  return 3;
               }

               if (n == 1) {
                  return 4;
               }
            }
         } else if (block == Blocks.tripwire_hook || block instanceof BlockDirectional) {
            EnumFacing horizontal = EnumFacing.getHorizontal(n);
            if (this.coordBaseMode == EnumFacing.SOUTH) {
               if (horizontal == EnumFacing.SOUTH || horizontal == EnumFacing.NORTH) {
                  return horizontal.getOpposite().getHorizontalIndex();
               }
            } else if (this.coordBaseMode == EnumFacing.WEST) {
               if (horizontal == EnumFacing.NORTH) {
                  return EnumFacing.WEST.getHorizontalIndex();
               }

               if (horizontal == EnumFacing.SOUTH) {
                  return EnumFacing.EAST.getHorizontalIndex();
               }

               if (horizontal == EnumFacing.WEST) {
                  return EnumFacing.NORTH.getHorizontalIndex();
               }

               if (horizontal == EnumFacing.EAST) {
                  return EnumFacing.SOUTH.getHorizontalIndex();
               }
            } else if (this.coordBaseMode == EnumFacing.EAST) {
               if (horizontal == EnumFacing.NORTH) {
                  return EnumFacing.EAST.getHorizontalIndex();
               }

               if (horizontal == EnumFacing.SOUTH) {
                  return EnumFacing.WEST.getHorizontalIndex();
               }

               if (horizontal == EnumFacing.WEST) {
                  return EnumFacing.NORTH.getHorizontalIndex();
               }

               if (horizontal == EnumFacing.EAST) {
                  return EnumFacing.SOUTH.getHorizontalIndex();
               }
            }
         } else if (block == Blocks.piston || block == Blocks.sticky_piston || block == Blocks.lever || block == Blocks.dispenser) {
            if (this.coordBaseMode == EnumFacing.SOUTH) {
               if (n == EnumFacing.NORTH.getIndex() || n == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.getFront(n).getOpposite().getIndex();
               }
            } else if (this.coordBaseMode == EnumFacing.WEST) {
               if (n == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.WEST.getIndex();
               }

               if (n == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.EAST.getIndex();
               }

               if (n == EnumFacing.WEST.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }

               if (n == EnumFacing.EAST.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }
            } else if (this.coordBaseMode == EnumFacing.EAST) {
               if (n == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.EAST.getIndex();
               }

               if (n == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.WEST.getIndex();
               }

               if (n == EnumFacing.WEST.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }

               if (n == EnumFacing.EAST.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }
            }
         }
      } else if (this.coordBaseMode == EnumFacing.SOUTH) {
         if (n == 2) {
            return 3;
         }

         if (n == 3) {
            return 2;
         }
      } else if (this.coordBaseMode == EnumFacing.WEST) {
         if (n == 0) {
            return 2;
         }

         if (n == 1) {
            return 3;
         }

         if (n == 2) {
            return 0;
         }

         if (n == 3) {
            return 1;
         }
      } else if (this.coordBaseMode == EnumFacing.EAST) {
         if (n == 0) {
            return 2;
         }

         if (n == 1) {
            return 3;
         }

         if (n == 2) {
            return 1;
         }

         if (n == 3) {
            return 0;
         }
      }

      return n;
   }

   public void replaceAirAndLiquidDownwards(World world, IBlockState state, int n, int n2, int n3, StructureBoundingBox structureBoundingBox) {
      int xWithOffset = this.getXWithOffset(n, n3);
      int yWithOffset = this.getYWithOffset(n2);
      int zWithOffset = this.getZWithOffset(n, n3);
      if (structureBoundingBox.isVecInside(new BlockPos(xWithOffset, yWithOffset, zWithOffset))) {
         while (
            (world.isAirBlock(new BlockPos(xWithOffset, yWithOffset, zWithOffset)) || world.getBlockState(new BlockPos(xWithOffset, yWithOffset, zWithOffset)).getBlock().getMaterial().isLiquid())
               && yWithOffset > 1
         ) {
            world.setBlockState(new BlockPos(xWithOffset, yWithOffset, zWithOffset), state, 2);
            yWithOffset--;
         }
      }
   }

   public int getYWithOffset(int n) {
      return this.coordBaseMode == null ? n : n + this.boundingBox.minY;
   }

   public void randomlyRareFillWithBlocks(
      World world, StructureBoundingBox structureBoundingBox, int n, int n2, int n3, int n4, int n5, int n6, IBlockState state, boolean flag
   ) {
      float f = (float)(n4 - n + 1);
      float f2 = (float)(n5 - n2 + 1);
      float f3 = (float)(n6 - n3 + 1);
      float f4 = (float)n + f / 2.0F;
      float f5 = (float)n3 + f3 / 2.0F;

      for (int i = n2; i <= n5; i++) {
         float f6 = (float)(i - n2) / f2;

         for (int j = n; j <= n4; j++) {
            float f7 = ((float)j - f4) / (f * 0.5F);

            for (int k = n3; k <= n6; k++) {
               float f8 = ((float)k - f5) / (f3 * 0.5F);
               if (!flag || this.getBlockStateFromPos(world, j, i, k, structureBoundingBox).getBlock().getMaterial() != Material.air) {
                  if ((f7 * f7 + f6 * f6 + f8 * f8) <= 1.05F) {
                     this.setBlockState(world, state, j, i, k, structureBoundingBox);
                  }
               }
            }
         }
      }
   }

   public void generateMaybeBox(
      World world,
      StructureBoundingBox structureBoundingBox,
      Random rand,
      float f,
      int n,
      int n2,
      int n3,
      int n4,
      int n5,
      int n6,
      IBlockState state,
      IBlockState state2,
      boolean flag
   ) {
      for (int i = n2; i <= n5; i++) {
         for (int j = n; j <= n4; j++) {
            for (int k = n3; k <= n6; k++) {
               if (rand.nextFloat() <= f && (!flag || this.getBlockStateFromPos(world, j, i, k, structureBoundingBox).getBlock().getMaterial() != Material.air)) {
                  if (i != n2 && i != n5 && j != n && j != n4 && k != n3 && k != n6) {
                     this.setBlockState(world, state2, j, i, k, structureBoundingBox);
                  } else {
                     this.setBlockState(world, state, j, i, k, structureBoundingBox);
                  }
               }
            }
         }
      }
   }

public static abstract class BlockSelector {
      public IBlockState blockstate = Blocks.air.getDefaultState();

      public IBlockState getBlockState() {
         return this.blockstate;
      }

      public abstract void selectBlocks(Random rand, int n, int n2, int n3, boolean flag);
   }
}
