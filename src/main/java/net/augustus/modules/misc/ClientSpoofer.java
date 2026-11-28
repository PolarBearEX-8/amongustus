package net.augustus.modules.misc;

import io.netty.buffer.Unpooled;
import java.awt.Color;
import java.util.Random;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.settings.TextInputSetting;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.minecraft.client.resources.Language;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class ClientSpoofer extends Module implements PacketListener {
   private final StringValue clients;
   public final TextInputSetting customSpoofString;
   private final BooleanValue randomizeClientSettings;
   private final BooleanValue generateNewRandomizationOnlyAfterAccountchange;
   private final RandomizedRangeSettings randomViewDistance;
   private String lastPlayerUuid;
   private C15PacketClientSettings cachedClientSettingsPacket;

   public ClientSpoofer() {
       super("ClientSpoofer", "KundenVernebler", new Color(163, 240, 223), Categorys.MISC);
      this.clients = (StringValue)(new StringValue(
            this, "Clients", "Default", new String[]{"Default", "LabyMod", "CheatBreaker", "PvPLounge", "Lunar", "Badlion", "Custom", "CancelPacket"}
         ))
         .markVisualOnly();
      this.customSpoofString = (TextInputSetting)((TextInputSetting)new TextInputSetting(this, "Custom spoof string", "Example Text").withVisibility(() -> this.isCustomClientMode())).markVisualOnly();
      this.randomizeClientSettings = (BooleanValue)(new BooleanValue(this, "RandomizeClientSettings", false)).markVisualOnly();
      BooleanValue booleanValue = new BooleanValue(this, "GenerateNewRandomizationOnlyAfterAccountchange", false);
      BooleanValue booleanValue2 = this.randomizeClientSettings;
      BooleanValue booleanValue3 = booleanValue2;
      this.generateNewRandomizationOnlyAfterAccountchange = (BooleanValue)((BooleanValue)booleanValue.withVisibility(() -> booleanValue3.getValue())).markVisualOnly();
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(this, "RandomViewDistance", 10.0, 12.0, 2.0, 16.0, 0);
      booleanValue2 = this.randomizeClientSettings;
      BooleanValue booleanValue4 = booleanValue2;
      this.randomViewDistance = (RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue4.getValue())).markAllVisualOnly();
      this.lastPlayerUuid = "";
      this.cachedClientSettingsPacket = null;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   @Override
   public void onPacketSendNetty(EventPacket event) {
      Packet packet = event.getPacket();
      if ((packet instanceof C17PacketCustomPayload || packet instanceof C15PacketClientSettings) && !mc.isIntegratedServerRunning()) {
         if (packet instanceof C17PacketCustomPayload) {
            this.spoofBrandPayload((C17PacketCustomPayload)packet, event);
         }

         if (packet instanceof C15PacketClientSettings && this.randomizeClientSettings.getValue()) {
            C15PacketClientSettings packet3 = (C15PacketClientSettings)packet;
            Object[] array = mc.getLanguageManager().getLanguageMap().values().toArray();
            if (array.length > 0 && array[0] instanceof Language[]) {
               String str = mc.getSession() == null ? "" : mc.getSession().getPlayerID();
               if (this.generateNewRandomizationOnlyAfterAccountchange.getValue() && this.lastPlayerUuid.equals(str) && this.cachedClientSettingsPacket != null) {
                  if (this.generateNewRandomizationOnlyAfterAccountchange.getValue() && this.lastPlayerUuid.equals(str)) {
                     packet3.setLang(this.cachedClientSettingsPacket.getLang());
                     int view = this.cachedClientSettingsPacket.getView();
                     mc.gameSettings.renderDistanceChunks = view;
                     packet3.setView(view);
                     packet3.setEnableColors(this.cachedClientSettingsPacket.isColorsEnabled());
                  }
               } else {
                  packet3.setLang(((Language[])array)[DefaultRandomGenerator.instance.nextInt((byte)0, mc.getLanguageManager().getLanguageMap().values().size() - 1)].getLanguageCode());
                  int n2 = (int)this.randomViewDistance.nextValue();
                  mc.gameSettings.renderDistanceChunks = n2;
                  packet3.setView(n2);
                  packet3.setEnableColors(new Random().nextBoolean());
               }

               this.lastPlayerUuid = str;
               this.cachedClientSettingsPacket = packet3;
            }
         }
      }
   }

   private void spoofBrandPayload(C17PacketCustomPayload packet, EventPacket event) {
      if (packet.getChannelName().equals("MC|Brand")) {
         switch (this.clients.getSelectedValue()) {
            case "LabyMod":
               this.setBrandPayloadData(packet, "LMC");
               break;
            case "CheatBreaker":
               this.setBrandPayloadData(packet, "CB");
               break;
            case "PvPLounge":
               this.setBrandPayloadData(packet, "PLC18");
               break;
            case "Lunar":
               this.setBrandPayloadData(packet, "Lunar-Client");
               break;
            case "Badlion":
               this.setBrandPayloadData(packet, "badlion");
               break;
            case "Custom":
               this.setBrandPayloadData(packet, this.customSpoofString.getValue());
               break;
            case "CancelPacket":
               event.cancel();
         }
      }
   }

   private void setBrandPayloadData(C17PacketCustomPayload packet, String str) {
      packet.setData(new PacketBuffer(Unpooled.buffer()).writeString(str));
   }

   private boolean isCustomClientMode() {
      return this.clients.getSelectedValue().equals("Custom");
   }

}
