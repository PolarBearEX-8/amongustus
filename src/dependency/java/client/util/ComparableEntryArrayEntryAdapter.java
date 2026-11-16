package client.util;

import client.setting.AdvancedSettingArrayEntry;
import client.setting.RenderChoiceProperty;
import net.labymod.Cube;
import net.labymod.FaceUv;
import net.labymod.ModelProperties;
import net.labymod.UvFaces;
import net.labymod.UvUnion;

public class ComparableEntryArrayEntryAdapter {
   public ComparableEntryAdapter[] comparableEntryAdapterArray = new ComparableEntryAdapter[6];
   public Boolean memberRef;
   public StaticFloatEntry staticFloatEntry = new StaticFloatEntry(0.0F, 0.0F, 0.0F);
   public double value;
   public StaticFloatEntry staticFloatEntry3;
   public StaticFloatEntry staticFloatEntry2;

   public static ComparableEntryArrayEntryAdapter resolveComparableEntryArrayEntryAda(Cube cube, ModelProperties modelProperties, Double offset, Boolean flag) {
      ComparableEntryArrayEntryAdapter comparableEntryArrayEntryAdapter = new ComparableEntryArrayEntryAdapter(cube.getSize());
      UvUnion uv = cube.getUv();
      UvFaces faceUV = uv.faceUV;
      boolean isBoxUV = uv.isBoxUV;
      comparableEntryArrayEntryAdapter.memberRef = cube.getMirror();
      comparableEntryArrayEntryAdapter.value = cube.getInflate() == null ? (offset == null ? 0.0 : offset) : cube.getInflate() / 16.0;
      float factor = modelProperties.getTextureHeight().floatValue();
      float factor2 = modelProperties.getTextureWidth().floatValue();
      DynamicDoubleEntry dynamicDoubleEntry = AdvancedSettingArrayEntry.resolveDynamicDoubleEntry(cube.getSize());
      DynamicDoubleEntry dynamicDoubleEntry2 = AdvancedSettingArrayEntry.resolveDynamicDoubleEntry(cube.getOrigin());
      DynamicDoubleEntry dynamicDoubleEntry3 = new DynamicDoubleEntry(-(dynamicDoubleEntry2.value2 + dynamicDoubleEntry.value2) / 16.0, dynamicDoubleEntry2.value3 / 16.0, dynamicDoubleEntry2.value / 16.0);
      dynamicDoubleEntry.value2 *= 0.0625;
      dynamicDoubleEntry.value3 *= 0.0625;
      dynamicDoubleEntry.value *= 0.0625;
      StaticFloatEntry staticFloatEntry4 = AdvancedSettingArrayEntry.resolveStaticFloatEntry(AdvancedSettingArrayEntry.resolveDynamicDoubleEntry(cube.getRotation()));
      staticFloatEntry4.factor2 *= -1.0F;
      staticFloatEntry4.factor3 *= -1.0F;
      float factor3 = (float)Math.toRadians((double)staticFloatEntry4.getFactor2());
      staticFloatEntry4.setFactor2(factor3);
      float factor4 = (float)Math.toRadians((double)staticFloatEntry4.getFactor3());
      staticFloatEntry4.setFactor3(factor4);
      float factor5 = (float)Math.toRadians((double)staticFloatEntry4.getFactor());
      staticFloatEntry4.setFactor(factor5);
      StaticFloatEntry staticFloatEntry5 = AdvancedSettingArrayEntry.resolveStaticFloatEntry(AdvancedSettingArrayEntry.resolveDynamicDoubleEntry(cube.getPivot()));
      staticFloatEntry5.factor2 *= -1.0F;
      comparableEntryArrayEntryAdapter.staticFloatEntry3 = staticFloatEntry5;
      comparableEntryArrayEntryAdapter.staticFloatEntry2 = staticFloatEntry4;
      IndirectFloatHolder indirectFloatHolder = new IndirectFloatHolder(dynamicDoubleEntry3.value2 - comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value3 - comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value - comparableEntryArrayEntryAdapter.value);
      IndirectFloatHolder indirectFloatHolder2 = new IndirectFloatHolder(dynamicDoubleEntry3.value2 - comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value3 - comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value + dynamicDoubleEntry.value + comparableEntryArrayEntryAdapter.value);
      IndirectFloatHolder indirectFloatHolder3 = new IndirectFloatHolder(dynamicDoubleEntry3.value2 - comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value3 + dynamicDoubleEntry.value3 + comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value - comparableEntryArrayEntryAdapter.value);
      IndirectFloatHolder indirectFloatHolder4 = new IndirectFloatHolder(
         dynamicDoubleEntry3.value2 - comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value3 + dynamicDoubleEntry.value3 + comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value + dynamicDoubleEntry.value + comparableEntryArrayEntryAdapter.value
      );
      IndirectFloatHolder indirectFloatHolder5 = new IndirectFloatHolder(dynamicDoubleEntry3.value2 + dynamicDoubleEntry.value2 + comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value3 - comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value - comparableEntryArrayEntryAdapter.value);
      IndirectFloatHolder indirectFloatHolder6 = new IndirectFloatHolder(
         dynamicDoubleEntry3.value2 + dynamicDoubleEntry.value2 + comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value3 - comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value + dynamicDoubleEntry.value + comparableEntryArrayEntryAdapter.value
      );
      IndirectFloatHolder indirectFloatHolder7 = new IndirectFloatHolder(
         dynamicDoubleEntry3.value2 + dynamicDoubleEntry.value2 + comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value3 + dynamicDoubleEntry.value3 + comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value - comparableEntryArrayEntryAdapter.value
      );
      IndirectFloatHolder indirectFloatHolder8 = new IndirectFloatHolder(
         dynamicDoubleEntry3.value2 + dynamicDoubleEntry.value2 + comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value3 + dynamicDoubleEntry.value3 + comparableEntryArrayEntryAdapter.value, dynamicDoubleEntry3.value + dynamicDoubleEntry.value + comparableEntryArrayEntryAdapter.value
      );
      ComparableEntryAdapter comparableEntryAdapter;
      ComparableEntryAdapter comparableEntryAdapter2;
      ComparableEntryAdapter comparableEntryAdapter3;
      ComparableEntryAdapter comparableEntryAdapter4;
      ComparableEntryAdapter comparableEntryAdapter5;
      ComparableEntryAdapter comparableEntryAdapter6;
      if (!isBoxUV) {
         FaceUv west = faceUV.getWest();
         FaceUv east = faceUV.getEast();
         FaceUv north = faceUV.getNorth();
         FaceUv south = faceUV.getSouth();
         FaceUv up = faceUV.getUp();
         FaceUv down = faceUV.getDown();
         comparableEntryAdapter = west == null
            ? null
            : new ComparableEntryAdapter(
               new IndirectFloatHolder[]{indirectFloatHolder4, indirectFloatHolder3, indirectFloatHolder, indirectFloatHolder2},
               west.getUv(),
               west.getUvSize(),
               factor2,
               factor,
               cube.getMirror(),
               RenderChoiceProperty.WEST
            );
         comparableEntryAdapter2 = east == null
            ? null
            : new ComparableEntryAdapter(
               new IndirectFloatHolder[]{indirectFloatHolder7, indirectFloatHolder8, indirectFloatHolder6, indirectFloatHolder5},
               east.getUv(),
               east.getUvSize(),
               factor2,
               factor,
               cube.getMirror(),
               RenderChoiceProperty.EAST
            );
         comparableEntryAdapter4 = north == null
            ? null
            : new ComparableEntryAdapter(
               new IndirectFloatHolder[]{indirectFloatHolder3, indirectFloatHolder7, indirectFloatHolder5, indirectFloatHolder},
               north.getUv(),
               north.getUvSize(),
               factor2,
               factor,
               cube.getMirror(),
               RenderChoiceProperty.NORTH
            );
         comparableEntryAdapter5 = south == null
            ? null
            : new ComparableEntryAdapter(
               new IndirectFloatHolder[]{indirectFloatHolder8, indirectFloatHolder4, indirectFloatHolder2, indirectFloatHolder6},
               south.getUv(),
               south.getUvSize(),
               factor2,
               factor,
               cube.getMirror(),
               RenderChoiceProperty.SOUTH
            );
         comparableEntryAdapter6 = up == null
            ? null
            : new ComparableEntryAdapter(
               new IndirectFloatHolder[]{indirectFloatHolder4, indirectFloatHolder8, indirectFloatHolder7, indirectFloatHolder3}, up.getUv(), up.getUvSize(), factor2, factor, cube.getMirror(), RenderChoiceProperty.UP
            );
         comparableEntryAdapter3 = down == null
            ? null
            : new ComparableEntryAdapter(
               new IndirectFloatHolder[]{indirectFloatHolder, indirectFloatHolder5, indirectFloatHolder6, indirectFloatHolder2},
               down.getUv(),
               down.getUvSize(),
               factor2,
               factor,
               cube.getMirror(),
               RenderChoiceProperty.DOWN
            );
         if (cube.getMirror() == Boolean.TRUE || flag == Boolean.TRUE) {
            comparableEntryAdapter = west == null
               ? null
               : new ComparableEntryAdapter(
                  new IndirectFloatHolder[]{indirectFloatHolder7, indirectFloatHolder8, indirectFloatHolder6, indirectFloatHolder5},
                  west.getUv(),
                  west.getUvSize(),
                  factor2,
                  factor,
                  cube.getMirror(),
                  RenderChoiceProperty.WEST
               );
            comparableEntryAdapter2 = east == null
               ? null
               : new ComparableEntryAdapter(
                  new IndirectFloatHolder[]{indirectFloatHolder4, indirectFloatHolder3, indirectFloatHolder, indirectFloatHolder2},
                  east.getUv(),
                  east.getUvSize(),
                  factor2,
                  factor,
                  cube.getMirror(),
                  RenderChoiceProperty.EAST
               );
         }
      } else {
         double[] boxUVCoords = cube.getUv().boxUVCoords;
         DynamicDoubleEntry dynamicDoubleEntry4 = AdvancedSettingArrayEntry.resolveDynamicDoubleEntry(cube.getSize());
         DynamicDoubleEntry dynamicDoubleEntry5 = new DynamicDoubleEntry(Math.floor(dynamicDoubleEntry4.value2), Math.floor(dynamicDoubleEntry4.value3), Math.floor(dynamicDoubleEntry4.value));
         comparableEntryAdapter = new ComparableEntryAdapter(
            new IndirectFloatHolder[]{indirectFloatHolder4, indirectFloatHolder3, indirectFloatHolder, indirectFloatHolder2},
            new double[]{boxUVCoords[0] + dynamicDoubleEntry5.value + dynamicDoubleEntry5.value2, boxUVCoords[1] + dynamicDoubleEntry5.value},
            new double[]{dynamicDoubleEntry5.value, dynamicDoubleEntry5.value3},
            factor2,
            factor,
            cube.getMirror(),
            RenderChoiceProperty.WEST
         );
         comparableEntryAdapter2 = new ComparableEntryAdapter(
            new IndirectFloatHolder[]{indirectFloatHolder7, indirectFloatHolder8, indirectFloatHolder6, indirectFloatHolder5},
            new double[]{boxUVCoords[0], boxUVCoords[1] + dynamicDoubleEntry5.value},
            new double[]{dynamicDoubleEntry5.value, dynamicDoubleEntry5.value3},
            factor2,
            factor,
            cube.getMirror(),
            RenderChoiceProperty.EAST
         );
         comparableEntryAdapter4 = new ComparableEntryAdapter(
            new IndirectFloatHolder[]{indirectFloatHolder3, indirectFloatHolder7, indirectFloatHolder5, indirectFloatHolder},
            new double[]{boxUVCoords[0] + dynamicDoubleEntry5.value, boxUVCoords[1] + dynamicDoubleEntry5.value},
            new double[]{dynamicDoubleEntry5.value2, dynamicDoubleEntry5.value3},
            factor2,
            factor,
            cube.getMirror(),
            RenderChoiceProperty.NORTH
         );
         comparableEntryAdapter5 = new ComparableEntryAdapter(
            new IndirectFloatHolder[]{indirectFloatHolder8, indirectFloatHolder4, indirectFloatHolder2, indirectFloatHolder6},
            new double[]{boxUVCoords[0] + dynamicDoubleEntry5.value + dynamicDoubleEntry5.value2 + dynamicDoubleEntry5.value, boxUVCoords[1] + dynamicDoubleEntry5.value},
            new double[]{dynamicDoubleEntry5.value2, dynamicDoubleEntry5.value3},
            factor2,
            factor,
            cube.getMirror(),
            RenderChoiceProperty.SOUTH
         );
         comparableEntryAdapter6 = new ComparableEntryAdapter(
            new IndirectFloatHolder[]{indirectFloatHolder4, indirectFloatHolder8, indirectFloatHolder7, indirectFloatHolder3},
            new double[]{boxUVCoords[0] + dynamicDoubleEntry5.value, boxUVCoords[1]},
            new double[]{dynamicDoubleEntry5.value2, dynamicDoubleEntry5.value},
            factor2,
            factor,
            cube.getMirror(),
            RenderChoiceProperty.UP
         );
         comparableEntryAdapter3 = new ComparableEntryAdapter(
            new IndirectFloatHolder[]{indirectFloatHolder2, indirectFloatHolder6, indirectFloatHolder5, indirectFloatHolder},
            new double[]{boxUVCoords[0] + dynamicDoubleEntry5.value + dynamicDoubleEntry5.value2, boxUVCoords[1]},
            new double[]{dynamicDoubleEntry5.value2, dynamicDoubleEntry5.value},
            factor2,
            factor,
            cube.getMirror(),
            RenderChoiceProperty.DOWN
         );
         if (cube.getMirror() == Boolean.TRUE) {
            comparableEntryAdapter = new ComparableEntryAdapter(
               new IndirectFloatHolder[]{indirectFloatHolder7, indirectFloatHolder8, indirectFloatHolder6, indirectFloatHolder5},
               new double[]{boxUVCoords[0] + dynamicDoubleEntry5.value + dynamicDoubleEntry5.value2, boxUVCoords[1] + dynamicDoubleEntry5.value},
               new double[]{dynamicDoubleEntry5.value, dynamicDoubleEntry5.value3},
               factor2,
               factor,
               cube.getMirror(),
               RenderChoiceProperty.WEST
            );
            comparableEntryAdapter2 = new ComparableEntryAdapter(
               new IndirectFloatHolder[]{indirectFloatHolder4, indirectFloatHolder3, indirectFloatHolder, indirectFloatHolder2},
               new double[]{boxUVCoords[0], boxUVCoords[1] + dynamicDoubleEntry5.value},
               new double[]{dynamicDoubleEntry5.value, dynamicDoubleEntry5.value3},
               factor2,
               factor,
               cube.getMirror(),
               RenderChoiceProperty.EAST
            );
         }
      }

      comparableEntryArrayEntryAdapter.comparableEntryAdapterArray[0] = comparableEntryAdapter;
      comparableEntryArrayEntryAdapter.comparableEntryAdapterArray[1] = comparableEntryAdapter2;
      comparableEntryArrayEntryAdapter.comparableEntryAdapterArray[2] = comparableEntryAdapter4;
      comparableEntryArrayEntryAdapter.comparableEntryAdapterArray[3] = comparableEntryAdapter5;
      comparableEntryArrayEntryAdapter.comparableEntryAdapterArray[4] = comparableEntryAdapter6;
      comparableEntryArrayEntryAdapter.comparableEntryAdapterArray[5] = comparableEntryAdapter3;
      return comparableEntryArrayEntryAdapter;
   }

   private ComparableEntryArrayEntryAdapter(double[] offsetArray) {
      if (offsetArray.length >= 3) {
         float factor = (float)offsetArray[0];
         float factor2 = (float)offsetArray[1];
         float factor3 = (float)offsetArray[2];
         float factor4 = factor2;
         float factor5 = factor;
         this.staticFloatEntry.applyFloat(factor5, factor4, factor3);
      }
   }
}
