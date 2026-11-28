package net.augustus.ui.account;

import net.augustus.Augustus;
import net.augustus.alt.AltUsernameGenerator;
import net.augustus.config.ClientSettingsConfigFile;
import net.augustus.render.RenderUtil;
import net.augustus.settings.property.BooleanProperty;
import net.augustus.settings.property.StringProperty;
import net.augustus.ui.GuiButtonClickHandler;
import net.augustus.ui.GuiPasswordField;
import net.augustus.ui.menu.ClientMenuTab;
import net.augustus.ui.widgets.ScrollBar;
import net.augustus.utils.TuplePair;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;

public class AccountManagerScreen extends ClientMenuTab {
   public static AccountManagerScreen instance;
   public static List randomNames = new ArrayList();
   private final List savedServers;
   public static final CopyOnWriteArrayList accounts = new CopyOnWriteArrayList();
   public final BooleanProperty removeInvalidAccounts;
   private CompletableFuture activeLoginTask;
   private final StringProperty apiKey;
   private GuiTextField emailOrTokenField;
   private AccountEntry selectedAccount;
   public static final ScrollBar scrollBar;
   private GuiPasswordField passwordField;

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      double d = (double)mouseY;
      scrollBar.handleMouseDrag(d);
   }

   private void loginFromClipboard() {
      if (this.activeLoginTask == null || this.activeLoginTask.isDone()) {
         String clipboardString = GuiScreen.getClipboardString();
         String[] parts = clipboardString.split(":");
         if (parts.length == 1 && parts[0].contains("@alt")) {
            this.setActiveLoginTask(CompletableFuture.runAsync(() -> loginClipboardAlteningToken(parts)));
         } else if (clipboardString.startsWith("eyJra") && clipboardString.length() > 16) {
            this.setActiveLoginTask(CompletableFuture.runAsync(() -> loginClipboardAccessToken(clipboardString)));
         } else if (parts.length == 1) {
            this.setActiveLoginTask(CompletableFuture.runAsync(() -> loginClipboardEasyMc(clipboardString)));
         } else if (parts.length == 2) {
            this.setActiveLoginTask(CompletableFuture.runAsync(() -> {
               AccountAuthenticator.loginMicrosoftCredentials(parts[0], parts[1]);
            }));
         }
      }
   }

   private void loadRandomNames() {
      randomNames.clear();
      String str = Augustus.getInstance().getName().toLowerCase() + "/names";
      ClientFileUtil.extractResource("assets/augustus/RandomNames.txt", str, false);

      try {
         BufferedReader reader = new BufferedReader(new FileReader(ClientPaths.CLIENT_DIR + "/names/RandomNames.txt"));

         String str3;
         while ((str3 = reader.readLine()) != null) {
            if (!str3.equals("")) {
               randomNames.add(str3.trim());
            }
         }

         reader.close();
      } catch (IOException e) {
      }

      Collections.shuffle(randomNames);
   }

   private static void loginAlteningApiKey(String str) {
      AccountAuthenticator.generateAndLoginTheAltening(str);
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      scrollBar.handleMouseWheel();
   }

   private void loginInputCredentials() {
      AccountAuthenticator.loginMicrosoftCredentials(this.emailOrTokenField.getText(), this.passwordField.getText());
   }

   private void performFieldLogin() {
      if (this.activeLoginTask == null || this.activeLoginTask.isDone()) {
         if (this.emailOrTokenField.getText().isEmpty() && this.passwordField.getText().isEmpty()) {
            CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
               String str;
               if (ThreadLocalRandom.current().nextBoolean()) {
                  str = SyllableUsernameGenerator.generateUsername();
               } else {
                  str = AltUsernameGenerator.generateUsername(16);
               }

               AccountAuthenticator.loginCracked(str, false);
            });
            this.setActiveLoginTask(completableFuture);
         } else if (!this.emailOrTokenField.getText().isEmpty() && !this.passwordField.getText().isEmpty()) {
            this.setActiveLoginTask(CompletableFuture.runAsync(() -> this.loginInputCredentials()));
         } else if (!this.emailOrTokenField.getText().isEmpty() && this.passwordField.getText().isEmpty()) {
            String str2 = this.emailOrTokenField.getText().trim();
            if (str2.contains("@alt")) {
               this.setActiveLoginTask(CompletableFuture.runAsync(() -> loginInputAlteningToken(str2)));
            } else if (str2.contains(":") && str2.contains("@")) {
               String[] parts = str2.split(":");
               this.setActiveLoginTask(CompletableFuture.runAsync(() -> loginSplitCredentials(parts)));
            } else if (str2.startsWith("eyJra") && str2.length() > 16) {
               this.setActiveLoginTask(CompletableFuture.runAsync(() -> {
                  AccountAuthenticator.loginWithAccessToken(str2);
               }));
            } else {
               this.setActiveLoginTask(CompletableFuture.runAsync(() -> {
                  AccountAuthenticator.loginCracked(str2, true);
               }));
            }
         }
      }
   }

   private void onDeleteSelectedClick(GuiButton button) {
      if (this.selectedAccount != null) {
         accounts.remove(this.selectedAccount);
         scrollBar.setItemCount(accounts.size());
         this.selectedAccount = null;
         AccountAuthenticator.playSuccessSound();
      } else {
         AccountAuthenticator.playFailureSound();
      }
   }

   private static void loginClipboardEasyMc(String str) {
      AccountAuthenticator.loginEasyMc(str);
   }

   @Override
   public void initContent() {
      super.initContent();
      this.loadSavedServerList();
      HashMap map = new HashMap();

      for (ServerData serverData : (Iterable<ServerData>)(this.savedServers)) {
         ResourceLocation location = new ResourceLocation("servers/" + serverData.serverIP + "/icon");
         map.put(serverData.serverIP, this.loadServerIconTexture(serverData, location));
      }

      if (accounts.isEmpty()) {
         Augustus.getInstance().getConfigManager().accountConfig.load();
      }

      for (AccountEntry accountEntry : (Iterable<AccountEntry>)(accounts)) {
         accountEntry.refreshServerIcons(map);
      }

      Color green = Color.GREEN;
      this.setStatus("Waiting...", green);
      ScaledResolution sr = new ScaledResolution(this.mc);
      int n = (int)Math.max(118.0F, Math.min((float)sr.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      this.emailOrTokenField = this.addTextField((byte)104, (byte)54, n, (byte)20);
      this.passwordField = this.addPasswordField((byte)104, (byte)79, n, (byte)20);
      GuiButtonClickHandler guiButtonClickHandler = button -> this.onLoginButtonClick(button);
      this.addButton((byte)104, (byte)104, n, (byte)20, "Login", guiButtonClickHandler);
      GuiButtonClickHandler guiButtonClickHandler2 = button2 -> this.onGenerateAlteningClick(button2);
      this.addButton((byte)104, (short)129, n, (byte)20, "Generate", guiButtonClickHandler2);
      GuiButtonClickHandler guiButtonClickHandler3 = button3 -> {
         // HARDENED: router UPnP ForceTermination POST (http://fritz.box:49000) disabled.
         // This button is intentionally inert; it could terminate the WAN connection.
      };
      this.addButton((byte)104, (short)154, n, (byte)20, "Reconnect", guiButtonClickHandler3);
      GuiButtonClickHandler guiButtonClickHandler4 = button4 -> this.onClipboardLoginClick(button4);
      this.addButton((byte)104, (short)179, n, (byte)20, "Clipboard login", guiButtonClickHandler4);
      GuiButtonClickHandler guiButtonClickHandler5 = button5 -> this.onDeviceCodeLoginClick(button5);
      this.addButton((byte)104, (short)204, n, (byte)20, "Device code login", guiButtonClickHandler5);
      GuiButtonClickHandler guiButtonClickHandler6 = button6 -> this.onExternalBrowserLoginClick(button6);
      this.addButton((byte)104, (short)229, n, (byte)20, "External browser login", guiButtonClickHandler6);
      GuiButtonClickHandler guiButtonClickHandler7 = button7 -> this.onDeleteSelectedClick(button7);
      this.addButton((byte)104, (short)254, n, (byte)20, "Delete selected Alt", guiButtonClickHandler7);
      StringBuilder sb = new StringBuilder().append("Remove invalid: ");
      String str12;
      if (!(Boolean)this.removeInvalidAccounts.getValue()) {
         str12 = "§cFalse";
      } else {
         short s7 = 10780;
         str12 = "§aTrue";
      }

      this.addButton((byte)104, (short)279, n, (byte)20, sb.append(str12).toString(), button8 -> this.onToggleRemoveInvalidClick(button8));
      GuiButtonClickHandler guiButtonClickHandler9 = button9 -> {
         try {
            Desktop.getDesktop().browse(new URI("https://alts.fernan.club/"));
         } catch (URISyntaxException | IOException e2) {
            e2.printStackTrace();
         }
      };
      this.addButton((byte)104, (short)304, n, (byte)20, "§cFernan's Altshop", guiButtonClickHandler9);
      int n2 = this.width - 5;
      int height2 = this.height;
      int width2 = this.width;
      scrollBar.setBounds(n2, (byte)52, width2, height2);
      scrollBar.setItemCount(accounts.size());
   }

   public void loadSavedServerList() {

      try {
         this.savedServers.clear();
         NBTTagCompound tag = CompressedStreamTools.read(new File(this.mc.mcDataDir, "servers.dat"));
         if (tag == null) {
            return;
         }

         NBTTagList tagList = tag.getTagList("servers", 10);

         for (int i = 0; i < tagList.tagCount(); i++) {
            this.savedServers.add(ServerData.getServerDataFromNBTCompound(tagList.getCompoundTagAt(i)));
         }
      } catch (Exception e) {
      }
   }

   private void onExternalBrowserLoginClick(GuiButton button) {
      this.startExternalBrowserLogin();
   }

   public void setActiveLoginTask(CompletableFuture completableFuture) {
      this.activeLoginTask = completableFuture;
   }

   private TuplePair loadServerIconTexture(ServerData serverData, ResourceLocation location) {
      DynamicTexture dynamicTexture = null;
      if (serverData.getBase64EncodedIconData() == null) {
         this.mc.getTextureManager().deleteTexture(location);
         dynamicTexture = null;
      } else {
         ByteBuf buf = Unpooled.copiedBuffer(serverData.getBase64EncodedIconData(), Charsets.UTF_8);
         ByteBuf buf2 = Base64.decode(buf);

         BufferedImage image;
         try {
            image = TextureUtil.readBufferedImage(new ByteBufInputStream(buf2));
            Validate.validState(image.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
            Validate.validState(image.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
         } catch (Throwable e) {
            serverData.setBase64EncodedIconData((String)null);
            return new TuplePair(location, null);
         } finally {
            buf.release();
            buf2.release();
         }

         dynamicTexture = new DynamicTexture(image.getWidth(), image.getHeight());
         this.mc.getTextureManager().loadTexture(location, dynamicTexture);

         try {
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), dynamicTexture.getTextureData(), 0, image.getWidth());
         } catch (Throwable e2) {
         }

         dynamicTexture.updateDynamicTexture();
      }

      return new TuplePair(location, dynamicTexture);
   }

   private static void loginClipboardAccessToken(String str) {
      AccountAuthenticator.loginWithAccessToken(str);
   }

   private static void loginSplitCredentials(String[] strings) {
      AccountAuthenticator.loginMicrosoftCredentials(strings[0], strings[1]);
   }

   private int drawServerIconRow(Collection items, int n, int n2, int n3, int n4, int n5) {
      int n6 = 0;
      int n8 = n * this.countLoadedServerIcons(items);

      for (TuplePair tuplePair : (Iterable<TuplePair>)(items)) {
         if (tuplePair.getSecond() != null) {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            this.drawDetailServerIcon((int)((float)n2 + (float)(n4 - n2) / 2.0F) - n8 / 2 + n6, n3 + n5, (ResourceLocation)tuplePair.getFirst());
            n6 += n;
         }
      }

      return n5 + 29;
   }

   public void startExternalBrowserLogin() {
      CompletableFuture.runAsync(() -> AccountAuthenticator.loginExternalBrowser());
   }

   private static void loginClipboardAlteningToken(String[] strings) {
      AccountAuthenticator.loginTheAlteningToken(strings[0]);
   }

   public CompletableFuture getActiveLoginTask() {
      return this.activeLoginTask;
   }

   private void onClipboardLoginClick(GuiButton button) {
      this.loginFromClipboard();
   }

   private void onLoginButtonClick(GuiButton button) {
      this.performFieldLogin();
   }

   private void onGenerateAlteningClick(GuiButton button) {
      this.generateAlteningAccount();
   }

   private void onToggleRemoveInvalidClick(GuiButton button) {
      BooleanProperty removeInvalidAccounts2 = this.removeInvalidAccounts;
      Boolean bool = !(Boolean)this.removeInvalidAccounts.getValue();
      removeInvalidAccounts2.setValue(bool);
      StringBuilder sb = new StringBuilder().append("Remove invalid: ");
      String str;
      if (!(Boolean)this.removeInvalidAccounts.getValue()) {
         str = "§cFalse";
      } else {
         short s = 20679;
         str = "§aTrue";
      }

      button.displayString = sb.append(str).toString();
   }

   private void onDeviceCodeLoginClick(GuiButton button) {
      this.startDeviceCodeLogin();
   }

   @Override
   public void drawContent(int n, int n2, float f) {
      super.drawContent(n, n2, f);
      FontRenderer fontRendererObj2 = this.fontRendererObj;
      ScaledResolution sr = new ScaledResolution(this.mc);
      int n3 = sr.getScaledWidth() - 102;
      int n4 = (int)Math.max(118.0F, Math.min((float)sr.getScaledWidth() / 2.0F - 100.0F - 20.0F, 150.0F));
      if (this.emailOrTokenField != null && this.emailOrTokenField.getText().isEmpty() && !this.emailOrTokenField.isFocused()) {
         fontRendererObj2.drawStringWithShadow("Email / Email:Password", 109.0F, 60.0F, Color.gray.getRGB());
      }

      if (this.passwordField != null && this.passwordField.getText().isEmpty() && !this.passwordField.isFocused()) {
         fontRendererObj2.drawStringWithShadow("Password", 109.0F, 85.0F, Color.gray.getRGB());
      }

      int n5 = Math.min(n3 - n4 - 20, 300);
      int n6 = sr.getScaledWidth() - 10 - n5;
      int clampedScrollOffset = (int)scrollBar.getClampedScrollOffset();

      for (AccountEntry accountEntry : (Iterable<AccountEntry>)(accounts)) {
         if (accountEntry.isMarked()) {
            accountEntry.drawEntry(n6, 57 + clampedScrollOffset, n5, (byte)40, n, n2, accountEntry == this.selectedAccount, this.savedServers);
            clampedScrollOffset += 42;
         }
      }

      for (AccountEntry accountEntry2 : (Iterable<AccountEntry>)(accounts)) {
         if (!accountEntry2.isMarked()) {
            accountEntry2.drawEntry(n6, 57 + clampedScrollOffset, n5, (byte)40, n, n2, accountEntry2 == this.selectedAccount, this.savedServers);
            clampedScrollOffset += 42;
         }
      }

      if (n3 - n4 - 330 > 300 && this.selectedAccount != null) {
         int n11 = n4 + 102 + 10;
         byte b3 = 57;
         int n12 = n11 + n3 - n4 - 330;
         int n13 = b3 + 170;
         RenderUtil.drawRect(n11, b3, n12, n13, new Color(0, 0, 0, 100).getRGB());
         RenderUtil.scissorRect((float)(n11 + 5), (float)(b3 + 5), (float)(n12 - 5), (float)(n13 - 5));
         int n14 = 0;
         n14 = this.drawCenteredDetailLine("§7Detailed information:", n11, b3, n12, n13, n14) + 5;
         n14 = this.drawCenteredDetailLine("§7Name: §f" + this.selectedAccount.getUsername(), n11, b3, n12, n13, n14);
         n14 = this.drawCenteredDetailLine("§7Login type: §f" + this.selectedAccount.getLoginType().getDisplayName(), n11, b3, n12, n13, n14);
         StringBuilder sb = new StringBuilder();
         short s = 2334;
         StringBuilder sb2 = sb.append("§7Account state: §f");
         String str4;
         if (this.selectedAccount.isWorking()) {
            str4 = "§aFunctional";
         } else {
            s = 6852;
            str4 = "§cInvalid";
         }

         n14 = this.drawCenteredDetailLine(sb2.append(str4).toString(), n11, b3, n12, n13, n14);
         DateFormat dateInstance = DateFormat.getDateInstance(2);
         DateFormat timeInstance = DateFormat.getTimeInstance(3);
         String str6 = dateInstance.format(this.selectedAccount.getLastLoginTime());
         String str7 = timeInstance.format(this.selectedAccount.getLastLoginTime());
         n14 = this.drawCenteredDetailLine("§7Last login time: §f" + str6 + " " + str7, n11, b3, n12, n13, n14);
         n14 = this.drawCenteredDetailLine("§7Playtime: §f" + this.selectedAccount.getPlayTimeMillis() / 1000L / 60L + " min", n11, b3, n12, n13, n14);
         if (this.countLoadedServerIcons(this.selectedAccount.getBannedServerIcons().values()) > 0) {
            n14 = this.drawCenteredDetailLine("§7Banned on servers:", n11, b3, n12, n13, n14) + 6;
            n14 = this.drawServerIconRow(this.selectedAccount.getBannedServerIcons().values(), (byte)35, n11, b3, n12, n14);
         }

         if (this.countLoadedServerIcons(this.selectedAccount.getUnbannedServerIcons().values()) > 0) {
            n14 = this.drawCenteredDetailLine("§7Unbanned on servers:", n11, b3, n12, n13, n14) + 6;
            this.drawServerIconRow(this.selectedAccount.getUnbannedServerIcons().values(), (byte)35, n11, b3, n12, n14);
         }
      }

      scrollBar.updateAndDraw();
   }

   public void startDeviceCodeLogin() {
      CompletableFuture.runAsync(() -> AccountAuthenticator.loginDeviceCode());
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);

      for (AccountEntry accountEntry : (Iterable<AccountEntry>)(accounts)) {
         accountEntry.mouseClicked(mouseX, mouseY, mouseButton);
         if ((mouseButton == 1 || mouseButton == 0) && RenderUtil.isHovered((float)accountEntry.getRenderX(), (float)accountEntry.getRenderY(), (float)accountEntry.getRenderWidth(), (float)accountEntry.getRenderHeight(), mouseX, mouseY)
            )
          {
            this.selectedAccount = accountEntry;
         }
      }

      double d = (double)mouseX;
      double d3 = (double)mouseY;
      scrollBar.handleMouseClick(d, d3);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if ((keyCode == 211 || keyCode == 14) && this.selectedAccount != null && !this.emailOrTokenField.isFocused() && !this.passwordField.isFocused()) {
         accounts.remove(this.selectedAccount);
         scrollBar.setItemCount(accounts.size());
         this.selectedAccount = null;
      }
   }

   private void drawDetailServerIcon(int n, int n2, ResourceLocation location) {
      this.mc.getTextureManager().bindTexture(location);
      GlStateManager.enableBlend();
      Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0F, 0.0F, 30, 30, 30.0F, 30.0F);
      GlStateManager.disableBlend();
   }

   private void generateAlteningAccount() {
      String clipboardString = getClipboardString();
      if (clipboardString.contains("api-")) {
         this.apiKey.setValue(clipboardString);
      }

      if (this.activeLoginTask == null || this.activeLoginTask.isDone()) {
         String value = (String)this.apiKey.getValue();
         if (value != null && !value.isEmpty()) {
            this.setActiveLoginTask(CompletableFuture.runAsync(() -> loginAlteningApiKey(value)));
         }
      }
   }

   static {
      scrollBar = new ScrollBar(42);
   }

   private int countLoadedServerIcons(Collection items) {
      int n = 0;

      for (TuplePair tuplePair : (Iterable<TuplePair>)(items)) {
         if (tuplePair.getSecond() != null) {
            n++;
         }
      }

      return n;
   }

   private static void loginInputAlteningToken(String str) {
      AccountAuthenticator.loginTheAlteningToken(str);
   }

   public AccountManagerScreen() {
       super("Account Manager");
      this.activeLoginTask = null;
      this.savedServers = Lists.newArrayList();
      this.apiKey = new StringProperty(ClientSettingsConfigFile.abstractFeature, "API-Key", "");
      this.removeInvalidAccounts = new BooleanProperty(ClientSettingsConfigFile.abstractFeature, "RemoveInvalidAccounts", false);
      instance = this;
      this.loadRandomNames();
   }

   private int drawCenteredDetailLine(String str, int n, int n2, int n3, int n4, int n5) {
      this.fontRendererObj
         .drawString(
            str,
            (float)n + (float)(n3 - n) / 2.0F - (float)this.fontRendererObj.getStringWidth(str) / 2.0F,
            (float)(n2 + 5 + n5),
            Color.white.getRGB()
         );
      return n5 + 11;
   }

}
