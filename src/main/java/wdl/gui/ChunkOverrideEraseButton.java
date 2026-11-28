package wdl.gui;

public class ChunkOverrideEraseButton extends ChunkOverrideModeButton {
   public final GuiWDLChunkOverrides ownerGui;

   public ChunkOverrideEraseButton(GuiWDLChunkOverrides guiWDLChunkOverrides, int n, int n2, int n3, ChunkOverrideMode chunkOverrideMode) {
      super(guiWDLChunkOverrides, n, n2, n3, chunkOverrideMode);
      this.ownerGui = guiWDLChunkOverrides;
      this.enabled = false;
   }
}
