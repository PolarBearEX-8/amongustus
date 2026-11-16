package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.StatCollector;

public class GeometryScheduler extends GuiChat {
   @Override
   public void updateState2() {
      super.updateState2();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 100, this.height - 40, StatCollector.getText("multiplayer.stopSleeping", new Object[0])
            )
         );
   }

   private void updateState() {
      this.mc.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(this.mc.thePlayer, EntityPlayerAction.STOP_SLEEPING));
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         this.updateState();
      } else {
         super.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count == 1) {
         this.updateState();
      } else if (count != 28 && count != 156) {
         super.applyChar(character, count);
      } else {
         String text = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().trim();
         if (!text.isEmpty()) {
            this.mc.thePlayer.sendChatMessage(text);
         }

         this.internalTexturesGuiIconsPngManagerAdapter.applyText("");
         this.mc.ingameGUI.getInternalLoggerEntry().renderElement3();
      }
   }
}
