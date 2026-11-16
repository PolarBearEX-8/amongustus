package cc.baritone.utils;

import java.util.List;
import java.util.StringTokenizer;

public class ControllerDelegate {
   public String labelText = ",";
   public List list = new java.util.ArrayList();

   public String[] toArray() {
      return (String[])this.list.toArray();
   }

   public ControllerDelegate(String text, String text2) {
      this.labelText = text;
      this.applyText(text, text2, this.list);
   }

   @Override
   public String toString() {
      return this.resolveText(this.labelText);
   }

   public List getList() {
      return this.list;
   }

   public void applyText(String text, String text2, List list2, boolean flag) {
      if (text != null && text2 != null) {
         if (flag) {
            StringTokenizer stringTokenizer = new StringTokenizer(text, text2);

            while (stringTokenizer.hasMoreTokens()) {
               list2.add(stringTokenizer.nextToken().trim());
            }
         } else {
            this.applyText(text, text2, list2);
         }
      }
   }

   public String resolveText(String text) {
      StringBuffer builder = new StringBuffer();

      for (int index = 0; index < this.list.size(); index++) {
         if (index == 0) {
            builder.append(this.list.get(index));
         } else {
            builder.append(text);
            builder.append(this.list.get(index));
         }
      }

      return builder.toString();
   }

   public ControllerDelegate(String text, String text2, boolean flag) {
      this.applyText(text, text2, this.list, flag);
   }

   public int size() {
      return this.list.size();
   }

   public void applyText(int count, String text) {
      if (text != null) {
         this.list.add(count, text.trim());
      }
   }

   public void applyText(String text, String text2, List list2) {
      if (text != null && text2 != null) {
         int count = 0;

         int count2;
         do {
            count2 = count;
            int count3 = text.indexOf(text2, count);
            if (count3 == -1) {
               break;
            }

            list2.add(text.substring(count, count3).trim());
            count = count3 + text2.length();
         } while (count != -1);

         list2.add(text.substring(count2).trim());
      }
   }

   public ControllerDelegate() {
   }

   public void clear2() {
      this.list.clear();
   }

   public void applyText(String text) {
      this.applyText(text, this.labelText, this.list);
   }

   public void applyTextByText(String text) {
      if (text != null) {
         this.list.add(text.trim());
      }
   }

   public void clear() {
      this.labelText = ",";
      this.list.clear();
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public String get(int count) {
      return (String)this.list.get(count);
   }

   public void applyText(String text, String text2, boolean flag) {
      this.applyText(text, text2, this.list, flag);
   }

   public void addAll(ControllerDelegate controllerDelegate) {
      this.list.addAll(controllerDelegate.list);
   }

   public ControllerDelegate(String text) {
      this.applyText(text, this.labelText, this.list);
   }

   public void applyText(String text, String text2) {
      this.applyText(text, text2, this.list);
   }
}
