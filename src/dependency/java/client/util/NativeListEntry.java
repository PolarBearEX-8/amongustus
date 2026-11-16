package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.InternalItemEntryAdapter;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class NativeListEntry extends InternalItemEntryAdapter {
   public static final List list = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.diamond, 0, 1, 3, 3),
         new ClientManagerAdapter2(Items.ironIngot, 0, 1, 5, 10),
         new ClientManagerAdapter2(Items.goldIngot, 0, 1, 3, 5),
         new ClientManagerAdapter2(Items.bread, 0, 1, 3, 15),
         new ClientManagerAdapter2(Items.apple, 0, 1, 3, 15),
         new ClientManagerAdapter2(Items.ironPickaxe, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironSword, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironChestplate, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironHelmet, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironLeggings, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.ironBoots, 0, 1, 1, 5),
         new ClientManagerAdapter2(Item.processAction2(Blocks.block113), 0, 3, 7, 5),
         new ClientManagerAdapter2(Item.processAction2(Blocks.block83), 0, 3, 7, 5),
         new ClientManagerAdapter2(Items.saddle, 0, 1, 1, 3),
         new ClientManagerAdapter2(Items.ironHorseArmor, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.goldenHorseArmor, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.diamondHorseArmor, 0, 1, 1, 1)
      }
   );
   public boolean enabled2;

   public NativeListEntry() {
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Chest", this.enabled2);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled2 = tagCompound.matchesText("Chest");
   }

   public static NativeListEntry resolveNativeListEntry(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list2, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 10, 6, 7, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list2, distanceAggregator) == null ? new NativeListEntry(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public int computeCount2(int count, int count2) {
      return 3;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 6 - 1, 0);
      }

      this.applyWorld(world, distanceAggregator, 0, 1, 0, 9, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 9, 0, 6, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 4, 0, 9, 4, 6, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 0, 9, 5, 6, Blocks.extraBlockAdapter6.getDefaultState(), Blocks.extraBlockAdapter6.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 5, 1, 8, 5, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 0, 2, 3, 0, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 1, 0, 0, 4, 0, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 1, 0, 3, 4, 0, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 1, 6, 0, 4, 6, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 3, 3, 1, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 3, 1, 2, 3, 3, 2, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 1, 3, 5, 3, 3, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 1, 1, 0, 3, 5, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 6, 5, 3, 6, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 1, 0, 5, 3, 0, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 1, 0, 9, 3, 0, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 1, 4, 9, 4, 6, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, Blocks.blockFlowingLava2.getDefaultState(), 7, 1, 5, distanceAggregator);
      this.applyWorld(world, Blocks.blockFlowingLava2.getDefaultState(), 8, 1, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block127.getDefaultState(), 9, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block127.getDefaultState(), 9, 2, 4, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 7, 2, 4, 8, 2, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 6, 1, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block39.getDefaultState(), 6, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block39.getDefaultState(), 6, 3, 3, distanceAggregator);
      this.applyWorld(world, Blocks.extraBlockAdapter5.getDefaultState(), 8, 1, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 2, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 2, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 2, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block94.getDefaultState(), 2, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 1, 1, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(this.computeCount(Blocks.block3, 3)), 2, 1, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(this.computeCount(Blocks.block3, 1)), 1, 1, 4, distanceAggregator);
      if (!this.enabled2 && distanceAggregator.matchesVec3i(new BlockPos(this.computeCountByIndex(5, 5), this.computeCount(1), this.computeCount(5, 5)))) {
         this.enabled2 = true;
         this.matchesWorld(world, distanceAggregator, random, 5, 1, 5, list, 3 + random.nextInt(6));
      }

      for (int index = 6; index <= 8; index++) {
         if (this.resolveIBlockState(world, index, 0, -1, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry
            && this.resolveIBlockState(world, index, -1, -1, distanceAggregator).getBlock().getMaterial() != Material.coreItemEntry) {
            this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), index, 0, -1, distanceAggregator);
         }
      }

      for (int index2 = 0; index2 < 7; index2++) {
         for (int index3 = 0; index3 < 10; index3++) {
            this.applyWorld(world, index3, 6, index2, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block65.getDefaultState(), index3, -1, index2, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 7, 1, 1, 1);
      return true;
   }

   public NativeListEntry(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }
}
