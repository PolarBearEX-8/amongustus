package client.setting;

public enum CustomGlintSwordsType implements NamedOption {
    SWORDS("Swords"),
    TOOLS("Tools"),
    ARMOR("Armor"),
    REST("Rest");

   public final String labelText;
   public boolean enabled;
   public static final CustomGlintSwordsType[] ENUM_VALUES = new CustomGlintSwordsType[]{SWORDS, TOOLS, ARMOR, REST};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private CustomGlintSwordsType(String text) {
      this.labelText = text;
   }


}
