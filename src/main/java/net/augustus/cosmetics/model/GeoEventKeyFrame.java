package net.augustus.cosmetics.model;

public class GeoEventKeyFrame {
   private Double startTick;
   private Object eventData;

   public GeoEventKeyFrame(Double d, Object obj) {
      this.startTick = d;
      this.eventData = obj;
   }

   public Double getStartTick() {
      return this.startTick;
   }

   public Object getEventData() {
      return this.eventData;
   }



}
