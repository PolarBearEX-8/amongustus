package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.List;
import net.augustus.Augustus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiScreenBook extends GuiScreen {
   private static final Logger LOGGER2 = LogManager.getLogger();
   private static final ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
   private final EntityPlayer editingPlayer;
   private final ItemStack bookObj;
   private final boolean bookIsUnsigned;
   private boolean bookIsModified;
   private boolean bookGettingSigned;
   private int updateCount;
   private int bookImageWidth = 192;
   private int bookImageHeight = 192;
   private int bookTotalPages = 1;
   private int currPage;
   private NBTTagList bookPages;
   private String bookTitle = "";
   private List cachedComponents;
   private int cachedPage = -1;
   private GuiScreenBook.NextPageButton buttonNextPage;
   private GuiScreenBook.NextPageButton buttonPreviousPage;
   private GuiButton buttonDone;
   private GuiButton buttonSign;
   private GuiButton buttonFinalize;
   private GuiButton buttonCancel;

   private void updateButtons() {
      this.buttonNextPage.visible = !this.bookGettingSigned && (this.currPage < this.bookTotalPages - 1 || this.bookIsUnsigned);
      this.buttonPreviousPage.visible = !this.bookGettingSigned && this.currPage > 0;
      this.buttonDone.visible = !this.bookIsUnsigned || !this.bookGettingSigned;
      if (this.bookIsUnsigned) {
         this.buttonSign.visible = !this.bookGettingSigned;
         this.buttonCancel.visible = this.bookGettingSigned;
         this.buttonFinalize.visible = this.bookGettingSigned;
         this.buttonFinalize.enabled = this.bookTitle.trim().length() > 0;
      }
   }

   private void addNewPage() {
      if (this.bookPages != null && this.bookPages.tagCount() < 50) {
         this.bookPages.appendTag(new NBTTagString(""));
         this.bookTotalPages++;
         this.bookIsModified = true;
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(bookGuiTextures);
      int n = (this.width - this.bookImageWidth) / 2;
      byte b = 2;
      this.drawTexturedModalRect(n, b, 0, 0, this.bookImageWidth, this.bookImageHeight);
      if (Augustus.getInstance().getModuleManager().antiStrike.shouldHideBooks()) {
         super.drawScreen(mouseX, mouseY, partialTicks);
      } else {
         if (this.bookGettingSigned) {
            String bookTitle2 = this.bookTitle;
            if (this.bookIsUnsigned) {
               if (this.updateCount / 6 % 2 == 0) {
                  bookTitle2 = bookTitle2 + "" + EnumChatFormatting.BLACK + "_";
               } else {
                  bookTitle2 = bookTitle2 + "" + EnumChatFormatting.GRAY + "_";
               }
            }

            String str = I18n.format("book.editTitle", new Object[0]);
            int stringWidth = this.fontRendererObj.getStringWidth(str);
            this.fontRendererObj.drawString(str, n + 36 + (116 - stringWidth) / 2, b + 16 + 16, 0);
            int stringWidth2 = this.fontRendererObj.getStringWidth(bookTitle2);
            this.fontRendererObj.drawString(bookTitle2, n + 36 + (116 - stringWidth2) / 2, b + 48, 0);
            Object obj = I18n.format("book.byAuthor", new Object[]{this.editingPlayer.getName()});
            int stringWidth3 = this.fontRendererObj.getStringWidth((String)obj);
            this.fontRendererObj.drawString(EnumChatFormatting.DARK_GRAY.toString() + obj, n + 36 + (116 - stringWidth3) / 2, b + 48 + 10, 0);
            String str2 = I18n.format("book.finalizeWarning", new Object[0]);
            this.fontRendererObj.drawSplitString(str2, n + 36, b + 80, 116, 0);
         } else {
            String str3 = I18n.format("book.pageIndicator", new Object[]{this.currPage + 1, this.bookTotalPages});
            String str4 = "";
            if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
               str4 = this.bookPages.getStringTagAt(this.currPage);
            }

            if (this.bookIsUnsigned) {
               if (this.fontRendererObj.getBidiFlag()) {
                  str4 = str4 + "_";
               } else if (this.updateCount / 6 % 2 == 0) {
                  str4 = str4 + "" + EnumChatFormatting.BLACK + "_";
               } else {
                  str4 = str4 + "" + EnumChatFormatting.GRAY + "_";
               }
            } else if (this.cachedPage != this.currPage) {
               if (ItemEditableBook.validBookTagContents(this.bookObj.getTagCompound())) {
                  try {
                     IChatComponent component = IChatComponent.Serializer.jsonToComponent(str4);
                     this.cachedComponents = component != null ? GuiUtilRenderComponents.splitText(component, 116, this.fontRendererObj, true, true) : null;
                  } catch (JsonParseException e) {
                     this.cachedComponents = null;
                  }
               } else {
                  this.cachedComponents = Lists.newArrayList(new ChatComponentText(EnumChatFormatting.DARK_RED + "* Invalid book tag *"));
               }

               this.cachedPage = this.currPage;
            }

            int stringWidth4 = this.fontRendererObj.getStringWidth(str3);
            this.fontRendererObj.drawString(str3, n - stringWidth4 + this.bookImageWidth - 44, b + 16, 0);
            if (this.cachedComponents == null) {
               this.fontRendererObj.drawSplitString(str4, n + 36, b + 16 + 16, 116, 0);
            } else {
               int n2 = Math.min(128 / this.fontRendererObj.FONT_HEIGHT, this.cachedComponents.size());

               for (int i = 0; i < n2; i++) {
                  IChatComponent component2 = (IChatComponent)this.cachedComponents.get(i);
                  this.fontRendererObj.drawString(component2.getUnformattedText(), n + 36, b + 16 + 16 + i * this.fontRendererObj.FONT_HEIGHT, 0);
               }

               IChatComponent clickedComponentAt = this.getClickedComponentAt(mouseX, mouseY);
               if (clickedComponentAt != null) {
                  this.handleComponentHover(clickedComponentAt, mouseX, mouseY);
               }
            }
         }

         super.drawScreen(mouseX, mouseY, partialTicks);
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (this.bookIsUnsigned) {
         if (this.bookGettingSigned) {
            this.keyTypedInTitle(typedChar, keyCode);
         } else {
            this.keyTypedInBook(typedChar, keyCode);
         }
      }
   }

   public GuiScreenBook(EntityPlayer player, ItemStack stack, boolean flag) {
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
      this.editingPlayer = player;
      this.bookObj = stack;
      this.bookIsUnsigned = flag;
      if (stack.hasTagCompound()) {
         this.bookPages = stack.getTagCompound().getTagList("pages", 8);
         if (this.bookPages != null) {
            this.bookPages = (NBTTagList)this.bookPages.copy();
            this.bookTotalPages = this.bookPages.tagCount();
            if (this.bookTotalPages < 1) {
               this.bookTotalPages = 1;
            }
         }
      }

      if (this.bookPages == null && flag) {
         this.bookPages = new NBTTagList();
         this.bookPages.appendTag(new NBTTagString(""));
         this.bookTotalPages = 1;
      }
   }

   private void pageSetCurrent(String str) {
      if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
         this.bookPages.set(this.currPage, new NBTTagString(str));
         this.bookIsModified = true;
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 0) {
            GuiScreen screen = (GuiScreen)null;
            this.mc.displayGuiScreen(screen);
            this.sendBookToServer(false);
         } else if (button.id == 3 && this.bookIsUnsigned) {
            this.bookGettingSigned = true;
         } else if (button.id == 1) {
            if (this.currPage < this.bookTotalPages - 1) {
               this.currPage++;
            } else if (this.bookIsUnsigned) {
               this.addNewPage();
               if (this.currPage < this.bookTotalPages - 1) {
                  this.currPage++;
               }
            }
         } else if (button.id == 2) {
            if (this.currPage > 0) {
               this.currPage--;
            }
         } else if (button.id == 5 && this.bookGettingSigned) {
            this.sendBookToServer(true);
            GuiScreen screen2 = (GuiScreen)null;
            this.mc.displayGuiScreen(screen2);
         } else if (button.id == 4 && this.bookGettingSigned) {
            this.bookGettingSigned = false;
         }

         this.updateButtons();
      }
   }

   public IChatComponent getClickedComponentAt(int n, int n2) {
      if (this.cachedComponents == null) {
         return null;
      } else {
         int n3 = n - (this.width - this.bookImageWidth) / 2 - 36;
         int n4 = n2 - 2 - 16 - 16;
         if (n3 >= 0 && n4 >= 0) {
            int n5 = Math.min(128 / this.fontRendererObj.FONT_HEIGHT, this.cachedComponents.size());
            if (n3 <= 116 && n4 < this.mc.fontRendererObj.FONT_HEIGHT * n5 + n5) {
               int n6 = n4 / this.mc.fontRendererObj.FONT_HEIGHT;
               if (n6 >= 0 && n6 < this.cachedComponents.size()) {
                  IChatComponent component = (IChatComponent)this.cachedComponents.get(n6);
                  int n7 = 0;

                  for (IChatComponent component2 : (Iterable<IChatComponent>)(component)) {
                     if (component2 instanceof ChatComponentText) {
                        n7 += this.mc.fontRendererObj.getStringWidth(((ChatComponentText)component2).getChatComponentText_TextValue());
                        if (n7 > n3) {
                           return component2;
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

   private void keyTypedInBook(char ch, int n) {
      if (GuiScreen.isKeyComboCtrlV(n)) {
         this.pageInsertIntoCurrent(GuiScreen.getClipboardString());
      } else {
         switch (n) {
            case 14:
               String str = this.pageGetCurrent();
               if (str.length() > 0) {
                  this.pageSetCurrent(str.substring(0, str.length() - 1));
               }

               return;
            case 28:
            case 156:
               this.pageInsertIntoCurrent("\n");
               return;
            default:
               if (ChatAllowedCharacters.isAllowedCharacter(ch)) {
                  this.pageInsertIntoCurrent(Character.toString(ch));
               }
         }
      }
   }

   private void sendBookToServer(boolean flag) throws IOException {
      if (this.bookIsUnsigned && this.bookIsModified && this.bookPages != null) {
         while (this.bookPages.tagCount() > 1) {
            if (this.bookPages.getStringTagAt(this.bookPages.tagCount() - 1).length() != 0) {
               break;
            }

            this.bookPages.removeTag(this.bookPages.tagCount() - 1);
         }

         if (this.bookObj.hasTagCompound()) {
            this.bookObj.getTagCompound().setTag("pages", this.bookPages);
         } else {
            this.bookObj.setTagInfo("pages", this.bookPages);
         }

         String str = "MC|BEdit";
         if (flag) {
            str = "MC|BSign";
            this.bookObj.setTagInfo("author", new NBTTagString(this.editingPlayer.getName()));
            this.bookObj.setTagInfo("title", new NBTTagString(this.bookTitle.trim()));

            for (int i = 0; i < this.bookPages.tagCount(); i++) {
               String stringTagAt2 = this.bookPages.getStringTagAt(i);
               stringTagAt2 = IChatComponent.Serializer.componentToJson(new ChatComponentText(stringTagAt2));
               this.bookPages.set(i, new NBTTagString(stringTagAt2));
            }

            this.bookObj.setItem(Items.written_book);
         }

         PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
         buf.writeItemStackToBuffer(this.bookObj);
         this.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload(str, buf));
      }
   }

   private void pageInsertIntoCurrent(String str) {
      String str3 = this.pageGetCurrent() + str;
      if (this.fontRendererObj.splitStringWidth(str3 + "" + EnumChatFormatting.BLACK + "_", 118) <= 128 && str3.length() < 256) {
         this.pageSetCurrent(str3);
      }
   }

   public static ResourceLocation accessBookGuiTextures() {
      return bookGuiTextures;
   }

   private String pageGetCurrent() {
      return this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount() ? this.bookPages.getStringTagAt(this.currPage) : "";
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.updateCount++;
   }

   private void keyTypedInTitle(char ch, int n) throws IOException {
      switch (n) {
         case 14:
            if (!this.bookTitle.isEmpty()) {
               this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
               this.updateButtons();
            }

            return;
         case 28:
         case 156:
            if (!this.bookTitle.isEmpty()) {
               this.sendBookToServer(true);
               GuiScreen screen = (GuiScreen)null;
               this.mc.displayGuiScreen(screen);
            }

            return;
         default:
            if (this.bookTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(ch)) {
               this.bookTitle = this.bookTitle + ch;
               this.updateButtons();
               this.bookIsModified = true;
            }
      }
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      if (this.bookIsUnsigned) {
         this.buttonList
            .add(this.buttonSign = new GuiButton(3, this.width / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.format("book.signButton", new Object[0])));
         this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.format("gui.done", new Object[0])));
         this.buttonList
            .add(this.buttonFinalize = new GuiButton(5, this.width / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.format("book.finalizeButton", new Object[0])));
         this.buttonList.add(this.buttonCancel = new GuiButton(4, this.width / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.format("gui.cancel", new Object[0])));
      } else {
         this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 100, 4 + this.bookImageHeight, 200, 20, I18n.format("gui.done", new Object[0])));
      }

      int n = (this.width - this.bookImageWidth) / 2;
      byte b = 2;
      this.buttonList.add(this.buttonNextPage = new GuiScreenBook.NextPageButton(1, n + 120, b + 154, true));
      this.buttonList.add(this.buttonPreviousPage = new GuiScreenBook.NextPageButton(2, n + 38, b + 154, false));
      this.updateButtons();
   }

   @Override
   public boolean handleComponentClick(IChatComponent component) {
      ClickEvent clickEvent = component == null ? null : component.getChatStyle().getChatClickEvent();
      if (clickEvent == null) {
         return false;
      } else if (clickEvent.getAction() == ClickEvent.Action.CHANGE_PAGE) {
         String value = clickEvent.getValue();

         try {
            int n = Integer.parseInt(value) - 1;
            if (n >= 0 && n < this.bookTotalPages && n != this.currPage) {
               this.currPage = n;
               this.updateButtons();
               return true;
            }
         } catch (Throwable e) {
         }

         return false;
      } else {
         boolean flag = super.handleComponentClick(component);
         if (flag && clickEvent.getAction() == ClickEvent.Action.RUN_COMMAND) {
            GuiScreen screen = (GuiScreen)null;
            this.mc.displayGuiScreen(screen);
         }

         return flag;
      }
   }

   @Override
   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0) {
         if (this.handleComponentClick(this.getClickedComponentAt(mouseX, mouseY))) {
            return;
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }



   public static class NextPageButton extends GuiButton {
      private final boolean isForward;

      @Override
      public void drawButton(Minecraft mc, int n, int n2) {
         if (this.visible) {
            boolean flag = n >= this.xPosition && n2 >= this.yPosition && n < this.xPosition + this.width && n2 < this.yPosition + this.height;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(GuiScreenBook.accessBookGuiTextures());
            int n3 = 0;
            int n4 = 192;
            if (flag) {
               n3 += 23;
            }

            if (!this.isForward) {
               n4 += 13;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, n3, n4, 23, 13);
         }
      }

      public NextPageButton(int n, int n2, int n3, boolean flag) {
         super(n, n2, n3, 23, 13, "");
         this.isForward = flag;
      }
   }
}
