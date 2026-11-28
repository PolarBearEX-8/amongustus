package net.augustus.modules.render;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.network.IpLookupProvider;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class ServerPinger extends Module {
   public final SettingGroup serverWidget;
   public final BooleanValue serverWidgetEnabled;
   public final DoubleValue pingDelay;
   public final ColorSetting widgetColor;
   public final ColorSetting widgetBackgroundColor;
   public final SettingGroup ipInfo;
   public final BooleanValue ipInfoEnabled;
   public final ColorSetting ipInfoTextColor;
   public final ColorSetting ipInfoBoxColor;
   public final StringValue backend;
   public final SettingGroup playerGraph;
   public final BooleanValue playerGraphEnabled;
   public final DoubleValue maxEntries;
   public final BooleanValue keepPlayerData;
   public final Map playerCountHistory;

   public ServerPinger() {
       super("ServerPinger", "BereitstellerFürTaktischWichtigeInformationen", new Color(178, 18, 253), Categorys.RENDER);
      this.serverWidget = (SettingGroup)(new SettingGroup(this, "ServerWidget", true)).markVisualOnly();
      this.serverWidgetEnabled = (BooleanValue)((BooleanValue)new BooleanValue(this, "ServerWidgetEnabled", true, this.serverWidget).withDisplayName("Enabled")).markVisualOnly();
      this.pingDelay = (DoubleValue)(new DoubleValue(this, "PingDelay", 5000.0, 1000.0, 50000.0, 0, this.serverWidget)).markVisualOnly();
      Color color2 = new Color(41, 146, 222);
      SettingGroup serverWidget2 = this.serverWidget;
      Color color3 = color2;
      String str2 = "Color";
      this.widgetColor = (ColorSetting)(new ColorSetting(this, str2, color3, serverWidget2)).markVisualOnly();
      color2 = new Color(0, 0, 0, 120);
      serverWidget2 = this.serverWidget;
      color3 = color2;
      str2 = "BackgroundColor";
      this.widgetBackgroundColor = (ColorSetting)(new ColorSetting(this, str2, color3, serverWidget2)).markVisualOnly();
      this.ipInfo = (SettingGroup)(new SettingGroup(this, "IpInfo", true)).markVisualOnly();
      this.ipInfoEnabled = (BooleanValue)((BooleanValue)new BooleanValue(this, "IpInfoEnabled", true, this.ipInfo).withDisplayName("Enabled")).markVisualOnly();
      color2 = new Color(41, 146, 222);
      serverWidget2 = this.ipInfo;
      color3 = color2;
      str2 = "TextColor";
      this.ipInfoTextColor = (ColorSetting)(new ColorSetting(this, str2, color3, serverWidget2)).markVisualOnly();
      color2 = new Color(0, 0, 0, 120);
      serverWidget2 = this.ipInfo;
      color3 = color2;
      str2 = "BoxColor";
      this.ipInfoBoxColor = (ColorSetting)(new ColorSetting(this, str2, color3, serverWidget2)).markVisualOnly();
      this.backend = (StringValue)(new StringValue(
            this,
            "Backend",
            IpLookupProvider.INCOLUMITAS.getDisplayName(),
            Arrays.stream(IpLookupProvider.values()).map(IpLookupProvider::getDisplayName).toArray(String[]::new),
            this.ipInfo
         ))
         .markVisualOnly();
      this.playerGraph = (SettingGroup)(new SettingGroup(this, "PlayerGraph", true)).markVisualOnly();
      this.playerGraphEnabled = (BooleanValue)((BooleanValue)new BooleanValue(this, "PlayerGraphEnabled", true, this.playerGraph).withDisplayName("Enabled")).markVisualOnly();
      this.maxEntries = (DoubleValue)(new DoubleValue(this, "MaxEntries", 200.0, 10.0, 500.0, 0, this.playerGraph)).markVisualOnly();
      this.keepPlayerData = (BooleanValue)(new BooleanValue(this, "KeepPlayerData", true, this.playerGraph)).markVisualOnly();
      this.playerCountHistory = new HashMap();
      this.widgetColor.setClientColorEnabled(true);
      this.widgetColor.setClientColorAlpha((short)255);
      this.ipInfoTextColor.setClientColorEnabled(true);
      this.ipInfoTextColor.setClientColorAlpha((short)255);
      this.setToggled(true, false);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.playerCountHistory.clear();
   }

   @Override
   public void updateEventSubscriptions() {
   }

}
