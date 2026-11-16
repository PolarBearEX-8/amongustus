package net.minecraft.world.storage;

import client.event.PostUpdateFilter;
import client.setting.RenderToggleProperty;
import client.util.InterpolationCollector;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class WorldInfo {
   public final List list = Lists.newArrayList();
   public double value5 = 0.0;
   public double value2 = 0.0;
   public double value6 = 6.0E7;
   public double value3 = this.value6;
   public long timestamp;
   public long timestamp2;
   public int count = 29999984;
   public double value = 0.2;
   public double value4 = 5.0;
   public int count3 = 15;
   public int count2 = 5;

   public double getValue7() {
      return this.value3;
   }

   public double getValue3() {
      if (this.getRenderToggleProperty() != RenderToggleProperty.STATIONARY) {
         double offset = (double)((float)(System.currentTimeMillis() - this.timestamp2) / (float)(this.timestamp - this.timestamp2));
         if (offset < 1.0) {
            return this.value6 + (this.value3 - this.value6) * offset;
         }

         this.applyDouble(this.value3);
      }

      return this.value6;
   }

   public double computeValueByEntity(Entity entity) {
      return this.computeValue(entity.posX, entity.posZ);
   }

   public double getValue2() {
      double offset = this.getValue5() + this.getValue3() / 2.0;
      if (offset > (double)this.count) {
         offset = (double)this.count;
      }

      return offset;
   }

   public void applyDouble(double offset) {
      this.value6 = offset;
      this.value3 = offset;
      this.timestamp = System.currentTimeMillis();
      this.timestamp2 = this.timestamp;
      Iterator iterator = this.newArrayList().iterator();

      while (iterator.hasNext()) {
         ((PostUpdateFilter)iterator.next()).applyWorldInfo(this, offset);
      }
   }

   public int getCount() {
      return this.count;
   }

   public boolean matchesInterpolationCollector(InterpolationCollector interpolationCollector) {
      return (double)interpolationCollector.getCountAsIndex() > this.getValue9()
         && (double)interpolationCollector.getCount3() < this.getValue2()
         && (double)interpolationCollector.getCount2() > this.getValue()
         && (double)interpolationCollector.getCount5() < this.getValueAsDouble();
   }

   public List newArrayList() {
      return Lists.newArrayList(this.list);
   }

   public double getValue() {
      double offset = this.getValue6() - this.getValue3() / 2.0;
      if (offset < (double)(-this.count)) {
         offset = (double)(-this.count);
      }

      return offset;
   }

   public double computeValue(double offset, double offset2) {
      double offset3 = offset2 - this.getValue();
      double offset4 = this.getValueAsDouble() - offset2;
      double offset5 = offset - this.getValue9();
      double offset6 = this.getValue2() - offset;
      return Math.min(Math.min(Math.min(offset5, offset6), offset3), offset4);
   }

   public double getValue4() {
      return this.value4;
   }

   public RenderToggleProperty getRenderToggleProperty() {
      return this.value3 < this.value6
         ? RenderToggleProperty.SHRINKING
         : (this.value3 > this.value6 ? RenderToggleProperty.GROWING : RenderToggleProperty.STATIONARY);
   }

   public void applyDouble(double offset, double offset2, long value7) {
      this.value6 = offset;
      this.value3 = offset2;
      this.timestamp2 = System.currentTimeMillis();
      this.timestamp = this.timestamp2 + value7;
      Iterator iterator = this.newArrayList().iterator();

      while (iterator.hasNext()) {
         ((PostUpdateFilter)iterator.next()).applyWorldInfo(this, offset, offset2, value7);
      }
   }

   public double getValue9() {
      double offset = this.getValue5() - this.getValue3() / 2.0;
      if (offset < (double)(-this.count)) {
         offset = (double)(-this.count);
      }

      return offset;
   }

   public void applyDouble(double offset, double offset2) {
      this.value5 = offset;
      this.value2 = offset2;
      Iterator iterator = this.newArrayList().iterator();

      while (iterator.hasNext()) {
         ((PostUpdateFilter)iterator.next()).applyWorldInfo(this, offset, offset2);
      }
   }

   public int getCount3() {
      return this.count3;
   }

   public void applyIndexByIndex(int count4) {
      this.count3 = count4;
      Iterator iterator = this.newArrayList().iterator();

      while (iterator.hasNext()) {
         ((PostUpdateFilter)iterator.next()).applyWorldInfo(this, count4);
      }
   }

   public void add(PostUpdateFilter postUpdateFilter) {
      this.list.add(postUpdateFilter);
   }

   public boolean matchesAxisAlignedBB(AxisAlignedBB boundingBox) {
      return boundingBox.maxX > this.getValue9() && boundingBox.minX < this.getValue2() && boundingBox.maxZ > this.getValue() && boundingBox.minZ < this.getValueAsDouble();
   }

   public void applyDouble2(double offset) {
      this.value = offset;
      Iterator iterator = this.newArrayList().iterator();

      while (iterator.hasNext()) {
         ((PostUpdateFilter)iterator.next()).applyWorldInfo2(this, offset);
      }
   }

   public double getValue10() {
      return this.value;
   }

   public void applyDoubleByDouble(double offset) {
      this.value4 = offset;
      Iterator iterator = this.newArrayList().iterator();

      while (iterator.hasNext()) {
         ((PostUpdateFilter)iterator.next()).applyWorldInfoByWorldInfo(this, offset);
      }
   }

   public double getValue6() {
      return this.value2;
   }

   public boolean checkBlockPos(BlockPos blockPos) {
      return (double)(blockPos.getX() + 1) > this.getValue9()
         && (double)blockPos.getX() < this.getValue2()
         && (double)(blockPos.getZ() + 1) > this.getValue()
         && (double)blockPos.getZ() < this.getValueAsDouble();
   }

   public long getTimestamp() {
      return this.getRenderToggleProperty() != RenderToggleProperty.STATIONARY ? this.timestamp - System.currentTimeMillis() : 0L;
   }

   public void applyIndex(int count4) {
      this.count2 = count4;
      Iterator iterator = this.newArrayList().iterator();

      while (iterator.hasNext()) {
         ((PostUpdateFilter)iterator.next()).applyWorldInfoByWorldInfo(this, count4);
      }
   }

   public double getValue8() {
      return this.timestamp == this.timestamp2 ? 0.0 : Math.abs(this.value6 - this.value3) / (double)(this.timestamp - this.timestamp2);
   }

   public int getCount2() {
      return this.count2;
   }

   public void setCount(int count4) {
      this.count = count4;
   }

   public double getValue5() {
      return this.value5;
   }

   public double getValueAsDouble() {
      double offset = this.getValue6() + this.getValue3() / 2.0;
      if (offset > (double)this.count) {
         offset = (double)this.count;
      }

      return offset;
   }
}
