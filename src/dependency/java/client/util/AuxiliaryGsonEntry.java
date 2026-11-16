package client.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AuxiliaryGsonEntry {
   public static final Gson gson = new GsonBuilder().registerTypeAdapter(AuxiliaryGsonEntry.class, new ExtendedJsonDeserializerAdapter()).create();
   public float factor8 = 684.412F;
   public float factor9 = 684.412F;
   public float factor11 = 512.0F;
   public float factor12 = 512.0F;
   public float factor4 = 200.0F;
   public float factor5 = 200.0F;
   public float factor6 = 0.5F;
   public float factor = 80.0F;
   public float factor2 = 160.0F;
   public float factor3 = 80.0F;
   public float factor7 = 8.5F;
   public float factor10 = 12.0F;
   public float factor13 = 1.0F;
   public float factor14 = 0.0F;
   public float factor15 = 1.0F;
   public float factor16 = 0.0F;
   public int count4 = 63;
   public boolean enabled = true;
   public boolean enabled2 = true;
   public int count = 8;
   public boolean enabled3 = true;
   public boolean enabled4 = true;
   public boolean enabled5 = true;
   public boolean enabled6 = true;
   public boolean enabled11 = true;
   public boolean enabled7 = true;
   public boolean enabled8 = true;
   public int count2 = 4;
   public boolean enabled9 = true;
   public int count3 = 80;
   public boolean enabled10 = false;
   public int count5 = -1;
   public int count6 = 4;
   public int count7 = 4;
   public int count8 = 33;
   public int count9 = 10;
   public int count10 = 0;
   public int count11 = 256;
   public int count12 = 33;
   public int count13 = 8;
   public int count14 = 0;
   public int count15 = 256;
   public int count16 = 33;
   public int count17 = 10;
   public int count18 = 0;
   public int count19 = 80;
   public int count20 = 33;
   public int count21 = 10;
   public int count22 = 0;
   public int count23 = 80;
   public int count24 = 33;
   public int count25 = 10;
   public int count26 = 0;
   public int count27 = 80;
   public int count28 = 17;
   public int count29 = 20;
   public int count30 = 0;
   public int count31 = 128;
   public int count32 = 9;
   public int count33 = 20;
   public int count34 = 0;
   public int count35 = 64;
   public int count36 = 9;
   public int count37 = 2;
   public int count38 = 0;
   public int count39 = 32;
   public int count40 = 8;
   public int count41 = 8;
   public int count42 = 0;
   public int count43 = 16;
   public int count44 = 8;
   public int count45 = 1;
   public int count46 = 0;
   public int count47 = 16;
   public int count48 = 7;
   public int count49 = 1;
   public int count50 = 16;
   public int count51 = 16;

   public AuxiliaryGsonEntry() {
      this.updateState();
   }

   public GlobalFloatEntry getGlobalFloatEntry() {
      return new GlobalFloatEntry(this, null);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         AuxiliaryGsonEntry auxiliaryGsonEntry = (AuxiliaryGsonEntry)other;
         return this.count25 != auxiliaryGsonEntry.count25
            ? false
            : (
               this.count27 != auxiliaryGsonEntry.count27
                  ? false
                  : (
                     this.count26 != auxiliaryGsonEntry.count26
                        ? false
                        : (
                           this.count24 != auxiliaryGsonEntry.count24
                              ? false
                              : (
                                 Float.compare(auxiliaryGsonEntry.factor7, this.factor7) != 0
                                    ? false
                                    : (
                                       Float.compare(auxiliaryGsonEntry.factor14, this.factor14) != 0
                                          ? false
                                          : (
                                             Float.compare(auxiliaryGsonEntry.factor13, this.factor13) != 0
                                                ? false
                                                : (
                                                   Float.compare(auxiliaryGsonEntry.factor16, this.factor16) != 0
                                                      ? false
                                                      : (
                                                         Float.compare(auxiliaryGsonEntry.factor15, this.factor15) != 0
                                                            ? false
                                                            : (
                                                               this.count6 != auxiliaryGsonEntry.count6
                                                                  ? false
                                                                  : (
                                                                     this.count29 != auxiliaryGsonEntry.count29
                                                                        ? false
                                                                        : (
                                                                           this.count31 != auxiliaryGsonEntry.count31
                                                                              ? false
                                                                              : (
                                                                                 this.count30 != auxiliaryGsonEntry.count30
                                                                                    ? false
                                                                                    : (
                                                                                       this.count28 != auxiliaryGsonEntry.count28
                                                                                          ? false
                                                                                          : (
                                                                                             Float.compare(auxiliaryGsonEntry.factor8, this.factor8) != 0
                                                                                                ? false
                                                                                                : (
                                                                                                   Float.compare(auxiliaryGsonEntry.factor6, this.factor6) != 0
                                                                                                      ? false
                                                                                                      : (
                                                                                                         Float.compare(auxiliaryGsonEntry.factor4, this.factor4) != 0
                                                                                                            ? false
                                                                                                            : (
                                                                                                               Float.compare(auxiliaryGsonEntry.factor5, this.factor5) != 0
                                                                                                                  ? false
                                                                                                                  : (
                                                                                                                     this.count45 != auxiliaryGsonEntry.count45
                                                                                                                        ? false
                                                                                                                        : (
                                                                                                                           this.count47 != auxiliaryGsonEntry.count47
                                                                                                                              ? false
                                                                                                                              : (
                                                                                                                                 this.count46 != auxiliaryGsonEntry.count46
                                                                                                                                    ? false
                                                                                                                                    : (
                                                                                                                                       this.count44
                                                                                                                                             != auxiliaryGsonEntry.count44
                                                                                                                                          ? false
                                                                                                                                          : (
                                                                                                                                             this.count21
                                                                                                                                                   != auxiliaryGsonEntry.count21
                                                                                                                                                ? false
                                                                                                                                                : (
                                                                                                                                                   this.count23
                                                                                                                                                         != auxiliaryGsonEntry.count23
                                                                                                                                                      ? false
                                                                                                                                                      : (
                                                                                                                                                         this.count22
                                                                                                                                                               != auxiliaryGsonEntry.count22
                                                                                                                                                            ? false
                                                                                                                                                            : (
                                                                                                                                                               this.count20
                                                                                                                                                                     != auxiliaryGsonEntry.count20
                                                                                                                                                                  ? false
                                                                                                                                                                  : (
                                                                                                                                                                     this.count9
                                                                                                                                                                           != auxiliaryGsonEntry.count9
                                                                                                                                                                        ? false
                                                                                                                                                                        : (
                                                                                                                                                                           this.count11
                                                                                                                                                                                 != auxiliaryGsonEntry.count11
                                                                                                                                                                              ? false
                                                                                                                                                                              : (
                                                                                                                                                                                 this.count10
                                                                                                                                                                                       != auxiliaryGsonEntry.count10
                                                                                                                                                                                    ? false
                                                                                                                                                                                    : (
                                                                                                                                                                                       this.count8
                                                                                                                                                                                             != auxiliaryGsonEntry.count8
                                                                                                                                                                                          ? false
                                                                                                                                                                                          : (
                                                                                                                                                                                             this.count
                                                                                                                                                                                                   != auxiliaryGsonEntry.count
                                                                                                                                                                                                ? false
                                                                                                                                                                                                : (
                                                                                                                                                                                                   this.count5
                                                                                                                                                                                                         != auxiliaryGsonEntry.count5
                                                                                                                                                                                                      ? false
                                                                                                                                                                                                      : (
                                                                                                                                                                                                         this.count37
                                                                                                                                                                                                               != auxiliaryGsonEntry.count37
                                                                                                                                                                                                            ? false
                                                                                                                                                                                                            : (
                                                                                                                                                                                                               this.count39
                                                                                                                                                                                                                     != auxiliaryGsonEntry.count39
                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                     this.count38
                                                                                                                                                                                                                           != auxiliaryGsonEntry.count38
                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                           this.count36
                                                                                                                                                                                                                                 != auxiliaryGsonEntry.count36
                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                 this.count17
                                                                                                                                                                                                                                       != auxiliaryGsonEntry.count17
                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                       this.count19
                                                                                                                                                                                                                                             != auxiliaryGsonEntry.count19
                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                             this.count18
                                                                                                                                                                                                                                                   != auxiliaryGsonEntry.count18
                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                   this.count16
                                                                                                                                                                                                                                                         != auxiliaryGsonEntry.count16
                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                         this.count13
                                                                                                                                                                                                                                                               != auxiliaryGsonEntry.count13
                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                               this.count15
                                                                                                                                                                                                                                                                     != auxiliaryGsonEntry.count15
                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                     this.count14
                                                                                                                                                                                                                                                                           != auxiliaryGsonEntry.count14
                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                           this.count12
                                                                                                                                                                                                                                                                                 != auxiliaryGsonEntry.count12
                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                 Float.compare(
                                                                                                                                                                                                                                                                                          auxiliaryGsonEntry.factor9,
                                                                                                                                                                                                                                                                                          this.factor9
                                                                                                                                                                                                                                                                                       )
                                                                                                                                                                                                                                                                                       != 0
                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                                                                       this.count33
                                                                                                                                                                                                                                                                                             != auxiliaryGsonEntry.count33
                                                                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                                                                             this.count35
                                                                                                                                                                                                                                                                                                   != auxiliaryGsonEntry.count35
                                                                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                                                                   this.count34
                                                                                                                                                                                                                                                                                                         != auxiliaryGsonEntry.count34
                                                                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                                                                         this.count32
                                                                                                                                                                                                                                                                                                               != auxiliaryGsonEntry.count32
                                                                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                                                                               this.count50
                                                                                                                                                                                                                                                                                                                     != auxiliaryGsonEntry.count50
                                                                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                                                                     this.count49
                                                                                                                                                                                                                                                                                                                           != auxiliaryGsonEntry.count49
                                                                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                                                                           this.count48
                                                                                                                                                                                                                                                                                                                                 != auxiliaryGsonEntry.count48
                                                                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                                                                 this.count51
                                                                                                                                                                                                                                                                                                                                       != auxiliaryGsonEntry.count51
                                                                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                                                                                                                       this.count3
                                                                                                                                                                                                                                                                                                                                             != auxiliaryGsonEntry.count3
                                                                                                                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                                                                                                                             Float.compare(
                                                                                                                                                                                                                                                                                                                                                      auxiliaryGsonEntry.factor12,
                                                                                                                                                                                                                                                                                                                                                      this.factor12
                                                                                                                                                                                                                                                                                                                                                   )
                                                                                                                                                                                                                                                                                                                                                   != 0
                                                                                                                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                                                                                                                   Float.compare(
                                                                                                                                                                                                                                                                                                                                                            auxiliaryGsonEntry.factor,
                                                                                                                                                                                                                                                                                                                                                            this.factor
                                                                                                                                                                                                                                                                                                                                                         )
                                                                                                                                                                                                                                                                                                                                                         != 0
                                                                                                                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                                                                                                                         Float.compare(
                                                                                                                                                                                                                                                                                                                                                                  auxiliaryGsonEntry.factor2,
                                                                                                                                                                                                                                                                                                                                                                  this.factor2
                                                                                                                                                                                                                                                                                                                                                               )
                                                                                                                                                                                                                                                                                                                                                               != 0
                                                                                                                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                                                                                                                               Float.compare(
                                                                                                                                                                                                                                                                                                                                                                        auxiliaryGsonEntry.factor3,
                                                                                                                                                                                                                                                                                                                                                                        this.factor3
                                                                                                                                                                                                                                                                                                                                                                     )
                                                                                                                                                                                                                                                                                                                                                                     != 0
                                                                                                                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                                                                                                                     this.count41
                                                                                                                                                                                                                                                                                                                                                                           != auxiliaryGsonEntry.count41
                                                                                                                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                                                                                                                           this.count43
                                                                                                                                                                                                                                                                                                                                                                                 != auxiliaryGsonEntry.count43
                                                                                                                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                                                                                                                 this.count42
                                                                                                                                                                                                                                                                                                                                                                                       != auxiliaryGsonEntry.count42
                                                                                                                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                                                                                                                                                                       this.count40
                                                                                                                                                                                                                                                                                                                                                                                             != auxiliaryGsonEntry.count40
                                                                                                                                                                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                                                                                                                                                                             this.count7
                                                                                                                                                                                                                                                                                                                                                                                                   != auxiliaryGsonEntry.count7
                                                                                                                                                                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                                                                                                                                                                   this.count4
                                                                                                                                                                                                                                                                                                                                                                                                         != auxiliaryGsonEntry.count4
                                                                                                                                                                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                                                                                                                                                                         Float.compare(
                                                                                                                                                                                                                                                                                                                                                                                                                  auxiliaryGsonEntry.factor10,
                                                                                                                                                                                                                                                                                                                                                                                                                  this.factor10
                                                                                                                                                                                                                                                                                                                                                                                                               )
                                                                                                                                                                                                                                                                                                                                                                                                               != 0
                                                                                                                                                                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                                                                                                                                                                               Float.compare(
                                                                                                                                                                                                                                                                                                                                                                                                                        auxiliaryGsonEntry.factor11,
                                                                                                                                                                                                                                                                                                                                                                                                                        this.factor11
                                                                                                                                                                                                                                                                                                                                                                                                                     )
                                                                                                                                                                                                                                                                                                                                                                                                                     != 0
                                                                                                                                                                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                                                                                                                                                                     this.enabled
                                                                                                                                                                                                                                                                                                                                                                                                                           != auxiliaryGsonEntry.enabled
                                                                                                                                                                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                                                                                                                                                                           this.enabled2
                                                                                                                                                                                                                                                                                                                                                                                                                                 != auxiliaryGsonEntry.enabled2
                                                                                                                                                                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                                                                                                                                                                 this.enabled9
                                                                                                                                                                                                                                                                                                                                                                                                                                       != auxiliaryGsonEntry.enabled9
                                                                                                                                                                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                                                                                                                                                                                                                       this.enabled10
                                                                                                                                                                                                                                                                                                                                                                                                                                             != auxiliaryGsonEntry.enabled10
                                                                                                                                                                                                                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                                                                                                                                                                                                                             this.enabled5
                                                                                                                                                                                                                                                                                                                                                                                                                                                   != auxiliaryGsonEntry.enabled5
                                                                                                                                                                                                                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.enabled7
                                                                                                                                                                                                                                                                                                                                                                                                                                                         != auxiliaryGsonEntry.enabled7
                                                                                                                                                                                                                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.enabled3
                                                                                                                                                                                                                                                                                                                                                                                                                                                               != auxiliaryGsonEntry.enabled3
                                                                                                                                                                                                                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                               this.enabled6
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     != auxiliaryGsonEntry.enabled6
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     this.enabled11
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           != auxiliaryGsonEntry.enabled11
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           this.enabled4
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 != auxiliaryGsonEntry.enabled4
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 this.enabled8
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       != auxiliaryGsonEntry.enabled8
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    : this.count2
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       == auxiliaryGsonEntry.count2
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                              )
                                                                                                                                                                                                                        )
                                                                                                                                                                                                                  )
                                                                                                                                                                                                            )
                                                                                                                                                                                                      )
                                                                                                                                                                                                )
                                                                                                                                                                                          )
                                                                                                                                                                                    )
                                                                                                                                                                              )
                                                                                                                                                                        )
                                                                                                                                                                  )
                                                                                                                                                            )
                                                                                                                                                      )
                                                                                                                                                )
                                                                                                                                          )
                                                                                                                                    )
                                                                                                                              )
                                                                                                                        )
                                                                                                                  )
                                                                                                            )
                                                                                                      )
                                                                                                )
                                                                                          )
                                                                                    )
                                                                              )
                                                                        )
                                                                  )
                                                            )
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
            );
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return gson.toJson(this);
   }

   public static AuxiliaryGsonEntry resolveAuxiliaryGsonEntry(String text) {
      if (text.length() == 0) {
         return new AuxiliaryGsonEntry();
      } else {
         try {
            return (AuxiliaryGsonEntry)gson.fromJson(text, AuxiliaryGsonEntry.class);
         } catch (Exception exception) {
            return new AuxiliaryGsonEntry();
         }
      }
   }

   @Override
   public int hashCode() {
      int count52 = this.factor8 != 0.0F ? Float.floatToIntBits(this.factor8) : 0;
      int count53 = 31 * count52 + (this.factor9 != 0.0F ? Float.floatToIntBits(this.factor9) : 0);
      int count54 = 31 * count53 + (this.factor11 != 0.0F ? Float.floatToIntBits(this.factor11) : 0);
      int count55 = 31 * count54 + (this.factor12 != 0.0F ? Float.floatToIntBits(this.factor12) : 0);
      int count56 = 31 * count55 + (this.factor4 != 0.0F ? Float.floatToIntBits(this.factor4) : 0);
      int count57 = 31 * count56 + (this.factor5 != 0.0F ? Float.floatToIntBits(this.factor5) : 0);
      int count58 = 31 * count57 + (this.factor6 != 0.0F ? Float.floatToIntBits(this.factor6) : 0);
      int count59 = 31 * count58 + (this.factor != 0.0F ? Float.floatToIntBits(this.factor) : 0);
      int count60 = 31 * count59 + (this.factor2 != 0.0F ? Float.floatToIntBits(this.factor2) : 0);
      int count61 = 31 * count60 + (this.factor3 != 0.0F ? Float.floatToIntBits(this.factor3) : 0);
      int count62 = 31 * count61 + (this.factor7 != 0.0F ? Float.floatToIntBits(this.factor7) : 0);
      int count63 = 31 * count62 + (this.factor10 != 0.0F ? Float.floatToIntBits(this.factor10) : 0);
      int count64 = 31 * count63 + (this.factor13 != 0.0F ? Float.floatToIntBits(this.factor13) : 0);
      int count65 = 31 * count64 + (this.factor14 != 0.0F ? Float.floatToIntBits(this.factor14) : 0);
      int count66 = 31 * count65 + (this.factor15 != 0.0F ? Float.floatToIntBits(this.factor15) : 0);
      int count67 = 31 * count66 + (this.factor16 != 0.0F ? Float.floatToIntBits(this.factor16) : 0);
      int count68 = 31 * count67 + this.count4;
      int count69 = 31 * count68 + (this.enabled ? 1 : 0);
      int count70 = 31 * count69 + (this.enabled2 ? 1 : 0);
      int count71 = 31 * count70 + this.count;
      int count72 = 31 * count71 + (this.enabled3 ? 1 : 0);
      int count73 = 31 * count72 + (this.enabled4 ? 1 : 0);
      int count74 = 31 * count73 + (this.enabled5 ? 1 : 0);
      int count75 = 31 * count74 + (this.enabled6 ? 1 : 0);
      int count76 = 31 * count75 + (this.enabled11 ? 1 : 0);
      int count77 = 31 * count76 + (this.enabled7 ? 1 : 0);
      int count78 = 31 * count77 + (this.enabled8 ? 1 : 0);
      int count79 = 31 * count78 + this.count2;
      int count80 = 31 * count79 + (this.enabled9 ? 1 : 0);
      int count81 = 31 * count80 + this.count3;
      int count82 = 31 * count81 + (this.enabled10 ? 1 : 0);
      int count83 = 31 * count82 + this.count5;
      int count84 = 31 * count83 + this.count6;
      int count85 = 31 * count84 + this.count7;
      int count86 = 31 * count85 + this.count8;
      int count87 = 31 * count86 + this.count9;
      int count88 = 31 * count87 + this.count10;
      int count89 = 31 * count88 + this.count11;
      int count90 = 31 * count89 + this.count12;
      int count91 = 31 * count90 + this.count13;
      int count92 = 31 * count91 + this.count14;
      int count93 = 31 * count92 + this.count15;
      int count94 = 31 * count93 + this.count16;
      int count95 = 31 * count94 + this.count17;
      int count96 = 31 * count95 + this.count18;
      int count97 = 31 * count96 + this.count19;
      int count98 = 31 * count97 + this.count20;
      int count99 = 31 * count98 + this.count21;
      int count100 = 31 * count99 + this.count22;
      int count101 = 31 * count100 + this.count23;
      int count102 = 31 * count101 + this.count24;
      int count103 = 31 * count102 + this.count25;
      int count104 = 31 * count103 + this.count26;
      int count105 = 31 * count104 + this.count27;
      int count106 = 31 * count105 + this.count28;
      int count107 = 31 * count106 + this.count29;
      int count108 = 31 * count107 + this.count30;
      int count109 = 31 * count108 + this.count31;
      int count110 = 31 * count109 + this.count32;
      int count111 = 31 * count110 + this.count33;
      int count112 = 31 * count111 + this.count34;
      int count113 = 31 * count112 + this.count35;
      int count114 = 31 * count113 + this.count36;
      int count115 = 31 * count114 + this.count37;
      int count116 = 31 * count115 + this.count38;
      int count117 = 31 * count116 + this.count39;
      int count118 = 31 * count117 + this.count40;
      int count119 = 31 * count118 + this.count41;
      int count120 = 31 * count119 + this.count42;
      int count121 = 31 * count120 + this.count43;
      int count122 = 31 * count121 + this.count44;
      int count123 = 31 * count122 + this.count45;
      int count124 = 31 * count123 + this.count46;
      int count125 = 31 * count124 + this.count47;
      int count126 = 31 * count125 + this.count48;
      int count127 = 31 * count126 + this.count49;
      int count128 = 31 * count127 + this.count50;
      return 31 * count128 + this.count51;
   }

   public void updateState() {
      this.factor8 = 684.412F;
      this.factor9 = 684.412F;
      this.factor11 = 512.0F;
      this.factor12 = 512.0F;
      this.factor4 = 200.0F;
      this.factor5 = 200.0F;
      this.factor6 = 0.5F;
      this.factor = 80.0F;
      this.factor2 = 160.0F;
      this.factor3 = 80.0F;
      this.factor7 = 8.5F;
      this.factor10 = 12.0F;
      this.factor13 = 1.0F;
      this.factor14 = 0.0F;
      this.factor15 = 1.0F;
      this.factor16 = 0.0F;
      this.count4 = 63;
      this.enabled = true;
      this.enabled2 = true;
      this.count = 8;
      this.enabled3 = true;
      this.enabled4 = true;
      this.enabled5 = true;
      this.enabled6 = true;
      this.enabled11 = true;
      this.enabled7 = true;
      this.enabled8 = true;
      this.count2 = 4;
      this.enabled9 = true;
      this.count3 = 80;
      this.enabled10 = false;
      this.count5 = -1;
      this.count6 = 4;
      this.count7 = 4;
      this.count8 = 33;
      this.count9 = 10;
      this.count10 = 0;
      this.count11 = 256;
      this.count12 = 33;
      this.count13 = 8;
      this.count14 = 0;
      this.count15 = 256;
      this.count16 = 33;
      this.count17 = 10;
      this.count18 = 0;
      this.count19 = 80;
      this.count20 = 33;
      this.count21 = 10;
      this.count22 = 0;
      this.count23 = 80;
      this.count24 = 33;
      this.count25 = 10;
      this.count26 = 0;
      this.count27 = 80;
      this.count28 = 17;
      this.count29 = 20;
      this.count30 = 0;
      this.count31 = 128;
      this.count32 = 9;
      this.count33 = 20;
      this.count34 = 0;
      this.count35 = 64;
      this.count36 = 9;
      this.count37 = 2;
      this.count38 = 0;
      this.count39 = 32;
      this.count40 = 8;
      this.count41 = 8;
      this.count42 = 0;
      this.count43 = 16;
      this.count44 = 8;
      this.count45 = 1;
      this.count46 = 0;
      this.count47 = 16;
      this.count48 = 7;
      this.count49 = 1;
      this.count50 = 16;
      this.count51 = 16;
   }
}
