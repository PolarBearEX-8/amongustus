package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Map;
import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.UUID;
import net.augustus.ui.account.AccountEntry;
import net.minecraft.client.Minecraft;
import net.raphimc.minecraftauth.step.java.StepPlayerCertificates.PlayerCertificates;

public class Session {
   private final String username;
   private final String playerID;
   private final String token;
   private final Session.Type sessionType;
   private final PlayerCertificates playerCertificates;
   private final AccountEntry accountEntry;

   public String getToken() {
      return this.token;
   }

   public String getSessionID() {
      return "token:" + this.token + ":" + this.playerID;
   }

   public Session(String str, String str2, String str3, String str4, PlayerCertificates playerCertificates2, AccountEntry accountEntry2) {
      this.username = str;
      this.playerID = str2;
      this.token = str3;
      this.sessionType = Session.Type.setSessionType(str4);
      this.playerCertificates = playerCertificates2;
      this.accountEntry = accountEntry2;
   }

   public String getPlayerID() {
      return this.playerID;
   }

   public String getUsername() {
      return this.username;
   }

   public GameProfile getProfile() {
      try {
         UUID uuid = UUIDTypeAdapter.fromString(this.getPlayerID());
         return new GameProfile(uuid, this.getUsername());
      } catch (IllegalArgumentException e) {
         return new GameProfile((UUID)null, this.getUsername());
      }
   }

   public Session.Type getSessionType() {
      return this.sessionType;
   }

   public void updatePlayTime(boolean flag) {
      if (this.accountEntry != null) {
         if (flag
            && Minecraft.getMinecraft() != null
            && Minecraft.getMinecraft().getCurrentServerData() != null
            && Minecraft.getMinecraft().getCurrentServerData().serverIP != null
            && !Minecraft.getMinecraft().isSingleplayer()) {
            this.accountEntry.markUnbannedOnServer(Minecraft.getMinecraft().getCurrentServerData().serverIP);
         }

         if (flag && this.accountEntry.getSessionStartTime() <= 0L) {
            this.accountEntry.setSessionStartTime(System.currentTimeMillis());
         } else {
            if (!flag && this.accountEntry.getSessionStartTime() > 0L) {
               this.accountEntry.addPlayTime(System.currentTimeMillis() - this.accountEntry.getSessionStartTime());
               this.accountEntry.setSessionStartTime(-1L);
            }
         }
      }
   }

   public AccountEntry getAccountEntry() {
      return this.accountEntry;
   }



   public enum Type {
      LEGACY("legacy"),
      MOJANG("mojang");

   private static final Map SESSION_TYPES = Maps.newHashMap();
      private final String sessionType;
   static {
         for (Type type : values()) {
            SESSION_TYPES.put(type.sessionType, type);
         }
      }

      private Type(String str) {
         this.sessionType = str;
      }

      public static Type setSessionType(String str) {
         return (Type)SESSION_TYPES.get(str.toLowerCase());
      }
   }
}
