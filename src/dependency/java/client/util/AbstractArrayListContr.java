package client.util;

import java.util.Iterator;

public abstract class AbstractArrayListContr extends AbstractArrayListController {
   public String labelText2;
   public boolean enabled3;
   public boolean enabled4;

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public void applyIndexByIndex(int count, int count2, int count3) {
      if (this.isEnabled4()) {
         Iterator iterator = super.arrayList.iterator();

         while (iterator.hasNext()) {
            ((AbstractArrayListContr)iterator.next()).applyIndexByIndex(count, count2, count3);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (this.isEnabled4()) {
         Iterator iterator = super.arrayList.iterator();

         while (iterator.hasNext()) {
            ((AbstractArrayListController)iterator.next()).applyIndex(count, count2, count3);
         }
      }

      if (super.enabled2 && super.arrayListController != null) {
         boolean flag = count3 == 1;
         super.arrayListController.applyAbstractArrayListControlle(flag, this);
      }
   }

   @Override
   public void applyChar(char character, int count) {
      if (this.isEnabled4()) {
         Iterator iterator = super.arrayList.iterator();

         while (iterator.hasNext()) {
            ((AbstractArrayListContr)iterator.next()).applyChar(character, count);
         }
      }
   }

   public AbstractArrayListContr(String text) {
      super(text);
   }

   public AbstractArrayListContr withEnabled3(boolean flag) {
      this.enabled3 = flag;
      return this;
   }

   public void setEnabledByFlag(boolean enabledByFlag) {
      super.enabled = enabledByFlag;
      if (this.enabled4 && !super.enabled) {
         this.enabled4 = false;
      }
   }

   public boolean isActive() {
      return this.isEmpty() && super.enabled;
   }

   public AbstractArrayListContr withLabelText2(String text) {
      this.labelText2 = text;
      return this;
   }

   public int getCount() {
      return 40;
   }

   public AbstractArrayListContr(String text, boolean flag) {
      super(text, flag);
   }
}
