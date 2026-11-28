package net.augustus.events;

import net.augustus.events.listeners.LiquidPhysicsListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventLiquidPhysics extends AbstractEvent<LiquidPhysicsListener> {
   private double lavaHorizontalFriction;
   private float waterHorizontalFriction;
   private boolean ignoreLava;
   private double lavaGravity;
   private boolean ignoreWater;
   private double lavaVerticalFriction;
   private double waterGravity;
   private double airGravity = 0.08;
   private double waterVerticalFriction;
   public static final int ID = 66;

   public double getWaterVerticalFriction() {
      return this.waterVerticalFriction;
   }

   public double getWaterGravity() {
      return this.waterGravity;
   }

   public double getLavaVerticalFriction() {
      return this.lavaVerticalFriction;
   }

   @Override
   public void call(LiquidPhysicsListener listener) {
      listener.onLiquidPhysics(this);
   }

   public double getLavaGravity() {
      return this.lavaGravity;
   }

   public float getWaterHorizontalFriction() {
      return this.waterHorizontalFriction;
   }

   public void setWaterVerticalFriction(double d) {
      this.waterVerticalFriction = d;
   }

   private void reset(float f, double d, double d2, boolean flag, double d3, double d4, double d5, boolean flag2, double d6) {
      this.waterHorizontalFriction = f;
      this.waterVerticalFriction = d;
      this.waterGravity = d2;
      this.ignoreWater = flag;
      this.lavaHorizontalFriction = d3;
      this.lavaVerticalFriction = d4;
      this.lavaGravity = d5;
      this.ignoreLava = flag2;
      this.airGravity = d6;
   }

   public void setIgnoreWater(boolean flag) {
      this.ignoreWater = flag;
   }

   public boolean isIgnoreLava() {
      return this.ignoreLava;
   }

   public static void reset(
      EventLiquidPhysics event,
      float f,
      double d,
      double d2,
      boolean flag,
      double d3,
      double d4,
      double d5,
      boolean flag2,
      double d6
   ) {
      event.reset(f, d, d2, flag, d3, d4, d5, flag2, d6);
   }

   public double getAirGravity() {
      return this.airGravity;
   }

   public double getLavaHorizontalFriction() {
      return this.lavaHorizontalFriction;
   }

   public boolean isIgnoreWater() {
      return this.ignoreWater;
   }

   public void setLavaGravity(double d) {
      this.lavaGravity = d;
   }

   public void setAirGravity(double d) {
      this.airGravity = d;
   }

   public void setWaterGravity(double d) {
      this.waterGravity = d;
   }

   public void setIgnoreLava(boolean flag) {
      this.ignoreLava = flag;
   }

   public void setLavaHorizontalFriction(double d) {
      this.lavaHorizontalFriction = d;
   }


   public void setWaterHorizontalFriction(float f) {
      this.waterHorizontalFriction = f;
   }

   public void setLavaVerticalFriction(double d) {
      this.lavaVerticalFriction = d;
   }
}
