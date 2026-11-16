package client.util;

import augustus.modules.misc.ExploitFixer;
import client.Client;
import client.module.Module;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import io.netty.util.ByteProcessor;
import io.netty.util.ReferenceCounted;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.UUID;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.IChatComponent;

public class PacketBuffer extends ByteBuf {
   public final ByteBuf byteBuf;

   public ByteBuf setIndex(int count, int count2) {
      return this.byteBuf.setIndex(count, count2);
   }

   public float readFloat() {
      return this.byteBuf.readFloat();
   }

   public ByteBuf writeFloat(float factor) {
      return this.byteBuf.writeFloat(factor);
   }

   public short readShort() {
      return this.byteBuf.readShort();
   }

   public int readUnsignedShort() {
      return this.byteBuf.readUnsignedShort();
   }

   public ByteBuf writeShort(int count) {
      return this.byteBuf.writeShort(count);
   }

   public IChatComponent getChatComponent() throws IOException {
      return ChatComponentSerializer.parseChatComponent(this.resolveText(32767));
   }

   public long getTimestamp() {
      long value = 0L;
      int count = 0;

      byte byteValue;
      do {
         byteValue = this.readByte();
         value |= (long)(byteValue & 127) << count++ * 7;
         if (count > 10) {
            throw new RuntimeException("VarLong too big");
         }
      } while ((byteValue & 128) == 128);

      return value;
   }

   public void applyEnum(Enum enumItem) {
      this.updateInt(enumItem.ordinal());
   }

   public byte readByte() {
      return this.byteBuf.readByte();
   }

   public ByteBuf writeByte(int count) {
      return this.byteBuf.writeByte(count);
   }

   public long readLong() {
      return this.byteBuf.readLong();
   }

   public ByteBuf writeLong(long value) {
      return this.byteBuf.writeLong(value);
   }

   public boolean isWritable() {
      return this.byteBuf.isWritable();
   }

   public boolean isWritable(int count) {
      return this.byteBuf.isWritable(count);
   }

   public boolean isReadable(int count) {
      return this.byteBuf.isReadable(count);
   }

   public boolean isReadable() {
      return this.byteBuf.isReadable();
   }

   public ByteBuf retainedDuplicate() {
      return this.byteBuf.retainedDuplicate();
   }

   public static int computeCount(int count) {
      for (int index = 1; index < 5; index++) {
         if ((count & -1 << index * 7) == 0) {
            return index;
         }
      }

      return 5;
   }

   public byte[] getByteArray() {
      byte[] byteValueArray = new byte[this.getInt3()];
      this.readBytes(byteValueArray);
      return byteValueArray;
   }

   public PacketBuffer getPacketBuffer(String text) {
      byte[] bytes = text.getBytes(Charsets.UTF_8);
      if (bytes.length > 32767) {
         throw new EncoderException("String too big (was " + text.length() + " bytes encoded, max " + 32767 + ")");
      } else {
         this.updateInt(bytes.length);
         this.writeBytes(bytes);
         return this;
      }
   }

   public void setChatComponent(IChatComponent chatComponent) throws IOException {
      this.getPacketBuffer(ChatComponentSerializer.toJson(chatComponent));
   }

   public ItemStack getItemStack() throws IOException {
      ItemStack stack = null;
      ExploitFixer exploitFixer = Client.getClient().getModuleManager().exploitFixer;
      if (((Module)exploitFixer).isToggled() && exploitFixer.settingItemReading.isEnabled() && this.readableBytes() <= 0) {
         return null;
      } else {
         short shortValue = this.readShort();
         if (shortValue >= 0) {
            byte byteValue = this.readByte();
            short shortValue2 = this.readShort();
            stack = new ItemStack(Item.processAction(shortValue), byteValue, shortValue2);
            if (((Module)exploitFixer).isToggled() && exploitFixer.settingItemReading.isEnabled()) {
               try {
                  stack.processAction(this.getNBTTagCompound());
               } catch (Exception exception) {
               }
            } else {
               stack.processAction(this.getNBTTagCompound());
            }
         }

         return stack;
      }
   }

   public String resolveTextByIndex(int count) {
      int intItem = this.getInt3();
      if (intItem > count * 4) {
         throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + intItem + " > " + count * 4 + ")");
      } else if (intItem < 0) {
         throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
      } else {
         String text = new String(this.toString(this.byteBuf.readerIndex(), intItem, Charsets.UTF_8));
         this.readerIndex(this.readerIndex() + intItem);
         if (text.length() > count) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + intItem + " > " + count + ")");
         } else {
            return text;
         }
      }
   }

   public int getInt3() {
      int count = 0;
      int count2 = 0;

      byte byteValue;
      do {
         byteValue = this.readByte();
         count |= (byteValue & 127) << count2++ * 7;
         if (count2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((byteValue & 128) == 128);

      return count;
   }

   public void updateInt(int count) {
      int count2;
      for (count2 = count; (count2 & -128) != 0; count2 >>>= 7) {
         this.writeByte(count2 & 127 | 128);
      }

      this.writeByte(count2);
   }

   public void applyBlockPos(BlockPos blockPos) {
      this.writeLong(blockPos.getTimestamp());
   }

   public void applyUUID(UUID uuid) {
      this.writeLong(uuid.getMostSignificantBits());
      this.writeLong(uuid.getLeastSignificantBits());
   }

   public BlockPos getBlockPos() {
      return BlockPos.resolveBlockPos(this.readLong());
   }

   public String resolveText(int count) {
      int intItem = this.getInt3();
      if (intItem > count * 4) {
         throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + intItem + " > " + count * 4 + ")");
      } else if (intItem < 0) {
         throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
      } else {
         String text = new String(this.toString(this.byteBuf.readerIndex(), intItem, Charsets.UTF_8));
         this.readerIndex(this.readerIndex() + intItem);
         if (text.length() > count) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + intItem + " > " + count + ")");
         } else {
            return text;
         }
      }
   }

   public void applyItemStack(ItemStack stack) {
      if (stack == null) {
         this.writeShort(-1);
      } else {
         this.writeShort(Item.getIndex(stack.getItem()));
         this.writeByte(stack.animationsToGo);
         this.writeShort(stack.getIndexOffset());
         NBTTagCompound tagCompound = null;
         if (stack.getItem().isActive() || stack.getItem().processAction()) {
            tagCompound = stack.getStackTagCompound();
         }

         this.applyNBTTagCompound(tagCompound);
      }
   }

   public void applyByteArray(byte[] byteValueArray) {
      this.updateInt(byteValueArray.length);
      this.writeBytes(byteValueArray);
   }

   public NBTTagCompound getNBTTagCompound() throws IOException {
      int count = this.readerIndex();
      if (this.readByte() == 0) {
         return null;
      } else {
         this.readerIndex(count);
         return LoadingNBTDataManager.resolveNBTTagCompound(new ByteBufInputStream(this), new BytesWhereMaxAllowedManager(2097152L));
      }
   }

   public void applyTime(long value) {
      long value2;
      for (value2 = value; (value2 & -128L) != 0L; value2 >>>= 7) {
         this.writeByte((int)(value2 & 127L) | 128);
      }

      this.writeByte((int)value2);
   }

   public long memoryAddress() {
      return this.byteBuf.memoryAddress();
   }

   public ByteBuf retain() {
      return this.byteBuf.retain();
   }

   public ByteBuf retain(int count) {
      return this.byteBuf.retain(count);
   }

   public int refCnt() {
      return this.byteBuf.refCnt();
   }

   public ByteBuf touch(Object entry) {
      return this.byteBuf.touch(entry);
   }

   public ByteBuf touch() {
      return this.byteBuf.touch();
   }

   public Enum getEnum(Class clazz) {
      return ((Enum[])clazz.getEnumConstants())[this.getInt3()];
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      if (tagCompound == null) {
         this.writeByte(0);
      } else {
         try {
            LoadingNBTDataManager.applyNBTTagCompound(tagCompound, (java.io.DataOutput)new ByteBufOutputStream(this));
         } catch (IOException ioException) {
            throw new EncoderException(ioException);
         }
      }
   }

   public UUID getUUID() {
      return new UUID(this.readLong(), this.readLong());
   }

   public int getUnsignedMedium(int count) {
      return this.byteBuf.getUnsignedMedium(count);
   }

   public int readUnsignedShortLE() {
      return this.byteBuf.readUnsignedShortLE();
   }

   public int getUnsignedMediumLE(int count) {
      return this.byteBuf.getUnsignedMediumLE(count);
   }

   public ByteBuf readRetainedSlice(int count) {
      return this.byteBuf.readRetainedSlice(count);
   }

   public ByteBuf discardSomeReadBytes() {
      return this.byteBuf.discardSomeReadBytes();
   }

   public int getUnsignedShortLE(int count) {
      return this.byteBuf.getUnsignedShortLE(count);
   }

   public int writeCharSequence(CharSequence text, Charset charset) {
      return this.byteBuf.writeCharSequence(text, charset);
   }

   public long readUnsignedIntLE() {
      return this.byteBuf.readUnsignedIntLE();
   }

   public ByteBuffer internalNioBuffer(int count, int count2) {
      return this.byteBuf.internalNioBuffer(count, count2);
   }

   public int readUnsignedMediumLE() {
      return this.byteBuf.readUnsignedMediumLE();
   }

   public int readUnsignedMedium() {
      return this.byteBuf.readUnsignedMedium();
   }

   public int maxCapacity() {
      return this.byteBuf.maxCapacity();
   }

   public ByteBuf writeZero(int count) {
      return this.byteBuf.writeZero(count);
   }

   public int maxWritableBytes() {
      return this.byteBuf.maxWritableBytes();
   }

   public ByteBuf setShortLE(int count, int count2) {
      return this.byteBuf.setShortLE(count, count2);
   }

   public ByteBuf resetWriterIndex() {
      return this.byteBuf.resetWriterIndex();
   }

   public int writableBytes() {
      return this.byteBuf.writableBytes();
   }

   public ByteBuf readSlice(int count) {
      return this.byteBuf.readSlice(count);
   }

   public long getLongLE(int count) {
      return this.byteBuf.getLongLE(count);
   }

   public ByteBuf writeIntLE(int count) {
      return this.byteBuf.writeIntLE(count);
   }

   public ByteBuf setMedium(int count, int count2) {
      return this.byteBuf.setMedium(count, count2);
   }

   public ByteBuf resetReaderIndex() {
      return this.byteBuf.resetReaderIndex();
   }

   public short getShortLE(int count) {
      return this.byteBuf.getShortLE(count);
   }

   public double readDouble() {
      return this.byteBuf.readDouble();
   }

   public int getUnsignedShort(int count) {
      return this.byteBuf.getUnsignedShort(count);
   }

   public ByteBuffer[] nioBuffers(int count, int count2) {
      return this.byteBuf.nioBuffers(count, count2);
   }

   public ByteBuffer[] nioBuffers() {
      return this.byteBuf.nioBuffers();
   }

   public ByteBuf writeDouble(double offset) {
      return this.byteBuf.writeDouble(offset);
   }

   public long getUnsignedInt(int count) {
      return this.byteBuf.getUnsignedInt(count);
   }

   public CharSequence getCharSequence(int count, int count2, Charset charset) {
      return this.byteBuf.getCharSequence(count, count2, charset);
   }

   public int getIntLE(int count) {
      return this.byteBuf.getIntLE(count);
   }

   public ByteBuf writeShortLE(int count) {
      return this.byteBuf.writeShortLE(count);
   }

   public int ensureWritable(int count, boolean flag) {
      return this.byteBuf.ensureWritable(count, flag);
   }

   public ByteBuf ensureWritable(int count) {
      return this.byteBuf.ensureWritable(count);
   }

   public int getMedium(int count) {
      return this.byteBuf.getMedium(count);
   }

   public int readIntLE() {
      return this.byteBuf.readIntLE();
   }

   public ByteBuf writeMedium(int count) {
      return this.byteBuf.writeMedium(count);
   }

   public ByteBufAllocator alloc() {
      return this.byteBuf.alloc();
   }

   public long readUnsignedInt() {
      return this.byteBuf.readUnsignedInt();
   }

   public int bytesBefore(byte byteValue) {
      return this.byteBuf.bytesBefore(byteValue);
   }

   public int bytesBefore(int count, byte byteValue) {
      return this.byteBuf.bytesBefore(count, byteValue);
   }

   public int bytesBefore(int count, int count2, byte byteValue) {
      return this.byteBuf.bytesBefore(count, count2, byteValue);
   }

   public int readMedium() {
      return this.byteBuf.readMedium();
   }

   public ByteBuf retainedSlice(int count, int count2) {
      return this.byteBuf.retainedSlice(count, count2);
   }

   public ByteBuf retainedSlice() {
      return this.byteBuf.retainedSlice();
   }

   public ByteBuf setMediumLE(int count, int count2) {
      return this.byteBuf.setMediumLE(count, count2);
   }

   public int nioBufferCount() {
      return this.byteBuf.nioBufferCount();
   }

   public CharSequence readCharSequence(int count, Charset charset) {
      return this.byteBuf.readCharSequence(count, charset);
   }

   public int forEachByte(int count, int count2, ByteProcessor byteProcessor) {
      return this.byteBuf.forEachByte(count, count2, byteProcessor);
   }

   public int forEachByte(ByteProcessor byteProcessor) {
      return this.byteBuf.forEachByte(byteProcessor);
   }

   public ByteBuf markReaderIndex() {
      return this.byteBuf.markReaderIndex();
   }

   public int setCharSequence(int count, CharSequence text, Charset charset) {
      return this.byteBuf.setCharSequence(count, text, charset);
   }

   public ByteBuf setIntLE(int count, int count2) {
      return this.byteBuf.setIntLE(count, count2);
   }

   public ByteBuf markWriterIndex() {
      return this.byteBuf.markWriterIndex();
   }

   public ByteBuf asReadOnly() {
      return this.byteBuf.asReadOnly();
   }

   public boolean readBoolean() {
      return this.byteBuf.readBoolean();
   }

   public short readShortLE() {
      return this.byteBuf.readShortLE();
   }

   public long getUnsignedIntLE(int count) {
      return this.byteBuf.getUnsignedIntLE(count);
   }

   public int writerIndex() {
      return this.byteBuf.writerIndex();
   }

   public ByteBuf writerIndex(int count) {
      return this.byteBuf.writerIndex(count);
   }

   public ByteBuf setBytes(int count, ByteBuffer buffer) {
      return this.byteBuf.setBytes(count, buffer);
   }

   public ByteBuf setBytes(int count, ByteBuf byteBuf2) {
      return this.byteBuf.setBytes(count, byteBuf2);
   }

   public ByteBuf setBytes(int count, byte[] byteValueArray) {
      return this.byteBuf.setBytes(count, byteValueArray);
   }

   public int setBytes(int count, InputStream inputStream, int count2) throws IOException {
      return this.byteBuf.setBytes(count, inputStream, count2);
   }

   public ByteBuf setBytes(int count, byte[] byteValueArray, int count2, int count3) {
      return this.byteBuf.setBytes(count, byteValueArray, count2, count3);
   }

   public ByteBuf setBytes(int count, ByteBuf byteBuf2, int count2) {
      return this.byteBuf.setBytes(count, byteBuf2, count2);
   }

   public ByteBuf setBytes(int count, ByteBuf byteBuf2, int count2, int count3) {
      return this.byteBuf.setBytes(count, byteBuf2, count2, count3);
   }

   public int setBytes(int count, FileChannel fileChannel, long value, int count2) throws IOException {
      return this.byteBuf.setBytes(count, fileChannel, value, count2);
   }

   public int setBytes(int count, ScatteringByteChannel scatteringByteChannel, int count2) throws IOException {
      return this.byteBuf.setBytes(count, scatteringByteChannel, count2);
   }

   public ByteBuf writeBoolean(boolean flag) {
      return this.byteBuf.writeBoolean(flag);
   }

   public ByteBuf setZero(int count, int count2) {
      return this.byteBuf.setZero(count, count2);
   }

   public short readUnsignedByte() {
      return this.byteBuf.readUnsignedByte();
   }

   public ByteBuf readerIndex(int count) {
      return this.byteBuf.readerIndex(count);
   }

   public int readerIndex() {
      return this.byteBuf.readerIndex();
   }

   public int readMediumLE() {
      return this.byteBuf.readMediumLE();
   }

   public int forEachByteDesc(ByteProcessor byteProcessor) {
      return this.byteBuf.forEachByteDesc(byteProcessor);
   }

   public int forEachByteDesc(int count, int count2, ByteProcessor byteProcessor) {
      return this.byteBuf.forEachByteDesc(count, count2, byteProcessor);
   }

   public ByteBuf setLongLE(int count, long value) {
      return this.byteBuf.setLongLE(count, value);
   }

   public int readableBytes() {
      return this.byteBuf.readableBytes();
   }

   public ByteBuf discardReadBytes() {
      return this.byteBuf.discardReadBytes();
   }

   public boolean hasMemoryAddress() {
      return this.byteBuf.hasMemoryAddress();
   }

   public short getUnsignedByte(int count) {
      return this.byteBuf.getUnsignedByte(count);
   }

   public ByteBuf writeMediumLE(int count) {
      return this.byteBuf.writeMediumLE(count);
   }

   public ByteBuf writeLongLE(long value) {
      return this.byteBuf.writeLongLE(value);
   }

   public long readLongLE() {
      return this.byteBuf.readLongLE();
   }

   public int getMediumLE(int count) {
      return this.byteBuf.getMediumLE(count);
   }

   public ByteBuf skipBytes(int count) {
      return this.byteBuf.skipBytes(count);
   }

   public ByteBuffer nioBuffer(int count, int count2) {
      return this.byteBuf.nioBuffer(count, count2);
   }

   public ByteBuffer nioBuffer() {
      return this.byteBuf.nioBuffer();
   }

   public PacketBuffer(ByteBuf byteBuf2) {
      this.byteBuf = byteBuf2;
   }

   public boolean equals(Object other) {
      return this.byteBuf.equals(other);
   }

   public String toString() {
      return this.byteBuf.toString();
   }

   public String toString(int count, int count2, Charset charset) {
      return this.byteBuf.toString(count, count2, charset);
   }

   public String toString(Charset charset) {
      return this.byteBuf.toString(charset);
   }

   public int hashCode() {
      return this.byteBuf.hashCode();
   }

   public int compareTo(ByteBuf byteBuf2) {
      return this.byteBuf.compareTo(byteBuf2);
   }

   public int indexOf(int count, int count2, byte byteValue) {
      return this.byteBuf.indexOf(count, count2, byteValue);
   }

   public boolean getBoolean(int count) {
      return this.byteBuf.getBoolean(count);
   }

   public byte getByte(int count) {
      return this.byteBuf.getByte(count);
   }

   public short getShort(int count) {
      return this.byteBuf.getShort(count);
   }

   public char getChar(int count) {
      return this.byteBuf.getChar(count);
   }

   public int getInt(int count) {
      return this.byteBuf.getInt(count);
   }

   public long getLong(int count) {
      return this.byteBuf.getLong(count);
   }

   public float getFloat(int count) {
      return this.byteBuf.getFloat(count);
   }

   public double getDouble(int count) {
      return this.byteBuf.getDouble(count);
   }

   public ByteBuf clear() {
      return this.byteBuf.clear();
   }

   public ByteBuf getBytes(int count, ByteBuf byteBuf2, int count2, int count3) {
      return this.byteBuf.getBytes(count, byteBuf2, count2, count3);
   }

   public ByteBuf getBytes(int count, ByteBuf byteBuf2) {
      return this.byteBuf.getBytes(count, byteBuf2);
   }

   public ByteBuf getBytes(int count, byte[] byteValueArray) {
      return this.byteBuf.getBytes(count, byteValueArray);
   }

   public ByteBuf getBytes(int count, OutputStream outputStream, int count2) throws IOException {
      return this.byteBuf.getBytes(count, outputStream, count2);
   }

   public int getBytes(int count, FileChannel fileChannel, long value, int count2) throws IOException {
      return this.byteBuf.getBytes(count, fileChannel, value, count2);
   }

   public ByteBuf getBytes(int count, ByteBuffer buffer) {
      return this.byteBuf.getBytes(count, buffer);
   }

   public ByteBuf getBytes(int count, ByteBuf byteBuf2, int count2) {
      return this.byteBuf.getBytes(count, byteBuf2, count2);
   }

   public ByteBuf getBytes(int count, byte[] byteValueArray, int count2, int count3) {
      return this.byteBuf.getBytes(count, byteValueArray, count2, count3);
   }

   public int getBytes(int count, GatheringByteChannel gatheringByteChannel, int count2) throws IOException {
      return this.byteBuf.getBytes(count, gatheringByteChannel, count2);
   }

   public byte[] array() {
      return this.byteBuf.array();
   }

   public int arrayOffset() {
      return this.byteBuf.arrayOffset();
   }

   public boolean hasArray() {
      return this.byteBuf.hasArray();
   }

   public boolean isDirect() {
      return this.byteBuf.isDirect();
   }

   public int readInt() {
      return this.byteBuf.readInt();
   }

   public ByteBuf writeInt(int count) {
      return this.byteBuf.writeInt(count);
   }

   public ByteBuf copy(int count, int count2) {
      return this.byteBuf.copy(count, count2);
   }

   public ByteBuf copy() {
      return this.byteBuf.copy();
   }

   public ByteBuf setBoolean(int count, boolean flag) {
      return this.byteBuf.setBoolean(count, flag);
   }

   public ByteBuf setByte(int count, int count2) {
      return this.byteBuf.setByte(count, count2);
   }

   public ByteBuf setChar(int count, int count2) {
      return this.byteBuf.setChar(count, count2);
   }

   public ByteBuf setDouble(int count, double offset) {
      return this.byteBuf.setDouble(count, offset);
   }

   public ByteBuf setFloat(int count, float factor) {
      return this.byteBuf.setFloat(count, factor);
   }

   public ByteBuf setInt(int count, int count2) {
      return this.byteBuf.setInt(count, count2);
   }

   public ByteBuf setLong(int count, long value) {
      return this.byteBuf.setLong(count, value);
   }

   public ByteBuf setShort(int count, int count2) {
      return this.byteBuf.setShort(count, count2);
   }

   public ByteBuf unwrap() {
      return this.byteBuf.unwrap();
   }

   public ByteBuf capacity(int count) {
      return this.byteBuf.capacity(count);
   }

   public int capacity() {
      return this.byteBuf.capacity();
   }

   public char readChar() {
      return this.byteBuf.readChar();
   }

   public ByteBuf writeChar(int count) {
      return this.byteBuf.writeChar(count);
   }

   public ByteBuf writeBytes(byte[] byteValueArray, int count, int count2) {
      return this.byteBuf.writeBytes(byteValueArray, count, count2);
   }

   public ByteBuf writeBytes(ByteBuffer buffer) {
      return this.byteBuf.writeBytes(buffer);
   }

   public ByteBuf writeBytes(ByteBuf byteBuf2) {
      return this.byteBuf.writeBytes(byteBuf2);
   }

   public ByteBuf writeBytes(byte[] byteValueArray) {
      return this.byteBuf.writeBytes(byteValueArray);
   }

   public int writeBytes(ScatteringByteChannel scatteringByteChannel, int count) throws IOException {
      return this.byteBuf.writeBytes(scatteringByteChannel, count);
   }

   public ByteBuf writeBytes(ByteBuf byteBuf2, int count) {
      return this.byteBuf.writeBytes(byteBuf2, count);
   }

   public ByteBuf writeBytes(ByteBuf byteBuf2, int count, int count2) {
      return this.byteBuf.writeBytes(byteBuf2, count, count2);
   }

   public int writeBytes(FileChannel fileChannel, long value, int count) throws IOException {
      return this.byteBuf.writeBytes(fileChannel, value, count);
   }

   public int writeBytes(InputStream inputStream, int count) throws IOException {
      return this.byteBuf.writeBytes(inputStream, count);
   }

   public boolean isReadOnly() {
      return this.byteBuf.isReadOnly();
   }

   public ByteBuf readBytes(int count) {
      return this.byteBuf.readBytes(count);
   }

   public ByteBuf readBytes(ByteBuffer buffer) {
      return this.byteBuf.readBytes(buffer);
   }

   public ByteBuf readBytes(OutputStream outputStream, int count) throws IOException {
      return this.byteBuf.readBytes(outputStream, count);
   }

   public ByteBuf readBytes(byte[] byteValueArray) {
      return this.byteBuf.readBytes(byteValueArray);
   }

   public ByteBuf readBytes(ByteBuf byteBuf2) {
      return this.byteBuf.readBytes(byteBuf2);
   }

   public ByteBuf readBytes(byte[] byteValueArray, int count, int count2) {
      return this.byteBuf.readBytes(byteValueArray, count, count2);
   }

   public ByteBuf readBytes(ByteBuf byteBuf2, int count) {
      return this.byteBuf.readBytes(byteBuf2, count);
   }

   public int readBytes(GatheringByteChannel gatheringByteChannel, int count) throws IOException {
      return this.byteBuf.readBytes(gatheringByteChannel, count);
   }

   public int readBytes(FileChannel fileChannel, long value, int count) throws IOException {
      return this.byteBuf.readBytes(fileChannel, value, count);
   }

   public ByteBuf readBytes(ByteBuf byteBuf2, int count, int count2) {
      return this.byteBuf.readBytes(byteBuf2, count, count2);
   }

   public ByteBuf duplicate() {
      return this.byteBuf.duplicate();
   }

   public ByteBuf order(ByteOrder byteOrder) {
      return this.byteBuf.order(byteOrder);
   }

   public ByteOrder order() {
      return this.byteBuf.order();
   }

   public ByteBuf slice() {
      return this.byteBuf.slice();
   }

   public ByteBuf slice(int count, int count2) {
      return this.byteBuf.slice(count, count2);
   }

   public boolean release() {
      return this.byteBuf.release();
   }

   public boolean release(int count) {
      return this.byteBuf.release(count);
   }
}
