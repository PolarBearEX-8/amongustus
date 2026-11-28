package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.TextInputSetting;
import net.augustus.ui.menu.AbstractMainMenuScreen;
import net.augustus.events.listeners.DisconnectListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;

public class AutoReconnect extends Module implements TickListener, DisconnectListener {
   private final StringValue mode;
   private final TextInputSetting serverIP;
   private final BooleanValue reconnectOnManualDisconnect;
   private final BooleanValue reconnectMessage;
   private final BooleanValue abortReconnectProcessAtJoin;
   private final RandomizedRangeSettings reconnectDelay;
   private final TimeHelper timeHelper;
   private boolean reconnectPending;
   private String lastServerIp;
   private int lastServerPort;
   private boolean reconnectedFlag;

   public AutoReconnect() {
       super("AutoReconnect", "AutoReconnect", Color.CYAN, Categorys.MISC);
      this.mode = (StringValue)(new StringValue(this, "Mode", "LastServer", new String[]{"LastServer", "CustomServer"})).markVisualOnly();
      this.serverIP = (TextInputSetting)((TextInputSetting)new TextInputSetting(this, "ServerIP", "localhost:25565").withVisibility(() -> this.isCustomServerMode())).markVisualOnly();
      this.reconnectOnManualDisconnect = (BooleanValue)(new BooleanValue(this, "ReconnectOnManualDisconnect", false)).markVisualOnly();
      this.reconnectMessage = (BooleanValue)(new BooleanValue(this, "ReconnectMessage", true)).markVisualOnly();
      this.abortReconnectProcessAtJoin = (BooleanValue)(new BooleanValue(this, "AbortReconnectProcessAtJoin", true)).markVisualOnly();
      this.reconnectDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "ReconnectDelay", 5.0, 6.0, 0.0, 30.0, 1)).withoutNotInARow().markAllVisualOnly();
      this.timeHelper = new TimeHelper();
      this.reconnectPending = false;
      this.lastServerIp = "";
      this.lastServerPort = 25565;
      this.reconnectedFlag = false;
   }

   @Override
   public void onEnable() {
      this.reconnectPending = false;
      this.reconnectedFlag = false;
   }

   @Override
   public void onDisable() {
      this.reconnectPending = false;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(62);
   }

   @Override
   public void onDisconnect() {
      if (this.reconnectOnManualDisconnect.getValue() && !mc.isSingleplayer()) {
         this.captureLastServerAddress();
         this.reconnectPending = true;
         this.timeHelper.reset();
      }
   }

   @Override
   public void onPreTick() {
      if (mc.currentScreen instanceof GuiDisconnected && !mc.isSingleplayer() && !this.reconnectPending) {
         this.captureLastServerAddress();
         this.reconnectPending = true;
         this.timeHelper.reset();
      }

      if (mc.theWorld != null && mc.thePlayer != null && this.reconnectedFlag && this.reconnectMessage.getValue()) {
         this.sendChatMessage("Automatic reconnect successfully :)");
         this.reconnectedFlag = false;
      }

      if ((mc.currentScreen instanceof GuiConnecting || mc.isSingleplayer() || mc.currentScreen == null) && this.reconnectPending && this.abortReconnectProcessAtJoin.getValue()) {
         this.reconnectPending = false;
      }

      if (this.reconnectPending) {
         long l = (long)(this.reconnectDelay.getLastValue() * 1000.0);
         if (this.timeHelper.reached(l)) {
            this.connectToServer();
            this.reconnectPending = false;
         }
      }
   }

   private void connectToServer() {
      String str;
      int n;
      if (this.mode.getSelectedValue().equals("CustomServer")) {
         String value = this.serverIP.getValue();
         if (value.contains(":")) {
            String[] parts = value.split(":");
            str = parts[0];

            try {
               n = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
               n = 25565;
            }
         } else {
            str = value;
            n = 25565;
         }
      } else {
         str = this.lastServerIp;
         n = this.lastServerPort;
      }

      if (str != null && !str.isEmpty()) {
         String str2 = str + ":" + n;
         ServerData serverData = new ServerData(str2, str2, false);
         mc.displayGuiScreen(new GuiConnecting(new GuiMultiplayer(AbstractMainMenuScreen.createMainMenuScreen()), mc, serverData));
         this.reconnectDelay.nextValue();
         this.reconnectedFlag = true;
      }
   }

   private void captureLastServerAddress() {
      this.lastServerIp = GuiConnecting.lastServerIp;
      this.lastServerPort = GuiConnecting.lastServerPort;
   }

   private boolean isCustomServerMode() {
      return this.mode.getSelectedValue().equals("CustomServer");
   }

}
