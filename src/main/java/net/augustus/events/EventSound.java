package net.augustus.events;

import net.augustus.events.listeners.SoundListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventSound extends AbstractEvent<SoundListener> {
   private double z;
   public static final int ID = 35;
   private double y;
   private String soundName;
   private double x;

   public void setSoundName(String str) {
      this.soundName = str;
   }

   public String getSoundName() {
      return this.soundName;
   }

   public void setY(double d) {
      this.y = d;
   }

   private void reset(double d, double d2, double d3, String str) {
      this.x = d;
      this.y = d2;
      this.z = d3;
      this.soundName = str;
   }

   public static void reset(EventSound event, double d, double d2, double d3, String str) {
      event.reset(d, d2, d3, str);
   }

   public void setX(double d) {
      this.x = d;
   }


   public double getY() {
      return this.y;
   }

   public double getX() {
      return this.x;
   }

   public double getZ() {
      return this.z;
   }

   public void setZ(double d) {
      this.z = d;
   }

   @Override
   public void call(SoundListener listener) {
      listener.onSound(this);
   }
}
