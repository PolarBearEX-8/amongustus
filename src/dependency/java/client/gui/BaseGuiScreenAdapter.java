package client.gui;

import client.event.EventClickGuiListener;
import client.setting.ClientSettingsBlackType;
import client.util.InterpolationChecker;
import client.util.PngManager;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.util.StatCollector;

public class BaseGuiScreenAdapter extends GuiScreen {
   public NetHandlerPlayClient netHandlerPlayClient;
   public int count2;
   public InterpolationChecker interpolationChecker = PngManager.getInterpolationChecker();

   @Override
   public void applyIndex(int count, int count3, float factor) {
      if (this.interpolationChecker != null) {
         this.interpolationChecker.applyIndex(this.width, this.height);
      } else {
         this.applyIndex2(0);
      }

      applyFontRenderer4(
         this.fontRenderer, StatCollector.getText("multiplayer.downloadingTerrain", new Object[0]), this.width / 2, this.height / 2 - 50, 16777215
      );
      super.applyIndex(count, count3, factor);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
   }

   public BaseGuiScreenAdapter(NetHandlerPlayClient netHandlerPlayClient2) {
      this.netHandlerPlayClient = netHandlerPlayClient2;
   }

   @Override
   public void updateState2() {
      this.list.clear();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 100, 2, ClientSettingsBlackType.BOLD + ClientSettingsBlackType.GOLD.toString() + "Disconnect"
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 100, this.height - 20 - 2, ClientSettingsBlackType.BOLD + ClientSettingsBlackType.RED.toString() + "Force Load"
            )
         );
      EventClickGuiListener.updateState14();
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      super.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         this.mc.theWorld.updateState();
         this.mc.loadWorld((WorldClient)null);
         Minecraft mc = this.mc;
         GuiMultiplayer guiMultiplayer = new GuiMultiplayer(new GuiMainMenu());
         mc.displayGuiScreen(guiMultiplayer);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         this.mc.displayGuiScreen((GuiScreen)null);
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public void updateState4() {
      this.count2++;
      if (this.count2 % 20 == 0) {
         this.netHandlerPlayClient.handlePacket(new C00PacketKeepAlive());
      }
   }
}
