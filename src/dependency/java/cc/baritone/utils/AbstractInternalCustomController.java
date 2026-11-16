package cc.baritone.utils;

import client.util.DampenerProvider;

public abstract class AbstractInternalCustomController {
   public InternalCustom resolveInternalCustom(ClassLoader classLoader, String text) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
      Class clazz;
      if (classLoader == null) {
         clazz = Class.forName(DampenerProvider.a(text));
      } else {
         clazz = classLoader.loadClass(text);
      }

      return (InternalCustom)clazz.newInstance();
   }

   public abstract InternalCustom getInternalCustom() throws AuxiliaryExceptionAdapter;
}
