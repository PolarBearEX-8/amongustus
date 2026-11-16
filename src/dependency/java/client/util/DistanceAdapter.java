package client.util;

import client.event.PreUpdateDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.util.StatCollector;

public class DistanceAdapter implements PreUpdateDispatcher {
   public final GuiMultiplayer guiMultiplayer;
   public final Minecraft mc;
   public final InterpolationDispatcher interpolationDispatcher;
   public long timestamp = 0L;

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      this.guiMultiplayer.applyIndex(count);
      if (Minecraft.getTimestamp() - this.timestamp < 250L) {
         this.guiMultiplayer.renderElement();
      }

      this.timestamp = Minecraft.getTimestamp();
      return false;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   public DistanceAdapter(GuiMultiplayer guiMultiplayer2, InterpolationDispatcher interpolationDispatcher2) {
      this.guiMultiplayer = guiMultiplayer2;
      this.interpolationDispatcher = interpolationDispatcher2;
      this.mc = Minecraft.getMinecraft();
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      this.mc.fontRendererObj.getIndex(StatCollector.getText("lanServer.title", new Object[0]), count2 + 32 + 3, count3 + 1, 16777215);
      this.mc.fontRendererObj.getIndex(this.interpolationDispatcher.getLabelText(), count2 + 32 + 3, count3 + 12, 8421504);
      if (this.mc.gameSettings.state129) {
         this.mc.fontRendererObj.getIndex(StatCollector.getText("selectServer.hiddenAddress", new Object[0]), count2 + 32 + 3, count3 + 12 + 11, 3158064);
      } else {
         this.mc.fontRendererObj.getIndex(this.interpolationDispatcher.getLabelText2(), count2 + 32 + 3, count3 + 12 + 11, 3158064);
      }
   }

   public InterpolationDispatcher getInterpolationDispatcher() {
      return this.interpolationDispatcher;
   }
}
