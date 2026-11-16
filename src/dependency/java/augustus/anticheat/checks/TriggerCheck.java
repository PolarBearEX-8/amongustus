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

public class TriggerCheck extends AntiCheatCheck {
   public int count;
   public boolean enabled2;
   public int count2;
   public int count3;
   public boolean enabled;
   public EntityLivingBase entityLivingBase;
   public final ClickHelper clickHelper = new ClickHelper(10);

   private void updateState5() {
      RayTraceUtil.applyString("Rias P101: Trigger!");
   }

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   public static String[] getTextArray() {
      return null;
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C0APacketAnimation || packet instanceof CAnimateHandPacket) {
         this.count2++;
      }

      if (packet instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet2 = (C02PacketUseEntity)packet;
         if (packet2.getUseEntityAction() == UseEntityAction.ATTACK) {
            Entity entity = packet2.processAction(MinecraftAccessor.minecraft.theWorld);
            if (entity instanceof EntityLivingBase) {
               this.entityLivingBase = (EntityLivingBase)entity;
               this.count3++;
            }
         }
      }
   }

   public TriggerCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.entityLivingBase = null;
   }
}
