package client.util;

import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class DistanceEngine {
   public String labelText;
   public ResourceLocation[] resourceLocationArray;
   public int count = 1;
   public int count2 = 64;
   public int count3 = 3;
   public int count4 = 3;
   public int count5 = -2130706433;
   public int count6 = 16777215;
   public int count7 = 0;
   public int count8 = Integer.MIN_VALUE;

   public int getCount3() {
      return this.count3;
   }

   @Override
   public String toString() {
      return ""
         + this.labelText
         + ", weight: "
         + this.count
         + ", blur: "
         + this.count2
         + " "
         + this.count3
         + " "
         + this.count4
         + ", overlay: "
         + this.count5
         + " "
         + this.count6
         + " "
         + this.count7
         + " "
         + this.count8;
   }

   public ResourceLocation[] getResourceLocationArray() {
      return this.resourceLocationArray;
   }

   public int getCount5() {
      return this.count5;
   }

   public int getCount8() {
      return this.count8;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount6() {
      return this.count6;
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount7() {
      return this.count7;
   }

   public int getCount4() {
      return this.count4;
   }

   public DistanceEngine(String text, Properties properties) {
      NetherManager netherManager = new NetherManager("CustomPanorama");
      this.labelText = text;
      this.resourceLocationArray = new ResourceLocation[6];

      for (int index = 0; index < this.resourceLocationArray.length; index++) {
         this.resourceLocationArray[index] = new ResourceLocation(text + "/panorama_" + index + ".png");
      }

      this.count = netherManager.computeCount(properties.getProperty("weight"), 1);
      this.count2 = netherManager.computeCount(properties.getProperty("blur1"), 64);
      this.count3 = netherManager.computeCount(properties.getProperty("blur2"), 3);
      this.count4 = netherManager.computeCount(properties.getProperty("blur3"), 3);
      this.count5 = NetherManager.computeCountByText(properties.getProperty("overlay1.top"), -2130706433);
      this.count6 = NetherManager.computeCountByText(properties.getProperty("overlay1.bottom"), 16777215);
      this.count7 = NetherManager.computeCountByText(properties.getProperty("overlay2.top"), 0);
      this.count8 = NetherManager.computeCountByText(properties.getProperty("overlay2.bottom"), Integer.MIN_VALUE);
   }
}
