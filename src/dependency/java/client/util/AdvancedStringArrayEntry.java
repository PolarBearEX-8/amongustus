package client.util;

import client.setting.CombatOptionProperty;

public class AdvancedStringArrayEntry {
   public String[] stringArray;
   public final CombatOptionProperty combatOptionProperty;

   public String[] getStringArray() {
      return this.stringArray;
   }

   public CombatOptionProperty getCombatOptionProperty() {
      return this.combatOptionProperty;
   }

   public void setStringArray(String[] stringArray2) {
      this.stringArray = stringArray2;
   }

   public AdvancedStringArrayEntry(String[] textArray, CombatOptionProperty combatOptionProperty2) {
      this.stringArray = textArray;
      this.combatOptionProperty = combatOptionProperty2;
   }
}
