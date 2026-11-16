package client.util;

import client.Client;
import client.config.ClientNetworkConfig;
import client.event.PostUpdateProcessor;
import client.gui.AuxiliaryClientScreenAdapter;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManagerAdapter;
import client.render.RenderUtils;
import client.setting.CombatSpeedProperty;
import java.awt.Color;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ColorEntry extends AuxiliaryClientScreenAdapter {
   public Color color;
   public static final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
   public CompletableFuture completableFuture;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public static ClientNetworkConfig clientNetworkConfig3 = null;
   public static final AltDoubleEntry altDoubleEntry = new AltDoubleEntry(32);
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter2;
   public ClientNetworkConfig clientNetworkConfig2;
   public TexturesGuiIconsPngManagerAdapter texturesGuiIconsPngManagerAdapter;
   public static final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
   public CombatSpeedProperty combatSpeedProperty = CombatSpeedProperty.SOCKS5;
   public ClientNetworkConfig clientNetworkConfig;
   public boolean enabled8;
   public boolean enabled7;
   public String labelText3 = "Waiting...";

   private void applyAuxiliaryTexturesGuiIconsP7(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (this.clientNetworkConfig2 != null) {
         copyOnWriteArrayList.remove(this.clientNetworkConfig2);
         concurrentHashMap.remove(this.clientNetworkConfig2);
         AltDoubleEntry altDoubleEntry2 = altDoubleEntry;
         int size = copyOnWriteArrayList.size();
         altDoubleEntry2.setCount4(size);
         this.clientNetworkConfig2 = null;
         MsnManager.updateState3();
      } else {
         MsnManager.updateState4();
      }
   }

   private void applyAuxiliaryTexturesGuiIconsP4(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.updateState6();
   }

   private Pair getPair() {
      boolean enabled = this.enabled7;
      ClientNetworkConfig clientNetworkConfig4 = this.clientNetworkConfig;
      return ReachProxyHelper.resolvePair(clientNetworkConfig3, clientNetworkConfig4, enabled);
   }

   private void applyText(String text, Color color2) {
      this.labelText3 = text;
      this.color = color2;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      double offset = (double)count2;
      altDoubleEntry.applyDouble(offset);
   }

   private void applyAuxiliaryTexturesGuiIconsP3(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.updateState5();
   }

   public static Integer resolveInteger(String text) {
      try {
         return Integer.parseInt(text);
      } catch (Exception exception) {
         return -1;
      }
   }

   @Override
   public void applyIndexByIndex(int count, int count2, float factor) {
      super.applyIndexByIndex(count, count2, factor);
      ScaledResolution scaledResolution = new ScaledResolution(super.mc);
      String labelText = this.labelText3;
      float factor2 = (float)(102 + super.width) / 2.0F;
      int rgb = this.color.getRGB();
      float factor3 = 38.0F;
      float factor4 = factor2;
      String text = labelText;
      this.applyText(text, factor4, factor3, rgb);
      int count3 = (int)Math.max(100.0F, Math.min((float)scaledResolution.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      GL11.glPushMatrix();
      GL11.glEnable(3089);
      RenderUtils.drawBorderedRect(109.0F, 60.0F, (float)(102 + count3 - 2), 145.0F);
      if (this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty() && !this.internalTexturesGuiIconsPngManagerAdapter.isEnabled2()) {
         int rgb2 = Color.gray.getRGB();
         float factor5 = 60.0F;
         float factor6 = 109.0F;
         String text2 = "IP-Adress:Port";
         this.applyTextByText(text2, factor6, factor5, rgb2);
      }

      if (this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText().isEmpty() && !this.internalTexturesGuiIconsPngManagerAdapter2.isEnabled2()) {
         int rgb3 = Color.gray.getRGB();
         float factor7 = 110.0F;
         float factor8 = 109.0F;
         String text3 = "Username";
         this.applyTextByText(text3, factor8, factor7, rgb3);
      }

      if (this.texturesGuiIconsPngManagerAdapter.getLabelText2().isEmpty()
         && !this.texturesGuiIconsPngManagerAdapter.isEnabled5()
         && this.combatSpeedProperty != CombatSpeedProperty.SOCKS4) {
         int rgb4 = Color.gray.getRGB();
         float factor9 = 135.0F;
         float factor10 = 109.0F;
         String text4 = "Password";
         this.applyTextByText(text4, factor10, factor9, rgb4);
      }

      GL11.glDisable(3089);
      GL11.glPopMatrix();
      int count4 = Math.min(scaledResolution.getScaledWidth() - 102 - count3 - 20, 300);
      int count5 = scaledResolution.getScaledWidth() - 10 - count4;
      int value = (int)altDoubleEntry.getValue();
      GL11.glEnable(3089);
      RenderUtils.drawBorderedRect(102.0F, 52.0F, (float)super.width, (float)super.height);

      for (Object clientNetworkConfig4_o : copyOnWriteArrayList) {
         ClientNetworkConfig clientNetworkConfig4 = (ClientNetworkConfig)clientNetworkConfig4_o;
         InterpolationEngine interpolationEngine = (InterpolationEngine)concurrentHashMap.get(clientNetworkConfig4);
         if (interpolationEngine != null && interpolationEngine.isActive()) {
            int count6 = 57 + value;
            boolean flag = clientNetworkConfig4 == this.clientNetworkConfig2;
            byte byteValue = 30;
            int count7 = count6;
            interpolationEngine.applyIndex(count, count2, count5, count7, count4, byteValue, flag);
            value += 32;
         }
      }

      for (Object clientNetworkConfig5_o : copyOnWriteArrayList) {
         ClientNetworkConfig clientNetworkConfig5 = (ClientNetworkConfig)clientNetworkConfig5_o;
         InterpolationEngine interpolationEngine2 = (InterpolationEngine)concurrentHashMap.get(clientNetworkConfig5);
         if (interpolationEngine2 != null && !interpolationEngine2.isActive()) {
            int count8 = 57 + value;
            boolean flag2 = clientNetworkConfig5 == this.clientNetworkConfig2;
            byte byteValue2 = 30;
            int count9 = count8;
            interpolationEngine2.applyIndex(count, count2, count5, count9, count4, byteValue2, flag2);
            value += 32;
         }
      }

      GL11.glDisable(3089);
      altDoubleEntry.updateState();
      if (this.completableFuture != null && this.completableFuture.isDone()) {
         try {
            Pair pair = (Pair)this.completableFuture.get();
            labelText = (String)pair.getSecond();
            Color green = Color.green;
            Color color2 = Color.red.darker();
            double offset = Math.min(1.0, (double)((Integer)pair.getFirst()).intValue() / 1000.0);
            Color color3 = color2;
            Color color4 = new Color(RandomUtil.getIndex(green, color3, offset));
            String text5 = labelText;
            this.applyText(text5, color4);
            this.completableFuture = null;
            ClientNetworkConfig clientNetworkConfig6 = Client.getClient().getClientNetworkConfig();
            if (clientNetworkConfig6 != null && getClientNetworkConfig3() != null) {
               ClientNetworkConfig clientNetworkConfig7 = getClientNetworkConfig3();
               if (clientNetworkConfig6.matchesClientNetworkConfig(clientNetworkConfig7)) {
                  this.internalTexturesGuiIconsPngManagerAdapter.applyText(clientNetworkConfig6.getString3() + ":" + clientNetworkConfig6.getInt());
                  this.internalTexturesGuiIconsPngManagerAdapter2.applyText(clientNetworkConfig6.getText());
                  if (this.texturesGuiIconsPngManagerAdapter != null) {
                     TexturesGuiIconsPngManagerAdapter texturesGuiIconsPngManagerAdapter2 = this.texturesGuiIconsPngManagerAdapter;
                     String text6 = clientNetworkConfig6.getText2();
                     texturesGuiIconsPngManagerAdapter2.applyText(text6);
                  }
               }
            }
         } catch (ExecutionException | InterruptedException executionException) {
            throw new RuntimeException(executionException);
         }
      }
   }

   private void applyAuxiliaryTexturesGuiIconsP2(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.enabled7 = !this.enabled7;
      auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = "MC Check: " + (this.enabled7 ? "True" : "False");
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      if (this.clientNetworkConfig2 != null
         && (
            count == 211
               || count == 14
                  && !this.internalTexturesGuiIconsPngManagerAdapter.isEnabled2()
                  && !this.internalTexturesGuiIconsPngManagerAdapter2.isEnabled2()
                  && !this.texturesGuiIconsPngManagerAdapter.isEnabled5()
         )) {
         copyOnWriteArrayList.remove(this.clientNetworkConfig2);
         concurrentHashMap.remove(this.clientNetworkConfig2);
         this.clientNetworkConfig2 = null;
      }
   }

   public static synchronized void setClientNetworkConfig3(ClientNetworkConfig clientNetworkConfig4) {
      clientNetworkConfig3 = clientNetworkConfig4;
   }

   public void applyText2(String text, boolean flag) {
      if (text.isEmpty()) {
         Color color2 = Color.red.darker();
         String text2 = "IP is empty!";
         this.applyText(text2, color2);
      } else {
         String[] textArray = text.split(":");
         if (textArray.length >= 2 && textArray.length <= 4) {
            String[] textArray2 = new String[4];
            System.arraycopy(textArray, 0, textArray2, 0, textArray.length);
            if (!flag) {
               if (!this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText().isEmpty()) {
                  textArray2[2] = this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText();
               }

               if (!this.texturesGuiIconsPngManagerAdapter.getLabelText2().isEmpty()) {
                  textArray2[3] = this.texturesGuiIconsPngManagerAdapter.getLabelText2();
               }
            }

            int count = resolveInteger(textArray2[1]);
            if (count == -1) {
               Color color3 = Color.red.darker();
               String text3 = "Port is not numeric!";
               this.applyText(text3, color3);
            } else {
               String text4 = textArray2[0];
               String text5 = textArray2[2];
               String text6 = textArray2[3];
               clientNetworkConfig3 = new ClientNetworkConfig(text4, count, "", "", this.combatSpeedProperty);
               if (clientNetworkConfig3.getInetSocketAddress() == null) {
                  Color color4 = Color.red.darker();
                  String text7 = "No valid proxy format!";
                  this.applyText(text7, color4);
                  clientNetworkConfig3 = null;
               } else {
                  if (this.completableFuture == null) {
                     Color yellow = Color.yellow;
                     String text8 = "Pinging...";
                     this.applyText(text8, yellow);
                     this.clientNetworkConfig = Client.getClient().getClientNetworkConfig();
                     this.completableFuture = CompletableFuture.supplyAsync(this::getPair);
                  }
               }
            }
         } else {
            Color color5 = Color.red.darker();
            String text9 = "Wrong proxy format! [Ip:Port] / [Ip:Port:Username] / [Ip:Port:Username:Password]";
            this.applyText(text9, color5);
         }
      }
   }

   private void applyAuxiliaryTexturesGuiIconsP9(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      switch (BezierMonitor.countArray[this.combatSpeedProperty.ordinal()]) {
         case 1:
            this.combatSpeedProperty = CombatSpeedProperty.SOCKS5;
            break;
         case 2:
            this.combatSpeedProperty = CombatSpeedProperty.SOCKS4;
      }

      this.enabled8 = true;
      auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = "Type: " + this.combatSpeedProperty.getLabelText();
   }

   public static synchronized ClientNetworkConfig getClientNetworkConfig3() {
      return clientNetworkConfig3;
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      altDoubleEntry.updateState5();
   }

   private void applyAuxiliaryTexturesGuiIconsP6(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      String labelText = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
      boolean flag = false;
      String text = labelText;
      this.applyText2(text, flag);
   }

   private void applyAuxiliaryTexturesGuiIconsP8(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.applyAuxiliaryTexturesGuiIconsP9(auxiliaryTexturesGuiIconsPngManagerAdapter);
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      if (this.enabled8) {
         this.updateState2();
      }

      for (Object clientNetworkConfig4_o : copyOnWriteArrayList) {
         ClientNetworkConfig clientNetworkConfig4 = (ClientNetworkConfig)clientNetworkConfig4_o;
         InterpolationEngine interpolationEngine = (InterpolationEngine)concurrentHashMap.get(clientNetworkConfig4);
         interpolationEngine.applyColorEntry(count, count2, count3, this);
         if ((count3 == 1 || count3 == 0)
            && RenderUtils.checkCondition((float)interpolationEngine.getCount3(), (float)interpolationEngine.getCount4(), (float)interpolationEngine.getCount2(), (float)interpolationEngine.getCount(), count, count2)) {
            this.clientNetworkConfig2 = clientNetworkConfig4;
         }
      }

      double offset = (double)count;
      double offset2 = (double)count2;
      double offset3 = offset;
      altDoubleEntry.applyDouble(offset3, offset2);
   }

   @Override
   public void renderElement() {
      byte byteValue = 0;
      this.enabled8 = false;
      super.renderElement();
      ScaledResolution scaledResolution = new ScaledResolution(super.mc);
      int count = (int)Math.max(100.0F, Math.min((float)scaledResolution.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      int count2 = 1;
      this.internalTexturesGuiIconsPngManagerAdapter = this.resolveInternalTexturesGuiIconsPngM(104, 54, count, 20);
      String text = "Type: " + this.combatSpeedProperty.getLabelText();
      PostUpdateProcessor postUpdateProcessor = this::applyAuxiliaryTexturesGuiIconsP8;
      String text2 = text;
      byte byteValue2 = 20;
      byte byteValue3 = 79;
      byte byteValue4 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue4, byteValue3, count, byteValue2, text2, postUpdateProcessor);
      count2++;
      byte byteValue5 = 104;
      byte byteValue6 = 104;
      this.internalTexturesGuiIconsPngManagerAdapter2 = this.resolveInternalTexturesGuiIconsPngM(byteValue6, byteValue5, count, 20);
      count2++;
      if (this.combatSpeedProperty == CombatSpeedProperty.SOCKS5) {
         int count3 = 54 + 25 * count2;
         byte byteValue7 = 104;
         this.texturesGuiIconsPngManagerAdapter = this.resolveTexturesGuiIconsPngManagerAd(byteValue7, count3, count, 20);
         this.texturesGuiIconsPngManagerAdapter.setEnabled2(true);
         count2++;
      } else {
         this.texturesGuiIconsPngManagerAdapter.setEnabled2(false);
      }

      this.updateState7();
      int count4 = 54 + 25 * count2;
      PostUpdateProcessor postUpdateProcessor2 = this::applyAuxiliaryTexturesGuiIconsP6;
      String text3 = "Connect";
      byte byteValue8 = 20;
      int count5 = count4;
      byte byteValue9 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue9, count5, count, byteValue8, text3, postUpdateProcessor2);
      count4 = 54 + 25 * ++count2;
      PostUpdateProcessor postUpdateProcessor3 = this::applyAuxiliaryTexturesGuiIconsP5;
      String text4 = "Clear";
      byte byteValue10 = 20;
      int count6 = count4;
      byte byteValue11 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue11, count6, count, byteValue10, text4, postUpdateProcessor3);
      count4 = 54 + 25 * ++count2;
      PostUpdateProcessor postUpdateProcessor4 = this::applyAuxiliaryTexturesGuiIconsP4;
      String text5 = "Disconnect";
      byte byteValue12 = 20;
      int count7 = count4;
      byte byteValue13 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue13, count7, count, byteValue12, text5, postUpdateProcessor4);
      count4 = 54 + 25 * ++count2;
      PostUpdateProcessor postUpdateProcessor5 = this::applyAuxiliaryTexturesGuiIconsP3;
      String text6 = "Clipboard Login";
      byte byteValue14 = 20;
      int count8 = count4;
      byte byteValue15 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue15, count8, count, byteValue14, text6, postUpdateProcessor5);
      count4 = 54 + 25 * ++count2;
      text = "MC Check: " + (this.enabled7 ? "True" : "False");
      PostUpdateProcessor postUpdateProcessor6 = this::applyAuxiliaryTexturesGuiIconsP2;
      String text7 = text;
      byte byteValue16 = 20;
      int count9 = count4;
      byte byteValue17 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue17, count9, count, byteValue16, text7, postUpdateProcessor6);
      count4 = 54 + 25 * ++count2;
      PostUpdateProcessor postUpdateProcessor7 = this::applyAuxiliaryTexturesGuiIconsP7;
      String text8 = "Delete selected Proxy";
      byte byteValue18 = 20;
      int count10 = count4;
      byte byteValue19 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue19, count10, count, byteValue18, text8, postUpdateProcessor7);
      int count11 = super.width - 5;
      int height = super.height;
      int width = super.width;
      byte byteValue20 = 52;
      int count12 = count11;
      altDoubleEntry.applyIndex(count12, byteValue20, width, height);
      AltDoubleEntry altDoubleEntry2 = altDoubleEntry;
      int size = copyOnWriteArrayList.size();
      altDoubleEntry2.setCount4(size);
   }

   private void applyAuxiliaryTexturesGuiIconsP5(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.updateState();
   }

   private void updateState6() {
      Client.getClient().applyClientNetworkConfig((ClientNetworkConfig)null);
      Color green = Color.green;
      String text = "Waiting...";
      this.applyText(text, green);
   }

   private void updateState7() {
      if (clientNetworkConfig3 != null) {
         this.internalTexturesGuiIconsPngManagerAdapter.applyText(clientNetworkConfig3.getString3() + ":" + clientNetworkConfig3.getInt());
         this.internalTexturesGuiIconsPngManagerAdapter2.applyText(clientNetworkConfig3.getText());
         if (this.texturesGuiIconsPngManagerAdapter != null) {
            TexturesGuiIconsPngManagerAdapter texturesGuiIconsPngManagerAdapter2 = this.texturesGuiIconsPngManagerAdapter;
            String text = clientNetworkConfig3.getText2();
            texturesGuiIconsPngManagerAdapter2.applyText(text);
         }
      }
   }

   private void updateState() {
      this.internalTexturesGuiIconsPngManagerAdapter.applyText("");
      String text = "";
      this.texturesGuiIconsPngManagerAdapter.applyText(text);
      this.internalTexturesGuiIconsPngManagerAdapter2.applyText("");
      clientNetworkConfig3 = null;
   }

   private void updateState5() {
      String text = GuiScreen.getText().replace(" ", "");
      boolean flag = true;
      this.applyText2(text, flag);
   }

   public ColorEntry() {
      super("Proxy Manager");
      this.color = Color.green;
      this.enabled7 = false;
      this.clientNetworkConfig2 = null;
      this.clientNetworkConfig = null;
      this.completableFuture = null;
      this.enabled8 = false;
   }
}
