package net.augustus.render;

import org.lwjgl.util.vector.Vector3f;

public class ObjMaterial {
   public int illuminationModel;
   public float opticalDensity;
   public float specularExponent;
   public Vector3f specularColor;
   public Vector3f emissiveColor;
   public Vector3f ambientColor = new Vector3f(1.0F, 1.0F, 1.0F);
   public int displayListId;
   public Vector3f diffuseColor;
   public float dissolve;
   public String name;

   public ObjMaterial() {
      this.diffuseColor = new Vector3f(1.0F, 1.0F, 1.0F);
      this.specularColor = new Vector3f(1.0F, 1.0F, 1.0F);
      this.emissiveColor = new Vector3f(0.0F, 0.0F, 0.0F);
      this.specularExponent = 0.0F;
      this.opticalDensity = 1.0F;
      this.dissolve = 1.0F;
      this.illuminationModel = 2;
      this.displayListId = -1;
   }
}
