package net.minecraft.network.play.server;

import client.setting.MovementStateProperty;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.RayTraceBridge;
import java.io.IOException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.CombatTracker;

public class S42PacketCombatEvent implements Packet {
   public MovementStateProperty movementStateProperty;
   public int count2;
   public int count;
   public int count3;
   public String labelText;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS42PacketCombatEvent(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.movementStateProperty = (MovementStateProperty)packet.getEnum(MovementStateProperty.class);
      if (this.movementStateProperty == MovementStateProperty.END_COMBAT) {
         this.count3 = packet.getInt3();
         this.count = packet.readInt();
      } else if (this.movementStateProperty == MovementStateProperty.ENTITY_DIED) {
         this.count2 = packet.getInt3();
         this.count = packet.readInt();
         this.labelText = packet.resolveTextByIndex(32767);
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyEnum(this.movementStateProperty);
      if (this.movementStateProperty == MovementStateProperty.END_COMBAT) {
         packet.updateInt(this.count3);
         packet.writeInt(this.count);
      } else if (this.movementStateProperty == MovementStateProperty.ENTITY_DIED) {
         packet.updateInt(this.count2);
         packet.writeInt(this.count);
         packet.getPacketBuffer(this.labelText);
      }
   }

   public S42PacketCombatEvent() {
   }

   public S42PacketCombatEvent(CombatTracker combatTracker, MovementStateProperty movementStateProperty2) {
      this.movementStateProperty = movementStateProperty2;
      EntityLivingBase entityLivingBase = combatTracker.getEntityLivingBase();
      switch (RayTraceBridge.countArray[movementStateProperty2.ordinal()]) {
         case 1:
            this.count3 = combatTracker.getCount();
            this.count = entityLivingBase == null ? -1 : entityLivingBase.getEntityId();
            break;
         case 2:
            this.count2 = combatTracker.getEntityLivingBase2().getEntityId();
            this.count = entityLivingBase == null ? -1 : entityLivingBase.getEntityId();
            this.labelText = combatTracker.getIChatComponent().getString();
      }
   }
}
