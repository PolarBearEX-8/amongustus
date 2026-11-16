package client.setting;

import client.util.DampenerEvaluator;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.Random;

public enum MovementSliderProperty implements Iterable, Predicate {
   HORIZONTAL,
   VERTICAL;

   public static final MovementSliderProperty[] ENUM_VALUES = values();

   public RenderChoiceProperty[] getRenderChoicePropertyArray() {
      switch (DampenerEvaluator.countArray[this.ordinal()]) {
         case 1:
            return new RenderChoiceProperty[]{RenderChoiceProperty.NORTH, RenderChoiceProperty.EAST, RenderChoiceProperty.SOUTH, RenderChoiceProperty.WEST};
         case 2:
            return new RenderChoiceProperty[]{RenderChoiceProperty.UP, RenderChoiceProperty.DOWN};
         default:
            throw new Error("Someone's been tampering with the universe!");
      }
   }

   public boolean matchesRenderChoiceProperty(RenderChoiceProperty renderChoiceProperty) {
      return renderChoiceProperty != null && renderChoiceProperty.getSecondaryOptionType().getMovementSliderProperty() == this;
   }

   public RenderChoiceProperty resolveRenderChoiceProperty(Random random) {
      RenderChoiceProperty[] renderChoicePropertyArray = this.getRenderChoicePropertyArray();
      return renderChoicePropertyArray[random.nextInt(renderChoicePropertyArray.length)];
   }

   @Override
   public Iterator iterator() {
      return Iterators.forArray(this.getRenderChoicePropertyArray());
   }

   public boolean apply(Object entry) {
      RenderChoiceProperty renderChoiceProperty = (RenderChoiceProperty)entry;
      return this.matchesRenderChoiceProperty(renderChoiceProperty);
   }
}
