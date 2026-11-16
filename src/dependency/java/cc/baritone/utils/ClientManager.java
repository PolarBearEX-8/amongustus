package cc.baritone.utils;

import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.URL;

public final class ClientManager {
   public ExtendedOptionType resolveExtendedOptionType(ExtendedOptionType extendedOptionType) {
      try {
         String labelText = extendedOptionType.getLabelText2();
         YggdrasilUserAuthentication.BASE_URL = labelText;
         YggdrasilUserAuthentication.ROUTE_AUTHENTICATE = new URL(labelText + "authenticate");
         YggdrasilUserAuthentication.ROUTE_INVALIDATE = new URL(labelText + "invalidate");
         YggdrasilUserAuthentication.ROUTE_REFRESH = new URL(labelText + "refresh");
         YggdrasilUserAuthentication.ROUTE_VALIDATE = new URL(labelText + "validate");
         YggdrasilUserAuthentication.ROUTE_SIGNOUT = new URL(labelText + "signout");
         String labelText2 = extendedOptionType.getLabelText();
         YggdrasilMinecraftSessionService.BASE_URL = labelText2 + "session/minecraft/";
         YggdrasilMinecraftSessionService.JOIN_URL = new URL(labelText2 + "session/minecraft/join");
         YggdrasilMinecraftSessionService.CHECK_URL = new URL(labelText2 + "session/minecraft/hasJoined");
         return extendedOptionType;
      } catch (Exception exception) {
         exception.printStackTrace();
         return ExtendedOptionType.MOJANG;
      }
   }

   public ClientManager() {
      YggdrasilMinecraftSessionService.WHITELISTED_DOMAINS = new String[]{".minecraft.net", ".mojang.com", ".thealtening.com"};
   }
}
