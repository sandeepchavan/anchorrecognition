package StripeReader.core;

import java.util.Hashtable;

public interface Reader {

    /**
     * Locates and decodes a barcode in some format within an image.
     *
     * @param image image of barcode to decode
     * @return String which the barcode encodes
     * @throws ReaderException if the barcode cannot be located or decoded for any reason
     */
    Result decode(BinaryBitmap image) throws ReaderException;

    /**
     * Locates and decodes a barcode in some format within an image. This method also accepts
     * hints, each possibly associated to some data, which may help the implementation decode.
     *
     * @param image image of barcode to decode
     * @param hints passed as a {@link java.util.Hashtable} from {@link com.google.zxing.DecodeHintType}
     * to arbitrary data. The
     * meaning of the data depends upon the hint type. The implementation may or may not do
     * anything with these hints.
     * @return String which the barcode encodes
     * @throws ReaderException if the barcode cannot be located or decoded for any reason
     */
    Result decode(BinaryBitmap image, Hashtable hints) throws ReaderException;
}
