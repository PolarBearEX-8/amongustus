package net.minecraft.client.model;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.entity.model.anim.ModelUpdater;
import net.optifine.model.ModelSprite;
import net.optifine.shaders.SVertexFormat;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class ModelRenderer {
   public float textureWidth;
   public float textureHeight;
   private int textureOffsetX;
   private int textureOffsetY;
   public float rotationPointX;
   public float rotationPointY;
   public float rotationPointZ;
   public float rotateAngleX;
   public float rotateAngleY;
   public float rotateAngleZ;
   private boolean compiled;
   private int displayList;
   public boolean mirror;
   public boolean showModel;
   public boolean isHidden;
   public List cubeList;
   public List childModels;
   public final String boxName;
   private ModelBase baseModel;
   public float offsetX;
   public float offsetY;
   public float offsetZ;
   public List spriteList;
   public boolean mirrorV;
   private boolean resetDisplayList = false;
   public float scaleX;
   public float scaleY;
   public float scaleZ;
   private int countResetDisplayList;
   private ResourceLocation textureLocation;
   private String id;
   private ModelUpdater modelUpdater;
   private RenderGlobal renderGlobal;

   public void addBox(int[][] intsArr, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.cubeList.add(new ModelBox(this, intsArr, f, f2, f3, f4, f5, f6, f7, this.mirror));
   }

   public ModelRenderer(ModelBase modelBase, int n, int n2) {
      this(modelBase);
      this.setTextureOffset(n, n2);
   }

   public ModelRenderer setTextureOffset(int n, int n2) {
      this.textureOffsetX = n;
      this.textureOffsetY = n2;
      return this;
   }

   public ModelRenderer getChildDeep(String str) {
      if (str == null) {
         return null;
      } else {
         ModelRenderer child = this.getChild(str);
         if (child != null) {
            return child;
         } else {
            if (this.childModels != null) {
               for (int i = 0; i < this.childModels.size(); i++) {
                  ModelRenderer childDeep = ((ModelRenderer)this.childModels.get(i)).getChildDeep(str);
                  if (childDeep != null) {
                     return childDeep;
                  }
               }
            }

            return null;
         }
      }
   }

   public ModelRenderer addBox(String str, float f, float f2, float f3, int n, int n2, int n3) {
      str = this.boxName + "." + str;
      TextureOffset textureOffset = this.baseModel.getTextureOffset(str);
      this.setTextureOffset(textureOffset.textureOffsetX, textureOffset.textureOffsetY);
      this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, f, f2, f3, n, n2, n3, 0.0F).setBoxName(str));
      return this;
   }

   @Override
   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append(
         "id: "
            + this.id
            + ", boxes: "
            + (this.cubeList != null ? this.cubeList.size() : null)
            + ", submodels: "
            + (this.childModels != null ? this.childModels.size() : null)
      );
      return sb.toString();
   }

   public void postRender(float f) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(f);
         }

         if (this.rotateAngleX != 0.0F || this.rotateAngleY != 0.0F || this.rotateAngleZ != 0.0F) {
            GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
            if (this.rotateAngleZ != 0.0F) {
               GlStateManager.rotate(this.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
            }

            if (this.rotateAngleY != 0.0F) {
               GlStateManager.rotate(this.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (this.rotateAngleX != 0.0F) {
               GlStateManager.rotate(this.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            }
         } else if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
            GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
         }
      }
   }

   public int getDisplayList() {
      return this.displayList;
   }

   public void addBox(float f, float f2, float f3, int n, int n2, int n3, float f4) {
      this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, f, f2, f3, n, n2, n3, f4));
   }

   public void render(float f) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(f);
         }

         int n = 0;
         if (this.textureLocation != null && !this.renderGlobal.renderOverlayDamaged) {
            if (this.renderGlobal.renderOverlayEyes) {
               return;
            }

            n = GlStateManager.getBoundTexture();
            Config.getTextureManager().bindTexture(this.textureLocation);
         }

         if (this.modelUpdater != null) {
            this.modelUpdater.update();
         }

         boolean flag = this.scaleX != 1.0F || this.scaleY != 1.0F || this.scaleZ != 1.0F;
         GlStateManager.translate(this.offsetX, this.offsetY, this.offsetZ);
         if (this.rotateAngleX != 0.0F || this.rotateAngleY != 0.0F || this.rotateAngleZ != 0.0F) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
            if (this.rotateAngleZ != 0.0F) {
               GlStateManager.rotate(this.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
            }

            if (this.rotateAngleY != 0.0F) {
               GlStateManager.rotate(this.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (this.rotateAngleX != 0.0F) {
               GlStateManager.rotate(this.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            }

            if (flag) {
               GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
            }

            GlStateManager.callList(this.displayList);
            if (this.childModels != null) {
               for (Object obj : this.childModels) {
                  ((ModelRenderer)obj).render(f);
               }
            }

            GlStateManager.popMatrix();
         } else if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F) {
            if (flag) {
               GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
            }

            GlStateManager.callList(this.displayList);
            if (this.childModels != null) {
               for (Object obj2 : this.childModels) {
                  ((ModelRenderer)obj2).render(f);
               }
            }

            if (flag) {
               GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
            }
         } else {
            GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
            if (flag) {
               GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
            }

            GlStateManager.callList(this.displayList);
            if (this.childModels != null) {
               for (Object obj3 : this.childModels) {
                  ((ModelRenderer)obj3).render(f);
               }
            }

            if (flag) {
               GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
            }

            GlStateManager.translate(-this.rotationPointX * f, -this.rotationPointY * f, -this.rotationPointZ * f);
         }

         GlStateManager.translate(-this.offsetX, -this.offsetY, -this.offsetZ);
         if (n != 0) {
            GlStateManager.bindTexture(n);
         }
      }
   }

   public String getId() {
      return this.id;
   }

   public void setId(String str) {
      this.id = str;
   }

   public void renderWithRotation(float f) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(f);
         }

         int n = 0;
         if (this.textureLocation != null && !this.renderGlobal.renderOverlayDamaged) {
            if (this.renderGlobal.renderOverlayEyes) {
               return;
            }

            n = GlStateManager.getBoundTexture();
            Config.getTextureManager().bindTexture(this.textureLocation);
         }

         if (this.modelUpdater != null) {
            this.modelUpdater.update();
         }

         boolean flag = this.scaleX != 1.0F || this.scaleY != 1.0F || this.scaleZ != 1.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
         if (this.rotateAngleY != 0.0F) {
            GlStateManager.rotate(this.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         }

         if (this.rotateAngleX != 0.0F) {
            GlStateManager.rotate(this.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         }

         if (this.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(this.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
         }

         if (flag) {
            GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
         }

         GlStateManager.callList(this.displayList);
         if (this.childModels != null) {
            for (int i = 0; i < this.childModels.size(); i++) {
               ((ModelRenderer)this.childModels.get(i)).render(f);
            }
         }

         GlStateManager.popMatrix();
         if (n != 0) {
            GlStateManager.bindTexture(n);
         }
      }
   }

   private void compileDisplayList(float f) {
      if (this.displayList == 0) {
         this.displayList = GLAllocation.generateDisplayLists(1);
      }

      GL11.glNewList(this.displayList, 4864);
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      if (Config.isShaderPackLoaded()) {
         worldRenderer.begin(7, SVertexFormat.defVertexFormatTextured);
      } else {
         worldRenderer.begin(7, DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL);
      }

      for (int i = 0; i < this.cubeList.size(); i++) {
         ((ModelBox)this.cubeList.get(i)).render(worldRenderer, f);
      }

      for (int j = 0; j < this.spriteList.size(); j++) {
         ((ModelSprite)this.spriteList.get(j)).render(Tessellator.getInstance(), f);
      }

      Tessellator.getInstance().draw();
      GL11.glEndList();
      this.compiled = true;
   }

   public boolean isCompiled() {
      return this.compiled;
   }

   public ModelRenderer(ModelBase modelBase, String str) {
      this.spriteList = new ArrayList();
      this.mirrorV = false;
      this.scaleX = 1.0F;
      this.scaleY = 1.0F;
      this.scaleZ = 1.0F;
      this.textureLocation = null;
      this.id = null;
      this.renderGlobal = Config.getRenderGlobal();
      this.textureWidth = 64.0F;
      this.textureHeight = 32.0F;
      this.showModel = true;
      this.cubeList = Lists.newArrayList();
      this.baseModel = modelBase;
      modelBase.boxList.add(this);
      this.boxName = str;
      this.setTextureSize(modelBase.textureWidth, modelBase.textureHeight);
   }

   public ModelRenderer addBox(float f, float f2, float f3, int n, int n2, int n3, boolean flag) {
      this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, f, f2, f3, n, n2, n3, 0.0F, flag));
      return this;
   }

   public void addChild(ModelRenderer modelRenderer) {
      if (this.childModels == null) {
         this.childModels = Lists.newArrayList();
      }

      this.childModels.add(modelRenderer);
   }

   public void setRotationPoint(float f, float f2, float f3) {
      this.rotationPointX = f;
      this.rotationPointY = f2;
      this.rotationPointZ = f3;
   }

   private void checkResetDisplayList() {
      if (this.countResetDisplayList != Shaders.countResetDisplayLists) {
         this.compiled = false;
         this.countResetDisplayList = Shaders.countResetDisplayLists;
      }
   }

   public ModelRenderer(ModelBase modelBase, String str, int n, int n2) {
      this(modelBase, str);
      this.setTextureOffset(n, n2);
   }

   public ModelRenderer addBox(float f, float f2, float f3, int n, int n2, int n3) {
      this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, f, f2, f3, n, n2, n3, 0.0F));
      return this;
   }

   public void setTextureLocation(ResourceLocation location) {
      this.textureLocation = location;
   }

   public ModelRenderer(ModelBase modelBase) {
      this(modelBase, (String)null);
   }

   public ModelRenderer getChild(String str) {
      if (str == null) {
         return null;
      } else {
         if (this.childModels != null) {
            for (int i = 0; i < this.childModels.size(); i++) {
               ModelRenderer modelRenderer = (ModelRenderer)this.childModels.get(i);
               if (str.equals(modelRenderer.getId())) {
                  return modelRenderer;
               }
            }
         }

         return null;
      }
   }

   public void addSprite(float f, float f2, float f3, int n, int n2, int n3, float f4) {
      this.spriteList.add(new ModelSprite(this, this.textureOffsetX, this.textureOffsetY, f, f2, f3, n, n2, n3, f4));
   }

   public ModelRenderer setTextureSize(int n, int n2) {
      this.textureWidth = (float)n;
      this.textureHeight = (float)n2;
      return this;
   }

   public ResourceLocation getTextureLocation() {
      return this.textureLocation;
   }

   public void setModelUpdater(ModelUpdater modelUpdater2) {
      this.modelUpdater = modelUpdater2;
   }
}
