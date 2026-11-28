package net.labymod;

public class UvFaces {
   private FaceUv down;
   private FaceUv east;
   private FaceUv north;
   private FaceUv south;
   private FaceUv up;
   private FaceUv west;

   public void setSouth(FaceUv faceUv) {
      this.south = faceUv;
   }

   public void setWest(FaceUv faceUv) {
      this.west = faceUv;
   }

   public void setUp(FaceUv faceUv) {
      this.up = faceUv;
   }

   public FaceUv getWest() {
      return this.west;
   }

   public FaceUv getDown() {
      return this.down;
   }

   public FaceUv getEast() {
      return this.east;
   }

   public void setDown(FaceUv faceUv) {
      this.down = faceUv;
   }

   public FaceUv getUp() {
      return this.up;
   }

   public void setEast(FaceUv faceUv) {
      this.east = faceUv;
   }

   public FaceUv getSouth() {
      return this.south;
   }

   public void setNorth(FaceUv faceUv) {
      this.north = faceUv;
   }

   public FaceUv getNorth() {
      return this.north;
   }
}
