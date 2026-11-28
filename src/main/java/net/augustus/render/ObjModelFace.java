package net.augustus.render;

public class ObjModelFace {
   public int[] vertexIndices;
   public int[] texCoordIndices;
   public int[] normalIndices;
   public String materialName;

   public ObjModelFace(int[] ints, int[] ints2, int[] ints3, String str) {
      this.vertexIndices = ints;
      this.texCoordIndices = ints2;
      this.normalIndices = ints3;
      this.materialName = str;
   }
}
