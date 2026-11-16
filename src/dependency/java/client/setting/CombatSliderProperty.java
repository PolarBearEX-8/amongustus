package client.setting;

import client.util.AngleReader;
import client.util.GeometrySorter;
import net.minecraft.util.EnumFacing;

public enum CombatSliderProperty {
   DOWN(
      new AngleReader[]{
         new AngleReader(GeometrySorter.count3, GeometrySorter.count, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count3, GeometrySorter.count, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count, GeometrySorter.count4, null)
      }
   ),
   UP(
      new AngleReader[]{
         new AngleReader(GeometrySorter.count3, GeometrySorter.count6, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count3, GeometrySorter.count6, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count6, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count6, GeometrySorter.count5, null)
      }
   ),
   NORTH(
      new AngleReader[]{
         new AngleReader(GeometrySorter.count2, GeometrySorter.count6, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count3, GeometrySorter.count, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count3, GeometrySorter.count6, GeometrySorter.count5, null)
      }
   ),
   SOUTH(
      new AngleReader[]{
         new AngleReader(GeometrySorter.count3, GeometrySorter.count6, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count3, GeometrySorter.count, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count6, GeometrySorter.count4, null)
      }
   ),
   WEST(
      new AngleReader[]{
         new AngleReader(GeometrySorter.count3, GeometrySorter.count6, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count3, GeometrySorter.count, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count3, GeometrySorter.count, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count3, GeometrySorter.count6, GeometrySorter.count4, null)
      }
   ),
   EAST(
      new AngleReader[]{
         new AngleReader(GeometrySorter.count2, GeometrySorter.count6, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count, GeometrySorter.count4, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count, GeometrySorter.count5, null),
         new AngleReader(GeometrySorter.count2, GeometrySorter.count6, GeometrySorter.count5, null)
      }
   );
   public static final CombatSliderProperty[] ENUM_VALUES2 = new CombatSliderProperty[6];
   public final AngleReader[] angleReaderArray;
   public static final CombatSliderProperty[] ENUM_VALUES = values();

   public static CombatSliderProperty resolveCombatSliderProperty(EnumFacing enumFacing) {
      return ENUM_VALUES2[enumFacing.getIndexOffset1()];
   }

   public AngleReader resolveAngleReader(int count) {
      return this.angleReaderArray[count];
   }

   private CombatSliderProperty(AngleReader[] angleReaderArray2) {
      this.angleReaderArray = angleReaderArray2;
   }

   static {
      ENUM_VALUES2[GeometrySorter.count] = DOWN;
      ENUM_VALUES2[GeometrySorter.count6] = UP;
      ENUM_VALUES2[GeometrySorter.count5] = NORTH;
      ENUM_VALUES2[GeometrySorter.count4] = SOUTH;
      ENUM_VALUES2[GeometrySorter.count3] = WEST;
      ENUM_VALUES2[GeometrySorter.count2] = EAST;
   }
}
