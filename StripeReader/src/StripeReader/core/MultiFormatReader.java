package StripeReader.core;

import StripeReader.barcodetypes.MultiFormatOneDReader;
import StripeReader.barcodetypes.pdf417.PDF417Reader;
import StripeReader.barcodetypes.qrcode.QRCodeReader;
import StripeReader.barcodetypes.datamatrix.DataMatrixReader;

import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatReader implements Reader {

    private Hashtable hints;
    private Vector readers;

    /**
     * This version of decode honors the intent of Reader.decode(BinaryBitmap) in that it
     * passes null as a hint to the decoders. However, that makes it inefficient to call repeatedly.
     * Use setHints() followed by decodeWithState() for continuous scan applications.
     *
     * @param image The pixel data to decode
     * @return The contents of the image
     * @throws ReaderException Any errors which occurred
     */
    public Result decode(BinaryBitmap image) throws ReaderException {
        setHints(null);
        return decodeInternal(image);
    }

    /**
     * Decode an image using the hints provided. Does not honor existing state.
     *
     * @param image The pixel data to decode
     * @param hints The hints to use, clearing the previous state.
     * @return The contents of the image
     * @throws ReaderException Any errors which occurred
     */
    public Result decode(BinaryBitmap image, Hashtable hints) throws ReaderException {
        setHints(hints);
        return decodeInternal(image);
    }

    /**
     * Decode an image using the state set up by calling setHints() previously. Continuous scan
     * clients will get a <b>large</b> speed increase by using this instead of decode().
     *
     * @param image The pixel data to decode
     * @return The contents of the image
     * @throws ReaderException Any errors which occurred
     */
    public Result decodeWithState(BinaryBitmap image) throws ReaderException {
        // Make sure to set up the default state so we don't crash
        if (readers == null) {
            setHints(null);
        }
        return decodeInternal(image);
    }

    /**
     * This method adds state to the MultiFormatReader. By setting the hints once, subsequent calls
     * to decodeWithState(image) can reuse the same set of readers without reallocating memory. This
     * is important for performance in continuous scan clients.
     *
     * @param hints The set of hints to use for subsequent calls to decode(image)
     */
    public void setHints(Hashtable hints) {
        this.hints = hints;

        boolean tryHarder = hints != null && hints.containsKey(DecodeHintType.TRY_HARDER);
        Vector formats = hints == null ? null : (Vector) hints.get(DecodeHintType.POSSIBLE_FORMATS);
        readers = new Vector();
        if (formats != null) {
            boolean addOneDReader =
                    formats.contains(BarcodeFormat.UPC_A) ||
                    formats.contains(BarcodeFormat.UPC_E) ||
                    formats.contains(BarcodeFormat.EAN_13) ||
                    formats.contains(BarcodeFormat.EAN_8) ||
                    formats.contains(BarcodeFormat.CODE_39) ||
                    formats.contains(BarcodeFormat.CODE_128) ||
                    formats.contains(BarcodeFormat.ITF);
            // Put 1D readers upfront in "normal" mode
            if (addOneDReader && !tryHarder) {
                readers.addElement(new MultiFormatOneDReader(hints));
            }
            if (formats.contains(BarcodeFormat.QR_CODE)) {
                readers.addElement(new QRCodeReader());
            }
            if (formats.contains(BarcodeFormat.DATAMATRIX)) {
                readers.addElement(new DataMatrixReader());
            }
            if (formats.contains(BarcodeFormat.PDF417)) {
                readers.addElement(new PDF417Reader());
            }
            // At end in "try harder" mode
            if (addOneDReader && tryHarder) {
                readers.addElement(new MultiFormatOneDReader(hints));
            }
        }
        if (readers.isEmpty()) {
            if (!tryHarder) {
                readers.addElement(new MultiFormatOneDReader(hints));
            }
            readers.addElement(new QRCodeReader());

            // TODO re-enable once Data Matrix is ready
            // readers.addElement(new DataMatrixReader());

            // TODO: Enable once PDF417 has passed QA
            //readers.addElement(new PDF417Reader());

            if (tryHarder) {
                readers.addElement(new MultiFormatOneDReader(hints));
            }
        }
    }

    private Result decodeInternal(BinaryBitmap image) throws ReaderException {
        int size = readers.size();
        for (int i = 0; i < size; i++) {
            Reader reader = (Reader) readers.elementAt(i);
            try {
                return reader.decode(image, hints);
            } catch (ReaderException re) {
                // continue
            }
        }

        throw ReaderException.getInstance();
    }
}
