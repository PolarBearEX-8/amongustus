package augustus.anticheat.checks;

import client.util.ClickHelper;
import client.util.MinecraftAccessor;
import client.util.Packet;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class EagleCCheck extends AntiCheatCheck {
   public double value;
   public double value2;
   public final ClickHelper clickHelper = new ClickHelper(20);

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      long value3 = 1366079884L;
      char character = '\uaf6d';
      if (packet instanceof C0BPacketEntityAction && ((C0BPacketEntityAction)packet).getEntityPlayerAction() == EntityPlayerAction.START_SNEAKING) {
         double offset = Math.abs((double)Math.round(MinecraftAccessor.minecraft.thePlayer.posX) - MinecraftAccessor.minecraft.thePlayer.posX);
         double offset2 = Math.abs((double)Math.round(MinecraftAccessor.minecraft.thePlayer.posZ) - MinecraftAccessor.minecraft.thePlayer.posZ);
         double offset3 = Math.abs(MinecraftAccessor.minecraft.thePlayer.posX - this.value);
         double offset4 = Math.abs(MinecraftAccessor.minecraft.thePlayer.posZ - this.value2);
         if (offset3 > 0.9 && offset4 < 0.2) {
            this.clickHelper.processDouble(offset);
         } else if (offset4 > 0.9 && offset3 < 0.2) {
            this.clickHelper.processDouble(offset2);
         }

         if (this.clickHelper.canExecute()) {
            double valueAsDouble = this.clickHelper.getValueAsDouble();
            if (valueAsDouble < 0.015) {
               String text = "std: " + valueAsDouble;
               this.applyTextByText(text);
               this.clickHelper.clear();
            }
         }

         this.value = MinecraftAccessor.minecraft.thePlayer.posX;
         this.value2 = MinecraftAccessor.minecraft.thePlayer.posZ;
      }
   }

   public EagleCCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
