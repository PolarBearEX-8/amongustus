package client.util;

import client.ClientGlobals;
import client.render.AssetsRendersystemManager;
import client.render.StencilFramebuffer;
import client.setting.ManagedOptionType;
import client.setting.PassiveOptionType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class ClientTickEventSync {
   public final Minecraft mc = Minecraft.getMinecraft();
   public final Framebuffer framebuffer = new StencilFramebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
   public final IntListEntry intListEntry;
   public final Framebuffer framebuffer2 = new StencilFramebuffer(this.mc.displayWidth, this.mc.displayHeight, true);

   public void updateState() {
      this.mc.getFramebuffer().setState(true);
   }

   public void processBoolean(boolean flag, int count) {
      if (Display.isActive()) {
         Framebuffer framebuffer3 = flag ? this.framebuffer : this.framebuffer2;
         GL11.glPushMatrix();
         boolean flag2 = false;
         PassiveOptionType.CULL_FACE.setEnabled(flag2);
         boolean flag3 = true;
         PassiveOptionType.TEXTURE_2D.setEnabled(flag3);
         boolean flag4 = true;
         PassiveOptionType.BLEND.setEnabled(flag4);
         ManagedOptionType managedOptionType = ManagedOptionType.resolveManagedOptionType(ClientGlobals.moduleManager.blur.settingModesNew.getSelected());
         Framebuffer framebuffer4 = this.mc.getFramebuffer();
         Framebuffer framebuffer5 = managedOptionType.resolveFramebuffer(framebuffer4, count, -509083719);
         this.mc.getFramebuffer().setState(Minecraft.state3);
         this.intListEntry.updateState2();
         char character = 0;
         String text = "shapes";
         char character2 = character;
         this.intListEntry.applyText(character2, text, 1781016089, 50324, framebuffer3);
         char character3 = 0;
         String text2 = "tex";
         char character4 = character3;
         this.intListEntry.applyText(character4, text2, 1781016089, 50324, framebuffer5);
         float count6 = (float)framebuffer3.count4;
         float count7 = (float)framebuffer3.count5;
         float factor = count6;
         String text3 = "resolution";
         this.intListEntry.applyText(text3, factor, count7);
         count6 = (float)framebuffer3.count4;
         float count8 = (float)framebuffer3.count5;
         float factor2 = count6;
         float factor3 = 0.0F;
         float factor4 = 0.0F;
         this.intListEntry.applyFloat(factor4, factor3, factor2, count8);
         this.intListEntry.updateState();
         this.framebuffer.updateState();
         this.framebuffer2.updateState();
         this.mc.getFramebuffer().setState(Minecraft.state3);
         PassiveOptionType.CULL_FACE.updateState();
         PassiveOptionType.TEXTURE_2D.updateState();
         PassiveOptionType.BLEND.updateState();
         GL11.glPopMatrix();
      }
   }

   public void setActive(boolean flag, boolean flag2) {
      if (flag2) {
         this.framebuffer.setState(flag);
      } else {
         this.framebuffer2.setState(flag);
      }
   }

   public ClientTickEventSync() {
      this.intListEntry = new IntListEntry(AssetsRendersystemManager.resolveText("legacy.vert"), AssetsRendersystemManager.resolveText("cutout.frag"));
   }
}
