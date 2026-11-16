package client.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;

public class LengthManager {
   public static List resolveList(List list) {
      ArrayList list2 = new ArrayList();
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         LocalIntArrayEntry localIntArrayEntry = resolveLocalIntArrayEntry((LocalIntArrayEntry)iterator.next());
         list2.add(localIntArrayEntry);
      }

      return list2;
   }

   public static void applyIndexArray(int[] countArray, String text) {
      int count = countArray.length / 4;
      BuildManager.applyText4(text + "Length: " + countArray.length + ", step: " + count);

      for (int index = 0; index < 4; index++) {
         int count2 = index * count;
         float factor = Float.intBitsToFloat(countArray[count2 + 0]);
         float factor2 = Float.intBitsToFloat(countArray[count2 + 1]);
         float factor3 = Float.intBitsToFloat(countArray[count2 + 2]);
         int count3 = countArray[count2 + 3];
         float factor4 = Float.intBitsToFloat(countArray[count2 + 4]);
         float factor5 = Float.intBitsToFloat(countArray[count2 + 5]);
         BuildManager.applyText4(text + index + " xyz: " + factor + "," + factor2 + "," + factor3 + " col: " + count3 + " u,v: " + factor4 + "," + factor5);
      }
   }

   public static IBakedModel resolveIBakedModel(IBakedModel iBakedModel) {
      List list = resolveList(iBakedModel.getList());
      EnumFacing[] enumFacingArray = EnumFacing.VALUES;
      ArrayList list2 = new ArrayList();

      for (int index = 0; index < enumFacingArray.length; index++) {
         EnumFacing enumFacing = enumFacingArray[index];
         List list3 = resolveList(iBakedModel.resolveList(enumFacing));
         list2.add(list3);
      }

      return new ListenerWrapper(list, list2, iBakedModel.isEnabled(), iBakedModel.canExecute(), iBakedModel.getServiceStringEntry(), iBakedModel.getSpecialItemEntry());
   }

   public static LocalIntArrayEntry resolveLocalIntArrayEntry(LocalIntArrayEntry localIntArrayEntry) {
      return new LocalIntArrayEntry((int[])localIntArrayEntry.getIndexArray().clone(), localIntArrayEntry.getCount(), localIntArrayEntry.getEnumFacing(), localIntArrayEntry.getServiceStringEntry());
   }

   public static void applyText(String text, LocalIntArrayEntry localIntArrayEntry, String text2) {
      BuildManager.applyText4(
         text2
            + "Quad: "
            + localIntArrayEntry.getClass().getName()
            + ", type: "
            + text
            + ", face: "
            + localIntArrayEntry.getEnumFacing()
            + ", tint: "
            + localIntArrayEntry.getCount()
            + ", sprite: "
            + localIntArrayEntry.getServiceStringEntry()
      );
      applyIndexArray(localIntArrayEntry.getIndexArray(), "  " + text2);
   }

   private static void applyText(String text, List list, String text2) {
      for (LocalIntArrayEntry localIntArrayEntry : (Iterable<LocalIntArrayEntry>) (list)) {
         applyText(text, localIntArrayEntry, text2);
      }
   }

   public static void applyIBakedModel(IBakedModel iBakedModel) {
      if (iBakedModel != null) {
         BuildManager.applyText4(
            "Model: "
               + iBakedModel
               + ", ao: "
               + iBakedModel.isEnabled()
               + ", gui3d: "
               + iBakedModel.canExecute()
               + ", builtIn: "
               + iBakedModel.isActive()
               + ", particle: "
               + iBakedModel.getServiceStringEntry()
         );
         EnumFacing[] enumFacingArray = EnumFacing.VALUES;

         for (int index = 0; index < enumFacingArray.length; index++) {
            EnumFacing enumFacing = enumFacingArray[index];
            List list = iBakedModel.resolveList(enumFacing);
            applyText(enumFacing.getText(), list, "  ");
         }

         List list2 = iBakedModel.getList();
         applyText("General", list2, "  ");
      }
   }
}
