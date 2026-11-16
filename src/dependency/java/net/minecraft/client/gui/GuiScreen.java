package net.minecraft.client.gui;

import augustus.modules.misc.ClientSettings;
import client.Client;
import client.ClientGlobals;
import client.event.EventBusManager;
import client.event.EventGuiOpenCallback;
import client.event.EventSlotClick;
import client.event.EventSlotClickCallback;
import client.event.PreMotionObserver;
import client.gui.AltGuiScreenAdapter;
import client.gui.AuxiliaryClientScreenAdapter;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalClientScreenAdapter;
import client.gui.PrimaryGuiScreenAdapter;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Module;
import client.render.WorldRenderer;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import client.util.AuxiliaryDuplicateStatIdManagerAdapter;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.ClickEventActionManager;
import client.util.DampenerEstimator;
import client.util.DefaultVertexFormats;
import client.util.DuplicateStatIdManager;
import client.util.GeometryDispatcher;
import client.util.HoverEventActionManager;
import client.util.ModularStringEntry;
import client.util.MouseUtils;
import client.util.QueueEntry;
import client.util.SecondaryExceptionAdapter;
import client.util.SkippingEntityWithIdManager;
import client.util.SplinePredictor;
import client.util.StatCraftItemManager;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public abstract class GuiScreen extends TexturesGuiIconsPngManager implements PreMotionObserver {
   public static final Set set = new HashSet();
   public static final Logger logger = LogManager.getLogger();
   public static final Set set2 = Sets.newHashSet(new String[]{"http", "https"});
   public static final Splitter splitter = Splitter.on('\n');
   public Minecraft mc;
   public RenderItem renderItem;
   public int width;
   public int height;
   public List list = Lists.newArrayList();
   public List list2 = Lists.newArrayList();
   public boolean enabled;
   public FontRenderer fontRenderer;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public int indexOffset;
   public long timestamp;
   public int count;
   public URI uRI;
   public boolean state = false;
   public final GuiScreen guiScreen;
   public boolean enabled5 = false;
   public boolean enabled2 = false;
   public boolean enabled3 = false;
   public boolean enabled4 = false;

   public void updateState2() {
   }

   public void applyText(String text, int count2, int count3) {
      this.applyList(Arrays.asList(text), count2, count3);
   }

   public void applyItemStack(ItemStack stack, int count2, int count3) {
      if (!ClientGlobals.moduleManager.antiStrike.canExecute6()) {
         List list3 = stack.resolveList(this.mc.thePlayer, this.mc.gameSettings.state136);

         for (int index = 0; index < list3.size(); index++) {
            if (index == 0) {
               list3.set(index, stack.processAction8().rarityColor + (String)list3.get(index));
            } else {
               list3.set(index, ClientSettingsBlackType.GRAY + (String)list3.get(index));
            }
         }

         this.applyList(list3, count2, count3);
      }
   }

   public void applyIndex2(int count2) {
      ClientSettings clientSettings = Client.getClient().getModuleManager().clientSettings;
      if (((Module)clientSettings).isToggled() && clientSettings.settingCustomBackground.isEnabled()) {
         ModularStringEntry modularStringEntry = Client.getClient().getGoldenShard().getModularStringEntry();
         float factor = 1800.0F;
         modularStringEntry.applyFloat(factor);
      } else {
         GlStateManager.applyState3();
         GlStateManager.applyState20();
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         worldRenderer.getWorldRenderer(0.0, (double)this.height, 0.0)
            .getWorldRenderer6(0.0, (double)((float)this.height / 32.0F + (float)count2))
            .resolveWorldRenderer(64, 64, 64, 255)
            .updateState();
         worldRenderer.getWorldRenderer((double)this.width, (double)this.height, 0.0)
            .getWorldRenderer6((double)((float)this.width / 32.0F), (double)((float)this.height / 32.0F + (float)count2))
            .resolveWorldRenderer(64, 64, 64, 255)
            .updateState();
         worldRenderer.getWorldRenderer((double)this.width, 0.0, 0.0)
            .getWorldRenderer6((double)((float)this.width / 32.0F), (double)count2)
            .resolveWorldRenderer(64, 64, 64, 255)
            .updateState();
         worldRenderer.getWorldRenderer(0.0, 0.0, 0.0).getWorldRenderer6(0.0, (double)count2).resolveWorldRenderer(64, 64, 64, 255).updateState();
         tessellator.updateState();
      }
   }

   public void enableRepeatEvents() {
   }

   public static String getText() {
      try {
         Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
         if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return (String)contents.getTransferData(DataFlavor.stringFlavor);
         }
      } catch (Exception exception) {
      }

      return "";
   }

   public void applyText(String text, boolean flag) {
   }

   public void applyMinecraft(Minecraft mc2, int count2, int count3) {
      this.applyMinecraft2(mc2, count2, count3);
   }

   public static boolean checkIndexByIndex(int count2) {
      return count2 == 30 && isActive3() && !isActiveAsFlag() && !isActive2();
   }

   public void renderElement4() throws IOException {
      if (Mouse.isCreated()
         && (
            !((Module)ClientGlobals.moduleManager.guiMove).isToggled()
               || !ClientGlobals.moduleManager.guiMove.canExecute7()
               || !MouseUtils.checkCondition(0)
               || !MouseUtils.checkCondition(1)
               || !ClientGlobals.moduleManager.guiMove.canExecute10()
         )) {
         EventSlotClick eventSlotClick = EventSlotClickCallback.getEventSlotClick();
         EventBusManager.getDietrichEvents2().post(51, eventSlotClick);
         if (eventSlotClick.isEnabled()) {
            if (this.enabled2) {
               this.applyIndex4(0);
               this.enabled2 = false;
            }

            if (this.enabled4) {
               this.applyIndex4(1);
               this.enabled4 = false;
            }
         }

         for (int index = 0; index < eventSlotClick.getCount3(); index++) {
            this.applyIndexByIndex(0);
            this.applyIndex4(0);
         }

         for (int index2 = 0; index2 < eventSlotClick.getCount(); index2++) {
            this.applyIndexByIndex(1);
            this.applyIndex4(1);
         }

         while (Mouse.next()) {
            int eventButton = Mouse.getEventButton();
            boolean eventButtonState = Mouse.getEventButtonState();
            if (eventButton != 0 && eventButton != 1) {
               QueueEntry.updateState();
            }

            if (eventSlotClick.isEnabled()) {
               if (eventButton == 0) {
                  this.enabled5 = eventButtonState;
               } else if (eventButton == 1) {
                  this.enabled3 = eventButtonState;
               }
            } else {
               boolean flag = false;
               if (eventButton == 0 && this.enabled2 != this.enabled5) {
                  this.enabled5 = this.enabled2;
                  flag = true;
               }

               if (eventButton == 1 && this.enabled4 != this.enabled3) {
                  this.enabled3 = this.enabled4;
                  flag = true;
               }

               if (!flag) {
                  this.updateState3();
               }
            }
         }
      }

      if (Keyboard.isCreated()) {
         while (Keyboard.next()) {
            this.renderElement2();
         }
      }
   }

   public boolean matchesIChatComponent(IChatComponent iChatComponent) {
      if (iChatComponent == null) {
         return false;
      } else {
         ClickEventActionManager clickEventActionManager = iChatComponent.getStyleHasParentManager().getClickEventActionManager();
         if (isActiveAsFlag()) {
            if (iChatComponent.getStyleHasParentManager().getTextAsText() != null) {
               this.applyText(iChatComponent.getStyleHasParentManager().getTextAsText(), false);
            }
         } else if (clickEventActionManager != null) {
            if (clickEventActionManager.getClientSettingsOpenUrlType() == ClientSettingsOpenUrlType.OPEN_URL) {
               if (!this.mc.gameSettings.state13) {
                  return false;
               }

               try {
                  URI uri = new URI(clickEventActionManager.getLabelText());
                  String scheme = uri.getScheme();
                  if (scheme == null) {
                     throw new URISyntaxException(clickEventActionManager.getLabelText(), "Missing protocol");
                  }

                  if (!set2.contains(scheme.toLowerCase())) {
                     throw new URISyntaxException(clickEventActionManager.getLabelText(), "Unsupported protocol: " + scheme.toLowerCase());
                  }

                  if (this.mc.gameSettings.state124) {
                     this.uRI = uri;
                     Minecraft mc2 = this.mc;
                     SplinePredictor splinePredictor = new SplinePredictor(this, clickEventActionManager.getLabelText(), 31102009, false);
                     mc2.displayGuiScreen(splinePredictor);
                  } else {
                     this.applyURI(uri);
                  }
               } catch (URISyntaxException uRISyntaxException) {
                  logger.error("Can't open url for " + clickEventActionManager, uRISyntaxException);
               }
            } else if (clickEventActionManager.getClientSettingsOpenUrlType() == ClientSettingsOpenUrlType.OPEN_FILE) {
               URI uri2 = new File(clickEventActionManager.getLabelText()).toURI();
               this.applyURI(uri2);
            } else if (clickEventActionManager.getClientSettingsOpenUrlType() == ClientSettingsOpenUrlType.SUGGEST_COMMAND) {
               this.applyText(clickEventActionManager.getLabelText(), true);
            } else if (clickEventActionManager.getClientSettingsOpenUrlType() == ClientSettingsOpenUrlType.RUN_COMMAND) {
               this.applyTextByText(clickEventActionManager.getLabelText(), false);
            } else {
               logger.error("Don't know how to handle " + clickEventActionManager);
            }

            return true;
         }

         return false;
      }
   }

   public void applyIndex(int count2, int count3, int count4, long value) {
   }

   public void setIndexOffset(int indexOffset2) {
      this.indexOffset = indexOffset2;
   }

   private void applyIndexByIndex(int count2) throws IOException {
      if (!((Module)ClientGlobals.moduleManager.invManager).isToggled() || !ClientGlobals.moduleManager.invManager.checkCondition2() || this.state) {
         int count3 = Mouse.getEventX() * this.width / this.mc.displayWidth;
         int count4 = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
         if (!this.mc.gameSettings.state48 || this.count++ <= 0) {
            this.indexOffset = count2;
            this.timestamp = Minecraft.getTimestamp();
            this.processAction(count3, count4, this.indexOffset);
         }
      }
   }

   public void applyList(List list3, int count2, int count3) {
      if (!list3.isEmpty()) {
         GlStateManager.applyState8();
         RenderHelper.disableStandardItemLighting();
         GlStateManager.applyState3();
         GlStateManager.enableDepth();
         int count4 = 0;

         for (String text : (Iterable<String>)(Iterable<?>)list3) {
            int index = this.fontRenderer.getIndex(text);
            if (index > count4) {
               count4 = index;
            }
         }

         int count5 = count2 + 12;
         int count6 = count3 - 12;
         int count7 = 8;
         if (list3.size() > 1) {
            count7 += 2 + (list3.size() - 1) * 10;
         }

         if (count5 + count4 > this.width) {
            count5 -= 28 + count4;
         }

         if (count6 + count7 + 6 > this.height) {
            count6 = this.height - count7 - 6;
         }

         this.factor = 300.0F;
         this.renderItem.factor = 300.0F;
         this.applyIndex(count5 - 3, count6 - 4, count5 + count4 + 3, count6 - 3, -267386864, -267386864);
         this.applyIndex(count5 - 3, count6 + count7 + 3, count5 + count4 + 3, count6 + count7 + 4, -267386864, -267386864);
         this.applyIndex(count5 - 3, count6 - 3, count5 + count4 + 3, count6 + count7 + 3, -267386864, -267386864);
         this.applyIndex(count5 - 4, count6 - 3, count5 - 3, count6 + count7 + 3, -267386864, -267386864);
         this.applyIndex(count5 + count4 + 3, count6 - 3, count5 + count4 + 4, count6 + count7 + 3, -267386864, -267386864);
         this.applyIndex(count5 - 3, count6 - 3 + 1, count5 - 3 + 1, count6 + count7 + 3 - 1, 1347420415, 1344798847);
         this.applyIndex(count5 + count4 + 2, count6 - 3 + 1, count5 + count4 + 3, count6 + count7 + 3 - 1, 1347420415, 1344798847);
         this.applyIndex(count5 - 3, count6 - 3, count5 + count4 + 3, count6 - 3 + 1, 1347420415, 1347420415);
         this.applyIndex(count5 - 3, count6 + count7 + 2, count5 + count4 + 3, count6 + count7 + 3, 1344798847, 1344798847);

         for (int index2 = 0; index2 < list3.size(); index2++) {
            String text2 = (String)list3.get(index2);
            this.fontRenderer.getIndex2(text2, (float)count5, (float)count6, -1);
            if (index2 == 0) {
               count6 += 2;
            }

            count6 += 10;
         }

         this.factor = 0.0F;
         this.renderItem.factor = 0.0F;
         GlStateManager.applyState13();
         GlStateManager.disableDepth();
         RenderHelper.enableStandardItemLighting();
         GlStateManager.disableLighting();
      }
   }

   public void applyMinecraft2(Minecraft mc2, int count2, int count3) {
      this.mc = mc2;
      this.renderItem = mc2.getRenderItem();
      this.fontRenderer = mc2.fontRendererObj;
      this.width = count2;
      this.height = count3;
      this.list.clear();
      this.updateState2();
   }

   public static void applyText4(String text) {
      if (!StringUtils.isEmpty(text)) {
         try {
            StringSelection stringSelection = new StringSelection(text);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, (ClipboardOwner)null);
         } catch (Exception exception) {
         }
      }
   }

   public static boolean checkIndex(int count2) {
      return count2 == 46 && isActive3() && !isActiveAsFlag() && !isActive2();
   }

   public boolean isActive() {
      return true;
   }

   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2) throws IOException {
   }

   public void renderElement2() throws IOException {
      char eventCharacter = Keyboard.getEventCharacter();
      int eventKey = Keyboard.getEventKey();
      if (Keyboard.getEventKeyState() || eventKey == 0 && Character.isDefined(eventCharacter)) {
         if (!set.contains(this)
            && !(this instanceof GuiMainMenu)
            && !(this instanceof GuiMultiplayer)
            && !(this instanceof AltGuiScreenAdapter)
            && !(this instanceof PrimaryGuiScreenAdapter)
            && !(this instanceof InternalClientScreenAdapter)
            && !(this instanceof AuxiliaryClientScreenAdapter)) {
            QueueEntry.updateState3();
         }

         this.applyChar(eventCharacter, eventKey);
      }

      this.mc.updateState18();
   }

   public void updateState4() {
   }

   public void applyChar(char character, int count2) throws IOException {
      if (count2 == 1) {
         GuiScreen guiScreen2 = this.guiScreen;
         this.mc.displayGuiScreen(guiScreen2);
         if (this.mc.currentScreen == null) {
            this.mc.updateState21();
         }
      }
   }

   public void applyIndex(int count2, int count3, int count4) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter != null && count4 == 0) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.applyIndex(count2, count3);
         this.auxiliaryTexturesGuiIconsPngManagerAdapter = null;
      }
   }

   public void applyText3(String text) {
      this.applyTextByText(text, true);
   }

   public static boolean checkIndex3(int count2) {
      return count2 == 47 && isActive3() && !isActiveAsFlag() && !isActive2();
   }

   @Override
   public void applyFlag(boolean flag, int count2) {
      if (count2 == 31102009) {
         if (flag) {
            this.applyURI(this.uRI);
         }

         this.uRI = null;
         this.mc.displayGuiScreen(this);
      }
   }

   public void applyURI(URI uri) {
      try {
         Class clazz = Class.forName("java.awt.Desktop");
         Object entry = clazz.getMethod("getDesktop").invoke(null);
         clazz.getMethod("browse", URI.class).invoke(entry, uri);
      } catch (Throwable throwable) {
         logger.error("Couldn't open link", throwable);
      }
   }

   public void applyIChatComponent(IChatComponent iChatComponent, int count2, int count3) {
      if (iChatComponent != null && iChatComponent.getStyleHasParentManager().getHoverEventActionManager() != null) {
         HoverEventActionManager hoverEventActionManager = iChatComponent.getStyleHasParentManager().getHoverEventActionManager();
         if (hoverEventActionManager.getClientSettingsShowTextType() == ClientSettingsShowTextType.SHOW_ITEM) {
            ItemStack stack = null;

            try {
               NBTTagCompound tagCompound = DampenerEstimator.resolveNBTTagCompound(hoverEventActionManager.getIChatComponent().getString());
               if (tagCompound instanceof NBTTagCompound) {
                  stack = ItemStack.resolveItemStack(tagCompound);
               }
            } catch (SecondaryExceptionAdapter exception) {
            }

            if (stack != null) {
               this.applyItemStack(stack, count2, count3);
            } else {
               this.applyText(ClientSettingsBlackType.RED + "Invalid Item!", count2, count3);
            }
         } else if (hoverEventActionManager.getClientSettingsShowTextType() == ClientSettingsShowTextType.SHOW_ENTITY) {
            if (this.mc.gameSettings.state136) {
               try {
                  NBTTagCompound tagCompound2 = DampenerEstimator.resolveNBTTagCompound(hoverEventActionManager.getIChatComponent().getString());
                  if (tagCompound2 instanceof NBTTagCompound) {
                     ArrayList list3 = Lists.newArrayList();
                     NBTTagCompound tagCompound3 = tagCompound2;
                     list3.add(tagCompound3.resolveText("name"));
                     if (tagCompound3.matchesText("type", 8)) {
                        String text = tagCompound3.resolveText("type");
                        list3.add("Type: " + text + " (" + SkippingEntityWithIdManager.computeCount(text) + ")");
                     }

                     list3.add(tagCompound3.resolveText("id"));
                     this.applyList(list3, count2, count3);
                  } else {
                     this.applyText(ClientSettingsBlackType.RED + "Invalid Entity!", count2, count3);
                  }
               } catch (SecondaryExceptionAdapter exception2) {
                  this.applyText(ClientSettingsBlackType.RED + "Invalid Entity!", count2, count3);
               }
            }
         } else if (hoverEventActionManager.getClientSettingsShowTextType() == ClientSettingsShowTextType.SHOW_TEXT) {
            this.applyList(splitter.splitToList(hoverEventActionManager.getIChatComponent().getString2()), count2, count3);
         } else if (hoverEventActionManager.getClientSettingsShowTextType() == ClientSettingsShowTextType.SHOW_ACHIEVEMENT) {
            DuplicateStatIdManager duplicateStatIdManager = StatCraftItemManager.get(hoverEventActionManager.getIChatComponent().getString());
            if (duplicateStatIdManager != null) {
               IChatComponent iChatComponent2 = duplicateStatIdManager.getIChatComponent();
               BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(
                  "stats.tooltip.type." + (duplicateStatIdManager.isActive() ? "achievement" : "statistic"), new Object[0]
               );
               baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withMemberRef5(Boolean.TRUE);
               String text2 = duplicateStatIdManager instanceof AuxiliaryDuplicateStatIdManagerAdapter ? ((AuxiliaryDuplicateStatIdManagerAdapter)duplicateStatIdManager).getText() : null;
               ArrayList list4 = Lists.newArrayList(new String[]{iChatComponent2.getString2(), baseManagerStyleIChatComponentAdapter.getString2()});
               if (text2 != null) {
                  list4.addAll(this.fontRenderer.getList(text2, 150));
               }

               this.applyList(list4, count2, count3);
            } else {
               this.applyText(ClientSettingsBlackType.RED + "Invalid statistic/achievement!", count2, count3);
            }
         }

         GlStateManager.applyState3();
      }
   }

   public void updateState3() throws IOException {
      if ((!((Module)ClientGlobals.moduleManager.invManager).isToggled() || !ClientGlobals.moduleManager.invManager.checkCondition2())
            && (!((Module)ClientGlobals.moduleManager.refill).isToggled() || !ClientGlobals.moduleManager.refill.canExecute5())
         || this.state) {
         int count2 = Mouse.getEventX() * this.width / this.mc.displayWidth;
         int count3 = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
         int eventButton = Mouse.getEventButton();
         if (Mouse.getEventButtonState()) {
            if (eventButton == 0) {
               this.enabled5 = true;
               this.enabled2 = true;
            }

            if (eventButton == 1) {
               this.enabled3 = true;
               this.enabled4 = true;
            }

            this.state = true;
            ClientGlobals.moduleManager.inventoryClicker.updateState();
            if (this.mc.gameSettings.state48 && this.count++ > 0) {
               return;
            }

            this.indexOffset = eventButton;
            this.timestamp = Minecraft.getTimestamp();
            this.processAction(count2, count3, this.indexOffset);
         } else if (eventButton != -1) {
            if (eventButton == 0) {
               this.enabled5 = false;
               this.enabled2 = false;
            }

            if (eventButton == 1) {
               this.enabled3 = false;
               this.enabled4 = false;
            }

            ClientGlobals.moduleManager.inventoryClicker.updateState2();
            if (this.mc.gameSettings.state48 && --this.count > 0) {
               return;
            }

            this.indexOffset = -1;
            this.applyIndex(count2, count3, eventButton);
            this.state = false;
         } else if (this.indexOffset != -1 && this.timestamp > 0L) {
            long value = Minecraft.getTimestamp() - this.timestamp;
            this.applyIndex(count2, count3, this.indexOffset, value);
         }
      }
   }

   public boolean isState() {
      return this.state;
   }

   public void applyIndex3(int count2) {
      if (this.mc.theWorld != null) {
         if (!EventGuiOpenCallback.resolveEventGuiOpenByGuiScreen(this).isCancelled()) {
            this.applyIndex(0, 0, this.width, this.height, -1072689136, -804253680);
         }
      } else {
         this.applyIndex2(count2);
      }
   }

   public void applyTextByText(String text, boolean flag) {
      if (flag) {
         this.mc.ingameGUI.getInternalLoggerEntry().applyText(text);
      }

      this.mc.thePlayer.sendChatMessage(text);
   }

   public static boolean isActive2() {
      return Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
   }

   public void renderElement3() {
      this.applyIndex3(0);
   }

   public static boolean checkIndex2(int count2) {
      return count2 == 45 && isActive3() && !isActiveAsFlag() && !isActive2();
   }

   public void applyIndex(int count2, int count3, float factor) {
      for (int index = 0; index < this.list.size(); index++) {
         ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(index)).applyMinecraftByMinecraft(this.mc, count2, count3);
      }

      for (int index2 = 0; index2 < this.list2.size(); index2++) {
         ((GeometryDispatcher)this.list2.get(index2)).applyMinecraft2(this.mc, count2, count3);
      }
   }

   public void processAction(int count2, int count3, int count4) throws IOException {
      if (count4 == 0) {
         for (int index = 0; index < this.list.size(); index++) {
            AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2 = (AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(index);
            if (auxiliaryTexturesGuiIconsPngManagerAdapter2.matchesMinecraft(this.mc, count2, count3)) {
               this.auxiliaryTexturesGuiIconsPngManagerAdapter = auxiliaryTexturesGuiIconsPngManagerAdapter2;
               auxiliaryTexturesGuiIconsPngManagerAdapter2.applySoundHandler(this.mc.getMcSoundHandler());
               this.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter2);
            }
         }
      }
   }

   private void applyIndex4(int count2) {
      if (!((Module)ClientGlobals.moduleManager.invManager).isToggled() || !ClientGlobals.moduleManager.invManager.checkCondition2() || this.state) {
         int count3 = Mouse.getEventX() * this.width / this.mc.displayWidth;
         int count4 = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
         if (!this.mc.gameSettings.state48 || --this.count <= 0) {
            this.indexOffset = -1;
            this.applyIndex(count3, count4, count2);
         }
      }
   }

   public static boolean isActive3() {
      return Minecraft.state3 ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220) : Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
   }

   public static boolean isActiveAsFlag() {
      return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public GuiScreen(GuiScreen screen) {
      this.guiScreen = screen;
   }

   public GuiScreen() {
      this.guiScreen = null;
   }
}
