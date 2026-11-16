package client.setting;

import client.event.PostRenderFilter;
import client.render.AssetsRendersystemManager;
import client.util.FramebufferEntry;
import client.util.IntListEntry;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.item.GenericItemEntry;

public enum ManagedOptionType implements PostRenderFilter {
    MC_BOX("Box", new FramebufferEntry(new IntListEntry(AssetsRendersystemManager.resolveText("legacy.vert"), AssetsRendersystemManager.resolveText("impl/box.frag")))),
    KAWASE("Kawase", new GenericItemEntry(
            new IntListEntry(AssetsRendersystemManager.resolveText("legacy.vert"), AssetsRendersystemManager.resolveText("impl/kawase/upsample.frag")),
            new IntListEntry(AssetsRendersystemManager.resolveText("legacy.vert"), AssetsRendersystemManager.resolveText("impl/kawase/downsample.frag"))
         )),
    GAUSSIAN("Gaussian", new FramebufferEntry(new IntListEntry(AssetsRendersystemManager.resolveText("legacy.vert"), AssetsRendersystemManager.resolveText("impl/gaussian.frag"))));

   public final PostRenderFilter postRenderFilter;
   public final String labelText;

   public static ManagedOptionType resolveManagedOptionType(String text) {
      for (ManagedOptionType managedOptionType : values()) {
         if (managedOptionType.getLabelText().equalsIgnoreCase(text)) {
            return managedOptionType;
         }
      }

      return KAWASE;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public Framebuffer resolveFramebuffer(Framebuffer framebuffer, int count, int count2) {
      long value = 11133L;
      int count3 = -509083719;
      int count4 = (int)((value << 32 | (long)count3 << 32 >>> 32) << 32 >>> 32);
      return this.postRenderFilter.resolveFramebuffer(framebuffer, count, count4);
   }

   private ManagedOptionType(String text, PostRenderFilter postRenderFilter2) {
      this.labelText = text;
      this.postRenderFilter = postRenderFilter2;
   }


}
