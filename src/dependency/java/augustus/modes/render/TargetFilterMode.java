package augustus.modes.render;

import client.setting.NamedOption;

public enum TargetFilterMode implements NamedOption {
    NORMAL("Normal"),
    SIMPLE("Simple"),
    GOMME("OldGomme"),
    OTHER("Other"),
    LIST_BASED("ListBased");

   public final String labelText;
   public boolean enabled;
   public static final TargetFilterMode[] ENUM_VALUES = new TargetFilterMode[]{NORMAL, SIMPLE, GOMME, OTHER, LIST_BASED};

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

   private TargetFilterMode(String text) {
      this.labelText = text;
   }


}
