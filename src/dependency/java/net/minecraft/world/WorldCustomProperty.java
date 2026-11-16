package net.minecraft.world;

import client.util.AdapterStringEntry;
import client.util.SecondaryFloatArrayEntry;
import client.util.SmoothTransformer;
import client.util.SplineChecker;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class WorldCustomProperty extends AdapterStringEntry {
   public World world;
   public final List list = Lists.newArrayList();
   public final List list2 = Lists.newArrayList();
   public final List list3 = Lists.newArrayList();
   public int count;

   public void applyWorldByWorld(World world2) {
      this.world = world2;
      Iterator iterator = this.list3.iterator();

      while (iterator.hasNext()) {
         ((SplineChecker)iterator.next()).setWorld(world2);
      }
   }

   private SmoothTransformer resolveSmoothTransformer(BlockPos blockPos) {
      for (SmoothTransformer smoothTransformer : (Iterable<SmoothTransformer>)(Iterable<?>)this.list2) {
         if (smoothTransformer.getBlockPos().getX() == blockPos.getX() && smoothTransformer.getBlockPos().getZ() == blockPos.getZ() && Math.abs(smoothTransformer.getBlockPos().getY() - blockPos.getY()) <= 1) {
            return smoothTransformer;
         }
      }

      Iterator iterator = this.list3.iterator();

      while (iterator.hasNext()) {
         SmoothTransformer smoothTransformer2 = ((SplineChecker)iterator.next()).resolveSmoothTransformer2(blockPos);
         if (smoothTransformer2 != null) {
            return smoothTransformer2;
         }
      }

      return null;
   }

   public WorldCustomProperty(World world2) {
      super(resolveText(world2.secondaryFloatArrayEntry));
      this.world = world2;
      this.updateState();
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      this.count = tagCompound.computeCount("Tick");
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Villages", 10);

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         SplineChecker splineChecker = new SplineChecker();
         splineChecker.applyNBTTagCompoundByNBTTagCompound(nBTTagCompound);
         this.list3.add(splineChecker);
      }
   }

   private void applyBlockPos2(BlockPos blockPos) {
      EnumFacing enumFacing = BlockDarkOakDoor.resolveEnumFacing(this.world, blockPos);
      EnumFacing enumFacing2 = enumFacing.getEnumFacing5();
      int count2 = this.computeCount(blockPos, enumFacing, 5);
      int count3 = this.computeCount(blockPos, enumFacing2, count2 + 1);
      if (count2 != count3) {
         this.list2.add(new SmoothTransformer(blockPos, count2 < count3 ? enumFacing : enumFacing2, this.count));
      }
   }

   private void updateState5() {
      for (int index = 0; index < this.list2.size(); index++) {
         SmoothTransformer smoothTransformer = (SmoothTransformer)this.list2.get(index);
         SplineChecker splineChecker = this.resolveSplineChecker(smoothTransformer.getBlockPos(), 32);
         if (splineChecker == null) {
            splineChecker = new SplineChecker(this.world);
            this.list3.add(splineChecker);
            this.updateState();
         }

         splineChecker.applySmoothTransformer(smoothTransformer);
      }

      this.list2.clear();
   }

   private void updateState3() {
      Iterator iterator = this.list3.iterator();

      while (iterator.hasNext()) {
         if (((SplineChecker)iterator.next()).isEmpty()) {
            iterator.remove();
            this.updateState();
         }
      }
   }

   public WorldCustomProperty(String text) {
      super(text);
   }

   private boolean matchesBlockPosByBlockPos(BlockPos blockPos) {
      Block block = this.world.getBlockState(blockPos).getBlock();
      return block instanceof BlockDarkOakDoor ? block.getMaterial() == Material.material : false;
   }

   public SplineChecker resolveSplineChecker(BlockPos blockPos, int count2) {
      SplineChecker splineChecker = null;
      double offset = Float.MAX_VALUE;

      for (SplineChecker splineChecker2 : (Iterable<SplineChecker>)(Iterable<?>)this.list3) {
         double coordinate = splineChecker2.getBlockPos2().getCoordinate(blockPos);
         if (coordinate < offset) {
            float factor = (float)(count2 + splineChecker2.getCount());
            if (coordinate <= (double)(factor * factor)) {
               splineChecker = splineChecker2;
               offset = coordinate;
            }
         }
      }

      return splineChecker;
   }

   public void updateState4() {
      this.count++;
      Iterator iterator = this.list3.iterator();

      while (iterator.hasNext()) {
         ((SplineChecker)iterator.next()).applyIndexByIndex(this.count);
      }

      this.updateState3();
      this.updateState2();
      this.updateState5();
      if (this.count % 400 == 0) {
         this.updateState();
      }
   }

   public List getList3() {
      return this.list3;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Tick", this.count);
      NBTTagList nBTTagList = new NBTTagList();

      for (SplineChecker splineChecker : (Iterable<SplineChecker>)(Iterable<?>)this.list3) {
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         splineChecker.applyNBTTagCompound(tagCompound2);
         nBTTagList.applyLISTManager(tagCompound2);
      }

      tagCompound.applyText("Villages", nBTTagList);
   }

   private void applyBlockPosByBlockPos(BlockPos blockPos) {
      for (int index = -16; index < 16; index++) {
         for (int index2 = -4; index2 < 4; index2++) {
            for (int index3 = -16; index3 < 16; index3++) {
               BlockPos blockPos2 = blockPos.add(index, index2, index3);
               if (this.matchesBlockPosByBlockPos(blockPos2)) {
                  SmoothTransformer smoothTransformer = this.resolveSmoothTransformer(blockPos2);
                  if (smoothTransformer == null) {
                     this.applyBlockPos2(blockPos2);
                  } else {
                     smoothTransformer.setCount(this.count);
                  }
               }
            }
         }
      }
   }

   private void updateState2() {
      if (!this.list.isEmpty()) {
         this.applyBlockPosByBlockPos((BlockPos)this.list.remove(0));
      }
   }

   public static String resolveText(SecondaryFloatArrayEntry secondaryFloatArrayEntry) {
      return "villages" + secondaryFloatArrayEntry.getText();
   }

   public void applyBlockPos(BlockPos blockPos) {
      if (this.list.size() <= 64 && !this.matchesBlockPos(blockPos)) {
         this.list.add(blockPos);
      }
   }

   private boolean matchesBlockPos(BlockPos blockPos) {
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         if (((BlockPos)iterator.next()).equals(blockPos)) {
            return true;
         }
      }

      return false;
   }

   private int computeCount(BlockPos blockPos, EnumFacing enumFacing, int count2) {
      int count3 = 0;

      for (int index = 1; index <= 5; index++) {
         int count4 = blockPos.getX() + enumFacing.getInt2() * index;
         int count5 = blockPos.getY() + enumFacing.getInt3() * index;
         int count6 = blockPos.getZ() + enumFacing.getInt() * index;
         if (this.world.checkIndex2(count4, count5, count6)) {
            if (++count3 >= count2) {
               return count3;
            }
         }
      }

      return count3;
   }
}
