package cc.baritone.utils;

import java.util.stream.Stream;

public class EnumBreadthFirstScheduler implements BreadthFirstScheduler {
   public final int count;
   public final String labelText;
   public final String labelText2;

   @Override
   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public Enum resolveEnum(Class clazz) throws TertiaryClientManager {
      return (Enum)Stream.of(clazz.getEnumConstants()).filter(item -> this.matchesEnum((Enum)item)).findFirst().orElseThrow(() -> this.getSimpleName(clazz));
   }

   @Override
   public boolean matchesClass(Class clazz) {
      try {
         this.resolveTarget(clazz);
         return true;
      } catch (Throwable throwable) {
         return false;
      }
   }

   private boolean matchesEnum(Enum enumItem) {
      return enumItem.name().equalsIgnoreCase(this.labelText);
   }

   @Override
   public Object resolveTarget(Class clazz) throws TertiaryClientManager {
      return RuntimeComponentHandler.INSTANCE.resolveTarget(clazz, this);
   }

   @Override
   public Object resolveTarget(Class clazz, Class clazz2, Object entry) throws TertiaryClientManager {
      return RuntimeComponentHandler.INSTANCE.resolveTarget(clazz, clazz2, this, entry);
   }

   public EnumBreadthFirstScheduler(int count2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   private TertiaryClientManager getSimpleName(Class clazz) {
      return new TertiaryClientManager(this, clazz.getSimpleName());
   }

   @Override
   public int getCount() {
      return this.count;
   }

   @Override
   public boolean matchesClass(Class clazz, Class clazz2, Object entry) {
      try {
         this.resolveTarget(clazz, clazz2, entry);
         return true;
      } catch (Throwable throwable) {
         return false;
      }
   }
}
