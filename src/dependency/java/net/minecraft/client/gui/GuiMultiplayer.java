package net.minecraft.client.gui;

import augustus.modes.movement.MovementLanserverdetectorMode;
import augustus.modules.misc.ClientSettings;
import client.Client;
import client.event.CanTResolveHostnameListener;
import client.event.PostRenderInterceptor;
import client.event.PreMotionObserver;
import client.event.PreUpdateDispatcher;
import client.gui.AltGuiScreenAdapter;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.DefaultGuiScreenAdapter;
import client.gui.SupplementalGuiScreenAdapter;
import client.util.AccountProfile;
import client.util.CancelledManager;
import client.util.DiscordRpcWorker;
import client.util.DistanceAdapter;
import client.util.IconManager;
import client.util.InternalClientManagerProvider;
import client.util.InterpolationDispatcher;
import client.util.LanServerScanningManager;
import client.util.ListContainer;
import client.util.ServersManager;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.item.ServiceItemEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiMultiplayer extends GuiScreen implements PostRenderInterceptor, PreMotionObserver {
   public static final ResourceLocation resourceLocation7 = new ResourceLocation("augustus", "pictures/true.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("augustus", "pictures/false.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("augustus", "pictures/unknown.png");
   public static final Logger logger2 = LogManager.getLogger();
   public final CancelledManager cancelledManager = new CancelledManager();
   public InternalClientManagerProvider internalClientManagerProvider;
   public ServersManager serversManager;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public boolean enabled10;
   public boolean enabled9;
   public boolean enabled7;
   public boolean enabled8;
   public String labelText;
   public ServerData serverData;
   public ListContainer listContainer;
   public MovementLanserverdetectorMode movementLanserverdetectorMode;
   public boolean enabled6;

   @Override
   public void updateState2() {
      ServiceItemEntry serviceItemEntry = IconManager.getServiceItemEntry();
      serviceItemEntry.updateState();
      DiscordRpcWorker threadEntry = Client.getClient().getThreadEntry();
      String text = "Multiplayer Menu";
      String text2 = "Idle";
      threadEntry.applyString(text2, text);
      Keyboard.enableRepeatEvents(true);
      this.list.clear();
      if (!this.enabled6) {
         this.enabled6 = true;
         this.serversManager = new ServersManager(this.mc);
         this.serversManager.updateState2();
         this.listContainer = new ListContainer();

         try {
            this.movementLanserverdetectorMode = new MovementLanserverdetectorMode(this.listContainer);
            this.movementLanserverdetectorMode.start();
         } catch (Exception exception) {
            logger2.warn("Unable to start LAN server detection: " + exception.getMessage());
         }

         this.internalClientManagerProvider = new InternalClientManagerProvider(this, this.mc, this.width, this.height, 32, this.height - 64, 36);
         this.internalClientManagerProvider.applyServersManager(this.serversManager);
      } else {
         this.internalClientManagerProvider.applyIndex(this.width, this.height, 32, this.height - 64);
      }

      this.renderElement6();
   }

   @Override
   public ServersManager getServersManager() {
      return this.serversManager;
   }

   @Override
   public void applyIndex(int count) {
      this.internalClientManagerProvider.setCount20(count);
      PreUpdateDispatcher preUpdateDispatcher = count < 0 ? null : this.internalClientManagerProvider.get(count);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = false;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = false;
      if (preUpdateDispatcher != null && !(preUpdateDispatcher instanceof LanServerScanningManager)) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = true;
         if (preUpdateDispatcher instanceof CanTResolveHostnameListener) {
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = true;
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = true;
         }
      }
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
      if (this.movementLanserverdetectorMode != null) {
         this.movementLanserverdetectorMode.interrupt();
         this.movementLanserverdetectorMode = null;
      }

      this.cancelledManager.updateState2();
   }

   @Override
   public void applyCanTResolveHostnameListene(CanTResolveHostnameListener canTResolveHostnameListener, int count, boolean flag) {
      int count2 = flag ? 0 : count - 1;
      this.serversManager.applyIndex(count, count2);
      if (this.internalClientManagerProvider.getCount3() == count) {
         this.applyIndex(count2);
      }

      this.internalClientManagerProvider.applyServersManager(this.serversManager);
   }

   @Override
   public boolean matchesCanTResolveHostnameListene(CanTResolveHostnameListener canTResolveHostnameListener, int count) {
      return count > 0;
   }

   private void renderElement5() {
      Minecraft mc = this.mc;
      GuiMultiplayer guiMultiplayer = new GuiMultiplayer(this.guiScreen);
      mc.displayGuiScreen(guiMultiplayer);
   }

   @Override
   public void applyGuiScreen(GuiScreen screen, int count, int count2) {
      if (this.labelText != null) {
         screen.applyList(Lists.newArrayList(Splitter.on("\n").split(this.labelText)), count, count2);
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         PreUpdateDispatcher preUpdateDispatcher = this.internalClientManagerProvider.getCount3() < 0
            ? null
            : this.internalClientManagerProvider.get(this.internalClientManagerProvider.getCount3());
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2 && preUpdateDispatcher instanceof CanTResolveHostnameListener) {
            String labelText2 = ((CanTResolveHostnameListener)preUpdateDispatcher).getServerData().labelText3;
            if (labelText2 != null) {
               this.enabled10 = true;
               String text = StatCollector.getText("selectServer.deleteQuestion", new Object[0]);
               String text2 = "'" + labelText2 + "' " + StatCollector.getText("selectServer.deleteWarning", new Object[0]);
               String text3 = StatCollector.getText("selectServer.deleteButton", new Object[0]);
               String text4 = StatCollector.getText("gui.cancel", new Object[0]);
               DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(this, text, text2, text3, text4, this.internalClientManagerProvider.getCount3());
               this.mc.displayGuiScreen(defaultGuiScreenAdapter);
            }
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.renderElement();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4) {
            this.enabled8 = true;
            Minecraft mc = this.mc;
            AltGuiScreenAdapter altGuiScreenAdapter = new AltGuiScreenAdapter(
               this, this.serverData = new ServerData(StatCollector.getText("selectServer.defaultName", new Object[0]), "", false)
            );
            mc.displayGuiScreen(altGuiScreenAdapter);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            this.enabled9 = true;
            Minecraft mc2 = this.mc;
            SupplementalGuiScreenAdapter supplementalGuiScreenAdapter = new SupplementalGuiScreenAdapter(
               this, this.serverData = new ServerData(StatCollector.getText("selectServer.defaultName", new Object[0]), "", false)
            );
            mc2.displayGuiScreen(supplementalGuiScreenAdapter);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 7 && preUpdateDispatcher instanceof CanTResolveHostnameListener) {
            this.enabled7 = true;
            ServerData serverData2 = ((CanTResolveHostnameListener)preUpdateDispatcher).getServerData();
            this.serverData = new ServerData(serverData2.labelText3, serverData2.text, false);
            this.serverData.applyServerData(serverData2);
            Minecraft mc3 = this.mc;
            SupplementalGuiScreenAdapter supplementalGuiScreenAdapter2 = new SupplementalGuiScreenAdapter(this, this.serverData);
            mc3.displayGuiScreen(supplementalGuiScreenAdapter2);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 8) {
            this.renderElement5();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == -1338
            && preUpdateDispatcher instanceof CanTResolveHostnameListener
            && Minecraft.getMinecraft().getSession() != null
            && Minecraft.getMinecraft().getSession().getKeybindAnimationEntry() != null) {
            CanTResolveHostnameListener canTResolveHostnameListener = (CanTResolveHostnameListener)preUpdateDispatcher;
            ServerData serverData3 = canTResolveHostnameListener.getServerData();
            if (serverData3 != null) {
               AccountProfile keybindAnimationEntry = Minecraft.getMinecraft().getSession().getKeybindAnimationEntry();
               String text5 = serverData3.text;
               Long value = 0L;
               String text6 = text5;
               keybindAnimationEntry.applyText(text6, value);
            }
         }
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      int count2 = this.internalClientManagerProvider.getCount3();
      PreUpdateDispatcher preUpdateDispatcher = count2 < 0 ? null : this.internalClientManagerProvider.get(count2);
      if (count >= 2 && count <= 10) {
         int count3 = count - 2;
         if (count3 < this.internalClientManagerProvider.size()) {
            this.applyIndex(count3);
         }
      }

      if (count == 63) {
         this.renderElement5();
      } else if (count2 >= 0) {
         if (count == 200) {
            if (isActiveAsFlag()) {
               if (count2 > 0 && preUpdateDispatcher instanceof CanTResolveHostnameListener) {
                  this.serversManager.applyIndex(count2, count2 - 1);
                  this.applyIndex(this.internalClientManagerProvider.getCount3() - 1);
                  this.internalClientManagerProvider.applyIndex(-this.internalClientManagerProvider.getCount5());
                  this.internalClientManagerProvider.applyServersManager(this.serversManager);
               }
            } else if (count2 > 0) {
               this.applyIndex(this.internalClientManagerProvider.getCount3() - 1);
               this.internalClientManagerProvider.applyIndex(-this.internalClientManagerProvider.getCount5());
               if (this.internalClientManagerProvider.get(this.internalClientManagerProvider.getCount3()) instanceof LanServerScanningManager
                  && this.internalClientManagerProvider.getCount3() > 0) {
                  this.applyIndex(this.internalClientManagerProvider.size() - 1);
                  this.internalClientManagerProvider.applyIndex(-this.internalClientManagerProvider.getCount5());
               }
            }
         } else if (count == 208) {
            if (isActiveAsFlag()) {
               if (count2 < this.serversManager.size() - 1) {
                  this.serversManager.applyIndex(count2, count2 + 1);
                  this.applyIndex(count2 + 1);
                  this.internalClientManagerProvider.applyIndex(this.internalClientManagerProvider.getCount5());
                  this.internalClientManagerProvider.applyServersManager(this.serversManager);
               }
            } else if (count2 < this.internalClientManagerProvider.size() - 1) {
               this.applyIndex(this.internalClientManagerProvider.getCount3() + 1);
               this.internalClientManagerProvider.applyIndex(this.internalClientManagerProvider.getCount5());
               if (this.internalClientManagerProvider.get(this.internalClientManagerProvider.getCount3()) instanceof LanServerScanningManager
                  && this.internalClientManagerProvider.getCount3() < this.internalClientManagerProvider.size() - 1) {
                  this.applyIndex(this.internalClientManagerProvider.size() + 1);
                  this.internalClientManagerProvider.applyIndex(this.internalClientManagerProvider.getCount5());
               }
            }
         } else if (count != 28 && count != 156) {
            super.applyChar(character, count);
         } else {
            this.applyAuxiliaryTexturesGuiIconsP((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(2));
         }
      } else {
         super.applyChar(character, count);
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      super.applyIndex(count, count2, count3);
      this.internalClientManagerProvider.checkIndexByIndex(count, count2, count3);
   }

   @Override
   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   @Override
   public boolean matchesCanTResolveHostnameListeneByCanTResolveHostn(CanTResolveHostnameListener canTResolveHostnameListener, int count) {
      return count < this.serversManager.size() - 1;
   }

   @Override
   public void updateState4() {
      super.updateState4();
      if (this.listContainer.isEnabled()) {
         List list = this.listContainer.unmodifiableList();
         this.listContainer.updateState();
         this.internalClientManagerProvider.applyList(list);
      }

      this.cancelledManager.updateState();
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      PreUpdateDispatcher preUpdateDispatcher = this.internalClientManagerProvider.getCount3() < 0
         ? null
         : this.internalClientManagerProvider.get(this.internalClientManagerProvider.getCount3());
      if (this.enabled10) {
         this.enabled10 = false;
         if (flag && preUpdateDispatcher instanceof CanTResolveHostnameListener) {
            this.serversManager.remove(this.internalClientManagerProvider.getCount3());
            this.serversManager.updateState();
            this.internalClientManagerProvider.setCount20(-1);
            this.internalClientManagerProvider.applyServersManager(this.serversManager);
         }

         this.mc.displayGuiScreen(this);
      } else if (this.enabled8) {
         this.enabled8 = false;
         if (flag) {
            this.applyServerData(this.serverData);
         } else {
            this.mc.displayGuiScreen(this);
         }
      } else if (this.enabled9) {
         this.enabled9 = false;
         if (flag) {
            this.serversManager.add(this.serverData);
            this.serversManager.updateState();
            this.internalClientManagerProvider.setCount20(-1);
            this.internalClientManagerProvider.applyServersManager(this.serversManager);
         }

         this.mc.displayGuiScreen(this);
      } else if (this.enabled7) {
         this.enabled7 = false;
         if (flag && preUpdateDispatcher instanceof CanTResolveHostnameListener) {
            ServerData serverData2 = ((CanTResolveHostnameListener)preUpdateDispatcher).getServerData();
            serverData2.labelText3 = this.serverData.labelText3;
            serverData2.text = this.serverData.text;
            serverData2.applyServerData(this.serverData);
            this.serversManager.updateState();
            this.internalClientManagerProvider.applyServersManager(this.serversManager);
         }

         this.mc.displayGuiScreen(this);
      }
   }

   @Override
   public void applyCanTResolveHostnameListeneByCanTResolveHostn(CanTResolveHostnameListener canTResolveHostnameListener, int count, boolean flag) {
      int count2 = flag ? this.serversManager.size() - 1 : count + 1;
      this.serversManager.applyIndex(count, count2);
      if (this.internalClientManagerProvider.getCount3() == count) {
         this.applyIndex(count2);
      }

      this.internalClientManagerProvider.applyServersManager(this.serversManager);
   }

   private void applyServerData(ServerData serverData2) {
      Minecraft mc = this.mc;
      GuiConnecting guiConnecting = new GuiConnecting(this, this.mc, serverData2);
      mc.displayGuiScreen(guiConnecting);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      if (!IconManager.getServiceItemEntry().isActive()) {
         this.internalClientManagerProvider.updateState();
      }
   }

   @Override
   public void renderElement() {
      PreUpdateDispatcher preUpdateDispatcher = this.internalClientManagerProvider.getCount3() < 0
         ? null
         : this.internalClientManagerProvider.get(this.internalClientManagerProvider.getCount3());
      if (preUpdateDispatcher instanceof CanTResolveHostnameListener) {
         this.applyServerData(((CanTResolveHostnameListener)preUpdateDispatcher).getServerData());
      } else if (preUpdateDispatcher instanceof DistanceAdapter) {
         InterpolationDispatcher interpolationDispatcher = ((DistanceAdapter)preUpdateDispatcher).getInterpolationDispatcher();
         this.applyServerData(new ServerData(interpolationDispatcher.getLabelText(), interpolationDispatcher.getLabelText2(), true));
      }
   }

   @Override
   public CancelledManager getCancelledManager() {
      return this.cancelledManager;
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      if (!IconManager.getServiceItemEntry().checkIndex(count, count2, count3)) {
         this.internalClientManagerProvider.checkIndex(count, count2, count3);
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.labelText = null;
      this.renderElement3();
      this.internalClientManagerProvider.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("multiplayer.title", new Object[0]), this.width / 2, 20, 16777215);
      super.applyIndex(count, count2, factor);
      ClientSettings.processAction();
      ClientSettings.setActive4(false);
      this.applyGuiScreen(this, count, count2);
      IconManager.getServiceItemEntry().applyIndex(count, count2, factor);
   }

   public void renderElement6() {
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               7, this.width / 2 - 154, this.height - 28, 70, 20, StatCollector.getText("selectServer.edit", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               2, this.width / 2 - 76, this.height - 28, 70, 20, StatCollector.getText("selectServer.delete", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 154, this.height - 52, 100, 20, StatCollector.getText("selectServer.select", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               4, this.width / 2 - 50, this.height - 52, 100, 20, StatCollector.getText("selectServer.direct", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               3, this.width / 2 + 4 + 50, this.height - 52, 100, 20, StatCollector.getText("selectServer.add", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               8, this.width / 2 + 2, this.height - 28, 70, 20, StatCollector.getText("selectServer.refresh", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 + 80, this.height - 28, 74, 20, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(-1338, this.width / 2 + 4 + 80, 6, 70, 20, "Mark banned"));
      this.applyIndex(this.internalClientManagerProvider.getCount3());
   }

   public GuiMultiplayer(GuiScreen screen) {
      super(screen);
   }
}
