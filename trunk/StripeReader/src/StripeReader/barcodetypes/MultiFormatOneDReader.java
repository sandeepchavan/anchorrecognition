package StripeReader.barcodetypes;

import StripeReader.core.DecodeHintType;
import StripeReader.core.ReaderException;
import StripeReader.core.Result;
import StripeReader.core.BarcodeFormat;
import StripeReader.common.BitArray;

import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatOneDReader extends AbstractOneDReader {

    private final Vector readers;

    public MultiFormatOneDReader(Hashtable hints) {
        Vector possibleFormats = hints == null ? null : (Vector) hints.get(DecodeHintType.POSSIBLE_FORMATS);
        readers = new Vector();
        if (possibleFormats != null) {
            if (possibleFormats.contains(BarcodeFormat.EAN_13) ||
                    possibleFormats.contains(BarcodeFormat.UPC_A) ||
                    possibleFormats.contains(BarcodeFormat.EAN_8) ||
                    possibleFormats.contains(BarcodeFormat.UPC_E)) {
                readers.addElement(new MultiFormatUPCEANReader(hints));
            }
            if (possibleFormats.contains(BarcodeFormat.CODE_39)) {
                readers.addElement(new Code39Reader());
            }
            if (possibleFormats.contains(BarcodeFormat.CODE_128)) {
                readers.addElement(new Code128Reader());
            }
            if (possibleFormats.contains(BarcodeFormat.ITF)) {
                readers.addElement(new ITFReader());
            }
        }
        if (readers.isEmpty()) {
            readers.addElement(new MultiFormatUPCEANReader(hints));
            readers.addElement(new Code39Reader());
            readers.addElement(new Code128Reader());
            readers.addElement(new ITFReader());
        }
    }

    public Result decodeRow(int rowNumber, BitArray row, Hashtable hints) throws ReaderException {
        int size = readers.size();
        for (int i = 0; i < size; i++) {
            OneDReader reader = (OneDReader) readers.elementAt(i);
            try {
                return reader.decodeRow(rowNumber, row, hints);
            } catch (Exception re) {
                // continue
            }
        }

        throw ReaderException.getInstance();
    }
}
