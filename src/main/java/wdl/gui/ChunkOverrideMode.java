package wdl.gui;

public enum ChunkOverrideMode {
   PANNING(0, 128),
   REQUESTING(16, 128),
   ERASING(32, 128),
   MOVING(48, 128);

public final int overlayU;
   public final int overlayV;
private ChunkOverrideMode(int n, int n2) {
      this.overlayU = n;
      this.overlayV = n2;
   }
}
