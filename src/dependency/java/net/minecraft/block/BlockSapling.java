package net.minecraft.block;

import client.event.PreMotionFilter;
import client.setting.MovementProperty;
import client.util.AdvancedIBlockStateEntry;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.ClientManagerCoordinator;
import client.util.CurveParser;
import client.util.CustomRandomEntry;
import client.util.DecayableManager;
import client.util.ExtendedIBlockStateEntry;
import client.util.IBlockState;
import client.util.IBlockStateEntryBridge;
import client.util.ImmutableSetEntry;
import client.util.InternalClientManagerController;
import client.util.RotationPicker;
import client.util.SecondaryClientManagerHandler;
import client.util.SecondaryIBlockStateEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSapling extends SecondaryBlockAdapter implements PreMotionFilter {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("type", MovementProperty.class);
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("stage", 0, 1);

   public boolean matchesWorld(World world, BlockPos blockPos, MovementProperty movementProperty) {
      IBlockState blockState = world.getBlockState(blockPos);
      return blockState.getBlock() == this && blockState.getComparable(immutableSetEntry) == movementProperty;
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state) {
         super.updateTick(world, blockPos, blockState, random);
         if (world.computeCountByIndex(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()) >= 9 && random.nextInt(7) == 0) {
            this.applyWorld5(world, blockPos, blockState, random);
         }
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(immutableSetEntry, MovementProperty.resolveMovementProperty(count & 7))
         .resolveIBlockState(auxiliaryImmutableSetEntry, (count & 8) >> 3);
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      return true;
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld5(world, blockPos, blockState, random);
   }

   @Override
   public boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      return (double)world.random.nextFloat() < 0.45;
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (MovementProperty movementProperty : MovementProperty.values()) {
         list.add(new ItemStack(item, 1, movementProperty.getCount()));
      }
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public void applyWorld5(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if ((Integer)blockState.getComparable(auxiliaryImmutableSetEntry) == 0) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry), 4);
      } else {
         this.applyWorld8(world, blockPos, blockState, random);
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry, auxiliaryImmutableSetEntry});
   }

   private boolean matchesWorld(World world, BlockPos blockPos, int count, int count2, MovementProperty movementProperty) {
      return this.matchesWorld(world, blockPos.add(count, 0, count2), movementProperty)
         && this.matchesWorld(world, blockPos.add(count + 1, 0, count2), movementProperty)
         && this.matchesWorld(world, blockPos.add(count, 0, count2 + 1), movementProperty)
         && this.matchesWorld(world, blockPos.add(count + 1, 0, count2 + 1), movementProperty);
   }

   public void applyWorld8(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      Object entry = random.nextInt(10) == 0 ? new CustomRandomEntry(true) : new SecondaryIBlockStateEntry(true);
      int count = 0;
      int count2 = 0;
      boolean flag = false;
      switch (RotationPicker.countArray[((MovementProperty)blockState.getComparable(immutableSetEntry)).ordinal()]) {
         case 1:
            outerLoop:
            for (count = 0; count >= -1; count--) {
               for (count2 = 0; count2 >= -1; count2--) {
                  if (this.matchesWorld(world, blockPos, count, count2, MovementProperty.SPRUCE)) {
                     entry = new AdvancedIBlockStateEntry(false, random.nextBoolean());
                     flag = true;
                     break outerLoop;
                  }
               }
            }

            if (!flag) {
               count2 = 0;
               count = 0;
               entry = new ExtendedIBlockStateEntry(true);
            }
            break;
         case 2:
            entry = new ClientManagerCoordinator(true, false);
            break;
         case 3:
            IBlockState blockState2 = Blocks.block140.getDefaultState().resolveIBlockState(BlockLog.immutableSetEntry3, MovementProperty.JUNGLE);
            IBlockState blockState3 = Blocks.decayableManager2
               .getDefaultState()
               .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.JUNGLE)
               .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);

            innerLoop:
            for (count = 0; count >= -1; count--) {
               for (count2 = 0; count2 >= -1; count2--) {
                  if (this.matchesWorld(world, blockPos, count, count2, MovementProperty.JUNGLE)) {
                     entry = new IBlockStateEntryBridge(true, 10, 20, blockState2, blockState3);
                     flag = true;
                     break innerLoop;
                  }
               }
            }

            if (!flag) {
               count2 = 0;
               count = 0;
               entry = new SecondaryIBlockStateEntry(true, 4 + random.nextInt(7), blockState2, blockState3, false);
            }
            break;
         case 4:
            entry = new SecondaryClientManagerHandler(true);
            break;
         case 5:
            scanLoop:
            for (count = 0; count >= -1; count--) {
               for (count2 = 0; count2 >= -1; count2--) {
                  if (this.matchesWorld(world, blockPos, count, count2, MovementProperty.DARK_OAK)) {
                     entry = new InternalClientManagerController(true);
                     flag = true;
                     break scanLoop;
                  }
               }
            }

            if (!flag) {
               return;
            }
         case 6:
      }

      IBlockState defaultState = Blocks.air.getDefaultState();
      if (flag) {
         world.matchesBlockPos(blockPos.add(count, 0, count2), defaultState, 4);
         world.matchesBlockPos(blockPos.add(count + 1, 0, count2), defaultState, 4);
         world.matchesBlockPos(blockPos.add(count, 0, count2 + 1), defaultState, 4);
         world.matchesBlockPos(blockPos.add(count + 1, 0, count2 + 1), defaultState, 4);
      } else {
         world.matchesBlockPos(blockPos, defaultState, 4);
      }

      if (!((CurveParser)entry).matchesWorld(world, random, blockPos.add(count, 0, count2))) {
         if (flag) {
            world.matchesBlockPos(blockPos.add(count, 0, count2), blockState, 4);
            world.matchesBlockPos(blockPos.add(count + 1, 0, count2), blockState, 4);
            world.matchesBlockPos(blockPos.add(count, 0, count2 + 1), blockState, 4);
            world.matchesBlockPos(blockPos.add(count + 1, 0, count2 + 1), blockState, 4);
         } else {
            world.matchesBlockPos(blockPos, blockState, 4);
         }
      }
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0 | ((MovementProperty)blockState.getComparable(immutableSetEntry)).getCount() | (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) << 3;
   }

   @Override
   public String getText() {
      return I18n.getString2(this.getTextAsText() + "." + MovementProperty.OAK.getLabelText() + ".name");
   }

   public BlockSapling() {
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(immutableSetEntry, MovementProperty.OAK).resolveIBlockState(auxiliaryImmutableSetEntry, 0)
      );
      float factor = 0.4F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, factor * 2.0F, 0.5F + factor);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
