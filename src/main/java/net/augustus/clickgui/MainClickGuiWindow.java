package net.augustus.clickgui;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.config.ClipboardConfigCodec;
import net.augustus.events.listeners.ClickGuiListener;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.settings.Setting;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.SettingVisibilityRegistry;
import net.augustus.settings.property.StringProperty;
import net.augustus.ui.widgets.SmoothScrollBar;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.audio.ClientSounds;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.ModuleManager;
import net.augustus.modules.misc.ClientAntiCheat;
import net.augustus.modules.RotationModule;
import net.augustus.modules.EntityRotationModule;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class MainClickGuiWindow extends ClickGuiWindow {
   public int tooltipX;
   public int tooltipY;
   private CategoryTabButton contextMenuCategory;
   private boolean hoveringModuleContextMenu;
   private final StringProperty selectedModuleProperty;
   private boolean hoveringCategoryContextMenu;
   private CategoryTabButton selectedCategory;
   private final TimeHelper timeHelper;
   private CustomFontUtil moduleFont;
   public SettingGroup contextMenuSetting;
   private ModuleSettingsPanel settingsPanel;
   private Module hoveredModule;
   private int moduleScrollOffset;
   private Module contextMenuModule;
   public Module selectedModule;
   private final ArrayList categoryTabs;
   public int contextMenuY;
   public int contextMenuX;
   private static final Queue PENDING_SETTING_LISTENERS = new ConcurrentLinkedQueue();
   private int categoryScrollOffset;
   private SmoothScrollBar categoryScrollBar;
   private final StringProperty selectedCategoryProperty;
   private SmoothScrollBar moduleScrollBar;

   private void handleCategoryContextMenuClick(int n, int n2) {
      if (this.contextMenuCategory != null) {
         if (Augustus.getInstance().isConfigEditingEnabled()) {
            if (RenderUtil.isHovered((float)this.contextMenuX, (float)this.contextMenuY, 130.0F, 19.0F, n, n2)) {
               Categorys categorys = this.contextMenuCategory.getCategorys();
               if (!ClipboardConfigCodec.importCategory(categorys)) {
                  ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
                  ChatUtil.sendChat("Failed to import " + categorys.name() + " to clipboard");
               }
            } else if (RenderUtil.isHovered((float)this.contextMenuX, (float)(this.contextMenuY + 21), 130.0F, 19.0F, n, n2)) {
               if (!ClipboardConfigCodec.exportCategory(this.contextMenuCategory.getCategorys())) {
                  ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
                  ChatUtil.sendChat("Failed to export " + this.contextMenuCategory.getCategorys().name() + " to clipboard");
               }
            } else if (RenderUtil.isHovered((float)this.contextMenuX, (float)(this.contextMenuY + 40), 130.0F, 19.0F, n, n2) && this.selectedCategory != null) {
               int n3 = 0;
               List modulesByCategory = ClientManagers.moduleManager.getModulesByCategory(this.selectedCategory.getCategorys());

               for (Module module : (Iterable<Module>)(modulesByCategory)) {
                  n3 += module.isLoadFromConfig() ? 1 : 0;
               }

               boolean flag3 = (double)n3 / (double)modulesByCategory.size() < 0.5;

               for (Module module2 : (Iterable<Module>)(modulesByCategory)) {
                  module2.setLoadFromConfig(flag3);
               }
            } else if (RenderUtil.isHovered((float)this.contextMenuX, (float)(this.contextMenuY + 59), 130.0F, 19.0F, n, n2) && this.selectedCategory != null) {
               int n4 = 0;
               List modulesByCategory2 = ClientManagers.moduleManager.getModulesByCategory(this.selectedCategory.getCategorys());

               for (Module module3 : (Iterable<Module>)(modulesByCategory2)) {
                  n4 += module3.isShownInArrayList() ? 1 : 0;
               }

               boolean flag4 = (double)n4 / (double)modulesByCategory2.size() < 0.5;

               for (Module module4 : (Iterable<Module>)(modulesByCategory2)) {
                  module4.setShowInArrayList(flag4);
               }
            }

            return;
         }

      }
   }

   public static void enqueueSettingChangeListener(SettingChangeListener listener) {
      PENDING_SETTING_LISTENERS.add(listener);
   }

   @Override
   public void drawWindow(int n, int n2, float f) {
      if (this.isVisible()) {
         SettingVisibilityRegistry.registerAll();
         ClickGuiListener.postClickGuiUpdate();

         for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getModules())) {
            if (module instanceof RotationModule) {
               ((RotationModule)module).initVisibilityConditions();
            } else if (module instanceof EntityRotationModule) {
               ((EntityRotationModule)module).initVisibilityConditions();
            }
         }

         super.drawWindow(n, n2, f);
         ScaledResolution sr = new ScaledResolution(mc);
         GL11.glPushMatrix();
         GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
         n *= sr.getScaleFactor();
         n2 *= sr.getScaleFactor();
         GL11.glPushMatrix();
         n -= this.getX();
         n2 -= this.getY();
         GL11.glTranslatef((float)this.getX(), (float)this.getY(), 0.0F);
         GL11.glEnable(3089);
         RenderUtil.scissorRectScaled(
            (float)this.getX(),
            (float)this.getY(),
            (float)(this.getX() + this.getWidth() - 150),
            (float)(this.getY() + this.getHeight()),
            1.0
         );
         if (!ClickGuiScreen.configEditable || ClickGuiScreen.protectedConfigCreator) {
            this.moduleFont.drawString("config protected but feel free to edit :)", 120.0, 6.0, Color.GREEN.getRGB());
         }

         int colorInt = ClientManagers.moduleManager.clickGui.headerColor.getColorInt((byte)0);
         int colorInt2 = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         GL11.glDisable(3089);
         RenderUtil.drawLine(200, 90, this.getWidth(), 90, 3.0F, colorInt);
         RenderUtil.drawLine(200, 30, 200, this.getHeight(), 3.0F, colorInt);
         if (!this.isFullScreen() && ClientManagers.moduleManager.clickGui.outline.getValue()) {
            float width2 = (float)this.getWidth();
            float height2 = (float)this.getHeight();
            float f2 = this.isFullScreen() ? 0.0F : (float)this.cornerRadius;
            RenderUtil.drawRoundedRectOutline(0.0F, 0.0F, width2, height2, f2, 1, 1, ClientManagers.moduleManager.clickGui.outlineColor.getColorInt((byte)0));
         }

         GL11.glEnable(3089);
         RenderUtil.scissorRectScaled(
            (float)this.getX(),
            (float)this.getY(),
            (float)(this.getX() + this.getWidth()),
            (float)(this.getY() + this.getHeight()),
            1.0
         );
         RenderUtil.scissorRectScaled(
            (float)(this.getX() + 210),
            (float)(this.getY() + 30),
            (float)(this.getX() + this.getWidth() - this.cornerRadius),
            (float)(this.getY() + 90),
            1.0
         );
         int n3 = 0;
         this.hoveringModuleContextMenu = this.contextMenuModule != null && RenderUtil.isHovered((float)this.contextMenuX, (float)this.contextMenuY, 130.0F, 59.0F, n, n2);
         this.hoveringCategoryContextMenu = this.contextMenuCategory != null && RenderUtil.isHovered((float)this.contextMenuX, (float)this.contextMenuY, 130.0F, 78.0F, n, n2);

         for (CategoryTabButton categoryTabButton : (Iterable<CategoryTabButton>)(this.categoryTabs)) {
            categoryTabButton.drawTab(n, n2, 220 + n3 + this.categoryScrollOffset, (byte)50, this.hoveringModuleContextMenu);
            n3 += categoryTabButton.getTextWidth() + 30;
         }

         RenderUtil.drawLine(
            this.selectedCategory.getX(), this.selectedCategory.bottom - this.selectedCategory.getPaddingY(), this.selectedCategory.right - this.selectedCategory.getPaddingX(), this.selectedCategory.bottom - this.selectedCategory.getPaddingY(), 2.0F, colorInt2
         );
         SmoothScrollBar categoryScrollBar2 = this.categoryScrollBar;
         int n6 = this.getWidth() - this.cornerRadius;
         int n7 = 220 - this.categoryScrollOffset;
         int n8 = ((CategoryTabButton)this.categoryTabs.get(this.categoryTabs.size() - 1)).right - 220;
         categoryScrollBar2.drawHorizontalBar((short)220, (byte)75, n6, (byte)85, n7, n8, colorInt);
         SmoothScrollBar categoryScrollBar3 = this.categoryScrollBar;
         int n12 = ((CategoryTabButton)this.categoryTabs.get(this.categoryTabs.size() - 1)).right - 220;
         int n13 = this.getWidth() - this.cornerRadius;
         n8 = this.categoryScrollOffset;
         int n14 = this.getWidth() - this.cornerRadius - 220;
         this.categoryScrollOffset = categoryScrollBar3.updateScrollOffset(n12, (short)220, n13, n8, (short)220, (byte)35, n14, (byte)50, n, n2);
         RenderUtil.scissorRectScaled(
            (float)this.getX(),
            (float)(this.getY() + 30),
            (float)(this.getX() + 195),
            (float)(this.getY() + this.getHeight() - this.cornerRadius),
            1.0
         );
         int n19 = 0;

         for (Module module2 : (Iterable<Module>)(this.selectedCategory.getModules())) {
            if (!module2.isHiddenInGui()) {
               n7 = 40 + n19 + this.moduleScrollOffset;
               boolean flag2 = this.hoveringModuleContextMenu;
               CustomFontUtil moduleFont4 = this.moduleFont;
               module2.renderInClickGui(n, n2, (byte)20, n7, this.selectedModule, moduleFont4, flag2);
               n19 += (int)this.moduleFont.getFontHeight() + 5;
               if (!module2.equals(this.selectedModule)) {
                  module2.setBindingKey(false);
               }
            }
         }

         categoryScrollBar2 = this.moduleScrollBar;
         n13 = this.getHeight() - this.cornerRadius;
         categoryScrollBar2.drawVerticalBar((short)185, (byte)40, (short)195, n13, 40 - this.moduleScrollOffset, n19, colorInt);
         SmoothScrollBar moduleScrollBar2 = this.moduleScrollBar;
         n13 = this.getHeight() - this.cornerRadius;
         n8 = this.moduleScrollOffset;
         int cornerRadius2 = this.cornerRadius;
         n14 = 200 - this.cornerRadius;
         this.moduleScrollOffset = moduleScrollBar2.updateScrollOffset(n19, (byte)40, n13, n8, cornerRadius2, (byte)30, n14, this.getHeight() - this.cornerRadius - 30, n, n2);
         GL11.glPushMatrix();
         RenderUtil.scissorRectScaled(
            (float)(this.getX() + 210),
            (float)(this.getY() + 95),
            (float)(this.getX() + this.getWidth() - this.cornerRadius),
            (float)(this.getY() + this.getHeight() - this.cornerRadius),
            1.0
         );
         if (Augustus.getInstance().getMouseWheelTracker().lastDelta != 0
            && RenderUtil.isHovered(210.0F, 95.0F, (float)(this.getWidth() - this.cornerRadius), (float)(this.getHeight() - this.cornerRadius), n, n2)) {
            this.contextMenuSetting = null;
         }

         GL11.glTranslatef(210.0F, 95.0F, 0.0F);
         ModuleSettingsPanel settingsPanel2 = this.settingsPanel;
         int n28 = n - 210;
         n12 = n2 - 95;
         n6 = this.getWidth() - 210 - this.cornerRadius;
         n13 = this.getHeight() - 95 - this.cornerRadius;
         n7 = this.getX() + 210;
         n8 = this.getY() + 95;
         boolean flag4 = this.hoveringModuleContextMenu;
         Module selectedModule4 = this.selectedModule;
         settingsPanel2.drawPanel(n28, n12, n6, n13, n7, n8, this.cornerRadius, selectedModule4, flag4);
         GL11.glTranslatef(-210.0F, -95.0F, 0.0F);
         GL11.glDisable(3089);
         n19 = 0;
         if (ClientManagers.moduleManager.clickGui.moduleDescriptions.getValue()) {
            n7 = this.getHeight() - this.cornerRadius - 30;
            if (this.isMouseOver((byte)0, (byte)30, (short)195, n7, n, n2)) {
               if (Augustus.getInstance().getMouseWheelTracker().lastDelta != 0) {
                  this.contextMenuModule = null;
               }

               boolean flag5 = false;

               for (Module module3 : (Iterable<Module>)(this.selectedCategory.getModules())) {
                  if (!module3.isHiddenInGui()) {
                     n6 = 40 + n19 + this.moduleScrollOffset;
                     n7 = (int)(this.moduleFont.getFontHeight() + 2.0F);
                     if (this.isMouseOver((byte)18, n6, (short)160, n7, n, n2)) {
                        flag5 = true;
                        if (this.hoveredModule == null || !this.hoveredModule.equals(module3)) {
                           this.timeHelper.reset();
                           this.tooltipX = -1337;
                           this.tooltipY = -1337;
                        }

                        this.hoveredModule = module3;
                     }

                     if (!module3.getDescription().isEmpty()) {
                        if (this.timeHelper.reached(750L)) {
                           n6 = 40 + n19 + this.moduleScrollOffset;
                           n7 = (int)(this.moduleFont.getFontHeight() + 2.0F);
                           if (this.isMouseOver((byte)18, n6, (short)160, n7, n, n2)) {
                              if (this.tooltipX == -1337 && this.tooltipY == -1337) {
                                 this.tooltipX = n;
                                 this.tooltipY = n2;
                              }

                              CustomFontUtil consolas30 = FontManager.consolas30;
                              String description = module3.getDescription();
                              int tooltipY2 = this.tooltipY;
                              drawTooltip(consolas30, description, this.tooltipX, tooltipY2);
                           }
                        }
                     }

                     n19 += (int)(this.moduleFont.getFontHeight() + 5.0F);
                  }
               }

               if (!flag5) {
                  this.timeHelper.reset();
                  this.tooltipX = -1337;
                  this.tooltipY = -1337;
               }
            }
         }

         if (this.contextMenuModule != null) {
            this.drawModuleContextMenu(n, n2);
         }

         if (this.contextMenuCategory != null) {
            this.drawCategoryContextMenu(n, n2);
         }

         if (this.contextMenuSetting != null) {
            this.drawSettingContextMenu(n, n2);
         }

         GL11.glPopMatrix();
         GL11.glDisable(3089);
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }

   public void updatePanel() {

      while (!PENDING_SETTING_LISTENERS.isEmpty()) {
         SettingChangeListener listener = (SettingChangeListener)PENDING_SETTING_LISTENERS.poll();
         if (listener != null) {
            listener.onChanged();
         }
      }

      for (Setting setting : (Iterable<Setting>)(this.selectedModule.getSettings())) {
         setting.updateVisibility();
      }
   }

   private void handleModuleContextMenuClick(int n, int n2) {
      if (this.contextMenuModule != null) {
         if (Augustus.getInstance().isConfigEditingEnabled()) {
            if (RenderUtil.isHovered((float)this.contextMenuX, (float)this.contextMenuY, 130.0F, 19.0F, n, n2)) {
               if (!ClipboardConfigCodec.importModule(this.contextMenuModule)) {
                  ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
                  ChatUtil.sendChat("Failed to import " + this.contextMenuModule.getName() + " to clipboard");
               }
            } else if (RenderUtil.isHovered((float)this.contextMenuX, (float)(this.contextMenuY + 21), 130.0F, 19.0F, n, n2)) {
               if (!ClipboardConfigCodec.exportModule(this.contextMenuModule)) {
                  ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
                  ChatUtil.sendChat("Failed to export " + this.contextMenuModule.getName() + " to clipboard");
               }
            }

            return;
         }

      }
   }

   private void drawSettingContextMenu(int n, int n2) {
      if (!Augustus.getInstance().isConfigEditingEnabled()) {
      } else {
         RenderUtil.drawRect(this.contextMenuX, this.contextMenuY, this.contextMenuX + 130, this.contextMenuY + 40, 40, 39, 3);
         RenderUtil.drawRectOutline((double)this.contextMenuX, (double)this.contextMenuY, (double)(this.contextMenuX + 130), (double)(this.contextMenuY + 40), 1.0F, Color.BLACK);
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         CustomFontUtil consolas30 = FontManager.consolas30;
         double d = (double)(this.contextMenuX + 2);
         double d2 = (double)(this.contextMenuY + 2);
         int hoveredColor = RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, this.contextMenuX, this.contextMenuY, 130, 19);
         double d3 = d2;
         double d4 = d;
         consolas30.drawString("Import Settings", d4, d3, hoveredColor);
         consolas30 = FontManager.consolas30;
         d = (double)(this.contextMenuX + 2);
         d2 = (double)(this.contextMenuY + 2 + 19);
         int hoveredColor2 = RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, this.contextMenuX, this.contextMenuY + 21, 130, 19);
         double d5 = d2;
         double d6 = d;
         consolas30.drawString("Export Settings", d6, d5, hoveredColor2);
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.settingsPanel.keyTyped(typedChar, keyCode);
      ClickGuiListener.postClickGuiRender();
   }

   public void onClose() {
      this.settingsPanel.onClose();
      this.contextMenuModule = null;
      this.contextMenuCategory = null;
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      ScaledResolution sr = new ScaledResolution(mc);
      GL11.glPushMatrix();
      GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
      mouseX *= sr.getScaleFactor();
      mouseY *= sr.getScaleFactor();
      GL11.glPushMatrix();
      mouseX -= this.getX();
      mouseY -= this.getY();
      GL11.glTranslatef((float)this.getX(), (float)this.getY(), 0.0F);
      GL11.glTranslatef(210.0F, 90.0F, 0.0F);
      mouseX -= 210;
      mouseY -= 95;
      if (!this.hoveringModuleContextMenu) {
         this.settingsPanel.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      }

      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

public MainClickGuiWindow(String str, int n, int n2, int n3, int n4) {
       super(str, n, n2, n3, n4);
      this.categoryTabs = new ArrayList();
      this.moduleScrollBar = new SmoothScrollBar();
      this.categoryScrollBar = new SmoothScrollBar();
      this.settingsPanel = new ModuleSettingsPanel();
      this.moduleScrollOffset = 0;
      this.categoryScrollOffset = 0;
      this.timeHelper = new TimeHelper();
      this.hoveredModule = null;
      this.contextMenuModule = null;
      StringProperty stringProperty2 = (StringProperty)new StringProperty(this, "SelectedModule", "").withValueSupplier(() -> this.getSelectedModuleName());
      Consumer consumer = obj -> {
         this.setSelectedModule(ClientManagers.moduleManager.getModuleByName((String)obj));
      };
      this.selectedModuleProperty = (StringProperty)stringProperty2.withOnValueChanged(consumer);
      StringProperty stringProperty4 = (StringProperty)new StringProperty(this, "SelectedCategory", "").withValueSupplier(() -> this.getSelectedCategory().getCategorys().name());
      Consumer consumer2 = obj2 -> {

         for (CategoryTabButton categoryTabButton : (Iterable<CategoryTabButton>)(this.getCategoryTabs())) {
            if (categoryTabButton.getCategorys().name().equals(obj2)) {
               this.setSelectedCategory(categoryTabButton);
               break;
            }
         }
      };
      this.selectedCategoryProperty = (StringProperty)stringProperty4.withOnValueChanged(consumer2);
      this.hoveringModuleContextMenu = false;
      this.hoveringCategoryContextMenu = false;
      this.moduleFont = FontManager.consolas32;
      int n5 = 0;

      for (Categorys categorys : Categorys.values()) {
         if (!categorys.hidden) {
            CategoryTabButton categoryTabButton2 = new CategoryTabButton(220 + n5, 50, 11, 7, categorys, this.moduleFont);
            this.categoryTabs.add(categoryTabButton2);
            n5 += categoryTabButton2.getTextWidth() + 30;
         }
      }

      this.selectedCategory = (CategoryTabButton)this.categoryTabs.get(0);
      this.selectedModule = (Module)this.selectedCategory.getModules().get(0);
   }

   public CustomFontUtil getModuleFont() {
      return this.moduleFont;
   }

   public void setSelectedCategory(CategoryTabButton categoryTabButton) {
      this.selectedCategory = categoryTabButton;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      ScaledResolution sr = new ScaledResolution(mc);
      GL11.glPushMatrix();
      GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
      mouseX *= sr.getScaleFactor();
      mouseY *= sr.getScaleFactor();
      GL11.glPushMatrix();
      mouseX -= this.getX();
      mouseY -= this.getY();
      this.categoryScrollBar.mouseReleased(mouseX, mouseY, state);
      this.moduleScrollBar.mouseReleased(mouseX, mouseY, state);
      GL11.glTranslatef((float)this.getX(), (float)this.getY(), 0.0F);
      GL11.glTranslatef(210.0F, 90.0F, 0.0F);
      mouseX -= 210;
      mouseY -= 95;
      this.settingsPanel.mouseReleased(mouseX, mouseY, state);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      ClickGuiListener.postClickGuiRender();
   }

   private void drawModuleContextMenu(int n, int n2) {
      if (!Augustus.getInstance().isConfigEditingEnabled()) {
      } else {
         RenderUtil.drawRect(this.contextMenuX, this.contextMenuY, this.contextMenuX + 130, this.contextMenuY + 40, 40, 39, 3);
         RenderUtil.drawRectOutline((double)this.contextMenuX, (double)this.contextMenuY, (double)(this.contextMenuX + 130), (double)(this.contextMenuY + 40), 1.0F, Color.BLACK);
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         CustomFontUtil consolas30 = FontManager.consolas30;
         double d = (double)(this.contextMenuX + 2);
         double d2 = (double)(this.contextMenuY + 2);
         int hoveredColor = RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, this.contextMenuX, this.contextMenuY, 130, 19);
         double d3 = d2;
         double d4 = d;
         consolas30.drawString("Import Settings", d4, d3, hoveredColor);
         consolas30 = FontManager.consolas30;
         d = (double)(this.contextMenuX + 2);
         d2 = (double)(this.contextMenuY + 2 + 19);
         int hoveredColor2 = RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, this.contextMenuX, this.contextMenuY + 21, 130, 19);
         double d5 = d2;
         double d6 = d;
         consolas30.drawString("Export Settings", d6, d5, hoveredColor2);
      }
   }

   public ModuleSettingsPanel getSettingsPanel() {
      return this.settingsPanel;
   }

   public static void drawTooltip(CustomFontUtil customFontUtil, String str, int n, int n2) {
      TooltipRenderer.drawTooltip(customFontUtil, str, n, n2, Augustus.getInstance().getClickGui().getMainWindow().getWidth() - 240 - Augustus.getInstance().getClickGui().getMainWindow().cornerRadius - n);
   }

   public CategoryTabButton getSelectedCategory() {
      return this.selectedCategory;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (this.isVisible()) {
         super.mouseClicked(mouseX, mouseY, mouseButton);
         ScaledResolution sr = new ScaledResolution(mc);
         GL11.glPushMatrix();
         GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
         mouseX *= sr.getScaleFactor();
         mouseY *= sr.getScaleFactor();
         GL11.glPushMatrix();
         mouseX -= this.getX();
         mouseY -= this.getY();
         GL11.glTranslatef((float)this.getX(), (float)this.getY(), 0.0F);
         int cornerRadius2 = this.cornerRadius;
         int n = Math.max(this.cornerRadius, 30);
         int n2 = this.getWidth() - this.cornerRadius * 2;
         int n3 = this.getHeight() - this.cornerRadius - Math.max(this.cornerRadius, 30);
         if (!this.isMouseOver(cornerRadius2, n, n2, n3, mouseX, mouseY)) {
            GL11.glPopMatrix();
            GL11.glPopMatrix();
         } else {
            if ((mouseButton == 1 || mouseButton == 0) && !this.hoveringCategoryContextMenu) {
               this.contextMenuCategory = null;
            }

            n2 = this.getWidth() - this.cornerRadius - 210;
            if (this.isMouseOver((short)210, (byte)30, n2, (byte)60, mouseX, mouseY) && !this.hoveringModuleContextMenu) {
               for (CategoryTabButton categoryTabButton : (Iterable<CategoryTabButton>)(this.categoryTabs)) {
                  int height2 = categoryTabButton.height;
                  int width2 = categoryTabButton.width;
                  int top = categoryTabButton.top;
                  if (this.isMouseOver(categoryTabButton.left, top, width2, height2, mouseX, mouseY)) {
                     if (this.selectedCategory == categoryTabButton && mouseButton == 1) {
                        this.contextMenuCategory = categoryTabButton;
                        this.contextMenuX = mouseX;
                        this.contextMenuY = mouseY;
                        this.contextMenuModule = null;
                     } else if (!this.hoveringCategoryContextMenu) {
                        int moduleScrollOffset2 = this.moduleScrollOffset;
                        this.selectedCategory.setScrollOffset(moduleScrollOffset2);
                        this.selectedCategory = categoryTabButton;
                        this.moduleScrollOffset = categoryTabButton.getScrollOffset();
                        this.moduleScrollBar.resetPendingScroll();
                     }
                     break;
                  }
               }
            }

            if ((mouseButton == 1 || mouseButton == 0) && !this.hoveringModuleContextMenu) {
               this.contextMenuModule = null;
            }

            if ((mouseButton == 1 || mouseButton == 0) && !this.hoveringModuleContextMenu) {
               this.contextMenuModule = null;
            }

            for (Module module : (Iterable<Module>)(this.selectedCategory.getModules())) {
               if (!module.isHiddenInGui()) {
                  int n9 = module.getGuiX() - 2;
                  n = module.getGuiY();
                  n3 = module.getGuiHeight() + 2;
                  if (this.isMouseOver(n9, n, (short)160, n3, mouseX, mouseY)) {
                     if (mouseButton == 1) {
                        if (this.selectedModule == module && !(module instanceof ClientAntiCheat)) {
                           this.contextMenuX = mouseX;
                           this.contextMenuY = mouseY;
                           this.contextMenuModule = module;
                           this.contextMenuCategory = null;
                        }

                        this.selectedModule = module;
                        break;
                     }

                     if (mouseButton == 0 && !this.hoveringModuleContextMenu) {
                        module.toggle();
                        break;
                     }
                  }
               }
            }

            if (this.hoveringCategoryContextMenu && mouseButton == 0) {
               this.handleCategoryContextMenuClick(mouseX, mouseY);
            }

            if (!this.hoveringModuleContextMenu) {
               this.categoryScrollBar.mouseClicked(mouseX, mouseY, mouseButton);
               this.moduleScrollBar.mouseClicked(mouseX, mouseY, mouseButton);
            } else if (mouseButton == 0) {
               this.handleModuleContextMenuClick(mouseX, mouseY);
            }

            GL11.glPushMatrix();
            GL11.glTranslatef(210.0F, 95.0F, 0.0F);
            mouseX -= 210;
            mouseY -= 95;
            n = this.getWidth() - 210 - this.cornerRadius - (this.settingsPanel.verticalScrollVisible ? 15 : 0);
            if (this.isMouseOver((byte)0, (byte)0, n, this.getHeight() - 95 - this.cornerRadius - (this.settingsPanel.horizontalScrollVisible ? 15 : 0), mouseX, mouseY) && !this.hoveringModuleContextMenu) {
               boolean flag;
               if (this.contextMenuSetting != null) {
if (this.contextMenuSetting.mouseClicked(mouseX, mouseY, mouseButton)) {
flag = true;
} else {
flag = false;
}
} else {
flag = false;
}

               if (!flag) {
                  this.settingsPanel.mouseClicked(mouseX, mouseY, mouseButton);
               }
            }

            n2 = this.getWidth() - 210 - this.cornerRadius;
            n3 = this.getHeight() - 95 - this.cornerRadius;
            if (this.isMouseOver((byte)0, (byte)0, n2, n3, mouseX, mouseY) && !this.hoveringModuleContextMenu) {
               this.settingsPanel.handleScrollBarClick(mouseX, mouseY, mouseButton);
            }

            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            ClickGuiListener.postClickGuiRender();
         }
      }
   }

   public boolean isHoveringModuleContextMenu() {
      return this.hoveringModuleContextMenu;
   }

   private void drawCategoryContextMenu(int n, int n2) {
      if (!Augustus.getInstance().isConfigEditingEnabled()) {
      } else {
         RenderUtil.drawRect(this.contextMenuX, this.contextMenuY, this.contextMenuX + 130, this.contextMenuY + 78, 40, 39, 39);
         RenderUtil.drawRectOutline((double)this.contextMenuX, (double)this.contextMenuY, (double)(this.contextMenuX + 130), (double)(this.contextMenuY + 78), 1.0F, Color.BLACK);
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         CustomFontUtil consolas30 = FontManager.consolas30;
         double d = (double)(this.contextMenuX + 2);
         double d2 = (double)(this.contextMenuY + 2);
         int hoveredColor = RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, this.contextMenuX, this.contextMenuY, 130, 19);
         double d3 = d2;
         double d4 = d;
         consolas30.drawString("Import Settings", d4, d3, hoveredColor);
         consolas30 = FontManager.consolas30;
         d = (double)(this.contextMenuX + 2);
         d2 = (double)(this.contextMenuY + 21);
         int hoveredColor2 = RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, this.contextMenuX, this.contextMenuY + 21, 130, 19);
         double d5 = d2;
         double d6 = d;
         consolas30.drawString("Export Settings", d6, d5, hoveredColor2);
         consolas30 = FontManager.consolas30;
         d = (double)(this.contextMenuX + 2);
         d2 = (double)(this.contextMenuY + 40);
         int hoveredColor3 = RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, this.contextMenuX, this.contextMenuY + 40, 130, 19);
         double d7 = d2;
         double d8 = d;
         consolas30.drawString("LoadFromConfig", d8, d7, hoveredColor3);
         consolas30 = FontManager.consolas30;
         d = (double)(this.contextMenuX + 2);
         d2 = (double)(this.contextMenuY + 59);
         int hoveredColor4 = RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, this.contextMenuX, this.contextMenuY + 59, 130, 19);
         double d9 = d2;
         double d10 = d;
         consolas30.drawString("ShowInArrayList", d10, d9, hoveredColor4);
      }
   }

   public Module getSelectedModule() {
      return this.selectedModule;
   }

   private String getSelectedModuleName() {
      return this.getSelectedModule().getName();
   }

   public ArrayList getCategoryTabs() {
      return this.categoryTabs;
   }

   public void setSelectedModule(Module module) {
      if (module != null) {
         this.selectedModule = module;
      }
   }

}
