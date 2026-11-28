package net.augustus.cosmetics.model;

import java.io.File;
import java.util.ArrayList;
import net.labymod.ModelConfig;

public class CosmeticModel extends AbstractCosmeticPart {
   private float resizeFactor;
   private int categoryId;
   private float[] adjustment;
   private float[] previewRotation;
   private final File sourceFile;
   private final boolean local;
   private ArrayList itemModels;
   private float[] sideTransform;
   private float previewY;
   private float height;
   private final String textureName;
   private String author;
   private final int version;
   private float previewScale = 1.0F;
   private ModelConfig[] configs;
   private transient CosmeticAnimatable animatable;
   private final CosmeticSubModel[] subModels;
   private ArrayList textureModels;
   private transient GeoModel geoModel;
   private final Integer id;
   private final String name;

   public ModelConfig[] getConfigs() {
      return this.configs;
   }

   public String getAuthor() {
      return this.author;
   }

   public boolean hasTextureModels() {
      return this.textureModels != null;
   }

   public CosmeticSubModel[] getSubModels() {
      return this.subModels;
   }

   public CosmeticAnimatable getAnimatable() {
      return this.animatable;
   }

   public float[] getPreviewRotation() {
      return this.previewRotation;
   }

   public void setConfigs(ModelConfig[] modelConfigs) {
      this.configs = modelConfigs;
   }

   public float getPreviewY() {
      return this.previewY;
   }

   public boolean hasAnimation() {
      return this.animatable != null;
   }

   public void setCategoryId(int n) {
      this.categoryId = n;
   }

   public Integer getId() {
      return this.id;
   }

   public String getTextureName() {
      return this.textureName;
   }

   public void setPreviewY(float f) {
      this.previewY = f;
   }

   public int getCategoryId() {
      return this.categoryId;
   }

   public ArrayList getItemModels() {
      if (this.itemModels == null) {
         this.itemModels = new ArrayList();
      }

      return this.itemModels;
   }

   public float getHeight() {
      return this.height;
   }

   public float[] getSideTransform() {
      return this.sideTransform;
   }

   public boolean isLoaded() {
      return true;
   }

   public String getName() {
      return this.name;
   }

   public boolean hasConfigs() {
      return this.configs != null && this.configs.length > 0;
   }

   public boolean hasSubModels() {
      return this.subModels != null && this.subModels.length > 0;
   }

   public float getPreviewScale() {
      return this.previewScale;
   }

   public void setResizeFactor(float f) {
      this.resizeFactor = f;
   }

   public void setAdjustment(float[] floats) {
      this.adjustment = floats;
   }

   public void setSideTransform(float[] floats) {
      this.sideTransform = floats;
   }

   public File getSourceFile() {
      return this.sourceFile;
   }

   public void setHeight(float f) {
      this.height = f;
   }

   public float getResizeFactor() {
      return this.resizeFactor;
   }

   public void setPreviewScale(float f) {
      this.previewScale = f;
   }

   public void setPreviewRotation(float[] floats) {
      this.previewRotation = floats;
   }

   public boolean hasItemModels() {
      return this.itemModels != null;
   }

   public CosmeticModel(
      String str,
      Integer num,
      int n,
      int n2,
      boolean flag,
      String str2,
      GeoModel geoModel2,
      CosmeticSubModel[] cosmeticSubModels,
      ModelConfig[] modelConfigs,
      File file
   ) {
      super(CosmeticBodyPart.BODY);
      this.geoModel = geoModel2;
      this.name = str;
      this.id = num;
      this.categoryId = n;
      this.version = n2;
      this.local = flag;
      this.textureName = str2;
      this.subModels = cosmeticSubModels;
      this.configs = modelConfigs;
      this.sourceFile = file;
   }

   public GeoModel getGeoModel() {
      return this.geoModel;
   }

   public boolean isLocal() {
      return this.local;
   }

   public int getVersion() {
      return this.version;
   }

   public ArrayList getTextureModels() {
      if (this.textureModels == null) {
         this.textureModels = new ArrayList();
      }

      return this.textureModels;
   }

   public void setAnimationFile(GeoAnimationFile geoAnimationFile) {
      this.animatable = new CosmeticAnimatable(new CosmeticAnimatedGeoModel(this.geoModel, geoAnimationFile));
   }

   public float[] getAdjustment() {
      return this.adjustment;
   }
}
