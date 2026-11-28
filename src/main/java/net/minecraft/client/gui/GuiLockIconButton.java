package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiLockIconButton extends GuiButton {
   private boolean locked = false;

   public void setLocked(boolean flag) {
      this.locked = flag;
   }

   public boolean isLocked() {
      return this.locked;
   }

   @Override
   public void drawButton(Minecraft mc, int n, int n2) {
      if (this.visible) {
         mc.getTextureManager().bindTexture(GuiButton.buttonTextures);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = n >= this.xPosition && n2 >= this.yPosition && n < this.xPosition + this.width && n2 < this.yPosition + this.height;
         GuiLockIconButton.Icon icon;
         if (this.locked) {
            if (!this.enabled) {
               icon = GuiLockIconButton.Icon.LOCKED_DISABLED;
            } else if (flag) {
               icon = GuiLockIconButton.Icon.LOCKED_HOVER;
            } else {
               icon = GuiLockIconButton.Icon.LOCKED;
            }
         } else if (!this.enabled) {
            icon = GuiLockIconButton.Icon.UNLOCKED_DISABLED;
         } else if (flag) {
            icon = GuiLockIconButton.Icon.UNLOCKED_HOVER;
         } else {
            icon = GuiLockIconButton.Icon.UNLOCKED;
         }

         this.drawTexturedModalRect(this.xPosition, this.yPosition, icon.getIconX(), icon.getIconY(), this.width, this.height);
      }
   }

   public GuiLockIconButton(int n, int n2, int n3) {
      super(n, n2, n3, 20, 20, "");
   }


   public enum Icon {
      LOCKED(0, 146),
      LOCKED_HOVER(0, 166),
      LOCKED_DISABLED(0, 186),
      UNLOCKED(20, 146),
      UNLOCKED_HOVER(20, 166),
      UNLOCKED_DISABLED(20, 186);

   private final int iconX;
      private final int iconY;
   public int getIconX() {
         return this.iconX;
      }

      private Icon(int n, int n2) {
         this.iconX = n;
         this.iconY = n2;
      }

      public int getIconY() {
         return this.iconY;
      }
   }
}
