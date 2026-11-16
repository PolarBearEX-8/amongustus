package client.setting;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;

public enum SecondaryOptionType implements Predicate {
   X("x", MovementSliderProperty.HORIZONTAL),
   Y("y", MovementSliderProperty.VERTICAL),
   Z("z", MovementSliderProperty.HORIZONTAL);

   public final MovementSliderProperty movementSliderProperty;
   public final String labelText;
   public static final Map map = Maps.newHashMap();
   public static final SecondaryOptionType[] ENUM_VALUES = values();

   public String getLabelText() {
      return this.labelText;
   }

   public static SecondaryOptionType resolveSecondaryOptionType(String text) {
      return text == null ? null : (SecondaryOptionType)map.get(text.toLowerCase());
   }

   public String getLabelTextAsText() {
      return this.labelText;
   }

   public boolean isActiveAsFlag() {
      return this.movementSliderProperty == MovementSliderProperty.VERTICAL;
   }

   public boolean matchesRenderChoiceProperty(RenderChoiceProperty renderChoiceProperty) {
      return renderChoiceProperty != null && renderChoiceProperty.getSecondaryOptionType() == this;
   }

   public MovementSliderProperty getMovementSliderProperty() {
      return this.movementSliderProperty;
   }

   public boolean isActive() {
      return this.movementSliderProperty == MovementSliderProperty.HORIZONTAL;
   }

   private SecondaryOptionType(String text, MovementSliderProperty movementSliderProperty2) {
      this.labelText = text;
      this.movementSliderProperty = movementSliderProperty2;
   }

   static {
      for (SecondaryOptionType secondaryOptionType : values()) {
         map.put(secondaryOptionType.getLabelTextAsText().toLowerCase(), secondaryOptionType);
      }
   }

   @Override
   public String toString() {
      return this.labelText;
   }

   public boolean apply(Object entry) {
      RenderChoiceProperty renderChoiceProperty = (RenderChoiceProperty)entry;
      return this.matchesRenderChoiceProperty(renderChoiceProperty);
   }
}
