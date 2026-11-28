package net.augustus.cosmetics.model;

import java.util.ArrayList;
import java.util.List;

public class CosmeticSubModel extends AbstractCosmeticPart {
   private transient List boundTextures;
   private boolean visible;
   private final String name;
   private final int defaultColor;

   public boolean isVisible() {
      return this.visible;
   }

   public boolean hasBoundTextures() {
      return this.boundTextures != null && !this.boundTextures.isEmpty();
   }

   public int getDefaultColor() {
      return this.defaultColor;
   }

   public CosmeticSubModel(String str, int n, boolean flag) {
      super(CosmeticBodyPart.FREE);
      this.name = str;
      this.defaultColor = n;
      this.visible = flag;
   }

   public List getBoundTextures() {
      if (this.boundTextures == null) {
         this.boundTextures = new ArrayList();
      }

      return this.boundTextures;
   }

   public String getName() {
      return this.name;
   }

   public void setVisible(boolean flag) {
      this.visible = flag;
   }
}
