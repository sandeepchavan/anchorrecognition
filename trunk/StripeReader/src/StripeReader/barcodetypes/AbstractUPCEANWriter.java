package StripeReader.barcodetypes;

import java.util.Hashtable;

import StripeReader.core.BarcodeFormat;
import StripeReader.core.WriterException;
import StripeReader.common.ByteMatrix;

public abstract class AbstractUPCEANWriter implements UPCEANWriter {

    public ByteMatrix encode(String contents, BarcodeFormat format, int width, int height)
            throws WriterException {
        return encode(contents, format, width, height, null);
    }

    public ByteMatrix encode(String contents, BarcodeFormat format, int width, int height,
            Hashtable hints) throws WriterException {
        if (contents == null || contents.length() == 0) {
            throw new IllegalArgumentException("Found empty contents");
        }

        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + width + 'x' + height);
        }

        byte[] code = encode(contents);
        return renderResult(code, width, height);
    }

    /** @return a byte array of horizontal pixels (0 = white, 1 = black) */
    private static ByteMatrix renderResult(byte[] code, int width, int height) {
        int inputWidth = code.length;
        // Add quiet zone on both sides
        int fullWidth = inputWidth + (AbstractUPCEANReader.START_END_PATTERN.length << 1);
        int outputWidth = Math.max(width, fullWidth);
        int outputHeight = Math.max(1, height);

        int multiple = outputWidth / fullWidth;
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;

        ByteMatrix output = new ByteMatrix(outputWidth, outputHeight);
        byte[][] outputArray = output.getArray();

        byte[] row = new byte[outputWidth];

        // a. Write the white pixels at the left of each row
        for (int x = 0; x < leftPadding; x++) {
            row[x] = (byte) 255;
        }

        // b. Write the contents of this row of the barcode
        int offset = leftPadding;
        for (int x = 0; x < inputWidth; x++) {
            byte value = (code[x] == 1) ? 0 : (byte) 255;
            for (int z = 0; z < multiple; z++) {
                row[offset + z] = value;
            }
            offset += multiple;
        }

        // c. Write the white pixels at the right of each row
        offset = leftPadding + (inputWidth * multiple);
        for (int x = offset; x < outputWidth; x++) {
            row[x] = (byte) 255;
        }

        // d. Write the completed row multiple times
        for (int z = 0; z < outputHeight; z++) {
            System.arraycopy(row, 0, outputArray[z], 0, outputWidth);
        }

        return output;
    }

    /**
     * Appends the given pattern to the target array starting at pos.
     *
     * @param startColor
     *          starting color - 0 for white, 1 for black
     * @return the number of elements added to target.
     */
    protected static int appendPattern(byte[] target, int pos, int[] pattern, int startColor) {
        if (startColor != 0 && startColor != 1) {
            throw new IllegalArgumentException(
                    "startColor must be either 0 or 1, but got: " + startColor);
        }

        byte color = (byte) startColor;
        int numAdded = 0;
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i]; j++) {
                target[pos] = color;
                pos += 1;
                numAdded += 1;
            }
            color ^= 1; // flip color after each segment
        }
        return numAdded;
    }
}
