package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.util.EnumChatFormatting;

public abstract class GuiResourcePackList extends GuiListExtended {
   public final Minecraft mc;
   public final List list;

   @Override
   public int getScrollBarX() {
      return this.right - 6;
   }

   public abstract String getListHeader();

   @Override
   public int getListWidth() {
      return this.width;
   }

   public GuiResourcePackList(Minecraft mc2, int n, int n2, List list2) {
      super(mc2, n, n2, 32, n2 - 55 + 4, 36);
      this.mc = mc2;
      this.list = list2;
      this.centerListVertically = false;
      this.setHasListHeader(true, (int)((float)mc2.fontRendererObj.FONT_HEIGHT * 1.5F));
   }

   public ResourcePackListEntry getListEntry(int n) {
      return (ResourcePackListEntry)this.getList().get(n);
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      return this.getListEntry(n);
   }

   @Override
   public void drawListHeader(int n, int n2, Tessellator tessellator) {
      String str = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.BOLD + this.getListHeader();
      this.mc.fontRendererObj.drawString(str, n + this.width / 2 - this.mc.fontRendererObj.getStringWidth(str) / 2, Math.min(this.top + 3, n2), 16777215);
   }

   public List getList() {
      return this.list;
   }

   @Override
   public int getSize() {
      return this.getList().size();
   }
}
