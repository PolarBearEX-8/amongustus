package client.gui;

import augustus.modules.render.ServerPinger;
import client.Client;
import client.event.CanTResolveHostnameListener;
import client.event.PostRenderInterceptor;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.ClientSettingsBlackType;
import client.setting.ServerPingerIncolumitasType;
import client.util.AnglePredictor;
import client.util.CoreListEntry;
import client.util.JavaNamingFactoryInitialManager;
import client.util.SmoothReader;
import client.util.SplineDeserializer;
import client.util.TimeHelper;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.item.IronCrown;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class AltGuiScreenAdapter extends GuiScreen {
   public static final CoreListEntry coreListEntry = new CoreListEntry(250, 80);
   public final PostRenderInterceptor postRenderInterceptor;
   public final ServerData serverData;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public final TimeHelper timer = new TimeHelper();
   public String labelText;
   public CanTResolveHostnameListener canTResolveHostnameListener;
   public List list4;

   private ServerPinger getServerPinger() {
      return Client.getClient().getModuleManager().serverPinger;
   }

   @Override
   public void updateState2() {
      Keyboard.enableRepeatEvents(true);
      this.list.clear();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 100, this.height / 4 + 96 + 12, StatCollector.getText("selectServer.select", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 100, this.height / 4 + 120 + 12, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, 5, 5, 90, 20, "Resolve SRV"));
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(3, 5, 28, 90, 20, "Flush DNS Cache"));
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(2, this.fontRenderer, this.width / 2 - 100, 116, 200, 20);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(128);
      this.internalTexturesGuiIconsPngManagerAdapter.setEnabledByFlag(true);
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(this.mc.gameSettings.text68);
      ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).enabled3 = !this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty()
         && this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().split(":").length > 0;
      int count = this.height - 80 - 5;
      byte byteValue = 15;
      coreListEntry.applyIndex(byteValue, count);
      CoreListEntry coreListEntry2 = coreListEntry;
      int value = (int)this.getServerPinger().settingMaxEntries.getValue();
      coreListEntry2.setCount(value);
      this.applyText2(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText(), true);
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
      this.mc.gameSettings.text68 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
      this.mc.gameSettings.updateState();
   }

   private String resolveText(String text) {
      try {
         JavaNamingFactoryInitialManager javaNamingFactoryInitialManager = JavaNamingFactoryInitialManager.resolveJavaNamingFactoryInitialMana(text);
         return InetAddress.getByName(javaNamingFactoryInitialManager.toASCII()).getHostAddress() + ":" + javaNamingFactoryInitialManager.getCount();
      } catch (UnknownHostException unknownHostException) {
         return null;
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.postRenderInterceptor.applyFlag(false, 0);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            this.serverData.text = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
            this.postRenderInterceptor.applyFlag(true, 0);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            Toolkit.getDefaultToolkit()
               .getSystemClipboard()
               .setContents(new StringSelection(this.resolveText(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText())), null);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            AddressCacheCacheHelper.renderElement();
         }
      }
   }

   private void applyText(String text, ServerData serverData2) {
      try {
         String text2 = ClientSettingsBlackType.resolveText(serverData2.labelText5);
         List list = (List)this.getServerPinger().entryMap.computeIfAbsent(text, (item -> AltGuiScreenAdapter.resolveList((String)item)));
         list.add(Float.parseFloat(text2.split("/")[0]));
         coreListEntry.setList(list);
      } catch (Exception exception) {
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count)) {
         ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).enabled3 = !this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().isEmpty()
            && this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().split(":").length > 0;
         this.timer.reset();
      } else if (count == 28 || count == 156) {
         this.applyAuxiliaryTexturesGuiIconsP((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0));
      }

      if (count == 1) {
         GuiScreen postRenderInterceptor2 = (GuiScreen)this.postRenderInterceptor;
         this.mc.displayGuiScreen(postRenderInterceptor2);
      }
   }

   private void applyText2(String text, boolean flag) {
      if (this.getServerPinger().settingServerWidgetEnabled.isEnabled()) {
         this.serverData.text = text;
         this.serverData.enabled = false;
         this.canTResolveHostnameListener = new CanTResolveHostnameListener(this.postRenderInterceptor, this.serverData);
         this.canTResolveHostnameListener.setConsumer(serverData -> this.applyText(text, (ServerData)serverData));
      }

      if (((Module)this.getServerPinger()).isToggled() && this.getServerPinger().settingIpInfoEnabled.isEnabled() && flag && !text.isEmpty()) {
         ServerPingerIncolumitasType serverPingerIncolumitasType = Arrays.stream(ServerPingerIncolumitasType.values())
            .filter(this::matchesServerPingerIncolumitasTyp)
            .findFirst()
            .orElse(ServerPingerIncolumitasType.INCOLUMITAS);
         CompletableFuture.supplyAsync(() -> this.resolveTextByText(text)).thenAccept(resolved -> this.applyServerPingerIncolumitasTyp(serverPingerIncolumitasType, text, (String)resolved));
      }

      if (this.getServerPinger().settingPlayerGraphEnabled.isEnabled()) {
         if (!this.getServerPinger().settingKeepPlayerData.isEnabled()) {
            this.getServerPinger().entryMap.clear();
         }

         List orDefault = ((List)this.getServerPinger().entryMap.getOrDefault(text, new ArrayList()));
         coreListEntry.setList(orDefault);
         CoreListEntry coreListEntry2 = coreListEntry;
         int value = (int)this.getServerPinger().settingMaxEntries.getValue();
         coreListEntry2.setCount(value);
      }
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
      if (((Module)this.getServerPinger()).isToggled()) {
         TimeHelper timer2 = this.timer;
         double value = this.getServerPinger().settingPingDelay.getValue();
         if (timer2.hasReachedDouble(value)) {
            String labelText2 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
            boolean flag = this.labelText == null || !this.labelText.equals(labelText2);
            if (this.getServerPinger().settingPlayerGraphEnabled.isEnabled() || flag) {
               this.applyText2(labelText2, flag);
               this.labelText = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
            }

            this.timer.reset();
         }
      }
   }

   private String resolveTextByText(String text) {
      return this.resolveText(text);
   }

   private void applyServerPingerIncolumitasTyp(ServerPingerIncolumitasType serverPingerIncolumitasType, String text, String text2) {
      String text3 = text2 != null ? JavaNamingFactoryInitialManager.resolveJavaNamingFactoryInitialMana(text2).toASCII() : text;
      serverPingerIncolumitasType.supplyAsync(text3).whenComplete((ironCrown, throwable) -> this.applyIronCrown((IronCrown)ironCrown, (Throwable)throwable));
   }

   private boolean matchesServerPingerIncolumitasTyp(ServerPingerIncolumitasType serverPingerIncolumitasType) {
      return serverPingerIncolumitasType.getText().equals(this.getServerPinger().settingBackend.getSelected());
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
      if (((Module)this.getServerPinger()).isToggled() && this.getServerPinger().settingServerWidgetEnabled.isEnabled()) {
         if (this.canTResolveHostnameListener != null) {
            int count4 = this.width / 2 - 152;
            if (count >= count4 && count <= count4 + 305 && count2 >= 30 && count2 <= 66) {
               this.applyText2(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText(), true);
            }
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("selectServer.direct", new Object[0]), this.width / 2, 20, 16777215);
      this.applyFontRenderer(this.fontRenderer, StatCollector.getText("addServer.enterIp", new Object[0]), this.width / 2 - 100, 100, 10526880);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      super.applyIndex(count, count2, factor);
      if (((Module)this.getServerPinger()).isToggled()) {
         if (this.getServerPinger().settingServerWidgetEnabled.isEnabled() && this.canTResolveHostnameListener != null) {
            int count3 = this.width / 2 - 152;
            this.postRenderInterceptor.setLabelText(null);
            GL11.glPushMatrix();
            GL11.glTranslated((double)count3, 30.0, 0.0);
            RenderUtils.processAction(0, 0, 305, 34, this.getServerPinger().settingBackgroundColor.getInt12(0));
            this.canTResolveHostnameListener.applyIndex(0, 0, 0, 309, 34, count - count3, count2 - 30, false);
            double offset = (double)this.timer.getTime() / this.getServerPinger().settingPingDelay.getValue() * 305.0;
            RenderUtils.processAction(0, 32, (int)offset, 34, this.getServerPinger().settingColor.getInt12(0));
            GL11.glPopMatrix();
         }

         if (this.getServerPinger().settingIpInfoEnabled.isEnabled() && this.list4 != null) {
            int count4 = this.internalTexturesGuiIconsPngManagerAdapter.count10;
            int count5 = this.width / 4;
            RenderUtils.processAction(15, count4, 15 + count5, count4 + 220, this.getServerPinger().settingBoxColor.getInt12(0));
            GL11.glEnable(3089);
            RenderUtils.drawBorderedRect(15.0F, (float)count4, (float)(15 + count5), (float)(count4 + 220));

            for (int index = 0; index < this.list4.size(); index++) {
               String text = (String)this.list4.get(index);
               if (text != null) {
                  this.fontRenderer
                     .getIndex(text, 20, count4 + 5 + index * 10, text.endsWith(":") ? this.getServerPinger().settingTextColor.getInt12(0) : -1);
               }
            }

            GL11.glDisable(3089);
         }

         if (this.getServerPinger().settingPlayerGraphEnabled.isEnabled()) {
            coreListEntry.applyIndex(count, count2, factor);
         }

         this.postRenderInterceptor.applyGuiScreen(this, count, count2);
      }
   }

   private void applyIronCrown(IronCrown ironCrown, Throwable throwable) {
      if (throwable != null) {
         this.list4 = null;
      } else {
         this.list4 = new ArrayList();
         this.list4.add("Query:");
         this.list4.add(ironCrown.labelText + " (Managed by " + ironCrown.labelText2 + ")");
         AnglePredictor anglePredictor = ironCrown.anglePredictor;
         this.list4.add(anglePredictor.labelText4);
         this.list4.add("");
         this.list4.add("Company/" + anglePredictor.labelText3 + ":");
         this.list4.add(anglePredictor.labelText);
         this.list4.add("");
         this.list4.add("Network:");
         this.list4.add(anglePredictor.labelText2);
         this.list4.add("");
         SmoothReader smoothReader = ironCrown.smoothReader;
         this.list4.add("ASN:");
         this.list4.add(smoothReader.count + " (" + smoothReader.labelText2 + ")");
         this.list4.add("");
         this.list4.add("ASN Route:");
         this.list4.add(smoothReader.labelText3);
         this.list4.add("");
         this.list4.add("Net Description:");
         this.list4.add(smoothReader.labelText);
         this.list4.add("");
         SplineDeserializer splineDeserializer = ironCrown.splineDeserializer;
         this.list4.add("Location:");
         this.list4.add(splineDeserializer.labelText2 + ", " + splineDeserializer.labelText3 + ", " + splineDeserializer.labelText);
      }
   }

   private static List resolveList(String text) {
      return new ArrayList();
   }

   public AltGuiScreenAdapter(PostRenderInterceptor postRenderInterceptor2, ServerData serverData2) {
      this.postRenderInterceptor = postRenderInterceptor2;
      this.serverData = serverData2;
   }
}
