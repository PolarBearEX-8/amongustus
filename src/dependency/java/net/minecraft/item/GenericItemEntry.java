package net.minecraft.item;

import client.event.PostRenderFilter;
import client.render.StencilFramebuffer;
import client.util.IntListEntry;
import client.util.MinecraftAccessor;
import client.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.shader.Framebuffer;

public class GenericItemEntry implements MinecraftAccessor, PostRenderFilter {
   public final IntListEntry intListEntry;
   public final IntListEntry intListEntry2;
   public final Framebuffer framebuffer2;
   public final Map map = new HashMap();
   public final Framebuffer framebuffer = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, true);

   @Override
   public Framebuffer resolveFramebuffer(Framebuffer framebuffer3, int count, int count2) {
      int count3 = count;
      if (count > 20) {
         count3 = 20;
      }

      Framebuffer framebuffer4 = null;
      float factor = 1.0F;
      int count4 = 0;

      for (Pair pair : (Pair[])this.map.get(count3 - 1)) {
         Framebuffer framebuffer5 = null;
         Framebuffer framebuffer6 = null;
         if (count4 == 0) {
            framebuffer5 = framebuffer3;
            framebuffer6 = this.framebuffer;
         } else if (count4 % 2 != 0) {
            framebuffer5 = this.framebuffer;
            framebuffer6 = this.framebuffer2;
         } else if (count4 % 2 == 0) {
            framebuffer5 = this.framebuffer2;
            framebuffer6 = this.framebuffer;
         }

         factor *= (Float)pair.getFirst();
         float first = (Float)pair.getFirst();
         float second = (Float)pair.getSecond();
         framebuffer4 = this.resolveFramebufferByFramebuffer(second, factor, first, framebuffer6, framebuffer5);
         count4++;
      }

      return framebuffer4;
   }

   public Framebuffer resolveFramebufferByFramebuffer(float posX, float posY, float posZ, Framebuffer framebuffer3, Framebuffer framebuffer4) {
      framebuffer3.setState(true);
      IntListEntry intListEntry3 = posZ > 1.0F ? this.intListEntry2 : this.intListEntry;
      intListEntry3.updateState2();
      String text = "offset";
      intListEntry3.applyText(text, posX);
      String text2 = "tex";
      intListEntry3.applyText((char)0, text2, 1781016089, 50324, framebuffer4);
      float factor = (float)framebuffer4.count4 * posZ;
      float factor2 = (float)framebuffer4.count5 * posZ;
      float factor3 = factor;
      String text3 = "resolution";
      intListEntry3.applyText(text3, factor3, factor2);
      float factor4 = posY == 1.0F ? (float)framebuffer4.count5 : (float)framebuffer4.count5 - (float)framebuffer4.count5 * posY;
      factor = (float)framebuffer4.count4 * posY;
      float factor5 = 0.0F;
      float factor6 = factor;
      float factor7 = 0.0F;
      intListEntry3.applyFloat(factor7, factor4, factor6, factor5);
      intListEntry3.updateState();
      return framebuffer3;
   }

   public GenericItemEntry(IntListEntry intListEntry3, IntListEntry intListEntry4) {
      this.framebuffer2 = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, true);
      this.intListEntry2 = intListEntry3;
      this.intListEntry = intListEntry4;
      ArrayList list = new ArrayList();
      list.add(new Pair(1, 1.25F));
      list.add(new Pair(1, 2.25F));
      list.add(new Pair(2, 2.0F));
      list.add(new Pair(2, 3.0F));
      list.add(new Pair(2, 4.25F));
      list.add(new Pair(3, 2.5F));
      list.add(new Pair(3, 3.25F));
      list.add(new Pair(3, 4.25F));
      list.add(new Pair(3, 5.5F));
      list.add(new Pair(4, 3.25F));
      list.add(new Pair(4, 4.0F));
      list.add(new Pair(4, 5.0F));
      list.add(new Pair(4, 6.0F));
      list.add(new Pair(4, 7.25F));
      list.add(new Pair(4, 8.25F));
      list.add(new Pair(5, 4.5F));
      list.add(new Pair(5, 5.25F));
      list.add(new Pair(5, 6.25F));
      list.add(new Pair(5, 7.25F));
      list.add(new Pair(5, 8.5F));

      for (int index = 0; index < list.size(); index++) {
         Pair pair = (Pair)list.get(index);
         Pair[] pairArray = new Pair[(Integer)pair.getSecond() * 2];

         for (int index2 = 0; index2 < pairArray.length; index2++) {
            pairArray[index2] = new Pair(pair.getFirst(), index2 < pairArray.length / 2 ? 0.5F : 2.0F);
         }

         if (pairArray.length % 2 != 0) {
            int count = pairArray.length - 1;
            pairArray[count] = new Pair(pairArray[count].getSecond(), 1.0F);
         }

         this.map.put(index, pairArray);
      }
   }
}
