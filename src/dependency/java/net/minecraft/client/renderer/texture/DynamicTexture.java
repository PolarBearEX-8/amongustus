package net.minecraft.client.renderer.texture;

import client.util.ListenerAdapter26;
import client.util.SpecialLoggerEntry;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.resources.IResourceManager;

public class DynamicTexture extends ListenerAdapter26 {
   public final int[] countArray;
   public final int count2;
   public final int count3;

   public int[] getCountArray() {
      return this.countArray;
   }

   public void updateState2() {
      SpecialLoggerEntry.applyIndexArray(this.getCount(), this.countArray, this.count2, this.count3);
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
   }

   public DynamicTexture(BufferedImage bufferedImage) {
      this(bufferedImage.getWidth(), bufferedImage.getHeight());
      bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this.countArray, 0, bufferedImage.getWidth());
      this.updateState2();
   }

   public DynamicTexture(int count, int count4) {
      this.count2 = count;
      this.count3 = count4;
      this.countArray = new int[count * count4];
      SpecialLoggerEntry.applyIndex(this.getCount(), count, count4);
   }
}
