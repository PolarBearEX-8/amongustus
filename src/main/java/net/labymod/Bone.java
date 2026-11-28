package net.labymod;

import com.google.gson.annotations.SerializedName;

public class Bone {
   private Cube[] cubes;
   private Boolean debug;
   private Double inflate;
   private Boolean mirror;
   private String name;
   private Boolean neverRender;
   private String parent;
   private double[] pivot = new double[]{0.0, 0.0, 0.0};
   @SerializedName("poly_mesh")
   private PolyMesh polyMesh;
   private Boolean reset;
   private double[] rotation = new double[]{0.0, 0.0, 0.0};
   @SerializedName("texture_meshes")
   private TextureMesh[] textureMeshes;

   public String getParent() {
      return this.parent;
   }

   public void setParent(String str) {
      this.parent = str;
   }

   public Boolean getNeverRender() {
      return this.neverRender;
   }

   public double[] getRotation() {
      return this.rotation;
   }

   public Boolean getMirror() {
      return this.mirror;
   }

   public void setTextureMeshes(TextureMesh[] textureMeshs) {
      this.textureMeshes = textureMeshs;
   }

   public PolyMesh getPolyMesh() {
      return this.polyMesh;
   }

   public void setDebug(Boolean bool) {
      this.debug = bool;
   }

   public TextureMesh[] getTextureMeshes() {
      return this.textureMeshes;
   }

   public String getName() {
      return this.name;
   }

   public void setReset(Boolean bool) {
      this.reset = bool;
   }

   public Cube[] getCubes() {
      return this.cubes;
   }

   public void setCubes(Cube[] cubes2) {
      this.cubes = cubes2;
   }

   public void setMirror(Boolean bool) {
      this.mirror = bool;
   }

   public void setPolyMesh(PolyMesh polyMesh2) {
      this.polyMesh = polyMesh2;
   }

   public Boolean getReset() {
      return this.reset;
   }

   public Boolean getDebug() {
      return this.debug;
   }

   public void setInflate(Double d) {
      this.inflate = d;
   }

   public double[] getPivot() {
      return this.pivot;
   }

   public void setNeverRender(Boolean bool) {
      this.neverRender = bool;
   }

   public void setRotation(double[] doubles) {
      this.rotation = doubles;
   }

   public void setName(String str) {
      this.name = str;
   }

   public Double getInflate() {
      return this.inflate;
   }

   public void setPivot(double[] doubles) {
      this.pivot = doubles;
   }
}
