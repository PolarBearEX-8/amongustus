package client.util;

import client.event.PreUpdateDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

public class LanServerScanningManager implements PreUpdateDispatcher {
   public final Minecraft mc = Minecraft.getMinecraft();

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      int count8 = count3 + count5 / 2 - this.mc.fontRendererObj.indexOffset / 2;
      this.mc
         .fontRendererObj
         .getIndex(
            StatCollector.getText("lanServer.scanning", new Object[0]),
            this.mc.currentScreen.width / 2 - this.mc.fontRendererObj.getIndex(StatCollector.getText("lanServer.scanning", new Object[0])) / 2,
            count8,
            16777215
         );
      String text;
      switch ((int)(Minecraft.getTimestamp() / 300L % 4L)) {
         case 0:
         default:
            text = "O o o";
            break;
         case 1:
         case 3:
            text = "o O o";
            break;
         case 2:
            text = "o o O";
      }

      this.mc
         .fontRendererObj
         .getIndex(text, this.mc.currentScreen.width / 2 - this.mc.fontRendererObj.getIndex(text) / 2, count8 + this.mc.fontRendererObj.indexOffset, 8421504);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      return false;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }
}
