package net.augustus.ui.config;

import net.augustus.ClientManagers;
import net.augustus.clickgui.MainClickGuiWindow;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.TimeHelper;
import java.io.IOException;

public abstract class AbstractConfigListItem {
   private final String author;
   private final TimeHelper loadCooldownTimer;
   private int x;
   private int tooltipX;
   private int cachedNameWidth;
   private String updatedTime;
   private final TimeHelper hoverTimer;
   private final TimeHelper doubleClickTimer;
   private int lastMouseX;
   private int tooltipY;
   private int width;
   private CustomFontUtil fontRenderer;
   private int lastMouseY;
   private final boolean editable;
   private boolean rendered;
   private boolean hovered;
   private int y;
   private String updatedDate;
   private String configName;
   private int height;

   public AbstractConfigListItem(String configName, String author, String updatedDate, String updatedTime, boolean editable) {
      this.configName = configName;
      this.author = author;
      this.updatedDate = updatedDate;
      this.updatedTime = updatedTime;
      this.editable = editable;
      this.doubleClickTimer = new TimeHelper();
      this.loadCooldownTimer = new TimeHelper();
      this.hoverTimer = new TimeHelper();
      this.cachedNameWidth = -1;
   }

   public void renderTooltip() {
      if (this.rendered && this.updatedDate != null && this.updatedTime != null && !this.updatedDate.isEmpty() && !this.updatedTime.isEmpty()) {
         boolean isHovered = RenderUtil.isHovered(
            (float)(this.x - 5), (float)this.y, this.fontRenderer.getStringWidth(this.configName) + 10.0F, (float)(this.height + 2), this.lastMouseX, this.lastMouseY
         );
         if (isHovered) {
            if (!this.hovered) {
               this.hoverTimer.reset();
               this.tooltipX = -1337;
               this.tooltipY = -1337;
            }

            if (this.hoverTimer.reached(500L)) {
               if (this.tooltipX == -1337 && this.tooltipY == -1337) {
                  this.tooltipX = this.lastMouseX;
                  this.tooltipY = this.lastMouseY;
               }

               String text = "Updated: " + this.updatedDate + " " + this.updatedTime + " by " + this.author;
               MainClickGuiWindow.drawTooltip(this.fontRenderer, text, this.tooltipX, this.tooltipY);
            }
         }

         this.hovered = isHovered;
      }
   }

   public CustomFontUtil getFontRenderer() {
      return this.fontRenderer;
   }

   public abstract void loadConfig();

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0 && this.getFontRenderer() != null) {
         if (RenderUtil.isHovered((float)(this.getX() - 5), (float)this.getY(), (float)((int)(this.getFontRenderer().getStringWidth(this.getConfigName()) + 10.0F)), (float)(this.getHeight() + 2), mouseX, mouseY)) {
            if (!this.doubleClickTimer.reached(500L)) {
               if (this.loadCooldownTimer.reached(1000L)) {
                  this.loadCooldownTimer.reset();
                  this.loadConfig();
               }
            }
            this.doubleClickTimer.reset();
         }
      }
   }

   public int getY() {
      return this.y;
   }

   public int getX() {
      return this.x;
   }

   public boolean isAuthor(String name) {
      return this.author.equals(name);
   }

   public String getConfigName() {
      return this.configName;
   }

   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
   }

   public int getWidth() {
      return this.width;
   }

   public void mouseReleased(int mouseX, int mouseY, int state) {
   }

   public int getHeight() {
      return this.height;
   }

   public boolean isEditable() {
      return this.editable;
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   public void renderItem(int x, int y, int width, int height, int mouseX, int mouseY, CustomFontUtil fontRenderer, boolean selected) {
      this.x = x;
      this.y = y;
      this.lastMouseX = mouseX;
      this.lastMouseY = mouseY;
      this.width = width;
      this.height = height;
      this.fontRenderer = fontRenderer;
      if (this.cachedNameWidth == -1) {
         this.cachedNameWidth = (int)fontRenderer.getStringWidth(this.configName);
      }

      int baseColor = selected
         ? ClientManagers.moduleManager.clickGui.hoverTextColor.getColorInt((byte)0)
         : ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
      int color = RenderUtil.getHoveredColor(baseColor, 0.2, mouseX, mouseY, x - 5, y, this.cachedNameWidth + 10, height + 2);
      fontRenderer.drawString(this.configName, (double)x, (double)y, color);
      this.rendered = true;
   }
}
