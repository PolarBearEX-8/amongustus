package net.minecraft.client.gui;

import client.event.PreMotionObserver;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.DefaultGuiScreenAdapter;
import client.setting.ClientSettingsBlackType;
import java.io.IOException;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;

public class GuiGameOver extends GuiScreen implements PreMotionObserver {
   public int count2;
   public boolean enabled6 = false;

   @Override
   public void updateState2() {
      this.list.clear();
      if (this.mc.theWorld.getRandomSeedManager().isEnabled2()) {
         if (this.mc.isGamePaused()) {
            this.list
               .add(
                  new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                     1, this.width / 2 - 100, this.height / 4 + 96, StatCollector.getText("deathScreen.deleteWorld", new Object[0])
                  )
               );
         } else {
            this.list
               .add(
                  new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                     1, this.width / 2 - 100, this.height / 4 + 96, StatCollector.getText("deathScreen.leaveServer", new Object[0])
                  )
               );
         }
      } else {
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  0, this.width / 2 - 100, this.height / 4 + 72, StatCollector.getText("deathScreen.respawn", new Object[0])
               )
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  1, this.width / 2 - 100, this.height / 4 + 96, StatCollector.getText("deathScreen.titleScreen", new Object[0])
               )
            );
         if (this.mc.getSession() == null) {
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(1)).enabled3 = false;
         }
      }

      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((AuxiliaryTexturesGuiIconsPngManagerAdapter)iterator.next()).enabled3 = false;
      }

      Mouse.setGrabbed(false);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      switch (auxiliaryTexturesGuiIconsPngManagerAdapter.count2) {
         case 0:
            this.mc.thePlayer.updateState4();
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
            break;
         case 1:
            if (this.mc.theWorld.getRandomSeedManager().isEnabled2()) {
               Minecraft mc = this.mc;
               GuiMainMenu guiMainMenu = new GuiMainMenu();
               mc.displayGuiScreen(guiMainMenu);
            } else {
               DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(
                  this,
                  StatCollector.getText("deathScreen.quit.confirm", new Object[0]),
                  "",
                  StatCollector.getText("deathScreen.titleScreen", new Object[0]),
                  StatCollector.getText("deathScreen.respawn", new Object[0]),
                  0
               );
               this.mc.displayGuiScreen(defaultGuiScreenAdapter);
               defaultGuiScreenAdapter.applyIndex(20);
            }
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public void updateState4() {
      super.updateState4();
      this.count2++;
      if (this.count2 >= 20) {
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)iterator.next()).enabled3 = true;
         }
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (flag) {
         this.mc.theWorld.updateState();
         WorldClient world = null;
         this.mc.loadWorld(world);
         Minecraft mc = this.mc;
         GuiMainMenu guiMainMenu = new GuiMainMenu();
         mc.displayGuiScreen(guiMainMenu);
      } else {
         this.mc.thePlayer.updateState4();
         GuiScreen screen = null;
         this.mc.displayGuiScreen(screen);
      }
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      this.applyIndex(0, 0, this.width, this.height, 1615855616, -1602211792);
      GlStateManager.pushMatrix();
      GlStateManager.translate3f3(2.0F, 2.0F, 2.0F);
      boolean enabled = this.mc.theWorld.getRandomSeedManager().isEnabled2();
      String text = enabled ? StatCollector.getText("deathScreen.title.hardcore", new Object[0]) : StatCollector.getText("deathScreen.title", new Object[0]);
      applyFontRenderer4(this.fontRenderer, text, this.width / 2 / 2, 30, 16777215);
      GlStateManager.popMatrix();
      if (enabled) {
         applyFontRenderer4(this.fontRenderer, StatCollector.getText("deathScreen.hardcoreInfo", new Object[0]), this.width / 2, 144, 16777215);
      }

      applyFontRenderer4(
         this.fontRenderer,
         StatCollector.getText("deathScreen.score", new Object[0]) + ": " + ClientSettingsBlackType.YELLOW + this.mc.thePlayer.getCount6(),
         this.width / 2,
         100,
         16777215
      );
      super.applyIndex(count, count3, factor);
   }
}
