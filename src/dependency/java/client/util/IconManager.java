package client.util;

import client.Client;
import client.ClientGlobals;
import client.gui.ClientScreenAdapter;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.via.ViaLoadingBase;
import com.viaversion.vialoader.util.ProtocolVersionList;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ServiceItemEntry;
import net.minecraft.util.Session;

public class IconManager {
   public static final ServiceItemEntry serviceItemEntry;

   public static ServiceItemEntry getServiceItemEntry() {
      return serviceItemEntry;
   }

   private static void updateState() {
      Session session = Minecraft.getMinecraft().session;
      if (session != null) {
         GuiScreen.applyText4(session.getText1());
      }
   }

   private static void applyModeSettingByModeSetting(ModeSetting setting) {
      String selected = Client.getClient().getModuleManager().clientSettings.settingBanStatistics.getSelected();
      switch (selected) {
         case "Icon":
            String text = "Left";
            setting.setSelected(text);
            break;
         case "Left":
            String text2 = "Right";
            setting.setSelected(text2);
            break;
         case "Right":
            String text3 = "None";
            setting.setSelected(text3);
            break;
         case "None":
            String text4 = "Icon";
            setting.setSelected(text4);
      }
   }

   private static void applyProtocolVersion(ProtocolVersion protocolVersion) {
      ViaLoadingBase.protocolVersion = protocolVersion;
      NumberSetting settingProtocolVersionVal = ClientGlobals.moduleManager.protocolVersion.settingProtocolVersionVal;
      double version = (double)protocolVersion.getVersion();
      settingProtocolVersionVal.applyDouble(version);
      Client.getClient().getAltItemEntry().updateState();
   }

   private static String resolveText(ModeSetting setting) {
      return "Bans: §6" + setting.getSelected();
   }

   private static ProtocolVersion getProtocolVersion() {
      return ViaLoadingBase.protocolVersion;
   }

   private static void updateState2() {
      ClientScreenAdapter clientScreenAdapter = new ClientScreenAdapter(Minecraft.getMinecraft().currentScreen);
      Minecraft.getMinecraft().displayGuiScreen(clientScreenAdapter);
   }

   static {
      SecondaryListEntry secondaryListEntry = ServiceItemEntry.getSecondaryListEntry();
      String text = "Quick actions";
      secondaryListEntry.add(text);
      secondaryListEntry.add();
      Runnable task = IconManager::updateState2;
      String text2 = "Bungee Hack";
      secondaryListEntry.add(text2, task);
      Runnable task2 = IconManager::updateState;
      String text3 = "Copy IGN";
      secondaryListEntry.add(text3, task2);
      BooleanSetting settingFastServerPining = Client.getClient().getModuleManager().clientSettings.settingFastServerPining1;
      String text4 = "Fast Server Ping";
      secondaryListEntry.add(text4, settingFastServerPining);
      ModeSetting settingBanStatistics = Client.getClient().getModuleManager().clientSettings.settingBanStatistics;
      Runnable task3 = () -> IconManager.applyModeSettingByModeSetting(settingBanStatistics);
      Supplier supplier = () -> IconManager.resolveText(settingBanStatistics);
      secondaryListEntry.add(supplier, task3);
      secondaryListEntry.add();
      String text5 = "Protocol Hack";
      secondaryListEntry.add(text5);
      secondaryListEntry.add();
      List protocolsNewToOld = ProtocolVersionList.getProtocolsNewToOld();
      Supplier supplier2 = IconManager::getProtocolVersion;
      Consumer consumer = (item -> IconManager.applyProtocolVersion((ProtocolVersion)item));
      Function mapper = item -> ((ProtocolVersion)item).getName();
      Function mapper2 = (item -> ProtocolVersion.getClosest((String)item));
      Function mapper3 = mapper;
      Consumer consumer2 = consumer;
      Supplier supplier3 = supplier2;
      List list = protocolsNewToOld;
      secondaryListEntry.add(list, supplier3, consumer2, mapper3, mapper2);
      serviceItemEntry = secondaryListEntry.getServiceItemEntry();
   }
}
