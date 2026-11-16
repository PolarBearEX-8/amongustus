package augustus.anticheat.checks;

import client.Client;
import client.util.ClickHelper;
import client.util.MinecraftAccessor;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.UseEntityAction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class PerfectHitAlphaCheck extends AntiCheatCheck {
   public final ClickHelper clickHelper;
   public int count;
   public EntityLivingBase entityLivingBase = null;

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   private void updateState5() {
      RayTraceUtil.applyString("Rias P100: PerfectHit1!");
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet2 = (C02PacketUseEntity)packet;
         if (packet2.getUseEntityAction() == UseEntityAction.ATTACK) {
            Entity entity = packet2.processAction(MinecraftAccessor.minecraft.theWorld);
            if (entity instanceof EntityLivingBase) {
               this.entityLivingBase = (EntityLivingBase)entity;
               this.count++;
            }
         }
      }
   }

   public PerfectHitAlphaCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.clickHelper = new ClickHelper(25);
   }
}
