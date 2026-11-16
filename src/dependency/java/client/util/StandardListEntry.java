package client.util;

import client.setting.SharedOptionType;
import java.util.ArrayList;
import java.util.List;

public class StandardListEntry {
   public List list = new ArrayList();
   public NoneManager noneManager = this.resolveNoneManager("", SharedOptionType.NONE, true);

   public NoneManager resolveNoneManagerByText(String text, NoneManager noneManager2) {
      return this.resolveNoneManager(text, SharedOptionType.SHADOW, noneManager2);
   }

   public NoneManager resolveNoneManager(String text, NoneManager noneManager2) {
      return this.resolveNoneManager(text, SharedOptionType.GBUFFERS, noneManager2);
   }

   public String[] getTextArray() {
      String[] textArray = new String[this.list.size()];

      for (int index = 0; index < textArray.length; index++) {
         textArray[index] = ((NoneManager)this.list.get(index)).getLabelText();
      }

      return textArray;
   }

   public NoneManager getNoneManager() {
      return this.noneManager;
   }

   public NoneManager resolveNoneManager(String text) {
      return this.resolveNoneManager(text, SharedOptionType.DEFERRED, this.noneManager);
   }

   public int size() {
      return this.list.size();
   }

   private NoneManager resolveNoneManager(String text, SharedOptionType sharedOptionType, boolean flag) {
      int size = this.list.size();
      NoneManager noneManager2 = new NoneManager(size, text, sharedOptionType, flag);
      this.list.add(noneManager2);
      return noneManager2;
   }

   public NoneManager[] getNoneManagerArray() {
      return (NoneManager[])this.list.toArray(new NoneManager[this.list.size()]);
   }

   public NoneManager resolveNoneManager3(String text) {
      if (text == null) {
         return null;
      } else {
         for (int index = 0; index < this.list.size(); index++) {
            NoneManager noneManager2 = (NoneManager)this.list.get(index);
            if (noneManager2.getLabelText().equals(text)) {
               return noneManager2;
            }
         }

         return null;
      }
   }

   public NoneManager[] resolveNoneManagerArrayByText(String text, int count) {
      NoneManager[] noneManagerArray = new NoneManager[count];

      for (int index = 0; index < count; index++) {
         String text2 = index == 0 ? text : text + index;
         noneManagerArray[index] = this.resolveNoneManager2(text2);
      }

      return noneManagerArray;
   }

   public NoneManager[] resolveNoneManagerArray(NoneManager noneManager2, NoneManager noneManager3) {
      int count = noneManager2.getCount4();
      int count2 = noneManager3.getCount4();
      if (count > count2) {
         int count3 = count;
         count = count2;
         count2 = count3;
      }

      NoneManager[] noneManagerArray = new NoneManager[count2 - count + 1];

      for (int index = 0; index < noneManagerArray.length; index++) {
         noneManagerArray[index] = (NoneManager)this.list.get(count + index);
      }

      return noneManagerArray;
   }

   public NoneManager resolveNoneManager2(String text) {
      return this.resolveNoneManager(text, SharedOptionType.COMPOSITE, this.noneManager);
   }

   public NoneManager[] resolveNoneManagerArray(String text, int count) {
      NoneManager[] noneManagerArray = new NoneManager[count];

      for (int index = 0; index < count; index++) {
         String text2 = index == 0 ? text : text + index;
         noneManagerArray[index] = this.resolveNoneManager(text2);
      }

      return noneManagerArray;
   }

   public NoneManager resolveNoneManager(String text, SharedOptionType sharedOptionType, NoneManager noneManager2) {
      int size = this.list.size();
      NoneManager noneManager3 = new NoneManager(size, text, sharedOptionType, noneManager2);
      this.list.add(noneManager3);
      return noneManager3;
   }

   public NoneManager resolveNoneManagerByText(String text) {
      return this.resolveNoneManager(text, SharedOptionType.NONE, true);
   }

   @Override
   public String toString() {
      return this.list.toString();
   }
}
