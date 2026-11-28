package net.minecraft.client.renderer;

import net.minecraft.util.EnumFacing;

public enum EnumFaceDirection {
   DOWN(new EnumFaceDirection.VertexInformation[]{
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         )
      }),
   UP(new EnumFaceDirection.VertexInformation[]{
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         )
      }),
   NORTH(new EnumFaceDirection.VertexInformation[]{
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         )
      }),
   SOUTH(new EnumFaceDirection.VertexInformation[]{
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         )
      }),
   WEST(new EnumFaceDirection.VertexInformation[]{
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         )
      }),
   EAST(new EnumFaceDirection.VertexInformation[]{
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection.VertexInformation(
            EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX, null
         )
      });

private static final EnumFaceDirection[] facings = new EnumFaceDirection[6];
   private final EnumFaceDirection.VertexInformation[] vertexInfos;
public static EnumFaceDirection getFacing(EnumFacing facing) {
      return facings[facing.getIndex()];
   }

   public EnumFaceDirection.VertexInformation getVertexInformation(int n) {
      return this.vertexInfos[n];
   }

   private EnumFaceDirection(EnumFaceDirection.VertexInformation[] vertexInformations) {
      this.vertexInfos = vertexInformations;
   }

   static {
      facings[EnumFaceDirection.Constants.DOWN_INDEX] = DOWN;
      facings[EnumFaceDirection.Constants.UP_INDEX] = UP;
      facings[EnumFaceDirection.Constants.NORTH_INDEX] = NORTH;
      facings[EnumFaceDirection.Constants.SOUTH_INDEX] = SOUTH;
      facings[EnumFaceDirection.Constants.WEST_INDEX] = WEST;
      facings[EnumFaceDirection.Constants.EAST_INDEX] = EAST;
   }


   public static class InnerEntry {
   }

   public static final class Constants {
      public static final int SOUTH_INDEX = EnumFacing.SOUTH.getIndex();
      public static final int UP_INDEX = EnumFacing.UP.getIndex();
      public static final int EAST_INDEX = EnumFacing.EAST.getIndex();
      public static final int NORTH_INDEX = EnumFacing.NORTH.getIndex();
      public static final int DOWN_INDEX = EnumFacing.DOWN.getIndex();
      public static final int WEST_INDEX = EnumFacing.WEST.getIndex();
   }

   public static class VertexInformation {
      public final int xIndex;
      public final int yIndex;
      public final int zIndex;

      public VertexInformation(int n, int n2, int n3, EnumFaceDirection.InnerEntry obj) {
         this(n, n2, n3);
      }

      private VertexInformation(int n, int n2, int n3) {
         this.xIndex = n;
         this.yIndex = n2;
         this.zIndex = n3;
      }
   }
}
