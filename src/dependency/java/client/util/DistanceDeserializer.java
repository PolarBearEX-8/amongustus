package client.util;

import client.setting.MovementFactorProperty;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.item.GoldenIngot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class DistanceDeserializer extends GoldenIngot {
   public int count2;
   public int count4;
   public int count3;
   public int count5 = -1;

   public boolean matchesWorld(World world, DistanceAggregator distanceAggregator, int count) {
      if (this.count5 >= 0) {
         return true;
      } else {
         int count6 = 0;
         int count7 = 0;
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

         for (int index = this.distanceAggregator.count4; index <= this.distanceAggregator.count; index++) {
            for (int index2 = this.distanceAggregator.count3; index2 <= this.distanceAggregator.count5; index2++) {
               auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, 64, index);
               if (distanceAggregator.matchesVec3i(auxiliaryBlockPosAdapter)) {
                  count6 += Math.max(world.resolveBlockPos(auxiliaryBlockPosAdapter).getY(), world.secondaryFloatArrayEntry.getCount());
                  count7++;
               }
            }
         }

         if (count7 == 0) {
            return false;
         } else {
            this.count5 = count6 / count7;
            this.distanceAggregator.applyIndex(0, this.count5 - this.distanceAggregator.count6 + count, 0);
            return true;
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Width", this.count2);
      tagCompound.put("Height", this.count4);
      tagCompound.put("Depth", this.count3);
      tagCompound.put("HPos", this.count5);
   }

   public DistanceDeserializer(Random random, int count, int count6, int count7, int count8, int count9, int count10) {
      super(0);
      this.count2 = count8;
      this.count4 = count9;
      this.count3 = count10;
      this.enumFacing = MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random);
      switch (DistanceDeserializerEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
         case 1:
         case 2:
            this.distanceAggregator = new DistanceAggregator(count, count6, count7, count + count8 - 1, count6 + count9 - 1, count7 + count10 - 1);
            break;
         default:
            this.distanceAggregator = new DistanceAggregator(count, count6, count7, count + count10 - 1, count6 + count9 - 1, count7 + count8 - 1);
      }
   }

   public DistanceDeserializer() {
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count2 = tagCompound.computeCount("Width");
      this.count4 = tagCompound.computeCount("Height");
      this.count3 = tagCompound.computeCount("Depth");
      this.count5 = tagCompound.computeCount("HPos");
   }
}
