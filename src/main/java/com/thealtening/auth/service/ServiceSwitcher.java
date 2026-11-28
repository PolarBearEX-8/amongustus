package com.thealtening.auth.service;

import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

public final class ServiceSwitcher {

   private static void setStaticField(Class<?> targetClass, String fieldName, Object value) throws Exception {
      Field field = targetClass.getDeclaredField(fieldName);
      field.setAccessible(true);
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
      field.set(null, value);
   }

   public ServiceSwitcher() {
      try {
         setStaticField(
            YggdrasilMinecraftSessionService.class,
            "WHITELISTED_DOMAINS",
            new String[]{".minecraft.net", ".mojang.com", ".thealtening.com"}
         );
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public AlteningServiceType switchToService(AlteningServiceType alteningServiceType) {
      // HARDENED: reflective rewrite of Mojang auth/session URLs disabled.
      return AlteningServiceType.MOJANG;
   }
}
