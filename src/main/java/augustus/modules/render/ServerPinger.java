package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.ServerPingerIncolumitasType;
import client.setting.Setting;
import client.setting.SettingGroup;
import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ServerPinger extends Module {
    public SettingGroup settingServerWidget;
    public BooleanSetting settingServerWidgetEnabled;
    public NumberSetting settingPingDelay;
    public ColorSetting settingColor;
    public ColorSetting settingBackgroundColor;
    public SettingGroup settingIpInfo;
    public BooleanSetting settingIpInfoEnabled;
    public ColorSetting settingTextColor;
    public ColorSetting settingBoxColor;
    public ModeSetting settingBackend;
    public SettingGroup settingPlayerGraph;
    public BooleanSetting settingPlayerGraphEnabled;
    public NumberSetting settingMaxEntries;
    public BooleanSetting settingKeepPlayerData;
    public Map entryMap;

    public ServerPinger() {
        super("ServerPinger", "BereitstellerFürTaktischWichtigeInformationen", new Color(178, 18, 253), Category.RENDER);
        this.settingServerWidget = new SettingGroup(this, "ServerWidget", true);
        this.settingServerWidgetEnabled = (BooleanSetting)new BooleanSetting(this, "ServerWidgetEnabled", true, this.settingServerWidget).getSetting("Enabled");
        this.settingPingDelay = new NumberSetting(this, "PingDelay", 5000.0, 1000.0, 50000.0, 0, this.settingServerWidget);
        Color color = new Color(41, 146, 222);
        this.settingColor = new ColorSetting(this, "Color", color, this.settingServerWidget);
        Color color2 = new Color(0, 0, 0, 120);
        this.settingBackgroundColor = new ColorSetting(this, "BackgroundColor", color2, this.settingServerWidget);
        this.settingIpInfo = new SettingGroup(this, "IpInfo", true);
        this.settingIpInfoEnabled = (BooleanSetting)new BooleanSetting(this, "IpInfoEnabled", true, this.settingIpInfo).getSetting("Enabled");
        Color color3 = new Color(41, 146, 222);
        this.settingTextColor = new ColorSetting(this, "TextColor", color3, this.settingIpInfo);
        Color color4 = new Color(0, 0, 0, 120);
        this.settingBoxColor = new ColorSetting(this, "BoxColor", color4, this.settingIpInfo);
        String text = ServerPingerIncolumitasType.INCOLUMITAS.getText();
        Stream stream = Arrays.stream(ServerPingerIncolumitasType.values());
        this.settingBackend = new ModeSetting(
            this,
            "Backend",
            text,
            ((String[])(stream.map(object -> ((ServerPingerIncolumitasType)object).getText()).toArray(index -> getStringArray(index)))),
            this.settingIpInfo
        );
        this.settingPlayerGraph = new SettingGroup(this, "PlayerGraph", true);
        this.settingPlayerGraphEnabled = (BooleanSetting)new BooleanSetting(this, "PlayerGraphEnabled", true, this.settingPlayerGraph).getSetting("Enabled");
        this.settingMaxEntries = new NumberSetting(this, "MaxEntries", 200.0, 10.0, 500.0, 0, this.settingPlayerGraph);
        this.settingKeepPlayerData = new BooleanSetting(this, "KeepPlayerData", true, this.settingPlayerGraph);
        this.entryMap = new HashMap();
        this.settingColor.setState4(true);
        this.settingColor.updateInt(255);
        this.settingTextColor.setState4(true);
        this.settingTextColor.updateInt(255);
        this.processBoolean(true, false);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        this.entryMap.clear();
    }

    @Override
    public void onPreDisable() {
    }

    private static String[] getStringArray(int index) {
        return new String[index];
    }
}
