package net.minecraft.client.gui;

import client.ClientGlobals;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ClientSettingsBlackType;
import client.setting.CombatOffsetProperty;
import client.util.CheatingThread;
import client.util.JavaNamingFactoryInitialManager;
import client.util.MovementHandler;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiConnecting extends GuiScreen {
   public static final List list3 = new CopyOnWriteArrayList();
   public static final AtomicInteger atomicInteger = new AtomicInteger(0);
   public static final Logger logger2 = LogManager.getLogger();
   public NetworkManager networkManager;
   public boolean enabled6;
   public final GuiScreen guiScreen2;
   public static String labelText;
   public static String labelText2 = "";
   public static int count2;

   @Override
   public void updateState2() {
      this.list.clear();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 100, this.height / 4 + 120 + 12, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
   }

   public static void add(CombatOffsetProperty combatOffsetProperty) {
      list3.add(combatOffsetProperty);
   }

   private void applyRenderPassByRenderPass(int count, int count3, RenderPass renderPass) {
      if (ClientGlobals.moduleManager.clientSettings.settingRoundedButtons.isEnabled()) {
         RenderUtils.processFloat9(
            (float)this.width / 2.0F - 100.0F,
            (float)count,
            (float)this.width / 2.0F + 100.0F,
            (float)(count + count3),
            (float)ClientGlobals.moduleManager.clientSettings.settingRoundFactor.getValue(),
            1,
            Integer.MIN_VALUE
         );
         GlStateManager.disableBlend();
      } else {
         RenderUtils.processAction(this.width / 2 - 100, count, this.width / 2 + 100, count + count3, Integer.MIN_VALUE);
      }
   }

   public static Minecraft resolveMinecraftByGuiConnecting(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   public static NetworkManager resolveNetworkManager(GuiConnecting guiConnecting) {
      return guiConnecting.networkManager;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         this.enabled6 = true;
         if (this.networkManager != null) {
            this.networkManager.applyIChatComponent(new ChatComponentText("Aborted"));
         }

         GuiScreen guiScreen = this.guiScreen2;
         this.mc.displayGuiScreen(guiScreen);
      }
   }

   public static Minecraft resolveMinecraft5(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
   }

   @Override
   public void updateState4() {
      if (this.networkManager != null) {
         if (this.networkManager.isOpen()) {
            this.networkManager.updateState2();
         } else {
            this.networkManager.updateState3();
         }
      }
   }

   public static boolean matchesGuiConnecting(GuiConnecting guiConnecting) {
      return guiConnecting.enabled6;
   }

   private void applyText(String text, int count) {
      labelText = text;
      count2 = count;
      logger2.info("Connecting to " + text + ", " + count);
      new CheatingThread(this, "Server Connector #" + atomicInteger.incrementAndGet(), text, count).start();
   }

   public static Logger getLogger2() {
      return logger2;
   }

   public static GuiScreen resolveGuiScreen(GuiConnecting guiConnecting) {
      return guiConnecting.guiScreen2;
   }

   public static Minecraft resolveMinecraft7(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   public static Minecraft resolveMinecraft9(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      this.renderElement3();
      int count4 = (CombatOffsetProperty.values().length + 1) * this.fontRenderer.indexOffset + 2;
      int count5 = this.height / 4 + 110 - count4;
      MovementHandler.processBoolean(true, false);
      MovementHandler.applyRunnableTask(false, pass -> this.applyRenderPassByRenderPass(count5, count4, pass));
      int count6 = 0;

      for (CombatOffsetProperty combatOffsetProperty : (Iterable<CombatOffsetProperty>)(Iterable<?>)list3) {
         this.applyFontRenderer(
            this.fontRenderer,
            ClientSettingsBlackType.RED + combatOffsetProperty.getLabelText2(),
            this.width / 2 - 100 + 1 + 3,
            count5 + count6 * this.fontRenderer.indexOffset + 2 + 3,
            -1
         );
         count6++;
      }

      String text;
      switch ((int)(Minecraft.getTimestamp() / 300L % 4L)) {
         case 0:
         case 2:
         default:
            text = "_";
            break;
         case 1:
         case 3:
            text = "";
      }

      if (!list3.contains(CombatOffsetProperty.SUCCESS)) {
         this.applyFontRenderer(this.fontRenderer, text, this.width / 2 - 100 + 1 + 3, count5 + count6 * this.fontRenderer.indexOffset + 2 + 3, -1);
      }

      super.applyIndex(count, count3, factor);
   }

   public static Minecraft resolveMinecraft3(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   public static NetworkManager resolveNetworkManager(GuiConnecting guiConnecting, NetworkManager networkManager2) {
      return guiConnecting.networkManager = networkManager2;
   }

   public GuiConnecting(GuiScreen screen, Minecraft mc, ServerData serverData) {
      list3.clear();
      this.mc = mc;
      this.guiScreen2 = screen;
      add(CombatOffsetProperty.RESOLVING_SRV);
      labelText2 = serverData.text;
      JavaNamingFactoryInitialManager javaNamingFactoryInitialManager = JavaNamingFactoryInitialManager.resolveJavaNamingFactoryInitialMana(serverData.text);
      WorldClient world = null;
      mc.loadWorld(world);
      mc.setServerData(serverData);
      this.applyText(javaNamingFactoryInitialManager.toASCII(), javaNamingFactoryInitialManager.getCount());
   }

   public GuiConnecting(GuiScreen screen, Minecraft mc, String text, int count) {
      this.mc = mc;
      this.guiScreen2 = screen;
      WorldClient world = null;
      mc.loadWorld(world);
      this.applyText(text, count);
   }
}
