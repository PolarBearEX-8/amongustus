package net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.types;

import com.viaversion.nbt.io.NBTIO;
import com.viaversion.nbt.limiter.TagLimiter;
import com.viaversion.nbt.tag.CompoundTag;
import com.viaversion.viaversion.api.type.Type;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class NBTType extends Type<CompoundTag> {
   public NBTType() {
      super(CompoundTag.class);
   }

   public CompoundTag read(ByteBuf buffer) {
      short length = buffer.readShort();
      if (length < 0) {
         return null;
      } else {
         ByteBuf data = buffer.readSlice(length);

         try {
            InputStream in = new GZIPInputStream(new ByteBufInputStream(data));

            CompoundTag compoundTag;
            try {
               compoundTag = (CompoundTag)NBTIO.readTag(new DataInputStream(in), TagLimiter.create(2097152, 512), true, CompoundTag.class);
            } catch (Throwable e) {
               try {
                  in.close();
               } catch (Throwable e2) {
                  e.addSuppressed(e2);
               }

               throw e;
            }

            in.close();
            return compoundTag;
         } catch (IOException e3) {
            throw new RuntimeException(e3);
         }
      }
   }

   public void write(ByteBuf buffer, CompoundTag nbt) {
      if (nbt == null) {
         buffer.writeShort(-1);
      } else {
         ByteBuf data = buffer.alloc().buffer();

         try {
            try {
               OutputStream out = new GZIPOutputStream(new ByteBufOutputStream(data));

               try {
                  NBTIO.writeTag(new DataOutputStream(out), nbt, true);
               } catch (Throwable e) {
                  try {
                     out.close();
                  } catch (Throwable e2) {
                     e.addSuppressed(e2);
                  }

                  throw e;
               }

               out.close();
            } catch (IOException e3) {
               throw new RuntimeException(e3);
            }

            buffer.writeShort(data.readableBytes());
            buffer.writeBytes(data);
         } finally {
            data.release();
         }
      }
   }
}
