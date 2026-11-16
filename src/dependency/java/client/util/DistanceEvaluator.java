package client.util;

import client.event.PostRenderConsumer;
import client.setting.AltOptionType;
import client.setting.MovementProperty;
import java.util.Random;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityWitch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class DistanceEvaluator extends DistanceDeserializer {
   public boolean enabled;

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Witch", this.enabled);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("Witch");
   }

   public DistanceEvaluator() {
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (!this.matchesWorld(world, distanceAggregator, 0)) {
         return false;
      } else {
         this.applyWorld(
            world,
            distanceAggregator,
            1,
            1,
            1,
            5,
            1,
            7,
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            1,
            4,
            2,
            5,
            4,
            7,
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            2,
            1,
            0,
            4,
            1,
            0,
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            2,
            2,
            2,
            3,
            3,
            2,
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            1,
            2,
            3,
            1,
            3,
            6,
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            5,
            2,
            3,
            5,
            3,
            6,
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            2,
            2,
            7,
            4,
            3,
            7,
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            Blocks.block142.processAction(MovementProperty.SPRUCE.getCount()),
            false
         );
         this.applyWorld(world, distanceAggregator, 1, 0, 2, 1, 3, 2, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 5, 0, 2, 5, 3, 2, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 1, 0, 7, 1, 3, 7, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 5, 0, 7, 5, 3, 7, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 2, 3, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 3, 3, 7, distanceAggregator);
         this.applyWorld(world, Blocks.air.getDefaultState(), 1, 3, 4, distanceAggregator);
         this.applyWorld(world, Blocks.air.getDefaultState(), 5, 3, 4, distanceAggregator);
         this.applyWorld(world, Blocks.air.getDefaultState(), 5, 3, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block56.getDefaultState().resolveIBlockState(BlockFlowerPot.immutableSetEntry, AltOptionType.MUSHROOM_RED), 1, 3, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block46.getDefaultState(), 3, 2, 6, distanceAggregator);
         this.applyWorld(world, Blocks.blockCauldron.getDefaultState(), 4, 2, 6, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 2, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 5, 2, 1, distanceAggregator);
         int count = this.computeCount(Blocks.block3, 3);
         int count2 = this.computeCount(Blocks.block3, 1);
         int count3 = this.computeCount(Blocks.block3, 0);
         int count4 = this.computeCount(Blocks.block3, 2);
         this.applyWorld(world, distanceAggregator, 0, 4, 1, 6, 4, 1, Blocks.block85.processAction(count), Blocks.block85.processAction(count), false);
         this.applyWorld(world, distanceAggregator, 0, 4, 2, 0, 4, 7, Blocks.block85.processAction(count3), Blocks.block85.processAction(count3), false);
         this.applyWorld(world, distanceAggregator, 6, 4, 2, 6, 4, 7, Blocks.block85.processAction(count2), Blocks.block85.processAction(count2), false);
         this.applyWorld(world, distanceAggregator, 0, 4, 8, 6, 4, 8, Blocks.block85.processAction(count4), Blocks.block85.processAction(count4), false);

         for (int byteValue = 2; byteValue <= 7; byteValue += 5) {
            for (int byteValue2 = 1; byteValue2 <= 5; byteValue2 += 4) {
               this.applyWorldByWorld(world, Blocks.block140.getDefaultState(), byteValue2, -1, byteValue, distanceAggregator);
            }
         }

         if (!this.enabled) {
            int count5 = this.computeCountByIndex(2, 5);
            int count6 = this.computeCount(2);
            int count7 = this.computeCount(2, 5);
            if (distanceAggregator.matchesVec3i(new BlockPos(count5, count6, count7))) {
               this.enabled = true;
               EntityWitch entityWitch = new EntityWitch(world);
               entityWitch.applyDoubleByDouble((double)count5 + 0.5, (double)count6, (double)count7 + 0.5, 0.0F, 0.0F);
               entityWitch.resolvePostRenderConsumer(world.resolveGoldenOre(new BlockPos(count5, count6, count7)), (PostRenderConsumer)null);
               world.matchesEntity(entityWitch);
            }
         }

         return true;
      }
   }

   public DistanceEvaluator(Random random, int count, int count2) {
      super(random, count, 64, count2, 7, 7, 9);
   }
}
