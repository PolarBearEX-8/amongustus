package client.util;

import client.setting.LocalOptionType;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.AuxiliaryItemEntryBridge;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEntryProvider;
import net.minecraft.item.Items;
import net.minecraft.item.SecondaryItemEntryAdapter;
import net.minecraft.util.EnumFacing;

public class GlobalListEntry {
   public static final List list = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.ironIngot, 0, 1, 5, 10),
         new ClientManagerAdapter2(Items.goldIngot, 0, 1, 3, 5),
         new ClientManagerAdapter2(Items.redstone, 0, 4, 9, 5),
         new ClientManagerAdapter2(Items.dye, LocalOptionType.BLUE.getCount2(), 4, 9, 5),
         new ClientManagerAdapter2(Items.diamond, 0, 1, 2, 3),
         new ClientManagerAdapter2(Items.coal, 0, 3, 8, 10),
         new ClientManagerAdapter2(Items.bread, 0, 1, 3, 15),
         new ClientManagerAdapter2(Items.ironPickaxe, 0, 1, 1, 1),
         new ClientManagerAdapter2(Item.processAction2(Blocks.block13), 0, 4, 8, 1),
         new ClientManagerAdapter2(Items.melonSeeds, 0, 2, 4, 10),
         new ClientManagerAdapter2(Items.pumpkinSeeds, 0, 2, 4, 10),
         new ClientManagerAdapter2(Items.saddle, 0, 1, 1, 3),
         new ClientManagerAdapter2(Items.ironHorseArmor, 0, 1, 1, 1)
      }
   );

   public static List getList() {
      return list;
   }

   public static GoldenIngot resolveGoldenIngotByGoldenIngot(GoldenIngot goldenIngot, List list2, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      return resolveGoldenIngot(goldenIngot, list2, random, count, count2, count3, enumFacing, count4);
   }

   private static GoldenIngot resolveGoldenIngot(List list2, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      int count5 = random.nextInt(100);
      if (count5 >= 80) {
         DistanceAggregator distanceAggregator = EnumFacingEntry.resolveDistanceAggregator(list2, random, count, count2, count3, enumFacing);
         if (distanceAggregator != null) {
            return new EnumFacingEntry(count4, random, distanceAggregator, enumFacing);
         }
      } else if (count5 >= 70) {
         DistanceAggregator distanceAggregator2 = SecondaryItemEntryAdapter.resolveDistanceAggregator(list2, random, count, count2, count3, enumFacing);
         if (distanceAggregator2 != null) {
            return new SecondaryItemEntryAdapter(count4, random, distanceAggregator2, enumFacing);
         }
      } else {
         DistanceAggregator distanceAggregator3 = AuxiliaryItemEntryBridge.resolveDistanceAggregator(list2, random, count, count2, count3, enumFacing);
         if (distanceAggregator3 != null) {
            return new AuxiliaryItemEntryBridge(count4, random, distanceAggregator3, enumFacing);
         }
      }

      return null;
   }

   public static void updateState() {
      EnhancedLoggerEntry.applyClassByClass(AuxiliaryItemEntryBridge.class, "MSCorridor");
      EnhancedLoggerEntry.applyClassByClass(EnumFacingEntry.class, "MSCrossing");
      EnhancedLoggerEntry.applyClassByClass(ItemEntryProvider.class, "MSRoom");
      EnhancedLoggerEntry.applyClassByClass(SecondaryItemEntryAdapter.class, "MSStairs");
   }

   private static GoldenIngot resolveGoldenIngot(GoldenIngot goldenIngot, List list2, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      if (count4 > 8) {
         return null;
      } else if (Math.abs(count - goldenIngot.getDistanceAggregator().count3) <= 80 && Math.abs(count3 - goldenIngot.getDistanceAggregator().count4) <= 80) {
         GoldenIngot goldenIngot2 = resolveGoldenIngot(list2, random, count, count2, count3, enumFacing, count4 + 1);
         if (goldenIngot2 != null) {
            list2.add(goldenIngot2);
            goldenIngot2.applyGoldenIngot(goldenIngot, list2, random);
         }

         return goldenIngot2;
      } else {
         return null;
      }
   }
}
