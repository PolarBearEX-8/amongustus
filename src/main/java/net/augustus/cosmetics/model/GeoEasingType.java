package net.augustus.cosmetics.model;

public enum GeoEasingType {
   NONE,
   Linear,
   EaseInOutSine,
   EaseInQuad,
   EaseOutQuad,
   EaseInCubic,
   EaseOutCubic,
   EaseInOutCubic,
   EaseOutQuart,
   EaseInOutQuart,
   EaseOutQuint,
   EaseInExpo,
   EaseInOutCirc,
   EaseInOutBack,
   EaseOutElastic,
   EaseInOutElastic,
   EaseInBounce,

   CUSTOM,
   Step,
   EaseInSine,
   EaseOutSine,
   EaseInOutQuad,
   EaseInQuart,
   EaseInQuint,
   EaseInOutQuint,
   EaseOutExpo,
   EaseInOutExpo,
   EaseInCirc,
   EaseOutCirc,
   EaseInBack,
   EaseOutBack,
   EaseInElastic,
   EaseOutBounce,
   EaseInOutBounce;


   public static GeoEasingType getEasingTypeFromString(String str) {

      for (GeoEasingType geoEasingType : values()) {
         if (geoEasingType.name().compareToIgnoreCase(str) == 0) {
            return geoEasingType;
         }
      }

      return null;
   }






}
