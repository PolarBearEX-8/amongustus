package client.util;

import client.setting.BooleanSettingProperty;
import client.setting.InternalNumberSettingEntry;
import client.setting.RangeSetting;
import client.setting.SettingGroup;
import net.minecraft.entity.EntityLivingBaseEntry;

public class CustomStringEntryAdapter extends SmoothWriter {
   public RangeSetting settingDelay;
   public InternalNumberSettingEntry internalNumberSettingEntry;

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      double[] valueArray = entityLivingBaseEntry.valueArray2;
      double offset = 0.5;
      double offset2 = 0.5;
      double offset3 = this.internalNumberSettingEntry.computeValue(offset2, offset);
      return TrajectoryPredictor.getCoordinate(valueArray, offset3);
   }

   public RangeSetting getSettingDelay() {
      return this.settingDelay;
   }

   @Override
   public void updateState() {
      this.internalNumberSettingEntry.updateState8();
   }

   public CustomStringEntryAdapter(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "Future Interval", true);
      RangeSetting setting = new RangeSetting(booleanSettingProperty.getModule(), "UNoise3FutureIntervalDelay", 0.0, 0.0, 0.0, 3.0, 0, this.getSetting());
      String text = "Delay";
      this.settingDelay = setting.getRangeSetting(text);
      SettingGroup setting2 = this.getSetting();
      InternalNumberSettingEntry internalNumberSettingEntry2 = new InternalNumberSettingEntry(booleanSettingProperty.resolveBooleanSettingPropertyBySettingGroup(setting2), "noise", true);
      String text2 = "Noise";
      this.internalNumberSettingEntry = internalNumberSettingEntry2.resolveInternalNumberSettingEntry(text2);
      this.internalNumberSettingEntry.updateState7();
      double offset = 100.0;
      double offset2 = 0.0;
      double offset3 = 100.0;
      this.internalNumberSettingEntry.resolveInternalNumberSettingEntry(offset3, offset2, offset);
   }
}
