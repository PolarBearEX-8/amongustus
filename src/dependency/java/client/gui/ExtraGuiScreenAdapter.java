package client.gui;

import client.setting.GameSettings;
import java.io.IOException;
import java.net.URI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtraGuiScreenAdapter extends GuiScreen {
   public static final Logger logger2 = LogManager.getLogger();
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/demo_background.png");

   @Override
   public void renderElement3() {
      super.renderElement3();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      int count = (this.width - 248) / 2;
      int count2 = (this.height - 166) / 2;
      this.processAction(count, count2, 0, 0, 248, 166);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      int count3 = (this.width - 248) / 2 + 10;
      int count4 = (this.height - 166) / 2 + 8;
      this.fontRenderer.getIndex(StatCollector.getText("demo.help.title", new Object[0]), count3, count4, 2039583);
      int count5 = count4 + 12;
      GameSettings gameSettings = this.mc.gameSettings;
      this.fontRenderer
         .getIndex(
            StatCollector.getText(
               "demo.help.movementShort",
               new Object[]{
                  GameSettings.resolveText(gameSettings.keyBindForward.getKeyCode()),
                  GameSettings.resolveText(gameSettings.keyBindLeft.getKeyCode()),
                  GameSettings.resolveText(gameSettings.keyBindBack.getKeyCode()),
                  GameSettings.resolveText(gameSettings.keyBindRight.getKeyCode())
               }
            ),
            count3,
            count5,
            5197647
         );
      this.fontRenderer.getIndex(StatCollector.getText("demo.help.movementMouse", new Object[0]), count3, count5 + 12, 5197647);
      this.fontRenderer
         .getIndex(StatCollector.getText("demo.help.jump", new Object[]{GameSettings.resolveText(gameSettings.keyBindJump.getKeyCode())}), count3, count5 + 24, 5197647);
      this.fontRenderer
         .getIndex(
            StatCollector.getText("demo.help.inventory", new Object[]{GameSettings.resolveText(gameSettings.keyBindInventory.getKeyCode())}), count3, count5 + 36, 5197647
         );
      this.fontRenderer.applyText(StatCollector.getText("demo.help.fullWrapped", new Object[0]), count3, count5 + 68, 218, 2039583);
      super.applyIndex(count, count2, factor);
   }

   @Override
   public void updateState4() {
      super.updateState4();
   }

   @Override
   public void updateState2() {
      this.list.clear();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 116, this.height / 2 + 62 + -16, 114, 20, StatCollector.getText("demo.help.buy", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               2, this.width / 2 + 2, this.height / 2 + 62 + -16, 114, 20, StatCollector.getText("demo.help.later", new Object[0])
            )
         );
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      switch (auxiliaryTexturesGuiIconsPngManagerAdapter.count2) {
         case 1:
            auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = false;

            try {
               Class clazz = Class.forName("java.awt.Desktop");
               Object entry = clazz.getMethod("getDesktop").invoke(null);
               clazz.getMethod("browse", URI.class).invoke(entry, new URI("http://www.minecraft.net/store?source=demo"));
            } catch (Throwable throwable) {
               logger2.error("Couldn't open link", throwable);
            }
            break;
         case 2:
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
            this.mc.updateState21();
      }
   }
}
