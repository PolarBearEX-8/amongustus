package augustus.anticheat.checks;

import client.setting.BridgeOptionType;
import client.setting.DirectOptionType;
import client.setting.RenderNumberProperty;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;

public class Intave14P41Check extends AntiCheatCheck {
   public CompositeDoubleEntry compositeDoubleEntry;
   public int count2;
   public int count;
   public boolean enabled = true;

   @Override
   public void updateState2() {
   }

   public void updateState5() {
      this.count2 = 0;
      this.count = 0;
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C0BPacketEntityAction) {
         EntityPlayerAction entityPlayerAction = ((C0BPacketEntityAction)packet).getEntityPlayerAction();
         boolean flag = entityPlayerAction == EntityPlayerAction.START_SPRINTING || entityPlayerAction == EntityPlayerAction.STOP_SPRINTING;
         boolean flag2 = entityPlayerAction == EntityPlayerAction.START_SNEAKING || entityPlayerAction == EntityPlayerAction.STOP_SNEAKING;
         if (flag || flag2) {
            if (minecraft.thePlayer.isActive23()) {
               this.updateState5();
            } else if (flag ? this.count2++ >= 1 : this.count++ >= 1) {
               if (this.enabled) {
                  BridgeOptionType bridgeOptionType = BridgeOptionType.PROBABLE;
                  double offset = 1.0;
                  this.compositeDoubleEntry.applyDouble(offset);
                  this.compositeDoubleEntry.applyBridgeOptionType(bridgeOptionType);
               }

               if (flag) {
                  RenderNumberProperty renderNumberProperty = RenderNumberProperty.CANCEL;
                  this.compositeDoubleEntry.applyRenderNumberProperty(renderNumberProperty);
               }
            }
         }
      }

      if (packet instanceof C03PacketPlayer || packet instanceof C0CPacketInput) {
         this.updateState5();
      }
   }

   public Intave14P41Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.compositeDoubleEntry = new CompositeDoubleEntry(41, "sent too many sprint/sneak toggles", DirectOptionType.KILLAURA);
   }
}
