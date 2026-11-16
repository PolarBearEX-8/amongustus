package client.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MlgBlockCollector {
   public int count;
   public int count4;
   public int count2;
   public final ArrayList arrayList = new ArrayList();
   public final int count3;
   public ResourceLocation resourceLocation2;
   public int count5;
   public ResourceLocation resourceLocation;
   public long timestamp;
   public final String labelText;

   public String getText() {
      return this.labelText;
   }

   private void updateState2() {
      this.count2 = -1;
      this.timestamp = 0L;
   }

   public ResourceLocation getResourceLocation() {
      int count6 = this.count3;
      return this.resolveResourceLocationByIndex(count6);
   }

   private void updateState() {
      this.count2++;
      if (this.count2 >= this.count5) {
         this.count2 = -1;
         this.timestamp = 0L;
      }
   }

   public int getInt() {
      return this.count3 * this.count5;
   }

   public int currentTimeMillis() {
      return (int)(System.currentTimeMillis() - this.timestamp);
   }

   public int getIndex() {
      return this.count;
   }

   private ResourceLocation resolveResourceLocationByIndex(int count6) {
      long timestamp2 = System.currentTimeMillis();
      if (this.timestamp == 0L) {
         if (this.resourceLocation2 != null && Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getMcSoundHandler() != null) {
            SoundPlayer.processAction(this.resourceLocation2);
         }

         this.timestamp = timestamp2;
         this.updateState();
         this.resourceLocation = (ResourceLocation)this.arrayList.get(this.count2);
      } else if (timestamp2 - this.timestamp > (long)count6) {
         this.timestamp = timestamp2;
         this.resourceLocation = (ResourceLocation)this.arrayList.get(this.count2);
         this.updateState();
      }

      return this.resourceLocation;
   }

   public int getIndex2() {
      return this.count4;
   }

   public MlgBlockCollector(String text, int count6) {
      this.count2 = -1;
      this.timestamp = 0L;
      this.resourceLocation2 = null;
      count6 = !text.contains("yolo") && !text.contains("pussy")
         ? (text.contains("wow") ? 31 : (text.contains("snoop") ? 40 : (text.contains("sonic") ? 900 : count6)))
         : 36;
      this.count3 = count6;
      this.labelText = text;
      ArrayList arrayList2 = FileManager.getArrayList(text);
      Collections.sort(arrayList2);
      String text2 = (String)arrayList2.get(0);
      if (text2.endsWith(".ogg")) {
         text2 = (String)arrayList2.get(1);
      }

      try {
         BufferedImage bufferedImage = ImageIO.read(MlgBlockCollector.class.getClassLoader().getResourceAsStream("assets/augustus/" + text + "/" + text2));
         this.count4 = bufferedImage.getWidth();
         this.count = bufferedImage.getHeight();
      } catch (IOException ioException) {
         this.count4 = 0;
         this.count = 0;
      }

      for (String text3 : (Iterable<String>)(Iterable)(arrayList2)) {
         if (text3.endsWith(".ogg")) {
            String text4 = "mlg/gif/" + text.substring(9) + "/";
            String text5 = "sounds/" + text4;
            ArrayList arrayList3 = FileManager.getArrayList(text5);
            if (!arrayList3.isEmpty() && Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getMcSoundHandler() != null) {
               String text6 = (String)arrayList3.get(0);
               this.resourceLocation2 = new ResourceLocation("augustus", text5 + text6);
               Minecraft.getMinecraft().getMcSoundHandler().applyResourceLocation2(this.resourceLocation2, text4 + text6.substring(0, text6.length() - 4));
            }
         } else {
            this.arrayList.add(new ResourceLocation("augustus", text + "/" + text3));
         }
      }

      this.count5 = this.arrayList.size();
   }
}
