package net.minecraft.client.gui;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;

public class GuiYesNo extends GuiScreen {
   public GuiYesNoCallback parentScreen;
   public String messageLine1;
   private String messageLine2;
   private final List listLines = Lists.newArrayList();
   public String confirmButtonText;
   public String cancelButtonText;
   public int parentButtonClickedId;
   private int ticksUntilEnable;
   public NetworkManager resourcePackNetManager;
   public String resourcePackUrl;
   public String resourcePackHash;

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.messageLine1, this.width / 2, 70, 16777215);
      int n = 90;

      for (String str : (Iterable<String>)(this.listLines)) {
         drawCenteredString(this.fontRendererObj, str, this.width / 2, n, 16777215);
         n += this.fontRendererObj.FONT_HEIGHT;
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (--this.ticksUntilEnable == 0) {
         for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
            button.enabled = true;
         }
      }
   }

   public void setButtonDelay(int n) {
      this.ticksUntilEnable = n;

      for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
         button.enabled = false;
      }
   }

   public GuiYesNo(GuiYesNoCallback guiYesNoCallback, String str, String str2, int n) {
      this.parentScreen = guiYesNoCallback;
      this.messageLine1 = str;
      this.messageLine2 = str2;
      this.parentButtonClickedId = n;
      this.confirmButtonText = I18n.format("gui.yes", new Object[0]);
      this.cancelButtonText = I18n.format("gui.no", new Object[0]);
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }

   public GuiYesNo(GuiYesNoCallback guiYesNoCallback, String str, String str2, String str3, String str4, int n) {
      this.parentScreen = guiYesNoCallback;
      this.messageLine1 = str;
      this.messageLine2 = str2;
      this.confirmButtonText = str3;
      this.cancelButtonText = str4;
      this.parentButtonClickedId = n;
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id >= 2) {
         this.resourcePackNetManager.sendPacket(new C19PacketResourcePackStatus(this.resourcePackHash, C19PacketResourcePackStatus.Action.ACCEPTED));
         if (button.id == 3) {
            Futures.addCallback(Minecraft.getMinecraft().getResourcePackRepository().pingResourcePackUrl(this.resourcePackUrl), new GuiYesNo.DefaultFutureCallback(this), MoreExecutors.directExecutor());
         }

         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
      } else {
         this.parentScreen.confirmClicked(button.id == 0, this.parentButtonClickedId);
      }
   }

   @Override
   public void initGui() {
      this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 155, this.height / 6 + 96, this.confirmButtonText));
      this.buttonList.add(new GuiOptionButton(1, this.width / 2 - 155 + 160, this.height / 6 + 96, this.cancelButtonText));
      this.listLines.clear();
      this.listLines.addAll(this.fontRendererObj.listFormattedStringToWidth(this.messageLine2, this.width - 50));
      if (this.messageLine1.equals(I18n.format("multiplayer.texturePrompt.line1", new Object[0]))) {
         this.buttonList.add(new GuiButton(2, 5, 5, 98, 20, "Fake accept"));
         this.buttonList.add(new GuiButton(3, 5, 28, 98, 20, "Fake download"));
      }
   }



   public static class DefaultFutureCallback implements FutureCallback {
      public final GuiYesNo yesNoScreen;

      public DefaultFutureCallback(GuiYesNo guiYesNo) {
         this.yesNoScreen = guiYesNo;
      }

      public void onSuccess(Object obj) {
         this.yesNoScreen.resourcePackNetManager.sendPacket(new C19PacketResourcePackStatus(this.yesNoScreen.resourcePackHash, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
      }

      public void onFailure(Throwable t) {
         this.yesNoScreen.resourcePackNetManager.sendPacket(new C19PacketResourcePackStatus(this.yesNoScreen.resourcePackHash, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
      }
   }
}
