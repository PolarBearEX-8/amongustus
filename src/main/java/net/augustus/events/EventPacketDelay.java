package net.augustus.events;

import net.augustus.events.listeners.PacketDelayListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class EventPacketDelay extends AbstractEvent<PacketDelayListener> {
   private double maxTargetDistance;
   private Packet packet;
   private EnumPacketDirection packetDirection;
   public static final int ID = 25;
   private int outgoingDelayMs;
   private EntityLivingBase targetEntity;
   private ReentrantReadWriteLock queueLock;
   private Queue outboundQueue;
   private boolean delayed = false;
   private int incomingDelayMs;
   private INetHandler netHandler;
   private final PacketDelayDirection direction;

   public int getIncomingDelayMs() {
      return this.incomingDelayMs;
   }

   public EventPacketDelay(PacketDelayDirection packetDelayDirection, Packet packet2, INetHandler netHandler2, EnumPacketDirection enumPacketDirection) {
      this.targetEntity = null;
      this.incomingDelayMs = -1;
      this.outgoingDelayMs = -1;
      this.direction = packetDelayDirection;
      this.packet = packet2;
      this.netHandler = netHandler2;
      this.packetDirection = enumPacketDirection;
   }

   public void setIncomingDelayWithTarget(int n, EntityLivingBase entity, double d) {
      this.incomingDelayMs = n;
      this.targetEntity = entity;
      this.maxTargetDistance = d;
   }

   public ReentrantReadWriteLock getQueueLock() {
      return this.queueLock;
   }

   public void setDelayed(boolean flag) {
      if (flag) {
         this.delayed = true;
      }
   }

   public void setPacket(Packet packet2) {
      this.packet = packet2;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public EntityLivingBase getTargetEntity() {
      return this.targetEntity;
   }

   public boolean isDelayed() {
      return this.delayed;
   }

   public double getMaxTargetDistance() {
      return this.maxTargetDistance;
   }

   public void setQueueLock(ReentrantReadWriteLock reentrantReadWriteLock) {
      this.queueLock = reentrantReadWriteLock;
   }

   public void setDelayedWithTarget(boolean flag, EntityLivingBase entity, double d) {
      if (flag) {
         this.delayed = true;
         this.targetEntity = entity;
         this.maxTargetDistance = d;
      }
   }

   @Override
   public void call(PacketDelayListener listener) {
      if (this.direction == PacketDelayDirection.INCOMING) {
         listener.onIncomingPacketDelay(this);
      } else {
         listener.onOutgoingPacketDelay(this);
      }
   }

   public Queue getOutboundQueue() {
      return this.outboundQueue;
   }

   public void setPacketDirection(EnumPacketDirection enumPacketDirection) {
      this.packetDirection = enumPacketDirection;
   }

   public void setOutboundQueue(Queue queue) {
      this.outboundQueue = queue;
   }

   public boolean hasOutgoingDelay() {
      return this.getOutgoingDelayMs() > 0;
   }

   public int getOutgoingDelayMs() {
      return this.outgoingDelayMs;
   }

   public INetHandler getNetHandler() {
      return this.netHandler;
   }

   public EventPacketDelay(PacketDelayDirection packetDelayDirection, Packet packet2, Queue queue, ReentrantReadWriteLock reentrantReadWriteLock) {
      this.targetEntity = null;
      this.incomingDelayMs = -1;
      this.outgoingDelayMs = -1;
      this.direction = packetDelayDirection;
      this.packet = packet2;
      this.outboundQueue = queue;
      this.queueLock = reentrantReadWriteLock;
   }

   public void setOutgoingDelayMs(int n) {
      this.outgoingDelayMs = n;
   }

   public void setNetHandler(INetHandler netHandler2) {
      this.netHandler = netHandler2;
   }


   public EnumPacketDirection getPacketDirection() {
      return this.packetDirection;
   }

   public void setIncomingDelayMs(int n) {
      this.incomingDelayMs = n;
   }

   public boolean hasIncomingDelay() {
      return this.getIncomingDelayMs() > 0;
   }
}
