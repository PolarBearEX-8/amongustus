package client.setting;

public enum CombatNumberProperty {
    SUCCESS_COUNT(0, "SuccessCount"),
    AFFECTED_BLOCKS(1, "AffectedBlocks"),
    AFFECTED_ENTITIES(2, "AffectedEntities"),
    AFFECTED_ITEMS(3, "AffectedItems"),
    QUERY_RESULT(4, "QueryResult");

   public final int count;
   public final String labelText;
   public static final CombatNumberProperty[] ENUM_VALUES = new CombatNumberProperty[]{
      SUCCESS_COUNT, AFFECTED_BLOCKS, AFFECTED_ENTITIES, AFFECTED_ITEMS, QUERY_RESULT
   };

   public int getCount() {
      return this.count;
   }

   public static String[] getTextArray() {
      String[] textArray = new String[values().length];
      int count2 = 0;

      for (CombatNumberProperty combatNumberProperty : values()) {
         textArray[count2++] = combatNumberProperty.getLabelText();
      }

      return textArray;
   }

   public static CombatNumberProperty resolveCombatNumberProperty(String text) {
      for (CombatNumberProperty combatNumberProperty : values()) {
         if (combatNumberProperty.getLabelText().equals(text)) {
            return combatNumberProperty;
         }
      }

      return null;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private CombatNumberProperty(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }


}
