package net.augustus.viaversion;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.config.ConfigManager;
import net.augustus.ui.BungeeHackScreen;
import net.augustus.ui.menu.QuickMenuBuilder;
import net.augustus.ui.widgets.QuickActionsSidebar;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Session;

public class MultiplayerProtocolMenu {
   private static final QuickActionsSidebar SIDEBAR;

   private static void selectProtocolVersion(ProtocolVersion protocolVersion) {
      ViaAugustus.targetProtocolVersion = protocolVersion;
      ClientManagers.moduleManager.protocolVersion.protocolVersionVal.setValue((double)protocolVersion.getVersion());
      Augustus.getInstance().getConfigManager().snapshotAll();
   }

   private static void cycleBansDisplayMode(StringValue booleansSetting) {
      switch (Augustus.getInstance().getModuleManager().clientSettings.banStatistics.getSelectedValue()) {
         case "Icon":
            booleansSetting.setSelectedValue("Left");
            break;
         case "Left":
            booleansSetting.setSelectedValue("Right");
            break;
         case "Right":
            booleansSetting.setSelectedValue("None");
            break;
         case "None":
            booleansSetting.setSelectedValue("Icon");
      }
   }

   static {
      QuickMenuBuilder quickMenuBuilder = QuickActionsSidebar.builder();
      quickMenuBuilder.addHeader("Quick actions");
      quickMenuBuilder.addSpacer();
      Runnable runnable = () -> {
         BungeeHackScreen bungeeHackScreen = new BungeeHackScreen(Minecraft.getMinecraft().currentScreen);
         Minecraft.getMinecraft().displayGuiScreen(bungeeHackScreen);
      };
      quickMenuBuilder.addAction("Bungee Hack", runnable);
      Runnable runnable2 = () -> {
         Session session = Minecraft.getMinecraft().session;
         if (session != null) {
            GuiScreen.setClipboardString(session.getUsername());
         }
      };
      quickMenuBuilder.addAction("Copy IGN", runnable2);
      BooleanValue fastServerPining1 = Augustus.getInstance().getModuleManager().clientSettings.fastServerPining1;
      quickMenuBuilder.addBooleanSetting("Fast Server Ping", fastServerPining1);
      StringValue banStatistics = Augustus.getInstance().getModuleManager().clientSettings.banStatistics;
      quickMenuBuilder.addDynamicAction(() -> formatBansDisplayLabel(banStatistics), () -> cycleBansDisplayMode(banStatistics));
      quickMenuBuilder.addSpacer();
      quickMenuBuilder.addHeader("Protocol Hack");
      quickMenuBuilder.addSpacer();
      quickMenuBuilder.addSelectableOptions(ProtocolVersion.getReversedProtocols(), () -> ViaAugustus.targetProtocolVersion, obj -> selectProtocolVersion((ProtocolVersion)obj), obj2 -> ((ProtocolVersion)obj2).getName(), obj3 -> ProtocolVersion.getClosest((String)obj3));
      SIDEBAR = quickMenuBuilder.build();
   }

   public static QuickActionsSidebar getSidebar() {
      return SIDEBAR;
   }

   private static String formatBansDisplayLabel(StringValue booleansSetting) {
      return "Bans: §6" + booleansSetting.getSelectedValue();
   }

}
