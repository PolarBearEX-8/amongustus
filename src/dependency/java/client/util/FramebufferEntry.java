package client.util;

import client.event.PostRenderFilter;
import client.render.StencilFramebuffer;
import client.setting.SimpleOptionType;
import net.minecraft.client.shader.Framebuffer;

public class FramebufferEntry implements PostRenderFilter, MinecraftAccessor {
   public final Framebuffer framebuffer2 = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, true);
   public final IntListEntry intListEntry;
   public final Framebuffer framebuffer = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, true);

   public void applyFramebufferByFramebuffer(float factor, Framebuffer framebuffer3, Framebuffer framebuffer4, SimpleOptionType simpleOptionType) {
      framebuffer3.setState(true);
      this.intListEntry.updateState2();
      String text = "power";
      this.intListEntry.applyText(text, factor);
      String text2 = "tex";
      this.intListEntry.applyText((char)0, text2, 1781016089, 50324, framebuffer4);
      float coordX = (float)simpleOptionType.x;
      float coordY = (float)simpleOptionType.y;
      float factor2 = coordX;
      String text3 = "direction";
      this.intListEntry.applyText(text3, factor2, coordY);
      coordX = (float)framebuffer3.count4;
      float count4 = (float)framebuffer3.count5;
      float factor3 = coordX;
      String text4 = "resolution";
      this.intListEntry.applyText(text4, factor3, count4);
      coordX = (float)framebuffer3.count4;
      float count5 = (float)framebuffer3.count5;
      float factor4 = coordX;
      float factor5 = 0.0F;
      float factor6 = 0.0F;
      this.intListEntry.applyFloat(factor6, factor5, factor4, count5);
      this.intListEntry.updateState();
   }

   @Override
   public Framebuffer resolveFramebuffer(Framebuffer framebuffer3, int count, int count2) {
      long value = 11133L;
      int count3 = -509083719;
      if (this.intListEntry == null) {
         return this.framebuffer2;
      } else {
         float factor = (float)count;
         SimpleOptionType simpleOptionType = SimpleOptionType.HORIZONTAL;
         Framebuffer framebuffer4 = this.framebuffer;
         float factor2 = factor;
         this.applyFramebufferByFramebuffer(factor2, framebuffer4, framebuffer3, simpleOptionType);
         factor = (float)count;
         SimpleOptionType simpleOptionType2 = SimpleOptionType.VERTICAL;
         Framebuffer framebuffer5 = this.framebuffer;
         Framebuffer framebuffer6 = this.framebuffer2;
         float factor3 = factor;
         this.applyFramebufferByFramebuffer(factor3, framebuffer6, framebuffer5, simpleOptionType2);
         return this.framebuffer2;
      }
   }

   public FramebufferEntry(IntListEntry intListEntry2) {
      this.intListEntry = intListEntry2;
   }
}
