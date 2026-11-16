package client.util;

import client.util.RecordNode14;

/**
 * Self-contained AES (128/192/256) in GCM mode.
 * <p>
 * Output format of {@link #resolveByteArray(byte[], byte[], byte[])}: 12-byte nonce || ciphertext || 16-byte tag.
 */
public class AesCipherEngine {
    public final byte[] byteFlagArray;
    public final int[] countArray;
    public static final int count;
    public static final int[] countArray2;
    public final byte[] byteFlagArray2;
    public byte[] byteFlagArray3;
    public static final int[] countArray3;
    public static final int count2;
    public static final int count3;
    public static final int[][] countArray4;
    public byte[] byteFlagArray4;
    public static final int[] countArray5;
    public static final int count4;
    public static final int count5;
    public RecordNode14 recordNode14;
    public final int[] countArray6;
    public int[] countArray7;

    static {
        count3 = 12;
        count5 = 14;
        count = 10;
        count4 = 16;
        count2 = 16;
        countArray3 = new int[]{99, 124, 119, 123, 242, 107, 111, 197, 48, 1, 103, 43, 254, 215, 171, 118, 202, 130, 201, 125, 250, 89, 71, 240, 173, 212, 162, 175, 156, 164, 114, 192, 183, 253, 147, 38, 54, 63, 247, 204, 52, 165, 229, 241, 113, 216, 49, 21, 4, 199, 35, 195, 24, 150, 5, 154, 7, 18, 128, 226, 235, 39, 178, 117, 9, 131, 44, 26, 27, 110, 90, 160, 82, 59, 214, 179, 41, 227, 47, 132, 83, 209, 0, 237, 32, 252, 177, 91, 106, 203, 190, 57, 74, 76, 88, 207, 208, 239, 170, 251, 67, 77, 51, 133, 69, 249, 2, 127, 80, 60, 159, 168, 81, 163, 64, 143, 146, 157, 56, 245, 188, 182, 218, 33, 16, 255, 243, 210, 205, 12, 19, 236, 95, 151, 68, 23, 196, 167, 126, 61, 100, 93, 25, 115, 96, 129, 79, 220, 34, 42, 144, 136, 70, 238, 184, 20, 222, 94, 11, 219, 224, 50, 58, 10, 73, 6, 36, 92, 194, 211, 172, 98, 145, 149, 228, 121, 231, 200, 55, 109, 141, 213, 78, 169, 108, 86, 244, 234, 101, 122, 174, 8, 186, 120, 37, 46, 28, 166, 180, 198, 232, 221, 116, 31, 75, 189, 139, 138, 112, 62, 181, 102, 72, 3, 246, 14, 97, 53, 87, 185, 134, 193, 29, 158, 225, 248, 152, 17, 105, 217, 142, 148, 155, 30, 135, 233, 206, 85, 40, 223, 140, 161, 137, 13, 191, 230, 66, 104, 65, 153, 45, 15, 176, 84, 187, 22};
        countArray5 = new int[]{82, 9, 106, 213, 48, 54, 165, 56, 191, 64, 163, 158, 129, 243, 215, 251, 124, 227, 57, 130, 155, 47, 255, 135, 52, 142, 67, 68, 196, 222, 233, 203, 84, 123, 148, 50, 166, 194, 35, 61, 238, 76, 149, 11, 66, 250, 195, 78, 8, 46, 161, 102, 40, 217, 36, 178, 118, 91, 162, 73, 109, 139, 209, 37, 114, 248, 246, 100, 134, 104, 152, 22, 212, 164, 92, 204, 93, 101, 182, 146, 108, 112, 72, 80, 253, 237, 185, 218, 94, 21, 70, 87, 167, 141, 157, 132, 144, 216, 171, 0, 140, 188, 211, 10, 247, 228, 88, 5, 184, 179, 69, 6, 208, 44, 30, 143, 202, 63, 15, 2, 193, 175, 189, 3, 1, 19, 138, 107, 58, 145, 17, 65, 79, 103, 220, 234, 151, 242, 207, 206, 240, 180, 230, 115, 150, 172, 116, 34, 231, 173, 53, 133, 226, 249, 55, 232, 28, 117, 223, 110, 71, 241, 26, 113, 29, 41, 197, 137, 111, 183, 98, 14, 170, 24, 190, 27, 252, 86, 62, 75, 198, 210, 121, 32, 154, 219, 192, 254, 120, 205, 90, 244, 31, 221, 168, 51, 136, 7, 199, 49, 177, 18, 16, 89, 39, 128, 236, 95, 96, 81, 127, 169, 25, 181, 74, 13, 45, 229, 122, 159, 147, 201, 156, 239, 160, 224, 59, 77, 174, 42, 245, 176, 200, 235, 187, 60, 131, 83, 153, 97, 23, 43, 4, 126, 186, 119, 214, 38, 225, 105, 20, 99, 85, 33, 12, 125};
        countArray2 = new int[]{0, 1, 2, 4, 8, 16, 32, 64, 128, 27, 54};
        countArray4 = new int[16][256];
        for (int value = 0; value < 256; ++value) {
            AesCipherEngine.countArray4[2][value] = (value & 0x80) == 0 ? value << 1 & 0xFF : (value << 1 ^ 0x1B) & 0xFF;
            AesCipherEngine.countArray4[3][value] = countArray4[2][value] ^ value;
            AesCipherEngine.countArray4[9][value] = countArray4[2][countArray4[2][countArray4[2][value]]] ^ value;
            AesCipherEngine.countArray4[11][value] = countArray4[2][countArray4[2][value] ^ value] ^ value;
            // NOTE: (value ^ value) == 0 is preserved from the original implementation.
            AesCipherEngine.countArray4[13][value] = countArray4[2][countArray4[2][value ^ value]] ^ value;
            AesCipherEngine.countArray4[14][value] = countArray4[2][countArray4[2][value ^ value] ^ value];
        }
    }

    public AesCipherEngine() {
        this.countArray = new int[16];
        this.countArray6 = new int[16];
        this.byteFlagArray2 = new byte[16];
        this.byteFlagArray = new byte[16];
        this.byteFlagArray3 = null;
        this.countArray7 = null;
        this.byteFlagArray4 = null;
    }

    /** Encrypts {@code plaintext} with {@code key} and no additional authenticated data. */
    public byte[] resolveByteArray(byte[] plaintext, byte[] key) {
        return this.resolveByteArray(plaintext, key, null);
    }

    /** Decrypts {@code payload} with {@code key} and no additional authenticated data. */
    public byte[] resolveByteArrayByByteArray(byte[] payload, byte[] key) {
        return this.resolveByteArrayByByteArray(payload, key, null);
    }

    private static String resolveText(byte[] bytes, int offset, int length) {
        StringBuilder builder = new StringBuilder();
        for (int index = offset; index < offset + length; ++index) {
            builder.append(String.format("%02X", bytes[index]));
        }
        return builder.toString();
    }

    private static String resolveText(byte[] bytes) {
        return AesCipherEngine.resolveText(bytes, 0, bytes.length);
    }

    /**
     * AES-GCM encryption. Returns nonce (12 bytes) || ciphertext || tag (16 bytes).
     */
    public byte[] resolveByteArray(byte[] plaintext, byte[] key, byte[] associatedData) {
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalArgumentException("Invalid key size. Must be 16, 24, or 32 bytes");
        }
        // Nonce from a 48-bit LCG seeded with System.nanoTime().
        byte[] nonce = new byte[12];
        long seed = System.nanoTime();
        for (int index = 0; index < nonce.length; ++index) {
            seed = seed * 25214903917L + 11L & 0xFFFFFFFFFFFFL;
            nonce[index] = (byte)(seed >> 16 & 0xFFL);
        }
        int[] roundKeys = this.resolveIndexArray(key);
        byte[] hashKey = this.resolveByteArray(key, roundKeys);

        byte[] counter = new byte[16];
        System.arraycopy(nonce, 0, counter, 0, nonce.length);
        counter[15] = 1;
        byte[] ciphertext = new byte[plaintext.length];
        int[] keystream = new int[16];
        for (int offset = 0; offset < plaintext.length; offset += 16) {
            for (int index = 0; index < 16; ++index) {
                keystream[index] = counter[index] & 0xFF;
            }
            this.applyIndexArray(keystream, roundKeys);
            int blockLength = Math.min(16, plaintext.length - offset);
            for (int index = 0; index < blockLength; ++index) {
                ciphertext[offset + index] = (byte)(plaintext[offset + index] ^ (byte)keystream[index]);
            }
            for (int index = counter.length - 1; index >= 12; --index) {
                if (++counter[index] != 0) {
                    break;
                }
            }
        }

        byte[] tag = this.resolveByteArray(nonce, ciphertext, associatedData, roundKeys, hashKey);
        byte[] output = new byte[nonce.length + ciphertext.length + tag.length];
        System.arraycopy(nonce, 0, output, 0, nonce.length);
        System.arraycopy(ciphertext, 0, output, nonce.length, ciphertext.length);
        System.arraycopy(tag, 0, output, nonce.length + ciphertext.length, tag.length);
        return output;
    }

    /**
     * AES-GCM decryption of nonce (12 bytes) || ciphertext || tag (16 bytes).
     *
     * @throws SecurityException if the authentication tag does not match
     */
    public byte[] resolveByteArrayByByteArray(byte[] payload, byte[] key, byte[] associatedData) {
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalArgumentException("Invalid key size. Must be 16, 24, or 32 bytes");
        }
        if (payload.length < 28) {
            throw new IllegalArgumentException("Data too short for GCM mode");
        }
        byte[] nonce = new byte[12];
        System.arraycopy(payload, 0, nonce, 0, nonce.length);
        int ciphertextLength = payload.length - nonce.length - 16;
        byte[] ciphertext = new byte[ciphertextLength];
        System.arraycopy(payload, nonce.length, ciphertext, 0, ciphertextLength);
        byte[] expectedTag = new byte[16];
        System.arraycopy(payload, nonce.length + ciphertextLength, expectedTag, 0, 16);

        int[] roundKeys = this.resolveIndexArray(key);
        byte[] hashKey = this.resolveByteArray(key, roundKeys);
        byte[] actualTag = this.resolveByteArray(nonce, ciphertext, associatedData, roundKeys, hashKey);
        int difference = 0;
        for (int index = 0; index < 16; ++index) {
            difference |= expectedTag[index] ^ actualTag[index];
        }
        if (difference != 0) {
            throw new SecurityException("GCM authentication failed");
        }

        byte[] counter = new byte[16];
        System.arraycopy(nonce, 0, counter, 0, nonce.length);
        counter[15] = 1;
        byte[] plaintext = new byte[ciphertextLength];
        int[] keystream = new int[16];
        for (int offset = 0; offset < ciphertext.length; offset += 16) {
            for (int index = 0; index < 16; ++index) {
                keystream[index] = counter[index] & 0xFF;
            }
            this.applyIndexArray(keystream, roundKeys);
            int blockLength = Math.min(16, ciphertext.length - offset);
            for (int index = 0; index < blockLength; ++index) {
                plaintext[offset + index] = (byte)(ciphertext[offset + index] ^ (byte)keystream[index]);
            }
            for (int index = counter.length - 1; index >= 12; --index) {
                if (++counter[index] != 0) {
                    break;
                }
            }
        }
        return plaintext;
    }

    /** Encrypts one AES block (state as 16 ints in 0..255) in place with the expanded round keys. */
    private void applyIndexArray(int[] state, int[] roundKeys) {
        int rounds = roundKeys.length / 16 - 1;
        for (int index = 0; index < 16; ++index) {
            state[index] ^= roundKeys[index];
        }
        for (int round = 1; round < rounds; ++round) {
            for (int index = 0; index < 16; ++index) {
                state[index] = countArray3[state[index]];
            }
            // ShiftRows
            int swap = state[1];
            state[1] = state[5];
            state[5] = state[9];
            state[9] = state[13];
            state[13] = swap;
            swap = state[2];
            int swap2 = state[6];
            state[2] = state[10];
            state[6] = state[14];
            state[10] = swap;
            state[14] = swap2;
            swap = state[15];
            state[15] = state[11];
            state[11] = state[7];
            state[7] = state[3];
            state[3] = swap;
            for (int column = 0; column < 4; ++column) {
                int a0 = state[column * 4];
                int a1 = state[column * 4 + 1];
                int a2 = state[column * 4 + 2];
                int a3 = state[column * 4 + 3];
                state[column * 4] = countArray4[2][a0] ^ countArray4[3][a1] ^ a2 ^ a3;
                state[column * 4 + 1] = a0 ^ countArray4[2][a1] ^ countArray4[3][a2] ^ a3;
                state[column * 4 + 2] = a0 ^ a1 ^ countArray4[2][a2] ^ countArray4[3][a3];
                state[column * 4 + 3] = countArray4[3][a0] ^ a1 ^ a2 ^ countArray4[2][a3];
            }
            int roundOffset = round * 16;
            for (int index = 0; index < 16; ++index) {
                state[index] ^= roundKeys[roundOffset + index];
            }
        }
        for (int index = 0; index < 16; ++index) {
            state[index] = countArray3[state[index]];
        }
        // ShiftRows
        int swap = state[1];
        state[1] = state[5];
        state[5] = state[9];
        state[9] = state[13];
        state[13] = swap;
        swap = state[2];
        int swap2 = state[6];
        state[2] = state[10];
        state[6] = state[14];
        state[10] = swap;
        state[14] = swap2;
        swap = state[15];
        state[15] = state[11];
        state[11] = state[7];
        state[7] = state[3];
        state[3] = swap;
        int lastOffset = rounds * 16;
        for (int index = 0; index < 16; ++index) {
            state[index] ^= roundKeys[lastOffset + index];
        }
    }

    /** GHASH multiply-by-H of {@code block} in place, using the precomputed tables. */
    private void applyByteArray(byte[] block) {
        long high = 0L;
        long low = 0L;
        for (int index = 0; index < 16; ++index) {
            int value = block[index] & 0xFF;
            if (value != 0) {
                long entry = this.recordNode14.timestampArray[index][value];
                high ^= entry >>> 32;
                low ^= entry & 0xFFFFFFFFL;
            }
        }
        for (int index = 0; index < 8; ++index) {
            block[index] = (byte)(high >>> 56 - index * 8 & 0xFFL);
            block[index + 8] = (byte)(low >>> 56 - index * 8 & 0xFFL);
        }
    }

    /** Computes the GCM authentication tag. Leaves E(K, J0) in {@code byteFlagArray}. */
    private byte[] resolveByteArray(byte[] nonce, byte[] ciphertext, byte[] associatedData, int[] roundKeys, byte[] hashKey) {
        int associatedLength = associatedData != null ? associatedData.length : 0;
        if (this.recordNode14 == null || !this.recordNode14.matchesByteArray(hashKey)) {
            this.recordNode14 = new RecordNode14(hashKey);
        }
        byte[] hash = new byte[16];
        if (associatedData != null && associatedLength > 0) {
            int blockCount = (associatedLength + 16 - 1) / 16;
            for (int block = 0; block < blockCount; ++block) {
                int blockLength = Math.min(16, associatedLength - block * 16);
                for (int index = 0; index < blockLength; ++index) {
                    hash[index] = (byte)(hash[index] ^ associatedData[block * 16 + index]);
                }
                this.applyByteArray(hash);
            }
        }
        if (ciphertext.length > 0) {
            int blockCount = (ciphertext.length + 16 - 1) / 16;
            for (int block = 0; block < blockCount; ++block) {
                int blockLength = Math.min(16, ciphertext.length - block * 16);
                for (int index = 0; index < blockLength; ++index) {
                    hash[index] = (byte)(hash[index] ^ ciphertext[block * 16 + index]);
                }
                this.applyByteArray(hash);
            }
        }
        byte[] lengthBlock = new byte[16];
        long associatedBits = (long)associatedLength * 8L;
        long ciphertextBits = (long)ciphertext.length * 8L;
        for (int index = 7; index >= 0; --index) {
            lengthBlock[index] = (byte)(associatedBits & 0xFFL);
            associatedBits >>>= 8;
            lengthBlock[index + 8] = (byte)(ciphertextBits & 0xFFL);
            ciphertextBits >>>= 8;
        }
        for (int index = 0; index < 16; ++index) {
            hash[index] = (byte)(hash[index] ^ lengthBlock[index]);
        }
        this.applyByteArray(hash);

        byte[] initialCounter = new byte[16];
        System.arraycopy(nonce, 0, initialCounter, 0, nonce.length);
        initialCounter[15] = 1;
        for (int index = 0; index < 16; ++index) {
            this.countArray6[index] = initialCounter[index] & 0xFF;
        }
        this.applyIndexArray(this.countArray6, roundKeys);
        for (int index = 0; index < 16; ++index) {
            this.byteFlagArray[index] = (byte)this.countArray6[index];
        }
        byte[] tag = new byte[16];
        for (int index = 0; index < 16; ++index) {
            tag[index] = (byte)(hash[index] ^ this.byteFlagArray[index]);
        }
        return tag;
    }

    /**
     * Computes the GHASH key H = E(K, 0^128). Cached in {@code byteFlagArray4} while {@code key}
     * matches {@code byteFlagArray3}.
     */
    private byte[] resolveByteArray(byte[] key, int[] roundKeys) {
        if (this.byteFlagArray3 != null && this.byteFlagArray4 != null) {
            boolean sameKey = true;
            for (int index = 0; index < key.length; ++index) {
                if (key[index] != this.byteFlagArray3[index]) {
                    sameKey = false;
                    break;
                }
            }
            if (sameKey) {
                return this.byteFlagArray4;
            }
        }
        for (int index = 0; index < 16; ++index) {
            this.countArray[index] = 0;
        }
        this.applyIndexArray(this.countArray, roundKeys);
        for (int index = 0; index < 16; ++index) {
            this.byteFlagArray2[index] = (byte)this.countArray[index];
        }
        if (this.byteFlagArray4 == null) {
            this.byteFlagArray4 = new byte[16];
        }
        System.arraycopy(this.byteFlagArray2, 0, this.byteFlagArray4, 0, 16);
        return this.byteFlagArray2;
    }

    /**
     * AES key expansion. Returns round keys as bytes (0..255) in an int array; cached in
     * {@code countArray7} while {@code key} matches {@code byteFlagArray3}.
     */
    private int[] resolveIndexArray(byte[] key) {
        if (this.byteFlagArray3 != null && key.length == this.byteFlagArray3.length) {
            boolean sameKey = true;
            for (int index = 0; index < key.length; ++index) {
                if (key[index] != this.byteFlagArray3[index]) {
                    sameKey = false;
                    break;
                }
            }
            if (sameKey && this.countArray7 != null) {
                return this.countArray7;
            }
        }
        int rounds = key.length == 16 ? 10 : (key.length == 24 ? 12 : 14);
        int keyWords = key.length / 4;
        int totalWords = 4 * (rounds + 1);
        int[] words = new int[totalWords];
        for (int index = 0; index < keyWords; ++index) {
            words[index] = (key[4 * index] & 0xFF) << 24 | (key[4 * index + 1] & 0xFF) << 16
                    | (key[4 * index + 2] & 0xFF) << 8 | key[4 * index + 3] & 0xFF;
        }
        for (int index = keyWords; index < totalWords; ++index) {
            int temp = words[index - 1];
            if (index % keyWords == 0) {
                temp = temp << 8 & 0xFFFFFF00 | temp >>> 24 & 0xFF;
                temp = countArray3[temp >>> 24 & 0xFF] << 24 | countArray3[temp >>> 16 & 0xFF] << 16
                        | countArray3[temp >>> 8 & 0xFF] << 8 | countArray3[temp & 0xFF];
                temp ^= countArray2[index / keyWords] << 24;
            } else if (keyWords > 6 && index % keyWords == 4) {
                temp = countArray3[temp >>> 24 & 0xFF] << 24 | countArray3[temp >>> 16 & 0xFF] << 16
                        | countArray3[temp >>> 8 & 0xFF] << 8 | countArray3[temp & 0xFF];
            }
            words[index] = words[index - keyWords] ^ temp;
        }
        int[] roundKeys = new int[totalWords * 4];
        for (int index = 0; index < totalWords; ++index) {
            roundKeys[4 * index] = words[index] >>> 24 & 0xFF;
            roundKeys[4 * index + 1] = words[index] >>> 16 & 0xFF;
            roundKeys[4 * index + 2] = words[index] >>> 8 & 0xFF;
            roundKeys[4 * index + 3] = words[index] & 0xFF;
        }
        this.byteFlagArray3 = new byte[key.length];
        System.arraycopy(key, 0, this.byteFlagArray3, 0, key.length);
        this.countArray7 = roundKeys;
        return roundKeys;
    }
}
