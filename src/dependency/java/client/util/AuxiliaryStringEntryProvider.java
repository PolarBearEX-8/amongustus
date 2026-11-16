package client.util;

import client.setting.BooleanSettingProperty;
import client.setting.InternalNumberSettingEntry;
import client.setting.InternalSettingAdapter;
import client.setting.SettingGroup;
import net.minecraft.entity.EntityLivingBaseEntry;

public class AuxiliaryStringEntryProvider extends SmoothWriter {
   public InternalNumberSettingEntry internalNumberSettingEntry;

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      double offset = 0.5;
      double offset2 = 0.5;
      double offset3 = this.internalNumberSettingEntry.computeValue(offset2, offset);
      return TrajectoryPredictor.getCoordinate(entityLivingBaseEntry.valueArray, offset3);
   }

   @Override
   public void updateState() {
      this.internalNumberSettingEntry.updateState8();
   }

   public AuxiliaryStringEntryProvider(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "Noise", true);
      SettingGroup setting = this.getSetting();
      InternalNumberSettingEntry internalNumberSettingEntry2 = new InternalNumberSettingEntry(booleanSettingProperty.resolveBooleanSettingPropertyBySettingGroup(setting), true);
      String text = "Noise";
      this.internalNumberSettingEntry = internalNumberSettingEntry2.resolveInternalNumberSettingEntry(text);
      InternalNumberSettingEntry internalNumberSettingEntry3 = this.internalNumberSettingEntry;
      InternalSettingAdapter internalSettingAdapter = this.internalNumberSettingEntry.getBooleanSupplierEntry().withEnabled(true).getInternalSettingAdapter();
      internalNumberSettingEntry3.add(internalSettingAdapter);
      double offset = 100.0;
      double offset2 = 1.0;
      double offset3 = 100.0;
      this.internalNumberSettingEntry.resolveInternalNumberSettingEntry(offset3, offset2, offset);
   }
}
