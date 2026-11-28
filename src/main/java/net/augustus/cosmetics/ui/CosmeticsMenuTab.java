package net.augustus.cosmetics.ui;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CosmeticsMenuTab {
   private String name;
   private String displayTitle;
   private String icon;
   private Runnable action;
   private ArrayList elements;
   private boolean listLayout;

   public CosmeticsMenuTab(String str, byte b, String str2, long l, ArrayList list, int n) {
      this.elements = new ArrayList();
      this.name = str;
      this.icon = str2;
      this.elements = list;
   }

   public CosmeticsMenuTab populateElements(Consumer consumer, boolean flag) {
      consumer.accept(this.elements);
      this.listLayout = flag;
      return this;
   }

   public boolean isListLayout() {
      return this.listLayout;
   }

   public CosmeticsMenuTab withDisplayTitle(String str) {
      this.displayTitle = str;
      return this;
   }

   public boolean hasAction() {
      return this.action != null;
   }

   public void runAction() {
      this.action.run();
   }

   public String getIcon() {
      return this.icon;
   }

   public ArrayList getElements() {
      return this.elements;
   }

   public String getName() {
      return this.name;
   }

   public CosmeticsMenuTab withAction(Runnable runnable) {
      this.action = runnable;
      return this;
   }

   public String getEffectiveTitle() {
      return this.displayTitle == null ? this.name : this.displayTitle;
   }

   public CosmeticsMenuTab(String str, String str2) {
      this.elements = new ArrayList();
      this.name = str;
      this.icon = str2;
   }



}
