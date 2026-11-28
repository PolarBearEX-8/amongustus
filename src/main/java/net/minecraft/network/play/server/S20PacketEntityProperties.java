package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S20PacketEntityProperties implements Packet {
   private int entityId;
   private final List snapshots = Lists.newArrayList();

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public List getSnapshots() {
      return this.snapshots;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      int n = buf.readInt();

      for (int i = 0; i < n; i++) {
         String str = buf.readStringFromBuffer(64);
         double d = buf.readDouble();
         ArrayList list = Lists.newArrayList();
         int n2 = buf.readVarIntFromBuffer();

         for (int j = 0; j < n2; j++) {
            UUID uuid = buf.readUuid();
            list.add(new AttributeModifier(uuid, "Unknown synced attribute modifier", buf.readDouble(), buf.readByte()));
         }

         this.snapshots.add(new S20PacketEntityProperties.Snapshot(this, str, d, list));
      }
   }

   public S20PacketEntityProperties(int n, Collection items) {
      this.entityId = n;

      for (IAttributeInstance attributeInstance : (Iterable<IAttributeInstance>)(items)) {
         this.snapshots
            .add(new S20PacketEntityProperties.Snapshot(this, attributeInstance.getAttribute().getAttributeUnlocalizedName(), attributeInstance.getBaseValue(), attributeInstance.getModifiers()));
      }
   }

   public S20PacketEntityProperties() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityProperties(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeInt(this.snapshots.size());

      for (S20PacketEntityProperties.Snapshot snapshot : (Iterable<S20PacketEntityProperties.Snapshot>)(this.snapshots)) {
         buf.writeString(snapshot.getName());
         buf.writeDouble(snapshot.getBaseValue());
         buf.writeVarIntToBuffer(snapshot.getModifiers().size());

         for (AttributeModifier attributeModifier : (Iterable<AttributeModifier>)(snapshot.getModifiers())) {
            buf.writeUuid(attributeModifier.getID());
            buf.writeDouble(attributeModifier.getAmount());
            buf.writeByte(attributeModifier.getOperation());
         }
      }
   }


   public static class Snapshot {
      private final String name;
      private final double baseValue;
      private final Collection modifiers;
      public final S20PacketEntityProperties parentPacket;

      public String getName() {
         return this.name;
      }

      public Snapshot(S20PacketEntityProperties packet, String str, double d, Collection items) {
         this.parentPacket = packet;
         this.name = str;
         this.baseValue = d;
         this.modifiers = items;
      }

      public Collection getModifiers() {
         return this.modifiers;
      }

      public double getBaseValue() {
         return this.baseValue;
      }
   }
}
