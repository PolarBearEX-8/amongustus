package client.util;

import client.setting.BooleanSettingProperty;
import client.setting.CombatChoiceProperty;
import client.setting.InternalNumberSettingEntry;
import client.setting.InternalSettingAdapter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.entity.EntityLivingBaseEntry;

public class TertiaryStringEntryAdapter extends SmoothWriter {
   public static ArrayList arrayList = new ArrayList();
   public InternalNumberSettingEntry internalNumberSettingEntry;
   public static long timestamp = ThreadLocalRandom.current().nextLong();
   public double value;
   public CombatChoiceProperty combatChoiceProperty3;
   public CombatChoiceProperty combatChoiceProperty2;

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      double value2 = this.combatChoiceProperty3.getValue();
      double offset = 0.5;
      double offset2 = 0.0;
      double offset3 = this.internalNumberSettingEntry.computeValue(offset2, offset);
      if (entityLivingBaseEntry.enabled) {
         double[] valueArray = entityLivingBaseEntry.valueArray;
         double offset4 = PlayerUtil.getDouble(0.5 - value2, 0.5 + value2, Math.sin(this.value) * 0.5 + 0.5) - offset3;
         return TrajectoryPredictor.getCoordinate(valueArray, offset4);
      } else {
         double[] valueArray2 = entityLivingBaseEntry.valueArray;
         double offset5 = PlayerUtil.getDouble(0.5 - value2, 0.5 + value2, Math.cos(this.value) * 0.5 + 0.5) - offset3;
         return TrajectoryPredictor.getCoordinate(valueArray2, offset5);
      }
   }

   public static void clear() {
      arrayList.clear();
   }

   public static void updateState2() {
      float factor = (float)ThreadLocalRandom.current().nextDouble(-5.0, 5.0);
      float factor2 = (float)ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI);

      for (TertiaryStringEntryAdapter tertiaryStringEntryAdapter : (Iterable<TertiaryStringEntryAdapter>)(Iterable)arrayList) {
         tertiaryStringEntryAdapter.combatChoiceProperty3.applyFloat(factor);
         tertiaryStringEntryAdapter.combatChoiceProperty2.applyFloat(factor);
         tertiaryStringEntryAdapter.internalNumberSettingEntry.applyFloat(factor);
         tertiaryStringEntryAdapter.value = (double)factor2;
      }
   }

   @Override
   public void updateState() {
      this.combatChoiceProperty3.updateState8();
      this.combatChoiceProperty2.updateState8();
      this.internalNumberSettingEntry.updateState8();
      double offset = this.combatChoiceProperty2.getValue() / 180.0 * Math.PI;
      this.value += offset;
   }

   public TertiaryStringEntryAdapter(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "Circle", true);
      arrayList.add(this);
      CombatChoiceProperty combatChoiceProperty = new CombatChoiceProperty(booleanSettingProperty.getModule(), this.getSetting().getString() + "_radius", this.getSetting(), false);
      String text = "Radius";
      this.combatChoiceProperty3 = combatChoiceProperty.resolveCombatChoiceProperty(text);
      double offset = 0.4;
      double offset2 = 0.15;
      double offset3 = 0.5;
      double offset4 = 0.01;
      this.combatChoiceProperty3.resolveCombatChoiceProperty(offset4, offset3, offset2, offset, 2);
      this.combatChoiceProperty3.updateState7();
      combatChoiceProperty = new CombatChoiceProperty(booleanSettingProperty.getModule(), this.getSetting().getString() + "_angleChange", this.getSetting(), false);
      String text2 = "Angle rate of change";
      this.combatChoiceProperty2 = combatChoiceProperty.resolveCombatChoiceProperty(text2);
      double offset5 = 0.4;
      double offset6 = 0.2;
      double offset7 = 180.0;
      double offset8 = 0.01;
      this.combatChoiceProperty2.resolveCombatChoiceProperty(offset8, offset7, offset6, offset5, 2);
      this.combatChoiceProperty2.updateState7();
      InternalNumberSettingEntry internalNumberSettingEntry2 = new InternalNumberSettingEntry(booleanSettingProperty.getModule(), this.getSetting().getString() + "_center", this.getSetting(), false);
      String text3 = "Center";
      this.internalNumberSettingEntry = internalNumberSettingEntry2.resolveInternalNumberSettingEntry(text3);
      double offset9 = 100.0;
      double offset10 = 0.0;
      double offset11 = 0.0;
      this.internalNumberSettingEntry.resolveInternalNumberSettingEntry(offset11, offset10, offset9);
      this.internalNumberSettingEntry.updateState7();
      Random random = new Random(timestamp);

      for (InternalSettingAdapter internalSettingAdapter : (Iterable<InternalSettingAdapter>)(Iterable)this.combatChoiceProperty3.getArrayList()) {
         float factor = random.nextFloat() * 200.0F - 100.0F;
         internalSettingAdapter.setFactor3(factor);
      }

      for (InternalSettingAdapter internalSettingAdapter2 : (Iterable<InternalSettingAdapter>)(Iterable)this.combatChoiceProperty2.getArrayList()) {
         float factor2 = random.nextFloat() * 200.0F - 100.0F;
         internalSettingAdapter2.setFactor3(factor2);
      }

      for (InternalSettingAdapter internalSettingAdapter3 : (Iterable<InternalSettingAdapter>)(Iterable)this.internalNumberSettingEntry.getArrayList()) {
         float factor3 = random.nextFloat() * 200.0F - 100.0F;
         internalSettingAdapter3.setFactor3(factor3);
      }
   }
}
