package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import java.io.IOException;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiScreenOptionsSounds extends GuiScreen {
   private final GameSettings game_settings_4;
   public String title = "Options";
   private String offDisplayString;

   @Override
   public void initGui() {
      int n = 0;
      this.title = I18n.format("options.sounds.title", new Object[0]);
      this.offDisplayString = I18n.format("options.off", new Object[0]);
      this.buttonList
         .add(
            new GuiScreenOptionsSounds.Button(
               this,
               SoundCategory.MASTER.getCategoryId(),
               this.width / 2 - 155 + n % 2 * 160,
               this.height / 6 - 12 + 24 * (n >> 1),
               SoundCategory.MASTER,
               true
            )
         );
      n += 2;

      for (SoundCategory soundCategory : SoundCategory.values()) {
         if (soundCategory != SoundCategory.MASTER && soundCategory != SoundCategory.AUGUSTUS) {
            this.buttonList
               .add(
                  new GuiScreenOptionsSounds.Button(
                     this, soundCategory.getCategoryId(), this.width / 2 - 155 + n % 2 * 160, this.height / 6 - 12 + 24 * (n >> 1), soundCategory, false
                  )
               );
            n++;
         }
      }

      this.buttonList
         .add(
            new GuiScreenOptionsSounds.Button(
               this,
               SoundCategory.AUGUSTUS.getCategoryId(),
               this.width / 2 - 155 + n % 2 * 160,
               this.height / 6 - 12 + 24 * (n >> 1),
               SoundCategory.AUGUSTUS,
               true
            )
         );
      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
   }

   public String getSoundVolume(SoundCategory soundCategory) {
      float soundLevel = this.game_settings_4.getSoundLevel(soundCategory);
      return soundLevel == 0.0F ? this.offDisplayString : (int)(soundLevel * 100.0F) + "%";
   }

   public GuiScreenOptionsSounds(GuiScreen screen, GameSettings gameSettings) {
      super(screen);
      this.game_settings_4 = gameSettings;
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled && button.id == 200) {
         this.mc.gameSettings.saveOptions();
         GuiScreen parentScreen2 = this.parentScreen;
         this.mc.displayGuiScreen(parentScreen2);
      }
   }

   public static GameSettings accessGame_settings_4(GuiScreenOptionsSounds guiScreenOptionsSounds) {
      return guiScreenOptionsSounds.game_settings_4;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }



   public static class Button extends GuiButton {
      private final SoundCategory category;
      private final String categoryName;
      public float volume;
      public boolean pressed;
      public final GuiScreenOptionsSounds soundsScreen;

      @Override
      public boolean mousePressed(Minecraft mc, int n, int n2) {
         if (super.mousePressed(mc, n, n2)) {
            this.volume = (float)(n - (this.xPosition + 4)) / (float)(this.width - 8);
            this.volume = MathHelper.clamp_float(this.volume, 0.0F, 1.0F);
            mc.gameSettings.setSoundLevel(this.category, this.volume);
            mc.gameSettings.saveOptions();
            this.displayString = this.categoryName + ": " + this.soundsScreen.getSoundVolume(this.category);
            this.pressed = true;
            return true;
         } else {
            return false;
         }
      }

      @Override
      public void mouseDragged(Minecraft mc, int n, int n2) {
         if (this.visible) {
            if (this.pressed) {
               this.volume = (float)(n - (this.xPosition + 4)) / (float)(this.width - 8);
               this.volume = MathHelper.clamp_float(this.volume, 0.0F, 1.0F);
               mc.gameSettings.setSoundLevel(this.category, this.volume);
               mc.gameSettings.saveOptions();
               this.displayString = this.categoryName + ": " + this.soundsScreen.getSoundVolume(this.category);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.volume * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.volume * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
         }
      }

      @Override
      public void mouseReleased(int n, int n2) {
         if (this.pressed) {
            if (this.category == SoundCategory.MASTER) {
               float f = 1.0F;
            } else {
               GuiScreenOptionsSounds.accessGame_settings_4(this.soundsScreen).getSoundLevel(this.category);
            }

            this.soundsScreen.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         }

         this.pressed = false;
      }

      @Override
      public int getHoverState(boolean flag) {
         return 0;
      }

      @Override
      public void playPressSound(SoundHandler soundHandler) {
      }

      public Button(GuiScreenOptionsSounds guiScreenOptionsSounds, int n, int n2, int n3, SoundCategory soundCategory, boolean flag) {
         super(n, n2, n3, flag ? 310 : 150, 20, "");
         this.soundsScreen = guiScreenOptionsSounds;
         this.volume = 1.0F;
         this.category = soundCategory;
         this.categoryName = soundCategory == SoundCategory.AUGUSTUS ? "Augustus" : I18n.format("soundCategory." + soundCategory.getCategoryName(), new Object[0]);
         this.displayString = this.categoryName + ": " + guiScreenOptionsSounds.getSoundVolume(soundCategory);
         this.volume = GuiScreenOptionsSounds.accessGame_settings_4(guiScreenOptionsSounds).getSoundLevel(soundCategory);
      }
   }
}
