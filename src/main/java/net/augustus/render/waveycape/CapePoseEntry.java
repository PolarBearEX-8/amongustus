package net.augustus.render.waveycape;

public final class CapePoseEntry {
   public final CapeMatrix3f normal;
   public final CapeMatrix4f pose;

   public CapeMatrix4f getPose() {
      return this.pose;
   }

   public CapeMatrix3f getNormal() {
      return this.normal;
   }

   public CapePoseEntry(CapeMatrix4f capeMatrix4f, CapeMatrix3f capeMatrix3f) {
      this.pose = capeMatrix4f;
      this.normal = capeMatrix3f;
   }
}
