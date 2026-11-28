package net.augustus.ui;

import net.augustus.utils.render.BlurShadowManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class AugustusGuiScreen extends GuiScreen {
   private final List passwordFields;
   private boolean renderDefaultBackground;
   private final List textFields;
   private GuiScreen parentScreen;
   private int nextControlId;
   private final Map buttonClickHandlers = new HashMap();

   public static void disableScissor() {
      GL11.glDisable(3089);
   }

   public void navigateBack() {
      this.mc.displayGuiScreen(this.getParentScreen());
   }

   public void drawCenteredString(String str, float f, float f2) {
      this.drawCenteredString(str, f, f2, (byte)-1);
   }

   public void disableDefaultBackground() {
      this.renderDefaultBackground = false;
   }

   @Override
   public void onResize(Minecraft mc2, int n, int n2) {
      super.onResize(mc2, n, n2);
   }

   private void onBackButtonClicked(GuiButton button) {
      this.navigateBack();
   }

   public AugustusGuiScreen() {
      this.textFields = new ArrayList();
      this.passwordFields = new ArrayList();
      this.nextControlId = 0;
      this.renderDefaultBackground = true;
   }

   public AugustusGuiScreen(GuiScreen screen) {
      super(screen);
      this.textFields = new ArrayList();
      this.passwordFields = new ArrayList();
      this.nextControlId = 0;
      this.renderDefaultBackground = true;
   }

   public GuiButton addButton(int n, int n2, int n3, int n4, String str, GuiButtonClickHandler guiButtonClickHandler) {
      int nextControlId2 = this.nextControlId;
      GuiButton button = new GuiButton(nextControlId2, n, n2, n3, n4, str);
      this.buttonList.add(button);
      this.buttonClickHandlers.put(nextControlId2, guiButtonClickHandler);
      this.nextControlId++;
      return button;
   }

   public void initContent() {
   }

   public int getCenterY() {
      return this.height / 2;
   }

   public static void enableScissor(int n, int n2, int n3, int n4) {
      int n5 = n3 - n;
      int n6 = n4 - n2;
      ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
      int scaleFactor = sr.getScaleFactor();
      GL11.glScissor(n * scaleFactor, (sr.getScaledHeight() - n4) * scaleFactor, n5 * scaleFactor, n6 * scaleFactor);
      GL11.glEnable(3089);
   }

   public int getCenterX() {
      return this.width / 2;
   }

   public void drawTooltip(List list, int n, int n2) {
      this.drawHoveringText(list, n + 5, n2 + 5);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == 1 && this.getParentScreen() != null) {
         this.navigateBack();
      } else {
         super.keyTyped(typedChar, keyCode);

         for (GuiTextField guiTextField : (Iterable<GuiTextField>)(this.textFields)) {
            guiTextField.textboxKeyTyped(typedChar, keyCode);
         }

         for (GuiPasswordField guiPasswordField : (Iterable<GuiPasswordField>)(this.passwordFields)) {
            guiPasswordField.textboxKeyTyped(typedChar, keyCode);
         }
      }
   }

   public void drawCenteredString(String str, float f, float f2, int n) {
      Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(str, f - (float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(str) / 2.0F, f2, n);
   }

   public void drawString(String str, float f, float f2, int n) {
      Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(str, f, f2, n);
   }

   public void openScreen(GuiScreen screen) {
      Minecraft.getMinecraft().displayGuiScreen(screen);
   }

   public void drawString(String str, float f, float f2) {
      this.drawString(str, f, f2, (byte)-1);
   }

   public void setParentScreen(GuiScreen screen) {
      this.parentScreen = screen;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);

      for (GuiTextField guiTextField : (Iterable<GuiTextField>)(this.textFields)) {
         guiTextField.mouseClicked(mouseX, mouseY, mouseButton);
      }

      for (GuiPasswordField guiPasswordField : (Iterable<GuiPasswordField>)(this.passwordFields)) {
         guiPasswordField.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   public void drawCenteredScaledString(String str, float f, float f2, float f3) {
      this.drawScaledString(str, f - (float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(str) / 2.0F, f2, f3, (byte)-1);
   }

   public void drawScaledString(String str, float f, float f2, float f3, int n) {
      GL11.glPushMatrix();
      GL11.glScalef(f3, f3, f3);
      Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(str, f, f2, n);
      GL11.glPopMatrix();
   }

   public GuiScreen getParentScreen() {
      return this.parentScreen;
   }

   public void drawContent(int n, int n2, float f) {
   }

   @Override
   public void updateScreen() {
      super.updateScreen();

      for (GuiTextField guiTextField : (Iterable<GuiTextField>)(this.textFields)) {
         guiTextField.updateCursorCounter();
      }

      for (GuiPasswordField guiPasswordField : (Iterable<GuiPasswordField>)(this.passwordFields)) {
         guiPasswordField.updateCursorCounter();
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.renderDefaultBackground) {
         this.drawDefaultBackground();
         BlurShadowManager.flushRenderQueues(new ScaledResolution(this.mc), true);
      }

      for (GuiTextField guiTextField : (Iterable<GuiTextField>)(this.textFields)) {
         guiTextField.drawTextBox();
      }

      for (GuiPasswordField guiPasswordField : (Iterable<GuiPasswordField>)(this.passwordFields)) {
         guiPasswordField.drawTextBox();
      }

      this.drawContent(mouseX, mouseY, partialTicks);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public GuiPasswordField addPasswordField(int n, int n2, int n3, int n4) {
      int nextControlId2 = this.nextControlId;
      GuiPasswordField guiPasswordField = new GuiPasswordField(nextControlId2, Minecraft.getMinecraft().fontRendererObj, n, n2, n3, n4);
      guiPasswordField.setMaxStringLength((short)1337);
      this.nextControlId++;
      this.passwordFields.add(guiPasswordField);
      return guiPasswordField;
   }

   @Override
   public final void initGui() {
      super.initGui();
      this.buttonClickHandlers.clear();
      this.buttonList.clear();
      this.textFields.clear();
      this.passwordFields.clear();
      if (this.getParentScreen() != null) {
         int n = this.height - 26;
         GuiButtonClickHandler guiButtonClickHandler = button -> this.onBackButtonClicked(button);
         this.addButton((byte)5, n, (byte)20, (byte)20, "<-", guiButtonClickHandler);
      }

      this.initContent();
   }

   public void drawScaledString(String str, float f, float f2, float f3) {
      this.drawScaledString(str, f, f2, f3, (byte)-1);
   }

   public int getStringWidth(String str) {
      return Minecraft.getMinecraft().fontRendererObj.getStringWidth(str);
   }

   public GuiTextField addTextField(int n, int n2, int n3, int n4) {
      int nextControlId2 = this.nextControlId;
      GuiTextField guiTextField = new GuiTextField(nextControlId2, Minecraft.getMinecraft().fontRendererObj, n, n2, n3, n4);
      guiTextField.setMaxStringLength(1337);
      this.nextControlId++;
      this.textFields.add(guiTextField);
      return guiTextField;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      super.actionPerformed(button);

      try {
         ((GuiButtonClickHandler)this.buttonClickHandlers.get(button.id)).onClick(button);
      } catch (Exception e) {
      }
   }

}
