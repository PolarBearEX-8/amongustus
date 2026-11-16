package client.setting;

public enum RenderBooleanProperty {
   POSITION("Position"),
   NORMAL("Normal"),
   COLOR("Vertex Color"),
   UV("UV"),
   MATRIX("Bone Matrix"),
   BLEND_WEIGHT("Blend Weight"),
   PADDING("Padding");

   public final String labelText;
   public static final RenderBooleanProperty[] ENUM_VALUES = values();

   public String getLabelText() {
      return this.labelText;
   }

   private RenderBooleanProperty(String text) {
      this.labelText = text;
   }
}
