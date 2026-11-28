package net.minecraft.world.border;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;

public class WorldBorder {
   private final List listeners = Lists.newArrayList();
   private double centerX = 0.0;
   private double centerZ = 0.0;
   private double startDiameter = 6.0E7;
   private double endDiameter = this.startDiameter;
   private long endTime;
   private long startTime;
   private int worldSize = 29999984;
   private double damageAmount = 0.2;
   private double damageBuffer = 5.0;
   private int warningTime = 15;
   private int warningDistance = 5;

   public List getListeners() {
      return Lists.newArrayList(this.listeners);
   }

   public int getWarningTime() {
      return this.warningTime;
   }

   public boolean contains(BlockPos pos) {
      return (double)(pos.getX() + 1) > this.minX()
         && (double)pos.getX() < this.maxX()
         && (double)(pos.getZ() + 1) > this.minZ()
         && (double)pos.getZ() < this.maxZ();
   }

   public void setDamageAmount(double d) {
      this.damageAmount = d;

      for (IBorderListener listener : (Iterable<IBorderListener>)(this.getListeners())) {
         listener.onDamageAmountChanged(this, d);
      }
   }

   public int getWarningDistance() {
      return this.warningDistance;
   }

   public double getDamageAmount() {
      return this.damageAmount;
   }

   public void setWarningTime(int n) {
      this.warningTime = n;

      for (IBorderListener listener : (Iterable<IBorderListener>)(this.getListeners())) {
         listener.onWarningTimeChanged(this, n);
      }
   }

   public void setDamageBuffer(double d) {
      this.damageBuffer = d;

      for (IBorderListener listener : (Iterable<IBorderListener>)(this.getListeners())) {
         listener.onDamageBufferChanged(this, d);
      }
   }

   public double maxZ() {
      double d = this.getCenterZ() + this.getDiameter() / 2.0;
      if (d > (double)this.worldSize) {
         d = (double)this.worldSize;
      }

      return d;
   }

   public int getSize() {
      return this.worldSize;
   }

   public double getResizeSpeed() {
      return this.endTime == this.startTime ? 0.0 : Math.abs(this.startDiameter - this.endDiameter) / (double)(this.endTime - this.startTime);
   }

   public double getCenterZ() {
      return this.centerZ;
   }

   public void setTransition(double d) {
      this.startDiameter = d;
      this.endDiameter = d;
      this.endTime = System.currentTimeMillis();
      this.startTime = this.endTime;

      for (IBorderListener listener : (Iterable<IBorderListener>)(this.getListeners())) {
         listener.onSizeChanged(this, d);
      }
   }

   public boolean contains(AxisAlignedBB box) {
      return box.maxX > this.minX() && box.minX < this.maxX() && box.maxZ > this.minZ() && box.minZ < this.maxZ();
   }

   public long getTimeUntilTarget() {
      return this.getStatus() != EnumBorderStatus.STATIONARY ? this.endTime - System.currentTimeMillis() : 0L;
   }

   public void setCenter(double d, double d2) {
      this.centerX = d;
      this.centerZ = d2;

      for (IBorderListener listener : (Iterable<IBorderListener>)(this.getListeners())) {
         listener.onCenterChanged(this, d, d2);
      }
   }

   public double getClosestDistance(double d, double d2) {
      double d3 = d2 - this.minZ();
      double d4 = this.maxZ() - d2;
      double d7 = Math.min(d - this.minX(), this.maxX() - d);
      d7 = Math.min(d7, d3);
      return Math.min(d7, d4);
   }

   public void setTransition(double d, double d2, long l) {
      this.startDiameter = d;
      this.endDiameter = d2;
      this.startTime = System.currentTimeMillis();
      this.endTime = this.startTime + l;

      for (IBorderListener listener : (Iterable<IBorderListener>)(this.getListeners())) {
         listener.onTransitionStarted(this, d, d2, l);
      }
   }

   public double getCenterX() {
      return this.centerX;
   }

   public boolean contains(ChunkCoordIntPair chunkCoordIntPair) {
      return (double)chunkCoordIntPair.getXEnd() > this.minX()
         && (double)chunkCoordIntPair.getXStart() < this.maxX()
         && (double)chunkCoordIntPair.getZEnd() > this.minZ()
         && (double)chunkCoordIntPair.getZStart() < this.maxZ();
   }

   public void setSize(int n) {
      this.worldSize = n;
   }

   public double getDiameter() {
      if (this.getStatus() != EnumBorderStatus.STATIONARY) {
         double d = (double)((float)(System.currentTimeMillis() - this.startTime) / (float)(this.endTime - this.startTime));
         if (d < 1.0) {
            return this.startDiameter + (this.endDiameter - this.startDiameter) * d;
         }

         this.setTransition(this.endDiameter);
      }

      return this.startDiameter;
   }

   public double minX() {
      double d = this.getCenterX() - this.getDiameter() / 2.0;
      if (d < (double)(-this.worldSize)) {
         d = (double)(-this.worldSize);
      }

      return d;
   }

   public double minZ() {
      double d = this.getCenterZ() - this.getDiameter() / 2.0;
      if (d < (double)(-this.worldSize)) {
         d = (double)(-this.worldSize);
      }

      return d;
   }

   public EnumBorderStatus getStatus() {
      return this.endDiameter < this.startDiameter
         ? EnumBorderStatus.SHRINKING
         : (this.endDiameter > this.startDiameter ? EnumBorderStatus.GROWING : EnumBorderStatus.STATIONARY);
   }

   public double getTargetSize() {
      return this.endDiameter;
   }

   public double getClosestDistance(Entity entity) {
      return this.getClosestDistance(entity.posX, entity.posZ);
   }

   public double maxX() {
      double d = this.getCenterX() + this.getDiameter() / 2.0;
      if (d > (double)this.worldSize) {
         d = (double)this.worldSize;
      }

      return d;
   }

   public void addListener(IBorderListener listener) {
      this.listeners.add(listener);
   }

   public double getDamageBuffer() {
      return this.damageBuffer;
   }

   public void setWarningDistance(int n) {
      this.warningDistance = n;

      for (IBorderListener listener : (Iterable<IBorderListener>)(this.getListeners())) {
         listener.onWarningDistanceChanged(this, n);
      }
   }
}
