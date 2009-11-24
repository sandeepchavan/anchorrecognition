package StripeReader.core;

public final class DecodeHintType {

    // No, we can't use an enum here. J2ME doesn't support it.
    /**
     * Unspecified, application-specific hint. Maps to an unspecified {@link Object}.
     */
    public static final DecodeHintType OTHER = new DecodeHintType();
    /**
     * Image is a pure monochrome image of a barcode. Doesn't matter what it maps to;
     * use {@link Boolean#TRUE}.
     */
    public static final DecodeHintType PURE_BARCODE = new DecodeHintType();
    /**
     * Image is known to be of one of a few possible formats.
     * Maps to a {@link java.util.Vector} of {@link BarcodeFormat}s.
     */
    public static final DecodeHintType POSSIBLE_FORMATS = new DecodeHintType();
    /**
     * Spend more time to try to find a barcode; optimize for accuracy, not speed.
     * Doesn't matter what it maps to; use {@link Boolean#TRUE}.
     */
    public static final DecodeHintType TRY_HARDER = new DecodeHintType();
    /**
     * Allowed lengths of encoded data -- reject anything else. Maps to an int[].
     */
    public static final DecodeHintType ALLOWED_LENGTHS = new DecodeHintType();

    private DecodeHintType() {
    }
}
