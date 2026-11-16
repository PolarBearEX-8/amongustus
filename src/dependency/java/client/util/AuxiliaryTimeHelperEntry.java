package client.util;

import client.event.PostUpdateProcessor;
import client.gui.AugustusMainMenu;
import client.gui.AuxiliaryClientScreenAdapter;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManager;
import client.network.CosmeticsNetworkHandler;
import client.render.RenderUtils;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiConnecting;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import org.lwjgl.opengl.GL11;

public class AuxiliaryTimeHelperEntry extends AuxiliaryClientScreenAdapter {
   public final TimeHelper timer = new TimeHelper();
   public static int count6 = 1;
   public static String labelText5 = "";
   public static final AltDoubleEntry altDoubleEntry = new AltDoubleEntry(43);
   public static Color color = Color.green;
   public static String labelText3 = "Show: all";
   public String labelText4;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;

   private void applyText(int count, int count2, int count3, int count4, int count5, String text, int count7) {
      byte byteValue = 0;
      FontRenderer fontRendererObj = this.mc.fontRendererObj;
      TexturesGuiIconsPngManager.processAction(count, count2, count3, count4, new Color(0, 0, 0, text.equals(this.labelText4) ? 200 : 100).getRGB());
      String text2 = count5 == 1 ? "§c" : (count5 == 2 ? "§a" : "§f");
      String text3 = "§7Name: "
         + text2
         + text
         + " §7[§e"
         + count7
         + "§7]"
         + text2
         + (count5 == 1 ? " (Offline)" : (count5 == 2 ? " (Online)" : " (Pending request)"));
      float factor = (float)count + 5.5F;
      float factor2 = (float)count2 + 3.5F;
      int rgb = Color.white.getRGB();
      float factor3 = factor2;
      float factor4 = factor;
      String text4 = text3;
      this.applyTextByText(text4, factor4, factor3, rgb);
      String text5 = "-----";
      String text6 = "-----";
      if (count5 == 2) {
         StringBuilder builder = new StringBuilder();
         StringBuilder builder2 = new StringBuilder();
         boolean flag = true;

         for (Object friendRecordObj : AutoReportCache.concurrentHashMap.values()) {
            FriendRecord friendRecord = (FriendRecord)friendRecordObj;
            if (friendRecord.getText().equals(text)) {
               if (!flag) {
                  builder2.append(", ");
                  builder.append(", ");
               }

               builder.append(friendRecord.getLabelText6());
               builder2.append(friendRecord.getLabelText5());
               flag = false;
            }
         }

         if (!builder.toString().isEmpty()) {
            text5 = builder.toString();
         }

         if (!builder2.toString().isEmpty()) {
            text6 = builder2.toString();
         }
      }

      text3 = "§7Server: §f" + text5;
      factor = (float)count + 5.5F;
      factor2 = (float)count2 + 7.0F + (float)fontRendererObj.indexOffset;
      int rgb2 = Color.white.getRGB();
      float factor5 = factor2;
      float factor6 = factor;
      String text7 = text3;
      this.applyTextByText(text7, factor6, factor5, rgb2);
      text3 = "§7Ingame names: §f" + text6;
      factor = (float)count + 5.5F;
      factor2 = (float)count2 + 10.5F + (float)(fontRendererObj.indexOffset * 2);
      int rgb3 = Color.white.getRGB();
      float factor7 = factor2;
      float factor8 = factor;
      String text8 = text3;
      this.applyTextByText(text8, factor8, factor7, rgb3);
   }

   public static Color getColor() {
      return color;
   }

   private static void applyAuxiliaryTexturesGuiIconsP4(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      count6++;
      switch (count6) {
         case 1:
            labelText3 = "Show: all";
            break;
         case 2:
            labelText3 = "Show: online";
            break;
         case 3:
            labelText3 = "Show: requests";
      }

      auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = labelText3;
      if (count6 >= 3) {
         count6 = 0;
      }
   }

   public static synchronized String getLabelText5() {
      return labelText5;
   }

   private int computeCount(int count, int count2, int count3, int count4, int count5, int count7) {
      int count8 = count4;
      if ((count6 != 2 || count == 2) && (count6 != 0 || count == 0)) {
         for (Object friendProfileObj : FriendListManager.values()) {
            FriendProfile friendProfile = (FriendProfile)friendProfileObj;
            int index = friendProfile.getIndex();
            if (index == count) {
               int count9 = count3 + count8;
               int count10 = count2 + count5;
               int count11 = count3 + count7 + count8;
               int count12 = friendProfile.getCount2();
               String labelText = friendProfile.getLabelText();
               int count13 = count11;
               int count14 = count10;
               int count15 = count9;
               this.applyText(count2, count15, count14, count13, index, labelText, count12);
               count8 += count7 + 2;
            }
         }

         return count8;
      } else {
         return count4;
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      double offset = (double)count2;
      altDoubleEntry.applyDouble(offset);
   }

   public static synchronized void applyText(String text, Color color2) {
      labelText5 = text;
      color = color2;
   }

   @Override
   public void applyIndexByIndex(int count, int count2, float factor) {
      AltDoubleEntry altDoubleEntry2 = altDoubleEntry;
      int size = FriendListManager.values().size();
      altDoubleEntry2.setCount4(size);
      super.applyIndexByIndex(count, count2, factor);
      ScaledResolution scaledResolution = new ScaledResolution(this.mc);
      String labelText = getLabelText5();
      float factor2 = (float)(102 + this.width) / 2.0F;
      int rgb = getColor().getRGB();
      float factor3 = 38.0F;
      float factor4 = factor2;
      String text = labelText;
      this.applyText(text, factor4, factor3, rgb);
      if (this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty() && !this.internalTexturesGuiIconsPngManagerAdapter.isEnabled2()) {
         int rgb2 = Color.gray.getRGB();
         float factor5 = 60.0F;
         float factor6 = 109.0F;
         String text2 = "Friend user name";
         this.applyTextByText(text2, factor6, factor5, rgb2);
      }

      int count3 = (int)Math.max(100.0F, Math.min((float)scaledResolution.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      int count4 = Math.min(scaledResolution.getScaledWidth() - 102 - count3 - 20, 300);
      int count5 = scaledResolution.getScaledWidth() - 10 - count4;
      int value = (int)altDoubleEntry.getValue();
      GL11.glEnable(3089);
      RenderUtils.drawBorderedRect(102.0F, 52.0F, (float)this.width, (float)this.height);

      for (int index = 2; index >= 0; index--) {
         value = this.computeCount(index, count5, 57, value, count4, 41);
      }

      GL11.glDisable(3089);
      altDoubleEntry.updateState();
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      altDoubleEntry.updateState5();
   }

   private int computeCount(int count, int count2, int count3, int count4, int count5, int count7, int count8, int count9) {
      int count10 = count4;
      if ((count6 != 2 || count == 2) && (count6 != 0 || count == 0)) {
         for (Object friendProfileObj : FriendListManager.values()) {
            FriendProfile friendProfile = (FriendProfile)friendProfileObj;
            if (friendProfile.getIndex() == count) {
               int count11 = count3 + count10;
               int count12 = count2 + count5;
               int count13 = count3 + count7 + count10;
               int count14 = count12;
               int count15 = count11;
               this.applyFriendProfile(count2, count15, count14, count13, count8, count9, friendProfile);
               count10 += count7 + 2;
            }
         }

         return count10;
      } else {
         return count4;
      }
   }

   private void applyAuxiliaryTexturesGuiIconsP6(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (!CosmeticsNetworkHandler.isToggled() || !CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute()) {
         Color color2 = Color.RED;
         applyText("You are not online. Try again later.", color2);
      } else if (this.labelText4 == null) {
         Color color3 = Color.RED;
         applyText("No friend selected to remove", color3);
      } else if (!this.timer.hasReached(2000L)) {
         Color color4 = Color.RED;
         applyText("Click slower! (1 seconds delay)", color4);
      } else {
         this.timer.reset();
         SessionTokenManager.applyText3(this.labelText4);
      }
   }

   private void applyAuxiliaryTexturesGuiIconsP3(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (!CosmeticsNetworkHandler.isToggled() || !CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute()) {
         Color color2 = Color.RED;
         applyText("You are not online. Try again later.", color2);
      } else if (this.labelText4 == null) {
         Color color3 = Color.RED;
         applyText("No friend selected to join", color3);
      } else if (!this.timer.hasReached(1000L)) {
         Color color4 = Color.RED;
         applyText("Click slower! (1 seconds delay)", color4);
      } else {
         this.timer.reset();

         for (Object friendRecordObj : AutoReportCache.concurrentHashMap.values()) {
            FriendRecord friendRecord = (FriendRecord)friendRecordObj;
            if (friendRecord.getText().equals(this.labelText4) && friendRecord.getLabelText6() != null && !friendRecord.getLabelText6().isEmpty()) {
               if (this.mc.thePlayer != null) {
                  this.mc.state = true;
                  boolean gamePaused = this.mc.isGamePaused();
                  this.mc.theWorld.updateState();
                  this.mc.loadWorld((WorldClient)null);
                  if (gamePaused) {
                     Minecraft mc = this.mc;
                     GuiMainMenu guiMainMenu = new GuiMainMenu();
                     mc.displayGuiScreen(guiMainMenu);
                  } else {
                     Minecraft mc2 = this.mc;
                     GuiMultiplayer guiMultiplayer = new GuiMultiplayer(new GuiMainMenu());
                     mc2.displayGuiScreen(guiMultiplayer);
                  }
               }

               String labelText = friendRecord.getLabelText6();
               Minecraft mc3 = this.mc;
               GuiConnecting guiConnecting = new GuiConnecting(
                  new SharedStringEntry(AugustusMainMenu.resolveGuiScreen((short)0, (char)3322, -80609536), 2), this.mc, new ServerData(labelText, labelText, false)
               );
               mc3.displayGuiScreen(guiConnecting);
               Color color5 = Color.GREEN;
               applyText("Joining server", color5);
               break;
            }
         }

         Color color6 = Color.RED;
         applyText("No server to join", color6);
      }
   }

   private void applyFriendProfile(int count, int count2, int count3, int count4, int count5, int count7, FriendProfile friendProfile) {
      if (RenderUtils.checkCondition(count5, count7, (double)count, (double)count2, (double)count3, (double)count4)) {
         this.labelText4 = friendProfile.getLabelText();
      }
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      if (count3 == 0) {
         ScaledResolution scaledResolution = new ScaledResolution(this.mc);
         int count4 = (int)Math.max(100.0F, Math.min((float)scaledResolution.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
         int count5 = Math.min(scaledResolution.getScaledWidth() - 102 - count4 - 20, 300);
         int count7 = scaledResolution.getScaledWidth() - 10 - count5;
         int value = (int)altDoubleEntry.getValue();

         for (int index = 2; index >= 0; index--) {
            value = this.computeCount(index, count7, 57, value, count5, 41, count, count2);
         }

         double offset = (double)count;
         double offset2 = (double)count2;
         double offset3 = offset;
         altDoubleEntry.applyDouble(offset3, offset2);
      }
   }

   @Override
   public void renderElement() {
      super.renderElement();
      ScaledResolution scaledResolution = new ScaledResolution(this.mc);
      int count = (int)Math.max(100.0F, Math.min((float)scaledResolution.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      this.internalTexturesGuiIconsPngManagerAdapter = this.resolveInternalTexturesGuiIconsPngM(104, 54, count, 20);
      PostUpdateProcessor postUpdateProcessor = this::applyAuxiliaryTexturesGuiIconsP2;
      String text = "Send friend request";
      byte byteValue = 20;
      byte byteValue2 = 79;
      byte byteValue3 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue3, byteValue2, count, byteValue, text, postUpdateProcessor);
      PostUpdateProcessor postUpdateProcessor2 = this::applyAuxiliaryTexturesGuiIconsP6;
      String text2 = "Remove friend";
      byte byteValue4 = 20;
      byte byteValue5 = 104;
      byte byteValue6 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue6, byteValue5, count, byteValue4, text2, postUpdateProcessor2);
      PostUpdateProcessor postUpdateProcessor3 = this::applyAuxiliaryTexturesGuiIconsP5;
      String text3 = "Accept friend";
      byte byteValue7 = 20;
      short shortValue = 129;
      byte byteValue8 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue8, shortValue, count, byteValue7, text3, postUpdateProcessor3);
      PostUpdateProcessor postUpdateProcessor4 = this::applyAuxiliaryTexturesGuiIconsP3;
      String text4 = "Join Server";
      byte byteValue9 = 20;
      short shortValue2 = 154;
      byte byteValue10 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue10, shortValue2, count, byteValue9, text4, postUpdateProcessor4);
      String text5 = labelText3;
      PostUpdateProcessor postUpdateProcessor5 = AuxiliaryTimeHelperEntry::applyAuxiliaryTexturesGuiIconsP4;
      String text6 = text5;
      byte byteValue11 = 20;
      short shortValue3 = 179;
      byte byteValue12 = 104;
      this.resolveAuxiliaryTexturesGuiIconsPng(byteValue12, shortValue3, count, byteValue11, text6, postUpdateProcessor5);
      Color color2 = Color.GREEN;
      applyText("", color2);
      int count2 = this.width - 5;
      int count3 = this.height;
      int count4 = this.width;
      byte byteValue13 = 52;
      int count5 = count2;
      altDoubleEntry.applyIndex(count5, byteValue13, count4, count3);
   }

   private void applyAuxiliaryTexturesGuiIconsP5(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (!CosmeticsNetworkHandler.isToggled() || !CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute()) {
         Color color2 = Color.RED;
         applyText("You are not online. Try again later.", color2);
      } else if (this.labelText4 == null) {
         Color color3 = Color.RED;
         applyText("No friend selected to accept", color3);
      } else if (!this.timer.hasReached(1000L)) {
         Color color4 = Color.RED;
         applyText("Click slower! (1 seconds delay)", color4);
      } else {
         this.timer.reset();
         SessionTokenManager.applyText(this.labelText4);
      }
   }

   private void applyAuxiliaryTexturesGuiIconsP2(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (!TabListBotFilter.matchesText(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText(), 3)) {
         Color color2 = Color.RED;
         applyText("Invalid ElectricLauncher username", color2);
      } else if (!this.timer.hasReached(1000L)) {
         Color color3 = Color.RED;
         applyText("Click slower! (1 seconds delay)", color3);
      } else {
         this.timer.reset();
         SessionTokenManager.applyTextByText(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText());
      }
   }

   public AuxiliaryTimeHelperEntry() {
      super("Friends");
   }
}
