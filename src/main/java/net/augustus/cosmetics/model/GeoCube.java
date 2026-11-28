package net.augustus.cosmetics.model;

import net.labymod.Cube;
import net.labymod.FaceUv;
import net.labymod.ModelProperties;
import net.labymod.UvFaces;
import net.labymod.UvUnion;

public class GeoCube {
   public Boolean mirror;
   public GeoQuad[] quads;
   public GeoVector3f size;
   public double inflate;
   public GeoVector3f pivot;
   public GeoVector3f rotation;

   public static GeoCube createFromPojoCube(Cube cube, ModelProperties modelProperties, Double d, Boolean bool) {
      GeoCube geoCube = new GeoCube(cube.getSize());
      UvUnion uv = cube.getUv();
      UvFaces faceUV = uv.faceUV;
      boolean isBoxUV = uv.isBoxUV;
      geoCube.mirror = cube.getMirror();
      geoCube.inflate = cube.getInflate() == null ? (d == null ? 0.0 : d) : cube.getInflate() / 16.0;
      float f = modelProperties.getTextureHeight().floatValue();
      float f2 = modelProperties.getTextureWidth().floatValue();
      GeoVector3d geoVector3d = GeoVectorUtil.arrayToVector(cube.getSize());
      GeoVector3d geoVector3d2 = GeoVectorUtil.arrayToVector(cube.getOrigin());
      geoVector3d2 = new GeoVector3d(-(geoVector3d2.x + geoVector3d.x) / 16.0, geoVector3d2.y / 16.0, geoVector3d2.z / 16.0);
      geoVector3d.x *= 0.0625;
      geoVector3d.y *= 0.0625;
      geoVector3d.z *= 0.0625;
      GeoVector3f geoVector3f = GeoVectorUtil.convertDoubleToFloat(GeoVectorUtil.arrayToVector(cube.getRotation()));
      geoVector3f.x *= -1.0F;
      geoVector3f.y *= -1.0F;
      geoVector3f.setX((float)Math.toRadians((double)geoVector3f.getX()));
      geoVector3f.setY((float)Math.toRadians((double)geoVector3f.getY()));
      geoVector3f.setZ((float)Math.toRadians((double)geoVector3f.getZ()));
      GeoVector3f geoVector3f2 = GeoVectorUtil.convertDoubleToFloat(GeoVectorUtil.arrayToVector(cube.getPivot()));
      geoVector3f2.x *= -1.0F;
      geoCube.pivot = geoVector3f2;
      geoCube.rotation = geoVector3f;
      GeoVertex geoVertex = new GeoVertex(geoVector3d2.x - geoCube.inflate, geoVector3d2.y - geoCube.inflate, geoVector3d2.z - geoCube.inflate);
      GeoVertex geoVertex2 = new GeoVertex(geoVector3d2.x - geoCube.inflate, geoVector3d2.y - geoCube.inflate, geoVector3d2.z + geoVector3d.z + geoCube.inflate);
      GeoVertex geoVertex3 = new GeoVertex(geoVector3d2.x - geoCube.inflate, geoVector3d2.y + geoVector3d.y + geoCube.inflate, geoVector3d2.z - geoCube.inflate);
      GeoVertex geoVertex4 = new GeoVertex(geoVector3d2.x - geoCube.inflate, geoVector3d2.y + geoVector3d.y + geoCube.inflate, geoVector3d2.z + geoVector3d.z + geoCube.inflate);
      GeoVertex geoVertex5 = new GeoVertex(geoVector3d2.x + geoVector3d.x + geoCube.inflate, geoVector3d2.y - geoCube.inflate, geoVector3d2.z - geoCube.inflate);
      GeoVertex geoVertex6 = new GeoVertex(geoVector3d2.x + geoVector3d.x + geoCube.inflate, geoVector3d2.y - geoCube.inflate, geoVector3d2.z + geoVector3d.z + geoCube.inflate);
      GeoVertex geoVertex7 = new GeoVertex(geoVector3d2.x + geoVector3d.x + geoCube.inflate, geoVector3d2.y + geoVector3d.y + geoCube.inflate, geoVector3d2.z - geoCube.inflate);
      GeoVertex geoVertex8 = new GeoVertex(geoVector3d2.x + geoVector3d.x + geoCube.inflate, geoVector3d2.y + geoVector3d.y + geoCube.inflate, geoVector3d2.z + geoVector3d.z + geoCube.inflate);
      GeoQuad geoQuad;
      GeoQuad geoQuad2;
      GeoQuad geoQuad3;
      GeoQuad geoQuad4;
      GeoQuad geoQuad5;
      GeoQuad geoQuad6;
      if (!isBoxUV) {
         FaceUv west = faceUV.getWest();
         FaceUv east = faceUV.getEast();
         FaceUv north = faceUV.getNorth();
         FaceUv south = faceUV.getSouth();
         FaceUv up = faceUV.getUp();
         FaceUv down = faceUV.getDown();
         geoQuad = west == null
            ? null
            : new GeoQuad(
               new GeoVertex[]{geoVertex4, geoVertex3, geoVertex, geoVertex2}, west.getUv(), west.getUvSize(), f2, f, cube.getMirror(), GeoDirection.WEST
            );
         geoQuad2 = east == null
            ? null
            : new GeoQuad(
               new GeoVertex[]{geoVertex7, geoVertex8, geoVertex6, geoVertex5}, east.getUv(), east.getUvSize(), f2, f, cube.getMirror(), GeoDirection.EAST
            );
         geoQuad3 = north == null
            ? null
            : new GeoQuad(
               new GeoVertex[]{geoVertex3, geoVertex7, geoVertex5, geoVertex}, north.getUv(), north.getUvSize(), f2, f, cube.getMirror(), GeoDirection.NORTH
            );
         geoQuad4 = south == null
            ? null
            : new GeoQuad(
               new GeoVertex[]{geoVertex8, geoVertex4, geoVertex2, geoVertex6}, south.getUv(), south.getUvSize(), f2, f, cube.getMirror(), GeoDirection.SOUTH
            );
         geoQuad5 = up == null
            ? null
            : new GeoQuad(
               new GeoVertex[]{geoVertex4, geoVertex8, geoVertex7, geoVertex3}, up.getUv(), up.getUvSize(), f2, f, cube.getMirror(), GeoDirection.UP
            );
         geoQuad6 = down == null
            ? null
            : new GeoQuad(
               new GeoVertex[]{geoVertex, geoVertex5, geoVertex6, geoVertex2}, down.getUv(), down.getUvSize(), f2, f, cube.getMirror(), GeoDirection.DOWN
            );
         if (cube.getMirror() == Boolean.TRUE || bool == Boolean.TRUE) {
            geoQuad = west == null
               ? null
               : new GeoQuad(
                  new GeoVertex[]{geoVertex7, geoVertex8, geoVertex6, geoVertex5}, west.getUv(), west.getUvSize(), f2, f, cube.getMirror(), GeoDirection.WEST
               );
            geoQuad2 = east == null
               ? null
               : new GeoQuad(
                  new GeoVertex[]{geoVertex4, geoVertex3, geoVertex, geoVertex2}, east.getUv(), east.getUvSize(), f2, f, cube.getMirror(), GeoDirection.EAST
               );
         }
      } else {
         double[] boxUVCoords = cube.getUv().boxUVCoords;
         GeoVector3d geoVector3d3 = GeoVectorUtil.arrayToVector(cube.getSize());
         geoVector3d3 = new GeoVector3d(Math.floor(geoVector3d3.x), Math.floor(geoVector3d3.y), Math.floor(geoVector3d3.z));
         geoQuad = new GeoQuad(
            new GeoVertex[]{geoVertex4, geoVertex3, geoVertex, geoVertex2},
            new double[]{boxUVCoords[0] + geoVector3d3.z + geoVector3d3.x, boxUVCoords[1] + geoVector3d3.z},
            new double[]{geoVector3d3.z, geoVector3d3.y},
            f2,
            f,
            cube.getMirror(),
            GeoDirection.WEST
         );
         geoQuad2 = new GeoQuad(
            new GeoVertex[]{geoVertex7, geoVertex8, geoVertex6, geoVertex5},
            new double[]{boxUVCoords[0], boxUVCoords[1] + geoVector3d3.z},
            new double[]{geoVector3d3.z, geoVector3d3.y},
            f2,
            f,
            cube.getMirror(),
            GeoDirection.EAST
         );
         geoQuad3 = new GeoQuad(
            new GeoVertex[]{geoVertex3, geoVertex7, geoVertex5, geoVertex},
            new double[]{boxUVCoords[0] + geoVector3d3.z, boxUVCoords[1] + geoVector3d3.z},
            new double[]{geoVector3d3.x, geoVector3d3.y},
            f2,
            f,
            cube.getMirror(),
            GeoDirection.NORTH
         );
         geoQuad4 = new GeoQuad(
            new GeoVertex[]{geoVertex8, geoVertex4, geoVertex2, geoVertex6},
            new double[]{boxUVCoords[0] + geoVector3d3.z + geoVector3d3.x + geoVector3d3.z, boxUVCoords[1] + geoVector3d3.z},
            new double[]{geoVector3d3.x, geoVector3d3.y},
            f2,
            f,
            cube.getMirror(),
            GeoDirection.SOUTH
         );
         geoQuad5 = new GeoQuad(
            new GeoVertex[]{geoVertex4, geoVertex8, geoVertex7, geoVertex3},
            new double[]{boxUVCoords[0] + geoVector3d3.z, boxUVCoords[1]},
            new double[]{geoVector3d3.x, geoVector3d3.z},
            f2,
            f,
            cube.getMirror(),
            GeoDirection.UP
         );
         geoQuad6 = new GeoQuad(
            new GeoVertex[]{geoVertex2, geoVertex6, geoVertex5, geoVertex},
            new double[]{boxUVCoords[0] + geoVector3d3.z + geoVector3d3.x, boxUVCoords[1]},
            new double[]{geoVector3d3.x, geoVector3d3.z},
            f2,
            f,
            cube.getMirror(),
            GeoDirection.DOWN
         );
         if (cube.getMirror() == Boolean.TRUE) {
            geoQuad = new GeoQuad(
               new GeoVertex[]{geoVertex7, geoVertex8, geoVertex6, geoVertex5},
               new double[]{boxUVCoords[0] + geoVector3d3.z + geoVector3d3.x, boxUVCoords[1] + geoVector3d3.z},
               new double[]{geoVector3d3.z, geoVector3d3.y},
               f2,
               f,
               cube.getMirror(),
               GeoDirection.WEST
            );
            geoQuad2 = new GeoQuad(
               new GeoVertex[]{geoVertex4, geoVertex3, geoVertex, geoVertex2},
               new double[]{boxUVCoords[0], boxUVCoords[1] + geoVector3d3.z},
               new double[]{geoVector3d3.z, geoVector3d3.y},
               f2,
               f,
               cube.getMirror(),
               GeoDirection.EAST
            );
         }
      }

      geoCube.quads[0] = geoQuad;
      geoCube.quads[1] = geoQuad2;
      geoCube.quads[2] = geoQuad3;
      geoCube.quads[3] = geoQuad4;
      geoCube.quads[4] = geoQuad5;
      geoCube.quads[5] = geoQuad6;
      return geoCube;
   }

   private GeoCube(double[] doubles) {
      this.quads = new GeoQuad[6];
      this.size = new GeoVector3f(0.0F, 0.0F, 0.0F);
      if (doubles.length >= 3) {
         float f = (float)doubles[0];
         float f2 = (float)doubles[1];
         float f3 = (float)doubles[2];
         this.size.set(f, f2, f3);
      }
   }
}
