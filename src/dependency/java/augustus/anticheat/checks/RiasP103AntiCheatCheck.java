package augustus.anticheat.checks;

import client.Client;
import client.util.ClickHelper;
import client.util.MinecraftAccessor;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.UseEntityAction;
import java.util.LinkedList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class RiasP103AntiCheatCheck extends AntiCheatCheck {
   public double value;
   public boolean enabled;
   public final ClickHelper[] clickHelperArray = new ClickHelper[10];
   public EntityLivingBase entityLivingBase;
   public final LinkedList linkedList = new LinkedList();

   private void applyText2(String text) {
      RayTraceUtil.applyString("Rias P103: HurtTimeHit: " + text);
   }

   private double getValue() {
      return 0.0;
   }

   public RiasP103AntiCheatCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);

      for (int index = 0; index < this.clickHelperArray.length; index++) {
         this.clickHelperArray[index] = new ClickHelper(20);
      }
   }

   private static double doubleValue(Double offset) {
      return offset;
   }

   private void updateState6() {
      Client.getClient().getClass();
   }

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   private void updateState5() {
      ClickHelper[] clickHelperArray2 = this.clickHelperArray;
      int length = clickHelperArray2.length;

      for (int index = 0; index < length; index++) {
         clickHelperArray2[index].clear();
      }
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet2 = (C02PacketUseEntity)packet;
         if (packet2.getUseEntityAction() == UseEntityAction.ATTACK) {
            Entity entity = packet2.processAction(MinecraftAccessor.minecraft.theWorld);
            if (entity instanceof EntityLivingBase) {
               this.entityLivingBase = (EntityLivingBase)entity;
               this.enabled = true;
            }
         }
      }
   }

   private boolean isActiveAsFlag() {
      ClickHelper[] clickHelperArray2 = this.clickHelperArray;
      int length = clickHelperArray2.length;

      for (int index = 0; index < length; index++) {
         if (!clickHelperArray2[index].canExecute()) {
            return false;
         }
      }

      return true;
   }
}
