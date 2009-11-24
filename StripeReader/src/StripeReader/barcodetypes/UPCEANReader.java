package StripeReader.barcodetypes;

import StripeReader.core.ReaderException;
import StripeReader.core.Result;
import StripeReader.common.BitArray;

public interface UPCEANReader extends OneDReader {

    /**
     * <p>Like {@link #decodeRow(int, BitArray, java.util.Hashtable)}, but
     * allows caller to inform method about where the UPC/EAN start pattern is
     * found. This allows this to be computed once and reused across many implementations.</p>
     */
    Result decodeRow(int rowNumber, BitArray row, int[] startGuardRange) throws ReaderException;
}
