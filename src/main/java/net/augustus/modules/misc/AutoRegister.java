package net.augustus.modules.misc;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.settings.TextInputSetting;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S45PacketTitle;
import org.apache.commons.lang3.StringUtils;

public class AutoRegister extends Module implements PacketListener, TickListener {
   private final StringValue passwordMode;
   private final RandomizedRangeSettings delay;
   public final TextInputSetting customPassword;
   private final BooleanValue getPasswordFromTxtIfPresent;
   private final BooleanValue printOnRegister;
   private final BooleanValue printOnLogin;
   private final HashMap scheduledCommands;
   private final HashMap filePasswords;

   public AutoRegister() {
       super("AutoRegister", "AutoRegistrieren", new Color(189, 32, 110), Categorys.MISC);
      this.passwordMode = (StringValue)(new StringValue(this, "PasswordMode", "Generated", new String[]{"Generated", "Private", "Private2", "Custom"}))
         .markVisualOnly();
      this.delay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "Delay", 700.0, 1400.0, 0.0, 4000.0, 0)).withoutNotInARow().markAllVisualOnly();
      this.customPassword = (TextInputSetting)((TextInputSetting)new TextInputSetting(this, "Custom password", "Hallo1337", true).withVisibility(() -> this.isCustomPasswordMode())).markVisualOnly();
      this.getPasswordFromTxtIfPresent = (BooleanValue)(new BooleanValue(this, "Get password from txt if present", false)).markVisualOnly();
      this.printOnRegister = (BooleanValue)(new BooleanValue(this, "Print on register", false)).markVisualOnly();
      this.printOnLogin = (BooleanValue)(new BooleanValue(this, "Print on login", false)).markVisualOnly();
      this.scheduledCommands = new HashMap();
      this.filePasswords = new HashMap();
      String str = Augustus.getInstance().getName().toLowerCase() + File.separator;
      ClientFileUtil.extractResource("assets/augustus/autoregister.txt", str, false);
   }

   @Override
   public void onEnable() {
      this.scheduledCommands.clear();
      this.loadPasswordsFromFile();
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      String str = this.extractMessageText(event.getPacket());
      if (str != null) {
         if (StringUtils.containsIgnoreCase(str, "/reg") || StringUtils.containsIgnoreCase(str, "/register")) {
            this.scheduleAuthCommand(false);
         } else if (StringUtils.containsIgnoreCase(str, "/login")) {
            this.scheduleAuthCommand(true);
         }
      }
   }

   @Override
   public void onTick() {
      if (!this.scheduledCommands.isEmpty()) {
         for (Double d : (Iterable<Double>)(this.scheduledCommands.keySet())) {
            if (!(d > (double)System.currentTimeMillis())) {
               mc.thePlayer.sendChatMessage((String)this.scheduledCommands.get(d));
               this.scheduledCommands.remove(d);
               break;
            }
         }
      }
   }

   private String extractMessageText(Packet packet) {
      if (packet instanceof S02PacketChat) {
         return ((S02PacketChat)packet).getChatComponent().getUnformattedText();
      } else {
         if (packet instanceof S45PacketTitle) {
            S45PacketTitle packet3 = (S45PacketTitle)packet;
            if (packet3.getMessage() != null) {
               return packet3.getMessage().getUnformattedText();
            }
         }

         return null;
      }
   }

   private void scheduleAuthCommand(boolean flag) {
      double d = this.delay.nextValue();
      double d2 = (double)System.currentTimeMillis() + d;
      String str = MathStatisticsUtil.formatRounded(d / 1000.0, 1);
      if (this.scheduledCommands.isEmpty()) {
         if (flag) {
            String str3 = this.resolvePassword(this.passwordMode.getSelectedValue(), this.getPasswordFromTxtIfPresent.getValue());
            this.sendChatMessage(new StringBuilder().append("Logging in... (").append(str).append("s)").append(this.printOnLogin.getValue() ? " with " + str3 : "").toString());
            this.scheduledCommands.put(d2, "/login " + str3);
         } else {
            String str6 = this.resolvePassword(this.passwordMode.getSelectedValue(), this.getPasswordFromTxtIfPresent.getValue());
            StringBuilder sb2 = new StringBuilder().append("Register in... (").append(str);
            short s = 28607;
            this.sendChatMessage(sb2.append("s)").append(this.printOnRegister.getValue() ? " with " + str6 : "").toString());
            this.scheduledCommands.put(d2, "/register " + str6 + " " + str6);
         }
      }
   }

   public String resolvePassword(String str, boolean flag) {
      String username = mc.getSession().getUsername();
      if (flag && this.filePasswords.containsKey(username)) {
         return (String)this.filePasswords.get(username);
      } else {
         if (str.equalsIgnoreCase("Custom")) {
            return this.customPassword.getValue();
         } else {
            byte[] bytes = username.getBytes(StandardCharsets.UTF_8);
            if (str.equalsIgnoreCase("private2")) {
               for (int i = 0; i < bytes.length; i++) {
                  bytes[i] = (byte)(bytes[i] ^ 5);
               }
            }

            if (str.equalsIgnoreCase("generated")) {
               byte[] digest = sha256(bytes);
               return Base64.getEncoder().encodeToString(digest).substring(0, 12);
            } else {
               byte[] digest = sha256(bytes);
               int hash = Augustus.getInstance().getUsername().hashCode();

               for (int j = 0; j < digest.length; j++) {
                  digest[j] = (byte)(digest[j] ^ (byte)(j ^ 267 ^ bytes[j % bytes.length]) ^ (byte)(hash >> j % 5 & 0xFF));
               }

               Random rand = str.equalsIgnoreCase("Private")
                  ? new Random((long)hash)
                  : new Random((long)(hash ^ username.hashCode()));

               return Base64.getEncoder().encodeToString(digest).substring(0, rand.nextInt(3) + 10);
            }
         }
      }
   }

   private static byte[] sha256(byte[] data) {
      try {
         return MessageDigest.getInstance("SHA-256").digest(data);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   private void loadPasswordsFromFile() {
      this.filePasswords.clear();

      File file;
      try {
         file = ClientFileUtil.getExistingFile("augustus/autoregister.txt");
      } catch (FileNotFoundException e) {
         String str = Augustus.getInstance().getName().toLowerCase() + File.separator;
         ClientFileUtil.extractResource("assets/augustus/autoregister.txt", str, false);

         try {
            file = ClientFileUtil.getExistingFile("augustus/autoregister.txt");
         } catch (FileNotFoundException e2) {
            return;
         }
      }

      try {
         for (String str3 : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
            if (!str3.startsWith("#")) {
               str3 = str3.replace("\\:", "\u0000");
               String[] parts = str3.split(":");
               if (parts.length >= 2) {
                  this.filePasswords.put(parts[0].replace("\u0000", ":"), parts[1].replace("\u0000", ":"));
               }
            }
         }
      } catch (Throwable e3) {
      }
   }

   private boolean isCustomPasswordMode() {
      return this.passwordMode.getSelectedValue().equals("Custom");
   }

}
