package client.util;

import client.setting.IndirectOptionType;
import client.setting.InternalOptionType;
import client.setting.LocalOptionType;
import client.setting.MovementFactorProperty;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BooleanArrayEntry extends DistanceDeserializer {
   public boolean[] enabledArray = new boolean[4];
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

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 0, -4, 0, this.count2 - 1, 0, this.count3 - 1, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);

      for (int index = 1; index <= 9; index++) {
         this.applyWorld(
            world,
            distanceAggregator,
            index,
            index,
            index,
            this.count2 - 1 - index,
            index,
            this.count3 - 1 - index,
            Blocks.block136.getDefaultState(),
            Blocks.block136.getDefaultState(),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            index + 1,
            index,
            index + 1,
            this.count2 - 2 - index,
            index,
            this.count3 - 2 - index,
            Blocks.air.getDefaultState(),
            Blocks.air.getDefaultState(),
            false
         );
      }

      for (int index2 = 0; index2 < this.count2; index2++) {
         for (int index3 = 0; index3 < this.count3; index3++) {
            this.applyWorldByWorld(world, Blocks.block136.getDefaultState(), index2, -5, index3, distanceAggregator);
         }
      }

      int count = this.computeCount(Blocks.block122, 3);
      int count2 = this.computeCount(Blocks.block122, 2);
      int count3 = this.computeCount(Blocks.block122, 0);
      int count4 = this.computeCount(Blocks.block122, 1);
      int count5 = ~LocalOptionType.ORANGE.getCount2() & 15;
      int count6 = ~LocalOptionType.BLUE.getCount2() & 15;
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 9, 4, Blocks.block136.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 10, 1, 3, 10, 3, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, Blocks.block122.processAction(count), 2, 10, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count2), 2, 10, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count3), 0, 10, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count4), 4, 10, 2, distanceAggregator);
      this.applyWorld(world, distanceAggregator, this.count2 - 5, 0, 0, this.count2 - 1, 9, 4, Blocks.block136.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, this.count2 - 4, 10, 1, this.count2 - 2, 10, 3, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, Blocks.block122.processAction(count), this.count2 - 3, 10, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count2), this.count2 - 3, 10, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count3), this.count2 - 5, 10, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count4), this.count2 - 1, 10, 2, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 8, 0, 0, 12, 4, 4, Blocks.block136.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 1, 0, 11, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 9, 1, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 9, 2, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 9, 3, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 10, 3, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 11, 3, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 11, 2, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 11, 1, 1, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 4, 1, 1, 8, 3, 3, Blocks.block136.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 1, 2, 8, 2, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 12, 1, 1, 16, 3, 3, Blocks.block136.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 12, 1, 2, 16, 2, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 4, 5, this.count2 - 6, 4, this.count3 - 6, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 4, 9, 11, 4, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(
         world,
         distanceAggregator,
         8,
         1,
         8,
         8,
         3,
         8,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(
         world,
         distanceAggregator,
         12,
         1,
         8,
         12,
         3,
         8,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(
         world,
         distanceAggregator,
         8,
         1,
         12,
         8,
         3,
         12,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(
         world,
         distanceAggregator,
         12,
         1,
         12,
         12,
         3,
         12,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(world, distanceAggregator, 1, 1, 5, 4, 4, 11, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, this.count2 - 5, 1, 5, this.count2 - 2, 4, 11, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 7, 9, 6, 7, 11, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, this.count2 - 7, 7, 9, this.count2 - 7, 7, 11, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(
         world,
         distanceAggregator,
         5,
         5,
         9,
         5,
         7,
         11,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(
         world,
         distanceAggregator,
         this.count2 - 6,
         5,
         9,
         this.count2 - 6,
         7,
         11,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(world, Blocks.air.getDefaultState(), 5, 5, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 5, 6, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 6, 6, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), this.count2 - 6, 5, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), this.count2 - 6, 6, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), this.count2 - 7, 6, 10, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 2, 4, 4, 2, 6, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, this.count2 - 3, 4, 4, this.count2 - 3, 6, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, Blocks.block122.processAction(count), 2, 4, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count), 2, 3, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count), this.count2 - 3, 4, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count), this.count2 - 3, 3, 4, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 1, 1, 3, 2, 2, 3, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, this.count2 - 3, 1, 3, this.count2 - 2, 2, 3, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, Blocks.block122.getDefaultState(), 1, 1, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block122.getDefaultState(), this.count2 - 2, 1, 2, distanceAggregator);
      this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.SAND.getCount()), 1, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.SAND.getCount()), this.count2 - 2, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count4), 2, 1, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block122.processAction(count3), this.count2 - 3, 1, 2, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 4, 3, 5, 4, 3, 18, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, this.count2 - 5, 3, 5, this.count2 - 5, 3, 17, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 1, 5, 4, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, this.count2 - 6, 1, 5, this.count2 - 5, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

      for (int byteValue = 5; byteValue <= 17; byteValue += 2) {
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 4, 1, byteValue, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), 4, 2, byteValue, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), this.count2 - 5, 1, byteValue, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), this.count2 - 5, 2, byteValue, distanceAggregator);
      }

      this.applyWorld(world, Blocks.block107.processAction(count5), 10, 0, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 10, 0, 8, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 9, 0, 9, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 11, 0, 9, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 8, 0, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 12, 0, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 7, 0, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 13, 0, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 9, 0, 11, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 11, 0, 11, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 10, 0, 12, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 10, 0, 13, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count6), 10, 0, 10, distanceAggregator);

      for (int index4 = 0; index4 <= this.count2 - 1; index4 += this.count2 - 1) {
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 2, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 2, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 2, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 3, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 3, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 3, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 4, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), index4, 4, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 4, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 5, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 5, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 5, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 6, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), index4, 6, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 6, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 7, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 7, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index4, 7, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 8, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 8, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index4, 8, 3, distanceAggregator);
      }

      for (int index5 = 2; index5 <= this.count2 - 3; index5 += this.count2 - 3 - 2) {
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5 - 1, 2, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5, 2, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5 + 1, 2, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5 - 1, 3, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5, 3, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5 + 1, 3, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5 - 1, 4, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), index5, 4, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5 + 1, 4, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5 - 1, 5, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5, 5, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5 + 1, 5, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5 - 1, 6, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), index5, 6, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5 + 1, 6, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5 - 1, 7, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5, 7, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block107.processAction(count5), index5 + 1, 7, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5 - 1, 8, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5, 8, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), index5 + 1, 8, 0, distanceAggregator);
      }

      this.applyWorld(
         world,
         distanceAggregator,
         8,
         4,
         0,
         12,
         6,
         0,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(world, Blocks.air.getDefaultState(), 8, 6, 0, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 12, 6, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 9, 5, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), 10, 5, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block107.processAction(count5), 11, 5, 0, distanceAggregator);
      this.applyWorld(
         world,
         distanceAggregator,
         8,
         -14,
         8,
         12,
         -11,
         12,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(
         world,
         distanceAggregator,
         8,
         -10,
         8,
         12,
         -10,
         12,
         Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()),
         Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()),
         false
      );
      this.applyWorld(
         world,
         distanceAggregator,
         8,
         -9,
         8,
         12,
         -9,
         12,
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()),
         false
      );
      this.applyWorld(world, distanceAggregator, 8, -8, 8, 12, -1, 12, Blocks.block136.getDefaultState(), Blocks.block136.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, -11, 9, 11, -1, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, Blocks.block23.getDefaultState(), 10, -11, 10, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 9, -13, 9, 11, -13, 11, Blocks.block135.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, Blocks.air.getDefaultState(), 8, -11, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 8, -10, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), 7, -10, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 7, -11, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 12, -11, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 12, -10, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), 13, -10, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 13, -11, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 10, -11, 8, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 10, -10, 8, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), 10, -10, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 10, -11, 7, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 10, -11, 12, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 10, -10, 12, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.CHISELED.getCount()), 10, -10, 13, distanceAggregator);
      this.applyWorld(world, Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount()), 10, -11, 13, distanceAggregator);

      for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable<?>)MovementFactorProperty.HORIZONTAL) {
         if (!this.enabledArray[enumFacing.getIndexOffset()]) {
            int count7 = enumFacing.getInt2() * 2;
            int count8 = enumFacing.getInt() * 2;
            this.enabledArray[enumFacing.getIndexOffset()] = this.matchesWorld(
               world,
               distanceAggregator,
               random,
               10 + count7,
               -11,
               10 + count8,
               ClientManagerAdapter2.resolveList(list, new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random)}),
               2 + random.nextInt(5)
            );
         }
      }

      return true;
   }

   public BooleanArrayEntry() {
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabledArray[0] = tagCompound.matchesText("hasPlacedChest0");
      this.enabledArray[1] = tagCompound.matchesText("hasPlacedChest1");
      this.enabledArray[2] = tagCompound.matchesText("hasPlacedChest2");
      this.enabledArray[3] = tagCompound.matchesText("hasPlacedChest3");
   }

   public BooleanArrayEntry(Random random, int count, int count2) {
      super(random, count, 64, count2, 21, 15, 21);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("hasPlacedChest0", this.enabledArray[0]);
      tagCompound.applyText("hasPlacedChest1", this.enabledArray[1]);
      tagCompound.applyText("hasPlacedChest2", this.enabledArray[2]);
      tagCompound.applyText("hasPlacedChest3", this.enabledArray[3]);
   }
}
