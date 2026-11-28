package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuRecipient;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.gui.spectator.categories.SpectatorDetails;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class GuiSpectator extends Gui implements ISpectatorMenuRecipient {
   private static final ResourceLocation WIDGETS_TEXTURE = new ResourceLocation("textures/gui/widgets.png");
   public static final ResourceLocation SPECTATOR_WIDGETS_TEXTURE = new ResourceLocation("textures/gui/spectator_widgets.png");
   private final Minecraft mc;
   private long lastSelectionTime;
   private SpectatorMenu menu;

   public void renderSelectedItem(ScaledResolution sr) {
      int n = (int)(this.getHotbarAlpha() * 255.0F);
      if (n > 3 && this.menu != null) {
         ISpectatorMenuObject selectedItem = this.menu.getSelectedItem();
         String str = selectedItem != SpectatorMenu.EMPTY_SLOT ? selectedItem.getSpectatorName().getFormattedText() : this.menu.getCategory().getPrompt().getFormattedText();
         if (str != null) {
            int n2 = (sr.getScaledWidth() - this.mc.fontRendererObj.getStringWidth(str)) / 2;
            int n3 = sr.getScaledHeight() - 35;
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            this.mc.fontRendererObj.drawStringWithShadow(str, (float)n2, (float)n3, 16777215 + (n << 24));
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
         }
      }
   }

   public void onMouseScroll(int n) {
      int n2 = this.menu.getSelectedSlot() + n;

      while (n2 >= 0 && n2 <= 8 && (this.menu.getItem(n2) == SpectatorMenu.EMPTY_SLOT || !this.menu.getItem(n2).isEnabled())) {
         n2 += n;
      }

      if (n2 >= 0 && n2 <= 8) {
         this.menu.selectSlot(n2);
         this.lastSelectionTime = Minecraft.getSystemTime();
      }
   }

   public void renderTooltip(ScaledResolution sr, float f) {
      if (this.menu != null) {
         float hotbarAlpha = this.getHotbarAlpha();
         if (hotbarAlpha <= 0.0F) {
            this.menu.exit();
         } else {
            int n = sr.getScaledWidth() / 2;
            float zLevel2 = this.zLevel;
            this.zLevel = -90.0F;
            this.renderPage(sr, hotbarAlpha, n, (float)sr.getScaledHeight() - 22.0F * hotbarAlpha, this.menu.getCurrentPage());
            this.zLevel = zLevel2;
         }
      }
   }

   public void onMiddleClick() {
      this.lastSelectionTime = Minecraft.getSystemTime();
      if (this.isMenuActive()) {
         int selectedSlot = this.menu.getSelectedSlot();
         if (selectedSlot != -1) {
            this.menu.selectSlot(selectedSlot);
         }
      } else {
         this.menu = new SpectatorMenu(this);
      }
   }

   public void renderPage(ScaledResolution sr, float f, int n, float f2, SpectatorDetails spectatorDetails) {
      GlStateManager.enableRescaleNormal();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(1.0F, 1.0F, 1.0F, f);
      this.mc.getTextureManager().bindTexture(WIDGETS_TEXTURE);
      this.drawTexturedModalRect((float)(n - 91), f2, 0, 0, 182, 22);
      if (spectatorDetails.getSelectedSlot() >= 0) {
         this.drawTexturedModalRect((float)(n - 91 - 1 + spectatorDetails.getSelectedSlot() * 20), f2 - 1.0F, 0, 22, 24, 22);
      }

      RenderHelper.enableGUIStandardItemLighting();

      for (int i = 0; i < 9; i++) {
         this.renderSlotIcon(i, sr.getScaledWidth() / 2 - 90 + i * 20 + 2, f2 + 3.0F, f, spectatorDetails.getObject(i));
      }

      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.disableBlend();
   }

   private float getHotbarAlpha() {
      return MathHelper.clamp_float((float)(this.lastSelectionTime - Minecraft.getSystemTime() + 5000L) / 2000.0F, 0.0F, 1.0F);
   }

   public void onHotbarSelected(int n) {
      this.lastSelectionTime = Minecraft.getSystemTime();
      if (this.menu != null) {
         this.menu.selectSlot(n);
      } else {
         this.menu = new SpectatorMenu(this);
      }
   }

   private void renderSlotIcon(int n, int n2, float f, float f2, ISpectatorMenuObject spectatorMenuObject) {
      this.mc.getTextureManager().bindTexture(SPECTATOR_WIDGETS_TEXTURE);
      if (spectatorMenuObject != SpectatorMenu.EMPTY_SLOT) {
         int n3 = (int)(f2 * 255.0F);
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)n2, f, 0.0F);
         float f3 = spectatorMenuObject.isEnabled() ? 1.0F : 0.25F;
         GlStateManager.color(f3, f3, f3, f2);
         spectatorMenuObject.renderIcon(f3, n3);
         GlStateManager.popMatrix();
         String str = String.valueOf(GameSettings.getKeyDisplayString(this.mc.gameSettings.keyBindsHotbar[n].getKeyCode()));
         if (n3 > 3 && spectatorMenuObject.isEnabled()) {
            this.mc
               .fontRendererObj
               .drawStringWithShadow(str, (float)(n2 + 19 - 2 - this.mc.fontRendererObj.getStringWidth(str)), f + 6.0F + 3.0F, 16777215 + (n3 << 24));
         }
      }
   }

   @Override
   public void onSpectatorMenuClosed(SpectatorMenu spectatorMenu) {
      this.menu = null;
      this.lastSelectionTime = 0L;
   }

   public GuiSpectator(Minecraft mc2) {
      this.mc = mc2;
   }

   public boolean isMenuActive() {
      return this.menu != null;
   }
}
