package client.util;

import client.setting.BooleanSettingProperty;
import client.setting.InternalNumberSettingEntry;
import client.setting.SettingGroup;
import net.minecraft.entity.EntityLivingBaseEntry;
import net.minecraft.util.Vec3;

public class SecondaryStringEntryAdapter extends SmoothWriter {
   public InternalNumberSettingEntry internalNumberSettingEntry2;
   public InternalNumberSettingEntry internalNumberSettingEntry;

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      double offset = 0.5;
      double offset2 = 0.5;
      double doubleItem = PlayerUtil.getDouble(entityLivingBaseEntry.axisAlignedBB.minX, entityLivingBaseEntry.axisAlignedBB.maxX, this.internalNumberSettingEntry2.computeValue(offset2, offset));
      double offset3 = 0.5;
      double offset4 = 0.5;
      Vec3 vec = new Vec3(
         doubleItem, 0.0, PlayerUtil.getDouble(entityLivingBaseEntry.axisAlignedBB.minZ, entityLivingBaseEntry.axisAlignedBB.maxZ, this.internalNumberSettingEntry.computeValue(offset4, offset3))
      );
      Vec3 vec3 = vec.getVec311(entityLivingBaseEntry.vec4);
      return Math.atan2(vec3.zCoord, vec3.xCoord) / Math.PI * 180.0 - 90.0;
   }

   @Override
   public void updateState() {
      this.internalNumberSettingEntry2.updateState8();
      this.internalNumberSettingEntry.updateState8();
   }

   public SecondaryStringEntryAdapter(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "Point", true);
      SettingGroup setting = this.getSetting();
      booleanSettingProperty = booleanSettingProperty.resolveBooleanSettingPropertyBySettingGroup(setting);
      InternalNumberSettingEntry internalNumberSettingEntry3 = new InternalNumberSettingEntry(booleanSettingProperty, "x", true);
      String text = "x";
      internalNumberSettingEntry3 = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(text);
      double offset = 100.0;
      double offset2 = 0.0;
      double offset3 = 100.0;
      this.internalNumberSettingEntry2 = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(offset3, offset2, offset);
      internalNumberSettingEntry3 = new InternalNumberSettingEntry(booleanSettingProperty, "z", true);
      String text2 = "z";
      internalNumberSettingEntry3 = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(text2);
      double offset4 = 100.0;
      double offset5 = 0.0;
      double offset6 = 100.0;
      this.internalNumberSettingEntry = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(offset6, offset5, offset4);
      this.internalNumberSettingEntry2.updateState7();
      this.internalNumberSettingEntry.updateState7();
   }
}
