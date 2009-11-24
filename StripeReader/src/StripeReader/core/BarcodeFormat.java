package StripeReader.core;

import java.util.Hashtable;

public final class BarcodeFormat {

    // No, we can't use an enum here. J2ME doesn't support it.
    private static final Hashtable VALUES = new Hashtable();
    /** QR Code 2D barcode format. */
    public static final BarcodeFormat QR_CODE = new BarcodeFormat("QR_CODE");
    /** DataMatrix 2D barcode format. */
    public static final BarcodeFormat DATAMATRIX = new BarcodeFormat("DATAMATRIX");
    /** UPC-E 1D format. */
    public static final BarcodeFormat UPC_E = new BarcodeFormat("UPC_E");
    /** UPC-A 1D format. */
    public static final BarcodeFormat UPC_A = new BarcodeFormat("UPC_A");
    /** EAN-8 1D format. */
    public static final BarcodeFormat EAN_8 = new BarcodeFormat("EAN_8");
    /** EAN-13 1D format. */
    public static final BarcodeFormat EAN_13 = new BarcodeFormat("EAN_13");
    /** Code 128 1D format. */
    public static final BarcodeFormat CODE_128 = new BarcodeFormat("CODE_128");
    /** Code 39 1D format. */
    public static final BarcodeFormat CODE_39 = new BarcodeFormat("CODE_39");
    /** ITF (Interleaved Two of Five) 1D format. */
    public static final BarcodeFormat ITF = new BarcodeFormat("ITF");
    /** PDF417 format. */
    public static final BarcodeFormat PDF417 = new BarcodeFormat("PDF417");
    private final String name;

    private BarcodeFormat(String name) {
        this.name = name;
        VALUES.put(name, this);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public static BarcodeFormat valueOf(String name) {
        BarcodeFormat format = (BarcodeFormat) VALUES.get(name);
        if (format == null) {
            throw new IllegalArgumentException();
        }
        return format;
    }
}
