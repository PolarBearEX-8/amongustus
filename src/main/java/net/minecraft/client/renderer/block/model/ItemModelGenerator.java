package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector3f;

public class ItemModelGenerator {
   public static final List LAYERS = Lists.newArrayList(new String[]{"layer0", "layer1", "layer2", "layer3", "layer4"});

   private void createOrExpandSpan(List list, ItemModelGenerator.SpanFacing spanFacing, int n, int n2) {
      ItemModelGenerator.Span span = null;

      for (ItemModelGenerator.Span span2 : (Iterable<ItemModelGenerator.Span>)(list)) {
         if (span2.getSpanFacing() == spanFacing) {
            int n3 = ItemModelGenerator.SpanFacing.invokeIsHorizontal(spanFacing) ? n2 : n;
            if (span2.getAnchor() == n3) {
               span = span2;
               break;
            }
         }
      }

      int n4 = ItemModelGenerator.SpanFacing.invokeIsHorizontal(spanFacing) ? n2 : n;
      int n5 = ItemModelGenerator.SpanFacing.invokeIsHorizontal(spanFacing) ? n : n2;
      if (span == null) {
         list.add(new ItemModelGenerator.Span(spanFacing, n5, n4));
      } else {
         span.expand(n5);
      }
   }

   public ModelBlock makeItemModel(TextureMap textureMap, ModelBlock modelBlock) {
      HashMap map = Maps.newHashMap();
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < LAYERS.size(); i++) {
         String str = (String)LAYERS.get(i);
         if (!modelBlock.isTexturePresent(str)) {
            break;
         }

         String str2 = modelBlock.resolveTextureName(str);
         map.put(str, str2);
         list.addAll(this.getBlockParts(i, str, textureMap.getAtlasSprite(new ResourceLocation(str2).toString())));
      }

      if (list.isEmpty()) {
         return null;
      } else {
         map.put("particle", modelBlock.isTexturePresent("particle") ? modelBlock.resolveTextureName("particle") : (String)map.get("layer0"));
         return new ModelBlock(list, map, false, false, modelBlock.getAllTransforms());
      }
   }

   private boolean isTransparent(int[] ints, int n, int n2, int n3, int n4) {
      return n >= 0 && n2 >= 0 && n < n3 && n2 < n4 ? (ints[n2 * n3 + n] >> 24 & 0xFF) == 0 : true;
   }

   private List getSpans(TextureAtlasSprite textureAtlasSprite) {
      int iconWidth = textureAtlasSprite.getIconWidth();
      int iconHeight = textureAtlasSprite.getIconHeight();
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < textureAtlasSprite.getFrameCount(); i++) {
         int[] ints = textureAtlasSprite.getFrameTextureData(i)[0];

         for (int j = 0; j < iconHeight; j++) {
            for (int k = 0; k < iconWidth; k++) {
               boolean flag = !this.isTransparent(ints, k, j, iconWidth, iconHeight);
               this.checkTransition(ItemModelGenerator.SpanFacing.UP, list, ints, k, j, iconWidth, iconHeight, flag);
               this.checkTransition(ItemModelGenerator.SpanFacing.DOWN, list, ints, k, j, iconWidth, iconHeight, flag);
               this.checkTransition(ItemModelGenerator.SpanFacing.LEFT, list, ints, k, j, iconWidth, iconHeight, flag);
               this.checkTransition(ItemModelGenerator.SpanFacing.RIGHT, list, ints, k, j, iconWidth, iconHeight, flag);
            }
         }
      }

      return list;
   }

   private List getSideBlockParts(TextureAtlasSprite textureAtlasSprite, String str, int n) {
      float iconWidth = (float)textureAtlasSprite.getIconWidth();
      float iconHeight = (float)textureAtlasSprite.getIconHeight();
      ArrayList list = Lists.newArrayList();

      for (ItemModelGenerator.Span span : (Iterable<ItemModelGenerator.Span>)(this.getSpans(textureAtlasSprite))) {
         float f = 0.0F;
         float f2 = 0.0F;
         float f3 = 0.0F;
         float f4 = 0.0F;
         float f5 = 0.0F;
         float f6 = 0.0F;
         float f7 = 0.0F;
         float f8 = 0.0F;
         float f9 = 0.0F;
         float f10 = 0.0F;
         float min = (float)span.getMin();
         float max = (float)span.getMax();
         float anchor = (float)span.getAnchor();
         ItemModelGenerator.SpanFacing spanFacing = span.getSpanFacing();
         switch (spanFacing) {
            case UP:
               f5 = min;
               f = min;
               f3 = f6 = max + 1.0F;
               f7 = anchor;
               f2 = anchor;
               f8 = anchor;
               f4 = anchor;
               f9 = 16.0F / iconWidth;
               f10 = 16.0F / (iconHeight - 1.0F);
               break;
            case DOWN:
               f8 = anchor;
               f7 = anchor;
               f5 = min;
               f = min;
               f3 = f6 = max + 1.0F;
               f2 = anchor + 1.0F;
               f4 = anchor + 1.0F;
               f9 = 16.0F / iconWidth;
               f10 = 16.0F / (iconHeight - 1.0F);
               break;
            case LEFT:
               f5 = anchor;
               f = anchor;
               f6 = anchor;
               f3 = anchor;
               f8 = min;
               f2 = min;
               f4 = f7 = max + 1.0F;
               f9 = 16.0F / (iconWidth - 1.0F);
               f10 = 16.0F / iconHeight;
               break;
            case RIGHT:
               f6 = anchor;
               f5 = anchor;
               f = anchor + 1.0F;
               f3 = anchor + 1.0F;
               f8 = min;
               f2 = min;
               f4 = f7 = max + 1.0F;
               f9 = 16.0F / (iconWidth - 1.0F);
               f10 = 16.0F / iconHeight;
         }

         float f11 = 16.0F / iconWidth;
         float f12 = 16.0F / iconHeight;
         f *= f11;
         f3 *= f11;
         f2 *= f12;
         f4 *= f12;
         f2 = 16.0F - f2;
         f4 = 16.0F - f4;
         f5 *= f9;
         f6 *= f9;
         f7 *= f10;
         f8 *= f10;
         HashMap map = Maps.newHashMap();
         map.put(spanFacing.getFacing(), new BlockPartFace((EnumFacing)null, n, str, new BlockFaceUV(new float[]{f5, f7, f6, f8}, 0)));
         switch (spanFacing) {
            case UP:
               list.add(new BlockPart(new Vector3f(f, f2, 7.5F), new Vector3f(f3, f2, 8.5F), map, (BlockPartRotation)null, true));
               break;
            case DOWN:
               list.add(new BlockPart(new Vector3f(f, f4, 7.5F), new Vector3f(f3, f4, 8.5F), map, (BlockPartRotation)null, true));
               break;
            case LEFT:
               list.add(new BlockPart(new Vector3f(f, f2, 7.5F), new Vector3f(f, f4, 8.5F), map, (BlockPartRotation)null, true));
               break;
            case RIGHT:
               list.add(new BlockPart(new Vector3f(f3, f2, 7.5F), new Vector3f(f3, f4, 8.5F), map, (BlockPartRotation)null, true));
         }
      }

      return list;
   }

   private List getBlockParts(int n, String str, TextureAtlasSprite textureAtlasSprite) {
      HashMap map = Maps.newHashMap();
      map.put(EnumFacing.SOUTH, new BlockPartFace((EnumFacing)null, n, str, new BlockFaceUV(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0)));
      map.put(EnumFacing.NORTH, new BlockPartFace((EnumFacing)null, n, str, new BlockFaceUV(new float[]{16.0F, 0.0F, 0.0F, 16.0F}, 0)));
      ArrayList list = Lists.newArrayList();
      list.add(new BlockPart(new Vector3f(0.0F, 0.0F, 7.5F), new Vector3f(16.0F, 16.0F, 8.5F), map, (BlockPartRotation)null, true));
      list.addAll(this.getSideBlockParts(textureAtlasSprite, str, n));
      return list;
   }

   private void checkTransition(ItemModelGenerator.SpanFacing spanFacing, List list, int[] ints, int n, int n2, int n3, int n4, boolean flag) {
      if (this.isTransparent(ints, n + spanFacing.getXOffset(), n2 + spanFacing.getYOffset(), n3, n4) && flag) {
         this.createOrExpandSpan(list, spanFacing, n, n2);
      }
   }

public static class Span {
      private final ItemModelGenerator.SpanFacing spanFacing;
      private int min;
      private int max;
      private final int anchor;

      public int getMax() {
         return this.max;
      }

      public void expand(int n) {
         if (n < this.min) {
            this.min = n;
         } else if (n > this.max) {
            this.max = n;
         }
      }

      public int getMin() {
         return this.min;
      }

      public ItemModelGenerator.SpanFacing getSpanFacing() {
         return this.spanFacing;
      }

      public Span(ItemModelGenerator.SpanFacing spanFacing2, int n, int n2) {
         this.spanFacing = spanFacing2;
         this.min = n;
         this.max = n;
         this.anchor = n2;
      }

      public int getAnchor() {
         return this.anchor;
      }
   }

   public enum SpanFacing {
      UP(EnumFacing.UP, 0, -1),
      DOWN(EnumFacing.DOWN, 0, 1),
      LEFT(EnumFacing.EAST, -1, 0),
      RIGHT(EnumFacing.WEST, 1, 0);

   private final EnumFacing facing;
      private final int xOffset;
      private final int yOffset;
   public static boolean invokeIsHorizontal(SpanFacing spanFacing) {
         return spanFacing.isHorizontal();
      }

      public EnumFacing getFacing() {
         return this.facing;
      }

      public int getYOffset() {
         return this.yOffset;
      }

      private SpanFacing(EnumFacing facing2, int n, int n2) {
         this.facing = facing2;
         this.xOffset = n;
         this.yOffset = n2;
      }

      private boolean isHorizontal() {
         return this == DOWN || this == UP;
      }

      public int getXOffset() {
         return this.xOffset;
      }
   }
}
