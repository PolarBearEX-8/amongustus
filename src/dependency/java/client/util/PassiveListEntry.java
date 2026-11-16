package client.util;

import client.setting.MovementDelayProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.item.IronIngot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector3f;

public class PassiveListEntry {
   public static final List list = Lists.newArrayList(new String[]{"layer0", "layer1", "layer2", "layer3", "layer4"});

   public NativeLoggerEntry resolveNativeLoggerEntry(ListenerAdapter27 listenerAdapter, NativeLoggerEntry nativeLoggerEntry) {
      HashMap map = Maps.newHashMap();
      ArrayList list2 = Lists.newArrayList();

      for (int index = 0; index < list.size(); index++) {
         String text = (String)list.get(index);
         if (!nativeLoggerEntry.matchesText(text)) {
            break;
         }

         String text2 = nativeLoggerEntry.resolveText(text);
         map.put(text, text2);
         ServiceStringEntry serviceStringEntry = listenerAdapter.get(new ResourceLocation(text2).toString());
         list2.addAll(this.resolveList(index, text, serviceStringEntry));
      }

      if (list2.isEmpty()) {
         return null;
      } else {
         map.put("particle", nativeLoggerEntry.matchesText("particle") ? nativeLoggerEntry.resolveText("particle") : (String)map.get("layer0"));
         return new NativeLoggerEntry(list2, map, false, false, nativeLoggerEntry.getSpecialItemEntry());
      }
   }

   private List resolveList(ServiceStringEntry serviceStringEntry) {
      int count = serviceStringEntry.getCount();
      int count2 = serviceStringEntry.getCount2();
      ArrayList list2 = Lists.newArrayList();

      for (int index = 0; index < serviceStringEntry.size(); index++) {
         int[] countArray = serviceStringEntry.get(index)[0];

         for (int index2 = 0; index2 < count2; index2++) {
            for (int index3 = 0; index3 < count; index3++) {
               boolean flag = !this.matchesIndexArray(countArray, index3, index2, count, count2);
               this.applyMovementDelayProperty(MovementDelayProperty.UP, list2, countArray, index3, index2, count, count2, flag);
               this.applyMovementDelayProperty(MovementDelayProperty.DOWN, list2, countArray, index3, index2, count, count2, flag);
               this.applyMovementDelayProperty(MovementDelayProperty.LEFT, list2, countArray, index3, index2, count, count2, flag);
               this.applyMovementDelayProperty(MovementDelayProperty.RIGHT, list2, countArray, index3, index2, count, count2, flag);
            }
         }
      }

      return list2;
   }

   private boolean matchesIndexArray(int[] countArray, int count, int count2, int count3, int count4) {
      return count >= 0 && count2 >= 0 && count < count3 && count2 < count4 ? (countArray[count2 * count3 + count] >> 24 & 0xFF) == 0 : true;
   }

   private void applyMovementDelayProperty(MovementDelayProperty movementDelayProperty, List list2, int[] countArray, int count, int count2, int count3, int count4, boolean flag) {
      if (this.matchesIndexArray(countArray, count + movementDelayProperty.getCount(), count2 + movementDelayProperty.getCount2(), count3, count4) && flag) {
         this.applyList(list2, movementDelayProperty, count, count2);
      }
   }

   private void applyList(List list2, MovementDelayProperty movementDelayProperty, int count, int count2) {
      IronIngot ironIngot = null;

      for (IronIngot ironIngot2 : (Iterable<IronIngot>)(Iterable<?>)list2) {
         if (ironIngot2.getMovementDelayProperty() == movementDelayProperty) {
            int count3 = MovementDelayProperty.matchesMovementDelayProperty(movementDelayProperty) ? count2 : count;
            if (ironIngot2.getCount() == count3) {
               ironIngot = ironIngot2;
               break;
            }
         }
      }

      int count4 = MovementDelayProperty.matchesMovementDelayProperty(movementDelayProperty) ? count2 : count;
      int count5 = MovementDelayProperty.matchesMovementDelayProperty(movementDelayProperty) ? count : count2;
      if (ironIngot == null) {
         list2.add(new IronIngot(movementDelayProperty, count5, count4));
      } else {
         ironIngot.applyIndex(count5);
      }
   }

   private List resolveList(int count, String text, ServiceStringEntry serviceStringEntry) {
      HashMap map = Maps.newHashMap();
      map.put(EnumFacing.SOUTH, new EnumFacingHolder((EnumFacing)null, count, text, new TertiaryFloatArrayEntry(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0)));
      map.put(EnumFacing.NORTH, new EnumFacingHolder((EnumFacing)null, count, text, new TertiaryFloatArrayEntry(new float[]{16.0F, 0.0F, 0.0F, 16.0F}, 0)));
      ArrayList list2 = Lists.newArrayList();
      list2.add(new Vector3fHolder(new Vector3f(0.0F, 0.0F, 7.5F), new Vector3f(16.0F, 16.0F, 8.5F), map, (Vector3fEntry)null, true));
      list2.addAll(this.resolveList(serviceStringEntry, text, count));
      return list2;
   }

   private List resolveList(ServiceStringEntry serviceStringEntry, String text, int count) {
      float count2 = (float)serviceStringEntry.getCount();
      float count3 = (float)serviceStringEntry.getCount2();
      ArrayList list2 = Lists.newArrayList();

      for (IronIngot ironIngot : (Iterable<IronIngot>)(Iterable<?>)this.resolveList(serviceStringEntry)) {
         float factor = 0.0F;
         float factor2 = 0.0F;
         float factor3 = 0.0F;
         float factor4 = 0.0F;
         float factor5 = 0.0F;
         float factor6 = 0.0F;
         float factor7 = 0.0F;
         float factor8 = 0.0F;
         float factor9 = 0.0F;
         float factor10 = 0.0F;
         float count4 = (float)ironIngot.getCount2();
         float count5 = (float)ironIngot.getCount3();
         float count6 = (float)ironIngot.getCount();
         MovementDelayProperty movementDelayProperty = ironIngot.getMovementDelayProperty();
         switch (GlobalIntArrayEntry.countArray[movementDelayProperty.ordinal()]) {
            case 1:
               factor5 = count4;
               factor = count4;
               factor3 = factor6 = count5 + 1.0F;
               factor7 = count6;
               factor2 = count6;
               factor8 = count6;
               factor4 = count6;
               factor9 = 16.0F / count2;
               factor10 = 16.0F / (count3 - 1.0F);
               break;
            case 2:
               factor8 = count6;
               factor7 = count6;
               factor5 = count4;
               factor = count4;
               factor3 = factor6 = count5 + 1.0F;
               factor2 = count6 + 1.0F;
               factor4 = count6 + 1.0F;
               factor9 = 16.0F / count2;
               factor10 = 16.0F / (count3 - 1.0F);
               break;
            case 3:
               factor5 = count6;
               factor = count6;
               factor6 = count6;
               factor3 = count6;
               factor8 = count4;
               factor2 = count4;
               factor4 = factor7 = count5 + 1.0F;
               factor9 = 16.0F / (count2 - 1.0F);
               factor10 = 16.0F / count3;
               break;
            case 4:
               factor6 = count6;
               factor5 = count6;
               factor = count6 + 1.0F;
               factor3 = count6 + 1.0F;
               factor8 = count4;
               factor2 = count4;
               factor4 = factor7 = count5 + 1.0F;
               factor9 = 16.0F / (count2 - 1.0F);
               factor10 = 16.0F / count3;
         }

         float factor11 = 16.0F / count2;
         float factor12 = 16.0F / count3;
         float factor13 = factor * factor11;
         float factor14 = factor3 * factor11;
         float factor15 = factor2 * factor12;
         float factor16 = factor4 * factor12;
         float factor17 = 16.0F - factor15;
         float factor18 = 16.0F - factor16;
         float factor19 = factor5 * factor9;
         float factor20 = factor6 * factor9;
         float factor21 = factor7 * factor10;
         float factor22 = factor8 * factor10;
         HashMap map = Maps.newHashMap();
         map.put(
            movementDelayProperty.getEnumFacing(), new EnumFacingHolder((EnumFacing)null, count, text, new TertiaryFloatArrayEntry(new float[]{factor19, factor21, factor20, factor22}, 0))
         );
         switch (GlobalIntArrayEntry.countArray[movementDelayProperty.ordinal()]) {
            case 1:
               list2.add(new Vector3fHolder(new Vector3f(factor13, factor17, 7.5F), new Vector3f(factor14, factor17, 8.5F), map, (Vector3fEntry)null, true));
               break;
            case 2:
               list2.add(new Vector3fHolder(new Vector3f(factor13, factor18, 7.5F), new Vector3f(factor14, factor18, 8.5F), map, (Vector3fEntry)null, true));
               break;
            case 3:
               list2.add(new Vector3fHolder(new Vector3f(factor13, factor17, 7.5F), new Vector3f(factor13, factor18, 8.5F), map, (Vector3fEntry)null, true));
               break;
            case 4:
               list2.add(new Vector3fHolder(new Vector3f(factor14, factor17, 7.5F), new Vector3f(factor14, factor18, 8.5F), map, (Vector3fEntry)null, true));
         }
      }

      return list2;
   }
}
