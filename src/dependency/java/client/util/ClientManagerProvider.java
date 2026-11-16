package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockStonebrick;
import net.minecraft.block.BlockTripwire;
import net.minecraft.block.BlockTripwireHook;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ClientManagerProvider extends DistanceDeserializer {
   public boolean enabled;
   public boolean enabled2;
   public boolean enabled3;
   public boolean enabled4;
   public static final List list = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.diamond, 0, 1, 3, 3),
         new ClientManagerAdapter2(Items.ironIngot, 0, 1, 5, 10),
         new ClientManagerAdapter2(Items.goldIngot, 0, 2, 7, 15),
         new ClientManagerAdapter2(Items.emerald, 0, 1, 3, 2),
         new ClientManagerAdapter2(Items.bone, 0, 4, 6, 20),
         new ClientManagerAdapter2(Items.rottenFlesh, 0, 3, 7, 16),
         new ClientManagerAdapter2(Items.saddle, 0, 1, 1, 3),
         new ClientManagerAdapter2(Items.ironHorseArmor, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.goldenHorseArmor, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.diamondHorseArmor, 0, 1, 1, 1)
      }
   );
   public static final List list2 = Lists.newArrayList(new ClientManagerAdapter2[]{new ClientManagerAdapter2(Items.arrow, 0, 2, 7, 30)});
   public static RandomCustomIBlockStateEntry randomCustomIBlockStateEntry = new RandomCustomIBlockStateEntry(null);

   public ClientManagerProvider(Random random, int count, int count2) {
      super(random, count, 64, count2, 12, 10, 15);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("placedMainChest");
      this.enabled2 = tagCompound.matchesText("placedHiddenChest");
      this.enabled3 = tagCompound.matchesText("placedTrap1");
      this.enabled4 = tagCompound.matchesText("placedTrap2");
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (!this.matchesWorld(world, distanceAggregator, 0)) {
         return false;
      } else {
         int count = this.computeCount(Blocks.block125, 3);
         int count2 = this.computeCount(Blocks.block125, 2);
         int count3 = this.computeCount(Blocks.block125, 0);
         int count4 = this.computeCount(Blocks.block125, 1);
         this.applyWorld(world, distanceAggregator, 0, -4, 0, this.count2 - 1, 0, this.count3 - 1, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 2, 1, 2, 9, 2, 2, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 2, 1, 12, 9, 2, 12, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 2, 1, 3, 2, 2, 11, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 9, 1, 3, 9, 2, 11, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 1, 3, 1, 10, 6, 1, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 1, 3, 13, 10, 6, 13, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 1, 3, 2, 1, 6, 12, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 10, 3, 2, 10, 6, 12, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 2, 3, 2, 9, 3, 12, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 2, 6, 2, 9, 6, 12, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 3, 7, 3, 8, 7, 11, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 4, 8, 4, 7, 8, 10, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 3, 1, 3, 8, 2, 11);
         this.applyWorld(world, distanceAggregator, 4, 3, 6, 7, 3, 9);
         this.applyWorld(world, distanceAggregator, 2, 4, 2, 9, 5, 12);
         this.applyWorld(world, distanceAggregator, 4, 6, 5, 7, 6, 9);
         this.applyWorld(world, distanceAggregator, 5, 7, 6, 6, 7, 8);
         this.applyWorld(world, distanceAggregator, 5, 1, 2, 6, 2, 2);
         this.applyWorld(world, distanceAggregator, 5, 2, 12, 6, 2, 12);
         this.applyWorld(world, distanceAggregator, 5, 5, 1, 6, 5, 1);
         this.applyWorld(world, distanceAggregator, 5, 5, 13, 6, 5, 13);
         this.applyWorld(world, Blocks.air.getDefaultState(), 1, 5, 5, distanceAggregator);
         this.applyWorld(world, Blocks.air.getDefaultState(), 10, 5, 5, distanceAggregator);
         this.applyWorld(world, Blocks.air.getDefaultState(), 1, 5, 9, distanceAggregator);
         this.applyWorld(world, Blocks.air.getDefaultState(), 10, 5, 9, distanceAggregator);

         for (int byteValue = 0; byteValue <= 14; byteValue += 14) {
            this.applyWorld(world, distanceAggregator, 2, 4, byteValue, 2, 5, byteValue, false, random, randomCustomIBlockStateEntry);
            this.applyWorld(world, distanceAggregator, 4, 4, byteValue, 4, 5, byteValue, false, random, randomCustomIBlockStateEntry);
            this.applyWorld(world, distanceAggregator, 7, 4, byteValue, 7, 5, byteValue, false, random, randomCustomIBlockStateEntry);
            this.applyWorld(world, distanceAggregator, 9, 4, byteValue, 9, 5, byteValue, false, random, randomCustomIBlockStateEntry);
         }

         this.applyWorld(world, distanceAggregator, 5, 6, 0, 6, 6, 0, false, random, randomCustomIBlockStateEntry);

         for (int byteValue2 = 0; byteValue2 <= 11; byteValue2 += 11) {
            for (int byteValue3 = 2; byteValue3 <= 12; byteValue3 += 2) {
               this.applyWorld(world, distanceAggregator, byteValue2, 4, byteValue3, byteValue2, 5, byteValue3, false, random, randomCustomIBlockStateEntry);
            }

            this.applyWorld(world, distanceAggregator, byteValue2, 6, 5, byteValue2, 6, 5, false, random, randomCustomIBlockStateEntry);
            this.applyWorld(world, distanceAggregator, byteValue2, 6, 9, byteValue2, 6, 9, false, random, randomCustomIBlockStateEntry);
         }

         this.applyWorld(world, distanceAggregator, 2, 7, 2, 2, 9, 2, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 9, 7, 2, 9, 9, 2, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 2, 7, 12, 2, 9, 12, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 9, 7, 12, 9, 9, 12, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 4, 9, 4, 4, 9, 4, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 7, 9, 4, 7, 9, 4, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 4, 9, 10, 4, 9, 10, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 7, 9, 10, 7, 9, 10, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 5, 9, 7, 6, 9, 7, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, Blocks.block125.processAction(count), 5, 9, 6, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 6, 9, 6, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count2), 5, 9, 8, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count2), 6, 9, 8, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 4, 0, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 5, 0, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 6, 0, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 7, 0, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 4, 1, 8, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 4, 2, 9, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 4, 3, 10, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 7, 1, 8, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 7, 2, 9, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count), 7, 3, 10, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 4, 1, 9, 4, 1, 9, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 7, 1, 9, 7, 1, 9, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 4, 1, 10, 7, 2, 10, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 5, 4, 5, 6, 4, 5, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, Blocks.block125.processAction(count3), 4, 4, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block125.processAction(count4), 7, 4, 5, distanceAggregator);

         for (int index = 0; index < 4; index++) {
            this.applyWorld(world, Blocks.block125.processAction(count2), 5, 0 - index, 6 + index, distanceAggregator);
            this.applyWorld(world, Blocks.block125.processAction(count2), 6, 0 - index, 6 + index, distanceAggregator);
            this.applyWorld(world, distanceAggregator, 5, 0 - index, 7 + index, 6, 0 - index, 9 + index);
         }

         this.applyWorld(world, distanceAggregator, 1, -3, 12, 10, -1, 13);
         this.applyWorld(world, distanceAggregator, 1, -3, 1, 3, -1, 13);
         this.applyWorld(world, distanceAggregator, 1, -3, 1, 9, -1, 5);

         for (int byteValue4 = 1; byteValue4 <= 13; byteValue4 += 2) {
            this.applyWorld(world, distanceAggregator, 1, -3, byteValue4, 1, -2, byteValue4, false, random, randomCustomIBlockStateEntry);
         }

         for (int byteValue5 = 2; byteValue5 <= 12; byteValue5 += 2) {
            this.applyWorld(world, distanceAggregator, 1, -1, byteValue5, 3, -1, byteValue5, false, random, randomCustomIBlockStateEntry);
         }

         this.applyWorld(world, distanceAggregator, 2, -2, 1, 5, -2, 1, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 7, -2, 1, 9, -2, 1, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 6, -3, 1, 6, -3, 1, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 6, -1, 1, 6, -1, 1, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(
            world,
            Blocks.blockTripwireHook
               .processAction(this.computeCount(Blocks.blockTripwireHook, EnumFacing.EAST.getIndexOffset()))
               .resolveIBlockState(BlockTripwireHook.internalImmutableSetEntry2, Boolean.TRUE),
            1,
            -3,
            8,
            distanceAggregator
         );
         this.applyWorld(
            world,
            Blocks.blockTripwireHook
               .processAction(this.computeCount(Blocks.blockTripwireHook, EnumFacing.WEST.getIndexOffset()))
               .resolveIBlockState(BlockTripwireHook.internalImmutableSetEntry2, Boolean.TRUE),
            4,
            -3,
            8,
            distanceAggregator
         );
         this.applyWorld(world, Blocks.block6.getDefaultState().resolveIBlockState(BlockTripwire.internalImmutableSetEntry5, Boolean.TRUE), 2, -3, 8, distanceAggregator);
         this.applyWorld(world, Blocks.block6.getDefaultState().resolveIBlockState(BlockTripwire.internalImmutableSetEntry5, Boolean.TRUE), 3, -3, 8, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 5, -3, 7, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 5, -3, 6, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 5, -3, 5, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 5, -3, 4, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 5, -3, 3, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 5, -3, 2, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 5, -3, 1, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 4, -3, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 3, -3, 1, distanceAggregator);
         if (!this.enabled3) {
            this.enabled3 = this.matchesWorld(world, distanceAggregator, random, 3, -2, 1, EnumFacing.NORTH.getIndexOffset1(), list2, 2);
         }

         this.applyWorld(world, Blocks.block144.processAction(15), 3, -2, 2, distanceAggregator);
         this.applyWorld(
            world,
            Blocks.blockTripwireHook
               .processAction(this.computeCount(Blocks.blockTripwireHook, EnumFacing.NORTH.getIndexOffset()))
               .resolveIBlockState(BlockTripwireHook.internalImmutableSetEntry2, Boolean.TRUE),
            7,
            -3,
            1,
            distanceAggregator
         );
         this.applyWorld(
            world,
            Blocks.blockTripwireHook
               .processAction(this.computeCount(Blocks.blockTripwireHook, EnumFacing.SOUTH.getIndexOffset()))
               .resolveIBlockState(BlockTripwireHook.internalImmutableSetEntry2, Boolean.TRUE),
            7,
            -3,
            5,
            distanceAggregator
         );
         this.applyWorld(world, Blocks.block6.getDefaultState().resolveIBlockState(BlockTripwire.internalImmutableSetEntry5, Boolean.TRUE), 7, -3, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block6.getDefaultState().resolveIBlockState(BlockTripwire.internalImmutableSetEntry5, Boolean.TRUE), 7, -3, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block6.getDefaultState().resolveIBlockState(BlockTripwire.internalImmutableSetEntry5, Boolean.TRUE), 7, -3, 4, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 8, -3, 6, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 9, -3, 6, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 9, -3, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 9, -3, 4, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 9, -2, 4, distanceAggregator);
         if (!this.enabled4) {
            this.enabled4 = this.matchesWorld(world, distanceAggregator, random, 9, -2, 3, EnumFacing.WEST.getIndexOffset1(), list2, 2);
         }

         this.applyWorld(world, Blocks.block144.processAction(15), 8, -1, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block144.processAction(15), 8, -2, 3, distanceAggregator);
         if (!this.enabled) {
            this.enabled = this.matchesWorld(
               world,
               distanceAggregator,
               random,
               8,
               -3,
               3,
               ClientManagerAdapter2.resolveList(list, new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random)}),
               2 + random.nextInt(5)
            );
         }

         this.applyWorld(world, Blocks.block131.getDefaultState(), 9, -3, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 8, -3, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 4, -3, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 5, -2, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 5, -1, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 6, -3, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 7, -2, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 7, -1, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 8, -3, 5, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 9, -1, 1, 9, -1, 5, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 8, -3, 8, 10, -1, 10);
         this.applyWorld(world, Blocks.block42.processAction(BlockStonebrick.count4), 8, -2, 11, distanceAggregator);
         this.applyWorld(world, Blocks.block42.processAction(BlockStonebrick.count4), 9, -2, 11, distanceAggregator);
         this.applyWorld(world, Blocks.block42.processAction(BlockStonebrick.count4), 10, -2, 11, distanceAggregator);
         this.applyWorld(
            world,
            Blocks.block48
               .processAction(BlockLever.computeCount(EnumFacing.getEnumFacing6(this.computeCount(Blocks.block48, EnumFacing.NORTH.getIndexOffset1())))),
            8,
            -2,
            12,
            distanceAggregator
         );
         this.applyWorld(
            world,
            Blocks.block48
               .processAction(BlockLever.computeCount(EnumFacing.getEnumFacing6(this.computeCount(Blocks.block48, EnumFacing.NORTH.getIndexOffset1())))),
            9,
            -2,
            12,
            distanceAggregator
         );
         this.applyWorld(
            world,
            Blocks.block48
               .processAction(BlockLever.computeCount(EnumFacing.getEnumFacing6(this.computeCount(Blocks.block48, EnumFacing.NORTH.getIndexOffset1())))),
            10,
            -2,
            12,
            distanceAggregator
         );
         this.applyWorld(world, distanceAggregator, 8, -3, 8, 8, -3, 10, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, distanceAggregator, 10, -3, 8, 10, -3, 10, false, random, randomCustomIBlockStateEntry);
         this.applyWorld(world, Blocks.block131.getDefaultState(), 10, -2, 9, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 8, -2, 9, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 8, -2, 10, distanceAggregator);
         this.applyWorld(world, Blocks.blockRedstoneWire.getDefaultState(), 10, -1, 9, distanceAggregator);
         this.applyWorld(world, Blocks.blockPiston.processAction(EnumFacing.UP.getIndexOffset1()), 9, -2, 8, distanceAggregator);
         this.applyWorld(world, Blocks.blockPiston.processAction(this.computeCount(Blocks.blockPiston, EnumFacing.WEST.getIndexOffset1())), 10, -2, 8, distanceAggregator);
         this.applyWorld(world, Blocks.blockPiston.processAction(this.computeCount(Blocks.blockPiston, EnumFacing.WEST.getIndexOffset1())), 10, -1, 8, distanceAggregator);
         this.applyWorld(
            world,
            Blocks.blockPoweredRepeater.processAction(this.computeCount(Blocks.blockPoweredRepeater, EnumFacing.NORTH.getIndexOffset())),
            10,
            -2,
            10,
            distanceAggregator
         );
         if (!this.enabled2) {
            this.enabled2 = this.matchesWorld(
               world,
               distanceAggregator,
               random,
               9,
               -3,
               10,
               ClientManagerAdapter2.resolveList(list, new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random)}),
               2 + random.nextInt(5)
            );
         }

         return true;
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("placedMainChest", this.enabled);
      tagCompound.applyText("placedHiddenChest", this.enabled2);
      tagCompound.applyText("placedTrap1", this.enabled3);
      tagCompound.applyText("placedTrap2", this.enabled4);
   }

   public ClientManagerProvider() {
   }
}
