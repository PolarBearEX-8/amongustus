package client.util;

import client.setting.CombatBooleanProperty;

public class FalseManager {
   public String labelText;
   public boolean enabled;
   public int count;
   public double value;
   public final CombatBooleanProperty combatBooleanProperty;

   public boolean isEnabled() {
      return this.enabled;
   }

   public void applyText(String text) {
      this.labelText = text;
      if (text != null) {
         if (text.equals("false")) {
            this.enabled = false;
            return;
         }

         if (text.equals("true")) {
            this.enabled = true;
            return;
         }
      }

      this.enabled = Boolean.parseBoolean(text);
      this.count = this.enabled ? 1 : 0;

      try {
         this.count = Integer.parseInt(text);
      } catch (NumberFormatException numberFormatException) {
      }

      try {
         this.value = Double.parseDouble(text);
      } catch (NumberFormatException numberFormatException2) {
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public CombatBooleanProperty getCombatBooleanProperty() {
      return this.combatBooleanProperty;
   }

   public FalseManager(String text, CombatBooleanProperty combatBooleanProperty2) {
      this.combatBooleanProperty = combatBooleanProperty2;
      this.applyText(text);
   }
}
