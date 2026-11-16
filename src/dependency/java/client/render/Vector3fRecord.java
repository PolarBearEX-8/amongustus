package client.render;

import org.lwjgl.util.vector.Vector3f;

public class Vector3fRecord {
   public Vector3f vector3f2 = new Vector3f(1.0F, 1.0F, 1.0F);
   public Vector3f vector3f3;
   public int count;
   public float factor;
   public float factor2;
   public Vector3f vector3f4;
   public int count2;
   public Vector3f vector3f = new Vector3f(1.0F, 1.0F, 1.0F);
   public String labelText;
   public float factor3;

   public Vector3fRecord() {
      this.vector3f3 = new Vector3f(1.0F, 1.0F, 1.0F);
      this.vector3f4 = new Vector3f(0.0F, 0.0F, 0.0F);
      ObjModelMesh.getStringArray();
      this.factor = 0.0F;
      this.factor2 = 1.0F;
      this.factor3 = 1.0F;
      this.count2 = 2;
      this.count = -1;
   }
}
