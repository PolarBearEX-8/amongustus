package net.minecraft.client.gui;

import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.ui.AugustusGuiScreen;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;

public abstract class GuiSlot {
   public final Minecraft mc;
   public int width;
   public int height;
   public int top;
   public int bottom;
   public int right;
   public int left;
   public final int slotHeight;
   private int scrollUpButtonID;
   private int scrollDownButtonID;
   public int mouseX;
   public int mouseY;
   public boolean centerListVertically = true;
   public int initialClickY = -2;
   public float scrollMultiplier;
   public float amountScrolled;
   public int selectedElement = -1;
   public long lastClicked;
   public boolean visible = true;
   public boolean showSelectionBox = true;
   public boolean hasListHeader;
   public int headerPadding;
   private boolean enabled = true;

   public void setHasListHeader(boolean flag, int n) {
      this.hasListHeader = flag;
      this.headerPadding = n;
      if (!flag) {
         this.headerPadding = 0;
      }
   }

   public void drawSelectionBox(int n, int n2, int n3, int n4) {
      int size = this.getSize();
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();

      for (int i = 0; i < size; i++) {
         int n5 = n2 + i * this.slotHeight + this.headerPadding;
         int n6 = this.slotHeight - 4;
         if (n5 > this.bottom || n5 + n6 < this.top) {
            this.updateItemPos(i, n, n5);
         }

         if (this.showSelectionBox && this.isSelected(i)) {
            int n7 = this.left + (this.width / 2 - this.getListWidth() / 2);
            int n8 = this.left + this.width / 2 + this.getListWidth() / 2;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableTexture2D();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldRenderer.pos((double)n7, (double)(n5 + n6 + 2), 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
            worldRenderer.pos((double)n8, (double)(n5 + n6 + 2), 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
            worldRenderer.pos((double)n8, (double)(n5 - 2), 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
            worldRenderer.pos((double)n7, (double)(n5 - 2), 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
            worldRenderer.pos((double)(n7 + 1), (double)(n5 + n6 + 1), 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos((double)(n8 - 1), (double)(n5 + n6 + 1), 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos((double)(n8 - 1), (double)(n5 - 1), 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos((double)(n7 + 1), (double)(n5 - 1), 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
            instance.draw();
            GlStateManager.enableTexture2D();
         }

         if (!(this instanceof GuiResourcePackList) || n5 >= this.top - this.slotHeight && n5 <= this.bottom) {
            this.drawSlot(i, n, n5, n6, n3, n4);
         }
      }
   }

   public int getAmountScrolled() {
      return (int)this.amountScrolled;
   }

   public void handleMouseInput() {
      if (this.isMouseYWithinSlotBounds(this.mouseY)) {
         if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.mouseY >= this.top && this.mouseY <= this.bottom) {
            int n = (this.width - this.getListWidth()) / 2;
            int n2 = (this.width + this.getListWidth()) / 2;
            int n3 = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
            int n4 = n3 / this.slotHeight;
            if (n4 < this.getSize() && this.mouseX >= n && this.mouseX <= n2 && n4 >= 0 && n3 >= 0) {
               this.elementClicked(n4, false, this.mouseX, this.mouseY);
               this.selectedElement = n4;
            } else if (this.mouseX >= n && this.mouseX <= n2 && n3 < 0) {
               this.clickedHeader(this.mouseX - n, this.mouseY - this.top + (int)this.amountScrolled - 4);
            }
         }

         if (!MouseInputUtil.isButtonDown(0) || !this.getEnabled()) {
            this.initialClickY = -1;
         } else if (this.initialClickY == -1) {
            boolean flag = true;
            if (this.mouseY >= this.top && this.mouseY <= this.bottom) {
               int n5 = (this.width - this.getListWidth()) / 2;
               int n6 = (this.width + this.getListWidth()) / 2;
               int n7 = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
               int n8 = n7 / this.slotHeight;
               if (n8 < this.getSize() && this.mouseX >= n5 && this.mouseX <= n6 && n8 >= 0 && n7 >= 0) {
                  this.elementClicked(n8, n8 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L, this.mouseX, this.mouseY);
                  this.selectedElement = n8;
                  this.lastClicked = Minecraft.getSystemTime();
               } else if (this.mouseX >= n5 && this.mouseX <= n6 && n7 < 0) {
                  this.clickedHeader(this.mouseX - n5, this.mouseY - this.top + (int)this.amountScrolled - 4);
                  flag = false;
               }

               int scrollBarX = this.getScrollBarX();
               int n9 = scrollBarX + 6;
               if (this.mouseX >= scrollBarX && this.mouseX <= n9) {
                  this.scrollMultiplier = -1.0F;
                  int maxScroll = this.getMaxScroll();
                  if (maxScroll < 1) {
                     maxScroll = 1;
                  }

                  int n10 = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());
                  n10 = MathHelper.clamp_int(n10, 32, this.bottom - this.top - 8);
                  this.scrollMultiplier = this.scrollMultiplier / ((float)(this.bottom - this.top - n10) / (float)maxScroll);
               } else {
                  this.scrollMultiplier = 1.0F;
               }

               if (flag) {
                  this.initialClickY = this.mouseY;
               } else {
                  this.initialClickY = -2;
               }
            } else {
               this.initialClickY = -2;
            }
         } else if (this.initialClickY >= 0) {
            this.amountScrolled = this.amountScrolled - (float)(this.mouseY - this.initialClickY) * this.scrollMultiplier;
            this.initialClickY = this.mouseY;
         }

         int eventDWheel = Mouse.getEventDWheel();
         if (eventDWheel != 0) {
            if (eventDWheel > 0) {
               eventDWheel = -1;
            } else if (eventDWheel < 0) {
               eventDWheel = 1;
            }

            this.amountScrolled = this.amountScrolled + (float)(eventDWheel * this.slotHeight / 2);
         }
      }
   }

   public void setEnabled(boolean flag) {
      this.enabled = flag;
   }

   public abstract int getSize();

   public void bindAmountScrolled() {
      this.amountScrolled = MathHelper.clamp_float(this.amountScrolled, 0.0F, (float)this.getMaxScroll());
   }

   public boolean isMouseYWithinSlotBounds(int n) {
      return n >= this.top && n <= this.bottom && this.mouseX >= this.left && this.mouseX <= this.right;
   }

   public int getMaxScroll() {
      return Math.max(0, this.getContentHeight() - (this.bottom - this.top - 4));
   }

   public int getScrollBarX() {
      return this.width / 2 + 124;
   }

   public abstract void drawBackground();

   public GuiSlot(Minecraft mc2, int n, int n2, int n3, int n4, int n5) {
      this.mc = mc2;
      this.width = n;
      this.height = n2;
      this.top = n3;
      this.bottom = n4;
      this.slotHeight = n5;
      this.left = 0;
      this.right = n;
   }

   public void scrollBy(int n) {
      this.amountScrolled += (float)n;
      this.bindAmountScrolled();
      this.initialClickY = -2;
   }

   public int getListWidth() {
      return 220;
   }

   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button.id == this.scrollUpButtonID) {
            this.amountScrolled = this.amountScrolled - (float)(this.slotHeight * 2 / 3);
            this.initialClickY = -2;
            this.bindAmountScrolled();
         } else if (button.id == this.scrollDownButtonID) {
            this.amountScrolled = this.amountScrolled + (float)(this.slotHeight * 2 / 3);
            this.initialClickY = -2;
            this.bindAmountScrolled();
         }
      }
   }

   public void setDimensions(int n, int n2, int n3, int n4) {
      this.width = n;
      this.height = n2;
      this.top = n3;
      this.bottom = n4;
      this.left = 0;
      this.right = n;
   }

   public void renderDecorations(int n, int n2) {
   }

   public void drawListHeader(int n, int n2, Tessellator tessellator) {
   }

   public void drawScreen(int mouseX2, int mouseY2, float partialTicks) {
      if (this.visible) {
         if (Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer) {
            RenderUtil.drawRect((this.left + (this.width / 2 - this.getListWidth() / 2)) - 2, 0, (this.left + this.width / 2 + this.getListWidth() / 2) + 2, this.height, Integer.MIN_VALUE);
         }

         this.mouseX = mouseX2;
         this.mouseY = mouseY2;
         this.drawBackground();
         int scrollBarX = this.getScrollBarX();
         int n3 = scrollBarX + 6;
         this.bindAmountScrolled();
         GlStateManager.disableLighting();
         GlStateManager.disableFog();
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         float f = 32.0F;
         if (this.mc.inGameHasFocus) {
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldRenderer.pos((double)this.left, (double)this.bottom, 0.0)
               .tex((double)((float)this.left / f), (double)((float)(this.bottom + (int)this.amountScrolled) / f))
               .color(32, 32, 32, 255)
               .endVertex();
            worldRenderer.pos((double)this.right, (double)this.bottom, 0.0)
               .tex((double)((float)this.right / f), (double)((float)(this.bottom + (int)this.amountScrolled) / f))
               .color(32, 32, 32, 255)
               .endVertex();
            worldRenderer.pos((double)this.right, (double)this.top, 0.0)
               .tex((double)((float)this.right / f), (double)((float)(this.top + (int)this.amountScrolled) / f))
               .color(32, 32, 32, 255)
               .endVertex();
            worldRenderer.pos((double)this.left, (double)this.top, 0.0)
               .tex((double)((float)this.left / f), (double)((float)(this.top + (int)this.amountScrolled) / f))
               .color(32, 32, 32, 255)
               .endVertex();
            instance.draw();
         }

         int n4 = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
         int n5 = this.top + 4 - (int)this.amountScrolled;
         if (this.hasListHeader) {
            this.drawListHeader(n4, n5, instance);
         }

         AugustusGuiScreen.enableScissor(this.left, this.top, this.right, this.bottom);
         this.drawSelectionBox(n4, n5, mouseX2, mouseY2);
         AugustusGuiScreen.disableScissor();
         GlStateManager.disableDepth();
         byte b = 4;
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
         GlStateManager.disableAlpha();
         GlStateManager.shadeModel(7425);
         GlStateManager.disableTexture2D();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         worldRenderer.pos((double)this.left, (double)(this.top + b), 0.0).tex(0.0, 1.0).color(0, 0, 0, 0).endVertex();
         worldRenderer.pos((double)this.right, (double)(this.top + b), 0.0).tex(1.0, 1.0).color(0, 0, 0, 0).endVertex();
         worldRenderer.pos((double)this.right, (double)this.top, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
         worldRenderer.pos((double)this.left, (double)this.top, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
         instance.draw();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         worldRenderer.pos((double)this.left, (double)this.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
         worldRenderer.pos((double)this.right, (double)this.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
         worldRenderer.pos((double)this.right, (double)(this.bottom - b), 0.0).tex(1.0, 0.0).color(0, 0, 0, 0).endVertex();
         worldRenderer.pos((double)this.left, (double)(this.bottom - b), 0.0).tex(0.0, 0.0).color(0, 0, 0, 0).endVertex();
         instance.draw();
         int maxScroll = this.getMaxScroll();
         if (maxScroll > 0) {
            int n6 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
            n6 = MathHelper.clamp_int(n6, 32, this.bottom - this.top - 8);
            int n7 = (int)this.amountScrolled * (this.bottom - this.top - n6) / maxScroll + this.top;
            if (n7 < this.top) {
               n7 = this.top;
            }

            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldRenderer.pos((double)scrollBarX, (double)this.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos((double)n3, (double)this.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos((double)n3, (double)this.top, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos((double)scrollBarX, (double)this.top, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
            instance.draw();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldRenderer.pos((double)scrollBarX, (double)(n7 + n6), 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
            worldRenderer.pos((double)n3, (double)(n7 + n6), 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
            worldRenderer.pos((double)n3, (double)n7, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
            worldRenderer.pos((double)scrollBarX, (double)n7, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
            instance.draw();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldRenderer.pos((double)scrollBarX, (double)(n7 + n6 - 1), 0.0).tex(0.0, 1.0).color(192, 192, 192, 255).endVertex();
            worldRenderer.pos((double)(n3 - 1), (double)(n7 + n6 - 1), 0.0).tex(1.0, 1.0).color(192, 192, 192, 255).endVertex();
            worldRenderer.pos((double)(n3 - 1), (double)n7, 0.0).tex(1.0, 0.0).color(192, 192, 192, 255).endVertex();
            worldRenderer.pos((double)scrollBarX, (double)n7, 0.0).tex(0.0, 0.0).color(192, 192, 192, 255).endVertex();
            instance.draw();
         }

         BlurShadowManager.enableBlurOnce();
         BlurShadowManager.enableNormalPassOnce();
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> {
            RenderUtil.drawRect(this.left, this.bottom, this.right, this.bottom + this.height, Integer.MIN_VALUE);
            RenderUtil.drawRect(this.left, 0, this.right, this.top, Integer.MIN_VALUE);
         });
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
         GlStateManager.disableAlpha();
         GlStateManager.shadeModel(7425);
         GlStateManager.disableTexture2D();
         this.renderDecorations(mouseX2, mouseY2);
         GlStateManager.enableTexture2D();
         GlStateManager.shadeModel(7424);
         GlStateManager.enableAlpha();
         GlStateManager.disableBlend();
         BlurShadowManager.flushRenderQueues(new ScaledResolution(this.mc), true);
         if (Augustus.getInstance().getModuleManager().notifications.isToggled()) {
            Augustus.getInstance().getModuleManager().notifications.renderNotifications(true);
         }
      }
   }

   public int getContentHeight() {
      return this.getSize() * this.slotHeight + this.headerPadding;
   }

   public void setShowSelectionBox(boolean flag) {
      this.showSelectionBox = flag;
   }

   public void clickedHeader(int n, int n2) {
   }

   public int getSlotHeight() {
      return this.slotHeight;
   }

   public boolean getEnabled() {
      return this.enabled;
   }

   public abstract boolean isSelected(int n);

   public abstract void elementClicked(int n, boolean flag, int n2, int n3);

   public void setSlotXBoundsFromLeft(int n) {
      this.left = n;
      this.right = n + this.width;
   }

   public void updateItemPos(int n, int n2, int n3) {
   }

   public void registerScrollButtons(int n, int n2) {
      this.scrollUpButtonID = n;
      this.scrollDownButtonID = n2;
   }

   public int getSlotIndexFromScreenCoords(int n, int n2) {
      int n3 = this.left + this.width / 2 - this.getListWidth() / 2;
      int n4 = this.left + this.width / 2 + this.getListWidth() / 2;
      int n5 = n2 - this.top - this.headerPadding + (int)this.amountScrolled - 4;
      int n6 = n5 / this.slotHeight;
      return n < this.getScrollBarX() && n >= n3 && n <= n4 && n6 >= 0 && n5 >= 0 && n6 < this.getSize() ? n6 : -1;
   }

   public abstract void drawSlot(int n, int n2, int n3, int n4, int n5, int n6);
}
