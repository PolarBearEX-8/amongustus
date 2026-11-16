package net.minecraft.client.gui;

import cc.baritone.api.SecondaryItemEntry;
import client.Client;
import client.ClientGlobals;
import client.event.PostRenderInterceptor;
import client.gui.AltGuiScreenAdapter;
import client.gui.AugustusMainMenu;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.CommonGuiScreenAdapter;
import client.module.Module;
import client.render.RenderUtils;
import client.util.BezierSelector;
import client.util.CancelledManager;
import client.util.SharedStringEntry;
import client.util.SplineBridge;
import client.util.SplineFactory;
import client.util.SplineSystem;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.StatCollector;

public class GuiScreenServerList extends GuiScreen implements PostRenderInterceptor {
   public ServerData serverData;
   public static final CancelledManager cancelledManager = new CancelledManager();
   public String labelText;

   @Override
   public void updateState2() {
      this.list.clear();
      if (((Module)ClientGlobals.moduleManager.invisMode).isToggled() && ClientGlobals.moduleManager.invisMode.canExecute5()) {
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  1, this.width / 2 - 100, this.height / 4 + 120 + -16, StatCollector.getText("menu.returnToMenu", new Object[0])
               )
            );
         if (!this.mc.isGamePaused()) {
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).labelText = StatCollector.getText("menu.disconnect", new Object[0]);
         }

         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  4, this.width / 2 - 100, this.height / 4 + 24 + -16, StatCollector.getText("menu.returnToGame", new Object[0])
               )
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  0, this.width / 2 - 100, this.height / 4 + 96 + -16, 98, 20, StatCollector.getText("menu.options", new Object[0])
               )
            );
         AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
         this.list
            .add(
               auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  7, this.width / 2 + 2, this.height / 4 + 96 + -16, 98, 20, StatCollector.getText("menu.shareToLan", new Object[0])
               )
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  5, this.width / 2 - 100, this.height / 4 + 48 + -16, 98, 20, StatCollector.getText("gui.achievements", new Object[0])
               )
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  6, this.width / 2 + 2, this.height / 4 + 48 + -16, 98, 20, StatCollector.getText("gui.stats", new Object[0])
               )
            );
         auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = this.mc.isFullScreen() && !this.mc.getIntegratedServer().isEnabled14();
      } else {
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  1, this.width / 2 - 100, this.height / 4 + 120 + -16, StatCollector.getText("menu.returnToMenu", new Object[0])
               )
            );
         if (!this.mc.isGamePaused()) {
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).labelText = StatCollector.getText("menu.disconnect", new Object[0]);
         }

         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  4, this.width / 2 - 100, this.height / 4 + 24 + -16, StatCollector.getText("menu.returnToGame", new Object[0])
               )
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  0, this.width / 2 - 100, this.height / 4 + 96 + -16, 98, 20, StatCollector.getText("menu.options", new Object[0])
               )
            );
         AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
         this.list
            .add(
               auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  7, this.width / 2 + 2, this.height / 4 + 96 + -16, 98, 20, StatCollector.getText("menu.shareToLan", new Object[0])
               )
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  5, this.width / 2 - 100, this.height / 4 + 48 + -16, 98, 20, StatCollector.getText("gui.achievements", new Object[0])
               )
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  6, this.width / 2 + 2, this.height / 4 + 48 + -16, 98, 20, StatCollector.getText("gui.stats", new Object[0])
               )
            );
         auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = this.mc.isFullScreen() && !this.mc.getIntegratedServer().isEnabled14();
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(1000, this.width / 2 - 100, this.height / 4 + 72 + -16, 98, 20, Client.getClient().getString4())
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  1001, this.width / 2 + 2, this.height / 4 + 72 + -16, 98, 20, StatCollector.getText("selectServer.direct", new Object[0])
               )
            );
         SecondaryItemEntry.applyGuiScreenServerList(this, this.list);
      }
   }

   private void renderElement5() {
      int count = this.width / 2;
      int count2 = BezierSelector.computeCount(Client.getClient().getInt2());
      int count3 = Client.getClient().computeCount(0x39530c290446L);
      int count4 = count - 100;
      int count5 = count + 100;
      RenderUtils.processAction(count4, 15, count5, 28, new Color(0, 0, 0, 150).getRGB());
      RenderUtils.processDouble4(
         (double)count4, 15.0, (double)count4 + (double)(count5 - count4) * ((double)count3 / (double)count2), 28.0, Client.getClient().getColor().getRGB()
      );
      String text = count3 + "/" + count2 + " ep, lv " + Client.getClient().getInt2();
      int index = this.fontRenderer.getIndex(text);
      this.fontRenderer.getIndex(text, count - index / 2, 18, Color.WHITE.getRGB());
   }

   @Override
   public void applyGuiScreen(GuiScreen screen, int count, int count2) {
      if (this.labelText != null) {
         screen.applyList(Lists.newArrayList(Splitter.on("\n").split(this.labelText)), count, count2);
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      SecondaryItemEntry.applyGuiScreenServerList(this, auxiliaryTexturesGuiIconsPngManagerAdapter);
      switch (auxiliaryTexturesGuiIconsPngManagerAdapter.count2) {
         case 0:
            Minecraft mc = this.mc;
            CommonGuiScreenAdapter commonGuiScreenAdapter = new CommonGuiScreenAdapter(this, this.mc.gameSettings);
            mc.displayGuiScreen(commonGuiScreenAdapter);
            break;
         case 1:
            auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = false;
            this.renderElement6();
         case 2:
         case 3:
         default:
            break;
         case 4:
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
            this.mc.updateState21();
            break;
         case 5:
            Minecraft mc2 = this.mc;
            SplineSystem splineSystem = new SplineSystem(this, this.mc.thePlayer.getStatFileWriter());
            mc2.displayGuiScreen(splineSystem);
            break;
         case 6:
            Minecraft mc3 = this.mc;
            SplineBridge splineBridge = new SplineBridge(this, this.mc.thePlayer.getStatFileWriter());
            mc3.displayGuiScreen(splineBridge);
            break;
         case 7:
            Minecraft mc4 = this.mc;
            SplineFactory splineFactory = new SplineFactory(this);
            mc4.displayGuiScreen(splineFactory);
            break;
         case 1000:
            Minecraft mc5 = this.mc;
            SharedStringEntry sharedStringEntry = new SharedStringEntry(this, 0);
            mc5.displayGuiScreen(sharedStringEntry);
            break;
         case 1001:
            Minecraft mc6 = this.mc;
            AltGuiScreenAdapter altGuiScreenAdapter = new AltGuiScreenAdapter(
               this, this.serverData = new ServerData("", this.mc.isFullScreen() ? "" : this.mc.getServerData().text, false)
            );
            mc6.displayGuiScreen(altGuiScreenAdapter);
      }
   }

   @Override
   public void updateState4() {
      super.updateState4();
   }

   @Override
   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (flag) {
         this.renderElement6();
         Minecraft mc = this.mc;
         GuiConnecting guiConnecting = new GuiConnecting(new GuiMultiplayer(AugustusMainMenu.resolveGuiScreen((short)0, (char)3322, -80609536)), this.mc, this.serverData);
         mc.displayGuiScreen(guiConnecting);
      } else {
         this.mc.displayGuiScreen(this);
      }
   }

   @Override
   public CancelledManager getCancelledManager() {
      return cancelledManager;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      if (!((Module)ClientGlobals.moduleManager.invisMode).isToggled() || !ClientGlobals.moduleManager.invisMode.canExecute5()) {
         this.renderElement5();
      }

      applyFontRenderer4(this.fontRenderer, StatCollector.getText("menu.game", new Object[0]), this.width / 2, 40, 16777215);
      super.applyIndex(count, count2, factor);
   }

   private void renderElement6() {
      boolean gamePaused = this.mc.isGamePaused();
      this.mc.theWorld.updateState();
      WorldClient world = null;
      this.mc.loadWorld(world);
      if (gamePaused) {
         Minecraft mc = this.mc;
         GuiMainMenu guiMainMenu = new GuiMainMenu();
         mc.displayGuiScreen(guiMainMenu);
      } else {
         Minecraft mc2 = this.mc;
         GuiMultiplayer guiMultiplayer = new GuiMultiplayer(new GuiMainMenu());
         mc2.displayGuiScreen(guiMultiplayer);
      }
   }
}
