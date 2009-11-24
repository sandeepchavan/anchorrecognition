package StripeReader.barcodetypes;

import StripeReader.core.Reader;
import StripeReader.core.ReaderException;
import StripeReader.core.Result;
import StripeReader.common.BitArray;

import java.util.Hashtable;

public interface OneDReader extends Reader {

    /**
     * <p>Attempts to decode a one-dimensional barcode format given a single row of
     * an image.</p>
     *
     * @param rowNumber row number from top of the row
     * @param row the black/white pixel data of the row
     * @param hints decode hints
     * @return {@link Result} containing encoded string and start/end of barcode
     * @throws ReaderException if an error occurs or barcode cannot be found
     */
    Result decodeRow(int rowNumber, BitArray row, Hashtable hints) throws ReaderException;
}
