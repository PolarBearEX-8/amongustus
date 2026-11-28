package net.augustus.cosmetics;

public abstract class CosmeticsMenuEntry extends CosmeticsMenuElement {
   public String description;
   public boolean expanded;
   public boolean popupActive;

   public CosmeticsMenuEntry(String str) {
      super(str);
   }

   public void updateInteractable(boolean flag) {
      this.interactable = flag;
      if (this.expanded && !this.interactable) {
         this.expanded = false;
      }
   }

   public boolean canExpand() {
      return this.hasChildren() && this.interactable;
   }

   public boolean isPopupActive() {
      return this.popupActive;
   }

   public void keyTyped(char typedChar, int keyCode) {
      if (this.isExpanded()) {
         for (CosmeticsMenuEntry cosmeticsMenuEntry : (Iterable<CosmeticsMenuEntry>)(this.children)) {
            cosmeticsMenuEntry.keyTyped(typedChar, keyCode);
         }
      }
   }

   public CosmeticsMenuEntry withPopupActive(boolean flag) {
      this.popupActive = flag;
      return this;
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      if (this.isExpanded()) {
         for (CosmeticsMenuElement cosmeticsMenuElement : (Iterable<CosmeticsMenuElement>)(this.children)) {
            cosmeticsMenuElement.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }

      if (this.hovered && this.parentGui != null) {
         boolean flag2 = mouseButton == 1;
         this.parentGui.setPopupOpen(flag2, this);
      }
   }

   public boolean isExpanded() {
      return this.expanded;
   }

   public CosmeticsMenuEntry withDescription(String str) {
      this.description = str;
      return this;
   }

   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (this.isExpanded()) {
         for (CosmeticsMenuEntry cosmeticsMenuEntry : (Iterable<CosmeticsMenuEntry>)(this.children)) {
            cosmeticsMenuEntry.mouseReleased(mouseX, mouseY, state);
         }
      }
   }

   public int getEntryHeight() {
      return 40;
   }

   public CosmeticsMenuEntry(String str, boolean flag) {
       super(str, flag);
   }



}
