package net.augustus.settings;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.clickgui.MainClickGuiWindow;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import net.augustus.modules.AbstractFeature;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.misc.Dummy;

public abstract class Setting extends AbstractFeature {
   private boolean dispatchingUserChange;
   public int boundsWidth;
   public boolean focused;
   public static boolean notifyListenersOnConfigLoad;
   private static HashMap legacyNameCache;
   public int renderX;
   private String displayName;
   public int lastMouseX;
   private long hoverStartTimeMs;
   public SettingVisibilityCondition visibilityCondition;
   public int tooltipAnchorX;
   private static HashMap legacyValueCache;
   public int lastMouseY;
   private String description;
   public CustomFontUtil renderFont;
   private boolean cachedVisible;
   public boolean tooltipHovered;
   public int contentWidth;
   public int boundsHeight;
   public SettingChangeListener changeListener;
   public int renderY;
   private boolean dispatchingChange;
   private boolean visualOnly;
   public SettingChangeListener userChangeListener;
   private String translatedName;
   private final SettingGroup parentGroup;
   private boolean labelDirty;
   public int tooltipAnchorY;

   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
   }

   public String getDisplayName() {
      return this.translatedName.isEmpty() ? this.displayName : this.translatedName;
   }

   public Setting withChangeListener(SettingChangeListener listener) {
      this.changeListener = listener;
      return this;
   }

   public synchronized void notifyValueChanged() {
      this.dispatchChangeListener(this.changeListener);
   }

   public void applyTranslation(String str, String str2) {
      this.labelDirty = true;
      if (str2.equals("English_Default")) {
         this.translatedName = "";
      } else {
         this.translatedName = str2;
      }
   }

   public SettingGroup getParentGroup() {
      return this.parentGroup;
   }

   public abstract void resetToDefault();

   public boolean hasDescription() {
      return this.description.length() > 0;
   }

   public void setRenderFont(CustomFontUtil customFontUtil) {
      this.renderFont = customFontUtil;
   }

   public Setting(Module module, SettingGroup doubleValue, String str, boolean flag) {
       super(module, str);
      this.description = "";
      this.visibilityCondition = null;
      this.changeListener = null;
      this.userChangeListener = null;
      this.dispatchingChange = false;
      this.dispatchingUserChange = false;
      this.cachedVisible = false;
      this.visualOnly = false;
      this.translatedName = "";
      this.labelDirty = false;
      this.contentWidth = 0;
      this.renderX = -1000;
      this.renderY = -1000;
      this.boundsWidth = -1000;
      this.boundsHeight = -1000;
      this.renderFont = FontManager.consolas30;
      this.hoverStartTimeMs = 0L;
      this.parentGroup = doubleValue;
      if (module != null && !(module instanceof Dummy) && flag) {
         ClientManagers.settingManager.registerSetting(this);
         ArrayList list = doubleValue != null ? doubleValue.getChildren() : module.getSettings();
         list.removeIf(obj -> this.matchesConfigName((Setting)obj));
         list.add(this);
      }

      if (module != null) {
         this.visualOnly = module.getCategorys() == Categorys.RENDER || module.getCategorys() == Categorys.FUN;
      }

      this.displayName = str;
   }

   public boolean isFocused() {
      return this.focused;
   }

   public void dispatchChangeListener(SettingChangeListener listener) {
      if (listener != null && !this.dispatchingChange) {
         this.dispatchingChange = true;
         MainClickGuiWindow.enqueueSettingChangeListener(() -> {
            listener.onChanged();
            this.dispatchingChange = false;
         });
      }
   }

   static {
      notifyListenersOnConfigLoad = false;
      legacyNameCache = new HashMap();
      legacyValueCache = new HashMap();
   }

   public void setFocused(boolean flag) {
      this.focused = flag;
   }

   public void updateVisibility() {
   }

   public Setting withDisplayName(String str) {
      this.displayName = str;
      return this;
   }

   public void setDisplayName(String str) {
      this.displayName = str;
   }

   public Setting(Module module, SettingGroup doubleValue, String str) {
       this(module, doubleValue, str, true);
   }

   public Setting withUserChangeListener(SettingChangeListener listener) {
      this.userChangeListener = listener;
      return this;
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   public void mouseReleased(int mouseX, int mouseY, int state) {
   }

   public int getHeight() {
      return 25;
   }

   public boolean isVisualOnly() {
      return this.visualOnly;
   }

   public void onGuiClosed() {
   }

   public void setTranslatedName(String str) {
      this.translatedName = str;
   }

   public void setDescription(String str) {
      this.description = str;
   }

   public synchronized void notifyUserChanged() {
      this.dispatchUserChangeListener(this.userChangeListener);
   }

   private boolean matchesConfigName(Setting setting) {
      return setting.getName().equals(this.getName());
   }

   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      this.lastMouseX = n;
      this.lastMouseY = n2;
      this.renderX = n3;
      this.renderY = n4;
      this.boundsWidth = n5;
      this.boundsHeight = n6;
      this.renderFont = customFontUtil;
   }

   public void updateDisplayName(String str) {
      if (!this.displayName.equals(str)) {
         this.displayName = str;
         this.labelDirty = true;
      }
   }

   public boolean isVisible() {
      return this.visibilityCondition == null || this.visibilityCondition.isVisible();
   }

   public void drawDescriptionTooltip(int n) {
      if (this.hasDescription() && Augustus.getInstance().getModuleManager().clickGui.settingDescriptions.getValue()) {
         boolean isHovered = RenderUtil.isHovered((float)(this.renderX - 2), (float)this.renderY, (float)n, this.renderFont.getFontHeight(), this.lastMouseX, this.lastMouseY);
         if (isHovered) {
            if (!this.tooltipHovered) {
               this.hoverStartTimeMs = System.currentTimeMillis();
               this.tooltipAnchorX = -1337;
               this.tooltipAnchorY = -1337;
            }

            if (System.currentTimeMillis() - this.hoverStartTimeMs >= 750L) {
               if (this.tooltipAnchorX == -1337 && this.tooltipAnchorY == -1337) {
                  this.tooltipAnchorX = this.lastMouseX;
                  this.tooltipAnchorY = this.lastMouseY;
               }

               CustomFontUtil renderFont2 = this.renderFont;
               String description2 = this.getDescription();
               int tooltipAnchorY2 = this.tooltipAnchorY;
               MainClickGuiWindow.drawTooltip(renderFont2, description2, this.tooltipAnchorX, tooltipAnchorY2);
            }
         }

         this.tooltipHovered = isHovered;
      }
   }

   public Setting markVisualOnly() {
      this.visualOnly = true;
      return this;
   }

   public boolean isCachedVisible() {
      return this.cachedVisible;
   }

   public void setVisibilityCondition(SettingVisibilityCondition condition) {
      this.visibilityCondition = condition;
   }

   public Setting withVisibility(SettingVisibilityCondition condition) {
      this.visibilityCondition = condition;
      return this;
   }

   public void setParentModule(Module module) {
      this.setFeatureBase(module);
   }

   public String getParentModuleName() {
      return this.getFeatureBase() != null ? ((Module)this.getFeatureBase()).getName() : "";
   }

   public boolean isLabelDirty() {
      return this.labelDirty;
   }

   public Module getParentModule() {
      return (Module)this.getFeatureBase();
   }

   public void setLabelDirty(boolean flag) {
      this.labelDirty = flag;
   }

   public boolean consumeLabelDirty(boolean flag) {
      boolean flag2 = this.labelDirty || flag;
      if (flag2) {
         this.labelDirty = false;
      }

      return flag2;
   }

   @Override
   public void onAfterDeserialize() {
      if (this.changeListener != null && notifyListenersOnConfigLoad) {
         this.changeListener.onChanged();
         this.dispatchingChange = false;
      }
   }

   public String[] getTranslationKeys() {
      return new String[]{this.getName()};
   }

   public void setCachedVisible(boolean flag) {
      this.cachedVisible = flag;
   }

   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      return false;
   }

   public int getContentWidth() {
      return this.contentWidth;
   }

   public abstract void renderTooltip();

   public String getTranslatedName() {
      return this.translatedName;
   }

   public void randomize() {
   }

   public void dispatchUserChangeListener(SettingChangeListener listener) {
      if (listener != null && !this.dispatchingUserChange) {
         this.dispatchingUserChange = true;
         MainClickGuiWindow.enqueueSettingChangeListener(() -> {
            listener.onChanged();
            this.dispatchingUserChange = false;
         });
      }
   }

   public String getDescription() {
      return this.description;
   }

   public void addVisibilityCondition(SettingVisibilityCondition condition) {
      SettingVisibilityCondition visibilityCondition2 = this.visibilityCondition;
      this.visibilityCondition = () -> {
         return (visibilityCondition2 == null || visibilityCondition2.isVisible()) && condition.isVisible();
      };
   }

}
