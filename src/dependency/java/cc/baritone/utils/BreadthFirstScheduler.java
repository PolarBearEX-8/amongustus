package cc.baritone.utils;

public interface BreadthFirstScheduler {
   Object resolveTarget(Class clazz) throws TertiaryClientManager;

   String getLabelText2();

   boolean matchesClass(Class clazz);

   Enum resolveEnum(Class clazz) throws TertiaryClientManager;

   boolean matchesClass(Class clazz, Class clazz2, Object entry);

   Object resolveTarget(Class clazz, Class clazz2, Object entry) throws TertiaryClientManager;

   String getLabelText();

   int getCount();
}
