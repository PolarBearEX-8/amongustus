package client.util;

import client.event.ExternalListener;
import client.setting.BooleanSettingProperty;
import client.setting.InternalNumberSettingEntry;
import client.setting.InternalSettingAdapter;
import client.setting.RangeSetting;
import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.MathHelper;

public class DoubleContainer {
   public static final double value2 = 3.0;
   public Runnable runnable2;
   public RangeSetting settingResyncRate;
   public static final double value9 = 1.0E-9;
   public static final int count = 100;
   public double value8;
   public double value4;
   public Runnable runnable;
   public boolean enabled2;
   public double value3;
   public float factor2;
   public double value6;
   public double value7;
   public int count2;
   public InternalNumberSettingEntry internalNumberSettingEntry;
   public double value10;
   public float factor;
   public InternalNumberSettingEntry internalNumberSettingEntry2;
   public boolean enabled;
   public double value;
   public static final double value5 = 0.001;
   public SimulationSnapshot simulationSnapshot = new SimulationSnapshot(0.0, 0.0);

   public boolean isEnabled() {
      return this.enabled;
   }

   public SmoothEvaluator resolveSmoothEvaluator(double posX, double posY, double posZ, int count3) {
      long value11 = 0x35D684781E6BL;
      long keySalt = 0x65FB1F940A19L ^ value11;
      int count4 = (int)((keySalt ^ 0x94FC026A085L) >>> 48);
      double offset;
      if (Math.signum(posZ) != Math.signum(posX) && posX != 0.0) {
         offset = 0.0;
      } else {
         offset = Math.abs(posZ);
      }

      double value12 = this.value8;
      double value13 = this.value8;
      double value14 = this.simulationSnapshot.value2;
      ArrayList list = this.resolveArrayList(posY, value14, value12, value12);
      int size = list.size();
      DistanceRegistry distanceRegistry = new DistanceRegistry(0.0, 0.0, 0.0);
      RotationMatcher rotationMatcher = new RotationMatcher(posX, this.simulationSnapshot.value, offset, value13, value13, value13);
      RotationMatcher rotationMatcher2 = this.resolveRotationMatcher(size, rotationMatcher);
      ArrayList list2 = this.resolveArrayList(rotationMatcher2);
      if (list2.size() > size || rotationMatcher2.value4 > value13) {
         RotationMatcher rotationMatcher3 = new RotationMatcher(posX, this.simulationSnapshot.value, offset, value13, value13, value13);
         list2 = this.resolveArrayList(rotationMatcher3);
         int size2 = list2.size();
         RotationMatcher rotationMatcher4 = new RotationMatcher(posY, this.simulationSnapshot.value2, 0.0, value12, value12, value12);
         int count5 = size2;
         RotationMatcher rotationMatcher5 = this.resolveRotationMatcher(count5, rotationMatcher4);
         list = this.resolveArrayList(rotationMatcher5);
      }

      if (this.count2 >= list2.size() || this.count2 >= list.size()) {
         if (this.runnable2 != null) {
            this.runnable2.run();
         }

         this.count2 = -1;
      }

      if (this.count2 == -1) {
         this.count2 = (int)((double)Math.max(list2.size(), list.size()) * this.value);
         this.value = this.settingResyncRate.computeValue(true);
      }

      byte byteValue = 0;
      double value15 = this.resolveDistanceRegistry(list2, byteValue, distanceRegistry).value2;
      byte byteValue2 = 0;
      double value16 = this.resolveDistanceRegistry(list, byteValue2, distanceRegistry).value2;
      this.internalNumberSettingEntry.updateState8();
      this.internalNumberSettingEntry2.updateState8();
      double value17 = this.internalNumberSettingEntry.getValue();
      float factor3 = this.factor2;
      boolean flag = false;
      double offset2 = this.internalNumberSettingEntry.computeValue(flag, factor3);
      double offset3 = this.internalNumberSettingEntry2.getValue() + 1.0;
      float factor4 = this.factor;
      boolean flag2 = false;
      double offset4 = this.internalNumberSettingEntry2.computeValue(flag2, factor4) + 1.0;
      boolean flag3 = list2.size() < 2 && list.size() < 2;
      if (count3 >= list2.size() && count3 >= list.size() && this.enabled2) {
         offset2 = 0.0;
         value17 = 0.0;
         offset4 = 1.0;
         offset3 = 1.0;
      }

      byte byteValue3 = 0;
      byte byteValue4 = 0;
      SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(
         this.resolveDistanceRegistry(list2, byteValue3, distanceRegistry).value, this.resolveDistanceRegistry(list, byteValue4, distanceRegistry).value
      );
      SimulationSnapshot simulationSnapshot3 = new SimulationSnapshot(simulationSnapshot2.value * offset3 + simulationSnapshot2.value2 * value17, simulationSnapshot2.value2 * offset4 + simulationSnapshot2.value * offset2);
      this.simulationSnapshot = new SimulationSnapshot(value15, value16);
      return new SmoothEvaluator(simulationSnapshot3, simulationSnapshot2, flag3);
   }

   public void applyDouble(double offset, boolean flag) {
      if (flag) {
         this.value7 = offset;
         this.enabled = true;
      } else {
         this.value8 = offset;
      }
   }

   private DistanceRegistry resolveDistanceRegistry(ArrayList list, int count3, DistanceRegistry distanceRegistry) {
      if (list.isEmpty()) {
         return distanceRegistry;
      } else if (count3 >= list.size()) {
         return (DistanceRegistry)list.get(list.size() - 1);
      } else {
         return count3 < 0 ? distanceRegistry : (DistanceRegistry)list.get(count3);
      }
   }

   public SmoothEvaluator resolveSmoothEvaluator(AtomicDouble atomicDouble, AtomicDouble atomicDouble2, double offset, int count3, ExternalListener externalListener) {
      long value11 = 0x4748F53A1C4CL;
      SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(0.0, 0.0);
      SimulationSnapshot simulationSnapshot3 = new SimulationSnapshot(0.0, 0.0);
      boolean flag = false;

      for (int index = 0; index < count3; index++) {
         double offset2 = atomicDouble.get() / 0.15 - simulationSnapshot3.value;
         double offset3 = atomicDouble2.get() / 0.15 - simulationSnapshot3.value2;
         int count4 = count3 - index;
         double offset4 = offset3;
         double offset5 = offset2;
         SmoothEvaluator smoothEvaluator = this.resolveSmoothEvaluator(offset5, offset4, offset, count4);
         SimulationSnapshot simulationSnapshot4 = smoothEvaluator.simulationSnapshot2;
         simulationSnapshot2 = simulationSnapshot2.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot4);
         SimulationSnapshot simulationSnapshot5 = smoothEvaluator.simulationSnapshot;
         simulationSnapshot3 = simulationSnapshot3.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot5);
         flag |= smoothEvaluator.enabled;
         double value12 = simulationSnapshot2.value2;
         double value13 = simulationSnapshot2.value;
         externalListener.applyIndex(index, value13, value12);
      }

      if (flag) {
         this.runnable.run();
         this.count2 = -1;
      }

      return new SmoothEvaluator(simulationSnapshot2, simulationSnapshot3);
   }

   public ArrayList resolveArrayList(double posX, double posX2, double posY, double posZ) {
      RotationMatcher rotationMatcher = new RotationMatcher();
      rotationMatcher.value2 = posX;
      rotationMatcher.value5 = 0.0;
      rotationMatcher.value4 = posY;
      rotationMatcher.value3 = rotationMatcher.value4;
      rotationMatcher.value = posZ;
      rotationMatcher.value7 = posX2;
      return this.resolveArrayList(rotationMatcher);
   }

   public void updateState() {
      this.enabled = false;
   }

   public RotationMatcher resolveRotationMatcher(int count3, RotationMatcher rotationMatcher) {
      double offset = 0.001;
      double value11 = rotationMatcher.value4;

      for (int index = 0; index < 100; index++) {
         double offset2 = 0.5 * (offset + value11);
         RotationMatcher rotationMatcher2 = rotationMatcher.getRotationMatcher();
         rotationMatcher2.value4 = rotationMatcher2.value3 = offset2;
         int size = this.resolveArrayList(rotationMatcher2).size();
         if (size < count3) {
            value11 = offset2;
         } else {
            if (size <= count3) {
               RotationMatcher rotationMatcher3 = rotationMatcher.getRotationMatcher();
               rotationMatcher3.value4 = rotationMatcher3.value3 = offset2;
               return rotationMatcher3;
            }

            offset = offset2;
         }

         if (Math.abs(value11 - offset) < 1.0E-9) {
            break;
         }
      }

      RotationMatcher rotationMatcher4 = rotationMatcher.getRotationMatcher();
      rotationMatcher4.value4 = rotationMatcher4.value3 = 0.5 * (offset + value11);
      return rotationMatcher4;
   }

   public ArrayList resolveArrayList(RotationMatcher rotationMatcher) {
      ArrayList list = new ArrayList();
      double offset = 0.0;
      double value11 = rotationMatcher.value2;
      double offset2 = value11;

      for (int index = 0; index < 1000 && offset2 != 0.0; index++) {
         double offset3 = (rotationMatcher.value5 + Math.sqrt(2.0 * rotationMatcher.value3 * this.value4 * Math.abs(offset2))) * Math.signum(offset2) - rotationMatcher.value7;
         double value12 = rotationMatcher.value4;
         if (Math.signum(offset2) != Math.signum(rotationMatcher.value7)) {
            double value13 = this.value10;
            if (Math.abs(rotationMatcher.value7) > this.value3 / 0.15 / 3.0) {
               value13 = this.value6;
            }

            value12 = rotationMatcher.value * value13;
         } else if (Math.signum(offset3) != Math.signum(offset2)) {
            value12 = Double.POSITIVE_INFINITY;
         }

         double doubleItem = MathHelper.getDouble7(offset3, -value12, value12);
         rotationMatcher.value7 = rotationMatcher.value7 + doubleItem * rotationMatcher.value6;
         offset2 -= rotationMatcher.value7 * rotationMatcher.value6;
         offset += rotationMatcher.value7 * rotationMatcher.value6;
         if (Math.signum(offset2) != Math.signum(rotationMatcher.value2)) {
            rotationMatcher.value7 = rotationMatcher.value5 * Math.signum(rotationMatcher.value2);
            offset2 = 0.0;
            offset = value11;
         }

         list.add(new DistanceRegistry(rotationMatcher.value7, offset, doubleItem));
         if (Math.abs(offset2) > 1200.0) {
            offset2 = (Math.abs(offset2) - 1200.0) * -Math.signum(offset2);
         }
      }

      return list;
   }

   public double getValue8() {
      return this.enabled ? Math.min(this.value8, this.value7) : this.value8;
   }

   public DoubleContainer(BooleanSettingProperty booleanSettingProperty) {
      this.value8 = 3.0;
      this.value4 = 1.0;
      this.value10 = 1.0;
      this.value6 = 1.0;
      this.value3 = 10.0;
      this.enabled = false;
      this.value7 = this.value8;
      this.runnable = null;
      this.runnable2 = null;
      this.count2 = -1;
      this.value = 0.5;
      this.enabled2 = false;
      this.factor2 = (float)ThreadLocalRandom.current().nextDouble(-200.0, 200.0);
      this.factor = (float)ThreadLocalRandom.current().nextDouble(-200.0, 200.0);
      InternalNumberSettingEntry internalNumberSettingEntry3 = new InternalNumberSettingEntry(booleanSettingProperty, "disturbance", false);
      double offset = 0.5;
      double offset2 = 0.0;
      double offset3 = 0.2;
      internalNumberSettingEntry3 = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(offset3, offset2, offset, 2);
      String text = "Disturbance";
      this.internalNumberSettingEntry = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(text);
      InternalNumberSettingEntry internalNumberSettingEntry4 = this.internalNumberSettingEntry;
      BooleanSupplierEntry booleanSupplierEntry = this.internalNumberSettingEntry.getBooleanSupplierEntry();
      float factor3 = 16.6F;
      InternalSettingAdapter internalSettingAdapter = booleanSupplierEntry.withFactor(factor3).getInternalSettingAdapter();
      internalNumberSettingEntry4.add(internalSettingAdapter);
      internalNumberSettingEntry4 = this.internalNumberSettingEntry;
      BooleanSupplierEntry booleanSupplierEntry2 = this.internalNumberSettingEntry.getBooleanSupplierEntry();
      float factor4 = 16.6F;
      InternalSettingAdapter internalSettingAdapter2 = booleanSupplierEntry2.withFactor(factor4).getInternalSettingAdapter();
      internalNumberSettingEntry4.add(internalSettingAdapter2);
      internalNumberSettingEntry4 = this.internalNumberSettingEntry;
      BooleanSupplierEntry booleanSupplierEntry3 = this.internalNumberSettingEntry.getBooleanSupplierEntry();
      float factor5 = 16.6F;
      InternalSettingAdapter internalSettingAdapter3 = booleanSupplierEntry3.withFactor(factor5).getInternalSettingAdapter();
      internalNumberSettingEntry4.add(internalSettingAdapter3);
      internalNumberSettingEntry3 = new InternalNumberSettingEntry(booleanSettingProperty, "overshoot", false);
      double offset4 = 0.2;
      double offset5 = 0.0;
      double offset6 = 0.01;
      internalNumberSettingEntry3 = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(offset6, offset5, offset4, 3);
      String text2 = "Overshoot";
      this.internalNumberSettingEntry2 = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(text2);
      internalNumberSettingEntry4 = this.internalNumberSettingEntry2;
      BooleanSupplierEntry booleanSupplierEntry4 = this.internalNumberSettingEntry2.getBooleanSupplierEntry();
      float factor6 = 16.6F;
      InternalSettingAdapter internalSettingAdapter4 = booleanSupplierEntry4.withFactor(factor6).getInternalSettingAdapter();
      internalNumberSettingEntry4.add(internalSettingAdapter4);
      internalNumberSettingEntry4 = this.internalNumberSettingEntry2;
      BooleanSupplierEntry booleanSupplierEntry5 = this.internalNumberSettingEntry2.getBooleanSupplierEntry();
      float factor7 = 16.6F;
      InternalSettingAdapter internalSettingAdapter5 = booleanSupplierEntry5.withFactor(factor7).getInternalSettingAdapter();
      internalNumberSettingEntry4.add(internalSettingAdapter5);
      RangeSetting setting = new RangeSetting(booleanSettingProperty.getModule(), "UNoise3ResyncRate", 0.4, 0.6, 0.0, 1.0, 2, booleanSettingProperty.getSettingGroup());
      String text3 = "Resync rate";
      this.settingResyncRate = setting.getRangeSetting(text3);
   }
}
