package client.util;

import augustus.modules.combat.Criticals;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.setting.BooleanSetting;
import client.setting.SettingGroup;

public abstract class InternalAttackEntityListenerAdapter extends ExtendedStringArrayEntry implements AttackEntityListener {
   public final BooleanSetting settingOnlyOnHit;
   public boolean enabled2;

   @Override
   public void updateState() {
      this.enabled2 = false;
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      this.enabled2 = true;
   }

   public void updateState3() {
      this.enabled2 = false;
   }

   @Override
   public boolean isActive() {
      return super.isActive() && (this.enabled2 || !this.settingOnlyOnHit.isEnabled());
   }

   @Override
   public void updateState2() {
      byte byteValue = 1;
      this.applyIndex(byteValue);
   }

   public InternalAttackEntityListenerAdapter(Criticals criticals, SettingGroup settingGroup, String text) {
        super(criticals, settingGroup, text);
      
      BooleanSetting setting = new BooleanSetting(this.module, this.getString() + "OnlyOnHit", false, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      setting = (BooleanSetting)setting.getSetting(booleanSupplier);
      String text2 = "OnlyOnHit";
      this.settingOnlyOnHit = (BooleanSetting)setting.getSetting(text2);
      this.enabled2 = false;
   }
}
