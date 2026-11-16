package client.setting;

import java.util.Arrays;

public enum CombatSpeedProperty {
    SOCKS4("Socks 4"),
    SOCKS5("Socks 5");

   public static final CombatSpeedProperty[] ENUM_VALUES = new CombatSpeedProperty[]{SOCKS4, CombatSpeedProperty.SOCKS5};
   public final String labelText;

   public String getLabelText() {
      return this.labelText;
   }

   public static CombatSpeedProperty resolveCombatSpeedProperty(String text) {
      return Arrays.stream(values()).filter(property -> matchesText(text, property)).findFirst().orElse(null);
   }

   private static boolean matchesText(String text, CombatSpeedProperty combatSpeedProperty) {
      return combatSpeedProperty.getLabelText().equals(text);
   }

   private CombatSpeedProperty(String text) {
      this.labelText = text;
   }


}
