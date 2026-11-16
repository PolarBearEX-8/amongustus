package net.minecraft.util;

import client.setting.SessionLegacyType;
import client.util.AccountProfile;
import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.raphimc.minecraftauth.step.java.StepPlayerCertificates.PlayerCertificates;

public class Session {
   public final String text1;
   public final String text;
   public final String text2;
   public final SessionLegacyType sessionLegacyType;
   public final PlayerCertificates playerCertificates;
   public final AccountProfile keybindAnimationEntry;

   public String getTextAsText() {
      return "token:" + this.text2 + ":" + this.text;
   }

   public String getText2() {
      return this.text2;
   }

   public SessionLegacyType getSessionLegacyType() {
      return this.sessionLegacyType;
   }

   public String getText() {
      return this.text;
   }

   public AccountProfile getKeybindAnimationEntry() {
      return this.keybindAnimationEntry;
   }

   public String getText1() {
      return this.text1;
   }

   public void setEnabled(boolean enabled) {
      if (this.keybindAnimationEntry != null) {
         if (enabled
            && Minecraft.getMinecraft() != null
            && Minecraft.getMinecraft().getServerData() != null
            && Minecraft.getMinecraft().getServerData().text != null
            && !Minecraft.getMinecraft().isFullScreen()) {
            AccountProfile keybindAnimationEntry2 = this.keybindAnimationEntry;
            String text3 = Minecraft.getMinecraft().getServerData().text;
            keybindAnimationEntry2.applyText(text3);
         }

         if (enabled && this.keybindAnimationEntry.getTimestamp3() <= 0L) {
            AccountProfile keybindAnimationEntry3 = this.keybindAnimationEntry;
            long timestamp = System.currentTimeMillis();
            keybindAnimationEntry3.setTimestamp3(timestamp);
         } else {
            if (!enabled && this.keybindAnimationEntry.getTimestamp3() > 0L) {
               AccountProfile keybindAnimationEntry4 = this.keybindAnimationEntry;
               long value = System.currentTimeMillis() - this.keybindAnimationEntry.getTimestamp3();
               keybindAnimationEntry4.applyTime(value);
               this.keybindAnimationEntry.setTimestamp3(-1L);
            }
         }
      }
   }

   public GameProfile getGameProfile() {
      try {
         UUID uuid = UUIDTypeAdapter.fromString(this.getText());
         return new GameProfile(uuid, this.getText1());
      } catch (IllegalArgumentException illegalArgumentException) {
         return new GameProfile((UUID)null, this.getText1());
      }
   }

   public Session(String text3, String text4, String text5, String text6, PlayerCertificates playerCertificates2, AccountProfile accountProfile) {
      this.text1 = text3;
      this.text = text4;
      this.text2 = text5;
      this.sessionLegacyType = SessionLegacyType.resolveSessionLegacyType(text6);
      this.playerCertificates = playerCertificates2;
      this.keybindAnimationEntry = accountProfile;
   }
}
