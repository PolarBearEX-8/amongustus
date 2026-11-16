package client.gui;

import client.event.PostUpdateProcessor;
import client.util.MovementHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ClientScreen extends GuiScreen {
   public boolean enabled6;
   public GuiScreen guiScreen2;
   public final Map map = new HashMap();
   public final List list5;
   public final List list3 = new ArrayList();
   public int count2;

   public void setGuiScreen2(GuiScreen guiScreen) {
      this.guiScreen2 = guiScreen;
   }

   public void applyText(String text, float posX, float posY, float posZ, int count) {
      GL11.glPushMatrix();
      GL11.glScalef(posZ, posZ, posZ);
      Minecraft.getMinecraft().fontRendererObj.getIndex2(text, posX, posY, count);
      GL11.glPopMatrix();
   }

   public TexturesGuiIconsPngManagerAdapter resolveTexturesGuiIconsPngManagerAd(int count, int count3, int count4, int count5) {
      int count6 = this.count2;
      TexturesGuiIconsPngManagerAdapter texturesGuiIconsPngManagerAdapter = new TexturesGuiIconsPngManagerAdapter(count6, Minecraft.getMinecraft().fontRendererObj, count, count3, count4, count5);
      texturesGuiIconsPngManagerAdapter.applyIndex3(1337);
      this.count2++;
      this.list5.add(texturesGuiIconsPngManagerAdapter);
      return texturesGuiIconsPngManagerAdapter;
   }

   @Override
   public final void updateState2() {
      super.updateState2();
      this.map.clear();
      super.list.clear();
      this.list3.clear();
      this.list5.clear();
      if (this.getGuiScreen2() != null) {
         int count = super.height - 26;
         PostUpdateProcessor postUpdateProcessor = this::applyAuxiliaryTexturesGuiIconsPByAuxiliaryTexture;
         String text = "<-";
         byte byteValue = 20;
         byte byteValue2 = 20;
         int count3 = count;
         byte byteValue3 = 5;
         this.resolveAuxiliaryTexturesGuiIconsPng(byteValue3, count3, byteValue2, byteValue, text, postUpdateProcessor);
      }

      this.renderElement();
   }

   public AuxiliaryTexturesGuiIconsPngManagerAdapter resolveAuxiliaryTexturesGuiIconsPng(
      int count, int count3, int count4, int count5, String text, PostUpdateProcessor postUpdateProcessor
   ) {
      int count6 = this.count2;
      AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(count6, count, count3, count4, count5, text);
      super.list.add(auxiliaryTexturesGuiIconsPngManagerAdapter);
      this.map.put(count6, postUpdateProcessor);
      this.count2++;
      return auxiliaryTexturesGuiIconsPngManagerAdapter;
   }

   public int getCount4() {
      return super.height / 2;
   }

   @Override
   public void applyMinecraft(Minecraft mc, int count, int count3) {
      super.applyMinecraft(mc, count, count3);
   }

   public void applyTextByText(String text, float factor, float factor2, int count) {
      Minecraft.getMinecraft().fontRendererObj.getIndex2(text, factor, factor2, count);
   }

   public void applyIndexByIndex(int count, int count3, float factor) {
   }

   public void applyText(String text, float factor, float factor2) {
      this.applyText(text, factor, factor2, -1);
   }

   public int getCount3() {
      return super.width / 2;
   }

   public void renderElement5() {
      this.enabled6 = false;
   }

   public void applyText(String text, float factor, float factor2, int count) {
      Minecraft.getMinecraft().fontRendererObj.getIndex2(text, factor - (float)Minecraft.getMinecraft().fontRendererObj.getIndex(text) / 2.0F, factor2, count);
   }

   public void applyText(String text, float posX, float posY, float posZ) {
      long value = 0x693ffaf52652L;
      float factor = posX - (float)Minecraft.getMinecraft().fontRendererObj.getIndex(text) / 2.0F;
      this.applyText(text, factor, posY, posZ, -1);
   }

   public void applyTextByText(String text, float posX, float posY, float posZ) {
      this.applyText(text, posX, posY, posZ, -1);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      super.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);

      try {
         ((PostUpdateProcessor)this.map.get(auxiliaryTexturesGuiIconsPngManagerAdapter.count2)).applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
      } catch (Exception exception) {
      }
   }

   public int computeCount(String text) {
      return Minecraft.getMinecraft().fontRendererObj.getIndex(text);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count == 1 && this.getGuiScreen2() != null) {
         this.renderElement6();
      } else {
         super.applyChar(character, count);
         Iterator iterator = this.list3.iterator();

         while (iterator.hasNext()) {
            ((InternalTexturesGuiIconsPngManagerAdapter)iterator.next()).checkChar(character, count);
         }

         Iterator iterator2 = this.list5.iterator();

         while (iterator2.hasNext()) {
            ((TexturesGuiIconsPngManagerAdapter)iterator2.next()).checkChar(character, count);
         }
      }
   }

   private void applyAuxiliaryTexturesGuiIconsPByAuxiliaryTexture(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.renderElement6();
   }

   @Override
   public void updateState4() {
      super.updateState4();
      Iterator iterator = this.list3.iterator();

      while (iterator.hasNext()) {
         ((InternalTexturesGuiIconsPngManagerAdapter)iterator.next()).renderElement();
      }

      Iterator iterator2 = this.list5.iterator();

      while (iterator2.hasNext()) {
         ((TexturesGuiIconsPngManagerAdapter)iterator2.next()).renderElement4();
      }
   }

   public void applyGuiScreenByGuiScreen(GuiScreen screen, char character, char character2, int count) {
      Minecraft.getMinecraft().displayGuiScreen(screen);
   }

   public GuiScreen getGuiScreen2() {
      return this.guiScreen2;
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      if (this.enabled6) {
         this.renderElement3();
         MovementHandler.applyScaledResolution(new ScaledResolution(super.mc), true);
      }

      Iterator iterator = this.list3.iterator();

      while (iterator.hasNext()) {
         ((InternalTexturesGuiIconsPngManagerAdapter)iterator.next()).updateState();
      }

      Iterator iterator2 = this.list5.iterator();

      while (iterator2.hasNext()) {
         ((TexturesGuiIconsPngManagerAdapter)iterator2.next()).renderElement3();
      }

      this.applyIndexByIndex(count, count3, factor);
      super.applyIndex(count, count3, factor);
   }

   @Override
   public void processAction(int count, int count3, int count4) throws IOException {
      super.processAction(count, count3, count4);
      Iterator iterator = this.list3.iterator();

      while (iterator.hasNext()) {
         ((InternalTexturesGuiIconsPngManagerAdapter)iterator.next()).applyIndex(count, count3, count4);
      }

      Iterator iterator2 = this.list5.iterator();

      while (iterator2.hasNext()) {
         ((TexturesGuiIconsPngManagerAdapter)iterator2.next()).applyIndex(count, count3, count4);
      }
   }

   public static void applyIndex2(int count, int count3, int count4, int count5) {
      int count6 = count4 - count;
      int count7 = count5 - count3;
      ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
      int scaleFactor = scaledResolution.getScaleFactor();
      int count8 = scaledResolution.getScaledHeight() - count5;
      GL11.glScissor(count * scaleFactor, count8 * scaleFactor, count6 * scaleFactor, count7 * scaleFactor);
      GL11.glEnable(3089);
   }

   public void applyTextByText(String text, float factor, float factor2) {
      this.applyTextByText(text, factor, factor2, -1);
   }

   public void renderElement() {
   }

   public void applyListByList(List list, int count, int count3) {
      this.applyList(list, count + 5, count3 + 5);
   }

   public void renderElement6() {
      Minecraft mc = super.mc;
      GuiScreen guiScreen = this.getGuiScreen2();
      mc.displayGuiScreen(guiScreen);
   }

   public InternalTexturesGuiIconsPngManagerAdapter resolveInternalTexturesGuiIconsPngM(int count, int count3, int count4, int count5) {
      int count6 = this.count2;
      InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(
         count6, Minecraft.getMinecraft().fontRendererObj, count, count3, count4, count5
      );
      internalTexturesGuiIconsPngManagerAdapter.applyIndex3(1337);
      this.count2++;
      this.list3.add(internalTexturesGuiIconsPngManagerAdapter);
      return internalTexturesGuiIconsPngManagerAdapter;
   }

   public static void glDisable() {
      GL11.glDisable(3089);
   }

   public ClientScreen() {
      this.list5 = new ArrayList();
      this.count2 = 0;
      this.enabled6 = true;
   }

   public ClientScreen(GuiScreen screen) {
      super(screen);
      this.list5 = new ArrayList();
      this.count2 = 0;
      this.enabled6 = true;
   }
}
