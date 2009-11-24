package StripeReader.barcodetypes;

import StripeReader.core.Writer;

public interface UPCEANWriter extends Writer {

    /** @return a byte array of horizontal pixels (0 = white, 1 = black) */
    byte[] encode(String contents);
}
