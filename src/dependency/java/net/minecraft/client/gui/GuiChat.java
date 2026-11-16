package net.minecraft.client.gui;

import cc.baritone.utils.ActiveCustom;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.SettingsTxtManager;
import client.Client;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.module.Module;
import client.util.MovingObjectType;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiChat extends GuiScreen {
   public static final Logger logger2 = LogManager.getLogger();
   public String labelText = "";
   public int count2 = -1;
   public boolean enabled7;
   public boolean enabled6;
   public int count3;
   public List list3 = Lists.newArrayList();
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public String labelText2 = "";

   @Override
   public void updateState2() {
      Keyboard.enableRepeatEvents(true);
      this.count2 = this.mc.ingameGUI.getInternalLoggerEntry().getList2().size();
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(
         0, this.fontRenderer, 4, this.height - 12, this.width - 4, 12
      );
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(100);
      this.internalTexturesGuiIconsPngManagerAdapter.setEnabled4(false);
      this.internalTexturesGuiIconsPngManagerAdapter.setEnabledByFlag(true);
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(this.labelText2);
      this.internalTexturesGuiIconsPngManagerAdapter.setEnabled(false);
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
      this.mc.ingameGUI.getInternalLoggerEntry().renderElement3();
   }

   @Override
   public void applyText(String text, boolean flag) {
      if (flag) {
         this.internalTexturesGuiIconsPngManagerAdapter.applyText(text);
      } else {
         this.internalTexturesGuiIconsPngManagerAdapter.applyTextByText(text);
      }
   }

   private void applyText(String text, String text2) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         ActiveListener activeListener = SettingsTxtManager.getExtraListener().get();
         ActiveCustom activeCustom = new ActiveCustom(text);
         activeListener.getCompositeListener().forEach(activeCustom);
         if (activeCustom.isEnabled()) {
            return;
         }

         if (activeCustom.stringArray != null) {
            this.enabled6 = true;
            this.applyTextArray(activeCustom.stringArray);
            return;
         }
      }

      if (text.length() >= 1) {
         BlockPos blockPos = null;
         if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            blockPos = this.mc.objectMouseOver.getBlockPos();
         }

         this.mc.thePlayer.netHandlerPlayClient.handlePacket(new C14PacketTabComplete(text, blockPos));
         this.enabled6 = true;
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
   }

   public void applyIndex(int count) {
      int count4 = this.count2 + count;
      int size = this.mc.ingameGUI.getInternalLoggerEntry().getList2().size();
      int intItem = MathHelper.getInt12(count4, 0, size);
      if (intItem != this.count2) {
         if (intItem == size) {
            this.count2 = size;
            this.internalTexturesGuiIconsPngManagerAdapter.applyText(this.labelText);
         } else {
            if (this.count2 == size) {
               this.labelText = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
            }

            this.internalTexturesGuiIconsPngManagerAdapter.applyText((String)this.mc.ingameGUI.getInternalLoggerEntry().getList2().get(intItem));
            this.count2 = intItem;
         }
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      this.enabled6 = false;
      if (count == 15) {
         this.renderElement();
      } else {
         this.enabled7 = false;
      }

      if (count == 1) {
         GuiScreen screen = null;
         this.mc.displayGuiScreen(screen);
      } else if (count == 28 || count == 156) {
         String text = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().trim();
         if (text.length() > 0) {
            this.applyText3(text);
         }

         GuiScreen screen2 = null;
         this.mc.displayGuiScreen(screen2);
      } else if (count == 200) {
         this.applyIndex(-1);
      } else if (count == 208) {
         this.applyIndex(1);
      } else if (count == 201) {
         this.mc.ingameGUI.getInternalLoggerEntry().applyIndex(this.mc.ingameGUI.getInternalLoggerEntry().getCountAsIndex() - 1);
      } else if (count == 209) {
         this.mc.ingameGUI.getInternalLoggerEntry().applyIndex(-this.mc.ingameGUI.getInternalLoggerEntry().getCountAsIndex() + 1);
      } else {
         this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
      }
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      int eventDWheel = Mouse.getEventDWheel();
      if (eventDWheel != 0) {
         if (eventDWheel > 1) {
            eventDWheel = 1;
         }

         if (eventDWheel < -1) {
            eventDWheel = -1;
         }

         if (!isActiveAsFlag()) {
            eventDWheel *= 7;
         }

         this.mc.ingameGUI.getInternalLoggerEntry().applyIndex(eventDWheel);
      }
   }

   @Override
   public void applyIndex(int count, int count4, float factor) {
      processAction(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      IChatComponent iChatComponent = this.mc.ingameGUI.getInternalLoggerEntry().resolveIChatComponent(Mouse.getX(), Mouse.getY());
      if (iChatComponent != null && iChatComponent.getStyleHasParentManager().getHoverEventActionManager() != null) {
         this.applyIChatComponent(iChatComponent, count, count4);
      }

      super.applyIndex(count, count4, factor);
   }

   @Override
   public void processAction(int count, int count4, int count5) throws IOException {
      if (count5 == 0) {
         IChatComponent iChatComponent = this.mc.ingameGUI.getInternalLoggerEntry().resolveIChatComponent(Mouse.getX(), Mouse.getY());
         if (this.matchesIChatComponent(iChatComponent)) {
            return;
         }
      }

      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count4, count5);
      super.processAction(count, count4, count5);
   }

   public void renderElement() {
      if (this.enabled7) {
         this.internalTexturesGuiIconsPngManagerAdapter
            .applyIndex2(
               this.internalTexturesGuiIconsPngManagerAdapter.computeCount(-1, this.internalTexturesGuiIconsPngManagerAdapter.getCount2(), false)
                  - this.internalTexturesGuiIconsPngManagerAdapter.getCount2()
            );
         if (this.count3 >= this.list3.size()) {
            this.count3 = 0;
         }
      } else {
         int count = this.internalTexturesGuiIconsPngManagerAdapter.computeCount(-1, this.internalTexturesGuiIconsPngManagerAdapter.getCount2(), false);
         this.list3.clear();
         this.count3 = 0;
         String text = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().substring(count).toLowerCase();
         String text2 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().substring(0, this.internalTexturesGuiIconsPngManagerAdapter.getCount2());
         this.applyText(text2, text);
         if (this.list3.isEmpty()) {
            return;
         }

         this.enabled7 = true;
         this.internalTexturesGuiIconsPngManagerAdapter.applyIndex2(count - this.internalTexturesGuiIconsPngManagerAdapter.getCount2());
      }

      if (this.list3.size() > 1) {
         StringBuilder builder = new StringBuilder();

         for (String text3 : (Iterable<String>)this.list3) {
            if (builder.length() > 0) {
               builder.append(", ");
            }

            builder.append(text3);
         }

         this.mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent(new ChatComponentText(builder.toString()), 1);
      }

      this.internalTexturesGuiIconsPngManagerAdapter.applyTextByText((String)this.list3.get(this.count3++));
   }

   public void applyTextArray(String[] textArray) {
      if (this.enabled6) {
         this.enabled7 = false;
         this.list3.clear();

         for (String text : textArray) {
            if (text.length() > 0) {
               this.list3.add(text);
            }
         }

         String text2 = this.internalTexturesGuiIconsPngManagerAdapter
            .getLabelText()
            .substring(this.internalTexturesGuiIconsPngManagerAdapter.computeCount(-1, this.internalTexturesGuiIconsPngManagerAdapter.getCount2(), false));
         String commonPrefix = StringUtils.getCommonPrefix(textArray);
         if (commonPrefix.length() > 0 && !text2.equalsIgnoreCase(commonPrefix)) {
            this.internalTexturesGuiIconsPngManagerAdapter
               .applyIndex2(
                  this.internalTexturesGuiIconsPngManagerAdapter.computeCount(-1, this.internalTexturesGuiIconsPngManagerAdapter.getCount2(), false)
                     - this.internalTexturesGuiIconsPngManagerAdapter.getCount2()
               );
            this.internalTexturesGuiIconsPngManagerAdapter.applyTextByText(commonPrefix);
         } else if (this.list3.size() > 0) {
            this.enabled7 = true;
            this.renderElement();
         }
      }
   }

   public GuiChat() {
   }

   public GuiChat(String text) {
      this.labelText2 = text;
   }
}
