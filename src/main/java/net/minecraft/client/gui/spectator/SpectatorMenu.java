package net.minecraft.client.gui.spectator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.spectator.categories.SpectatorDetails;

public class SpectatorMenu {
   private static final ISpectatorMenuObject CLOSE_ITEM = new SpectatorMenu.EndSpectatorObject(null);
   private static final ISpectatorMenuObject SCROLL_LEFT = new SpectatorMenu.MoveMenuObject(-1, true);
   private static final ISpectatorMenuObject SCROLL_RIGHT_ENABLED = new SpectatorMenu.MoveMenuObject(1, true);
   private static final ISpectatorMenuObject SCROLL_RIGHT_DISABLED = new SpectatorMenu.MoveMenuObject(1, false);
   public static final ISpectatorMenuObject EMPTY_SLOT = new SpectatorMenu.DefaultSpectatorMenuObject();
   private final ISpectatorMenuRecipient listener;
   private final List previousCategories = Lists.newArrayList();
   private ISpectatorMenuView category = new BaseSpectatorGroup();
   private int selectedSlot = -1;
   private int page;

   public ISpectatorMenuObject getItem(int n) {
      int n2 = n + this.page * 6;
      return this.page > 0 && n == 0
         ? SCROLL_LEFT
         : (
            n == 7
               ? (n2 < this.category.getItems().size() ? SCROLL_RIGHT_ENABLED : SCROLL_RIGHT_DISABLED)
               : (
                  n == 8
                     ? CLOSE_ITEM
                     : (
                        n2 >= 0 && n2 < this.category.getItems().size()
                           ? (ISpectatorMenuObject)MoreObjects.firstNonNull(this.category.getItems().get(n2), EMPTY_SLOT)
                           : EMPTY_SLOT
                     )
               )
         );
   }

   public List getItems() {
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i <= 8; i++) {
         list.add(this.getItem(i));
      }

      return list;
   }

   public int getSelectedSlot() {
      return this.selectedSlot;
   }

   public SpectatorDetails getCurrentPage() {
      return new SpectatorDetails(this.category, this.getItems(), this.selectedSlot);
   }

   public ISpectatorMenuObject getSelectedItem() {
      return this.getItem(this.selectedSlot);
   }

   public static int assignPage(SpectatorMenu spectatorMenu, int n) {
      return spectatorMenu.page = n;
   }

   public SpectatorMenu(ISpectatorMenuRecipient spectatorMenuRecipient) {
      this.listener = spectatorMenuRecipient;
   }

   public void selectSlot(int n) {
      ISpectatorMenuObject item = this.getItem(n);
      if (item != EMPTY_SLOT) {
         if (this.selectedSlot == n && item.isEnabled()) {
            item.selectItem(this);
         } else {
            this.selectedSlot = n;
         }
      }
   }

   public void selectCategory(ISpectatorMenuView spectatorMenuView) {
      this.previousCategories.add(this.getCurrentPage());
      this.category = spectatorMenuView;
      this.selectedSlot = -1;
      this.page = 0;
   }

   public ISpectatorMenuView getCategory() {
      return this.category;
   }

   public void exit() {
      this.listener.onSpectatorMenuClosed(this);
   }


   public static final class DefaultSpectatorMenuObject implements ISpectatorMenuObject {
      @Override
      public IChatComponent getSpectatorName() {
         return new ChatComponentText("");
      }

      @Override
      public void selectItem(SpectatorMenu spectatorMenu) {
      }

      @Override
      public void renderIcon(float f, int n) {
      }

      @Override
      public boolean isEnabled() {
         return false;
      }
   }

   public static class MoveMenuObject implements ISpectatorMenuObject {
      private final int direction;
      private final boolean enabled;

      @Override
      public void selectItem(SpectatorMenu spectatorMenu) {
         SpectatorMenu.assignPage(spectatorMenu, this.direction);
      }

      public MoveMenuObject(int n, boolean flag) {
         this.direction = n;
         this.enabled = flag;
      }

      @Override
      public void renderIcon(float f, int n) {
         Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.SPECTATOR_WIDGETS_TEXTURE);
         if (this.direction < 0) {
            Gui.drawModalRectWithCustomSizedTexture(0, 0, 144.0F, 0.0F, 16, 16, 256.0F, 256.0F);
         } else {
            Gui.drawModalRectWithCustomSizedTexture(0, 0, 160.0F, 0.0F, 16, 16, 256.0F, 256.0F);
         }
      }

      @Override
      public IChatComponent getSpectatorName() {
         return this.direction < 0 ? new ChatComponentText("Previous Page") : new ChatComponentText("Next Page");
      }

      @Override
      public boolean isEnabled() {
         return this.enabled;
      }
   }

   public static class EndSpectatorObject implements ISpectatorMenuObject {
      public EndSpectatorObject(SpectatorMenu.DefaultSpectatorMenuObject obj) {
         this();
      }

      @Override
      public void selectItem(SpectatorMenu spectatorMenu) {
         spectatorMenu.exit();
      }

      @Override
      public IChatComponent getSpectatorName() {
         return new ChatComponentText("Close menu");
      }

      @Override
      public boolean isEnabled() {
         return true;
      }

      @Override
      public void renderIcon(float f, int n) {
         Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.SPECTATOR_WIDGETS_TEXTURE);
         Gui.drawModalRectWithCustomSizedTexture(0, 0, 128.0F, 0.0F, 16, 16, 256.0F, 256.0F);
      }

      private EndSpectatorObject() {
      }
   }
}
