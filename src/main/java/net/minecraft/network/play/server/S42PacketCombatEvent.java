package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.CombatTracker;

public class S42PacketCombatEvent implements Packet {
   public S42PacketCombatEvent.Event eventType;
   public int playerId;
   public int entityId;
   public int duration;
   public String deathMessage;

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleCombatEvent(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.eventType);
      if (this.eventType == S42PacketCombatEvent.Event.END_COMBAT) {
         buf.writeVarIntToBuffer(this.duration);
         buf.writeInt(this.entityId);
      } else if (this.eventType == S42PacketCombatEvent.Event.ENTITY_DIED) {
         buf.writeVarIntToBuffer(this.playerId);
         buf.writeInt(this.entityId);
         buf.writeString(this.deathMessage);
      }
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.eventType = (S42PacketCombatEvent.Event)buf.readEnumValue(S42PacketCombatEvent.Event.class);
      if (this.eventType == S42PacketCombatEvent.Event.END_COMBAT) {
         this.duration = buf.readVarIntFromBuffer();
         this.entityId = buf.readInt();
      } else if (this.eventType == S42PacketCombatEvent.Event.ENTITY_DIED) {
         this.playerId = buf.readVarIntFromBuffer();
         this.entityId = buf.readInt();
         this.deathMessage = buf.readStringFromBuffer(32767);
      }
   }

   public S42PacketCombatEvent() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S42PacketCombatEvent(CombatTracker combatTracker, S42PacketCombatEvent.Event event) {
      this.eventType = event;
      EntityLivingBase bestAttacker = combatTracker.getBestAttacker();
      switch (event) {
         case END_COMBAT:
            this.duration = combatTracker.getCombatDuration();
            this.entityId = bestAttacker == null ? -1 : bestAttacker.getEntityId();
            break;
         case ENTITY_DIED:
            this.playerId = combatTracker.getFighter().getEntityId();
            this.entityId = bestAttacker == null ? -1 : bestAttacker.getEntityId();
            this.deathMessage = combatTracker.getDeathMessage().getUnformattedText();
      }
   }

public enum Event {
      ENTER_COMBAT,
      END_COMBAT,
      ENTITY_DIED;
   }
}
