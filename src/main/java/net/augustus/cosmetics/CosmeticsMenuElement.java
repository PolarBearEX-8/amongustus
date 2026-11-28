package net.augustus.cosmetics;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class CosmeticsMenuElement {
   public String name;
   public boolean hovered;
   public int posY;
   public int index;
   public int height;
   public CosmeticsMenuGui parentGui;
   public ArrayList children;
   public int posX;
   public boolean interactable;

   public void setHovered(boolean flag) {
      this.hovered = flag;
   }

   public boolean hasChildren() {
      return !this.children.isEmpty();
   }

   public void draw(Object obj, int n, int n2, int n3, int n4, int n5, int n6) {
      this.hovered = n5 > n && n5 < n + n3 && n6 > n2 && n6 < n2 + n4;
      this.posX = n;
      this.posY = n2;
   }

   public void setInteractable(boolean flag) {
      this.interactable = flag;
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
   }

   public int getHeight() {
      return this.height;
   }

   public CosmeticsMenuElement withChildren(Consumer consumer) {
      consumer.accept(this.children);
      return this;
   }

   public CosmeticsMenuElement(String str, boolean flag) {
      this.children = new ArrayList();
      this.name = str;
      this.interactable = flag;
   }

   public void keyTyped(char typedChar, int keyCode) {
   }

   public int getIndex() {
      return this.index;
   }

   public CosmeticsMenuElement(String str) {
      this.children = new ArrayList();
      this.name = str;
   }

   public void attachToGui(CosmeticsMenuGui cosmeticsMenuGui, int n) {
      this.parentGui = cosmeticsMenuGui;
      this.index = n;
   }

   public void mouseReleased(int n, int n2) {
   }

   public ArrayList getChildren() {
      return this.children;
   }

   public void onValueChanged(Object obj) {
   }




}
