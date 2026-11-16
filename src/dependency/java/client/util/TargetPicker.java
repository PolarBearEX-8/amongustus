package client.util;

import client.event.PreUpdateSubscriber;
import client.setting.NativeOptionType;
import client.setting.RenderStateProperty;
import client.setting.StaticOptionType;
import client.setting.VirtualOptionType;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TargetPicker implements PreUpdateSubscriber {
   public RenderStateProperty renderStateProperty;
   public int count;
   public int count2;

   @Override
   public int getCount() {
      return this.renderStateProperty.getCount();
   }

   @Override
   public int getCount2() {
      return this.count2;
   }

   @Override
   public int getCountAsIndex() {
      return this.count;
   }

   public TargetPicker(
      RenderStateProperty renderStateProperty2,
      NativeOptionType nativeOptionType,
      int count3,
      int count4,
      int count5,
      VirtualOptionType virtualOptionType,
      StaticOptionType staticOptionType,
      ByteBuffer buffer,
      int count6,
      boolean flag,
      boolean flag2
   ) {
      this.renderStateProperty = renderStateProperty2;
      this.count = count6;
      this.count2 = GL11.glGenTextures();
      GL11.glBindTexture(this.getCount(), this.count2);
      int count7 = flag2 ? '\u812f' : 10497;
      int count8 = flag ? 9729 : 9728;
      switch (TargetPickerRenderStatePropertySwitchMap.countArray[renderStateProperty2.ordinal()]) {
         case 1:
            GL11.glTexImage1D(3552, 0, nativeOptionType.getCount(), count3, 0, virtualOptionType.getCount(), staticOptionType.getCount(), buffer);
            GL11.glTexParameteri(3552, 10242, count7);
            GL11.glTexParameteri(3552, 10240, count8);
            GL11.glTexParameteri(3552, 10241, count8);
            break;
         case 2:
            GL11.glTexImage2D(3553, 0, nativeOptionType.getCount(), count3, count4, 0, virtualOptionType.getCount(), staticOptionType.getCount(), buffer);
            GL11.glTexParameteri(3553, 10242, count7);
            GL11.glTexParameteri(3553, 10243, count7);
            GL11.glTexParameteri(3553, 10240, count8);
            GL11.glTexParameteri(3553, 10241, count8);
            break;
         case 3:
            GL12.glTexImage3D(32879, 0, nativeOptionType.getCount(), count3, count4, count5, 0, virtualOptionType.getCount(), staticOptionType.getCount(), buffer);
            GL11.glTexParameteri(32879, 10242, count7);
            GL11.glTexParameteri(32879, 10243, count7);
            GL11.glTexParameteri(32879, 32882, count7);
            GL11.glTexParameteri(32879, 10240, count8);
            GL11.glTexParameteri(32879, 10241, count8);
            break;
         case 4:
            GL11.glTexImage2D(34037, 0, nativeOptionType.getCount(), count3, count4, 0, virtualOptionType.getCount(), staticOptionType.getCount(), buffer);
            GL11.glTexParameteri(34037, 10242, count7);
            GL11.glTexParameteri(34037, 10243, count7);
            GL11.glTexParameteri(34037, 10240, count8);
            GL11.glTexParameteri(34037, 10241, count8);
      }

      GL11.glBindTexture(this.getCount(), 0);
   }

   @Override
   public void glDeleteTextures() {
      if (this.count2 > 0) {
         GL11.glDeleteTextures(this.count2);
         this.count2 = 0;
      }
   }
}
