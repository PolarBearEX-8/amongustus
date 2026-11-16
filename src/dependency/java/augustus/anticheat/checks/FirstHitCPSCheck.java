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
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.CAnimateHandPacket;

public class FirstHitCPSCheck extends AntiCheatCheck {
   public double value;
   public boolean enabled3;
   public int count3;
   public int count2;
   public int count;
   public boolean enabled2;
   public boolean enabled;
   public EntityLivingBase entityLivingBase;
   public final ClickHelper clickHelper2;
   public final ClickHelper clickHelper = new ClickHelper(20);

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet2 = (C02PacketUseEntity)packet;
         if (packet2.getUseEntityAction() == UseEntityAction.ATTACK) {
            Entity entity = packet2.processAction(MinecraftAccessor.minecraft.theWorld);
            if (entity instanceof EntityLivingBase) {
               this.entityLivingBase = (EntityLivingBase)entity;
               this.enabled3 = true;
            }
         }
      }

      if (packet instanceof C0APacketAnimation || packet instanceof CAnimateHandPacket) {
         this.enabled2 = true;
      }
   }

   private void updateState5() {
      RayTraceUtil.applyString("Rias P105: FirstHitCps!");
   }

   public FirstHitCPSCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.clickHelper2 = new ClickHelper(40);
   }
}
