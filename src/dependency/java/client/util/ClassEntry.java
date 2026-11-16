package client.util;

import client.event.PreRenderDispatcher;
import java.util.ArrayList;

public abstract class ClassEntry {
   public Class memberRef;
   public String labelText;
   public float factor;
   public String[] stringArray;

   public String getLabelText() {
      return this.labelText;
   }

   public abstract String[] getTextArray();

   public String[] getStringArray() {
      return this.stringArray;
   }

   public float getFactor() {
      return this.factor;
   }

   public abstract PassiveFloatEntry getPassiveFloatEntry();

   public abstract PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor2);

   public Class getMemberRef() {
      return this.memberRef;
   }

   public BoxesManager[] resolveBoxesManagerArray(PassiveFloatEntry passiveFloatEntry) {
      String[] textArray = this.getTextArray();
      ArrayList list = new ArrayList();

      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         BoxesManager boxesManager = this.resolveBoxesManager(passiveFloatEntry, text);
         if (boxesManager != null) {
            list.add(boxesManager);
         }
      }

      return ((BoxesManager[])list.toArray(new BoxesManager[list.size()]));
   }

   public abstract BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text);

   public ClassEntry(Class clazz, String text, float factor2) {
      this.memberRef = clazz;
      this.labelText = text;
      this.factor = factor2;
   }

   public ClassEntry(Class clazz, String text, float factor2, String[] textArray) {
      this.memberRef = clazz;
      this.labelText = text;
      this.factor = factor2;
      this.stringArray = textArray;
   }
}
