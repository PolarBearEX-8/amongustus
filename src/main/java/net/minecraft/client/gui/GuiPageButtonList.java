package net.minecraft.client.gui;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IntHashMap;

public class GuiPageButtonList extends GuiListExtended {
   private final List entries = Lists.newArrayList();
   private final IntHashMap componentMap = new IntHashMap();
   private final List editBoxes = Lists.newArrayList();
   private final GuiPageButtonList.GuiListEntry[][] pages;
   private int page;
   private GuiPageButtonList.GuiResponder responder;
   private Gui focusedControl;

   public int getPage() {
      return this.page;
   }

   private void populateEntries() {
      this.entries.clear();

      for (int i = 0; i < this.pages[this.page].length; i += 2) {
         GuiPageButtonList.GuiListEntry guiListEntry = this.pages[this.page][i];
         GuiPageButtonList.GuiListEntry guiListEntry2 = i < this.pages[this.page].length - 1 ? this.pages[this.page][i + 1] : null;
         Gui gui = (Gui)this.componentMap.lookup(guiListEntry.getId());
         Gui gui2 = guiListEntry2 != null ? (Gui)this.componentMap.lookup(guiListEntry2.getId()) : null;
         GuiPageButtonList.GuiEntry guiEntry = new GuiPageButtonList.GuiEntry(gui, gui2);
         this.entries.add(guiEntry);
      }
   }

   private GuiListButton createButton(int n, int n2, GuiPageButtonList.GuiButtonEntry guiButtonEntry) {
      GuiListButton guiListButton = new GuiListButton(this.responder, guiButtonEntry.getId(), n, n2, guiButtonEntry.getCaption(), guiButtonEntry.isInitialValue());
      guiListButton.visible = guiButtonEntry.isStartVisible();
      return guiListButton;
   }

   private void markVisibility(int n, int n2) {
      for (GuiPageButtonList.GuiListEntry guiListEntry : this.pages[n]) {
         if (guiListEntry != null) {
            this.setComponentVisibility((Gui)this.componentMap.lookup(guiListEntry.getId()), false);
         }
      }

      for (GuiPageButtonList.GuiListEntry guiListEntry2 : this.pages[n2]) {
         if (guiListEntry2 != null) {
            this.setComponentVisibility((Gui)this.componentMap.lookup(guiListEntry2.getId()), true);
         }
      }
   }

   public GuiPageButtonList(
      Minecraft mc2, int n, int n2, int n3, int n4, int n5, GuiPageButtonList.GuiResponder guiResponder, GuiPageButtonList.GuiListEntry[][] guiListEntrysArr
   ) {
      super(mc2, n, n2, n3, n4, n5);
      this.responder = guiResponder;
      this.pages = guiListEntrysArr;
      this.centerListVertically = false;
      this.populateComponents();
      this.populateEntries();
   }

   public Gui getFocusedControl() {
      return this.focusedControl;
   }

   @Override
   public boolean mouseClicked(int mouseX2, int mouseY2, int mouseButton) {
      boolean flag = super.mouseClicked(mouseX2, mouseY2, mouseButton);
      int n = this.getSlotIndexFromScreenCoords(mouseX2, mouseY2);
      if (n >= 0) {
         GuiPageButtonList.GuiEntry listEntry = this.getListEntry(n);
         if (this.focusedControl != GuiPageButtonList.GuiEntry.getFocusedComponentStatic(listEntry) && this.focusedControl != null && this.focusedControl instanceof GuiTextField) {
            ((GuiTextField)this.focusedControl).setFocused(false);
         }

         this.focusedControl = GuiPageButtonList.GuiEntry.getFocusedComponentStatic(listEntry);
      }

      return flag;
   }

   public void setPage(int n) {
      if (n != this.page) {
         int page2 = this.page;
         this.page = n;
         this.populateEntries();
         this.markVisibility(page2, n);
         this.amountScrolled = 0.0F;
      }
   }

   public void onKeyPressed(char ch, int n) {
      if (this.focusedControl instanceof GuiTextField) {
         GuiTextField focusedControl2 = (GuiTextField)this.focusedControl;
         if (!GuiScreen.isKeyComboCtrlV(n)) {
            if (n == 15) {
               focusedControl2.setFocused(false);
               int n2 = this.editBoxes.indexOf(this.focusedControl);
               if (GuiScreen.isShiftKeyDown()) {
                  if (n2 == 0) {
                     n2 = this.editBoxes.size() - 1;
                  } else {
                     n2--;
                  }
               } else if (n2 == this.editBoxes.size() - 1) {
                  n2 = 0;
               } else {
                  n2++;
               }

               this.focusedControl = (Gui)this.editBoxes.get(n2);
               focusedControl2 = (GuiTextField)this.focusedControl;
               focusedControl2.setFocused(true);
               int n3 = focusedControl2.yPosition + this.slotHeight;
               int yPosition = focusedControl2.yPosition;
               if (n3 > this.bottom) {
                  this.amountScrolled = this.amountScrolled + (float)(n3 - this.bottom);
               } else if (yPosition < this.top) {
                  this.amountScrolled = (float)yPosition;
               }
            } else {
               focusedControl2.textboxKeyTyped(ch, n);
            }
         } else {
            String[] strings = GuiScreen.getClipboardString().split(";");
            int n4 = this.editBoxes.indexOf(this.focusedControl);
            int n5 = n4;

            for (String str : strings) {
               ((GuiTextField)this.editBoxes.get(n5)).setText(str);
               if (n5 == this.editBoxes.size() - 1) {
                  n5 = 0;
               } else {
                  n5++;
               }

               if (n5 == n4) {
                  break;
               }
            }
         }
      }
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      return this.getListEntry(n);
   }

   @Override
   public int getListWidth() {
      return 400;
   }

   public Gui getComponent(int n) {
      return (Gui)this.componentMap.lookup(n);
   }

   public void setActive(boolean flag) {
      for (GuiPageButtonList.GuiEntry guiEntry : (Iterable<GuiPageButtonList.GuiEntry>)(this.entries)) {
         if (GuiPageButtonList.GuiEntry.getComponent1Static(guiEntry) instanceof GuiButton) {
            ((GuiButton)GuiPageButtonList.GuiEntry.getComponent1Static(guiEntry)).enabled = flag;
         }

         if (GuiPageButtonList.GuiEntry.getComponent2Static(guiEntry) instanceof GuiButton) {
            ((GuiButton)GuiPageButtonList.GuiEntry.getComponent2Static(guiEntry)).enabled = flag;
         }
      }
   }

   private Gui createEntry(GuiPageButtonList.GuiListEntry guiListEntry, int n, boolean flag) {
      return (Gui)(guiListEntry instanceof GuiPageButtonList.GuiSlideEntry
         ? this.createSlider(this.width / 2 - 155 + n, 0, (GuiPageButtonList.GuiSlideEntry)guiListEntry)
         : (
            guiListEntry instanceof GuiPageButtonList.GuiButtonEntry
               ? this.createButton(this.width / 2 - 155 + n, 0, (GuiPageButtonList.GuiButtonEntry)guiListEntry)
               : (
                  guiListEntry instanceof GuiPageButtonList.EditBoxEntry
                     ? this.createTextField(this.width / 2 - 155 + n, 0, (GuiPageButtonList.EditBoxEntry)guiListEntry)
                     : (
                        guiListEntry instanceof GuiPageButtonList.GuiLabelEntry
                           ? this.createLabel(this.width / 2 - 155 + n, 0, (GuiPageButtonList.GuiLabelEntry)guiListEntry, flag)
                           : null
                     )
               )
         ));
   }

   public void previousPage() {
      if (this.page > 0) {
         this.setPage(this.page - 1);
      }
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 32;
   }

   private GuiLabel createLabel(int n, int n2, GuiPageButtonList.GuiLabelEntry guiLabelEntry, boolean flag) {
      GuiLabel guiLabel;
      if (flag) {
         guiLabel = new GuiLabel(this.mc.fontRendererObj, guiLabelEntry.getId(), n, n2, this.width - n * 2, 20, -1);
      } else {
         guiLabel = new GuiLabel(this.mc.fontRendererObj, guiLabelEntry.getId(), n, n2, 150, 20, -1);
      }

      guiLabel.visible = guiLabelEntry.isStartVisible();
      guiLabel.addLine(guiLabelEntry.getCaption());
      guiLabel.setCentered();
      return guiLabel;
   }

   private void setComponentVisibility(Gui gui, boolean flag) {
      if (gui instanceof GuiButton) {
         ((GuiButton)gui).visible = flag;
      } else if (gui instanceof GuiTextField) {
         ((GuiTextField)gui).setVisible(flag);
      } else if (gui instanceof GuiLabel) {
         ((GuiLabel)gui).visible = flag;
      }
   }

   public void nextPage() {
      if (this.page < this.pages.length - 1) {
         this.setPage(this.page + 1);
      }
   }

   public int getPages() {
      return this.pages.length;
   }

   private GuiSlider createSlider(int n, int n2, GuiPageButtonList.GuiSlideEntry guiSlideEntry) {
      GuiSlider guiSlider = new GuiSlider(
         this.responder,
         guiSlideEntry.getId(),
         n,
         n2,
         guiSlideEntry.getCaption(),
         guiSlideEntry.getMinValue(),
         guiSlideEntry.getMaxValue(),
         guiSlideEntry.getInitialValue(),
         guiSlideEntry.getFormatter()
      );
      guiSlider.visible = guiSlideEntry.isStartVisible();
      return guiSlider;
   }

   @Override
   public int getSize() {
      return this.entries.size();
   }

   private void populateComponents() {
      for (GuiPageButtonList.GuiListEntry[] guiListEntrys : this.pages) {
         for (int i = 0; i < guiListEntrys.length; i += 2) {
            GuiPageButtonList.GuiListEntry guiListEntry = guiListEntrys[i];
            GuiPageButtonList.GuiListEntry guiListEntry2 = i < guiListEntrys.length - 1 ? guiListEntrys[i + 1] : null;
            Gui gui = this.createEntry(guiListEntry, 0, guiListEntry2 == null);
            Gui gui2 = this.createEntry(guiListEntry2, 160, guiListEntry == null);
            GuiPageButtonList.GuiEntry guiEntry = new GuiPageButtonList.GuiEntry(gui, gui2);
            this.entries.add(guiEntry);
            if (guiListEntry != null && gui != null) {
               this.componentMap.addKey(guiListEntry.getId(), gui);
               if (gui instanceof GuiTextField) {
                  this.editBoxes.add((GuiTextField)gui);
               }
            }

            if (guiListEntry2 != null && gui2 != null) {
               this.componentMap.addKey(guiListEntry2.getId(), gui2);
               if (gui2 instanceof GuiTextField) {
                  this.editBoxes.add((GuiTextField)gui2);
               }
            }
         }
      }
   }

   public GuiPageButtonList.GuiEntry getListEntry(int n) {
      return (GuiPageButtonList.GuiEntry)this.entries.get(n);
   }

   private GuiTextField createTextField(int n, int n2, GuiPageButtonList.EditBoxEntry editBoxEntry) {
      GuiTextField guiTextField = new GuiTextField(editBoxEntry.getId(), this.mc.fontRendererObj, n, n2, 150, 20);
      guiTextField.setText(editBoxEntry.getCaption());
      guiTextField.setGuiResponder(this.responder);
      guiTextField.setVisible(editBoxEntry.isStartVisible());
      guiTextField.setValidator(editBoxEntry.getFilter());
      return guiTextField;
   }


   public static class GuiEntry implements GuiListExtended.IGuiListEntry {
      private final Minecraft mc = Minecraft.getMinecraft();
      private final Gui component1;
      private final Gui component2;
      private Gui focusedComponent;

      @Override
      public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
         boolean flag = this.clickComponent(this.component1, n2, n3, n4);
         boolean flag2 = this.clickComponent(this.component2, n2, n3, n4);
         return flag || flag2;
      }

      public Gui getComponent2() {
         return this.component2;
      }

      private void renderLabel(GuiLabel guiLabel, int n, int n2, int n3, boolean flag) {
         guiLabel.y = n;
         if (!flag) {
            guiLabel.drawLabel(this.mc, n2, n3);
         }
      }

      private void renderTextField(GuiTextField guiTextField, int n, boolean flag) {
         guiTextField.yPosition = n;
         if (!flag) {
            guiTextField.drawTextBox();
         }
      }

      private void clickTextField(GuiTextField guiTextField, int n, int n2, int n3) {
         guiTextField.mouseClicked(n, n2, n3);
         if (guiTextField.isFocused()) {
            this.focusedComponent = guiTextField;
         }
      }

      private void releaseButton(GuiButton button, int n, int n2, int n3) {
         button.mouseReleased(n, n2);
      }

      private void renderComponent(Gui gui, int n, int n2, int n3, boolean flag) {
         if (gui != null) {
            if (gui instanceof GuiButton) {
               this.renderButton((GuiButton)gui, n, n2, n3, flag);
            } else if (gui instanceof GuiTextField) {
               this.renderTextField((GuiTextField)gui, n, flag);
            } else if (gui instanceof GuiLabel) {
               this.renderLabel((GuiLabel)gui, n, n2, n3, flag);
            }
         }
      }

      private boolean clickComponent(Gui gui, int n, int n2, int n3) {
         if (gui == null) {
            return false;
         } else if (gui instanceof GuiButton) {
            return this.clickButton((GuiButton)gui, n, n2, n3);
         } else {
            if (gui instanceof GuiTextField) {
               this.clickTextField((GuiTextField)gui, n, n2, n3);
            }

            return false;
         }
      }

      private boolean clickButton(GuiButton button, int n, int n2, int n3) {
         boolean flag = button.mousePressed(this.mc, n, n2);
         if (flag) {
            this.focusedComponent = button;
         }

         return flag;
      }

      private void renderButton(GuiButton button, int n, int n2, int n3, boolean flag) {
         button.yPosition = n;
         if (!flag) {
            button.drawButton(this.mc, n2, n3);
         }
      }

      private void releaseComponent(Gui gui, int n, int n2, int n3) {
         if (gui != null && gui instanceof GuiButton) {
            this.releaseButton((GuiButton)gui, n, n2, n3);
         }
      }

      public Gui getComponent1() {
         return this.component1;
      }

      public static Gui getComponent2Static(GuiEntry guiEntry) {
         return guiEntry.component2;
      }

      @Override
      public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
         this.renderComponent(this.component1, n3, n6, n7, false);
         this.renderComponent(this.component2, n3, n6, n7, false);
      }

      public static Gui getComponent1Static(GuiEntry guiEntry) {
         return guiEntry.component1;
      }

      @Override
      public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
         this.releaseComponent(this.component1, n2, n3, n4);
         this.releaseComponent(this.component2, n2, n3, n4);
      }

      public GuiEntry(Gui gui, Gui gui2) {
         this.component1 = gui;
         this.component2 = gui2;
      }

      public static Gui getFocusedComponentStatic(GuiEntry guiEntry) {
         return guiEntry.focusedComponent;
      }

      @Override
      public void setSelected(int n, int n2, int n3) {
         this.renderComponent(this.component1, n3, 0, 0, true);
         this.renderComponent(this.component2, n3, 0, 0, true);
      }
   }

   public static class EditBoxEntry extends GuiPageButtonList.GuiListEntry {
      private final Predicate filter;

      public Predicate getFilter() {
         return this.filter;
      }

      public EditBoxEntry(int n, String str, boolean flag, Predicate predicate) {
         super(n, str, flag);
         this.filter = (Predicate)MoreObjects.firstNonNull(predicate, Predicates.alwaysTrue());
      }
   }

   public static class GuiListEntry {
      private final int id;
      private final String caption;
      private final boolean startVisible;

      public boolean isStartVisible() {
         return this.startVisible;
      }

      public GuiListEntry(int n, String str, boolean flag) {
         this.id = n;
         this.caption = str;
         this.startVisible = flag;
      }

      public String getCaption() {
         return this.caption;
      }

      public int getId() {
         return this.id;
      }
   }

   public interface GuiResponder {
      void onTick(int n, float f);

      void setEntryValue2(int n, boolean flag);

      void setEntryValue(int n, String str);
   }

   public static class GuiLabelEntry extends GuiPageButtonList.GuiListEntry {
      public GuiLabelEntry(int n, String str, boolean flag) {
         super(n, str, flag);
      }
   }

   public static class GuiSlideEntry extends GuiPageButtonList.GuiListEntry {
      private final GuiSlider.FormatHelper formatter;
      private final float minValue;
      private final float maxValue;
      private final float initialValue;

      public GuiSlideEntry(int n, String str, boolean flag, GuiSlider.FormatHelper formatHelper, float f, float f2, float f3) {
         super(n, str, flag);
         this.formatter = formatHelper;
         this.minValue = f;
         this.maxValue = f2;
         this.initialValue = f3;
      }

      public float getInitialValue() {
         return this.initialValue;
      }

      public GuiSlider.FormatHelper getFormatter() {
         return this.formatter;
      }

      public float getMinValue() {
         return this.minValue;
      }

      public float getMaxValue() {
         return this.maxValue;
      }
   }

   public static class GuiButtonEntry extends GuiPageButtonList.GuiListEntry {
      private final boolean initialValue;

      public boolean isInitialValue() {
         return this.initialValue;
      }

      public GuiButtonEntry(int n, String str, boolean flag, boolean flag2) {
         super(n, str, flag);
         this.initialValue = flag2;
      }
   }
}
