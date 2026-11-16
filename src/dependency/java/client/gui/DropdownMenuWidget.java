package client.gui;

import client.Client;
import client.event.PostUpdateProcessor;
import client.render.RenderUtils;
import client.util.AbruptManager;
import client.util.AccountProfile;
import client.util.AltDoubleEntry;
import client.util.CircumManager;
import client.util.ConfigManager;
import client.util.Listener60EntryAdapter;
import client.util.LoadingNBTDataManager;
import client.util.MsnManager;
import client.util.OnlineConfigsManager;
import client.util.Pair;
import client.util.SmoothPicker;
import client.util.SpecialLoggerEntry;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.lwjgl.opengl.GL11;

public class DropdownMenuWidget extends AuxiliaryClientScreenAdapter {
   public static final AltDoubleEntry altDoubleEntry = new AltDoubleEntry(42);
   public static List list7 = new ArrayList();
   public AccountProfile accountProfile;
   public static DropdownMenuWidget dropdownMenuWidget;
   public TexturesGuiIconsPngManagerAdapter texturesGuiIconsPngManagerAdapter;
   public final List list6;
   public static final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
   public final SmoothPicker smoothPicker;
   public CompletableFuture completableFuture = null;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;

   private void applyResourceLocation(int count, int count2, ResourceLocation location) {
      super.mc.getTextureManager().applyResourceLocation3(location);
      GlStateManager.disableBlend();
      TexturesGuiIconsPngManager.applyIndex(count, count2, 0.0F, 0.0F, 30, 30, 30.0F, 30.0F);
      GlStateManager.enableBlend();
   }

   private int computeCount(String text, int count, int count2, int count3, int count4, int count5) {
      super.fontRenderer
         .computeCountByText(
            text, (float)count + (float)(count3 - count) / 2.0F - (float)super.fontRenderer.getIndex(text) / 2.0F, (float)(count2 + 5 + count5), Color.white.getRGB()
         );
      return count5 + 11;
   }

   private static void applyTextArray(String[] textArray) {
      String text = textArray[0];
      String text2 = textArray[1];
      MsnManager.applyTextByText(text, text2);
   }

   private void applyAuxiliaryTexturesGuiIconsP4(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.runAsync();
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      double offset = (double)count2;
      altDoubleEntry.applyDouble(offset);
   }

   private void applyAuxiliaryTexturesGuiIconsP3(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.runAsync2();
   }

   @Override
   public void applyIndexByIndex(int count, int count2, float factor) {
      super.applyIndexByIndex(count, count2, factor);
      FontRenderer fontRenderer = super.fontRenderer;
      ScaledResolution scaledResolution = new ScaledResolution(super.mc);
      int count3 = scaledResolution.getScaledWidth() - 102;
      scaledResolution.getScaledHeight();
      float factor2 = (float)(102 + super.width) / 2.0F;
      int rgb = MsnManager.getColor().getRGB();
      float factor3 = 38.0F;
      float factor4 = factor2;
      String text = "";
      this.applyText(text, factor4, factor3, rgb);
      int count4 = (int)Math.max(118.0F, Math.min((float)scaledResolution.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      GL11.glPushMatrix();
      GL11.glEnable(3089);
      RenderUtils.drawBorderedRect(109.0F, 60.0F, (float)(102 + count4 - 2), 70.0F);
      if (this.internalTexturesGuiIconsPngManagerAdapter != null
         && this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty()
         && !this.internalTexturesGuiIconsPngManagerAdapter.isEnabled2()) {
         fontRenderer.getIndex2("Email / Email:Password", 109.0F, 60.0F, Color.gray.getRGB());
      }

      RenderUtils.drawBorderedRect(109.0F, 85.0F, (float)(102 + count4 - 2), 95.0F);
      if (this.texturesGuiIconsPngManagerAdapter != null
         && this.texturesGuiIconsPngManagerAdapter.getLabelText2().isEmpty()
         && !this.texturesGuiIconsPngManagerAdapter.isEnabled5()) {
         fontRenderer.getIndex2("Password", 109.0F, 85.0F, Color.gray.getRGB());
      }

      GL11.glDisable(3089);
      GL11.glPopMatrix();
      int count5 = Math.min(count3 - count4 - 20, 300);
      int count6 = scaledResolution.getScaledWidth() - 10 - count5;
      int value = (int)altDoubleEntry.getValue();
      GL11.glEnable(3089);
      RenderUtils.drawBorderedRect(102.0F, 52.0F, (float)(count6 + count5), (float)(super.height - 2));

      for (AccountProfile accountProfile2 : (Iterable<AccountProfile>)(Iterable<?>)copyOnWriteArrayList) {
         if (accountProfile2.isEnabled4()) {
            int count7 = 57 + value;
            boolean flag = accountProfile2 == this.accountProfile;
            List list = this.list6;
            boolean flag2 = flag;
            byte byteValue = 40;
            int count8 = count7;
            accountProfile2.applyList(count6, count8, count5, byteValue, count, count2, flag2, list);
            value += 42;
         }
      }

      for (AccountProfile accountProfile3 : (Iterable<AccountProfile>)(Iterable<?>)copyOnWriteArrayList) {
         if (!accountProfile3.isEnabled4()) {
            int count9 = 57 + value;
            boolean flag3 = accountProfile3 == this.accountProfile;
            List list2 = this.list6;
            boolean flag4 = flag3;
            byte byteValue2 = 40;
            int count10 = count9;
            accountProfile3.applyList(count6, count10, count5, byteValue2, count, count2, flag4, list2);
            value += 42;
         }
      }

      if (count3 - count4 - 330 > 300 && this.accountProfile != null) {
         int count11 = count4 + 102 + 10;
         int count12 = count11 + count3 - count4 - 330;
         RenderUtils.processAction(count11, 57, count12, 227, new Color(0, 0, 0, 100).getRGB());
         RenderUtils.drawBorderedRect((float)(count11 + 5), (float)62, (float)(count12 - 5), (float)222);
         String text2 = "§7Detailed information:";
         int count13 = this.computeCount(text2, count11, 57, count12, 227, 0) + 5;
         String text3 = "§7Name: §f" + this.accountProfile.getLabelText();
         int count14 = this.computeCount(text3, count11, 57, count12, 227, count13);
         String text4 = "§7Login type: §f" + this.accountProfile.getMovementValueProperty().getLabelText();
         int count15 = this.computeCount(text4, count11, 57, count12, 227, count14);
         String text5 = "§7Account state: §f" + (this.accountProfile.isEnabled3() ? "§aFunctional" : "§cInvalid");
         int count16 = this.computeCount(text5, count11, 57, count12, 227, count15);
         DateFormat dateInstance = DateFormat.getDateInstance(2);
         DateFormat timeInstance = DateFormat.getTimeInstance(3);
         String text6 = dateInstance.format(this.accountProfile.getTimestamp2());
         String text7 = timeInstance.format(this.accountProfile.getTimestamp2());
         String text8 = "§7Last login time: §f" + text6 + " " + text7;
         int count17 = this.computeCount(text8, count11, 57, count12, 227, count16);
         String text9 = "§7Playtime: §f" + this.accountProfile.getTimestamp() / 1000L / 60L + " min";
         int count18 = this.computeCount(text9, count11, 57, count12, 227, count17);
         Collection values = this.accountProfile.getHashMap2().values();
         if (this.computeCount(values) > 0) {
            String text10 = "§7Banned on servers:";
            int count19 = this.computeCount(text10, count11, 57, count12, 227, count18) + 6;
            Collection values2 = this.accountProfile.getHashMap2().values();
            count18 = this.computeCount(values2, 35, count11, 57, count12, count19);
         }

         Collection values3 = this.accountProfile.getHashMap().values();
         if (this.computeCount(values3) > 0) {
            String text11 = "§7Unbanned on servers:";
            int count20 = this.computeCount(text11, count11, 57, count12, 227, count18) + 6;
            Collection values4 = this.accountProfile.getHashMap().values();
            this.computeCount(values4, 35, count11, 57, count12, count20);
         }
      }

      GL11.glDisable(3089);
      altDoubleEntry.updateState();
   }

   private void applyAuxiliaryTexturesGuiIconsP2(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.renderElement11();
   }

   public void setCompletableFuture(CompletableFuture completableFuture2) {
      this.completableFuture = completableFuture2;
   }

   private static void applyTextByText(String text) {
      MsnManager.applyText4(text);
   }

   private int computeCount(Collection collection) {
      int count = 0;
      Iterator iterator = collection.iterator();

      while (iterator.hasNext()) {
         if (((Pair)iterator.next()).getFirst() != null) {
            count++;
         }
      }

      return count;
   }

   public CompletableFuture getCompletableFuture() {
      return this.completableFuture;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      if ((count == 211 || count == 14)
         && this.accountProfile != null
         && !this.internalTexturesGuiIconsPngManagerAdapter.isEnabled2()
         && !this.texturesGuiIconsPngManagerAdapter.isEnabled5()) {
         copyOnWriteArrayList.remove(this.accountProfile);
         AltDoubleEntry altDoubleEntry2 = altDoubleEntry;
         int size = copyOnWriteArrayList.size();
         altDoubleEntry2.setCount4(size);
         this.accountProfile = null;
      }
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      altDoubleEntry.updateState5();
   }

   private void applyAuxiliaryTexturesGuiIconsP8(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.renderElement9();
   }

   private int computeCount(Collection collection, int count, int count2, int count3, int count4, int count5) {
      int count6 = 0;
      int count7 = this.computeCount(collection);
      int count8 = count * count7;

      for (Pair pair : (Iterable<Pair>)(Iterable<?>)collection) {
         if (pair.getFirst() != null) {
            GlStateManager.translate3f2(1.0F, 1.0F, 1.0F);
            int count9 = (int)((float)count2 + (float)(count4 - count2) / 2.0F) - count8 / 2 + count6;
            int count10 = count3 + count5;
            ResourceLocation second = (ResourceLocation)pair.getSecond();
            int count11 = count10;
            int count12 = count9;
            this.applyResourceLocation(count12, count11, second);
            count6 += count;
         }
      }

      return count5 + 29;
   }

   private void applyAuxiliaryTexturesGuiIconsP6(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.renderElement10();
   }

   private static void applyTextArray2(String[] textArray) {
      String text = textArray[0];
      String text2 = textArray[1];
      MsnManager.applyTextByText(text, text2);
   }

   private static void applyText2(String text) {
      MsnManager.applyText3(text);
   }

   @Override
   public void renderElement() {
      int count = 4176;
      int count2 = 247;
      int count3 = 239;
      super.renderElement();
      this.renderElement8();
      HashMap map = new HashMap();

      for (ServerData serverData : (Iterable<ServerData>)(Iterable<?>)this.list6) {
         ResourceLocation location = new ResourceLocation("servers/" + serverData.text + "/icon");
         byte byteValue2 = (byte)count3;
         map.put(serverData.text, this.resolvePairByServerData(serverData, count, location, count2, byteValue2));
      }

      if (copyOnWriteArrayList.isEmpty()) {
         Client.getClient().getAltItemEntry().bezierBuffer.updateState3();
      }

      Iterator iterator = copyOnWriteArrayList.iterator();

      while (iterator.hasNext()) {
         ((AccountProfile)iterator.next()).applyHashMap(map);
      }

      MsnManager.setColor(Color.GREEN);
      ScaledResolution scaledResolution = new ScaledResolution(super.mc);
      int count4 = (int)Math.max(118.0F, Math.min((float)scaledResolution.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      this.internalTexturesGuiIconsPngManagerAdapter = this.resolveInternalTexturesGuiIconsPngM(104, 54, count4, 20);
      this.texturesGuiIconsPngManagerAdapter = this.resolveTexturesGuiIconsPngManagerAd(104, 79, count4, 20);
      PostUpdateProcessor postUpdateProcessor = this::applyAuxiliaryTexturesGuiIconsP8;
      String text = "Login";
      byte byteValue3 = 20;
      byte byteValue4 = 104;
      byte byteValue5 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue5, byteValue4, count4, byteValue3, text, postUpdateProcessor);
      PostUpdateProcessor postUpdateProcessor2 = this::applyAuxiliaryTexturesGuiIconsP2;
      String text2 = "Generate";
      byte byteValue6 = 20;
      short shortValue = 129;
      byte byteValue7 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue7, shortValue, count4, byteValue6, text2, postUpdateProcessor2);
      PostUpdateProcessor postUpdateProcessor3 = DropdownMenuWidget::applyAuxiliaryTexturesGuiIconsP5;
      String text3 = "Reconnect";
      byte byteValue8 = 20;
      short shortValue2 = 154;
      byte byteValue9 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue9, shortValue2, count4, byteValue8, text3, postUpdateProcessor3);
      PostUpdateProcessor postUpdateProcessor4 = this::applyAuxiliaryTexturesGuiIconsP6;
      String text4 = "Clipboard login";
      byte byteValue10 = 20;
      short shortValue3 = 179;
      byte byteValue11 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue11, shortValue3, count4, byteValue10, text4, postUpdateProcessor4);
      PostUpdateProcessor postUpdateProcessor5 = this::applyAuxiliaryTexturesGuiIconsP4;
      String text5 = "Device code login";
      byte byteValue12 = 20;
      short shortValue4 = 204;
      byte byteValue13 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue13, shortValue4, count4, byteValue12, text5, postUpdateProcessor5);
      PostUpdateProcessor postUpdateProcessor6 = this::applyAuxiliaryTexturesGuiIconsP3;
      String text6 = "External browser login";
      byte byteValue14 = 20;
      short shortValue5 = 229;
      byte byteValue15 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue15, shortValue5, count4, byteValue14, text6, postUpdateProcessor6);
      PostUpdateProcessor postUpdateProcessor7 = this::applyAuxiliaryTexturesGuiIconsP7;
      String text7 = "Delete selected Alt";
      byte byteValue16 = 20;
      short shortValue6 = 254;
      byte byteValue17 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue17, shortValue6, count4, byteValue16, text7, postUpdateProcessor7);
      int count5 = super.width - 5;
      int height = super.height;
      int width = super.width;
      byte byteValue18 = 52;
      int count6 = count5;
      altDoubleEntry.applyIndex(count6, byteValue18, width, height);
      AltDoubleEntry altDoubleEntry2 = altDoubleEntry;
      int size = copyOnWriteArrayList.size();
      altDoubleEntry2.setCount4(size);
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);

      for (AccountProfile accountProfile2 : (Iterable<AccountProfile>)(Iterable<?>)copyOnWriteArrayList) {
         accountProfile2.applyIndex(count, count2, count3);
         if ((count3 == 1 || count3 == 0)
            && RenderUtils.checkCondition((float)accountProfile2.getCount(), (float)accountProfile2.getCount4(), (float)accountProfile2.getCount2(), (float)accountProfile2.getCount3(), count, count2)) {
            this.accountProfile = accountProfile2;
         }
      }

      double offset = (double)count;
      double offset2 = (double)count2;
      double offset3 = offset;
      altDoubleEntry.applyDouble(offset3, offset2);
   }

   private void applyAuxiliaryTexturesGuiIconsP7(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (this.accountProfile != null) {
         copyOnWriteArrayList.remove(this.accountProfile);
         AltDoubleEntry altDoubleEntry2 = altDoubleEntry;
         int size = copyOnWriteArrayList.size();
         altDoubleEntry2.setCount4(size);
         this.accountProfile = null;
         MsnManager.updateState3();
      } else {
         MsnManager.updateState4();
      }
   }

   private Pair resolvePairByServerData(ServerData serverData, int count, ResourceLocation location, int count2, byte byteValue) {
      DynamicTexture dynamicTexture;
      if (serverData.getLabelText2() == null) {
         super.mc.getTextureManager().applyResourceLocation2(location);
         dynamicTexture = null;
      } else {
         ByteBuf byteBuf = Unpooled.copiedBuffer(serverData.getLabelText2(), Charsets.UTF_8);
         ByteBuf byteBuf2 = Base64.decode(byteBuf);

         BufferedImage bufferedImage;
         searchBlock: {
            try {
               bufferedImage = SpecialLoggerEntry.resolveBufferedImage(new ByteBufInputStream(byteBuf2));
               Validate.validState(bufferedImage.getWidth() == 64, "Must be 64 pixels wide");
               Validate.validState(bufferedImage.getHeight() == 64, "Must be 64 pixels high");
               break searchBlock;
            } catch (Throwable throwable) {
               serverData.setLabelText2((String)null);
            } finally {
               byteBuf.release();
               byteBuf2.release();
            }

            return new Pair(location, null);
         }

         dynamicTexture = new DynamicTexture(bufferedImage.getWidth(), bufferedImage.getHeight());
         super.mc.getTextureManager().matchesResourceLocation(location, dynamicTexture);

         try {
            bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), dynamicTexture.getCountArray(), 0, bufferedImage.getWidth());
         } catch (Throwable throwable2) {
         }

         dynamicTexture.updateState2();
      }

      return new Pair(location, dynamicTexture);
   }

   private static void applyTextArrayByTextArray(String[] textArray) {
      MsnManager.applyText(textArray[0]);
   }

   private static void applyAuxiliaryTexturesGuiIconsP5(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      String[] textArray = new String[]{"igd", ""};
      String text = "<?xml version=\"1.0\" encoding=\"utf-8\"?><s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><s:Body><u:ForceTermination xmlns:u=\"urn:schemas-upnp-org:service:WANIPConnection:1\" /></s:Body></s:Envelope>";

      for (String text2 : textArray) {
         try {
            String text3 = "http://fritz.box:49000/" + text2 + "upnp/control/WANIPConn1";
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(text3).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "text/xml; charset=\"utf-8\"");
            httpURLConnection.setRequestProperty("Connection", "close");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(text.length()));
            httpURLConnection.setRequestProperty("HOST", "fritz.box:49000");
            httpURLConnection.setRequestProperty("SOAPACTION", "\"urn:schemas-upnp-org:service:WANIPConnection:1#ForceTermination\"");
            httpURLConnection.setDoOutput(true);
            boolean flag = false ;

            try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
               flag = true;
               outputStream.write(text.getBytes("UTF-8"));
               flag = false;
            }


            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
         } catch (Exception exception) {
            System.err.println("Fehler: " + exception.getMessage());
         }
      }
   }

   private void renderElement10() {
      if (this.completableFuture == null || this.completableFuture.isDone()) {
         String text = GuiScreen.getText();
         String[] textArray = text.split(":");
         if (textArray.length == 1 && textArray[0].contains("@alt")) {
            CompletableFuture completableFuture2 = CompletableFuture.runAsync(() -> DropdownMenuWidget.applyTextArrayByTextArray(textArray));
            this.setCompletableFuture(completableFuture2);
         } else if (textArray.length == 1) {
            CompletableFuture completableFuture3 = CompletableFuture.runAsync(() -> DropdownMenuWidget.applyTextByText(text));
            this.setCompletableFuture(completableFuture3);
         } else if (textArray.length == 2) {
            CompletableFuture completableFuture4 = CompletableFuture.runAsync(() -> DropdownMenuWidget.applyTextArray(textArray));
            this.setCompletableFuture(completableFuture4);
         }
      }
   }

   private void renderElement9() {
      if (this.completableFuture == null || this.completableFuture.isDone()) {
         if (this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty() && this.texturesGuiIconsPngManagerAdapter.getLabelText2().isEmpty()) {
            CompletableFuture completableFuture2 = CompletableFuture.runAsync(DropdownMenuWidget::renderElement7);
            this.setCompletableFuture(completableFuture2);
         } else if (!this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty()
            && !this.texturesGuiIconsPngManagerAdapter.getLabelText2().isEmpty()) {
            CompletableFuture completableFuture3 = CompletableFuture.runAsync(this::renderElement15);
            this.setCompletableFuture(completableFuture3);
         } else if (!this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty()
            && this.texturesGuiIconsPngManagerAdapter.getLabelText2().isEmpty()
            && this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().contains("@alt")) {
            CompletableFuture completableFuture4 = CompletableFuture.runAsync(this::renderElement12);
            this.setCompletableFuture(completableFuture4);
         } else if (!this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty()
            && this.texturesGuiIconsPngManagerAdapter.getLabelText2().isEmpty()
            && this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().contains(":")
            && this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().contains("@")) {
            String[] textArray = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().split(":");
            CompletableFuture completableFuture5 = CompletableFuture.runAsync(() -> DropdownMenuWidget.applyTextArray2(textArray));
            this.setCompletableFuture(completableFuture5);
         } else if (!this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty()
            && this.texturesGuiIconsPngManagerAdapter.getLabelText2().isEmpty()) {
            CompletableFuture completableFuture6 = CompletableFuture.runAsync(this::renderElement14);
            this.setCompletableFuture(completableFuture6);
         }
      }
   }

   private void renderElement12() {
      MsnManager.applyText(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText());
   }

   private static void renderElement7() {
      String text;
      if (ThreadLocalRandom.current().nextBoolean()) {
         text = CircumManager.getText();
      } else {
         text = AbruptManager.resolveText(16);
      }

      boolean flag = false;
      MsnManager.applyText(text, flag);
   }

   private void renderElement14() {
      String labelText = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
      boolean flag = true;
      MsnManager.applyText(labelText, flag);
   }

   public void renderElement8() {
      try {
         this.list6.clear();
         NBTTagCompound tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(new File(super.mc.mcDataDir, "servers.dat"));
         if (tagCompound == null) {
            return;
         }

         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("servers", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            this.list6.add(ServerData.resolveServerData(extendedLoggerEntry.getNBTTagCompound(index)));
         }
      } catch (Exception exception) {
      }
   }

   private void renderElement13() {
      list7.clear();
      String text = Client.getClient().getString4().toLowerCase() + "/names";
      ConfigManager.applyString("assets/augustus/RandomNames.txt", text, false);

      try {
         BufferedReader reader = new BufferedReader(new FileReader(OnlineConfigsManager.text + "/names/RandomNames.txt"));

         String text2;
         while ((text2 = reader.readLine()) != null) {
            if (!text2.equals("")) {
               list7.add(text2.trim());
            }
         }

         reader.close();
      } catch (IOException ioException) {
      }

      Collections.shuffle(list7);
   }

   public void runAsync() {
      CompletableFuture.runAsync(MsnManager::updateState);
   }

   private void renderElement11() {
      String text = getText();
      if (text.contains("api-")) {
         this.smoothPicker.applyObject2(text);
      }

      if (this.completableFuture == null || this.completableFuture.isDone()) {
         String value = (String)this.smoothPicker.getValue();
         if (value != null && !value.isEmpty()) {
            CompletableFuture completableFuture2 = CompletableFuture.runAsync(() -> DropdownMenuWidget.applyText2(value));
            this.setCompletableFuture(completableFuture2);
         }
      }
   }

   public void runAsync2() {
      CompletableFuture.runAsync(MsnManager::updateState2);
   }

   private void renderElement15() {
      String labelText = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
      String labelText2 = this.texturesGuiIconsPngManagerAdapter.getLabelText2();
      MsnManager.applyTextByText(labelText, labelText2);
   }

   public DropdownMenuWidget() {
      super("Account Manager");
      this.list6 = Lists.newArrayList();
      this.smoothPicker = new SmoothPicker(Listener60EntryAdapter.listenerTracker, "API-Key", "");
      dropdownMenuWidget = this;
      this.renderElement13();
   }
}
