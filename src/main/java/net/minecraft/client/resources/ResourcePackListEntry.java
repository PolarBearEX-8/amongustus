package net.minecraft.client.resources;

import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiYesNoCallback;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public abstract class ResourcePackListEntry implements GuiListExtended.IGuiListEntry {
   private static final ResourceLocation RESOURCE_PACKS_TEXTURE = new ResourceLocation("textures/gui/resource_packs.png");
   private static final IChatComponent INCOMPATIBLE = new ChatComponentTranslation("resourcePack.incompatible", new Object[0]);
   private static final IChatComponent INCOMPATIBLE_OLD = new ChatComponentTranslation("resourcePack.incompatible.old", new Object[0]);
   private static final IChatComponent INCOMPATIBLE_NEW = new ChatComponentTranslation("resourcePack.incompatible.new", new Object[0]);
   public final Minecraft mc;
   public final GuiScreenResourcePacks resourcePacksGUI;

   public boolean canMoveRight() {
      return !this.resourcePacksGUI.hasResourcePackEntry(this);
   }

   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      int resourcePackFormat = this.getResourcePackFormat();
      if (resourcePackFormat != 1) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         Gui.drawRect(n2 - 1, n3 - 1, n2 + n4 - 9, n3 + n5 + 1, -8978432);
      }

      this.bindResourcePackIcon();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.drawModalRectWithCustomSizedTexture(n2, n3, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      String resourcePackName = this.getResourcePackName();
      String str = this.getResourcePackDescription();
      if ((this.mc.gameSettings.touchscreen || flag) && this.showHoverOverlay()) {
         this.mc.getTextureManager().bindTexture(RESOURCE_PACKS_TEXTURE);
         Gui.drawRect(n2, n3, n2 + 32, n3 + 32, -1601138544);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         int n8 = n6 - n2;
         int n9 = n7 - n3;
         if (resourcePackFormat < 1) {
            resourcePackName = INCOMPATIBLE.getFormattedText();
            str = INCOMPATIBLE_OLD.getFormattedText();
         } else if (resourcePackFormat > 1) {
            resourcePackName = INCOMPATIBLE.getFormattedText();
            str = INCOMPATIBLE_NEW.getFormattedText();
         }

         if (this.canMoveRight()) {
            if (n8 < 32) {
               Gui.drawModalRectWithCustomSizedTexture(n2, n3, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(n2, n3, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         } else {
            if (this.canMoveLeft()) {
               if (n8 < 16) {
                  Gui.drawModalRectWithCustomSizedTexture(n2, n3, 32.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.drawModalRectWithCustomSizedTexture(n2, n3, 32.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }

            if (this.canMoveUp()) {
               if (n8 < 32 && n8 > 16 && n9 < 16) {
                  Gui.drawModalRectWithCustomSizedTexture(n2, n3, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.drawModalRectWithCustomSizedTexture(n2, n3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }

            if (this.canMoveDown()) {
               if (n8 < 32 && n8 > 16 && n9 > 16) {
                  Gui.drawModalRectWithCustomSizedTexture(n2, n3, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.drawModalRectWithCustomSizedTexture(n2, n3, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }
         }
      }

      if (this.mc.fontRendererObj.getStringWidth(resourcePackName) > 157) {
         resourcePackName = this.mc.fontRendererObj.trimStringToWidth(resourcePackName, 157 - this.mc.fontRendererObj.getStringWidth("...")) + "...";
      }

      this.mc.fontRendererObj.renderString(resourcePackName, (float)(n2 + 32 + 2), (float)(n3 + 1), 16777215, true);
      List list = this.mc.fontRendererObj.listFormattedStringToWidth(str, 157);

      for (int i = 0; i < 2 && i < list.size(); i++) {
         this.mc.fontRendererObj.renderString((String)list.get(i), (float)(n2 + 32 + 2), (float)(n3 + 12 + 10 * i), 8421504, true);
      }
   }

   public boolean canMoveUp() {
      List listContaining = this.resourcePacksGUI.getListContaining(this);
      int n = listContaining.indexOf(this);
      return n > 0 && ((ResourcePackListEntry)listContaining.get(n - 1)).showHoverOverlay();
   }

   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (this.showHoverOverlay() && n5 <= 32) {
         if (this.canMoveRight()) {
            this.resourcePacksGUI.markChanged();
            int resourcePackFormat = this.getResourcePackFormat();
            if (resourcePackFormat != 1) {
               String str = I18n.format("resourcePack.incompatible.confirm.title", new Object[0]);
               String str2 = I18n.format("resourcePack.incompatible.confirm." + (resourcePackFormat > 1 ? "new" : "old"), new Object[0]);
               this.mc.displayGuiScreen(new GuiYesNo(new ResourcePackListEntry.DefaultGuiYesNoCallback(this), str, str2, 0));
            } else {
               this.resourcePacksGUI.getListContaining(this).remove(this);
               this.resourcePacksGUI.getSelectedResourcePacks().add(0, this);
            }

            return true;
         }

         if (n5 < 16 && this.canMoveLeft()) {
            this.resourcePacksGUI.getListContaining(this).remove(this);
            this.resourcePacksGUI.getAvailableResourcePacks().add(0, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }

         if (n5 > 16 && n6 < 16 && this.canMoveUp()) {
            List listContaining = this.resourcePacksGUI.getListContaining(this);
            int n7 = listContaining.indexOf(this);
            listContaining.remove(this);
            listContaining.add(n7 - 1, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }

         if (n5 > 16 && n6 > 16 && this.canMoveDown()) {
            List listContaining2 = this.resourcePacksGUI.getListContaining(this);
            int n8 = listContaining2.indexOf(this);
            listContaining2.remove(this);
            listContaining2.add(n8 + 1, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }
      }

      return false;
   }

   public abstract String getResourcePackName();

   public boolean canMoveDown() {
      List listContaining = this.resourcePacksGUI.getListContaining(this);
      int n = listContaining.indexOf(this);
      return n >= 0 && n < listContaining.size() - 1 && ((ResourcePackListEntry)listContaining.get(n + 1)).showHoverOverlay();
   }

   public abstract int getResourcePackFormat();

   public ResourcePackListEntry(GuiScreenResourcePacks guiScreenResourcePacks) {
      this.resourcePacksGUI = guiScreenResourcePacks;
      this.mc = Minecraft.getMinecraft();
   }

   public boolean showHoverOverlay() {
      return true;
   }

   public void setSelected(int n, int n2, int n3) {
   }

   public abstract void bindResourcePackIcon();

   public boolean canMoveLeft() {
      return this.resourcePacksGUI.hasResourcePackEntry(this);
   }

   public abstract String getResourcePackDescription();



   public static class DefaultGuiYesNoCallback implements GuiYesNoCallback {
      public final ResourcePackListEntry entry;

      public DefaultGuiYesNoCallback(ResourcePackListEntry resourcePackListEntry) {
         this.entry = resourcePackListEntry;
      }

      @Override
      public void confirmClicked(boolean flag, int n) {
         List listContaining = this.entry.resourcePacksGUI.getListContaining(this.entry);
         GuiScreenResourcePacks resourcePacksGUI = this.entry.resourcePacksGUI;
         this.entry.mc.displayGuiScreen(resourcePacksGUI);
         if (flag) {
            listContaining.remove(this.entry);
            this.entry.resourcePacksGUI.getSelectedResourcePacks().add(0, this.entry);
         }
      }

   }
}
