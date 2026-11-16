package client.gui;

import augustus.modules.misc.ClientSettings;
import client.event.PostUpdateProcessor;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ClientSettingsBlackType;
import client.util.MovementHandler;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;

public class InternalClientScreenAdapter extends ClientScreen {
   public final List list6;
   public static final int count10 = 102;
   public static final int count5 = 20;
   public int count7 = 0;
   public static final int count6 = 4;
   public static final int count9 = 52;

   @Override
   public void enableRepeatEvents() {
      super.enableRepeatEvents();
      if (this.getAuxiliaryClientScreenAdapter() != null) {
         this.getAuxiliaryClientScreenAdapter().enableRepeatEvents();
      }
   }

   @Override
   public void applyMinecraft(Minecraft mc, int count, int count2) {
      super.applyMinecraft(mc, count, count2);
      if (this.getAuxiliaryClientScreenAdapter() != null) {
         this.getAuxiliaryClientScreenAdapter().applyMinecraft(mc, count, count2);
      }
   }

   public boolean matchesAuxiliaryClientScreenAdapt(AuxiliaryClientScreenAdapter auxiliaryClientScreenAdapter) {
      return false;
   }

   public AuxiliaryClientScreenAdapter getAuxiliaryClientScreenAdapter() {
      return (AuxiliaryClientScreenAdapter)this.getList6().get(this.count7);
   }

   @Override
   public void applyIndexByIndex(int count, int count2, float factor) {
      MovementHandler.processAction6();
      MovementHandler.processAction8();
      MovementHandler.applyRunnableTask(false, this::applyRenderPass);
      GL11.glPushMatrix();
      GL11.glTranslatef(51.0F, 0.0F, 0.0F);
      String text = ClientSettingsBlackType.RED + this.getAuxiliaryClientScreenAdapter().getLabelText2();
      float factor2 = (float)super.width / 4.0F;
      float factor3 = 2.0F;
      float factor4 = 8.0F;
      float factor5 = factor2;
      String text2 = text;
      this.applyText(text2, factor5, factor4, factor3);
      GL11.glPopMatrix();
      ClientSettings.processAction();
      ClientSettings.setActive4(true);
      this.getAuxiliaryClientScreenAdapter().renderElement5();
      this.getAuxiliaryClientScreenAdapter().applyIndex(count, count2, factor);
   }

   @Override
   public void renderElement2() throws IOException {
      super.renderElement2();
      if (this.getAuxiliaryClientScreenAdapter() != null) {
         this.getAuxiliaryClientScreenAdapter().renderElement2();
      }
   }

   @Override
   public void updateState4() {
      super.updateState4();
      if (this.getAuxiliaryClientScreenAdapter() != null) {
         this.getAuxiliaryClientScreenAdapter().updateState4();
      }
   }

   private void applyAuxiliaryClientScreenAdapt(AuxiliaryClientScreenAdapter auxiliaryClientScreenAdapter, AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.count7 = this.getList6().indexOf(auxiliaryClientScreenAdapter);
      AuxiliaryClientScreenAdapter auxiliaryClientScreenAdapter2 = this.getAuxiliaryClientScreenAdapter();
      int height = super.height;
      int width = super.width;
      Minecraft mc = super.mc;
      auxiliaryClientScreenAdapter2.applyMinecraft(mc, width, height);
   }

   private void applyRenderPass(RenderPass renderPass) {
      RenderUtils.processAction(0, 50, 100, super.height, Integer.MIN_VALUE);
      RenderUtils.processAction(0, 0, super.width, 50, Integer.MIN_VALUE);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      if (this.getAuxiliaryClientScreenAdapter() != null) {
         this.getAuxiliaryClientScreenAdapter().updateState3();
      }
   }

   public List getList6() {
      return this.list6;
   }

   @Override
   public void renderElement() {
      super.renderElement();
      GuiScreen.set.add(this);
      int count = 0;

      for (AuxiliaryClientScreenAdapter auxiliaryClientScreenAdapter : (Iterable<AuxiliaryClientScreenAdapter>)(Iterable<?>)this.getList6()) {
         int count2 = 52 + count * 24;
         String labelText = auxiliaryClientScreenAdapter.getLabelText2();
         PostUpdateProcessor postUpdateProcessor = button -> this.applyAuxiliaryClientScreenAdapt(auxiliaryClientScreenAdapter, button);
         String text = labelText;
         byte byteValue = 20;
         byte byteValue2 = 90;
         int count3 = count2;
         byte byteValue3 = 5;
         this.resolveAuxiliaryTexturesGuiIconsPng(byteValue3, count3, byteValue2, byteValue, text, postUpdateProcessor);
         count++;
      }

      this.getAuxiliaryClientScreenAdapter().applyMinecraft2(Minecraft.getMinecraft(), super.width, super.height);
   }

   private static void applyGuiScreen(GuiScreen screen, AuxiliaryClientScreenAdapter auxiliaryClientScreenAdapter) {
      auxiliaryClientScreenAdapter.setGuiScreen2(screen);
   }

   public InternalClientScreenAdapter(List list, GuiScreen screen, int count) {
      super(screen);
      this.count7 = count;
      this.setGuiScreen2(screen);
      this.list6 = list;
      list.forEach(item -> InternalClientScreenAdapter.applyGuiScreen(screen, (AuxiliaryClientScreenAdapter)item));

      for (AuxiliaryClientScreenAdapter auxiliaryClientScreenAdapter : (Iterable<AuxiliaryClientScreenAdapter>)(Iterable<?>)this.list6) {
         if (this.matchesAuxiliaryClientScreenAdapt(auxiliaryClientScreenAdapter)) {
            list.remove(auxiliaryClientScreenAdapter);
         }
      }
   }
}
