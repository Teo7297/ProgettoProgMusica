/*

<This Java Class is part of the jMusic API version 1.5, March 2004.>

Copyright (C) 2000 Andrew Sorensen & Andrew Brown

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or any
later version.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

*/

package jm.constants;

/** An interface storing frequency constants.
  * 
  * @see jm.music.data.Note
  * @author Andrew Sorensen, Andrew Brown, Andrew Troedson, Adam Kirby
  */
public interface Frequencies {
    
        // Math options
        // A=440; FOR x = 0 to 127; MIDI(x) = (A / 32) * (2 ^ ((x - 9) / 12))
        // Hertz (number of vibrations a second) = 6.875 x 2 ^ ( ( 3 + MIDI_Pitch ) / 12 )

        double[] FRQ = {
                /* (0) CN1 */   8.1757989156,
                /* (1) CSN1*/   8.6619572180,
                /* (2) DN1*/    9.1770239974,
                /* (3) DSN1*/   9.7227182413,
                /* (4) EN1*/    10.3008611535,
                /* (5) FN1*/    10.9133822323,
                /* (6) FSN1*/   11.5623257097,
                /* (7) GN1*/    12.2498573744,
                /* (8) GSN1*/   12.9782717994,
                /* (9) AN1*/    13.7500000000,
                /* (10) ASN1*/  14.5676175474,
                /* (11) BN1*/   15.4338531643,
                /* (12) C0*/    16.3515978313,
                /* (13) CS0*/   17.3239144361,
                /* (14) D0*/    18.3540479948,
                /* (15) DS0*/   19.4454364826,
                /* (16) E0*/    20.6017223071,
                /* (17) F0*/    21.8267644646,
                /* (18) FS0*/   23.1246514195,
                /* (19) G0*/    24.4997147489,
                /* (20) GS0*/   25.9565435987,
                /* (21) A0 */   27.5000000000,
                /* (22) AS0 */  29.1352350949,
                /* (23) B0 */   30.8677063285,
                /* (24) C1 */   32.7031956626,
                /* (25) CS1 */  34.6478288721,
                /* (26) D1 */   36.7080959897,
                /* (27) DS1 */  38.8908729653,
                /* (28) E1 */   41.2034446141,
                /* (29) F1 */   43.6535289291,
                /* (30) FS1 */  46.2493028390,
                /* (31) G1 */   48.9994294977,
                /* (32) GS1 */  51.9130871975,
                /* (33) A1 */   55.0000000000,
                /* (34) AS1 */  58.2704701898,
                /* (35) B1 */   61.7354126570,
                /* (36) C2 */   65.4063913251,
                /* (37) CS2 */  69.2956577442,
                /* (38) D2 */   73.4161919794,
                /* (39) DS2 */  77.7817459305,
                /* (40) E2 */   82.4068892282,
                /* (41) F2 */   87.3070578583,
                /* (42) FS2 */  92.4986056779,
                /* (43) G2 */   97.9988589954,
                /* (44) GS2 */  103.8261743950,
                /* (45) A2 */   110.0000000000,
                /* (46) AS2 */  116.5409403795,
                /* (47) B2 */   123.4708253140,
                /* (48) C3 */   130.8127826503,
                /* (49) CS3 */  138.5913154884,
                /* (50) D3 */   146.8323839587,
                /* (51) DS3 */  155.5634918610,
                /* (52) E3 */   164.8137784564,
                /* (53) F3 */   174.6141157165,
                /* (54) FS3 */  184.9972113558,
                /* (55) G3 */   195.9977179909,
                /* (56) GS3 */  207.6523487900,
                /* (57) A3 */   220.0000000000,
                /* (58) AS3 */  233.0818807590,
                /* (59) B3 */   246.9416506281,
                /* (60) C4 */   261.6255653006,
                /* (61) CS4 */  277.1826309769,
                /* (62) D4 */   293.6647679174,
                /* (63) DS4 */  311.1269837221,
                /* (64) E4 */   329.6275569129,
                /* (65) F4 */   349.2282314330,
                /* (66) FS4 */  369.9944227116,
                /* (67) G4 */   391.9954359817,
                /* (68) GS4 */  415.3046975799,
                /* (69) A4 */   440.0000000000,
                /* (70) AS4 */  466.1637615181,
                /* (71) B4 */   493.8833012561,
                /* (72) C5 */   523.2511306012,
                /* (73) CS5 */  554.3652619537,
                /* (74) D5 */   587.3295358348,
                /* (75) DS5 */  622.2539674442,
                /* (76) E5 */   659.2551138257,
                /* (77) F5 */   698.4564628660,
                /* (78) FS5 */  739.9888454233,
                /* (79) G5 */   783.9908719635,
                /* (80) GS5 */  830.6093951599,
                /* (81) A5 */   880.0000000000,
                /* (82) AS5 */  932.3275230362,
                /* (83) B5 */   987.7666025122,
                /* (84) C6 */   1046.5022612024,
                /* (85) CS6 */  1108.7305239075,
                /* (86) D6 */   1174.6590716696,
                /* (87) DS6 */  1244.5079348883,
                /* (88) E6 */   1318.5102276515,
                /* (89) F6 */   1396.9129257320,
                /* (90) FS6 */  1479.9776908465,
                /* (91) G6 */   1567.9817439270,
                /* (92) GS6 */  1661.2187903198,
                /* (93) A6 */   1760.0000000000,
                /* (94) AS6 */  1864.6550460724,
                /* (95) B6 */   1975.5332050245,
                /* (96) C7 */   2093.0045224048,
                /* (97) CS7 */  2217.4610478150,
                /* (98) D7 */   2349.3181433393,
                /* (99) DS7 */  2489.0158697766,
                /* (100) E7 */  2637.0204553030,
                /* (101) F7 */  2793.8258514640,
                /* (102) FS7 */ 2959.9553816931,
                /* (103) G7 */  3135.9634878540,
                /* (104) GS7 */ 3322.4375806396,
                /* (105) A7 */  3520.0000000000,
                /* (106) AS7 */ 3729.3100921447,
                /* (107) B7 */  3951.0664100490,
                /* (108) C8 */  4186.0090448096, 
                /* (109) CS8 */ 4434.9220956300,
                /* (110) D8 */  4698.6362866785,
                /* (111) DS8 */ 4978.0317395533,
                /* (112) E8 */  5274.0409106059,
                /* (113) F8 */  5587.6517029281,
                /* (114) FS8 */ 5919.9107633862,
                /* (115) G8 */  6271.9269757080,
                /* (116) GS8 */ 6644.8751612791,
                /* (117 A8 */   7040.0000000000,
                /* (118 AS8 */  7458.6202347565,
                /* (119 B8 */   7902.1328346582,
                /* (120 C9 */   8372.0180896192,
                /* (121) CS9 */ 8869.8441912599,
                /* (122) D9 */  9397.2725733570,
                /* (123) DS9 */ 9956.0634791066,
                /* (124) E9 */  10548.0818212118,
                /* (125) F9 */  11175.3034058561,
                /* (126) FS9 */ 11839.8215267723,
                /* (127) G9 */  12543.8539514160
        };
}