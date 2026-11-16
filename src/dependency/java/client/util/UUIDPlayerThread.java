package client.util;

import client.setting.CombatProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import java.math.BigInteger;
import java.util.UUID;

public class UUIDPlayerThread extends Thread {
   public final ListenerAdapter3 listenerAdapter3;

   @Override
   public void run() {
      GameProfile gameProfile = ListenerAdapter3.resolveGameProfile(this.listenerAdapter3);

      try {
         String text = new BigInteger(
               SharedLoggerEntry.resolveByteArray(
                  ListenerAdapter3.resolveText(this.listenerAdapter3),
                  ListenerAdapter3.resolveVanillaRunnable(this.listenerAdapter3).getKeyPair().getPublic(),
                  ListenerAdapter3.resolveSecretKey(this.listenerAdapter3)
               )
            )
            .toString(16);
         ListenerAdapter3.resolveGameProfile(
            this.listenerAdapter3,
            ListenerAdapter3.resolveVanillaRunnable(this.listenerAdapter3)
               .getMinecraftSessionService()
               .hasJoinedServer(new GameProfile((UUID)null, gameProfile.getName()), text)
         );
         if (ListenerAdapter3.resolveGameProfile(this.listenerAdapter3) != null) {
            ListenerAdapter3.getLogger()
               .info(
                  "UUID of player "
                     + ListenerAdapter3.resolveGameProfile(this.listenerAdapter3).getName()
                     + " is "
                     + ListenerAdapter3.resolveGameProfile(this.listenerAdapter3).getId()
               );
            ListenerAdapter3.resolveCombatProperty(this.listenerAdapter3, CombatProperty.READY_TO_ACCEPT);
         } else if (ListenerAdapter3.resolveVanillaRunnable(this.listenerAdapter3).isActive10()) {
            ListenerAdapter3.getLogger().warn("Failed to verify username but will let them in anyway!");
            ListenerAdapter3.resolveGameProfile(this.listenerAdapter3, this.listenerAdapter3.resolveGameProfile(gameProfile));
            ListenerAdapter3.resolveCombatProperty(this.listenerAdapter3, CombatProperty.READY_TO_ACCEPT);
         } else {
            this.listenerAdapter3.applyText("Failed to verify username!");
            ListenerAdapter3.getLogger()
               .error("Username '" + ListenerAdapter3.resolveGameProfile(this.listenerAdapter3).getName() + "' tried to join with an invalid session");
         }
      } catch (AuthenticationUnavailableException authenticationUnavailableException) {
         if (ListenerAdapter3.resolveVanillaRunnable(this.listenerAdapter3).isActive10()) {
            ListenerAdapter3.getLogger().warn("Authentication servers are down but will let them in anyway!");
            ListenerAdapter3.resolveGameProfile(this.listenerAdapter3, this.listenerAdapter3.resolveGameProfile(gameProfile));
            ListenerAdapter3.resolveCombatProperty(this.listenerAdapter3, CombatProperty.READY_TO_ACCEPT);
         } else {
            this.listenerAdapter3.applyText("Authentication servers are down. Please try again later, sorry!");
            ListenerAdapter3.getLogger().error("Couldn't verify username because servers are unavailable");
         }
      }
   }

   public UUIDPlayerThread(ListenerAdapter3 listenerAdapter, String text) {
      super(text);
      this.listenerAdapter3 = listenerAdapter;
   }
}
