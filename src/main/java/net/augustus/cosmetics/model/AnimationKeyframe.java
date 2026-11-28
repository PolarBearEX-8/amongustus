package net.augustus.cosmetics.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimationKeyframe {
   public List easingArgs;
   private Object endValue;
   private Object startValue;
   public GeoEasingType easingType = GeoEasingType.Linear;
   private Double length;

   public void setStartValue(Object obj) {
      this.startValue = obj;
   }

   public AnimationKeyframe(Double d, Object obj, Object obj2) {
      this.easingArgs = new ArrayList();
      this.length = d;
      this.startValue = obj;
      this.endValue = obj2;
   }

   @Override
   public boolean equals(Object obj) {
      return obj instanceof AnimationKeyframe && this.hashCode() == obj.hashCode();
   }

   public void setEndValue(Object obj) {
      this.endValue = obj;
   }

   public AnimationKeyframe(Double d, Object obj, Object obj2, GeoEasingType geoEasingType, List list) {
      this.easingArgs = new ArrayList();
      this.length = d;
      this.startValue = obj;
      this.endValue = obj2;
      this.easingType = geoEasingType;
      this.easingArgs = list;
   }

   public AnimationKeyframe(Double d, Object obj, Object obj2, GeoEasingType geoEasingType) {
      this.easingArgs = new ArrayList();
      this.length = d;
      this.startValue = obj;
      this.endValue = obj2;
      this.easingType = geoEasingType;
   }

   public Double getLength() {
      return this.length;
   }

   public Object getStartValue() {
      return this.startValue;
   }

   public void setLength(Double d) {
      this.length = d;
   }

   public Object getEndValue() {
      return this.endValue;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.length, this.startValue, this.endValue);
   }
}
