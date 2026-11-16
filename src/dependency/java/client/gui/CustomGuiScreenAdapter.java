package client.gui;

import client.util.AccountProfile;
import client.util.SharedStringEntry;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiConnecting;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;

public class CustomGuiScreenAdapter extends GuiScreen {
   public String labelText;
   public IChatComponent iChatComponent;
   public List list3;
   public int count2;

   @Override
   public void applyIndex(int count, int count3, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, this.height / 2 - this.count2 / 2 - this.fontRenderer.indexOffset * 2, 11184810);
      int count4 = this.height / 2 - this.count2 / 2;
      if (this.list3 != null) {
         for (String text : (Iterable<String>)(Iterable<?>)this.list3) {
            applyFontRenderer4(this.fontRenderer, text, this.width / 2, count4, 16777215);
            count4 += this.fontRenderer.indexOffset;
         }
      }

      super.applyIndex(count, count3, factor);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
   }

   @Override
   public void updateState2() {
      this.list.clear();
      this.list3 = this.fontRenderer.getList(this.iChatComponent.getString2(), this.width - 50);
      this.count2 = Math.min(20, this.list3.size()) * this.fontRenderer.indexOffset;
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 100, this.height / 2 + this.count2 / 2 + this.fontRenderer.indexOffset, StatCollector.getText("gui.toMenu", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 100, this.height / 2 + this.count2 / 2 + this.fontRenderer.indexOffset + 25, "Reconnect"
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               4, this.width / 2 - 100, this.height / 2 + this.count2 / 2 + this.fontRenderer.indexOffset + 50, "Augustus Menu"
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               2, this.width / 2 - 100, this.height / 2 + this.count2 / 2 + this.fontRenderer.indexOffset + 75, "Copy Error"
            )
         );
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(3, 5, 5, 98, 20, "Flush DNS Cache"));
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         GuiScreen guiScreen = this.guiScreen;
         this.mc.displayGuiScreen(guiScreen);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         String text = GuiConnecting.labelText + ":" + GuiConnecting.count2;
         Minecraft mc = this.mc;
         GuiConnecting guiConnecting = new GuiConnecting(
            new GuiMultiplayer(AugustusMainMenu.resolveGuiScreen((short)0, (char)3322, -80609536)), this.mc, new ServerData(text, text, false)
         );
         mc.displayGuiScreen(guiConnecting);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.iChatComponent.getString2()), null);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
         AddressCacheCacheHelper.renderElement();
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4) {
         Minecraft mc2 = this.mc;
         SharedStringEntry sharedStringEntry = new SharedStringEntry(this, 0);
         mc2.displayGuiScreen(sharedStringEntry);
      }
   }

   public CustomGuiScreenAdapter(GuiScreen screen, String text, IChatComponent iChatComponent2) {
      super(screen);
      this.labelText = StatCollector.getText(text, new Object[0]);
      this.iChatComponent = iChatComponent2;
      Mouse.setGrabbed(false);
      if (iChatComponent2 != null
         && Minecraft.getMinecraft() != null
         && Minecraft.getMinecraft().getSession() != null
         && (Minecraft.getMinecraft().getSession().getKeybindAnimationEntry() != null || GuiConnecting.labelText2.isEmpty())) {
         String string = iChatComponent2.getString();
         if (DayDaysHelper.matchesText(string)) {
            long value = DayDaysHelper.computeTimestamp(string);
            AccountProfile keybindAnimationEntry = Minecraft.getMinecraft().getSession().getKeybindAnimationEntry();
            String labelText2 = GuiConnecting.labelText2;
            Long value2 = value;
            String text2 = labelText2;
            keybindAnimationEntry.applyText(text2, value2);
         }
      }
   }
}
