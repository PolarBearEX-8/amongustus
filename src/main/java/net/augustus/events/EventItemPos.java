package net.augustus.events;

import net.augustus.events.listeners.ItemPosListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventItemPos extends AbstractEvent<ItemPosListener> {
   public static final int ID = 13;
   private double blockY;
   private double posX;
   private double blockX;
   private double scale;
   private double posY;
   private double blockZ;
   private double posZ;

   public void setBlockX(double d) {
      this.blockX = d;
   }

   public static void reset(
      EventItemPos event, double d, double d2, double d3, double d4, double d5, double d6, double d7
   ) {
      event.reset(d, d2, d3, d4, d5, d6, d7);
   }

   private void reset(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      this.posX = d;
      this.posY = d2;
      this.posZ = d3;
      this.scale = d4;
      this.blockX = d5;
      this.blockY = d6;
      this.blockZ = d7;
   }

   public double getBlockY() {
      return this.blockY;
   }

   public double getBlockZ() {
      return this.blockZ;
   }


   public void setBlockY(double d) {
      this.blockY = d;
   }

   public void setScale(double d) {
      this.scale = d;
   }

   public double getPosX() {
      return this.posX;
   }

   public double getBlockX() {
      return this.blockX;
   }

   public void setBlockZ(double d) {
      this.blockZ = d;
   }

   @Override
   public void call(ItemPosListener listener) {
      listener.onItemPos(this);
   }

   public double getPosZ() {
      return this.posZ;
   }

   public void setPosZ(double d) {
      this.posZ = d;
   }

   public double getPosY() {
      return this.posY;
   }

   public void setPosX(double d) {
      this.posX = d;
   }

   public double getScale() {
      return this.scale;
   }

   public void setPosY(double d) {
      this.posY = d;
   }
}
