package net.minecraft.item;

import client.setting.IndirectOptionType;
import client.util.ClassEntry3ArrayEntry;
import client.util.ClassEntry3Entry;
import client.util.ClientManagerAdapter2;
import client.util.DistanceAggregator;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class IronAxe extends ExtendedItemEntryAdapter {
   public static final List list = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.enderPearl, 0, 1, 1, 10),
         new ClientManagerAdapter2(Items.diamond, 0, 1, 3, 3),
         new ClientManagerAdapter2(Items.ironIngot, 0, 1, 5, 10),
         new ClientManagerAdapter2(Items.goldIngot, 0, 1, 3, 5),
         new ClientManagerAdapter2(Items.redstone, 0, 4, 9, 5),
         new ClientManagerAdapter2(Items.bread, 0, 1, 3, 15),
         new ClientManagerAdapter2(Items.apple, 0, 1, 3, 15),
         new ClientManagerAdapter2(Items.ironPickaxe, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironSword, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironChestplate, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironHelmet, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironLeggings, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironBoots, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.goldenApple, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.saddle, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.ironHorseArmor, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.goldenHorseArmor, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.diamondHorseArmor, 0, 1, 1, 1)
      }
   );
   public boolean enabled;

   public IronAxe(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
   }

   public IronAxe() {
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Chest", this.enabled);
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list2, Random random) {
      this.resolveGoldenIngotByClassEntry3Entry((ClassEntry3Entry)goldenIngot, list2, random, 1, 1);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 4, 6, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 1, 1, 0);
         this.applyWorld(world, random, distanceAggregator, IRONDOOREnum.OPENING, 1, 1, 6);
         this.applyWorld(world, distanceAggregator, 3, 1, 2, 3, 1, 4, Blocks.block42.getDefaultState(), Blocks.block42.getDefaultState(), false);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.SMOOTHBRICK.getCount()), 3, 1, 1, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.SMOOTHBRICK.getCount()), 3, 1, 5, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.SMOOTHBRICK.getCount()), 3, 2, 2, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.SMOOTHBRICK.getCount()), 3, 2, 4, distanceAggregator);

         for (int index = 2; index <= 4; index++) {
            this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.SMOOTHBRICK.getCount()), 2, 1, index, distanceAggregator);
         }

         if (!this.enabled && distanceAggregator.matchesVec3i(new BlockPos(this.computeCountByIndex(3, 3), this.computeCount(2), this.computeCount(3, 3)))) {
            this.enabled = true;
            this.matchesWorld(
               world,
               distanceAggregator,
               random,
               3,
               2,
               3,
               ClientManagerAdapter2.resolveList(list, new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random)}),
               2 + random.nextInt(2)
            );
         }

         return true;
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("Chest");
   }

   public static IronAxe resolveIronAxe(List list2, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -1, -1, 0, 5, 5, 7, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list2, distanceAggregator) == null ? new IronAxe(count4, random, distanceAggregator, enumFacing) : null;
   }
}
