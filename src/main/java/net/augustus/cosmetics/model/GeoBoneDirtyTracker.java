package net.augustus.cosmetics.model;

public class GeoBoneDirtyTracker {
   public boolean hasRotationChanged;
   public boolean hasPositionChanged;
   public IGeoBone model;
   public boolean hasScaleChanged;

   public GeoBoneDirtyTracker(boolean flag, boolean flag2, boolean flag3, IGeoBone geoBone) {
      this.hasScaleChanged = flag;
      this.hasPositionChanged = flag2;
      this.hasRotationChanged = flag3;
      this.model = geoBone;
   }
}
