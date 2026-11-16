package client.util;

import java.security.InvalidKeyException;
import java.security.ProviderException;
import java.util.Arrays;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

public final class CoreByteArrayEntry {
    public byte[] byteFlagArray4;
    public final int[] countArray10;
    public final int[] countArray7;
    public final int[] countArray16;
    public int[][] countArray2;
    public final int[] countArray8;
    public int[] countArray9;
    public int count;
    public boolean enabled2;
    public int[] countArray6;
    public final int[] countArray11;
    public int[] countArray12;
    public final byte[] byteFlagArray5;
    public final int[] countArray13;
    public final int[] countArray4;
    public final byte[] byteFlagArray;
    public final byte[] byteFlagArray2;
    public final int[] countArray14;
    public final int[] countArray15;
    public final int[] countArray;
    public final int[] countArray5;
    public boolean enabled;
    public byte[] byteFlagArray3;
    public final int[] countArray3;

    public int computeCount(int n) {
        return this.getCount2() - n % this.getCount2();
    }

    public void applyByteArray(byte[] byArray, int n, byte[] byArray2, int n2) {
        this.applyByteArray3(byArray, n, byArray2, n2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public int computeCount(byte[] byArray, int n, int n2) {
        int n3 = 0;
        byte by = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        if (byArray == null) return 0;
        if (n2 == 0) return 0;
        n3 = Math.addExact(n, n2);
        by = byArray[n3 - 1];
        n4 = by & 0xFF;
        if (n4 < 1) {
            return -1;
        }
        if (n4 > this.getCount2()) return -1;
        n5 = n3 - n4;
        if (n5 < n) return -1;
        n6 = n5;
        while (n6 < n3) {
            if (byArray[n6] != by) return -1;
            ++n6;
        }
        return n5;
    }

    /*
     * Enabled aggressive block sorting
     */
    private byte[] resolveByteArray(byte[] byArray, int n, int n2, boolean bl) throws IllegalBlockSizeException, ShortBufferException {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        byte[] byArray2 = null;
        n3 = this.getCount2();
        int cfr_ignored_0 = n2 % n3;
        n4 = Math.addExact(0, n2);
        n5 = n4;
        n6 = 0;
        int n7 = n5;
        n6 = this.computeCount(n7);
        if (bl && n6 > 0) {
            if (n6 != n3) throw new IllegalBlockSizeException("Input length must be multiple of " + n3 + " when decrypting with padded cipher");
        }
        if (bl) {
            return byArray;
        }
        byArray2 = new byte[Math.addExact(n4, n6)];
        if (n2 != 0) {
            System.arraycopy(byArray, n, byArray2, 0, n2);
        }
        if (n6 != 0) {
            int n8 = n6;
            int n9 = Math.addExact(0, n2);
            byte[] byArray3 = byArray2;
            this.applyByteArray(byArray3, n9, n8);
            return byArray2;
        }
        return byArray2;
    }

    public int getCount2() {
        return 16;
    }

    public void applyByteArray(boolean bl, byte[] byArray) {
        try {
            byte[] byArray2 = byArray;
            this.applyByteArray(byArray2);
            this.countArray12 = this.countArray2[bl ? 1 : 0];
        }
        catch (InvalidKeyException invalidKeyException) {}
    }

    public void applyIndex(int n, int n2) {
        long l = 0x20ec172c48aed9a9L;
        while ((int)(l % 3L) == 1) {
            if (n % n2 != 0) {
                l = (l ^ 0xCAAD554E02F2424CL) + 0x5b0eb8888ef14591L;
                continue;
            }
            return;
        }
        throw new ProviderException("Internal error in input buffering");
    }

    /*
     * Enabled aggressive block sorting
     */
    public byte[] resolveByteArrayByByteArray(byte[] byArray, int n, int n2) {
        long l = 1297686599L;
        int n3 = -17418;
        long l2 = (l << 16 | (long)n3 << 48 >>> 48) ^ 0x76D45F8373F7L;
        int n4 = (int)((l2 ^ 0x64D07484580DL) << 16 >>> 48);
        long l3 = l2 ^ 0x6EED6A3CC775L;
        byte[] byArray2 = null;
        int n5 = 0;
        if (n2 <= 0) return null;
        byArray2 = new byte[n2];
        int n6 = this.getCount2();
        int n7 = n2;
        this.applyIndex(n7, n6);
        int n8 = 0;
        byte[] byArray3 = byArray2;
        int n9 = n2;
        int n10 = n;
        byte[] byArray4 = byArray;
        this.computeCountByByteArray(byArray4, n10, n9, byArray3, n8);
        int n11 = byArray.length;
        int n12 = 0;
        byte[] byArray5 = byArray2;
        n5 = this.computeCount(byArray5, n12, n11);
        return Arrays.copyOf(byArray2, n5);
    }

    private int computeCountByIndex(int n) {
        return (n >> 2) + 6;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void applyByteArray2(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3;
        int n4 = n;
        int n5 = n2;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        long l = 0x31349e0078d4729L;
        long l2 = 0x6a93ffdf5ddcdbd1L;
        while ((n3 = (int)(l % 34L)) != 7) {
            block34: {
                block15: {
                    block12: {
                        block24: {
                            block11: {
                                block9: {
                                    block21: {
                                        block19: {
                                            block30: {
                                                block28: {
                                                    block26: {
                                                        block29: {
                                                            block14: {
                                                                block17: {
                                                                    block25: {
                                                                        block6: {
                                                                            block31: {
                                                                                block13: {
                                                                                    block32: {
                                                                                        block5: {
                                                                                            block27: {
                                                                                                block4: {
                                                                                                    block20: {
                                                                                                        block18: {
                                                                                                            block8: {
                                                                                                                block23: {
                                                                                                                    block7: {
                                                                                                                        block22: {
                                                                                                                            block10: {
                                                                                                                                block33: {
                                                                                                                                    block16: {
                                                                                                                                        if (n3 == 16) break block4;
                                                                                                                                        if (n3 == 28) break block5;
                                                                                                                                        if (n3 == 27) break block6;
                                                                                                                                        if (n3 == 21) {
                                                                                                                                            byArray2[n5] = (byte)(this.byteFlagArray2[n9 & 0xFF] ^ n11);
                                                                                                                                            return;
                                                                                                                                        }
                                                                                                                                        if (n3 == 10) break block7;
                                                                                                                                        if (n3 == 30) break block8;
                                                                                                                                        if (n3 == 31) break block9;
                                                                                                                                        if (n3 == 19) break block10;
                                                                                                                                        if (n3 == 17) break block11;
                                                                                                                                        if (n3 == 32) break block12;
                                                                                                                                        if (n3 == 0) break block13;
                                                                                                                                        if (n3 == 6) break block14;
                                                                                                                                        if (n3 == 22) break block15;
                                                                                                                                        if (n3 == 25) break block16;
                                                                                                                                        if (n3 == 15) break block17;
                                                                                                                                        if (n3 == 29) break block18;
                                                                                                                                        if (n3 == 20) break block19;
                                                                                                                                        if (n3 == 26) break block20;
                                                                                                                                        if (n3 == 18) break block21;
                                                                                                                                        if (n3 == 14) break block22;
                                                                                                                                        if (n3 == 1) break block23;
                                                                                                                                        if (n3 == 11) break block24;
                                                                                                                                        if (n3 == 13) break block25;
                                                                                                                                        if (n3 == 33) break block26;
                                                                                                                                        if (n3 == 8) break block27;
                                                                                                                                        if (n3 == 3) break block28;
                                                                                                                                        if (n3 == 12) break block29;
                                                                                                                                        if (n3 == 5) break block30;
                                                                                                                                        if (n3 == 24) break block31;
                                                                                                                                        if (n3 == 2) break block32;
                                                                                                                                        if (n3 == 23) break block33;
                                                                                                                                        if (n3 != 9) {
                                                                                                                                            byArray2[n5++] = (byte)(this.byteFlagArray2[n8 >>> 8 & 0xFF] ^ n11 >>> 8);
                                                                                                                                            l = (l ^ 0x6A02D047CB3FF21DL) - 0x3b32fcf7fe12e136L;
                                                                                                                                            l2 = (l2 ^ 0xB3296C602765E2C1L) - 0xf607ad55e8d2f00L;
                                                                                                                                            continue;
                                                                                                                                        }
                                                                                                                                        break block34;
                                                                                                                                    }
                                                                                                                                    byArray2[n5++] = (byte)(this.byteFlagArray2[n7 >>> 16 & 0xFF] ^ n11 >>> 16);
                                                                                                                                    l = (l ^ 0xB1FD9A84EC9160E1L) + 0x6ce1b93b9e239c58L;
                                                                                                                                    l2 = (l2 ^ 0xBBF02228AB8AFEC0L) - 0x1fe677d61090b8bL;
                                                                                                                                    continue;
                                                                                                                                }
                                                                                                                                byArray2[n5++] = (byte)(this.byteFlagArray2[n8 >>> 16 & 0xFF] ^ n11 >>> 16);
                                                                                                                                l = (l ^ 0x77B5DD360E1D4B91L) - 0x30b9c5fac3d42e3L;
                                                                                                                                l2 = (l2 ^ 0xAC5605E715F78A1CL) - 0x23f6169b06bacbaL;
                                                                                                                                continue;
                                                                                                                            }
                                                                                                                            n10 = this.countArray16[n10 >>> 24] ^ this.countArray8[n7 >>> 16 & 0xFF] ^ this.countArray14[n8 >>> 8 & 0xFF] ^ this.countArray3[n9 & 0xFF] ^ this.countArray12[n6++];
                                                                                                                            l = (l ^ 0xF05B03128EA6EF06L) - 0x4b5dc49c8ac53a17L;
                                                                                                                            l2 = (l2 ^ 0xDD71B4C84D876A63L) - 0x40ec98a1a1962bfdL;
                                                                                                                            continue;
                                                                                                                        }
                                                                                                                        byArray2[n5++] = (byte)(this.byteFlagArray2[n7 >>> 8 & 0xFF] ^ n11 >>> 8);
                                                                                                                        l = (l ^ 0xF1A3A481B08F0DD7L) - 0x3128d9e047c7f15aL;
                                                                                                                        l2 = (l2 ^ 0xB98C0491C35DA76EL) + 0xda499bcb846b32dL;
                                                                                                                        continue;
                                                                                                                    }
                                                                                                                    n9 = (byArray[n4++] << 24 | (byArray[n4++] & 0xFF) << 16 | (byArray[n4++] & 0xFF) << 8 | byArray[n4++] & 0xFF) ^ this.countArray12[n6++];
                                                                                                                    l = (l ^ 0xAA2BE3B8AF5E81C6L) - 0x6e73817c29ffed97L;
                                                                                                                    l2 = (l2 ^ 0x50CC8057F1DE94EEL) - 0x2da5b15c54f27c48L;
                                                                                                                    continue;
                                                                                                                }
                                                                                                                n6 = 0;
                                                                                                                l = (l ^ 0xA80AC2717F5DDEFCL) - 0x58fc6c6e986429bfL;
                                                                                                                l2 = (l2 ^ 0x82AF1E112FF30BEAL) + 0x4c6e3adbe98c0e9fL;
                                                                                                                continue;
                                                                                                            }
                                                                                                            n7 = (byArray[n4++] << 24 | (byArray[n4++] & 0xFF) << 16 | (byArray[n4++] & 0xFF) << 8 | byArray[n4++] & 0xFF) ^ this.countArray12[n6++];
                                                                                                            l = (l ^ 0xA7C2CEE13EA9F074L) + 0x4f18d7c33670c834L;
                                                                                                            l2 = (l2 ^ 0x6F6707C3322F2636L) - 0x20ae0e3efd950a12L;
                                                                                                            continue;
                                                                                                        }
                                                                                                        l = (l ^ 0x483A361813FA67C7L) - 0x140bea9a0ee00196L;
                                                                                                        l2 = (l2 ^ 0x84087BA6F511DC68L) - 0x4f085078a8e3764L;
                                                                                                        break block15;
                                                                                                    }
                                                                                                    n7 = n11;
                                                                                                    l = (l ^ 0x3046804FB4710BEEL) + 0x6585198316bb4d6L;
                                                                                                    l2 = (l2 ^ 0xA08CCE4CDB4F0CEBL) - 0x47c8295225f8ecf5L;
                                                                                                    continue;
                                                                                                }
                                                                                                n8 = (byArray[n4++] << 24 | (byArray[n4++] & 0xFF) << 16 | (byArray[n4++] & 0xFF) << 8 | byArray[n4++] & 0xFF) ^ this.countArray12[n6++];
                                                                                                l = (l ^ 0x2E9DFD3CF13DF3E3L) - 0x5c64c0778acf7bc1L;
                                                                                                l2 = (l2 ^ 0xA4CB905B8834D040L) - 0x61f0ad80ee9fe937L;
                                                                                                continue;
                                                                                            }
                                                                                            n12 = this.countArray16[n8 >>> 24] ^ this.countArray8[n9 >>> 16 & 0xFF] ^ this.countArray14[n10 >>> 8 & 0xFF] ^ this.countArray3[n7 & 0xFF] ^ this.countArray12[n6++];
                                                                                            l = (l ^ 0x8AFD65037B1BBA23L) - 0x650907075195a594L;
                                                                                            l2 = (l2 ^ 0xCA28805D971411D9L) - 0x27b8f10375a30f5cL;
                                                                                            continue;
                                                                                        }
                                                                                        n8 = n12;
                                                                                        l = (l ^ 0x9BC3F1D257A79CB3L) - 0x7d7e0d110a0ed59fL;
                                                                                        l2 = (l2 ^ 0xD5B2FEC3D8557B82L) + 0x31325be2c63b738bL;
                                                                                        continue;
                                                                                    }
                                                                                    n11 = this.countArray12[n6++];
                                                                                    l = (l ^ 0x278F40B27A872353L) + 0x36b4dacccf0fc116L;
                                                                                    l2 = (l2 ^ 0xF875144FA6077EEAL) - 0x2af827ff55db3dceL;
                                                                                    continue;
                                                                                }
                                                                                byArray2[n5++] = (byte)(this.byteFlagArray2[n9 >>> 16 & 0xFF] ^ n11 >>> 16);
                                                                                l = (l ^ 0x3D5FDDD914AE9F14L) - 0x2ed846767573d0e6L;
                                                                                l2 = (l2 ^ 0x79FBD77E6A14D0C3L) + 0x19fe81f18a98d790L;
                                                                                continue;
                                                                            }
                                                                            byArray2[n5++] = (byte)(this.byteFlagArray2[n10 >>> 16 & 0xFF] ^ n11 >>> 16);
                                                                            l = (l ^ 0xE5D55A3F1376313CL) - 0x249748d52f0e01c0L;
                                                                            l2 = (l2 ^ 0xB74C7497AE1B7C6L) + 0x4b60a6f8d0c92691L;
                                                                            continue;
                                                                        }
                                                                        byArray2[n5++] = (byte)(this.byteFlagArray2[n8 >>> 24] ^ n11 >>> 24);
                                                                        l = (l ^ 0x99595AB625C799D2L) - 0x7bd10c180fd906f3L;
                                                                        l2 = (l2 ^ 0x2D3155427C16C3CBL) - 0x5f3404de55778c4cL;
                                                                        continue;
                                                                    }
                                                                    n10 = (byArray[n4++] << 24 | (byArray[n4++] & 0xFF) << 16 | (byArray[n4++] & 0xFF) << 8 | byArray[n4++] & 0xFF) ^ this.countArray12[n6++];
                                                                    l = (l ^ 0x7025087F74377497L) - 0x17ee6db9b32f7eecL;
                                                                    l2 = (l2 ^ 0x69E752F3A9E3E635L) - 0x35f7340179f88f1dL;
                                                                    continue;
                                                                }
                                                                n11 = this.countArray12[n6++];
                                                                l = (l ^ 0xAAC28BCF164750ABL) - 0x792819a9a854a15L;
                                                                l2 = (l2 ^ 0xA99F27E1521F5D32L) + 0x102c92b29c7dbe6L;
                                                                continue;
                                                            }
                                                            byArray2[n5++] = (byte)(this.byteFlagArray2[n10 >>> 8 & 0xFF] ^ n11 >>> 8);
                                                            l = (l ^ 0x25397C25815E1052L) + 0x6ddb3c323b0f2c56L;
                                                            l2 = (l2 ^ 0xFF0425330C27C546L) + 0x2001bd7e1e46139fL;
                                                            continue;
                                                        }
                                                        byArray2[n5++] = (byte)(this.byteFlagArray2[n7 & 0xFF] ^ n11);
                                                        l = (l ^ 0x1F6BC7D90E1DB320L) - 0x19154669d1ea9d20L;
                                                        l2 = (l2 ^ 0xF6753E7123E4223DL) - 0x5a4b255dfedade43L;
                                                        continue;
                                                    }
                                                    byArray2[n5++] = (byte)(this.byteFlagArray2[n8 & 0xFF] ^ n11);
                                                    l = (l ^ 0x8D22EE84F90760B8L) + 0x4bb1a2a321bb162dL;
                                                    l2 = (l2 ^ 0x4BF8C9CE4BCCB428L) + 0x2ebcf86ee1bdb638L;
                                                    continue;
                                                }
                                                byArray2[n5++] = (byte)(this.byteFlagArray2[n9 >>> 8 & 0xFF] ^ n11 >>> 8);
                                                l = (l ^ 0x5CDA8B2D170EFF11L) - 0x298008f92e5204fbL;
                                                l2 = (l2 ^ 0x7552F7991650AD0FL) + 0x4300c729cc0cbe48L;
                                                continue;
                                            }
                                            n13 = this.countArray16[n9 >>> 24] ^ this.countArray8[n10 >>> 16 & 0xFF] ^ this.countArray14[n7 >>> 8 & 0xFF] ^ this.countArray3[n8 & 0xFF] ^ this.countArray12[n6++];
                                            l = (l ^ 0xE2621B75D92F985AL) - 0x5da083270be16dc8L;
                                            l2 = (l2 ^ 0x70193BD13357A412L) - 0x493276c10c3e9f5eL;
                                            continue;
                                        }
                                        n11 = this.countArray12[n6++];
                                        l = (l ^ 0xED029ECD35877760L) - 0x782bcf0740e36dd1L;
                                        l2 = (l2 ^ 0x56287B5FD0F885DL) + 0x3acb8b4efca8222L;
                                        continue;
                                    }
                                    byArray2[n5++] = (byte)(this.byteFlagArray2[n7 >>> 24] ^ n11 >>> 24);
                                    l = (l ^ 0xFD58A94948A7FAAEL) - 0x30a24ad881309c73L;
                                    l2 = (l2 ^ 0xCD765AE35BDF68B2L) + 0x13ae517b915a1888L;
                                    continue;
                                }
                                byArray2[n5++] = (byte)(this.byteFlagArray2[n9 >>> 24] ^ n11 >>> 24);
                                l = (l ^ 0x2F43512CA46A459AL) + 0x736529775200ffd1L;
                                l2 = (l2 ^ 0xDD55F93CB72BE9A0L) - 0x4177d560bf7560bbL;
                                continue;
                            }
                            n11 = this.countArray16[n7 >>> 24] ^ this.countArray8[n8 >>> 16 & 0xFF] ^ this.countArray14[n9 >>> 8 & 0xFF] ^ this.countArray3[n10 & 0xFF] ^ this.countArray12[n6++];
                            l = (l ^ 0xC277C51F1EA4F994L) + 0x6c122c726973a8f9L;
                            l2 = (l2 ^ 0xA3952FF166C98C0CL) - 0x4e245312102e63f4L;
                            continue;
                        }
                        byArray2[n5++] = (byte)(this.byteFlagArray2[n10 & 0xFF] ^ n11);
                        l = (l ^ 0xFAC4EB424431CAC0L) - 0x7d10f501c0d03adaL;
                        l2 = (l2 ^ 0x3D1CEBF70BD4B618L) + 0x30e83b4240a58d36L;
                        continue;
                    }
                    n9 = n13;
                    l = (l ^ 0xDD9630D517F129DL) - 0x6ef9b4bd12ed2914L;
                    l2 = (l2 ^ 0x9DF483478C331EB6L) + 0x32a68742751b4897L;
                    continue;
                }
                if (n6 >= this.count) {
                    long l3 = (l ^ 0x1EB0136C325AD9D3L) - 0x298a34322f80bb82L;
                    long l4 = (l2 ^ 0xAF308AA64D64DC5CL) + 0x11f914080cdacfd0L;
                    n11 = this.countArray12[n6++];
                    l = (l3 ^ 0xDAE8FD061294BEA7L) - 0x5ff90e7c0a565042L;
                    l2 = (l4 ^ 0xE682FC0861DCBDB9L) - 0x67fd5cd9cd7b2999L;
                    continue;
                }
                l = (l ^ 0x9F2AA7A069D8AD7EL) - 0x57fab471607fb96fL;
                l2 = (l2 ^ 0x18E180015F586768L) + 0x2954ba10dab41f95L;
                continue;
            }
            byArray2[n5++] = (byte)(this.byteFlagArray2[n10 >>> 24] ^ n11 >>> 24);
            l = (l ^ 0x97D2DD82A0377E1L) - 0x20dbd68369008171L;
            l2 = (l2 ^ 0x6406066A4A91A1C4L) - 0x3f0320d06ed9fc84L;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private int computeCount(int n, byte[] byArray) {
        int n2 = n;
        if (n2 == 0) {
            return 0;
        }
        n2 = this.countArray6[n2 & 0xFF];
        int n3 = byArray[0] != 0 ? this.countArray9[(n2 + this.countArray6[byArray[0] & 0xFF]) % 255] & 0xFF : 0;
        int n4 = byArray[1] == 0 ? 0 : this.countArray9[(n2 + this.countArray6[byArray[1] & 0xFF]) % 255] & 0xFF;
        int n5 = byArray[2] == 0 ? 0 : this.countArray9[(n2 + this.countArray6[byArray[2] & 0xFF]) % 255] & 0xFF;
        int n6 = byArray[3] == 0 ? 0 : this.countArray9[(n2 + this.countArray6[byArray[3] & 0xFF]) % 255] & 0xFF;
        return n3 << 24 | n4 << 16 | n5 << 8 | n6;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int computeCountByByteArray(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        int n4 = n;
        int n5 = n3;
        int n6 = 0;
        int n7 = 0;
        long l = 0x7bd266345a192aeeL;
        long l2 = 0x2e795cdb9696411bL;
        while (true) {
            block7: {
                block8: {
                    block9: {
                        block11: {
                            block5: {
                                block4: {
                                    block6: {
                                        block12: {
                                            block10: {
                                                int n8;
                                                if ((n8 = (int)(l % 10L)) == 0) break block4;
                                                if (n8 == 5) break block5;
                                                if (n8 == 9) break block6;
                                                if (n8 == 4) break block7;
                                                if (n8 == 7) break block8;
                                                if (n8 == 1) break block9;
                                                if (n8 == 6) break block10;
                                                if (n8 == 2) break block11;
                                                if (n8 != 3) {
                                                    n7 = 0;
                                                    l = (l ^ 0x69201D1A096FDA0L) + 0x30287f84457ba3d0L;
                                                    l2 = (l2 ^ 0x21D02B6E785BD7F6L) - 0x5bd50f49ca2405bL;
                                                    continue;
                                                }
                                                break block12;
                                            }
                                            byArray2[n7 + n5] = (byte)(this.byteFlagArray3[n7] ^ this.byteFlagArray4[n7]);
                                            l = (l ^ 0x82CBC8F48D9D9304L) + 0x72538f63e2ecb4f7L;
                                            l2 = (l2 ^ 0xFF5439F545F10D1BL) + 0xdd98d8a19be1b9dL;
                                            continue;
                                        }
                                        int n9 = 0;
                                        byte[] byArray3 = this.byteFlagArray3;
                                        int n10 = n4;
                                        byte[] byArray4 = byArray;
                                        this.applyByteArray(byArray4, n10, byArray3, n9);
                                        l = (l ^ 0xA4909492541D2B7BL) - 0x4b05aa694c0ca1b2L;
                                        l2 = (l2 ^ 0xFAF1D48B1685320BL) - 0x63da25fb433ec07eL;
                                        continue;
                                    }
                                    ++n7;
                                    l = (l ^ 0x700F85ADDF6D8FF8L) + 0x48921eafbb250d9bL;
                                    l2 = (l2 ^ 0xE57F94DE07EF52A1L) - 0x46dfc987ce32a345L;
                                }
                                if (n7 >= this.getCount2()) {
                                    long l3 = (l ^ 0x35FE914985CD047EL) - 0x35f14cc1a7f9de5L;
                                    long l4 = (l2 ^ 0x9B37C2DCBA1EC511L) + 0x1368787a255db6b3L;
                                    System.arraycopy(byArray, n4, this.byteFlagArray4, 0, this.getCount2());
                                    l = (l3 ^ 0x78640AE598241D81L) + 0x2655399a8b1d4009L;
                                    l2 = (l4 ^ 0xFF77654878B32071L) - 0x778d33c69fddbfd6L;
                                    continue;
                                }
                                l = (l ^ 0xE44B7866D67DCC48L) - 0x256b8407bc7117b6L;
                                l2 = (l2 ^ 0x234F1158E2E8FF71L) + 0x3c428340975ed85bL;
                                continue;
                            }
                            n5 += this.getCount2();
                            l = (l ^ 0x33BFC3D0671DDC64L) + 0x2bb1dfda66552a66L;
                            l2 = (l2 ^ 0x576F44BC7C54850CL) + 0x270676ef3e1ca4cdL;
                            continue;
                        }
                        n6 = n4 + n2;
                        l = (l ^ 0x7BC6CDBB59ABEF1EL) + 0x5a7c42684c661e5aL;
                        l2 = (l2 ^ 0x83403FC7EFD41311L) + 0x7307eb72523db0b4L;
                        continue;
                    }
                    n4 += this.getCount2();
                    l = (l ^ 0xEC3D2BAAC45BEA6BL) + 0x4d3d9df7bd2842dfL;
                    l2 = (l2 ^ 0x8CD798A0270E2FABL) + 0x582ff79a3d005d1eL;
                    continue;
                }
                l = (l ^ 0x38A052EAD3F962E5L) + 0x10039541d32e70eL;
                l2 = (l2 ^ 0x9E16259082D39C4BL) + 0xfc71d726cb129d2L;
            }
            if (n4 >= n6) {
                return n2;
            }
            l = (l ^ 0x824D2BE7207FE503L) + 0x48b66d0224747622L;
            l2 = (l2 ^ 0x843ACB72331DFB08L) + 0x4bec9b1087b0f086L;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void applyByteArray3(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        n3 = 4;
        n4 = (byArray[n++] << 24 | (byArray[n++] & 0xFF) << 16 | (byArray[n++] & 0xFF) << 8 | byArray[n++] & 0xFF) ^ this.countArray12[n3++];
        n5 = (byArray[n++] << 24 | (byArray[n++] & 0xFF) << 16 | (byArray[n++] & 0xFF) << 8 | byArray[n++] & 0xFF) ^ this.countArray12[n3++];
        n6 = (byArray[n++] << 24 | (byArray[n++] & 0xFF) << 16 | (byArray[n++] & 0xFF) << 8 | byArray[n++] & 0xFF) ^ this.countArray12[n3++];
        n7 = (byArray[n++] << 24 | (byArray[n++] & 0xFF) << 16 | (byArray[n++] & 0xFF) << 8 | byArray[n] & 0xFF) ^ this.countArray12[n3++];
        if (this.enabled) {
            n8 = this.countArray15[n4 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n6 >>> 8 & 0xFF] ^ this.countArray4[n5 & 0xFF] ^ this.countArray12[n3++];
            n9 = this.countArray15[n5 >>> 24] ^ this.countArray10[n4 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n6 & 0xFF] ^ this.countArray12[n3++];
            n10 = this.countArray15[n6 >>> 24] ^ this.countArray10[n5 >>> 16 & 0xFF] ^ this.countArray11[n4 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
            n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n6 >>> 16 & 0xFF] ^ this.countArray11[n5 >>> 8 & 0xFF] ^ this.countArray4[n4 & 0xFF] ^ this.countArray12[n3++];
            n4 = this.countArray15[n8 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n10 >>> 8 & 0xFF] ^ this.countArray4[n9 & 0xFF] ^ this.countArray12[n3++];
            n5 = this.countArray15[n9 >>> 24] ^ this.countArray10[n8 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n10 & 0xFF] ^ this.countArray12[n3++];
            n6 = this.countArray15[n10 >>> 24] ^ this.countArray10[n9 >>> 16 & 0xFF] ^ this.countArray11[n8 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
            n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n10 >>> 16 & 0xFF] ^ this.countArray11[n9 >>> 8 & 0xFF] ^ this.countArray4[n8 & 0xFF] ^ this.countArray12[n3++];
            if (this.enabled2) {
                n8 = this.countArray15[n4 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n6 >>> 8 & 0xFF] ^ this.countArray4[n5 & 0xFF] ^ this.countArray12[n3++];
                n9 = this.countArray15[n5 >>> 24] ^ this.countArray10[n4 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n6 & 0xFF] ^ this.countArray12[n3++];
                n10 = this.countArray15[n6 >>> 24] ^ this.countArray10[n5 >>> 16 & 0xFF] ^ this.countArray11[n4 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
                n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n6 >>> 16 & 0xFF] ^ this.countArray11[n5 >>> 8 & 0xFF] ^ this.countArray4[n4 & 0xFF] ^ this.countArray12[n3++];
                n4 = this.countArray15[n8 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n10 >>> 8 & 0xFF] ^ this.countArray4[n9 & 0xFF] ^ this.countArray12[n3++];
                n5 = this.countArray15[n9 >>> 24] ^ this.countArray10[n8 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n10 & 0xFF] ^ this.countArray12[n3++];
                n6 = this.countArray15[n10 >>> 24] ^ this.countArray10[n9 >>> 16 & 0xFF] ^ this.countArray11[n8 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
                n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n10 >>> 16 & 0xFF] ^ this.countArray11[n9 >>> 8 & 0xFF] ^ this.countArray4[n8 & 0xFF] ^ this.countArray12[n3++];
            }
        }
        n8 = this.countArray15[n4 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n6 >>> 8 & 0xFF] ^ this.countArray4[n5 & 0xFF] ^ this.countArray12[n3++];
        n9 = this.countArray15[n5 >>> 24] ^ this.countArray10[n4 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n6 & 0xFF] ^ this.countArray12[n3++];
        n10 = this.countArray15[n6 >>> 24] ^ this.countArray10[n5 >>> 16 & 0xFF] ^ this.countArray11[n4 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n6 >>> 16 & 0xFF] ^ this.countArray11[n5 >>> 8 & 0xFF] ^ this.countArray4[n4 & 0xFF] ^ this.countArray12[n3++];
        n4 = this.countArray15[n8 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n10 >>> 8 & 0xFF] ^ this.countArray4[n9 & 0xFF] ^ this.countArray12[n3++];
        n5 = this.countArray15[n9 >>> 24] ^ this.countArray10[n8 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n10 & 0xFF] ^ this.countArray12[n3++];
        n6 = this.countArray15[n10 >>> 24] ^ this.countArray10[n9 >>> 16 & 0xFF] ^ this.countArray11[n8 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n10 >>> 16 & 0xFF] ^ this.countArray11[n9 >>> 8 & 0xFF] ^ this.countArray4[n8 & 0xFF] ^ this.countArray12[n3++];
        n8 = this.countArray15[n4 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n6 >>> 8 & 0xFF] ^ this.countArray4[n5 & 0xFF] ^ this.countArray12[n3++];
        n9 = this.countArray15[n5 >>> 24] ^ this.countArray10[n4 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n6 & 0xFF] ^ this.countArray12[n3++];
        n10 = this.countArray15[n6 >>> 24] ^ this.countArray10[n5 >>> 16 & 0xFF] ^ this.countArray11[n4 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n6 >>> 16 & 0xFF] ^ this.countArray11[n5 >>> 8 & 0xFF] ^ this.countArray4[n4 & 0xFF] ^ this.countArray12[n3++];
        n4 = this.countArray15[n8 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n10 >>> 8 & 0xFF] ^ this.countArray4[n9 & 0xFF] ^ this.countArray12[n3++];
        n5 = this.countArray15[n9 >>> 24] ^ this.countArray10[n8 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n10 & 0xFF] ^ this.countArray12[n3++];
        n6 = this.countArray15[n10 >>> 24] ^ this.countArray10[n9 >>> 16 & 0xFF] ^ this.countArray11[n8 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n10 >>> 16 & 0xFF] ^ this.countArray11[n9 >>> 8 & 0xFF] ^ this.countArray4[n8 & 0xFF] ^ this.countArray12[n3++];
        n8 = this.countArray15[n4 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n6 >>> 8 & 0xFF] ^ this.countArray4[n5 & 0xFF] ^ this.countArray12[n3++];
        n9 = this.countArray15[n5 >>> 24] ^ this.countArray10[n4 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n6 & 0xFF] ^ this.countArray12[n3++];
        n10 = this.countArray15[n6 >>> 24] ^ this.countArray10[n5 >>> 16 & 0xFF] ^ this.countArray11[n4 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n6 >>> 16 & 0xFF] ^ this.countArray11[n5 >>> 8 & 0xFF] ^ this.countArray4[n4 & 0xFF] ^ this.countArray12[n3++];
        n4 = this.countArray15[n8 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n10 >>> 8 & 0xFF] ^ this.countArray4[n9 & 0xFF] ^ this.countArray12[n3++];
        n5 = this.countArray15[n9 >>> 24] ^ this.countArray10[n8 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n10 & 0xFF] ^ this.countArray12[n3++];
        n6 = this.countArray15[n10 >>> 24] ^ this.countArray10[n9 >>> 16 & 0xFF] ^ this.countArray11[n8 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n10 >>> 16 & 0xFF] ^ this.countArray11[n9 >>> 8 & 0xFF] ^ this.countArray4[n8 & 0xFF] ^ this.countArray12[n3++];
        n8 = this.countArray15[n4 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n6 >>> 8 & 0xFF] ^ this.countArray4[n5 & 0xFF] ^ this.countArray12[n3++];
        n9 = this.countArray15[n5 >>> 24] ^ this.countArray10[n4 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n6 & 0xFF] ^ this.countArray12[n3++];
        n10 = this.countArray15[n6 >>> 24] ^ this.countArray10[n5 >>> 16 & 0xFF] ^ this.countArray11[n4 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n6 >>> 16 & 0xFF] ^ this.countArray11[n5 >>> 8 & 0xFF] ^ this.countArray4[n4 & 0xFF] ^ this.countArray12[n3++];
        n4 = this.countArray15[n8 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n10 >>> 8 & 0xFF] ^ this.countArray4[n9 & 0xFF] ^ this.countArray12[n3++];
        n5 = this.countArray15[n9 >>> 24] ^ this.countArray10[n8 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n10 & 0xFF] ^ this.countArray12[n3++];
        n6 = this.countArray15[n10 >>> 24] ^ this.countArray10[n9 >>> 16 & 0xFF] ^ this.countArray11[n8 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n10 >>> 16 & 0xFF] ^ this.countArray11[n9 >>> 8 & 0xFF] ^ this.countArray4[n8 & 0xFF] ^ this.countArray12[n3++];
        n8 = this.countArray15[n4 >>> 24] ^ this.countArray10[n7 >>> 16 & 0xFF] ^ this.countArray11[n6 >>> 8 & 0xFF] ^ this.countArray4[n5 & 0xFF] ^ this.countArray12[n3++];
        n9 = this.countArray15[n5 >>> 24] ^ this.countArray10[n4 >>> 16 & 0xFF] ^ this.countArray11[n7 >>> 8 & 0xFF] ^ this.countArray4[n6 & 0xFF] ^ this.countArray12[n3++];
        n10 = this.countArray15[n6 >>> 24] ^ this.countArray10[n5 >>> 16 & 0xFF] ^ this.countArray11[n4 >>> 8 & 0xFF] ^ this.countArray4[n7 & 0xFF] ^ this.countArray12[n3++];
        n7 = this.countArray15[n7 >>> 24] ^ this.countArray10[n6 >>> 16 & 0xFF] ^ this.countArray11[n5 >>> 8 & 0xFF] ^ this.countArray4[n4 & 0xFF] ^ this.countArray12[n3++];
        n5 = this.countArray12[0];
        byArray2[n2++] = (byte)(this.byteFlagArray[n8 >>> 24] ^ n5 >>> 24);
        byArray2[n2++] = (byte)(this.byteFlagArray[n7 >>> 16 & 0xFF] ^ n5 >>> 16);
        byArray2[n2++] = (byte)(this.byteFlagArray[n10 >>> 8 & 0xFF] ^ n5 >>> 8);
        byArray2[n2++] = (byte)(this.byteFlagArray[n9 & 0xFF] ^ n5);
        n5 = this.countArray12[1];
        byArray2[n2++] = (byte)(this.byteFlagArray[n9 >>> 24] ^ n5 >>> 24);
        byArray2[n2++] = (byte)(this.byteFlagArray[n8 >>> 16 & 0xFF] ^ n5 >>> 16);
        byArray2[n2++] = (byte)(this.byteFlagArray[n7 >>> 8 & 0xFF] ^ n5 >>> 8);
        byArray2[n2++] = (byte)(this.byteFlagArray[n10 & 0xFF] ^ n5);
        n5 = this.countArray12[2];
        byArray2[n2++] = (byte)(this.byteFlagArray[n10 >>> 24] ^ n5 >>> 24);
        byArray2[n2++] = (byte)(this.byteFlagArray[n9 >>> 16 & 0xFF] ^ n5 >>> 16);
        byArray2[n2++] = (byte)(this.byteFlagArray[n8 >>> 8 & 0xFF] ^ n5 >>> 8);
        byArray2[n2++] = (byte)(this.byteFlagArray[n7 & 0xFF] ^ n5);
        n5 = this.countArray12[3];
        byArray2[n2++] = (byte)(this.byteFlagArray[n7 >>> 24] ^ n5 >>> 24);
        byArray2[n2++] = (byte)(this.byteFlagArray[n10 >>> 16 & 0xFF] ^ n5 >>> 16);
        byArray2[n2++] = (byte)(this.byteFlagArray[n9 >>> 8 & 0xFF] ^ n5 >>> 8);
        byArray2[n2] = (byte)(this.byteFlagArray[n8 & 0xFF] ^ n5);
    }

    /*
     * Enabled aggressive block sorting
     */
    private int[] resolveIndexArray(int[][] nArray, boolean bl) {
        int n = 0;
        int[] nArray2 = null;
        int n2 = 0;
        long l = 0x173516aa6de76bd9L;
        long l2 = 0x3fd59996baa53557L;
        while (true) {
            block14: {
                block5: {
                    block12: {
                        block8: {
                            block10: {
                                block7: {
                                    block13: {
                                        block9: {
                                            block11: {
                                                block6: {
                                                    block4: {
                                                        int n3;
                                                        if ((n3 = (int)(l % 11L)) == 2) break block4;
                                                        if (n3 == 9) break block5;
                                                        if (n3 == 7) break block6;
                                                        if (n3 == 6) break block7;
                                                        if (n3 == 0) break block8;
                                                        if (n3 == 10) break block9;
                                                        if (n3 == 8) break block10;
                                                        if (n3 == 4) break block11;
                                                        if (n3 == 1) break block12;
                                                        if (n3 == 5) break block13;
                                                        break block14;
                                                    }
                                                    System.arraycopy(nArray[n2 - 1], 0, nArray2, n2 * 4, 4);
                                                    l = (l ^ 0x531C4706A52011D0L) + 0x8dc5879138c8642L;
                                                    l2 = (l2 ^ 0x8366C816645605DFL) - 0x495b3883894fc2dL;
                                                    continue;
                                                }
                                                n2 = 1;
                                                l = (l ^ 0x47839B0A664CF1DL) - 0x88e06b506bc2e1L;
                                                l2 = (l2 ^ 0x5A33BDB2F08C395FL) - 0x2de4b09864b46874L;
                                                continue;
                                            }
                                            if (!bl) {
                                                long l3 = (l ^ 0x41D8F713D42C2839L) + 0xe2c0d45d89ea119L;
                                                long l4 = (l2 ^ 0xB4E1BA2BD4B6133BL) - 0x7071e9ad4d5456d0L;
                                                n2 = 0;
                                                l = (l3 ^ 0x6BEA8A2A5335E7E5L) - 0x4356b1fda4bda8f2L;
                                                l2 = (l4 ^ 0x771DDF9BC581608EL) - 0x263f2d67dd382a38L;
                                                continue;
                                            }
                                            l = (l ^ 0x743FC4C28FF286B3L) + 0x4e1bae98cff50274L;
                                            l2 = (l2 ^ 0xBD8E59480CE0B5D9L) + 0x631b4891d4052519L;
                                            continue;
                                        }
                                        System.arraycopy(nArray[n - 1], 0, nArray2, 0, 4);
                                        l = (l ^ 0x69641D7B8552F3CCL) - 0x7fdf753b7e8baedL;
                                        l2 = (l2 ^ 0x85FBD07CB650E516L) - 0x65d13670cc64024bL;
                                        continue;
                                    }
                                    ++n2;
                                    l = (l ^ 0x6D60F2C802A8A328L) - 0x3dbeca9a595f3f66L;
                                    l2 = (l2 ^ 0xA15CECBAF6B60AA7L) - 0x193688eee8923951L;
                                    break block8;
                                }
                                n = nArray.length;
                                l = (l ^ 0x6B2ACDAB8534365EL) - 0x641172012b812514L;
                                l2 = (l2 ^ 0xF1DF5A40A9BE897L) + 0x15e760c59615e935L;
                                continue;
                            }
                            nArray2 = new int[n * 4];
                            l = (l ^ 0x6DEE1EAA14BDC944L) - 0x1e6ea494e9951bceL;
                            l2 = (l2 ^ 0xF808EB0F7549259AL) - 0x4448ce32318ecd09L;
                            continue;
                        }
                        if (n2 >= n) {
                            return nArray2;
                        }
                        l = (l ^ 0x3E8BCFCEC362B52DL) + 0x36dd224ed4f66d5L;
                        l2 = (l2 ^ 0x4804EC9A4C94C736L) - 0x5a2c23f06ed7fea4L;
                        continue;
                    }
                    System.arraycopy(nArray[n2], 0, nArray2, n2 * 4, 4);
                    l = (l ^ 0x8D07E63562F508B3L) + 0x6eefa23bcaafeee9L;
                    l2 = (l2 ^ 0x7DA046942138363BL) + 0x2fd3b7781f308759L;
                    continue;
                }
                ++n2;
                l = (l ^ 0x44993284057CEFB9L) - 0x4c5d0a33f4530f29L;
                l2 = (l2 ^ 0x8D1125311DD9F590L) + 0x11cb4f3a7b5a4f4bL;
            }
            if (n2 >= n) {
                return nArray2;
            }
            l = (l ^ 0x46A5C44BF02E27A4L) - 0xc669dfb878f3b0cL;
            l2 = (l2 ^ 0xA4A77E6F7FBA6069L) - 0x6009eae4c5fe1a9cL;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int computeCount(int n, int n2) {
        if (n == 0) return 0;
        if (n2 == 0) {
            return 0;
        }
        int n3 = this.countArray9[(this.countArray6[n & 0xFF] + this.countArray6[n2 & 0xFF]) % 255];
        return n3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void applyByteArray(byte[] byArray) throws InvalidKeyException {
        int n = 21797;
        int n2 = 56028;
        int n3 = 61842;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ 0x76D45F8373F7L ^ 0x8154FEBE216L;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int[][] nArray = null;
        int[][] nArray2 = null;
        int n7 = 0;
        int[] nArray3 = null;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        int[] nArray4 = null;
        int[] nArray5 = null;
        long l2 = 0x38f31963af8db77L;
        long l3 = -0x4006589464dd7232L;
        while (true) {
            block39: {
                block66: {
                    block33: {
                        block73: {
                            long l4;
                            long l5;
                            block19: {
                                block45: {
                                    block31: {
                                        block44: {
                                            block26: {
                                                block49: {
                                                    long l6;
                                                    long l7;
                                                    block72: {
                                                        block61: {
                                                            block20: {
                                                                block38: {
                                                                    block68: {
                                                                        block59: {
                                                                            block21: {
                                                                                block56: {
                                                                                    block23: {
                                                                                        block55: {
                                                                                            block70: {
                                                                                                block71: {
                                                                                                    block29: {
                                                                                                        block53: {
                                                                                                            block40: {
                                                                                                                block32: {
                                                                                                                    block27: {
                                                                                                                        block22: {
                                                                                                                            block42: {
                                                                                                                                block46: {
                                                                                                                                    block62: {
                                                                                                                                        block63: {
                                                                                                                                            block30: {
                                                                                                                                                block51: {
                                                                                                                                                    block41: {
                                                                                                                                                        block24: {
                                                                                                                                                            block60: {
                                                                                                                                                                block52: {
                                                                                                                                                                    block65: {
                                                                                                                                                                        block57: {
                                                                                                                                                                            block69: {
                                                                                                                                                                                block37: {
                                                                                                                                                                                    block58: {
                                                                                                                                                                                        block36: {
                                                                                                                                                                                            block35: {
                                                                                                                                                                                                block25: {
                                                                                                                                                                                                    block34: {
                                                                                                                                                                                                        block64: {
                                                                                                                                                                                                            block43: {
                                                                                                                                                                                                                block67: {
                                                                                                                                                                                                                    block48: {
                                                                                                                                                                                                                        block50: {
                                                                                                                                                                                                                            block47: {
                                                                                                                                                                                                                                block28: {
                                                                                                                                                                                                                                    block54: {
                                                                                                                                                                                                                                        int n14;
                                                                                                                                                                                                                                        if ((n14 = (int)(l2 % 52L)) == 4) break block20;
                                                                                                                                                                                                                                        if (n14 == 30) break block21;
                                                                                                                                                                                                                                        if (n14 == 35) break block22;
                                                                                                                                                                                                                                        if (n14 == 8) break block23;
                                                                                                                                                                                                                                        if (n14 == 36) break block24;
                                                                                                                                                                                                                                        if (n14 == 9) break block25;
                                                                                                                                                                                                                                        if (n14 == 43) break block26;
                                                                                                                                                                                                                                        if (n14 == 11) break block27;
                                                                                                                                                                                                                                        if (n14 == 26) break block28;
                                                                                                                                                                                                                                        if (n14 == 45) break block29;
                                                                                                                                                                                                                                        if (n14 == 6) break block30;
                                                                                                                                                                                                                                        if (n14 == 34) break block31;
                                                                                                                                                                                                                                        if (n14 == 17) break block32;
                                                                                                                                                                                                                                        if (n14 == 48) break block33;
                                                                                                                                                                                                                                        if (n14 == 19) break block34;
                                                                                                                                                                                                                                        if (n14 == 3) break block35;
                                                                                                                                                                                                                                        if (n14 == 49) break block36;
                                                                                                                                                                                                                                        if (n14 == 29) break block37;
                                                                                                                                                                                                                                        if (n14 == 44) break block38;
                                                                                                                                                                                                                                        if (n14 == 27) break block39;
                                                                                                                                                                                                                                        if (n14 == 40) break block40;
                                                                                                                                                                                                                                        if (n14 == 39) break block41;
                                                                                                                                                                                                                                        if (n14 == 7) break block42;
                                                                                                                                                                                                                                        if (n14 == 22) break block43;
                                                                                                                                                                                                                                        if (n14 == 18) break block44;
                                                                                                                                                                                                                                        if (n14 == 21) break block45;
                                                                                                                                                                                                                                        if (n14 == 25) break block46;
                                                                                                                                                                                                                                        if (n14 == 33) break block47;
                                                                                                                                                                                                                                        if (n14 == 1) break block48;
                                                                                                                                                                                                                                        if (n14 == 41) break block49;
                                                                                                                                                                                                                                        if (n14 == 2) break block50;
                                                                                                                                                                                                                                        if (n14 == 12) break block51;
                                                                                                                                                                                                                                        if (n14 == 15) break block52;
                                                                                                                                                                                                                                        if (n14 == 24) break block53;
                                                                                                                                                                                                                                        if (n14 == 23) break block54;
                                                                                                                                                                                                                                        if (n14 == 47) break block55;
                                                                                                                                                                                                                                        if (n14 == 32) break block56;
                                                                                                                                                                                                                                        if (n14 == 50) break block57;
                                                                                                                                                                                                                                        if (n14 == 10) break block58;
                                                                                                                                                                                                                                        if (n14 == 46) break block59;
                                                                                                                                                                                                                                        if (n14 == 42) break block60;
                                                                                                                                                                                                                                        if (n14 == 37) break block61;
                                                                                                                                                                                                                                        if (n14 == 20) break block62;
                                                                                                                                                                                                                                        if (n14 == 31) break block63;
                                                                                                                                                                                                                                        if (n14 == 51) break block64;
                                                                                                                                                                                                                                        if (n14 == 38) break block65;
                                                                                                                                                                                                                                        if (n14 == 13) break block66;
                                                                                                                                                                                                                                        if (n14 == 0) break block67;
                                                                                                                                                                                                                                        if (n14 == 16) {
                                                                                                                                                                                                                                            throw new InvalidKeyException("Empty key");
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                        if (n14 == 14) break block68;
                                                                                                                                                                                                                                        if (n14 == 5) break block69;
                                                                                                                                                                                                                                        if (n10 < n5) break block70;
                                                                                                                                                                                                                                        break block71;
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    if (n7 == 8) {
                                                                                                                                                                                                                                        long l8 = (l2 ^ 0x2E888FF8EB5DD015L) - 0x20eb011bd1d731aeL;
                                                                                                                                                                                                                                        long l9 = (l3 ^ 0x166BDCA7168F24DEL) + 0x6399b26196d33865L;
                                                                                                                                                                                                                                        n8 = 1;
                                                                                                                                                                                                                                        l2 = (l8 ^ 0xBFE57A89C64D2DEBL) - 0x4200e3dfbdaf6747L;
                                                                                                                                                                                                                                        l3 = (l9 ^ 0x353F067A22034396L) + 0x62871a97cc6c75f3L;
                                                                                                                                                                                                                                        continue;
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    l2 = (l2 ^ 0x18A73B24FED6E96CL) + 0x75ef9a998922439eL;
                                                                                                                                                                                                                                    l3 = (l3 ^ 0x765FA13B67DFF1E9L) - 0xca92d664aea6f6L;
                                                                                                                                                                                                                                    continue;
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                n11 = nArray3[n7 - 1];
                                                                                                                                                                                                                                l2 = (l2 ^ 0xA4AFFC875ED90142L) - 0x581ba57e44d47d94L;
                                                                                                                                                                                                                                l3 = (l3 ^ 0x2E9798C53C911B34L) - 0x2b15ef955b0b5d9aL;
                                                                                                                                                                                                                                continue;
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                            ++n8;
                                                                                                                                                                                                                            ++n9;
                                                                                                                                                                                                                            l2 = (l2 ^ 0x5D19611E8C23257DL) + 0x3349761918522d03L;
                                                                                                                                                                                                                            l3 = (l3 ^ 0x2E1DA95CD86CDD00L) - 0x2dc8d6e94da909c3L;
                                                                                                                                                                                                                            break block55;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                        nArray[n10 / 4][n10 % 4] = nArray3[n9];
                                                                                                                                                                                                                        l2 = (l2 ^ 0x9E1A4654785FFB22L) + 0x409d4ea0c2c6b203L;
                                                                                                                                                                                                                        l3 = (l3 ^ 0x71E56A26B79F85D8L) + 0x72fbb7e273b585adL;
                                                                                                                                                                                                                        continue;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    n5 = (n4 + 1) * 4;
                                                                                                                                                                                                                    l2 = (l2 ^ 0x6D54029BE33F146FL) - 0x59f3d60309093b4cL;
                                                                                                                                                                                                                    l3 = (l3 ^ 0xA0BEABD6FD680BF4L) + 0xbc8ca0991a50664L;
                                                                                                                                                                                                                    continue;
                                                                                                                                                                                                                }
                                                                                                                                                                                                                this.enabled = n4 >= 12;
                                                                                                                                                                                                                this.enabled2 = n4 == 14;
                                                                                                                                                                                                                this.count = n4 * 4;
                                                                                                                                                                                                                this.countArray2 = new int[][]{nArray5, nArray4};
                                                                                                                                                                                                                return;
                                                                                                                                                                                                            }
                                                                                                                                                                                                            int n15 = n7 / 2;
                                                                                                                                                                                                            nArray3[n15] = nArray3[n15] ^ (this.byteFlagArray2[n11 & 0xFF] & 0xFF ^ (this.byteFlagArray2[n11 >>> 8 & 0xFF] & 0xFF) << 8 ^ (this.byteFlagArray2[n11 >>> 16 & 0xFF] & 0xFF) << 16 ^ this.byteFlagArray2[n11 >>> 24] << 24);
                                                                                                                                                                                                            l2 = (l2 ^ 0x9B15CD1BACC2BEADL) - 0x5c4539fcd8e5c514L;
                                                                                                                                                                                                            l3 = (l3 ^ 0xF384BE3DD4046B35L) + 0x3326aa265b91efd2L;
                                                                                                                                                                                                            continue;
                                                                                                                                                                                                        }
                                                                                                                                                                                                        n9 = 0;
                                                                                                                                                                                                        l2 = (l2 ^ 0xFA26A883D89C4328L) + 0x7c4f3df65c499cbfL;
                                                                                                                                                                                                        l3 = (l3 ^ 0x313491695F9B428AL) + 0x3c0262e8e1f5e06dL;
                                                                                                                                                                                                        continue;
                                                                                                                                                                                                    }
                                                                                                                                                                                                    int n16 = n8;
                                                                                                                                                                                                    nArray3[n16] = nArray3[n16] ^ nArray3[n9];
                                                                                                                                                                                                    l2 = (l2 ^ 0x4D61CE2E03AB93BCL) + 0x47cced0025004101L;
                                                                                                                                                                                                    l3 = (l3 ^ 0x6D773AB6B545A6B4L) + 0x20a8e491a9e63d07L;
                                                                                                                                                                                                    continue;
                                                                                                                                                                                                }
                                                                                                                                                                                                n8 = n9 + 1;
                                                                                                                                                                                                l2 = (l2 ^ 0x63D24E895A277CEAL) + 0x117c1ff18eb12e75L;
                                                                                                                                                                                                l3 = (l3 ^ 0xBCF51C3A835FF20L) - 0x764e3fd2208556d7L;
                                                                                                                                                                                                continue;
                                                                                                                                                                                            }
                                                                                                                                                                                            ++n9;
                                                                                                                                                                                            ++n10;
                                                                                                                                                                                            l2 = (l2 ^ 0x870335B6D2969135L) - 0x3c0fa5f1096c4de9L;
                                                                                                                                                                                            l3 = (l3 ^ 0x78B77420C2A4294BL) - 0x72f12a445b93bd34L;
                                                                                                                                                                                        }
                                                                                                                                                                                        if (n9 >= n7) {
                                                                                                                                                                                            l2 = (l2 ^ 0x84D80FA7B7193FDL) - 0x24cea464c9a3a809L;
                                                                                                                                                                                            l3 = (l3 ^ 0xA7E4D11736F282A6L) + 0x37852fafdce06e53L;
                                                                                                                                                                                            continue;
                                                                                                                                                                                        }
                                                                                                                                                                                        l2 = (l2 ^ 0x173A9007B978B407L) - 0x4b50c62134e3d2a0L;
                                                                                                                                                                                        l3 = (l3 ^ 0xC6A288977707B129L) + 0x2597747084092ec6L;
                                                                                                                                                                                        continue;
                                                                                                                                                                                    }
                                                                                                                                                                                    int n17 = n8;
                                                                                                                                                                                    nArray3[n17] = nArray3[n17] ^ nArray3[n9];
                                                                                                                                                                                    l2 = (l2 ^ 0xD81556EFB6A7C879L) - 0x6c151bdf6d793a46L;
                                                                                                                                                                                    l3 = (l3 ^ 0x32963F94E543028BL) + 0x20ef5afdbbe9b870L;
                                                                                                                                                                                    continue;
                                                                                                                                                                                }
                                                                                                                                                                                nArray2[n13][n9] = this.countArray5[n11 >>> 24 & 0xFF] ^ this.countArray13[n11 >>> 16 & 0xFF] ^ this.countArray[n11 >>> 8 & 0xFF] ^ this.countArray7[n11 & 0xFF];
                                                                                                                                                                                l2 = (l2 ^ 0x4362BD124036DFB3L) + 0x307d90b911304e6fL;
                                                                                                                                                                                l3 = (l3 ^ 0x74CA9F5764F9759FL) + 0x1e03a1cf87ca62e9L;
                                                                                                                                                                                continue;
                                                                                                                                                                            }
                                                                                                                                                                            ++n9;
                                                                                                                                                                            l2 = (l2 ^ 0xA5CF6CB27070FBC3L) - 0x7ac3761da2c18468L;
                                                                                                                                                                            l3 = (l3 ^ 0xFBC221F707B3F73L) - 0x5bfd37ddb178813aL;
                                                                                                                                                                        }
                                                                                                                                                                        if (n9 >= n6) {
                                                                                                                                                                            long l10 = (l2 ^ 0xD5FC7CF442F25E06L) - 0x16946a48ae4129d3L;
                                                                                                                                                                            long l11 = (l3 ^ 0x915D7540087EC2FDL) - 0x99b54c566747ebcL;
                                                                                                                                                                            ++n13;
                                                                                                                                                                            l2 = (l10 ^ 0x41C63BA91142CC65L) + 0x2db6f72779d2854aL;
                                                                                                                                                                            l3 = (l11 ^ 0xCDA772EE66D57325L) + 0x7095de665fca28f2L;
                                                                                                                                                                        } else {
                                                                                                                                                                            l2 = (l2 ^ 0xF81D249EAA5441D3L) - 0x30825ce64c906cf0L;
                                                                                                                                                                            l3 = (l3 ^ 0xB1163DCFE7ACFCBDL) - 0x1bb3a7b5bc8be823L;
                                                                                                                                                                            continue;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                    if (n13 >= n4) {
                                                                                                                                                                        long l12 = (l2 ^ 0x993CB705CB6EC7AAL) + 0x799b7b42e0dc7135L;
                                                                                                                                                                        long l13 = (l3 ^ 0xA853AA0B6A369FDEL) - 0x4d3d46bfa2e5ab69L;
                                                                                                                                                                        boolean bl = false;
                                                                                                                                                                        int[][] nArray6 = nArray;
                                                                                                                                                                        nArray5 = this.resolveIndexArray(nArray6, bl);
                                                                                                                                                                        l2 = (l12 ^ 0xC2C01DFA40EE8E7AL) - 0x5c0373ebe68f7db0L;
                                                                                                                                                                        l3 = (l13 ^ 0xAAF303DC70F522F9L) - 0x359c6e1371b0db64L;
                                                                                                                                                                        continue;
                                                                                                                                                                    }
                                                                                                                                                                    l2 = (l2 ^ 0x58C0962B5A81E353L) - 0x1a9d53be28af2072L;
                                                                                                                                                                    l3 = (l3 ^ 0xD41C3A2142ED4974L) - 0x4b81ff24027a620dL;
                                                                                                                                                                    continue;
                                                                                                                                                                }
                                                                                                                                                                nArray2[n4 - n10 / 4][n10 % 4] = nArray3[n9];
                                                                                                                                                                l2 = (l2 ^ 0x7A1129CFFE87EF50L) - 0x390ac7164b3a6c7bL;
                                                                                                                                                                l3 = (l3 ^ 0x4E3C50BD30FD00BCL) - 0x6f9ea069fb7277f6L;
                                                                                                                                                                continue;
                                                                                                                                                            }
                                                                                                                                                            n6 = 4;
                                                                                                                                                            l2 = (l2 ^ 0x692F7B5B1374C645L) - 0x31fb98d17643d62cL;
                                                                                                                                                            l3 = (l3 ^ 0x992817E3D922B7AEL) - 0x8986a9922865d0aL;
                                                                                                                                                            continue;
                                                                                                                                                        }
                                                                                                                                                        nArray3 = new int[n7];
                                                                                                                                                        l2 = (l2 ^ 0x5AB1A462195E488FL) - 0x15dc1adf882758a1L;
                                                                                                                                                        l3 = (l3 ^ 0x42ABF28AE4450322L) - 0x6a8a79f7ad7e4ab2L;
                                                                                                                                                        continue;
                                                                                                                                                    }
                                                                                                                                                    n9 = n7 / 2;
                                                                                                                                                    l2 = (l2 ^ 0x6C0EE1BA69A29D6BL) + 0x17d8799ffa35a985L;
                                                                                                                                                    l3 = (l3 ^ 0x6286D4A4FC6B5B6BL) - 0x6587d1c780637398L;
                                                                                                                                                    continue;
                                                                                                                                                }
                                                                                                                                                n9 = 0;
                                                                                                                                                l2 = (l2 ^ 0x6E4BC0DD32E0AC10L) + 0x57b310419f3efd33L;
                                                                                                                                                l3 = (l3 ^ 0x6F58942339AF2313L) + 0x21dd5fc61033e3d9L;
                                                                                                                                                continue;
                                                                                                                                            }
                                                                                                                                            if (n10 >= n5) {
                                                                                                                                                l2 = (l2 ^ 0xD9A3069DA02853EFL) + 0x4a2c1759cd60d94aL;
                                                                                                                                                l3 = (l3 ^ 0x7997C7A2590870C6L) + 0x699c256150b4dc50L;
                                                                                                                                                continue;
                                                                                                                                            }
                                                                                                                                            l2 = (l2 ^ 0x7B380F2207F3EF3FL) - 0xf52785448b1e05bL;
                                                                                                                                            l3 = (l3 ^ 0x8205397F66E397DDL) - 0x8cb3d41be0b48fdL;
                                                                                                                                            continue;
                                                                                                                                        }
                                                                                                                                        boolean bl = true;
                                                                                                                                        int[][] nArray7 = nArray2;
                                                                                                                                        nArray4 = this.resolveIndexArray(nArray7, bl);
                                                                                                                                        l2 = (l2 ^ 0x85CD8B8824D4F257L) + 0x594335c46385e4d8L;
                                                                                                                                        l3 = (l3 ^ 0x9413C770BCD0EC7DL) - 0x7cc12df8630b7f07L;
                                                                                                                                        continue;
                                                                                                                                    }
                                                                                                                                    nArray3[0] = nArray3[0] ^ (this.byteFlagArray2[n11 >>> 16 & 0xFF] << 24 ^ (this.byteFlagArray2[n11 >>> 8 & 0xFF] & 0xFF) << 16 ^ (this.byteFlagArray2[n11 & 0xFF] & 0xFF) << 8 ^ this.byteFlagArray2[n11 >>> 24] & 0xFF ^ this.byteFlagArray5[n12++] << 24);
                                                                                                                                    l2 = (l2 ^ 0x1D3B8AC05C14ADE8L) - 0x2a57ba91e8a5f3ddL;
                                                                                                                                    l3 = (l3 ^ 0xB279B51577B4837FL) + 0x800e004349db6f4L;
                                                                                                                                    continue;
                                                                                                                                }
                                                                                                                                nArray2 = new int[n4 + 1][4];
                                                                                                                                l2 = (l2 ^ 0xF355FD63824B8AE1L) + 0x724ca032258d3c09L;
                                                                                                                                l3 = (l3 ^ 0x1BED81B4206CFB3BL) - 0x367873308c0ed285L;
                                                                                                                                continue;
                                                                                                                            }
                                                                                                                            nArray2[n4 - n10 / 4][n10 % 4] = nArray3[n9];
                                                                                                                            l2 = (l2 ^ 0xAB2B1C51FB4F11FBL) + 0x6dc0dd171505acfbL;
                                                                                                                            l3 = (l3 ^ 0xC43B406D7EE1C463L) + 0x47dcf959084b3e36L;
                                                                                                                            continue;
                                                                                                                        }
                                                                                                                        if (byArray != null) {
                                                                                                                            l2 = (l2 ^ 0xD0CF891FB78F114EL) - 0x72122d42e9ce03bbL;
                                                                                                                            l3 = (l3 ^ 0x2FB38958F794983BL) - 0x1a60ef38d3dfef2L;
                                                                                                                            continue;
                                                                                                                        }
                                                                                                                        l2 = (l2 ^ 0x1686327D85811907L) + 0x39b8d171e622b724L;
                                                                                                                        l3 = (l3 ^ 0x3AF6B91C316C5883L) + 0x41cd92e377ede2ffL;
                                                                                                                        continue;
                                                                                                                    }
                                                                                                                    if (n10 >= n5) {
                                                                                                                        long l14 = (l2 ^ 0x241CDEFB1E9F6C35L) - 0x64ef20aac270bb7L;
                                                                                                                        long l15 = (l3 ^ 0x4F69D516BABFB098L) + 0x5b626ad470952aeeL;
                                                                                                                        n13 = 1;
                                                                                                                        l2 = (l14 ^ 0x157A4E3688472665L) + 0x50ddf8e52c2eba30L;
                                                                                                                        l3 = (l15 ^ 0xF08983A8654F05A3L) + 0x168700c1a92cd97dL;
                                                                                                                        continue;
                                                                                                                    }
                                                                                                                    l2 = (l2 ^ 0xDD0182C90A738EE1L) + 0x1fe88c5c677ce248L;
                                                                                                                    l3 = (l3 ^ 0xC0A88A5211F81D05L) + 0x5fb3e4ca5edf258cL;
                                                                                                                    continue;
                                                                                                                }
                                                                                                                n7 = byArray.length / 4;
                                                                                                                l2 = (l2 ^ 0x1AD3C41DF478C06BL) - 0xddf92dd47f8a766L;
                                                                                                                l3 = (l3 ^ 0xAC9CEE59C3D9B938L) + 0x106721c0aebba563L;
                                                                                                                continue;
                                                                                                            }
                                                                                                            ++n8;
                                                                                                            ++n9;
                                                                                                            l2 = (l2 ^ 0x3EFE09AE2E95DE97L) + 0x30270635ecd199f2L;
                                                                                                            l3 = (l3 ^ 0xC467ADD211B97F88L) + 0x6df3e6cb912debf8L;
                                                                                                            break block45;
                                                                                                        }
                                                                                                        n9 = 0;
                                                                                                        l2 = (l2 ^ 0xE59D37E990949931L) - 0x291b6733194afbd4L;
                                                                                                        l3 = (l3 ^ 0xDD5F6886D5105080L) - 0x3b8bbc88a667b869L;
                                                                                                        continue;
                                                                                                    }
                                                                                                    n9 = 0;
                                                                                                    l2 = (l2 ^ 0x32FB8905B6F659B1L) - 0x4c7945d90e57e2f3L;
                                                                                                    l3 = (l3 ^ 0x77531FD4F5983997L) - 0x263b7c8827b4afccL;
                                                                                                    continue;
                                                                                                }
                                                                                                l7 = (l2 ^ 0x46B5565C3515A3F3L) - 0x4b2f7af15e83f1bfL;
                                                                                                l6 = (l3 ^ 0xC21508BF3B4476C1L) + 0x3f427b9596907b81L;
                                                                                                break block72;
                                                                                            }
                                                                                            l2 = (l2 ^ 0x56DAAB3B4948D9E8L) - 0x498fbb628e118450L;
                                                                                            l3 = (l3 ^ 0x9142B09B9561A45AL) - 0x3fbeffb9b493cc2L;
                                                                                            continue;
                                                                                        }
                                                                                        if (n8 >= n7 / 2) {
                                                                                            long l16 = (l2 ^ 0x8893C0754EFDFBBBL) - 0x60b0d54ec603a93bL;
                                                                                            long l17 = (l3 ^ 0xE924DDAB1EC2C270L) - 0x553fea390744ddadL;
                                                                                            n11 = nArray3[n7 / 2 - 1];
                                                                                            l2 = (l16 ^ 0xB70FAB9D30F9DC88L) + 0x51f3a9274e238db1L;
                                                                                            l3 = (l17 ^ 0x7C6850AD24AACCE7L) - 0x4c047550ac59a1c5L;
                                                                                            continue;
                                                                                        }
                                                                                        l2 = (l2 ^ 0x239B5D3961DE1F98L) - 0x41997d110e015ad1L;
                                                                                        l3 = (l3 ^ 0x228357527E8157FEL) - 0x58ff8d98c0b10928L;
                                                                                        continue;
                                                                                    }
                                                                                    ++n8;
                                                                                    ++n9;
                                                                                    l2 = (l2 ^ 0xF2EA207CD08124DEL) - 0x5d6f74535eb5b16aL;
                                                                                    l3 = (l3 ^ 0xDD6A1A5656E9EAA6L) - 0x60b02fb5f2c8982dL;
                                                                                }
                                                                                if (n8 >= n7) {
                                                                                    l5 = (l2 ^ 0xB794C4F1EEE3F962L) + 0x5da5972961547622L;
                                                                                    l4 = (l3 ^ 0x4EF1410951503A8BL) + 0x4ed76a80d1cf981eL;
                                                                                    break block19;
                                                                                } else {
                                                                                    l2 = (l2 ^ 0xB0A13C9833D65644L) + 0x5f8f68b995be2f4bL;
                                                                                    l3 = (l3 ^ 0xB74CD31867624573L) + 0x419adaacc6956babL;
                                                                                    continue;
                                                                                }
                                                                            }
                                                                            n8 = 0;
                                                                            l2 = (l2 ^ 0x26312EE152EC647FL) - 0x4d97be12626df10eL;
                                                                            l3 = (l3 ^ 0xE6D021028D2AE272L) + 0xeafb0ac89daaa37L;
                                                                            continue;
                                                                        }
                                                                        int n18 = byArray.length;
                                                                        n4 = this.computeCountByIndex(n18);
                                                                        l2 = (l2 ^ 0xED189C3373B148E5L) - 0x6ecdd749c674344eL;
                                                                        l3 = (l3 ^ 0xE07FC818DAA38488L) + 0x2fcf9ac3c55a4916L;
                                                                        continue;
                                                                    }
                                                                    nArray3[n8] = byArray[n9] << 24 | (byArray[n9 + 1] & 0xFF) << 16 | (byArray[n9 + 2] & 0xFF) << 8 | byArray[n9 + 3] & 0xFF;
                                                                    l2 = (l2 ^ 0x7191D7E7DFFE310EL) + 0xdb182e6e3548b66L;
                                                                    l3 = (l3 ^ 0x9CA933F090CB120DL) + 0x2ae63db9be7d8dd7L;
                                                                    continue;
                                                                }
                                                                nArray[n10 / 4][n10 % 4] = nArray3[n9];
                                                                l2 = (l2 ^ 0xFA2A477FD0D698D5L) + 0x5b17fd05eca5e48aL;
                                                                l3 = (l3 ^ 0x878DF3C0DAD47A2L) + 0x422cc066078d21caL;
                                                                continue;
                                                            }
                                                            ++n9;
                                                            ++n10;
                                                            l2 = (l2 ^ 0x3766A7F4EB21831CL) - 0x24d09ddd159752c7L;
                                                            l3 = (l3 ^ 0xFDE56D813ED3FCL) + 0x603c265d7403cb61L;
                                                        }
                                                        if (n9 >= n7) {
                                                            l7 = (l2 ^ 0xAC07B56BF13368ABL) + 0x62306fbbebc5e762L;
                                                            l6 = (l3 ^ 0x6EC3A58A0955C289L) - 0x39ade946fc555534L;
                                                        } else {
                                                            l2 = (l2 ^ 0xD34A7A10C83433DEL) + 0x2bb76d362e634e61L;
                                                            l3 = (l3 ^ 0x80535ACC4FCF1A25L) - 0x5c54a63a0a37c30aL;
                                                            continue;
                                                        }
                                                    }
                                                    n12 = 0;
                                                    l2 = (l7 ^ 0x82CF8EC0E3364BF6L) - 0x5d8a14f54ca1476bL;
                                                    l3 = (l6 ^ 0x1AECB75BF5FCF8AFL) + 0x2e4ec35f5c58035aL;
                                                    continue;
                                                }
                                                n8 = 1;
                                                l2 = (l2 ^ 0x54E2EA9FC120C34EL) + 0x39cf0b6b96c16541L;
                                                l3 = (l3 ^ 0x797894C9D2ACA66L) - 0xcafa68296800f7L;
                                                continue;
                                            }
                                            nArray = new int[n4 + 1][4];
                                            l2 = (l2 ^ 0xB893832B1B0C06AL) - 0x348f2deb13d346a8L;
                                            l3 = (l3 ^ 0xA47E60DEB2296E95L) - 0x2613601afdd0ad74L;
                                            continue;
                                        }
                                        ++n8;
                                        n9 += 4;
                                        l2 = (l2 ^ 0xE0AEFB09DEA3CBBEL) + 0x77cccaf9b207cfdeL;
                                        l3 = (l3 ^ 0xFD3A7160B710CA77L) + 0x2edde88d8cc6ea0bL;
                                    }
                                    if (n8 >= n7) {
                                        long l19 = (l2 ^ 0xE6773740B58D72ACL) - 0x71fc70bf752da7b4L;
                                        long l20 = (l3 ^ 0x91CBF9E151E22396L) + 0x47d66132728b705aL;
                                        n10 = 0;
                                        l2 = (l19 ^ 0xD2FDB7D8F04DC2C0L) - 0x7d8da2f996cc76f5L;
                                        l3 = (l20 ^ 0x55EF31E03B80B20L) + 0x7239186ce59095c1L;
                                        continue;
                                    }
                                    l2 = (l2 ^ 0x4E71BEE0FA2FE85EL) + 0x57c13217c8ad5696L;
                                    l3 = (l3 ^ 0x927460A0BE4C1792L) - 0x6f6585b5bb51378cL;
                                    continue;
                                }
                                if (n8 < n7) break block73;
                                l5 = (l2 ^ 0x32B319B6CB9B7819L) - 0x103a7037a6595320L;
                                l4 = (l3 ^ 0xD9758B460EEA04BBL) + 0x227a07802873fadL;
                            }
                            n9 = 0;
                            l2 = (l5 ^ 0x4931A010104D40F5L) + 0x342a7af29b2e1cd0L;
                            l3 = (l4 ^ 0xE155F0580852A5AEL) - 0x1171ce2c6f9e3556L;
                            continue;
                        }
                        l2 = (l2 ^ 0x4B133A70B71F75A8L) + 0x1291f8ca0e90087fL;
                        l3 = (l3 ^ 0xC15ECD5ECEDA68ADL) + 0x12e596efc1bd90b5L;
                        continue;
                    }
                    int n19 = n8;
                    nArray3[n19] = nArray3[n19] ^ nArray3[n9];
                    l2 = (l2 ^ 0x105207B2CE095EA2L) - 0x1ef4017bd2fdf20aL;
                    l3 = (l3 ^ 0xA49BE1421AEC743AL) - 0x1fb50b24a996a3ccL;
                    continue;
                }
                n11 = nArray2[n13][n9];
                l2 = (l2 ^ 0xC006BD52DB6227E6L) + 0x56324a3c2f50884aL;
                l3 = (l3 ^ 0x94D0B59D455B1E6L) + 0x2754cb2a67598429L;
                continue;
            }
            n9 = 0;
            l2 = (l2 ^ 0x629C19446281561CL) - 0x2e9c95b56d3129a1L;
            l3 = (l3 ^ 0x3FBA6C39AE710CDDL) - 0x73147ae0f1f14584L;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void updateState() {
        this.byteFlagArray4 = new byte[this.getCount2()];
        this.byteFlagArray3 = new byte[this.getCount2()];
    }

    /*
     * Enabled aggressive block sorting
     */
    private int computeCount(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        int n4 = n;
        int n5 = n3;
        long l = 0x81a85478229L;
        long l2 = 0x76D45F8373F7L ^ l ^ 0x1BB79579BE4FL;
        int n6 = 0;
        int n7 = 0;
        long l3 = 0x4b948c3f68e4a844L;
        long l4 = 0x1ca8969b8ac75adeL;
        while (true) {
            block5: {
                block8: {
                    block3: {
                        block7: {
                            block11: {
                                block12: {
                                    block4: {
                                        block6: {
                                            block9: {
                                                block10: {
                                                    int n8;
                                                    if ((n8 = (int)(l3 % 10L)) == 8) break block3;
                                                    if (n8 == 4) break block4;
                                                    if (n8 == 1) break block5;
                                                    if (n8 == 2) break block6;
                                                    if (n8 == 5) break block7;
                                                    if (n8 == 9) break block8;
                                                    if (n8 == 0) break block9;
                                                    if (n8 == 7) break block10;
                                                    if (n8 == 3) break block11;
                                                    break block12;
                                                }
                                                System.arraycopy(byArray2, n5, this.byteFlagArray4, 0, this.getCount2());
                                                l3 = (l3 ^ 0x247AE97485B9F5ECL) - 0x141f9c283b72c390L;
                                                l4 = (l4 ^ 0x8DDDA0ECE37176F5L) - 0x530205bf95214ac1L;
                                                continue;
                                            }
                                            ++n7;
                                            l3 = (l3 ^ 0xA90658E61069FACFL) - 0x71b8baca65fb1013L;
                                            l4 = (l4 ^ 0x3FF602E020AEBB4BL) + 0x400353bb77a5b30fL;
                                            break block12;
                                        }
                                        n5 += this.getCount2();
                                        l3 = (l3 ^ 0x3BCA5DF2E711F715L) - 0x5ec973b7da16bbcdL;
                                        l4 = (l4 ^ 0x378AFC86CAEFADDL) - 0x15fb2bdd79bbd02bL;
                                        continue;
                                    }
                                    n6 = n4 + n2;
                                    l3 = (l3 ^ 0x23E520181C0C6744L) - 0x133aca3b40dd06cdL;
                                    l4 = (l4 ^ 0xA3BF7B93758A06FBL) + 0x34319d942367300eL;
                                    continue;
                                }
                                if (n7 >= this.getCount2()) {
                                    long l5 = (l3 ^ 0x420DFD634F05379FL) - 0x7540196cf0aa4a83L;
                                    long l6 = (l4 ^ 0xD71A0519C2C8314FL) - 0x275359735854470dL;
                                    int n9 = n5;
                                    byte[] byArray3 = byArray2;
                                    int n10 = 0;
                                    byte[] byArray4 = this.byteFlagArray3;
                                    this.applyByteArrayByByteArray(byArray4, n10, byArray3, n9);
                                    l3 = (l5 ^ 0x97C1E1EAAD6B01C9L) - 0x138fe9b8927229ceL;
                                    l4 = (l6 ^ 0x573D716A33E04F02L) - 0x723db2ce9591bc8L;
                                    continue;
                                }
                                l3 = (l3 ^ 0x5D2EA3ABAE44F792L) + 0x98d5e7739627cabL;
                                l4 = (l4 ^ 0xC7CD27F13343B2F4L) - 0x600d888f89204957L;
                                continue;
                            }
                            n7 = 0;
                            l3 = (l3 ^ 0x79375BF9F8468C9CL) + 0x3caba2cd0bcae683L;
                            l4 = (l4 ^ 0x1EEBDA5962B8E871L) + 0x59428c2be42c2279L;
                            continue;
                        }
                        n4 += this.getCount2();
                        l3 = (l3 ^ 0x3D41D0FBA1EADB3FL) - 0x34762cf15325f6beL;
                        l4 = (l4 ^ 0x834C300C112B5E89L) - 0x5ff42faa052ddefcL;
                        continue;
                    }
                    l3 = (l3 ^ 0x1D3DB9E0B04C91DL) + 0x333bad3eac9899e2L;
                    l4 = (l4 ^ 0x7931E0A636F12CD7L) - 0x4fe63d262359a1c7L;
                }
                if (n4 >= n6) {
                    return n2;
                }
                l3 = (l3 ^ 0x7B9D817134A9A55CL) + 0x49bf3dceefed4898L;
                l4 = (l4 ^ 0x370342D7109EC1D1L) - 0x47459b47d7fce550L;
                continue;
            }
            this.byteFlagArray3[n7] = (byte)(byArray[n7 + n4] ^ this.byteFlagArray4[n7]);
            l3 = (l3 ^ 0x493013F3BFA4CB1FL) - 0x1ec0bdc8e0b0cc2aL;
            l4 = (l4 ^ 0x3354C8DA872A3C73L) + 0x191c59ef646956e4L;
        }
    }

    public byte[] resolveByteArray(byte[] byArray, int n, int n2) {
        byte[] byArray2 = byArray;
        int n3 = n2;
        int n4 = 0;
        long l = 0x10c755ac3813L;
        long l2 = ((long)n4 << 48 | l << 16 >>> 16) ^ 0x76D45F8373F7L ^ 0x33731FD74490L;
        if (n3 <= 0) {
            return null;
        }
        try {
            byte[] byArray3 = byArray2;
            byArray2 = this.resolveByteArray(byArray3, n, n3, false);
        }
        catch (IllegalBlockSizeException | ShortBufferException generalSecurityException) {
            throw new RuntimeException(generalSecurityException);
        }
        n3 = byArray2.length;
        byte[] byArray4 = new byte[n3];
        int n5 = this.getCount2();
        int n6 = n3;
        this.applyIndex(n6, n5);
        this.computeCount(byArray2, n, n3, byArray4, 0);
        if (byArray4.length % this.getCount2() != 0) {
            System.out.println("Writing " + byArray4.length);
        }
        return byArray4;
    }

    public void applyByteArrayByByteArray(byte[] byArray, int n, byte[] byArray2, int n2) {
        this.applyByteArray2(byArray, n, byArray2, n2);
    }

    public void applyByteArray(byte[] byArray, int n, int n2) throws ShortBufferException {
        int n3 = 0;
        byte by = 0;
        long l = 0x13846bc440ce0172L;
        long l2 = -0x7cb1b4f3646d8062L;
        while (true) {
            block12: {
                block6: {
                    block8: {
                        block9: {
                            block10: {
                                block11: {
                                    block5: {
                                        block7: {
                                            int n4;
                                            if ((n4 = (int)(l % 7L)) == 3) break block5;
                                            if (n4 == 2) break block6;
                                            if (n4 == 4) break block7;
                                            if (n4 == 1) break block8;
                                            if (n4 == 6) break block9;
                                            if (n4 == 0) break block10;
                                            break block11;
                                        }
                                        Arrays.fill(byArray, n, n3, by);
                                        break block9;
                                    }
                                    by = (byte)(n2 & 0xFF);
                                    l = (l ^ 0xE290A0FFE6D18DA1L) - 0x65578f39743c66a1L;
                                    l2 = (l2 ^ 0xC6DFB3EB238D85EAL) + 0x7e03a9030a22a3ebL;
                                    continue;
                                }
                                if (n3 <= byArray.length) {
                                    l = (l ^ 0xEAE6FE9312A4E3BL) - 0x1f571489b1d6964aL;
                                    l2 = (l2 ^ 0x89E726587F8F395L) + 0x7a26f2bc816bbbe9L;
                                    continue;
                                }
                                l = (l ^ 0xD9D62E0166997AE5L) + 0x77dacdd1e1c66e92L;
                                l2 = (l2 ^ 0x9623678FAB5E0961L) - 0x702940ac6b66e16cL;
                                continue;
                            }
                            throw new ShortBufferException("Buffer too small to hold padding");
                        }
                        return;
                    }
                    if (byArray != null) break block12;
                }
                return;
            }
            long l3 = (l ^ 0x1C872EB6AC2C59BEL) + 0x1be8cca5abb96202L;
            long l4 = (l2 ^ 0x32ABABC90D389C3CL) + 0xad7224c8db57bc2L;
            n3 = Math.addExact(n, n2);
            l = (l3 ^ 0x6D7BD43C4D723DE8L) + 0xb933f5591e43204L;
            l2 = (l4 ^ 0x3A066C194546336EL) - 0x31fa53bbcb0f5c07L;
        }
    }

    public CoreByteArrayEntry() {
        long l = 0x2888896d279L;
        long l2 = 0x76D45F8373F7L ^ l ^ 0x787A9C4A78C4L;
        this.enabled = false;
        this.enabled2 = false;
        this.countArray2 = null;
        this.countArray12 = null;
        this.count = 0;
        this.countArray9 = new int[256];
        this.countArray6 = new int[256];
        this.byteFlagArray2 = new byte[256];
        this.byteFlagArray = new byte[256];
        this.countArray16 = new int[256];
        this.countArray8 = new int[256];
        this.countArray14 = new int[256];
        this.countArray3 = new int[256];
        this.countArray15 = new int[256];
        this.countArray10 = new int[256];
        this.countArray11 = new int[256];
        this.countArray4 = new int[256];
        this.countArray5 = new int[256];
        this.countArray13 = new int[256];
        this.countArray = new int[256];
        this.countArray7 = new int[256];
        this.byteFlagArray5 = new byte[30];
        this.byteFlagArray3 = new byte[this.getCount2()];
        this.byteFlagArray4 = new byte[this.getCount2()];
        this.countArray9[0] = 1;
        for (int i = 1; i < 256; ++i) {
            int n = this.countArray9[i - 1] << 1 ^ this.countArray9[i - 1];
            if ((n & 0x100) != 0) {
                n ^= 0x11B;
            }
            this.countArray9[i] = n;
        }
        for (int i = 1; i < 255; ++i) {
            this.countArray6[this.countArray9[i]] = i;
        }
        byte[][] byArrayArray = new byte[][]{{1, 1, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 1, 1, 1}, {1, 1, 1, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 0, 0, 0, 1}};
        byte[] byArray = new byte[]{0, 1, 1, 0, 0, 0, 1, 1};
        byte[][] byArray2 = new byte[256][8];
        byArray2[1][7] = 1;
        for (int i = 2; i < 256; ++i) {
            int n = this.countArray9[255 - this.countArray6[i]];
            for (int j = 0; j < 8; ++j) {
                byArray2[i][j] = (byte)(n >>> 7 - j & 1);
            }
        }
        byte[][] byArray3 = new byte[256][8];
        for (int i = 0; i < 256; ++i) {
            for (int j = 0; j < 8; ++j) {
                byArray3[i][j] = byArray[j];
                for (int k = 0; k < 8; ++k) {
                    byte[] byArray4 = byArray3[i];
                    int n = j;
                    byArray4[n] = (byte)(byArray4[n] ^ byArrayArray[j][k] * byArray2[i][k]);
                }
            }
        }
        for (int i = 0; i < 256; ++i) {
            this.byteFlagArray2[i] = (byte)(byArray3[i][0] << 7);
            for (int j = 1; j < 8; ++j) {
                int n = i;
                this.byteFlagArray2[n] = (byte)(this.byteFlagArray2[n] ^ byArray3[i][j] << 7 - j);
            }
            this.byteFlagArray[this.byteFlagArray2[i] & 0xFF] = (byte)i;
        }
        byte[][] byArrayArray2 = new byte[][]{{2, 1, 1, 3}, {3, 2, 1, 1}, {1, 3, 2, 1}, {1, 1, 3, 2}};
        byte[][] byArray5 = new byte[4][8];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                byArray5[i][j] = byArrayArray2[i][j];
            }
            byArray5[i][i + 4] = 1;
        }
        byte[][] byArray6 = new byte[4][4];
        for (int i = 0; i < 4; ++i) {
            byte by = byArray5[i][i];
            if (by == 0) {
                int n = i + 1;
                while (byArray5[n][i] == 0) {
                    ++n;
                }
                for (int j = 0; j < 8; ++j) {
                    byte by2 = byArray5[i][j];
                    byArray5[i][j] = byArray5[n][j];
                    byArray5[n][j] = by2;
                }
                by = byArray5[i][i];
            }
            for (int j = 0; j < 8; ++j) {
                if (byArray5[i][j] == 0) continue;
                byArray5[i][j] = (byte)this.countArray9[(255 + this.countArray6[byArray5[i][j] & 0xFF] - this.countArray6[by & 0xFF]) % 255];
            }
            for (int j = 0; j < 4; ++j) {
                if (i == j) continue;
                for (int k = i + 1; k < 8; ++k) {
                    byte[] byArray7 = byArray5[j];
                    int n = k;
                    byte by3 = byArray5[j][i];
                    byte by4 = byArray5[i][k];
                    byArray7[n] = (byte)(byArray7[n] ^ this.computeCount((int)by4, by3));
                }
                byArray5[j][i] = 0;
            }
        }
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                byArray6[i][j] = byArray5[i][j + 4];
            }
        }
        for (int i = 0; i < 256; ++i) {
            byte by = this.byteFlagArray2[i];
            byte[] byArray8 = byArrayArray2[0];
            byte by5 = by;
            this.countArray16[i] = this.computeCount((int)by5, byArray8);
            byte[] byArray9 = byArrayArray2[1];
            byte by6 = by;
            this.countArray8[i] = this.computeCount((int)by6, byArray9);
            byte[] byArray10 = byArrayArray2[2];
            byte by7 = by;
            this.countArray14[i] = this.computeCount((int)by7, byArray10);
            byte[] byArray11 = byArrayArray2[3];
            byte by8 = by;
            this.countArray3[i] = this.computeCount((int)by8, byArray11);
            byte by9 = this.byteFlagArray[i];
            byte[] byArray12 = byArray6[0];
            byte by10 = by9;
            this.countArray15[i] = this.computeCount((int)by10, byArray12);
            byte[] byArray13 = byArray6[1];
            byte by11 = by9;
            this.countArray10[i] = this.computeCount((int)by11, byArray13);
            byte[] byArray14 = byArray6[2];
            byte by12 = by9;
            this.countArray11[i] = this.computeCount((int)by12, byArray14);
            byte[] byArray15 = byArray6[3];
            byte by13 = by9;
            this.countArray4[i] = this.computeCount((int)by13, byArray15);
            byte[] byArray16 = byArray6[0];
            int n = i;
            this.countArray5[i] = this.computeCount(n, byArray16);
            byte[] byArray17 = byArray6[1];
            int n2 = i;
            this.countArray13[i] = this.computeCount(n2, byArray17);
            byte[] byArray18 = byArray6[2];
            int n3 = i;
            this.countArray[i] = this.computeCount(n3, byArray18);
            byte[] byArray19 = byArray6[3];
            int n4 = i;
            this.countArray7[i] = this.computeCount(n4, byArray19);
        }
        this.byteFlagArray5[0] = 1;
        int n = 1;
        for (int i = 1; i < 30; ++i) {
            n = this.computeCount(2, n);
            this.byteFlagArray5[i] = (byte)n;
        }
        this.countArray6 = null;
        this.countArray9 = null;
    }
}

