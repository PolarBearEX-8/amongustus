package client.setting;

import client.util.AuxiliaryComparableAdapter;
import client.util.DampenerEvaluator;
import client.util.InternalFloatArrayEntry;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;

public enum RenderChoiceProperty {
   DOWN(0, 1, -1, "down", MovementLimitProperty.NEGATIVE, SecondaryOptionType.Y, new AuxiliaryComparableAdapter(0, -1, 0)),

   UP(1, 0, -1, "up", MovementLimitProperty.POSITIVE, SecondaryOptionType.Y, new AuxiliaryComparableAdapter(0, 1, 0)),

   NORTH(2, 3, 2, "north", MovementLimitProperty.NEGATIVE, SecondaryOptionType.Z, new AuxiliaryComparableAdapter(0, 0, -1)),

   SOUTH(3, 2, 0, "south", MovementLimitProperty.POSITIVE, SecondaryOptionType.Z, new AuxiliaryComparableAdapter(0, 0, 1)),

   WEST(4, 5, 1, "west", MovementLimitProperty.NEGATIVE, SecondaryOptionType.X, new AuxiliaryComparableAdapter(-1, 0, 0)),

   EAST(5, 4, 3, "east", MovementLimitProperty.POSITIVE, SecondaryOptionType.X, new AuxiliaryComparableAdapter(1, 0, 0));

   public final MovementLimitProperty movementLimitProperty;
   public final String labelText;
   public final AuxiliaryComparableAdapter auxiliaryComparableAdapter;
   public static final RenderChoiceProperty[] ENUM_VALUES = values();
   public static final RenderChoiceProperty[] VALUES = new RenderChoiceProperty[6];
   public static final Map map = Maps.newHashMap();
   public final SecondaryOptionType secondaryOptionType;
   public final int count2;
   public final int count;
   public static final RenderChoiceProperty[] HORIZONTALS = new RenderChoiceProperty[4];
   public final int count3;

   public int getCount2() {
      return this.count2;
   }

   public MovementLimitProperty getMovementLimitProperty() {
      return this.movementLimitProperty;
   }

   public static RenderChoiceProperty resolveRenderChoiceProperty(int count4) {
      return HORIZONTALS[InternalFloatArrayEntry.computeCount(count4 % HORIZONTALS.length)];
   }

   public int getCount() {
      return this.secondaryOptionType == SecondaryOptionType.Z ? this.movementLimitProperty.getCount() : 0;
   }

   public static RenderChoiceProperty resolveRenderChoiceProperty(double offset) {
      return resolveRenderChoiceProperty(InternalFloatArrayEntry.computeCount(offset / 90.0 + 0.5) & 3);
   }

   public static RenderChoiceProperty resolveRenderChoiceProperty(Random random) {
      return values()[random.nextInt(values().length)];
   }

   public SecondaryOptionType getSecondaryOptionType() {
      return this.secondaryOptionType;
   }

   private RenderChoiceProperty getRenderChoiceProperty() {
      switch (DampenerEvaluator.countArray3[this.ordinal()]) {
         case 2:
            return DOWN;
         case 3:
         default:
            throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
         case 4:
            return UP;
         case 5:
            return EAST;
         case 6:
            return WEST;
      }
   }

   public RenderChoiceProperty resolveRenderChoiceProperty(char character, int count4, char character2) {
      switch (DampenerEvaluator.countArray3[this.ordinal()]) {
         case 1:
            return WEST;
         case 2:
            return NORTH;
         case 3:
            return EAST;
         case 4:
            return SOUTH;
         default:
            throw new IllegalStateException("Unable to get CCW facing of " + this);
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   public RenderChoiceProperty resolveRenderChoiceProperty(SecondaryOptionType secondaryOptionType2) {
      switch (DampenerEvaluator.countArray2[secondaryOptionType2.ordinal()]) {
         case 1:
            if (this != WEST && this != EAST) {
               return this.getRenderChoiceProperty2();
            }

            return this;
         case 2:
            if (this != UP && this != DOWN) {
               return this.getRenderChoicePropertyAsRenderChoiceProp();
            }

            return this;
         case 3:
            if (this != NORTH && this != SOUTH) {
               return this.getRenderChoiceProperty();
            }

            return this;
         default:
            throw new IllegalStateException("Unable to get CW facing for axis " + secondaryOptionType2);
      }
   }

   public RenderChoiceProperty getRenderChoicePropertyAsRenderChoiceProp() {
      switch (DampenerEvaluator.countArray3[this.ordinal()]) {
         case 1:
            return EAST;
         case 2:
            return SOUTH;
         case 3:
            return WEST;
         case 4:
            return NORTH;
         default:
            throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
      }
   }

   public static RenderChoiceProperty resolveRenderChoicePropertyByIndex(int count4) {
      return VALUES[InternalFloatArrayEntry.computeCount(count4 % VALUES.length)];
   }

   public static RenderChoiceProperty resolveRenderChoiceProperty(String text) {
      return text == null ? null : (RenderChoiceProperty)map.get(text.toLowerCase());
   }

   public int getCount4() {
      return this.secondaryOptionType == SecondaryOptionType.X ? this.movementLimitProperty.getCount() : 0;
   }

   public static RenderChoiceProperty resolveRenderChoiceProperty(MovementLimitProperty movementLimitProperty2, SecondaryOptionType secondaryOptionType2) {
      for (RenderChoiceProperty renderChoiceProperty : values()) {
         if (renderChoiceProperty.getMovementLimitProperty() == movementLimitProperty2 && renderChoiceProperty.getSecondaryOptionType() == secondaryOptionType2) {
            return renderChoiceProperty;
         }
      }

      throw new IllegalArgumentException("No such direction: " + movementLimitProperty2 + " " + secondaryOptionType2);
   }

   public AuxiliaryComparableAdapter getAuxiliaryComparableAdapter() {
      return this.auxiliaryComparableAdapter;
   }

   public static RenderChoiceProperty resolveRenderChoiceProperty(float posX, float posY, float posZ) {
      RenderChoiceProperty renderChoiceProperty = NORTH;
      float factor = Float.MIN_VALUE;

      for (RenderChoiceProperty renderChoiceProperty2 : values()) {
         float factor2 = posX * (float)renderChoiceProperty2.auxiliaryComparableAdapter.getCount()
            + posY * (float)renderChoiceProperty2.auxiliaryComparableAdapter.getCount2()
            + posZ * (float)renderChoiceProperty2.auxiliaryComparableAdapter.getCount3();
         if (factor2 > factor) {
            factor = factor2;
            renderChoiceProperty = renderChoiceProperty2;
         }
      }

      return renderChoiceProperty;
   }

   private RenderChoiceProperty getRenderChoiceProperty2() {
      switch (DampenerEvaluator.countArray3[this.ordinal()]) {
         case 1:
            return DOWN;
         case 2:
         case 4:
         default:
            throw new IllegalStateException("Unable to get X-rotated facing of " + this);
         case 3:
            return UP;
         case 5:
            return NORTH;
         case 6:
            return SOUTH;
      }
   }

   public int getCount3() {
      return this.secondaryOptionType == SecondaryOptionType.Y ? this.movementLimitProperty.getCount() : 0;
   }

   public RenderChoiceProperty getRenderChoiceProperty3() {
      return resolveRenderChoicePropertyByIndex(this.count3);
   }

   public String getLabelTextAsText() {
      return this.labelText;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   private RenderChoiceProperty(
      int count4, int count5, int count6, String text, MovementLimitProperty movementLimitProperty2, SecondaryOptionType secondaryOptionType2, AuxiliaryComparableAdapter auxiliaryComparableAdapter2
   ) {
      this.count2 = count4;
      this.count = count6;
      this.count3 = count5;
      this.labelText = text;
      this.secondaryOptionType = secondaryOptionType2;
      this.movementLimitProperty = movementLimitProperty2;
      this.auxiliaryComparableAdapter = auxiliaryComparableAdapter2;
   }

   static {
      for (RenderChoiceProperty renderChoiceProperty : values()) {
         VALUES[renderChoiceProperty.count2] = renderChoiceProperty;
         if (renderChoiceProperty.getSecondaryOptionType().isActive()) {
            HORIZONTALS[renderChoiceProperty.count] = renderChoiceProperty;
         }

         map.put(renderChoiceProperty.getLabelTextAsText().toLowerCase(), renderChoiceProperty);
      }
   }

   @Override
   public String toString() {
      return this.labelText;
   }
}
