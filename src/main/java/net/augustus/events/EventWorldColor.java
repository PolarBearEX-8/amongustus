package net.augustus.events;

import net.augustus.events.listeners.WorldColorListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventWorldColor extends AbstractEvent<WorldColorListener> {
   private float red;
   private float blue;
   private float green;
   public static final int ID = 44;
   private WorldColorType type;


   private void reset(float f, float f2, float f3, WorldColorType worldColorType) {
      this.red = f;
      this.green = f2;
      this.blue = f3;
      this.type = worldColorType;
   }

   public float getRed() {
      return this.red;
   }

   public float getGreen() {
      return this.green;
   }

   public float getBlue() {
      return this.blue;
   }

   public void setRed(float f) {
      this.red = f / 255.0F;
   }

   @Override
   public void call(WorldColorListener listener) {
      if (this.type == WorldColorType.WORLD_COLOR) {
         listener.onWorldColor(this);
      } else {
         listener.onFogColor(this);
      }
   }

   public void setBlue(float f) {
      this.blue = f / 255.0F;
   }

   public static void reset(EventWorldColor event, float f, float f2, float f3, WorldColorType worldColorType) {
      event.reset(f, f2, f3, worldColorType);
   }

   public void setGreen(float f) {
      this.green = f / 255.0F;
   }
}
