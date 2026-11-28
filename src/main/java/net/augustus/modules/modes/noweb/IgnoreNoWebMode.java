package net.augustus.modules.modes.noweb;

import net.augustus.events.EventUpdate;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.SettingGroup;

public class IgnoreNoWebMode extends AbstractNoWebMode {
   public IgnoreNoWebMode(NoWeb noWeb, SettingGroup doubleValue) {
      super(noWeb, doubleValue, "Ignore");
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate(EventUpdate event) {
   }

   @Override
   public void updateEventSubscriptions() {
   }
}
