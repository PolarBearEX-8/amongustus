package client.util;

import client.event.PreUpdateDispatcher;
import client.event.RayTraceValidatorPreMotionObserver;
import client.gui.DefaultGuiScreenAdapter;
import client.gui.TexturesGuiIconsPngManager;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public abstract class RayTraceValidator implements PreUpdateDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/gui/resource_packs.png");
   public static final IChatComponent iChatComponent = new BaseManagerStyleIChatComponentAdapter("resourcePack.incompatible", new Object[0]);
   public static final IChatComponent iChatComponent2 = new BaseManagerStyleIChatComponentAdapter("resourcePack.incompatible.old", new Object[0]);
   public static final IChatComponent iChatComponent3 = new BaseManagerStyleIChatComponentAdapter("resourcePack.incompatible.new", new Object[0]);
   public final Minecraft mc;
   public final GeometryPool geometryPool;

   public RayTraceValidator(GeometryPool geometryPool2) {
      this.geometryPool = geometryPool2;
      this.mc = Minecraft.getMinecraft();
   }

   public boolean isActive4() {
      List list = this.geometryPool.resolveList(this);
      int count = list.indexOf(this);
      return count > 0 && ((RayTraceValidator)list.get(count - 1)).isActive3();
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }

   public abstract String getText();

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      int count8 = this.getCount();
      if (count8 != 1) {
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         TexturesGuiIconsPngManager.processAction(count2 - 1, count3 - 1, count2 + count4 - 9, count3 + count5 + 1, -8978432);
      }

      this.updateState();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      TexturesGuiIconsPngManager.applyIndex(count2, count3, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      String text = this.getText();
      String textAsText = this.getTextAsText();
      if ((this.mc.gameSettings.state48 || flag) && this.isActive3()) {
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation);
         TexturesGuiIconsPngManager.processAction(count2, count3, count2 + 32, count3 + 32, -1601138544);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         int count9 = count6 - count2;
         int count10 = count7 - count3;
         if (count8 < 1) {
            text = iChatComponent.getString2();
            textAsText = iChatComponent2.getString2();
         } else if (count8 > 1) {
            text = iChatComponent.getString2();
            textAsText = iChatComponent3.getString2();
         }

         if (this.isActive2()) {
            if (count9 < 32) {
               TexturesGuiIconsPngManager.applyIndex(count2, count3, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               TexturesGuiIconsPngManager.applyIndex(count2, count3, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         } else {
            if (this.isActive()) {
               if (count9 < 16) {
                  TexturesGuiIconsPngManager.applyIndex(count2, count3, 32.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  TexturesGuiIconsPngManager.applyIndex(count2, count3, 32.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }

            if (this.isActive4()) {
               if (count9 < 32 && count9 > 16 && count10 < 16) {
                  TexturesGuiIconsPngManager.applyIndex(count2, count3, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  TexturesGuiIconsPngManager.applyIndex(count2, count3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }

            if (this.isActiveAsFlag()) {
               if (count9 < 32 && count9 > 16 && count10 > 16) {
                  TexturesGuiIconsPngManager.applyIndex(count2, count3, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  TexturesGuiIconsPngManager.applyIndex(count2, count3, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }
         }
      }

      if (this.mc.fontRendererObj.getIndex(text) > 157) {
         text = this.mc.fontRendererObj.resolveTextByText(text, 157 - this.mc.fontRendererObj.getIndex("...")) + "...";
      }

      this.mc.fontRendererObj.computeCount(text, (float)(count2 + 32 + 2), (float)(count3 + 1), 16777215);
      List list = this.mc.fontRendererObj.getList(textAsText, 157);

      for (int index = 0; index < 2 && index < list.size(); index++) {
         this.mc.fontRendererObj.computeCount((String)list.get(index), (float)(count2 + 32 + 2), (float)(count3 + 12 + 10 * index), 8421504);
      }
   }

   public abstract int getCount();

   public abstract String getTextAsText();

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   public boolean isActiveAsFlag() {
      List list = this.geometryPool.resolveList(this);
      int count = list.indexOf(this);
      return count >= 0 && count < list.size() - 1 && ((RayTraceValidator)list.get(count + 1)).isActive3();
   }

   public abstract void updateState();

   public boolean isActive() {
      return this.geometryPool.matchesRayTraceValidator(this);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.isActive3() && count5 <= 32) {
         if (this.isActive2()) {
            this.geometryPool.updateState();
            int count7 = this.getCount();
            if (count7 != 1) {
               String text = StatCollector.getText("resourcePack.incompatible.confirm.title", new Object[0]);
               String text2 = StatCollector.getText("resourcePack.incompatible.confirm." + (count7 > 1 ? "new" : "old"), new Object[0]);
               Minecraft mc2 = this.mc;
               DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(new RayTraceValidatorPreMotionObserver(this), text, text2, 0);
               mc2.displayGuiScreen(defaultGuiScreenAdapter);
            } else {
               this.geometryPool.resolveList(this).remove(this);
               this.geometryPool.getList().add(0, this);
            }

            return true;
         }

         if (count5 < 16 && this.isActive()) {
            this.geometryPool.resolveList(this).remove(this);
            this.geometryPool.getListAsList().add(0, this);
            this.geometryPool.updateState();
            return true;
         }

         if (count5 > 16 && count6 < 16 && this.isActive4()) {
            List list = this.geometryPool.resolveList(this);
            int count8 = list.indexOf(this);
            list.remove(this);
            list.add(count8 - 1, this);
            this.geometryPool.updateState();
            return true;
         }

         if (count5 > 16 && count6 > 16 && this.isActiveAsFlag()) {
            List list2 = this.geometryPool.resolveList(this);
            int count9 = list2.indexOf(this);
            list2.remove(this);
            list2.add(count9 + 1, this);
            this.geometryPool.updateState();
            return true;
         }
      }

      return false;
   }

   public boolean isActive2() {
      return !this.geometryPool.matchesRayTraceValidator(this);
   }

   public boolean isActive3() {
      return true;
   }
}
