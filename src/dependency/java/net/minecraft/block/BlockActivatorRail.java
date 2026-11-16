package net.minecraft.block;

import client.setting.MovementProfileProperty;
import client.util.AngleSystem;
import client.util.AuxiliaryPredicateHandler;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockActivatorRail extends SpecialBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;

   @Override
   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      boolean flag = world.matchesBlockPos7(blockPos) || this.matchesWorld(world, blockPos, blockState, true, 0) || this.matchesWorld(world, blockPos, blockState, false, 0);
      if (flag != comparable) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, flag), 3);
         world.applyBlockPosByBlockPos(blockPos.down(), this);
         if (((MovementProfileProperty)blockState.getComparable(immutableSetEntry)).isActive()) {
            world.applyBlockPosByBlockPos(blockPos.up(), this);
         }
      }
   }

   public boolean matchesWorld(World world, BlockPos blockPos, boolean flag, int count, MovementProfileProperty movementProfileProperty) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (blockState.getBlock() != this) {
         return false;
      } else {
         MovementProfileProperty comparable = (MovementProfileProperty)blockState.getComparable(immutableSetEntry);
         return movementProfileProperty != MovementProfileProperty.EAST_WEST
               || comparable != MovementProfileProperty.NORTH_SOUTH
                  && comparable != MovementProfileProperty.ASCENDING_NORTH
                  && comparable != MovementProfileProperty.ASCENDING_SOUTH
            ? (
               movementProfileProperty != MovementProfileProperty.NORTH_SOUTH
                     || comparable != MovementProfileProperty.EAST_WEST
                        && comparable != MovementProfileProperty.ASCENDING_EAST
                        && comparable != MovementProfileProperty.ASCENDING_WEST
                  ? (
                     (Boolean)blockState.getComparable(internalImmutableSetEntry)
                        ? (world.matchesBlockPos7(blockPos) ? true : this.matchesWorld(world, blockPos, blockState, flag, count + 1))
                        : false
                  )
                  : false
            )
            : false;
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(immutableSetEntry, MovementProfileProperty.resolveMovementProfileProperty(count & 7))
         .resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0);
   }

   @Override
   public IProperty getIProperty() {
      return immutableSetEntry;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((MovementProfileProperty)blockState.getComparable(immutableSetEntry)).getCount();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      return count;
   }

   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag, int count) {
      if (count >= 8) {
         return false;
      } else {
         int coordX = blockPos.getX();
         int coordY = blockPos.getY();
         int coordZ = blockPos.getZ();
         boolean flag2 = true;
         MovementProfileProperty comparable = (MovementProfileProperty)blockState.getComparable(immutableSetEntry);
         switch (AngleSystem.countArray[comparable.ordinal()]) {
            case 1:
               if (flag) {
                  coordZ++;
               } else {
                  coordZ--;
               }
               break;
            case 2:
               if (flag) {
                  coordX--;
               } else {
                  coordX++;
               }
               break;
            case 3:
               if (flag) {
                  coordX--;
               } else {
                  coordX++;
                  coordY++;
                  flag2 = false;
               }

               comparable = MovementProfileProperty.EAST_WEST;
               break;
            case 4:
               if (flag) {
                  coordX--;
                  coordY++;
                  flag2 = false;
               } else {
                  coordX++;
               }

               comparable = MovementProfileProperty.EAST_WEST;
               break;
            case 5:
               if (flag) {
                  coordZ++;
               } else {
                  coordZ--;
                  coordY++;
                  flag2 = false;
               }

               comparable = MovementProfileProperty.NORTH_SOUTH;
               break;
            case 6:
               if (flag) {
                  coordZ++;
                  coordY++;
                  flag2 = false;
               } else {
                  coordZ--;
               }

               comparable = MovementProfileProperty.NORTH_SOUTH;
         }

         return this.matchesWorld(world, new BlockPos(coordX, coordY, coordZ), flag, count, comparable)
            ? true
            : flag2 && this.matchesWorld(world, new BlockPos(coordX, coordY - 1, coordZ), flag, count, comparable);
      }
   }

   public BlockActivatorRail() {
      super(true);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(immutableSetEntry, MovementProfileProperty.NORTH_SOUTH)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
   }

   static {
      String[] textArray = new String[]{"shape", "powered"};
      immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(textArray[0], MovementProfileProperty.class, new AuxiliaryPredicateHandler());
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
   }
}
