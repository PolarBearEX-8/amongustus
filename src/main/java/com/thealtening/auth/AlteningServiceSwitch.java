package com.thealtening.auth;


import com.thealtening.auth.service.AlteningServiceType;
public class AlteningServiceSwitch {
   public static final int[] SWITCH_MAP = new int[AlteningServiceType.values().length];

   static {
      try {
         SWITCH_MAP[AlteningServiceType.MOJANG.ordinal()] = 1;
      } catch (NoSuchFieldError e) {
      }

      try {
         SWITCH_MAP[AlteningServiceType.THEALTENING.ordinal()] = 2;
      } catch (NoSuchFieldError e2) {
      }
   }
}
