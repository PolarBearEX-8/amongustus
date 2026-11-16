package client.event;

import client.setting.AdvancedOptionType;
import client.setting.EventPacketServerboundType;
import client.util.INetHandler;
import client.util.Packet;
import de.florianmichael.dietrichevents2.AbstractEvent;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.entity.EntityLivingBase;

public class EventSendPacket extends AbstractEvent {
   public INetHandler iNetHandler;
   public final AdvancedOptionType advancedOptionType;
   public EntityLivingBase targetEntity;
   public Queue queue;
   public static final int count = 25;
   public boolean state = false;
   public ReentrantReadWriteLock reentrantReadWriteLock;
   public EventPacketServerboundType eventPacketServerboundType;
   public double coordOffset;
   public int indexOffset2;
   public Packet packet;
   public int indexOffset;

   public boolean isActive() {
      return this.getIndexOffset2() > 0;
   }

   public void applyEntityLivingBase(int count2, EntityLivingBase livingEntity, double offset) {
      this.indexOffset = count2;
      this.targetEntity = livingEntity;
      this.coordOffset = offset;
   }

   public int getIndexOffset2() {
      return this.indexOffset2;
   }

   public void applyEntityLivingBase2(boolean flag, int count2, EntityLivingBase livingEntity, double offset, int count3) {
      if (flag) {
         this.state = true;
         this.targetEntity = livingEntity;
         this.coordOffset = offset;
      }
   }

   public void setQueue(Queue queue2) {
      this.queue = queue2;
   }

   public void setINetHandler(INetHandler iNetHandler2) {
      this.iNetHandler = iNetHandler2;
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public void call(Object entry) {
      NetworkPacketListener packet2 = (NetworkPacketListener)entry;
      this.processPacket(packet2);
   }

   public boolean isState() {
      return this.state;
   }

   public void processPacket(NetworkPacketListener packet2) {
      if (this.advancedOptionType == AdvancedOptionType.INCOMING) {
         packet2.onNetworkPacket(this);
      } else {
         packet2.onPostNetworkPacket(this);
      }
   }

   public void setPacket(Packet packet2) {
      this.packet = packet2;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public ReentrantReadWriteLock getReentrantReadWriteLock() {
      return this.reentrantReadWriteLock;
   }

   public boolean isActive2() {
      return this.getIndexOffset() > 0;
   }

   public void setEventPacketServerboundType(EventPacketServerboundType eventPacketServerboundType2) {
      this.eventPacketServerboundType = eventPacketServerboundType2;
   }

   public EntityLivingBase getTargetEntity() {
      return this.targetEntity;
   }

   public EventPacketServerboundType getEventPacketServerboundType() {
      return this.eventPacketServerboundType;
   }

   public void setIndexOffset2(int indexOffset3) {
      this.indexOffset2 = indexOffset3;
   }

   public INetHandler getINetHandler() {
      return this.iNetHandler;
   }

   public void setIndexOffset(int indexOffset3) {
      this.indexOffset = indexOffset3;
   }

   public Queue getQueue() {
      return this.queue;
   }

   public double getCoordOffset() {
      return this.coordOffset;
   }

   public void setCancelled(boolean cancelled) {
      if (cancelled) {
         this.state = true;
      }
   }

   public void setReentrantReadWriteLock(ReentrantReadWriteLock reentrantReadWriteLock2) {
      this.reentrantReadWriteLock = reentrantReadWriteLock2;
   }

   public EventSendPacket(AdvancedOptionType advancedOptionType2, Packet packet2, INetHandler iNetHandler2, EventPacketServerboundType packet3) {
      this.targetEntity = null;
      this.indexOffset = -1;
      this.indexOffset2 = -1;
      this.advancedOptionType = advancedOptionType2;
      this.packet = packet2;
      this.iNetHandler = iNetHandler2;
      this.eventPacketServerboundType = packet3;
   }

   public EventSendPacket(AdvancedOptionType advancedOptionType2, Packet packet2, Queue queue2, ReentrantReadWriteLock reentrantReadWriteLock2) {
      this.targetEntity = null;
      this.indexOffset = -1;
      this.indexOffset2 = -1;
      this.advancedOptionType = advancedOptionType2;
      this.packet = packet2;
      this.queue = queue2;
      this.reentrantReadWriteLock = reentrantReadWriteLock2;
   }
}
