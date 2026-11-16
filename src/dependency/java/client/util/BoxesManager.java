package client.util;

import client.render.WorldRenderer;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BoxesManager {
   public float factor12;
   public float factor11;
   public int count3;
   public int count2;
   public float factor5;
   public float factor2;
   public float factor10;
   public float factor;
   public float factor9;
   public float factor8;
   public boolean enabled5;
   public int count4;
   public boolean enabled;
   public boolean enabled3;
   public boolean enabled4;
   public List list;
   public List list2;
   public final String labelText;
   public PassiveFloatEntry passiveFloatEntry;
   public float factor4;
   public float factor6;
   public float factor3;
   public List list3;
   public boolean enabled6;
   public boolean enabled2 = false;
   public float factor7;
   public float factor13;
   public float factor14;
   public int count;
   public ResourceLocation resourceLocation;
   public String labelText2;
   public RotationChecker rotationChecker;
   public RenderGlobal renderGlobal;

   public int getCount4() {
      return this.count4;
   }

   private void updateState() {
      if (this.count != PreEndHandManager.count25) {
         this.enabled5 = false;
         this.count = PreEndHandManager.count25;
      }
   }

   public void applyFloat2(float factor15) {
      if (!this.enabled4 && this.enabled3) {
         this.updateState();
         if (!this.enabled5) {
            this.applyFloat(factor15);
         }

         int count5 = 0;
         if (this.resourceLocation != null && !this.renderGlobal.enabled) {
            if (this.renderGlobal.enabled4) {
               return;
            }

            count5 = GlStateManager.getStateInt4();
            BuildManager.getTextureManagerAsTextureManager().applyResourceLocation3(this.resourceLocation);
         }

         if (this.rotationChecker != null) {
            this.rotationChecker.updateState();
         }

         boolean flag = this.factor7 != 1.0F || this.factor13 != 1.0F || this.factor14 != 1.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate(this.factor5 * factor15, this.factor2 * factor15, this.factor10 * factor15);
         if (this.factor9 != 0.0F) {
            GlStateManager.rotate(this.factor9 * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         }

         if (this.factor != 0.0F) {
            GlStateManager.rotate(this.factor * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         }

         if (this.factor8 != 0.0F) {
            GlStateManager.rotate(this.factor8 * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
         }

         if (flag) {
            GlStateManager.translate3f3(this.factor7, this.factor13, this.factor14);
         }

         GlStateManager.applyIndexByIndex(this.count4);
         if (this.list2 != null) {
            for (int index = 0; index < this.list2.size(); index++) {
               ((BoxesManager)this.list2.get(index)).applyFloat3(factor15);
            }
         }

         GlStateManager.popMatrix();
         if (count5 != 0) {
            GlStateManager.setActiveTexture(count5);
         }
      }
   }

   public void setRotationChecker(RotationChecker rotationChecker2) {
      this.rotationChecker = rotationChecker2;
   }

   public BoxesManager resolveBoxesManager(float posX, float posY, float posZ, int count5, int count6, int count7) {
      this.list.add(new DampenerSelector(this, this.count3, this.count2, posX, posY, posZ, count5, count6, count7, 0.0F));
      return this;
   }

   public void setResourceLocation(ResourceLocation resourceLocation2) {
      this.resourceLocation = resourceLocation2;
   }

   public BoxesManager resolveBoxesManager(int count5, int count6) {
      this.factor12 = (float)count5;
      this.factor11 = (float)count6;
      return this;
   }

   public BoxesManager resolveBoxesManager(String text) {
      if (text == null) {
         return null;
      } else {
         if (this.list2 != null) {
            for (int index = 0; index < this.list2.size(); index++) {
               BoxesManager boxesManager = (BoxesManager)this.list2.get(index);
               if (text.equals(boxesManager.getLabelText2())) {
                  return boxesManager;
               }
            }
         }

         return null;
      }
   }

   public void applyFloat(float posX, float posY, float posZ, int count5, int count6, int count7, float factor15) {
      this.list3.add(new FovParser(this, this.count3, this.count2, posX, posY, posZ, count5, count6, count7, factor15));
   }

   private void applyFloat(float factor15) {
      if (this.count4 == 0) {
         this.count4 = VectorMatcher.computeCount(1);
      }

      GL11.glNewList(this.count4, 4864);
      WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();
      if (BuildManager.canExecute()) {
         worldRenderer.updateAuxiliaryLoggerEntry(7, RotationEvaluator.auxiliaryLoggerEntry);
      } else {
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry4);
      }

      for (int index = 0; index < this.list.size(); index++) {
         ((DampenerSelector)this.list.get(index)).applyWorldRenderer(worldRenderer, factor15);
      }

      for (int index2 = 0; index2 < this.list3.size(); index2++) {
         ((FovParser)this.list3.get(index2)).applyTessellator(Tessellator.getTessellator(), factor15);
      }

      Tessellator.getTessellator().updateState();
      GL11.glEndList();
      this.enabled5 = true;
   }

   public void setLabelText2(String labelText3) {
      this.labelText2 = labelText3;
   }

   public BoxesManager resolveBoxesManager(String text, float posX, float posY, float posZ, int count5, int count6, int count7) {
      String text2 = this.labelText + "." + text;
      RotationAdapter rotationAdapter = this.passiveFloatEntry.get(text2);
      this.resolveBoxesManagerByIndex(rotationAdapter.count, rotationAdapter.count2);
      this.list.add(new DampenerSelector(this, this.count3, this.count2, posX, posY, posZ, count5, count6, count7, 0.0F).withLabelText(text2));
      return this;
   }

   public void applyFloatByFloat(float factor15) {
      if (!this.enabled4 && this.enabled3) {
         this.updateState();
         if (!this.enabled5) {
            this.applyFloat(factor15);
         }

         if (this.factor != 0.0F || this.factor9 != 0.0F || this.factor8 != 0.0F) {
            GlStateManager.translate(this.factor5 * factor15, this.factor2 * factor15, this.factor10 * factor15);
            if (this.factor8 != 0.0F) {
               GlStateManager.rotate(this.factor8 * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
            }

            if (this.factor9 != 0.0F) {
               GlStateManager.rotate(this.factor9 * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (this.factor != 0.0F) {
               GlStateManager.rotate(this.factor * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            }
         } else if (this.factor5 != 0.0F || this.factor2 != 0.0F || this.factor10 != 0.0F) {
            GlStateManager.translate(this.factor5 * factor15, this.factor2 * factor15, this.factor10 * factor15);
         }
      }
   }

   public void applyFloatByFloat(float posX, float posY, float posZ, int count5, int count6, int count7, float factor15) {
      this.list.add(new DampenerSelector(this, this.count3, this.count2, posX, posY, posZ, count5, count6, count7, factor15));
   }

   public BoxesManager resolveBoxesManagerByText(String text) {
      if (text == null) {
         return null;
      } else {
         BoxesManager boxesManager = this.resolveBoxesManager(text);
         if (boxesManager != null) {
            return boxesManager;
         } else {
            if (this.list2 != null) {
               for (int index = 0; index < this.list2.size(); index++) {
                  BoxesManager boxesManager2 = ((BoxesManager)this.list2.get(index)).resolveBoxesManagerByText(text);
                  if (boxesManager2 != null) {
                     return boxesManager2;
                  }
               }
            }

            return null;
         }
      }
   }

   public void applyIndexArray(int[][] countMatrix, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.list.add(new DampenerSelector(this, countMatrix, posX, posX2, posX3, posX4, posX5, posY, posZ, this.enabled));
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public void applyFloat3(float factor15) {
      if (!this.enabled4 && this.enabled3) {
         this.updateState();
         if (!this.enabled5) {
            this.applyFloat(factor15);
         }

         int count5 = 0;
         if (this.resourceLocation != null && !this.renderGlobal.enabled) {
            if (this.renderGlobal.enabled4) {
               return;
            }

            count5 = GlStateManager.getStateInt4();
            BuildManager.getTextureManagerAsTextureManager().applyResourceLocation3(this.resourceLocation);
         }

         if (this.rotationChecker != null) {
            this.rotationChecker.updateState();
         }

         boolean flag = this.factor7 != 1.0F || this.factor13 != 1.0F || this.factor14 != 1.0F;
         GlStateManager.translate(this.factor4, this.factor6, this.factor3);
         if (this.factor != 0.0F || this.factor9 != 0.0F || this.factor8 != 0.0F) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(this.factor5 * factor15, this.factor2 * factor15, this.factor10 * factor15);
            if (this.factor8 != 0.0F) {
               GlStateManager.rotate(this.factor8 * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
            }

            if (this.factor9 != 0.0F) {
               GlStateManager.rotate(this.factor9 * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (this.factor != 0.0F) {
               GlStateManager.rotate(this.factor * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            }

            if (flag) {
               GlStateManager.translate3f3(this.factor7, this.factor13, this.factor14);
            }

            GlStateManager.applyIndexByIndex(this.count4);
            if (this.list2 != null) {
               Iterator iterator = this.list2.iterator();

               while (iterator.hasNext()) {
                  ((BoxesManager)iterator.next()).applyFloat3(factor15);
               }
            }

            GlStateManager.popMatrix();
         } else if (this.factor5 == 0.0F && this.factor2 == 0.0F && this.factor10 == 0.0F) {
            if (flag) {
               GlStateManager.translate3f3(this.factor7, this.factor13, this.factor14);
            }

            GlStateManager.applyIndexByIndex(this.count4);
            if (this.list2 != null) {
               Iterator iterator2 = this.list2.iterator();

               while (iterator2.hasNext()) {
                  ((BoxesManager)iterator2.next()).applyFloat3(factor15);
               }
            }

            if (flag) {
               GlStateManager.translate3f3(1.0F / this.factor7, 1.0F / this.factor13, 1.0F / this.factor14);
            }
         } else {
            GlStateManager.translate(this.factor5 * factor15, this.factor2 * factor15, this.factor10 * factor15);
            if (flag) {
               GlStateManager.translate3f3(this.factor7, this.factor13, this.factor14);
            }

            GlStateManager.applyIndexByIndex(this.count4);
            if (this.list2 != null) {
               Iterator iterator3 = this.list2.iterator();

               while (iterator3.hasNext()) {
                  ((BoxesManager)iterator3.next()).applyFloat3(factor15);
               }
            }

            if (flag) {
               GlStateManager.translate3f3(1.0F / this.factor7, 1.0F / this.factor13, 1.0F / this.factor14);
            }

            GlStateManager.translate(-this.factor5 * factor15, -this.factor2 * factor15, -this.factor10 * factor15);
         }

         GlStateManager.translate(-this.factor4, -this.factor6, -this.factor3);
         if (count5 != 0) {
            GlStateManager.setActiveTexture(count5);
         }
      }
   }

   public BoxesManager resolveBoxesManagerByIndex(int count5, int count6) {
      this.count3 = count5;
      this.count2 = count6;
      return this;
   }

   public void applyBoxesManager(BoxesManager boxesManager) {
      if (this.list2 == null) {
         this.list2 = Lists.newArrayList();
      }

      this.list2.add(boxesManager);
   }

   public void applyFloat(float posX, float posY, float posZ) {
      this.factor5 = posX;
      this.factor2 = posY;
      this.factor10 = posZ;
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public boolean isEnabled5() {
      return this.enabled5;
   }

   public BoxesManager resolveBoxesManager(float posX, float posY, float posZ, int count5, int count6, int count7, boolean flag) {
      this.list.add(new DampenerSelector(this, this.count3, this.count2, posX, posY, posZ, count5, count6, count7, 0.0F, flag));
      return this;
   }

   public BoxesManager(PassiveFloatEntry passiveFloatEntry2, int count5, int count6) {
      this(passiveFloatEntry2);
      this.resolveBoxesManagerByIndex(count5, count6);
   }

   public BoxesManager(PassiveFloatEntry passiveFloatEntry2, String text) {
      this.list3 = new ArrayList();
      this.enabled6 = false;
      this.factor7 = 1.0F;
      this.factor13 = 1.0F;
      this.factor14 = 1.0F;
      this.resourceLocation = null;
      this.labelText2 = null;
      this.renderGlobal = BuildManager.getRenderGlobalAsRenderGlobal();
      this.factor12 = 64.0F;
      this.factor11 = 32.0F;
      this.enabled3 = true;
      this.list = Lists.newArrayList();
      this.passiveFloatEntry = passiveFloatEntry2;
      passiveFloatEntry2.list.add(this);
      this.labelText = text;
      this.resolveBoxesManager(passiveFloatEntry2.count2, passiveFloatEntry2.count);
   }

   public BoxesManager(PassiveFloatEntry passiveFloatEntry2) {
      this(passiveFloatEntry2, (String)null);
   }

   @Override
   public String toString() {
      StringBuffer builder = new StringBuffer();
      builder.append(
         "id: "
            + this.labelText2
            + ", boxes: "
            + (this.list != null ? this.list.size() : null)
            + ", submodels: "
            + (this.list2 != null ? this.list2.size() : null)
      );
      return builder.toString();
   }
}
