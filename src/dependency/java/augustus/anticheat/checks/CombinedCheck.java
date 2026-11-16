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

public class CombinedCheck extends AntiCheatCheck {
   public boolean enabled;
   public boolean enabled2;
   public EntityLivingBase entityLivingBase;
   public int count;
   public final ClickHelper clickHelper = new ClickHelper(20);
   public final ClickHelper clickHelper3;
   public final ClickHelper clickHelper2;
   public int count2;

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   private void applyDouble(double offset, double offset2) {
      RayTraceUtil.applyString("Rias P106: Combined: " + offset + ", " + offset2);
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet2 = (C02PacketUseEntity)packet;
         if (packet2.getUseEntityAction() == UseEntityAction.ATTACK) {
            Entity entity = packet2.processAction(MinecraftAccessor.minecraft.theWorld);
            if (entity instanceof EntityLivingBase) {
               this.entityLivingBase = (EntityLivingBase)entity;
               this.enabled2 = true;
            }
         }
      }
   }

   public CombinedCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.clickHelper2 = new ClickHelper(20);
      this.clickHelper3 = new ClickHelper(20);
   }
}
