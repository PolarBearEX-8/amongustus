package net.augustus.ui.proxy;

import net.augustus.Augustus;
import net.augustus.proxy.ProxyProtocolType;
import net.augustus.render.RenderUtil;
import net.augustus.ui.GuiButtonClickHandler;
import net.augustus.ui.GuiPasswordField;
import net.augustus.ui.account.AccountAuthenticator;
import net.augustus.ui.menu.ClientMenuTab;
import net.augustus.ui.widgets.ScrollBar;
import net.augustus.utils.TuplePair;
import net.augustus.utils.network.ProxyEntry;
import net.augustus.utils.render.ColorUtil;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;

public class ProxyManagerScreen extends ClientMenuTab {
   private boolean reinitPending;
   private CompletableFuture activePingTask;
   private static ProxyEntry pendingProxy = null;
   public static final ScrollBar scrollBar;
   public static final CopyOnWriteArrayList proxies = new CopyOnWriteArrayList();
   private boolean mcServerCheckEnabled;
   private GuiTextField usernameField;
   private GuiPasswordField passwordField;
   private ProxyEntry selectedProxy;
   private ProxyEntry previousProxy;
   public static final ConcurrentHashMap proxyListEntries = new ConcurrentHashMap();
   private GuiTextField addressField;
   private ProxyProtocolType selectedProtocolType = ProxyProtocolType.SOCKS5;

   public static synchronized void setPendingProxy(ProxyEntry proxyEntry) {
      pendingProxy = proxyEntry;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (this.selectedProxy != null && (keyCode == 211 || keyCode == 14 && !this.addressField.isFocused() && !this.usernameField.isFocused() && !this.passwordField.isFocused())) {
         proxies.remove(this.selectedProxy);
         proxyListEntries.remove(this.selectedProxy);
         this.selectedProxy = null;
      }
   }

   public void connectToProxy(String str, boolean flag) {
      if (str.isEmpty()) {
         Color color = Color.red.darker();
         this.setStatus("IP is empty!", color);
      } else {
         String[] parts = str.split(":");
         if (parts.length >= 2 && parts.length <= 4) {
            String[] strings2 = new String[4];
            System.arraycopy(parts, 0, strings2, 0, parts.length);
            if (!flag) {
               if (!this.usernameField.getText().isEmpty()) {
                  strings2[2] = this.usernameField.getText();
               }

               if (!this.passwordField.getText().isEmpty()) {
                  strings2[3] = this.passwordField.getText();
               }
            }

            int n = parsePortOrDefault(strings2[1]);
            if (n == -1) {
               Color color2 = Color.red.darker();
               this.setStatus("Port is not numeric!", color2);
            } else {
               String str4 = strings2[0];
               String str5 = strings2[2] == null ? "" : strings2[2];
               String str6 = strings2[3] == null ? "" : strings2[3];
               ProxyProtocolType selectedProtocolType2 = this.selectedProtocolType;
               pendingProxy = new ProxyEntry(str4, n, str5, str6, selectedProtocolType2);
               if (pendingProxy.getSocketAddress() == null) {
                  Color color3 = Color.red.darker();
                  this.setStatus("No valid proxy format!", color3);
                  pendingProxy = null;
               } else {
                  if (this.activePingTask == null) {
                     Color yellow = Color.yellow;
                     this.setStatus("Pinging...", yellow);
                     this.previousProxy = Augustus.getInstance().getProxy();
                     this.activePingTask = CompletableFuture.supplyAsync(() -> {
                        boolean mcServerCheckEnabled2 = this.mcServerCheckEnabled;
                        ProxyEntry previousProxy2 = this.previousProxy;
                        return ProxyChecker.testAndApplyProxy(pendingProxy, previousProxy2, mcServerCheckEnabled2);
                     });
                  }
               }
            }
         } else {
            Color color4 = Color.red.darker();
            this.setStatus("Wrong proxy format! [Ip:Port] / [Ip:Port:Username] / [Ip:Port:Username:Password]", color4);
         }
      }
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      double d = (double)mouseY;
      scrollBar.handleMouseDrag(d);
   }

   public static synchronized ProxyEntry getPendingProxy() {
      return pendingProxy;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      scrollBar.handleMouseWheel();
   }

   private void onDisconnectClick(GuiButton button) {
      this.disconnectProxy();
   }

   public ProxyManagerScreen() {
      super("Proxy Manager");
      this.mcServerCheckEnabled = false;
      this.selectedProxy = null;
      this.previousProxy = null;
      this.activePingTask = null;
      this.reinitPending = false;
      Color green = Color.green;
      this.setStatus("Waiting...", green);
   }

   private void importProxiesAsync() {
      new Thread(() -> this.showImportProxiesDialog()).start();
   }

   private void showImportProxiesDialog() {
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Select Proxy File");
      jFileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
      if (jFileChooser.showOpenDialog(null) == 0) {
         File selectedFile = jFileChooser.getSelectedFile();

         try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            String str;
            while ((str = reader.readLine()) != null) {
               try {
                  if (!str.trim().isEmpty()) {
                     ProxyProtocolType selectedProtocolType2 = this.selectedProtocolType;
                     String str2 = str.trim();
                     if (str2.toLowerCase().startsWith("socks4://")) {
                        selectedProtocolType2 = ProxyProtocolType.SOCKS4;
                        str2 = str2.substring(9);
                     } else if (str2.toLowerCase().startsWith("socks5://")) {
                        selectedProtocolType2 = ProxyProtocolType.SOCKS5;
                        str2 = str2.substring(9);
                     }

                     String[] parts = str2.split(":");
                     if (parts.length >= 2) {
                        String str3 = parts[0];
                        int n2 = parsePortOrDefault(parts[1]);
                        if (n2 != -1) {
                           String str4 = parts.length > 2 ? parts[2] : "";
                           String str5 = parts.length > 3 ? parts[3] : "";
                           ProxyEntry proxyEntry = new ProxyEntry(str3, n2, str4, str5, selectedProtocolType2);
                           if (!proxies.contains(proxyEntry)) {
                              proxies.add(proxyEntry);
                              proxyListEntries.put(proxyEntry, new ProxyListEntry(proxyEntry));
                           }
                        }
                     }
                  }
               } catch (Exception e) {
                  System.err.println("Failed to parse proxy line: " + str);
               }
            }

            scrollBar.setItemCount(proxies.size());
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
   }

   private void onImportProxiesClick(GuiButton button) {
      this.importProxiesAsync();
   }

   static {
      scrollBar = new ScrollBar(32);
   }

   @Override
   public void drawContent(int n, int n2, float f) {
      ScaledResolution sr = new ScaledResolution(this.mc);
      int n3 = (int)Math.max(100.0F, Math.min((float)sr.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      if (this.addressField.getText().isEmpty() && !this.addressField.isFocused()) {
         int rgb = Color.gray.getRGB();
         float f2 = 60.0F;
         float f3 = 109.0F;
         this.drawString("IP-Adress:Port", f3, f2, rgb);
      }

      if (this.usernameField.getText().isEmpty() && !this.usernameField.isFocused()) {
         int rgb2 = Color.gray.getRGB();
         float f4 = 110.0F;
         float f5 = 109.0F;
         this.drawString("Username", f5, f4, rgb2);
      }

      if (this.passwordField.getText().isEmpty() && !this.passwordField.isFocused() && this.selectedProtocolType != ProxyProtocolType.SOCKS4) {
         int rgb3 = Color.gray.getRGB();
         float f6 = 135.0F;
         float f7 = 109.0F;
         this.drawString("Password", f7, f6, rgb3);
      }

      int n5 = Math.min((sr.getScaledWidth() - 102) - n3 - 20, 300);
      int n6 = sr.getScaledWidth() - 10 - n5;
      int clampedScrollOffset = (int)scrollBar.getClampedScrollOffset();

      for (ProxyEntry proxyEntry : (Iterable<ProxyEntry>)(proxies)) {
         ProxyListEntry proxyListEntry = (ProxyListEntry)proxyListEntries.get(proxyEntry);
         if (proxyListEntry != null && proxyListEntry.isFavorite()) {
            proxyListEntry.drawEntry(n, n2, n6, 57 + clampedScrollOffset, n5, (byte)30, proxyEntry == this.selectedProxy);
            clampedScrollOffset += 32;
         }
      }

      for (ProxyEntry proxyEntry2 : (Iterable<ProxyEntry>)(proxies)) {
         ProxyListEntry proxyListEntry2 = (ProxyListEntry)proxyListEntries.get(proxyEntry2);
         if (proxyListEntry2 != null && !proxyListEntry2.isFavorite()) {
            proxyListEntry2.drawEntry(n, n2, n6, 57 + clampedScrollOffset, n5, (byte)30, proxyEntry2 == this.selectedProxy);
            clampedScrollOffset += 32;
         }
      }

      scrollBar.updateAndDraw();
      if (this.activePingTask != null && this.activePingTask.isDone()) {
         try {
            TuplePair tuplePair = (TuplePair)this.activePingTask.get();
            String first = (String)tuplePair.getFirst();
            Color green = Color.green;
            Color color = Color.red.darker();
            double d = Math.min(1.0, (double)((Integer)tuplePair.getSecond()).intValue() / 1000.0);
            this.setStatus(first, new Color(ColorUtil.blendColors(green, color, d)));
            this.activePingTask = null;
            ProxyEntry proxy = Augustus.getInstance().getProxy();
            if (proxy != null && getPendingProxy() != null) {
               if (proxy.matches(getPendingProxy())) {
                  this.addressField.setText(proxy.getHost() + ":" + proxy.getPort());
                  this.usernameField.setText(proxy.getUsername());
                  if (this.passwordField != null) {
                     this.passwordField.setText(proxy.getPassword());
                  }
               }
            }
         } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
         }
      }
   }

   private void disconnectProxy() {
      Augustus.getInstance().setProxy((ProxyEntry)null);
      Color green = Color.green;
      this.setStatus("Waiting...", green);
   }

   private void onClearFieldsClick(GuiButton button) {
      this.clearInputFields();
   }

   private void connectFromClipboard() {
      this.connectToProxy(GuiScreen.getClipboardString().replace(" ", ""), true);
   }

   private void onDeleteSelectedProxyClick(GuiButton button) {
      if (this.selectedProxy != null) {
         proxies.remove(this.selectedProxy);
         proxyListEntries.remove(this.selectedProxy);
         scrollBar.setItemCount(proxies.size());
         this.selectedProxy = null;
         AccountAuthenticator.playSuccessSound();
      } else {
         AccountAuthenticator.playFailureSound();
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.reinitPending) {
         this.initGui();
      }

      for (ProxyEntry proxyEntry : (Iterable<ProxyEntry>)(proxies)) {
         ProxyListEntry proxyListEntry = (ProxyListEntry)proxyListEntries.get(proxyEntry);
         proxyListEntry.mouseClicked(mouseX, mouseY, mouseButton, this);
         if ((mouseButton == 1 || mouseButton == 0) && RenderUtil.isHovered((float)proxyListEntry.getRenderX(), (float)proxyListEntry.getRenderY(), (float)proxyListEntry.getRenderWidth(), (float)proxyListEntry.getRenderHeight(), mouseX, mouseY)
            )
          {
            this.selectedProxy = proxyEntry;
         }
      }

      double d = (double)mouseX;
      double d3 = (double)mouseY;
      scrollBar.handleMouseClick(d, d3);
   }

   public static Integer parsePortOrDefault(String str) {
      try {
         return Integer.parseInt(str);
      } catch (Exception e) {
         return -1;
      }
   }

   private void onToggleProtocolTypeClick(GuiButton button) {
      switch (this.selectedProtocolType) {
         case SOCKS4:
            this.selectedProtocolType = ProxyProtocolType.SOCKS5;
            break;
         case SOCKS5:
            this.selectedProtocolType = ProxyProtocolType.SOCKS4;
      }

      this.reinitPending = true;
      button.displayString = "Type: " + this.selectedProtocolType.getDisplayName();
   }

   private void populateFieldsFromPendingProxy() {
      if (pendingProxy != null) {
         this.addressField.setText(pendingProxy.getHost() + ":" + pendingProxy.getPort());
         this.usernameField.setText(pendingProxy.getUsername());
         if (this.passwordField != null) {
            this.passwordField.setText(pendingProxy.getPassword());
         }
      }
   }

   @Override
   public void initContent() {
      this.reinitPending = false;
      super.initContent();
      ScaledResolution sr = new ScaledResolution(this.mc);
      byte b = 104;
      byte b2 = 54;
      byte b3 = 25;
      int n = (int)Math.max(100.0F, Math.min((float)sr.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      int n2 = 1;
      this.addressField = this.addTextField(b, b2, n, (byte)20);
      int n3 = b2 + b3 * n2;
      String str = "Type: " + this.selectedProtocolType.getDisplayName();
      GuiButtonClickHandler guiButtonClickHandler = button -> {
         this.onToggleProtocolTypeClick(button);
      };
      this.addButton(b, n3, n, (byte)20, str, guiButtonClickHandler);
      int n5 = b2 + b3 * ++n2;
      this.usernameField = this.addTextField(b, n5, n, (byte)20);
      n2++;
      if (this.selectedProtocolType == ProxyProtocolType.SOCKS5) {
         n5 = b2 + b3 * n2;
         this.passwordField = this.addPasswordField(b, n5, n, (byte)20);
         this.passwordField.setVisible(true);
         n2++;
      } else {
         this.passwordField.setVisible(false);
      }

      this.populateFieldsFromPendingProxy();
      n3 = b2 + b3 * n2;
      GuiButtonClickHandler guiButtonClickHandler2 = button2 -> {
         this.connectToProxy(this.addressField.getText(), false);
      };
      this.addButton(b, n3, n, (byte)20, "Connect", guiButtonClickHandler2);
      n3 = b2 + b3 * ++n2;
      GuiButtonClickHandler guiButtonClickHandler3 = button3 -> this.onClearFieldsClick(button3);
      this.addButton(b, n3, n, (byte)20, "Clear", guiButtonClickHandler3);
      n3 = b2 + b3 * ++n2;
      GuiButtonClickHandler guiButtonClickHandler4 = button4 -> this.onDisconnectClick(button4);
      this.addButton(b, n3, n, (byte)20, "Disconnect", guiButtonClickHandler4);
      n3 = b2 + b3 * ++n2;
      GuiButtonClickHandler guiButtonClickHandler5 = button5 -> {
         this.connectFromClipboard();
      };
      this.addButton(b, n3, n, (byte)20, "Clipboard Login", guiButtonClickHandler5);
      n3 = b2 + b3 * ++n2;
      GuiButtonClickHandler guiButtonClickHandler6 = button6 -> this.onImportProxiesClick(button6);
      this.addButton(b, n3, n, (byte)20, "Import Proxies", guiButtonClickHandler6);
      n3 = b2 + b3 * ++n2;
      StringBuilder sb2 = new StringBuilder().append("MC Check: ");
      String str9;
      if (this.mcServerCheckEnabled) {
         str9 = "True";
      } else {
         str9 = "False";
      }

      str = sb2.append(str9).toString();
      GuiButtonClickHandler guiButtonClickHandler7 = button7 -> {
         this.mcServerCheckEnabled = !this.mcServerCheckEnabled;
         StringBuilder sb3 = new StringBuilder();
         sb3 = sb3.append("MC Check: ");
         String str10;
         if (this.mcServerCheckEnabled) {
            str10 = "True";
         } else {
            str10 = "False";
         }

         button7.displayString = sb3.append(str10).toString();
      };
      this.addButton(b, n3, n, (byte)20, str, guiButtonClickHandler7);
      n3 = b2 + b3 * ++n2;
      GuiButtonClickHandler guiButtonClickHandler8 = button8 -> this.onDeleteSelectedProxyClick(button8);
      this.addButton(b, n3, n, (byte)20, "Delete selected Proxy", guiButtonClickHandler8);
      n3 = this.width - 5;
      int height2 = this.height;
      int width2 = this.width;
      int n15 = n3;
      scrollBar.setBounds(n15, (byte)52, width2, height2);
      scrollBar.setItemCount(proxies.size());
   }

   private void clearInputFields() {
      this.addressField.setText("");
      String str = "";
      this.passwordField.setText(str);
      this.usernameField.setText("");
      pendingProxy = null;
   }

}
