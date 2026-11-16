package client.util;

import client.setting.ActiveOptionType;
import client.setting.CombatIntervalProperty;

public class LocalFloatHolder {
   public float factor = 1.0F;
   public final ActiveOptionType activeOptionType;
   public CombatIntervalProperty combatIntervalProperty;

   public void setFactor(float factor2) {
      this.factor = factor2;
   }

   public CombatIntervalProperty getCombatIntervalProperty() {
      return this.combatIntervalProperty;
   }

   public void setCombatIntervalProperty(CombatIntervalProperty combatIntervalProperty2) {
      this.combatIntervalProperty = combatIntervalProperty2;
   }

   public float getFactor() {
      return this.factor;
   }

   public ActiveOptionType getActiveOptionType() {
      return this.activeOptionType;
   }

   public LocalFloatHolder(ActiveOptionType activeOptionType2) {
      this.activeOptionType = activeOptionType2;
      this.combatIntervalProperty = activeOptionType2.getCombatIntervalProperty();
   }
}
