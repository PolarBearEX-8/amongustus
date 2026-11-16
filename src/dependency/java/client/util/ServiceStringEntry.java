package client.util;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ResourceLocation;

public class ServiceStringEntry {
   public final String labelText3;
   public List list = Lists.newArrayList();
   public int[][] countArray;
   public BaseListEntry baseListEntry;
   public boolean enabled;
   public int count3;
   public int count6;
   public int count;
   public int count2;
   public float factor5;
   public float factor2;
   public float factor;
   public float factor6;
   public int count10;
   public int count5;
   public static String labelText2 = "builtin/clock";
   public static String labelText = "builtin/compass";
   public int count8 = -1;
   public float factor3;
   public float factor4;
   public int count12;
   public int count9;
   public int count7 = -1;
   public ServiceStringEntry serviceStringEntry3 = null;
   public boolean enabled5 = false;
   public int count4 = 0;
   public ServiceStringEntry serviceStringEntry = null;
   public ServiceStringEntry serviceStringEntry2 = null;
   public boolean enabled3 = false;
   public boolean enabled4 = false;
   public ServiceStringEntry serviceStringEntry4 = null;
   public int count11 = -1;
   public boolean enabled2 = false;

   private void applyIndexArray(int[] countArray2) {
      if (countArray2 != null) {
         long value = 0L;
         long value2 = 0L;
         long value3 = 0L;
         long value4 = 0L;

         for (int index = 0; index < countArray2.length; index++) {
            int count13 = countArray2[index];
            int count14 = count13 >> 24 & 0xFF;
            if (count14 >= 16) {
               count14 = count13 >> 16 & 0xFF;
               int count15 = count13 >> 8 & 0xFF;
               count13 &= 255;
               value += (long)count14;
               value2 += (long)count15;
               value3 += (long)count13;
               value4++;
            }
         }

         if (value4 > 0L) {
            int count16 = (int)(value / value4);
            int count17 = (int)(value2 / value4);
            int count18 = (int)(value3 / value4);
            count18 = count16 << 16 | count17 << 8 | count18;

            for (int index2 = 0; index2 < countArray2.length; index2++) {
               count17 = countArray2[index2];
               if ((count17 >> 24 & 0xFF) <= 16) {
                  countArray2[index2] = count18;
               }
            }
         }
      }
   }

   public void applyServiceStringEntry(ServiceStringEntry serviceStringEntry5) {
      this.count3 = serviceStringEntry5.count3;
      this.count6 = serviceStringEntry5.count6;
      this.count = serviceStringEntry5.count;
      this.count2 = serviceStringEntry5.count2;
      this.enabled = serviceStringEntry5.enabled;
      this.factor5 = serviceStringEntry5.factor5;
      this.factor2 = serviceStringEntry5.factor2;
      this.factor = serviceStringEntry5.factor;
      this.factor6 = serviceStringEntry5.factor6;
      if (serviceStringEntry5 != BuildManager.getListenerAdapter27().getServiceStringEntry()) {
         this.count8 = serviceStringEntry5.count8;
      }

      this.factor3 = serviceStringEntry5.factor3;
      this.factor4 = serviceStringEntry5.factor4;
      this.count12 = serviceStringEntry5.count12;
      this.count9 = serviceStringEntry5.count9;
      this.count7 = serviceStringEntry5.count7;
      this.count4 = serviceStringEntry5.count4;
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.applyIndex(this.count, this.count2, 0, 0, false);
      }

      this.count11 = serviceStringEntry5.count11;
   }

   public void applyIndex2(int count13) {
      this.count2 = count13;
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.applyIndex2(this.count2);
      }
   }

   public double computeValueByFloat(float factor7) {
      float factor8 = this.factor6 - this.factor;
      return (double)((factor7 - this.factor) / factor8 * 16.0F);
   }

   private void updateState5() {
      double offset = 1.0 - (double)this.count5 / (double)this.baseListEntry.computeCountByIndex(this.count10);
      int count13 = this.baseListEntry.computeCount(this.count10);
      int count14 = this.baseListEntry.size() == 0 ? this.list.size() : this.baseListEntry.size();
      count14 = this.baseListEntry.computeCount((this.count10 + 1) % count14);
      if (count13 != count14 && count14 >= 0 && count14 < this.list.size()) {
         int[][] countMatrix = (int[][])this.list.get(count13);
         int[][] countMatrix2 = (int[][])this.list.get(count14);
         if (this.countArray == null || this.countArray.length != countMatrix.length) {
            this.countArray = new int[countMatrix.length][];
         }

         for (int index = 0; index < countMatrix.length; index++) {
            if (this.countArray[index] == null) {
               this.countArray[index] = new int[countMatrix[index].length];
            }

            if (index < countMatrix2.length && countMatrix2[index].length == countMatrix[index].length) {
               for (int index2 = 0; index2 < countMatrix[index].length; index2++) {
                  int count15 = countMatrix[index][index2];
                  int count16 = countMatrix2[index][index2];
                  int count17 = (int)((double)((count15 & 0xFF0000) >> 16) * offset + (double)((count16 & 0xFF0000) >> 16) * (1.0 - offset));
                  int count18 = (int)((double)((count15 & 0xFF00) >> 8) * offset + (double)((count16 & 0xFF00) >> 8) * (1.0 - offset));
                  count16 = (int)((double)(count15 & 0xFF) * offset + (double)(count16 & 0xFF) * (1.0 - offset));
                  this.countArray[index][index2] = count15 & 0xFF000000 | count17 << 16 | count18 << 8 | count16;
               }
            }
         }

         SpecialLoggerEntry.applyIndexArray(this.countArray, this.count, this.count2, this.count3, this.count6, false, false);
      }
   }

   public void applyIndexByIndex(int count13) {
      this.count = count13;
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.applyIndexByIndex(this.count);
      }
   }

   private void updateState() {
      this.baseListEntry = null;
      this.applyList(Lists.newArrayList());
      this.count10 = 0;
      this.count5 = 0;
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.updateState();
      }
   }

   public float getFactor() {
      return this.factor;
   }

   public int getCount3() {
      return this.count3;
   }

   public float computeFactor(float factor7) {
      factor7 -= this.factor4;
      float factor8 = (float)this.count9 / (float)this.count2;
      return factor7 * factor8;
   }

   public void setBaseListEntry(BaseListEntry baseListEntry2) {
      this.baseListEntry = baseListEntry2;
   }

   public float getFactor5() {
      return this.factor5;
   }

   public int getCount6() {
      return this.count6;
   }

   private void applyIndex(int count13) {
      if (this.list.size() <= count13) {
         for (int index = this.list.size(); index <= count13; index++) {
            this.list.add(null);
         }
      }

      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.applyIndex(count13);
      }
   }

   public int getCount11() {
      return this.count11;
   }

   public void updateState3() {
      if (this.baseListEntry != null) {
         this.enabled2 = BitSetHolder.isActive() ? BitSetHolder.getByIndex(this.count11) : true;
         this.count5++;
         if (this.count5 >= this.baseListEntry.computeCountByIndex(this.count10)) {
            int count13 = this.baseListEntry.computeCount(this.count10);
            int count14 = this.baseListEntry.size() == 0 ? this.list.size() : this.baseListEntry.size();
            this.count10 = (this.count10 + 1) % count14;
            this.count5 = 0;
            count14 = this.baseListEntry.computeCount(this.count10);
            boolean enabled6 = this.enabled5;
            if (!this.enabled2) {
               return;
            }

            if (count13 != count14 && count14 >= 0 && count14 < this.list.size()) {
               SpecialLoggerEntry.applyIndexArray((int[][])this.list.get(count14), this.count, this.count2, this.count3, this.count6, false, enabled6);
            }
         } else if (this.baseListEntry.isEnabled()) {
            if (!this.enabled2) {
               return;
            }

            this.updateState5();
         }
      }
   }

   public float computeFactorByFloat(float factor7) {
      factor7 -= this.factor3;
      float factor8 = (float)this.count12 / (float)this.count;
      return factor7 * factor8;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public int getCount2() {
      return this.count2;
   }

   public List addAll() {
      ArrayList list2 = new ArrayList();
      list2.addAll(this.list);
      return list2;
   }

   public float getFactor6() {
      return this.factor6;
   }

   public void applyIndex4(int count13) {
      ArrayList list2 = Lists.newArrayList();

      for (int index = 0; index < this.list.size(); index++) {
         int[][] countMatrix = (int[][])this.list.get(index);
         if (countMatrix != null) {
            try {
               list2.add(SpecialLoggerEntry.resolveIndexArray(count13, this.count, countMatrix));
            } catch (Throwable throwable) {
               CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Generating mipmaps for frame");
               CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Frame being iterated");
               crashReportCategory.add("Frame index", index);
               crashReportCategory.applyText("Frame sizes", new CurvePool(this, countMatrix));
               throw new SecondaryRuntimeExceptionAdapter(crashReport);
            }
         }
      }

      this.applyList(list2);
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.applyIndex4(count13);
      }
   }

   public boolean matchesIResourceManager(IResourceManager iResourceManager, ResourceLocation location) {
      return false;
   }

   public float computeFactorByDouble(double offset) {
      float factor7 = this.factor2 - this.factor5;
      return this.factor5 + factor7 * (float)offset / 16.0F;
   }

   public boolean matchesIResourceManager2(IResourceManager iResourceManager, ResourceLocation location) {
      return true;
   }

   private void updateState7() {
      if (PreEndHandManager.enabled15) {
         String text = this.labelText3 + "_n";
         ResourceLocation location = new ResourceLocation(text);
         location = BuildManager.getListenerAdapter27().resolveResourceLocation(location);
         if (BuildManager.matchesResourceLocation3(location)) {
            this.serviceStringEntry = new ServiceStringEntry(text);
            this.serviceStringEntry.enabled3 = true;
            this.serviceStringEntry.applyServiceStringEntry(this);
            this.serviceStringEntry.applyIndex4(this.count4);
         }
      }

      if (PreEndHandManager.enabled37) {
         String text2 = this.labelText3 + "_s";
         ResourceLocation location2 = new ResourceLocation(text2);
         location2 = BuildManager.getListenerAdapter27().resolveResourceLocation(location2);
         if (BuildManager.matchesResourceLocation3(location2)) {
            this.serviceStringEntry2 = new ServiceStringEntry(text2);
            this.serviceStringEntry2.enabled3 = true;
            this.serviceStringEntry2.applyServiceStringEntry(this);
            this.serviceStringEntry2.applyIndex4(this.count4);
         }
      }
   }

   public void applyRayTraceEvaluator(RayTraceEvaluator rayTraceEvaluator) {
      if (this.count8 < 0) {
         this.count8 = rayTraceEvaluator.getCount();
      }
   }

   public void applyBufferedImageArray(BufferedImage[] bufferedImageArray, BaseListEntry baseListEntry2) throws IOException {
      this.updateState();
      int width = bufferedImageArray[0].getWidth();
      int height = bufferedImageArray[0].getHeight();
      this.count = width;
      this.count2 = height;
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.count = this.count;
         this.serviceStringEntry3.count2 = this.count2;
      }

      int[][] countMatrix = new int[bufferedImageArray.length][];

      for (int index = 0; index < bufferedImageArray.length; index++) {
         BufferedImage bufferedImage = bufferedImageArray[index];
         if (bufferedImage != null) {
            if (this.count >> index != bufferedImage.getWidth()) {
               bufferedImage = InternalPngManager.resolveBufferedImage(bufferedImage, this.count >> index);
            }

            if (index > 0 && (bufferedImage.getWidth() != width >> index || bufferedImage.getHeight() != height >> index)) {
               throw new RuntimeException(
                  String.format(
                     "Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", index, bufferedImage.getWidth(), bufferedImage.getHeight(), width >> index, height >> index
                  )
               );
            }

            countMatrix[index] = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
            bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), countMatrix[index], 0, bufferedImage.getWidth());
         }
      }

      if (baseListEntry2 == null) {
         if (height != width) {
            throw new RuntimeException("broken aspect ratio and not an animation");
         }

         this.list.add(countMatrix);
      } else {
         int count13 = height / width;
         this.count2 = this.count;
         if (baseListEntry2.size() > 0) {
            for (int count14 : (Iterable<Integer>)(Iterable<?>)baseListEntry2.getSet()) {
               if (count14 >= count13) {
                  throw new RuntimeException("invalid frameindex " + count14);
               }

               this.applyIndex(count14);
               this.list.set(count14, resolveIndexArray(countMatrix, width, width, count14));
            }

            this.baseListEntry = baseListEntry2;
         } else {
            ArrayList list2 = Lists.newArrayList();

            for (int index2 = 0; index2 < count13; index2++) {
               this.list.add(resolveIndexArray(countMatrix, width, width, index2));
               list2.add(new SplineReader(index2, -1));
            }

            this.baseListEntry = new BaseListEntry(list2, this.count, this.count2, baseListEntry2.getCount(), baseListEntry2.isEnabled());
         }
      }

      if (!this.enabled3) {
         if (BuildManager.canExecute()) {
            this.updateState7();
         }

         for (int index3 = 0; index3 < this.list.size(); index3++) {
            int[][] countMatrix2 = (int[][])this.list.get(index3);
            if (countMatrix2 != null && !this.labelText3.startsWith("minecraft:blocks/leaves_")) {
               for (int index4 = 0; index4 < countMatrix2.length; index4++) {
                  int[] countArray2 = countMatrix2[index4];
                  this.applyIndexArray(countArray2);
               }
            }
         }

         if (this.serviceStringEntry3 != null) {
            this.serviceStringEntry3.applyBufferedImageArray(bufferedImageArray, baseListEntry2);
         }
      }
   }

   public BaseListEntry getBaseListEntry() {
      return this.baseListEntry;
   }

   public int getCount8() {
      return this.count8;
   }

   public void setCount8(int count13) {
      this.count8 = count13;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public static void setLabelText(String labelText4) {
      labelText = labelText4;
   }

   public void updateState4() {
      this.list.clear();
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.updateState4();
      }
   }

   public float computeFactor(double offset) {
      float factor7 = this.factor6 - this.factor;
      return this.factor + factor7 * ((float)offset / 16.0F);
   }

   public boolean isActive() {
      return this.baseListEntry != null;
   }

   private static int[][] resolveIndexArray(int[][] countMatrix, int count13, int count14, int count15) {
      int[][] countMatrix2 = new int[countMatrix.length][];

      for (int index = 0; index < countMatrix.length; index++) {
         int[] countArray2 = countMatrix[index];
         if (countArray2 != null) {
            countMatrix2[index] = new int[(count13 >> index) * (count14 >> index)];
            System.arraycopy(countArray2, count15 * countMatrix2[index].length, countMatrix2[index], 0, countMatrix2[index].length);
         }
      }

      return countMatrix2;
   }

   public int[][] get(int count13) {
      return (int[][])this.list.get(count13);
   }

   public void applyIndex(int count13, int count14, int count15, int count16, boolean flag) {
      this.count3 = count15;
      this.count6 = count16;
      this.enabled = flag;
      float factor7 = (float)(0.01F / (double)count13);
      float factor8 = (float)(0.01F / (double)count14);
      this.factor5 = (float)count15 / (float)((double)count13) + factor7;
      this.factor2 = (float)(count15 + this.count) / (float)((double)count13) - factor7;
      this.factor = (float)count16 / (float)count14 + factor8;
      this.factor6 = (float)(count16 + this.count2) / (float)count14 - factor8;
      this.factor3 = Math.min(this.factor5, this.factor2);
      this.factor4 = Math.min(this.factor, this.factor6);
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.applyIndex(this.count, this.count2, 0, 0, false);
      }

      if (this.serviceStringEntry != null) {
         this.serviceStringEntry.applyServiceStringEntry(this);
      }

      if (this.serviceStringEntry2 != null) {
         this.serviceStringEntry2.applyServiceStringEntry(this);
      }
   }

   public static ServiceStringEntry resolveServiceStringEntry(ResourceLocation location) {
      String text = location.toString();
      return (ServiceStringEntry)(labelText2.equals(text)
         ? new StringEntryProvider(text)
         : (labelText.equals(text) ? new AuxiliaryStringEntryDispatcher(text) : new ServiceStringEntry(text)));
   }

   public void updateState6() {
      if (this.count7 < 0) {
         this.count7 = SpecialLoggerEntry.getCount();
         SpecialLoggerEntry.applyIndex(this.count7, this.count4, this.count, this.count2);
         InternalPngManager.updateState3();
      }

      InternalPngManager.applyIndex(this.count7);
   }

   public void applyList(List list2) {
      this.list = list2;
      if (this.serviceStringEntry3 != null) {
         this.serviceStringEntry3.applyList(list2);
      }
   }

   public int size() {
      return this.list.size();
   }

   public void applyIndex3(int count13) {
      this.count11 = count13;
      if (this.serviceStringEntry != null) {
         this.serviceStringEntry.applyIndex3(count13);
      }

      if (this.serviceStringEntry2 != null) {
         this.serviceStringEntry2.applyIndex3(count13);
      }
   }

   public float getFactor2() {
      return this.factor2;
   }

   public double computeValue(float factor7) {
      float factor8 = this.factor2 - this.factor5;
      return (double)((factor7 - this.factor5) / factor8 * 16.0F);
   }

   public static void setLabelText2(String labelText4) {
      labelText2 = labelText4;
   }

   public int getCount() {
      return this.count;
   }

   public void updateState2() {
      if (this.count7 >= 0) {
         SpecialLoggerEntry.applyIndex(this.count7);
         this.count7 = -1;
      }
   }

   private ServiceStringEntry(String text, boolean flag) {
      this.labelText3 = text;
      this.enabled5 = flag;
   }

   public ServiceStringEntry(String text) {
      this.labelText3 = text;
      if (BuildManager.isActive46()) {
         this.serviceStringEntry3 = new ServiceStringEntry(this.getLabelText3() + ".spriteSingle", true);
      }
   }

   @Override
   public String toString() {
      return "TextureAtlasSprite{name='"
         + this.labelText3
         + '\''
         + ", frameCount="
         + this.list.size()
         + ", rotated="
         + this.enabled
         + ", x="
         + this.count3
         + ", y="
         + this.count6
         + ", height="
         + this.count2
         + ", width="
         + this.count
         + ", u0="
         + this.factor5
         + ", u1="
         + this.factor2
         + ", v0="
         + this.factor
         + ", v1="
         + this.factor6
         + '}';
   }
}
