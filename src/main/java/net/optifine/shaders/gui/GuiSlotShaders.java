package net.optifine.shaders.gui;

import net.minecraft.client.gui.GuiYesNoCallback;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.resources.I18n;
import net.optifine.Lang;
import net.optifine.config.Config;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.Shaders;
import net.optifine.util.ResUtils;

public class GuiSlotShaders extends GuiSlot {
   private ArrayList shaderslist;
   private int selectedIndex;
   private long lastClickedCached = 0L;
   public final GuiShaders shadersGui;

   private boolean checkCompatible(IShaderPack shaderPack, int n) {
      if (shaderPack == null) {
         return true;
      } else {
         Properties properties = ResUtils.readProperties2(shaderPack.getResourceAsStream("/shaders/shaders.properties"), "Shaders");
         if (properties == null) {
            return true;
         } else {
            String property = properties.getProperty("version.1.8.9");
            if (property == null) {
               return true;
            } else {
               property = property.trim();
               if (Config.compareRelease("M5", property) >= 0) {
                  return true;
               } else {
                  String str3 = ("HD_U_" + property).replace('_', ' ');
                  String str4 = I18n.format("of.message.shaders.nv1", new Object[]{str3});
                  String str5 = I18n.format("of.message.shaders.nv2", new Object[0]);
                  GuiSlotShaders.DefaultGuiYesNoCallback obj = new GuiSlotShaders.DefaultGuiYesNoCallback(this, n);
                  GuiYesNo guiYesNo = new GuiYesNo(obj, str4, str5, 0);
                  this.mc.displayGuiScreen(guiYesNo);
                  return false;
               }
            }
         }
      }
   }

   public static Minecraft getMinecraft(GuiSlotShaders guiSlotShaders) {
      return guiSlotShaders.mc;
   }

   public GuiSlotShaders(GuiShaders guiShaders, int n, int n2, int n3, int n4, int n5) {
      super(guiShaders.getMc(), n, n2, n3, n4, n5);
      this.shadersGui = guiShaders;
      this.updateList();
      this.amountScrolled = 0.0F;
      int n6 = this.selectedIndex * n5;
      int n7 = (n4 - n3) / 2;
      if (n6 > n7) {
         this.scrollBy(n6 - n7);
      }
   }

   private void selectIndex(int n) {
      this.selectedIndex = n;
      this.lastClickedCached = this.lastClicked;
      Shaders.setShaderPack((String)this.shaderslist.get(n));
      Shaders.uninit();
      this.shadersGui.updateButtons();
   }

   @Override
   public void drawBackground() {
   }

   @Override
   public int getScrollBarX() {
      return this.width - 6;
   }

   @Override
   public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
      String str = (String)this.shaderslist.get(n);
      if (str.equals("OFF")) {
         str = Lang.get("of.options.shaders.packNone");
      } else if (str.equals("(internal)")) {
         str = Lang.get("of.options.shaders.packDefault");
      }

      this.shadersGui.drawCenteredString(str, this.width / 2, n3 + 1, 14737632);
   }

   @Override
   public void elementClicked(int n, boolean flag, int n2, int n3) {
      if (n != this.selectedIndex || this.lastClicked != this.lastClickedCached) {
         if (this.checkCompatible(Shaders.getShaderPack((String)this.shaderslist.get(n)), n)) {
            this.selectIndex(n);
         }
      }
   }

   @Override
   public int getContentHeight() {
      return this.getSize() * 18;
   }

   public int getSelectedIndex() {
      return this.selectedIndex;
   }

   public void updateList() {
      this.shaderslist = Shaders.listOfShaders();
      this.selectedIndex = 0;
      int n = 0;

      for (int i = this.shaderslist.size(); n < i; n++) {
         if (((String)this.shaderslist.get(n)).equals(Shaders.currentShaderName)) {
            this.selectedIndex = n;
            break;
         }
      }
   }

   @Override
   public boolean isSelected(int n) {
      return n == this.selectedIndex;
   }

   public static void invokeSelectIndex(GuiSlotShaders guiSlotShaders, int n) {
      guiSlotShaders.selectIndex(n);
   }

   @Override
   public int getSize() {
      return this.shaderslist.size();
   }

   @Override
   public int getListWidth() {
      return this.width - 20;
   }



   public static class DefaultGuiYesNoCallback implements GuiYesNoCallback {
      public final int index;
      public final GuiSlotShaders guiSlotShaders;

      public DefaultGuiYesNoCallback(GuiSlotShaders guiSlotShaders2, int n) {
         this.guiSlotShaders = guiSlotShaders2;
         this.index = n;
      }

      @Override
      public void confirmClicked(boolean flag, int n) {
         if (flag) {
            GuiSlotShaders.invokeSelectIndex(this.guiSlotShaders, this.index);
         }

         GuiShaders shadersGui = this.guiSlotShaders.shadersGui;
         GuiSlotShaders.getMinecraft(this.guiSlotShaders).displayGuiScreen(shadersGui);
      }

   }
}
