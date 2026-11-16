package client.gui;

import client.Client;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.util.CharArrayHolder;
import client.util.ClickEventActionManager;
import client.util.FovWriter;
import client.util.HandlerStringEntry;
import client.util.PacketBuffer;
import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class SpecialGuiScreenAdapter extends GuiScreen {
   public static final Logger logger2 = LogManager.getLogger();
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/book.png");
   public final EntityPlayer entityPlayer;
   public final ItemStack itemStack;
   public final boolean enabled6;
   public boolean enabled8;
   public boolean enabled7;
   public int count2;
   public int count5 = 192;
   public int count8 = 192;
   public int count7 = 1;
   public int count3;
   public NBTTagList nBTTagList;
   public String labelText = "";
   public List list4;
   public int count6 = -1;
   public ToggleSwitchWidget toggleSwitchWidget2;
   public ToggleSwitchWidget toggleSwitchWidget;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter5;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;

   @Override
   public void updateState2() {
      this.list.clear();
      Keyboard.enableRepeatEvents(true);
      if (this.enabled6) {
         this.list
            .add(
               this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  3, this.width / 2 - 100, 4 + this.count8, 98, 20, StatCollector.getText("book.signButton", new Object[0])
               )
            );
         this.list
            .add(
               this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  0, this.width / 2 + 2, 4 + this.count8, 98, 20, StatCollector.getText("gui.done", new Object[0])
               )
            );
         this.list
            .add(
               this.auxiliaryTexturesGuiIconsPngManagerAdapter5 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  5, this.width / 2 - 100, 4 + this.count8, 98, 20, StatCollector.getText("book.finalizeButton", new Object[0])
               )
            );
         this.list
            .add(
               this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  4, this.width / 2 + 2, 4 + this.count8, 98, 20, StatCollector.getText("gui.cancel", new Object[0])
               )
            );
      } else {
         this.list
            .add(
               this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  0, this.width / 2 - 100, 4 + this.count8, 200, 20, StatCollector.getText("gui.done", new Object[0])
               )
            );
      }

      int count = (this.width - this.count5) / 2;
      this.list.add(this.toggleSwitchWidget2 = new ToggleSwitchWidget(1, count + 120, 156, true));
      this.list.add(this.toggleSwitchWidget = new ToggleSwitchWidget(2, count + 38, 156, false));
      this.renderElement5();
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public boolean matchesIChatComponent(IChatComponent iChatComponent) {
      ClickEventActionManager event = iChatComponent == null ? null : iChatComponent.getStyleHasParentManager().getClickEventActionManager();
      if (event == null) {
         return false;
      } else if (event.getClientSettingsOpenUrlType() == ClientSettingsOpenUrlType.CHANGE_PAGE) {
         String labelText2 = event.getLabelText();

         try {
            int count = Integer.parseInt(labelText2) - 1;
            if (count >= 0 && count < this.count7 && count != this.count3) {
               this.count3 = count;
               this.renderElement5();
               return true;
            }
         } catch (Throwable throwable) {
         }

         return false;
      } else {
         boolean flag = super.matchesIChatComponent(iChatComponent);
         if (flag && event.getClientSettingsOpenUrlType() == ClientSettingsOpenUrlType.RUN_COMMAND) {
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
         }

         return flag;
      }
   }

   public static ResourceLocation getResourceLocation5() {
      return resourceLocation5;
   }

   private String getTextAsText() {
      return this.nBTTagList != null && this.count3 >= 0 && this.count3 < this.nBTTagList.getInt() ? this.nBTTagList.resolveText(this.count3) : "";
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
            this.setEnabled(false);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3 && this.enabled6) {
            this.enabled7 = true;
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            if (this.count3 < this.count7 - 1) {
               this.count3++;
            } else if (this.enabled6) {
               this.renderElement();
               if (this.count3 < this.count7 - 1) {
                  this.count3++;
               }
            }
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            if (this.count3 > 0) {
               this.count3--;
            }
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 5 && this.enabled7) {
            this.setEnabled(true);
            GuiScreen screen2 = null;
            this.mc.displayGuiScreen(screen2);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4 && this.enabled7) {
            this.enabled7 = false;
         }

         this.renderElement5();
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      if (this.enabled6) {
         if (this.enabled7) {
            this.applyChar2(character, count);
         } else {
            this.applyCharByChar(character, count);
         }
      }
   }

   @Override
   public void updateState4() {
      super.updateState4();
      this.count2++;
   }

   private void applyChar2(char character, int count) throws IOException {
      switch (count) {
         case 14:
            if (!this.labelText.isEmpty()) {
               this.labelText = this.labelText.substring(0, this.labelText.length() - 1);
               this.renderElement5();
            }

            return;
         case 28:
         case 156:
            if (!this.labelText.isEmpty()) {
               this.setEnabled(true);
               GuiScreen screen = null;
               this.mc.displayGuiScreen(screen);
            }

            return;
         default:
            if (this.labelText.length() < 16 && CharArrayHolder.checkChar(character)) {
               this.labelText = this.labelText + character;
               this.renderElement5();
               this.enabled8 = true;
            }
      }
   }

   public IChatComponent resolveIChatComponent(int count, int count4) {
      if (this.list4 == null) {
         return null;
      } else {
         int count9 = count - (this.width - this.count5) / 2 - 36;
         int count10 = count4 - 2 - 16 - 16;
         if (count9 >= 0 && count10 >= 0) {
            int count11 = Math.min(128 / this.fontRenderer.indexOffset, this.list4.size());
            if (count9 <= 116 && count10 < this.mc.fontRendererObj.indexOffset * count11 + count11) {
               int count12 = count10 / this.mc.fontRendererObj.indexOffset;
               if (count12 >= 0 && count12 < this.list4.size()) {
                  IChatComponent iChatComponent = (IChatComponent)this.list4.get(count12);
                  int count13 = 0;

                  for (IChatComponent iChatComponent2 : (Iterable<IChatComponent>)(Iterable<?>)iChatComponent) {
                     if (iChatComponent2 instanceof ChatComponentText) {
                        count13 += this.mc.fontRendererObj.getIndex(((ChatComponentText)iChatComponent2).getLabelTextAsText());
                        if (count13 > count9) {
                           return iChatComponent2;
                        }
                     }
                  }
               }

               return null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   private void applyText(String text) {
      if (this.nBTTagList != null && this.count3 >= 0 && this.count3 < this.nBTTagList.getInt()) {
         this.nBTTagList.applyLISTManager(this.count3, new HandlerStringEntry(text));
         this.enabled8 = true;
      }
   }

   private void applyCharByChar(char character, int count) {
      if (GuiScreen.checkIndex3(count)) {
         this.applyTextByText(GuiScreen.getText());
      } else {
         switch (count) {
            case 14:
               String textAsText = this.getTextAsText();
               if (textAsText.length() > 0) {
                  this.applyText(textAsText.substring(0, textAsText.length() - 1));
               }

               return;
            case 28:
            case 156:
               this.applyTextByText("\n");
               return;
            default:
               if (CharArrayHolder.checkChar(character)) {
                  this.applyTextByText(Character.toString(character));
               }
         }
      }
   }

   private void renderElement5() {
      this.toggleSwitchWidget2.enabled2 = !this.enabled7 && (this.count3 < this.count7 - 1 || this.enabled6);
      this.toggleSwitchWidget.enabled2 = !this.enabled7 && this.count3 > 0;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled2 = !this.enabled6 || !this.enabled7;
      if (this.enabled6) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled2 = !this.enabled7;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2 = this.enabled7;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled2 = this.enabled7;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = this.labelText.trim().length() > 0;
      }
   }

   @Override
   public void applyIndex(int count, int count4, float factor) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      int count9 = (this.width - this.count5) / 2;
      this.processAction(count9, 2, 0, 0, this.count5, this.count8);
      if (Client.getClient().getModuleManager().antiStrike.canExecute5()) {
         super.applyIndex(count, count4, factor);
      } else {
         if (this.enabled7) {
            String labelText2 = this.labelText;
            if (this.enabled6) {
               if (this.count2 / 6 % 2 == 0) {
                  labelText2 = labelText2 + "" + ClientSettingsBlackType.BLACK + "_";
               } else {
                  labelText2 = labelText2 + "" + ClientSettingsBlackType.GRAY + "_";
               }
            }

            String text = StatCollector.getText("book.editTitle", new Object[0]);
            int index = this.fontRenderer.getIndex(text);
            this.fontRenderer.getIndex(text, count9 + 36 + (116 - index) / 2, 34, 0);
            int index2 = this.fontRenderer.getIndex(labelText2);
            this.fontRenderer.getIndex(labelText2, count9 + 36 + (116 - index2) / 2, 50, 0);
            String text2 = StatCollector.getText("book.byAuthor", new Object[]{this.entityPlayer.getText()});
            int index3 = this.fontRenderer.getIndex((String)text2);
            this.fontRenderer.getIndex(ClientSettingsBlackType.DARK_GRAY + text2, count9 + 36 + (116 - index3) / 2, 60, 0);
            String text3 = StatCollector.getText("book.finalizeWarning", new Object[0]);
            this.fontRenderer.applyText(text3, count9 + 36, 82, 116, 0);
         } else {
            String text4 = StatCollector.getText("book.pageIndicator", new Object[]{this.count3 + 1, this.count7});
            String text5 = "";
            if (this.nBTTagList != null && this.count3 >= 0 && this.count3 < this.nBTTagList.getInt()) {
               text5 = this.nBTTagList.resolveText(this.count3);
            }

            if (this.enabled6) {
               if (this.fontRenderer.isState1()) {
                  text5 = text5 + "_";
               } else if (this.count2 / 6 % 2 == 0) {
                  text5 = text5 + "" + ClientSettingsBlackType.BLACK + "_";
               } else {
                  text5 = text5 + "" + ClientSettingsBlackType.GRAY + "_";
               }
            } else if (this.count6 != this.count3) {
               if (ItemWrittenBook.matchesNBTTagCompound(this.itemStack.getStackTagCompound())) {
                  try {
                     IChatComponent iChatComponent = ChatComponentSerializer.parseChatComponent(text5);
                     this.list4 = iChatComponent != null ? FovWriter.resolveList(iChatComponent, 116, this.fontRenderer, true, true) : null;
                  } catch (JsonParseException jsonParseException) {
                     this.list4 = null;
                  }
               } else {
                  ChatComponentText chatComponentText = new ChatComponentText(ClientSettingsBlackType.DARK_RED + "* Invalid book tag *");
                  this.list4 = Lists.newArrayList(chatComponentText);
               }

               this.count6 = this.count3;
            }

            int index4 = this.fontRenderer.getIndex(text4);
            this.fontRenderer.getIndex(text4, count9 - index4 + this.count5 - 44, 18, 0);
            if (this.list4 == null) {
               this.fontRenderer.applyText(text5, count9 + 36, 34, 116, 0);
            } else {
               int count10 = Math.min(128 / this.fontRenderer.indexOffset, this.list4.size());

               for (int index5 = 0; index5 < count10; index5++) {
                  IChatComponent iChatComponent2 = (IChatComponent)this.list4.get(index5);
                  this.fontRenderer.getIndex(iChatComponent2.getString(), count9 + 36, 34 + index5 * this.fontRenderer.indexOffset, 0);
               }

               IChatComponent iChatComponent3 = this.resolveIChatComponent(count, count4);
               if (iChatComponent3 != null) {
                  this.applyIChatComponent(iChatComponent3, count, count4);
               }
            }
         }

         super.applyIndex(count, count4, factor);
      }
   }

   private void applyTextByText(String text) {
      String textAsText = this.getTextAsText();
      String text2 = textAsText + text;
      if (this.fontRenderer.computeCount(text2 + "" + ClientSettingsBlackType.BLACK + "_", 118) <= 128 && text2.length() < 256) {
         this.applyText(text2);
      }
   }

   @Override
   public void processAction(int count, int count4, int count9) throws IOException {
      if (count9 == 0) {
         IChatComponent iChatComponent = this.resolveIChatComponent(count, count4);
         if (this.matchesIChatComponent(iChatComponent)) {
            return;
         }
      }

      super.processAction(count, count4, count9);
   }

   private void renderElement() {
      if (this.nBTTagList != null && this.nBTTagList.getInt() < 50) {
         this.nBTTagList.applyLISTManager(new HandlerStringEntry(""));
         this.count7++;
         this.enabled8 = true;
      }
   }

   private void setEnabled(boolean enabled) throws IOException {
      if (this.enabled6 && this.enabled8 && this.nBTTagList != null) {
         while (this.nBTTagList.getInt() > 1 && this.nBTTagList.resolveText(this.nBTTagList.getInt() - 1).length() == 0) {
            this.nBTTagList.remove(this.nBTTagList.getInt() - 1);
         }

         if (this.itemStack.canExecute()) {
            this.itemStack.getStackTagCompound().applyText("pages", this.nBTTagList);
         } else {
            this.itemStack.applyText("pages", this.nBTTagList);
         }

         String text = "MC|BEdit";
         if (enabled) {
            text = "MC|BSign";
            this.itemStack.applyText("author", new HandlerStringEntry(this.entityPlayer.getText()));
            this.itemStack.applyText("title", new HandlerStringEntry(this.labelText.trim()));

            for (int index = 0; index < this.nBTTagList.getInt(); index++) {
               String text2 = this.nBTTagList.resolveText(index);
               String text3 = ChatComponentSerializer.toJson(new ChatComponentText(text2));
               this.nBTTagList.applyLISTManager(index, new HandlerStringEntry(text3));
            }

            this.itemStack.setItem(Items.writtenBook);
         }

         PacketBuffer packet = new PacketBuffer(Unpooled.buffer());
         packet.applyItemStack(this.itemStack);
         this.mc.getNetHandlerPlayClient().handlePacket(new C17PacketCustomPayload(text, packet));
      }
   }

   public SpecialGuiScreenAdapter(EntityPlayer player, ItemStack stack, boolean flag) {
      GuiScreen.set.add(this);
      this.entityPlayer = player;
      this.itemStack = stack;
      this.enabled6 = flag;
      if (stack.canExecute()) {
         NBTTagCompound stackTagCompound = stack.getStackTagCompound();
         this.nBTTagList = stackTagCompound.getExtendedLoggerEntry("pages", 8);
         if (this.nBTTagList != null) {
            this.nBTTagList = (NBTTagList)this.nBTTagList.getLISTManager();
            this.count7 = this.nBTTagList.getInt();
            if (this.count7 < 1) {
               this.count7 = 1;
            }
         }
      }

      if (this.nBTTagList == null && flag) {
         this.nBTTagList = new NBTTagList();
         this.nBTTagList.applyLISTManager(new HandlerStringEntry(""));
         this.count7 = 1;
      }
   }
}
