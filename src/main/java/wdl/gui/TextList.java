package wdl.gui;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class TextList extends GuiListExtended {
   public final int topMargin;
   public final int bottomMargin;
   private List entries;

   public void addBlankLine() {
      this.entries.add(new TextEntry(this.mc, "", 16777215));
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      return (GuiListExtended.IGuiListEntry)this.entries.get(n);
   }

   @Override
   public int getScrollBarX() {
      return this.width - 10;
   }

   @Override
   public int getSize() {
      return this.entries.size();
   }

   public TextList(Minecraft mc2, int n, int n2, int n3, int n4) {
      super(mc2, n, n2, n3, n2 - n4, mc2.fontRendererObj.FONT_HEIGHT + 1);
      this.topMargin = n3;
      this.bottomMargin = n4;
      this.entries = new ArrayList();
   }

   public void addLine(String str) {

      for (String str2 : (Iterable<String>)(Utils.wordWrap(str, this.getListWidth()))) {
         this.entries.add(new TextEntry(this.mc, str2, 16777215));
      }
   }

   public void clearLines() {
      this.entries.clear();
   }

   public void addLinkLine(String str, String str2) {

      for (String str3 : (Iterable<String>)(Utils.wordWrap(str, this.getListWidth()))) {
         this.entries.add(new LinkEntry(this.mc, str3, str2));
      }
   }

   @Override
   public int getListWidth() {
      return this.width - 18;
   }
}
