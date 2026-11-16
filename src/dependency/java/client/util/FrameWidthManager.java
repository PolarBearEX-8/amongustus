package client.util;

import client.event.BridgeListener;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Properties;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class FrameWidthManager {
   public String labelText = null;
   public String labelText2 = null;
   public ResourceLocation resourceLocation = null;
   public int count3 = -1;
   public int count = 0;
   public int count7 = 0;
   public int count5 = 0;
   public int count2 = 0;
   public SmoothAggregator[] smoothAggregatorArray = null;
   public int count6 = 0;
   public boolean enabled = false;
   public int count4 = 0;
   public ByteBuffer byteBuffer = null;
   public byte[] byteFlagArray = null;
   public ByteBuffer byteBuffer2 = null;
   public boolean enabled3 = true;
   public boolean enabled2 = true;

   public String getLabelText2() {
      return this.labelText2;
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public boolean isActive() {
      SmoothAggregator smoothAggregator = this.getSmoothAggregator();
      if (smoothAggregator == null) {
         return false;
      } else {
         smoothAggregator.count++;
         if (smoothAggregator.count < smoothAggregator.count3) {
            return this.enabled;
         } else {
            smoothAggregator.count = 0;
            this.count6++;
            if (this.count6 >= this.smoothAggregatorArray.length) {
               this.count6 = 0;
            }

            return true;
         }
      }
   }

   public SmoothAggregator resolveSmoothAggregator(int count8) {
      int count9 = count8;
      if (this.smoothAggregatorArray.length <= 0) {
         return null;
      } else {
         if (count8 < 0 || count8 >= this.smoothAggregatorArray.length) {
            count9 = 0;
         }

         return this.smoothAggregatorArray[count9];
      }
   }

   public SmoothAggregator getSmoothAggregator() {
      return this.resolveSmoothAggregator(this.count6);
   }

   public int getCount() {
      return this.smoothAggregatorArray.length;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private void applySmoothAggregator(SmoothAggregator smoothAggregator, SmoothAggregator smoothAggregator2, double offset) {
      int count8 = this.count5 * this.count2 * 4;
      int count9 = count8 * smoothAggregator.count2;
      if (count9 + count8 <= this.byteBuffer2.limit()) {
         int count10 = count8 * smoothAggregator2.count2;
         if (count10 + count8 <= this.byteBuffer2.limit()) {
            ((Buffer)this.byteBuffer).clear();

            for (int index = 0; index < count8; index++) {
               int count11 = this.byteBuffer2.get(count9 + index) & 255;
               int count12 = this.byteBuffer2.get(count10 + index) & 255;
               byte byteValue = (byte)this.computeCount(count11, count12, offset);
               this.byteBuffer.put(byteValue);
            }

            ((Buffer)this.byteBuffer).flip();
            GlStateManager.setActiveTexture(this.count3);
            GL11.glTexSubImage2D(3553, 0, this.count, this.count7, this.count5, this.count2, 6408, 5121, this.byteBuffer);
         }
      }
   }

   private int computeCount(int count8, int count9, double offset) {
      return (int)((double)count8 * (1.0 - offset) + (double)count9 * offset);
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public void updateState() {
      if (this.enabled2) {
         if (this.count3 < 0) {
            BridgeListener bridgeListener = InternalPngManager.resolveBridgeListenerByResourceLocation(this.resourceLocation);
            if (bridgeListener == null) {
               this.enabled2 = false;
               return;
            }

            this.count3 = bridgeListener.getCount();
         }

         if (this.byteBuffer2 == null) {
            this.byteBuffer2 = VectorMatcher.resolveByteBuffer(this.byteFlagArray.length);
            this.byteBuffer2.put(this.byteFlagArray);
            ((Buffer)this.byteBuffer2).flip();
            this.byteFlagArray = null;
         }

         this.enabled3 = BitSetHolder.isActive() ? BitSetHolder.get(this.count3) : true;
         if (this.isActive() && this.enabled3) {
            int count8 = this.count5 * this.count2 * 4;
            SmoothAggregator smoothAggregator = this.getSmoothAggregator();
            if (smoothAggregator != null) {
               int count9 = count8 * smoothAggregator.count2;
               if (count9 + count8 <= this.byteBuffer2.limit()) {
                  if (!this.enabled || smoothAggregator.count <= 0) {
                     ((Buffer)this.byteBuffer2).position(count9);
                     GlStateManager.setActiveTexture(this.count3);
                     GL11.glTexSubImage2D(3553, 0, this.count, this.count7, this.count5, this.count2, 6408, 5121, this.byteBuffer2);
                  } else if (this.count4 <= 1 || smoothAggregator.count % this.count4 == 0) {
                     SmoothAggregator smoothAggregator2 = this.resolveSmoothAggregator(this.count6 + 1);
                     double offset = 1.0 * (double)smoothAggregator.count / (double)smoothAggregator.count3;
                     this.applySmoothAggregator(smoothAggregator, smoothAggregator2, offset);
                  }
               }
            }
         }
      }
   }

   public FrameWidthManager(String text, byte[] byteValueArray, String text2, ResourceLocation location, int count8, int count9, int count10, int count11, Properties properties) {
      this.labelText = text;
      this.labelText2 = text2;
      this.resourceLocation = location;
      this.count = count8;
      this.count7 = count9;
      this.count5 = count10;
      this.count2 = count11;
      int count12 = count10 * count11 * 4;
      if (byteValueArray.length % count12 != 0) {
         BuildManager.applyText3("Invalid animated texture length: " + byteValueArray.length + ", frameWidth: " + count10 + ", frameHeight: " + count11);
      }

      this.byteFlagArray = byteValueArray;
      int count13 = byteValueArray.length / count12;
      if (properties.get("tile.0") != null) {
         for (int index = 0; properties.get("tile." + index) != null; index++) {
            count13 = index + 1;
         }
      }

      int count14 = Math.max(BuildManager.computeCount((String)properties.get("duration"), 1), 1);
      this.smoothAggregatorArray = new SmoothAggregator[count13];

      for (int index2 = 0; index2 < this.smoothAggregatorArray.length; index2++) {
         int count15 = BuildManager.computeCount((String)properties.get("tile." + index2), index2);
         int count16 = Math.max(BuildManager.computeCount((String)properties.get("duration." + index2), count14), 1);
         SmoothAggregator smoothAggregator = new SmoothAggregator(count15, count16);
         this.smoothAggregatorArray[index2] = smoothAggregator;
      }

      this.enabled = BuildManager.matchesText(properties.getProperty("interpolate"), false);
      this.count4 = BuildManager.computeCount(properties.getProperty("skip"), 0);
      if (this.enabled) {
         this.byteBuffer = VectorMatcher.resolveByteBuffer(count12);
      }
   }
}
