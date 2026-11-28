package wdl.gui;

import net.minecraft.client.gui.GuiListExtended;
import wdl.api.IWDLModWithGui;
import wdl.api.ModInfo;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiWDLExtensionsModEntry implements GuiListExtended.IGuiListEntry {
   public final ModInfo mod;
   private final String modDescription;
   private String label;
   private GuiButton button;
   private GuiButton disableButton;
   public final ExtensionModList parentList;

   public GuiWDLExtensionsModEntry(ExtensionModList extensionModList, ModInfo modInfo) {
       super();
       this.parentList = extensionModList;
      this.mod = modInfo;
      String displayName = modInfo.getDisplayName();
      this.modDescription = I18n.format("wdl.gui.extensions.modVersion", new Object[]{displayName, modInfo.version});
      if (!modInfo.isEnabled()) {
         this.label = "" + EnumChatFormatting.GRAY + EnumChatFormatting.ITALIC + this.modDescription;
      } else {
         this.label = this.modDescription;
      }

      if (modInfo.mod instanceof IWDLModWithGui) {
         IWDLModWithGui mod2 = (IWDLModWithGui)modInfo.mod;
         String buttonName = mod2.getButtonName();
         if (buttonName == null || buttonName.isEmpty()) {
            buttonName = I18n.format("wdl.gui.extensions.defaultSettingsButtonText", new Object[0]);
         }

         this.button = new GuiButton(0, 0, 0, 80, 20, mod2.getButtonName());
      }

      this.disableButton = new GuiButton(0, 0, 0, 80, 20, I18n.format("wdl.gui.extensions." + (modInfo.isEnabled() ? "enabled" : "disabled"), new Object[0]));
   }

   @Override
   public void setSelected(int n, int n2, int n3) {
   }

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      if (this.button != null) {
         this.button.xPosition = this.parentList.parentGui.width - 180;
         this.button.yPosition = n3 - 1;
         this.button.drawButton(ExtensionModList.getMinecraftForSettingsButtonDraw(this.parentList), n6, n7);
      }

      this.disableButton.xPosition = this.parentList.parentGui.width - 92;
      this.disableButton.yPosition = n3 - 1;
      this.disableButton.drawButton(ExtensionModList.getMinecraftForDisableButtonDraw(this.parentList), n6, n7);
      int n8 = n3 + n5 / 2 - GuiWDLExtensions.getFontRendererForEntryHeight(this.parentList.parentGui).FONT_HEIGHT / 2;
      GuiWDLExtensions.getFontRendererForEntryDraw(this.parentList.parentGui).drawString(this.label, n2, n8, 16777215);
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (this.button != null && this.button.mousePressed(ExtensionModList.getMinecraftForSettingsButtonPress(this.parentList), n2, n3)) {
         if (this.mod.mod instanceof IWDLModWithGui) {
            ((IWDLModWithGui)this.mod.mod).openGui(this.parentList.parentGui);
         }

         this.button.playPressSound(ExtensionModList.getMinecraftForSettingsButtonSound(this.parentList).getSoundHandler());
         return true;
      } else if (this.disableButton.mousePressed(ExtensionModList.getMinecraftForDisableButtonPress(this.parentList), n2, n3)) {
         this.mod.toggleEnabled();
         this.disableButton.playPressSound(ExtensionModList.getMinecraftForDisableButtonSound(this.parentList).getSoundHandler());
         this.disableButton.displayString = I18n.format("wdl.gui.extensions." + (this.mod.isEnabled() ? "enabled" : "disabled"), new Object[0]);
         if (!this.mod.isEnabled()) {
            this.label = "" + EnumChatFormatting.GRAY + EnumChatFormatting.ITALIC + this.modDescription;
         } else {
            this.label = this.modDescription;
         }

         return true;
      } else if (GuiWDLExtensions.getSelectedModIndex(this.parentList.parentGui) != n) {
         GuiWDLExtensions.setSelectedModIndex(this.parentList.parentGui, n);
         ExtensionModList.getMinecraftForSelectSound(this.parentList).getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         GuiWDLExtensions.updateDetailsListFor(this.parentList.parentGui, this.mod);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
      if (this.button != null) {
         this.button.mouseReleased(n2, n3);
      }
   }
}
