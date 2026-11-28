package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.util.MathHelper;

public class Stitcher {
   private final int mipmapLevelStitcher;
   private final Set setStitchHolders = Sets.newHashSetWithExpectedSize(256);
   private final List stitchSlots = Lists.newArrayListWithCapacity(256);
   private int currentWidth;
   private int currentHeight;
   private final int maxWidth;
   private final int maxHeight;
   private final boolean forcePowerOf2;
   private final int maxTileDimension;

   public List getStichSlots() {
      ArrayList list = Lists.newArrayList();

      for (Stitcher.Slot slot : (Iterable<Stitcher.Slot>)(this.stitchSlots)) {
         slot.getAllStitchSlots(list);
      }

      ArrayList list2 = Lists.newArrayList();

      for (Stitcher.Slot slot2 : (Iterable<Stitcher.Slot>)(list)) {
         Stitcher.Holder stitchHolder = slot2.getStitchHolder();
         TextureAtlasSprite atlasSprite = stitchHolder.getAtlasSprite();
         atlasSprite.initSprite(this.currentWidth, this.currentHeight, slot2.getOriginX(), slot2.getOriginY(), stitchHolder.isRotated());
         list2.add(atlasSprite);
      }

      return list2;
   }

   public Stitcher(int n, int n2, boolean flag, int n3, int n4) {
      this.mipmapLevelStitcher = n4;
      this.maxWidth = n;
      this.maxHeight = n2;
      this.forcePowerOf2 = flag;
      this.maxTileDimension = n3;
   }

   public void addSprite(TextureAtlasSprite textureAtlasSprite) {
      Stitcher.Holder holder = new Stitcher.Holder(textureAtlasSprite, this.mipmapLevelStitcher);
      if (this.maxTileDimension > 0) {
         holder.setNewDimension(this.maxTileDimension);
      }

      this.setStitchHolders.add(holder);
   }

   private boolean expandAndAllocateSlot(Stitcher.Holder holder) {
      int n = Math.min(holder.getWidth(), holder.getHeight());
      boolean flag = this.currentWidth == 0 && this.currentHeight == 0;
      boolean flag2;
      if (this.forcePowerOf2) {
         int n2 = MathHelper.roundUpToPowerOfTwo(this.currentWidth);
         int n3 = MathHelper.roundUpToPowerOfTwo(this.currentHeight);
         int n4 = MathHelper.roundUpToPowerOfTwo(this.currentWidth + n);
         int n5 = MathHelper.roundUpToPowerOfTwo(this.currentHeight + n);
         boolean flag3 = n4 <= this.maxWidth;
         boolean flag4 = n5 <= this.maxHeight;
         if (!flag3 && !flag4) {
            return false;
         }

         boolean flag5 = n2 != n4;
         if (flag5 ^ (n3 != n5)) {
            flag2 = !flag5;
         } else {
            flag2 = flag3 && n2 <= n3;
         }
      } else {
         boolean flag7 = this.currentWidth + n <= this.maxWidth;
         boolean flag8 = this.currentHeight + n <= this.maxHeight;
         if (!flag7 && !flag8) {
            return false;
         }

         flag2 = flag7 && (flag || this.currentWidth <= this.currentHeight);
      }

      int n6 = Math.max(holder.getWidth(), holder.getHeight());
      if (MathHelper.roundUpToPowerOfTwo((!flag2 ? this.currentHeight : this.currentWidth) + n6) > (!flag2 ? this.maxHeight : this.maxWidth)) {
         return false;
      } else {
         Stitcher.Slot slot;
         if (flag2) {
            if (holder.getWidth() > holder.getHeight()) {
               holder.rotate();
            }

            if (this.currentHeight == 0) {
               this.currentHeight = holder.getHeight();
            }

            slot = new Stitcher.Slot(this.currentWidth, 0, holder.getWidth(), this.currentHeight);
            this.currentWidth = this.currentWidth + holder.getWidth();
         } else {
            slot = new Stitcher.Slot(0, this.currentHeight, this.currentWidth, holder.getHeight());
            this.currentHeight = this.currentHeight + holder.getHeight();
         }

         slot.addSlot(holder);
         this.stitchSlots.add(slot);
         return true;
      }
   }

   public int getCurrentWidth() {
      return this.currentWidth;
   }

   private boolean allocateSlot(Stitcher.Holder holder) {
      for (int i = 0; i < this.stitchSlots.size(); i++) {
         if (((Stitcher.Slot)this.stitchSlots.get(i)).addSlot(holder)) {
            return true;
         }

         holder.rotate();
         if (((Stitcher.Slot)this.stitchSlots.get(i)).addSlot(holder)) {
            return true;
         }

         holder.rotate();
      }

      return this.expandAndAllocateSlot(holder);
   }

   public static int computeMipmapDimension(int n, int n2) {
      return getMipmapDimension(n, n2);
   }

   private static int getMipmapDimension(int n, int n2) {
      return (n >> n2) + ((n & (1 << n2) - 1) == 0 ? 0 : 1) << n2;
   }

   public int getCurrentHeight() {
      return this.currentHeight;
   }

   public void doStitch() {
      Stitcher.Holder[] holders = (Stitcher.Holder[])this.setStitchHolders.toArray(new Stitcher.Holder[this.setStitchHolders.size()]);
      Arrays.sort(holders);

      for (Stitcher.Holder holder : holders) {
         if (!this.allocateSlot(holder)) {
            String str = String.format(
               "Unable to fit: %s, size: %dx%d, atlas: %dx%d, atlasMax: %dx%d - Maybe try a lower resolution resourcepack?",
               holder.getAtlasSprite().getIconName(),
               holder.getAtlasSprite().getIconWidth(),
               holder.getAtlasSprite().getIconHeight(),
               this.currentWidth,
               this.currentHeight,
               this.maxWidth,
               this.maxHeight
            );
            throw new StitcherException(holder, str);
         }
      }

      if (this.forcePowerOf2) {
         this.currentWidth = MathHelper.roundUpToPowerOfTwo(this.currentWidth);
         this.currentHeight = MathHelper.roundUpToPowerOfTwo(this.currentHeight);
      }
   }


   public static class Slot {
      private final int originX;
      private final int originY;
      private final int width;
      private final int height;
      private List subSlots;
      private Stitcher.Holder holder;

      public void getAllStitchSlots(List list) {
         if (this.holder != null) {
            list.add(this);
         } else if (this.subSlots != null) {
            for (Slot slot : (Iterable<Slot>)(this.subSlots)) {
               slot.getAllStitchSlots(list);
            }
         }
      }

      public int getOriginX() {
         return this.originX;
      }

      public boolean addSlot(Stitcher.Holder holder2) {
         if (this.holder != null) {
            return false;
         } else {
            int width2 = holder2.getWidth();
            int height2 = holder2.getHeight();
            if (width2 <= this.width && height2 <= this.height) {
               if (width2 == this.width && height2 == this.height) {
                  this.holder = holder2;
                  return true;
               } else {
                  if (this.subSlots == null) {
                     this.subSlots = Lists.newArrayListWithCapacity(1);
                     this.subSlots.add(new Slot(this.originX, this.originY, width2, height2));
                     int n = this.width - width2;
                     int n2 = this.height - height2;
                     if (n2 > 0 && n > 0) {
                        if (Math.max(this.height, n) >= Math.max(this.width, n2)) {
                           this.subSlots.add(new Slot(this.originX, this.originY + height2, width2, n2));
                           this.subSlots.add(new Slot(this.originX + width2, this.originY, n, this.height));
                        } else {
                           this.subSlots.add(new Slot(this.originX + width2, this.originY, n, height2));
                           this.subSlots.add(new Slot(this.originX, this.originY + height2, this.width, n2));
                        }
                     } else if (n == 0) {
                        this.subSlots.add(new Slot(this.originX, this.originY + height2, width2, n2));
                     } else if (n2 == 0) {
                        this.subSlots.add(new Slot(this.originX + width2, this.originY, n, height2));
                     }
                  }

                  for (Slot slot : (Iterable<Slot>)(this.subSlots)) {
                     if (slot.addSlot(holder2)) {
                        return true;
                     }
                  }

                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public Stitcher.Holder getStitchHolder() {
         return this.holder;
      }

      public Slot(int n, int n2, int n3, int n4) {
         this.originX = n;
         this.originY = n2;
         this.width = n3;
         this.height = n4;
      }

      public int getOriginY() {
         return this.originY;
      }

      @Override
      public String toString() {
         return "Slot{originX="
            + this.originX
            + ", originY="
            + this.originY
            + ", width="
            + this.width
            + ", height="
            + this.height
            + ", texture="
            + this.holder
            + ", subSlots="
            + this.subSlots
            + '}';
      }
   }

   public static class Holder implements Comparable {
      private final TextureAtlasSprite theTexture;
      private final int width;
      private final int height;
      private final int mipmapLevelHolder;
      private boolean rotated;
      private float scaleFactor = 1.0F;

      public int getHeight() {
         return this.rotated
            ? Stitcher.computeMipmapDimension((int)((float)this.width * this.scaleFactor), this.mipmapLevelHolder)
            : Stitcher.computeMipmapDimension((int)((float)this.height * this.scaleFactor), this.mipmapLevelHolder);
      }

      public void rotate() {
         this.rotated = !this.rotated;
      }

      public int compareTo(Holder other) {
         int n;
         if (this.getHeight() == other.getHeight()) {
            if (this.getWidth() == other.getWidth()) {
               if (this.theTexture.getIconName() == null) {
                  return other.theTexture.getIconName() == null ? 0 : -1;
               }

               return this.theTexture.getIconName().compareTo(other.theTexture.getIconName());
            }

            n = this.getWidth() < other.getWidth() ? 1 : -1;
         } else {
            n = this.getHeight() < other.getHeight() ? 1 : -1;
         }

         return n;
      }

      @Override
      public int compareTo(Object other) {
         return this.compareTo((Holder)other);
      }

      public void setNewDimension(int n) {
         if (this.width > n && this.height > n) {
            this.scaleFactor = (float)n / (float)Math.min(this.width, this.height);
         }
      }

      @Override
      public String toString() {
         return "Holder{width=" + this.width + ", height=" + this.height + '}';
      }

      public Holder(TextureAtlasSprite textureAtlasSprite, int n) {
         this.theTexture = textureAtlasSprite;
         this.width = textureAtlasSprite.getIconWidth();
         this.height = textureAtlasSprite.getIconHeight();
         this.mipmapLevelHolder = n;
         this.rotated = Stitcher.computeMipmapDimension(this.height, n) > Stitcher.computeMipmapDimension(this.width, n);
      }

      public TextureAtlasSprite getAtlasSprite() {
         return this.theTexture;
      }

      public int getWidth() {
         return this.rotated
            ? Stitcher.computeMipmapDimension((int)((float)this.height * this.scaleFactor), this.mipmapLevelHolder)
            : Stitcher.computeMipmapDimension((int)((float)this.width * this.scaleFactor), this.mipmapLevelHolder);
      }

      public boolean isRotated() {
         return this.rotated;
      }
   }
}
