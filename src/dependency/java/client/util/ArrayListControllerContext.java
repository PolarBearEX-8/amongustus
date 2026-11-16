package client.util;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ArrayListControllerContext {
   public boolean enabled;
   public String labelText;
   public String labelText2;
   public ArrayList arrayList = new ArrayList();
   public String labelText3;
   public Runnable runnable;

   public ArrayListControllerContext(String text, String text2) {
      this.labelText3 = text;
      this.labelText = text2;
   }

   public ArrayListControllerContext accept(Consumer consumer, boolean flag) {
      consumer.accept(this.arrayList);
      this.enabled = flag;
      return this;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public ArrayListControllerContext withLabelText2(String text) {
      this.labelText2 = text;
      return this;
   }

   public void run() {
      this.runnable.run();
   }

   public String getLabelText2() {
      return this.labelText2 == null ? this.labelText3 : this.labelText2;
   }

   public ArrayListControllerContext withRunnable(Runnable task) {
      this.runnable = task;
      return this;
   }

   public ArrayList getArrayList() {
      return this.arrayList;
   }

   public ArrayListControllerContext(String text, String text2, ArrayList list, int count) {
      this.labelText3 = text;
      this.labelText = text2;
      this.arrayList = list;
   }

   public boolean isActive() {
      return this.runnable != null;
   }
}
