package net.minecraft.client.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.augustus.modules.render.ServerPinger;
import net.augustus.ui.multiplayer.ServerListHost;
import net.augustus.utils.network.IpLocationInfo;
import net.augustus.utils.network.IpLookupProvider;
import net.augustus.utils.network.DnsCacheFlusher;
import net.augustus.ui.widgets.LineGraphWidget;
import net.augustus.utils.network.IpCompanyInfo;
import net.augustus.render.RenderUtil;
import net.augustus.utils.network.IpLookupResult;
import net.augustus.Augustus;
import net.augustus.utils.network.IpAsnInfo;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiScreenServerList extends GuiScreen {
   private static final LineGraphWidget PLAYER_COUNT_GRAPH;
   private final ServerListHost serverListHost;
   private final ServerData serverData;
   private GuiTextField ipEdit;
   private final TimeHelper timeHelper = new TimeHelper();
   private String lastQueriedIp;
   private ServerListEntryNormal previewEntry;
   private List ipLookupLines;

   @Override
   public void updateScreen() {
      this.ipEdit.updateCursorCounter();
      if (this.getServerPingerModule().isToggled()) {
         if (this.timeHelper.reached(this.getServerPingerModule().pingDelay.getValue())) {
            String text = this.ipEdit.getText();
            boolean flag = this.lastQueriedIp == null || !this.lastQueriedIp.equals(text);
            if (this.getServerPingerModule().playerGraphEnabled.getValue() || flag) {
               this.queryServerAndIp(text, flag);
               this.lastQueriedIp = this.ipEdit.getText();
            }

            this.timeHelper.reset();
         }
      }
   }

   static {
      PLAYER_COUNT_GRAPH = new LineGraphWidget(250, 80);
   }

   private void queryServerAndIp(String str, boolean flag) {
      if (this.getServerPingerModule().serverWidgetEnabled.getValue()) {
         this.serverData.serverIP = str;
         this.serverData.pinged = false;
         this.previewEntry = new ServerListEntryNormal(this.serverListHost, this.serverData);
         this.previewEntry.setOnPingComplete(obj -> {
            try {
               String str2 = EnumChatFormatting.getTextWithoutFormattingCodes(((ServerData)obj).populationInfo);
               List list = (List)(this.getServerPingerModule().playerCountHistory.computeIfAbsent(str, obj2 -> new ArrayList()));
               list.add(Float.parseFloat(str2.split("/")[0]));
               PLAYER_COUNT_GRAPH.setSamples(list);
            } catch (Exception e) {
            }
         });
      }

      if (this.getServerPingerModule().isToggled() && this.getServerPingerModule().ipInfoEnabled.getValue() && flag && !str.isEmpty()) {
         IpLookupProvider ipLookupProvider = Arrays.stream(IpLookupProvider.values()).filter(ipLookupProvider2 -> {
            return ipLookupProvider2.getDisplayName().equals(this.getServerPingerModule().backend.getSelectedValue());
         }).findFirst().orElse(IpLookupProvider.INCOLUMITAS);
         CompletableFuture.<String>supplyAsync(() -> this.resolveServerHost(str)).thenAccept(str3 -> {
            ipLookupProvider.lookupAsync(str3 != null ? ServerAddress.fromString(str3).getIP() : str).whenComplete((obj3, obj4) -> {
               if (obj4 != null) {
                  this.ipLookupLines = null;
               } else {
                  this.ipLookupLines = new ArrayList();
                  this.ipLookupLines.add("Query:");
                  IpLookupResult var1g = (IpLookupResult)obj3;
                  this.ipLookupLines.add(var1g.ip + " (Managed by " + var1g.rir + ")");
                  IpCompanyInfo company = var1g.company;
                  this.ipLookupLines.add(company.domain);
                  this.ipLookupLines.add("");
                  this.ipLookupLines.add("Company/" + company.type + ":");
                  this.ipLookupLines.add(company.name);
                  this.ipLookupLines.add("");
                  this.ipLookupLines.add("Network:");
                  this.ipLookupLines.add(company.network);
                  this.ipLookupLines.add("");
                  IpAsnInfo asn = var1g.asn;
                  this.ipLookupLines.add("ASN:");
                  this.ipLookupLines.add(asn.asn + " (" + asn.country + ")");
                  this.ipLookupLines.add("");
                  this.ipLookupLines.add("ASN Route:");
                  this.ipLookupLines.add(asn.route);
                  this.ipLookupLines.add("");
                  this.ipLookupLines.add("Net Description:");
                  this.ipLookupLines.add(asn.descr);
                  this.ipLookupLines.add("");
                  IpLocationInfo location = var1g.location;
                  this.ipLookupLines.add("Location:");
                  this.ipLookupLines.add(location.country + ", " + location.state + ", " + location.city);
               }
            });
         });
      }

      if (this.getServerPingerModule().playerGraphEnabled.getValue()) {
         if (!this.getServerPingerModule().keepPlayerData.getValue()) {
            this.getServerPingerModule().playerCountHistory.clear();
         }

         List orDefault = (List)(this.getServerPingerModule().playerCountHistory.getOrDefault(str, new ArrayList()));
         PLAYER_COUNT_GRAPH.setSamples(orDefault);
         PLAYER_COUNT_GRAPH.setMaxSamples((int)this.getServerPingerModule().maxEntries.getValue());
      }
   }

   private ServerPinger getServerPingerModule() {
      return Augustus.getInstance().getModuleManager().serverPinger;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 1) {
            this.serverListHost.confirmClicked(false, 0);
         } else if (button.id == 0) {
            this.serverData.serverIP = this.ipEdit.getText();
            this.serverListHost.confirmClicked(true, 0);
         } else if (button.id == 2) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.resolveServerHost(this.ipEdit.getText())), null);
         } else if (button.id == 3) {
            DnsCacheFlusher.flushDns();
         }
      }
   }

   public GuiScreenServerList(ServerListHost serverListHost2, ServerData serverData2) {
      this.serverListHost = serverListHost2;
      this.serverData = serverData2;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.ipEdit.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.getServerPingerModule().isToggled() && this.getServerPingerModule().serverWidgetEnabled.getValue()) {
         if (this.previewEntry != null) {
            int n = this.width / 2 - 152;
            if (mouseX >= n && mouseX <= n + 305 && mouseY >= 30 && mouseY <= 66) {
               this.queryServerAndIp(this.ipEdit.getText(), true);
            }
         }
      }
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.format("selectServer.select", new Object[0])));
      this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));
      this.buttonList.add(new GuiButton(2, 5, 5, 90, 20, "Resolve SRV"));
      this.buttonList.add(new GuiButton(3, 5, 28, 90, 20, "Flush DNS Cache"));
      this.ipEdit = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 116, 200, 20);
      this.ipEdit.setMaxStringLength(128);
      this.ipEdit.setFocused(true);
      this.ipEdit.setText(this.mc.gameSettings.lastServer);
      ((GuiButton)this.buttonList.get(0)).enabled = !this.ipEdit.getText().isEmpty() && this.ipEdit.getText().split(":").length > 0;
      int n = this.height - 80 - 5;
      PLAYER_COUNT_GRAPH.setPosition((byte)15, n);
      PLAYER_COUNT_GRAPH.setMaxSamples((int)this.getServerPingerModule().maxEntries.getValue());
      this.queryServerAndIp(this.ipEdit.getText(), true);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, I18n.format("selectServer.direct", new Object[0]), this.width / 2, 20, 16777215);
      this.drawString(this.fontRendererObj, I18n.format("addServer.enterIp", new Object[0]), this.width / 2 - 100, 100, 10526880);
      this.ipEdit.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.getServerPingerModule().isToggled()) {
         if (this.getServerPingerModule().serverWidgetEnabled.getValue() && this.previewEntry != null) {
            byte b = 34;
            int n = this.width / 2 - 152;
            this.serverListHost.setHoveringText(null);
            GL11.glPushMatrix();
            GL11.glTranslated((double)n, 30.0, 0.0);
            RenderUtil.drawRect(0, 0, 305, 34, this.getServerPingerModule().widgetBackgroundColor.getColorInt((byte)0));
            this.previewEntry.drawEntry(0, 0, 0, 309, 34, mouseX - n, mouseY - 30, false);
            RenderUtil.drawRect(0, 32, (int)((double)this.timeHelper.getTime() / this.getServerPingerModule().pingDelay.getValue() * 305.0), 34, this.getServerPingerModule().widgetColor.getColorInt((byte)0));
            GL11.glPopMatrix();
         }

         if (this.getServerPingerModule().ipInfoEnabled.getValue() && this.ipLookupLines != null) {
            int yPosition = this.ipEdit.yPosition;
            int n3 = this.width / 4;
            int n4 = 15 + n3;
            RenderUtil.drawRect(15, yPosition, n4, yPosition + 220, this.getServerPingerModule().ipInfoBoxColor.getColorInt((byte)0));
            GL11.glEnable(3089);
            RenderUtil.scissorRect(15.0F, (float)yPosition, (float)(15 + n3), (float)(yPosition + 220));

            for (int i = 0; i < this.ipLookupLines.size(); i++) {
               String str = (String)this.ipLookupLines.get(i);
               if (str != null) {
                  FontRenderer fontRendererObj2 = this.fontRendererObj;
                  int n6 = yPosition + 5 + i * 10;
                  if (str.endsWith(":")) {
                     n4 = this.getServerPingerModule().ipInfoTextColor.getColorInt((byte)0);
                  } else {
                     n4 = -1;
                  }

                  fontRendererObj2.drawString(str, 20, n6, n4);
               }
            }

            GL11.glDisable(3089);
         }

         if (this.getServerPingerModule().playerGraphEnabled.getValue()) {
            PLAYER_COUNT_GRAPH.drawGraph(mouseX, mouseY, partialTicks);
         }

         this.serverListHost.renderHoveringText(this, mouseX, mouseY);
      }
   }

   @Override
   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      this.mc.gameSettings.lastServer = this.ipEdit.getText();
      this.mc.gameSettings.saveOptions();
   }

   private String resolveServerHost(String str) {
      try {
         ServerAddress serverAddress = ServerAddress.fromString(str);
         return InetAddress.getByName(serverAddress.getIP()).getHostAddress() + ":" + serverAddress.getPort();
      } catch (UnknownHostException e) {
         return null;
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (this.ipEdit.textboxKeyTyped(typedChar, keyCode)) {
         ((GuiButton)this.buttonList.get(0)).enabled = !this.ipEdit.getText().isEmpty() && this.ipEdit.getText().split(":").length > 0;
         this.timeHelper.reset();
      } else if (keyCode == 28 || keyCode == 156) {
         this.actionPerformed((GuiButton)this.buttonList.get(0));
      }

      if (keyCode == 1) {
         GuiScreen serverListHost2 = (GuiScreen)this.serverListHost;
         this.mc.displayGuiScreen(serverListHost2);
      }
   }

}
