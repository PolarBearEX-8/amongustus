package augustus.modules.misc;

import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventSendPacket;
import client.event.NetworkPacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class TestLatencyModule extends Module implements NetworkPacketListener, TickListener, ClickListener {
   public EntityLivingBase entityLivingBase;
   public boolean enabled3 = false;
   public int count6 = 0;
   public boolean enabled4 = false;
   public boolean enabled5 = false;
   public final TimeHelper timer = new TimeHelper();

   public TestLatencyModule() {
      super("TestLatencyModule", "TestModul", Color.red, Category.LATENCY);
   }

   @Override
   public void onPreDisable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEnable() {
      this.registerEvent(25);
      this.registerEvent(40);
      this.registerEvent(7);
   }

   @Override
   public void onPostNetworkPacket(EventSendPacket packet) {
      Packet packet2 = packet.getPacket();
      if (this.enabled3) {
         packet.setCancelled(true);
      }

      if (packet2 instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet3 = (C02PacketUseEntity)packet2;
         if (packet3.getUseEntityAction() == UseEntityAction.ATTACK) {
            Entity entity = packet3.processAction(minecraft.theWorld);
            if (entity instanceof EntityLivingBase) {
               this.entityLivingBase = (EntityLivingBase)entity;
               this.timer.reset();
               if (this.entityLivingBase.hurtTime <= 1 && this.count6 <= 0 && !packet.isState()) {
                  this.count6 = 10;
                  this.enabled4 = true;
               }
            }
         }
      }
   }

   @Override
   public void onPreTick() {
      if (this.timer.hasReached(2000L)) {
         this.entityLivingBase = null;
      }

      if (this.entityLivingBase != null) {
         this.count6--;
      }
   }

   @Override
   public void onClientTick() {
      if (this.entityLivingBase != null) {
         if (this.count6 <= 0 && (this.enabled3 || this.enabled4)) {
            this.enabled4 = false;
            this.enabled3 = false;
            this.enabled5 = false;
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (this.entityLivingBase != null && this.count6 > 0) {
         if (minecraft.objectMouseOver == null
            || minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY
            || !minecraft.objectMouseOver.entityHit.equals(this.entityLivingBase)) {
            this.enabled3 = true;
         } else if (this.enabled4 && !this.enabled3) {
            event.updateInt(1);
            event.setState4(true);
            event.setState(true);
            this.enabled3 = true;
         }
      }
   }
}
