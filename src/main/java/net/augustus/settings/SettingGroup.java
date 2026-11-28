package net.augustus.settings;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.augustus.modules.Module;
import net.augustus.modules.misc.Debug;
import net.augustus.modules.misc.Dummy;
import net.augustus.font.CustomFontUtil;
import net.augustus.ClientManagers;
import net.augustus.utils.ChatUtil;
import net.augustus.settings.property.BooleanProperty;
import net.augustus.config.ClipboardConfigCodec;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.render.RenderUtil;
import net.augustus.Augustus;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class SettingGroup extends Setting {
   private int maxChildWidth;
   private final BooleanProperty b;
   private float labelWidth;
   private static final Color TREE_LINE_COLOR = new Color(166, 136, 97);
   private final ArrayList visibleChildren;
   private int renderedHeight;
   private static final Color HEADER_COLOR = new Color(217, 156, 46);
   private int visibleChildCount;
   private final ArrayList children;

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      if (this.isExpanded()) {
         int n = 20;

         for (int i = 0; i < this.visibleChildCount && i < this.visibleChildren.size(); i++) {
            Setting setting = (Setting)this.visibleChildren.get(i);
            if (setting.isVisible() && setting.isCachedVisible()) {
               if (this.renderY + n + setting.getHeight() > -20 && this.renderY + n < this.boundsHeight + 20) {
                  setting.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
               }

               n += setting.getHeight();
            }
         }
      }
   }

   public SettingGroup(Module module, String str, SettingGroup doubleValue) {
       super(module, doubleValue, str + "Groupygroupy");
      this.children = new ArrayList();
      this.renderedHeight = 0;
      this.maxChildWidth = 0;
      this.b = new BooleanProperty(this, "B", false);
      this.labelWidth = -1.0F;
      this.visibleChildren = new ArrayList();
      this.visibleChildCount = 0;
      this.withDisplayName(str);
      if (module != null && !(module instanceof Dummy)) {
         ClientManagers.settingManager.registerSetting(this);
         doubleValue.getChildren().removeIf(obj -> this.matchesSubGroupName((Setting)obj));
         doubleValue.getChildren().add(this);
      }
   }

   @Override
   public void resetToDefault() {
      this.b.resetToDefault();
   }

   public SettingGroup visibleWhen(SettingVisibilityCondition condition) {
      return (SettingGroup)super.withVisibility(condition);
   }

   private boolean matchesExpandedGroupName(Setting setting) {
      return setting.getName().equals(this.getName());
   }

   private boolean matchesGroupName(Setting setting) {
      return setting.getName().equals(this.getName());
   }

   public SettingGroup(Module module, String str, boolean flag) {
       super(module, null, str + "Groupygroupy");
      this.children = new ArrayList();
      this.renderedHeight = 0;
      this.maxChildWidth = 0;
      this.b = new BooleanProperty(this, "B", false);
      this.labelWidth = -1.0F;
      this.visibleChildren = new ArrayList();
      this.visibleChildCount = 0;
      this.withDisplayName(str);
      this.setExpanded(flag);
      BooleanProperty booleanProperty = this.b;
      Boolean bool = flag;
      booleanProperty.setDefaultValue(bool);
      if (module != null && !(module instanceof Dummy)) {
         ClientManagers.settingManager.registerSetting(this);
         this.getParentModule().getSettings().removeIf(obj -> this.matchesExpandedGroupName((Setting)obj));
         this.getParentModule().getSettings().add(this);
      }
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      boolean flag = false;
      int n = 20;
      if (this.isExpanded() && Augustus.getInstance().getClickGui().getMainWindow().contextMenuSetting != this) {
         for (int i = 0; i < this.visibleChildCount && i < this.visibleChildren.size(); i++) {
            Setting setting = (Setting)this.visibleChildren.get(i);
            if (setting.isVisible() && setting.isCachedVisible() && (setting.isVisualOnly() || !this.getParentModule().isLoadFromConfig() || Augustus.getInstance().isConfigEditingEnabled())) {
               if (this.renderY + n + setting.getHeight() > -20 && this.renderY + n < this.boundsHeight + 20) {
                  if (setting.mouseClicked(mouseX, mouseY, mouseButton)) {
                     flag = true;
                     break;
                  }
               }

               n += setting.getHeight();
            }
         }
      }

      if (mouseButton == 0 || mouseButton == 1) {
         float f = this.labelWidth + 20.0F;
         if (RenderUtil.isHovered((float)this.renderX, (float)this.renderY, f, this.renderFont.getFontHeight(), mouseX, mouseY)
            && this != Augustus.getInstance().getClickGui().getMainWindow().contextMenuSetting) {
            if (mouseButton == 0) {
               this.setExpanded(!this.isExpanded());
            } else {
               Augustus.getInstance().getClickGui().getMainWindow().contextMenuX = mouseX + 210;
               Augustus.getInstance().getClickGui().getMainWindow().contextMenuY = mouseY + 95;
               Augustus.getInstance().getClickGui().getMainWindow().contextMenuSetting = this;
            }

            flag = true;
         } else if (this == Augustus.getInstance().getClickGui().getMainWindow().contextMenuSetting) {
            if (RenderUtil.isHovered(
               (float)Augustus.getInstance().getClickGui().getMainWindow().contextMenuX, (float)Augustus.getInstance().getClickGui().getMainWindow().contextMenuY, 130.0F, 59.0F, mouseX + 210, mouseY + 95
            )) {
               this.handleContextMenuClick(mouseX + 210, mouseY + 95);
               flag = true;
            } else {
               Augustus.getInstance().getClickGui().getMainWindow().contextMenuSetting = null;
            }
         }
      }

      return flag;
   }


   public boolean hasVisibleChildren() {
      return this.visibleChildCount != 0;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (this.isExpanded()) {
         int n = 20;

         for (int i = 0; i < this.visibleChildCount && i < this.visibleChildren.size(); i++) {
            Setting setting = (Setting)this.visibleChildren.get(i);
            if (setting.isVisible() && setting.isCachedVisible()) {
               if (this.renderY + n + setting.getHeight() > -20 && this.renderY + n < this.boundsHeight + 20) {
                  setting.keyTyped(typedChar, keyCode);
               }

               n += setting.getHeight();
            }
         }
      }
   }

   @Override
   public Setting withVisibility(SettingVisibilityCondition condition) {
      return this.visibleWhen(condition);
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      if (this.isExpanded()) {
         int n = 20;

         for (int i = 0; i < this.visibleChildCount && i < this.visibleChildren.size(); i++) {
            Setting setting = (Setting)this.visibleChildren.get(i);
            if (setting.isVisible() && setting.isCachedVisible()) {
               if (this.renderY + n + setting.getHeight() > -20 && this.renderY + n < this.boundsHeight + 20) {
                  setting.mouseReleased(mouseX, mouseY, state);
               }

               n += setting.getHeight();
            }
         }
      }
   }

   private void handleContextMenuClick(int n, int n2) {
      if (Augustus.getInstance().getClickGui().getMainWindow().contextMenuSetting == this && Augustus.getInstance().isConfigEditingEnabled()) {
         int contextMenuX = Augustus.getInstance().getClickGui().getMainWindow().contextMenuX;
         int contextMenuY = Augustus.getInstance().getClickGui().getMainWindow().contextMenuY;
         if (RenderUtil.isHovered((float)contextMenuX, (float)contextMenuY, 130.0F, 19.0F, n, n2)) {
            if (!ClipboardConfigCodec.importSettingGroupFromClipboard(this.getParentModule(), this)) {
               ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
               ChatUtil.sendChat("Failed to import " + this.getName() + " to clipboard");
            }
         } else if (RenderUtil.isHovered((float)contextMenuX, (float)(contextMenuY + 21), 130.0F, 19.0F, n, n2)) {
            if (!ClipboardConfigCodec.exportSettingGroup(this.getParentModule(), this)) {
               ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
               ChatUtil.sendChat("Failed to export " + this.getName() + " to clipboard");
            }
         }
      }
   }

   public boolean isExpanded() {
      return (Boolean)this.b.getValue();
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip((int)(this.labelWidth + 20.0F));

      for (int i = 0; i < this.visibleChildCount && i < this.visibleChildren.size(); i++) {
         ((Setting)this.visibleChildren.get(i)).renderTooltip();
      }
   }

   public SettingGroup(Module module, String str) {
       super(module, null, str + "Groupygroupy");
      this.children = new ArrayList();
      this.renderedHeight = 0;
      this.maxChildWidth = 0;
      this.b = new BooleanProperty(this, "B", false);
      this.labelWidth = -1.0F;
      this.visibleChildren = new ArrayList();
      this.visibleChildCount = 0;
      this.withDisplayName(str);
      if (module != null && !(module instanceof Dummy)) {
         ClientManagers.settingManager.registerSetting(this);
         this.getParentModule().getSettings().removeIf(obj -> this.matchesGroupName((Setting)obj));
         this.getParentModule().getSettings().add(this);
      }
   }

   public void setExpanded(boolean expanded) {
      BooleanProperty booleanProperty = this.b;
      Boolean bool = expanded;
      booleanProperty.setValue(bool);
   }

   @Override
   public int getHeight() {
      return this.renderedHeight;
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
      String displayName2 = this.getDisplayName();
      boolean flag2 = this.labelWidth == -1.0F;
      int n7;
      if (this.consumeLabelDirty(flag2)) {
         this.labelWidth = (float)((int)customFontUtil.getStringWidth(displayName2));
      }

      if (flag) {
         int hoveredColor = RenderUtil.getHoveredColor(HEADER_COLOR.getRGB(), 0.149, n, n2, this.renderX, this.renderY, (int)(this.labelWidth + 20.0F), (int)customFontUtil.getFontHeight());
         customFontUtil.drawString(displayName2, (double)n3, (double)n4, hoveredColor);
         if (this.isExpanded()) {
            RenderUtil.drawFilledTriangle((float)n3 + this.labelWidth + 11.0F, (float)(n4 + 8), 10.0F, hoveredColor);
         } else {
            GL11.glPushMatrix();
            float f = (float)n3 + this.labelWidth + 11.0F;
            float f2 = (float)(n4 + 8);
            GlStateManager.translate(f, f2, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(-f, -f2, 0.0F);
            RenderUtil.drawFilledTriangle(f, f2, 10.0F, hoveredColor);
            GL11.glPopMatrix();
         }
      }

      this.renderedHeight = 20;
      if (this.isExpanded()) {
         for (int i = 0; i < this.visibleChildCount && i < this.visibleChildren.size(); i++) {
            Setting setting = (Setting)this.visibleChildren.get(i);
            if (setting.isVisible() && setting.isCachedVisible() && (setting.isVisualOnly() || !this.getParentModule().isLoadFromConfig() || Augustus.getInstance().isConfigEditingEnabled())) {
               boolean flag4 = n4 + this.renderedHeight + setting.getHeight() > -20 && n4 + this.renderedHeight < n6 + 20;
               n7 = n3 + 11;
               setting.render(n, n2, n7, n4 + this.renderedHeight, n5 - 10, n6, customFontUtil, flag4 && flag);
               if (setting.equals((Setting)this.visibleChildren.get(this.visibleChildCount - 1))) {
                  RenderUtil.drawRect(
                     (double)(n3 + 5),
                     (double)((float)n4 + customFontUtil.getFontHeight() / 2.0F + (float)this.renderedHeight),
                     (double)(n3 + 8),
                     (double)((float)n4 + customFontUtil.getFontHeight() / 2.0F + (float)this.renderedHeight - 2.0F),
                     TREE_LINE_COLOR.getRGB()
                  );
                  RenderUtil.drawRect(
                     (double)(n3 + 3),
                     (double)((float)n4 + customFontUtil.getFontHeight()),
                     (double)(n3 + 5),
                     (double)((float)n4 + customFontUtil.getFontHeight() / 2.0F + (float)this.renderedHeight),
                     TREE_LINE_COLOR.getRGB()
                  );
               } else {
                  RenderUtil.drawRect(
                     (double)(n3 + 5),
                     (double)((float)n4 + customFontUtil.getFontHeight() / 2.0F + (float)this.renderedHeight),
                     (double)(n3 + 7),
                     (double)((float)n4 + customFontUtil.getFontHeight() / 2.0F + (float)this.renderedHeight - 2.0F),
                     TREE_LINE_COLOR.getRGB()
                  );
                  RenderUtil.drawRect(
                     (double)(n3 + 3),
                     (double)((float)n4 + customFontUtil.getFontHeight()),
                     (double)(n3 + 5),
                     (double)((float)n4 + customFontUtil.getFontHeight() + (float)this.renderedHeight),
                     TREE_LINE_COLOR.getRGB()
                  );
               }

               this.renderedHeight = this.renderedHeight + setting.getHeight();
               if (this.maxChildWidth < setting.getContentWidth()) {
                  this.maxChildWidth = setting.getContentWidth();
               }
            }
         }
      } else {
         this.renderedHeight = 27;
      }
   }

   private boolean matchesSubGroupName(Setting setting) {
      return setting.getName().equals(this.getName());
   }

   public SettingGroup(Module module, String str, boolean flag, SettingGroup doubleValue) {
       super(module, doubleValue, str + "Groupygroupy");
      this.children = new ArrayList();
      this.renderedHeight = 0;
      this.maxChildWidth = 0;
      this.b = new BooleanProperty(this, "B", false);
      this.labelWidth = -1.0F;
      this.visibleChildren = new ArrayList();
      this.visibleChildCount = 0;
      this.withDisplayName(str);
      this.setExpanded(flag);
      BooleanProperty booleanProperty = this.b;
      Boolean bool = flag;
      booleanProperty.setDefaultValue(bool);
      if (module != null && !(module instanceof Dummy)) {
         ClientManagers.settingManager.registerSetting(this);
         doubleValue.getChildren().removeIf(obj -> this.matchesNestedGroupName((Setting)obj));
         doubleValue.getChildren().add(this);
      }
   }

   public ArrayList getChildren() {
      return this.children;
   }

   private boolean matchesNestedGroupName(Setting setting) {
      return setting.getName().equals(this.getName());
   }

   @Override
   public int getContentWidth() {
      return this.maxChildWidth;
   }

   @Override
   public void updateVisibility() {
      super.updateVisibility();
      this.visibleChildCount = 0;

      for (Setting setting : (Iterable<Setting>)(this.children)) {
         setting.setCachedVisible(setting.isVisible());
         setting.setRenderFont(this.renderFont);
         if (setting.isVisible() && setting.isCachedVisible() && (setting.isVisualOnly() || !this.getParentModule().isLoadFromConfig() || Augustus.getInstance().isConfigEditingEnabled())) {
            if (this.visibleChildren.size() <= this.visibleChildCount) {
               this.visibleChildren.add(setting);
            } else {
               this.visibleChildren.set(this.visibleChildCount, setting);
            }

            this.visibleChildCount++;
         }
      }

      for (Setting setting2 : (Iterable<Setting>)(this.children)) {
         setting2.updateVisibility();
      }
   }

}
