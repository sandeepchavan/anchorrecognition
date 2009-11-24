package StripeReader.barcodetypes.datamatrix;

import StripeReader.core.BarcodeFormat;
import StripeReader.core.DecodeHintType;
import StripeReader.core.BinaryBitmap;
import StripeReader.core.Reader;
import StripeReader.core.ReaderException;
import StripeReader.core.Result;
import StripeReader.core.ResultPoint;
import StripeReader.core.ResultMetadataType;
import StripeReader.common.BitMatrix;
import StripeReader.common.DecoderResult;
import StripeReader.common.DetectorResult;
import StripeReader.barcodetypes.datamatrix.decoder.Decoder;
import StripeReader.barcodetypes.datamatrix.detector.Detector;

import java.util.Hashtable;

public final class DataMatrixReader implements Reader {

    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    /**
     * Locates and decodes a Data Matrix code in an image.
     *
     * @return a String representing the content encoded by the Data Matrix code
     * @throws ReaderException if a Data Matrix code cannot be found, or cannot be decoded
     */
    public Result decode(BinaryBitmap image) throws ReaderException {
        return decode(image, null);
    }

    public Result decode(BinaryBitmap image, Hashtable hints)
            throws ReaderException {
        DecoderResult decoderResult;
        ResultPoint[] points;
        if (hints != null && hints.containsKey(DecodeHintType.PURE_BARCODE)) {
            BitMatrix bits = extractPureBits(image.getBlackMatrix());
            decoderResult = decoder.decode(bits);
            points = NO_POINTS;
        } else {
            DetectorResult detectorResult = new Detector(image.getBlackMatrix()).detect();
            decoderResult = decoder.decode(detectorResult.getBits());
            points = detectorResult.getPoints();
        }
        Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), points, BarcodeFormat.DATAMATRIX);
        if (decoderResult.getByteSegments() != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderResult.getByteSegments());
        }
        if (decoderResult.getECLevel() != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderResult.getECLevel().toString());
        }
        return result;
    }

    /**
     * This method detects a Data Matrix code in a "pure" image -- that is, pure monochrome image
     * which contains only an unrotated, unskewed, image of a Data Matrix code, with some white border
     * around it. This is a specialized method that works exceptionally fast in this special
     * case.
     */
    private static BitMatrix extractPureBits(BitMatrix image) throws ReaderException {
        // Now need to determine module size in pixels

        int height = image.getHeight();
        int width = image.getWidth();
        int minDimension = Math.min(height, width);

        // First, skip white border by tracking diagonally from the top left down and to the right:
        int borderWidth = 0;
        while (borderWidth < minDimension && !image.get(borderWidth, borderWidth)) {
            borderWidth++;
        }
        if (borderWidth == minDimension) {
            throw ReaderException.getInstance();
        }

        // And then keep tracking across the top-left black module to determine module size
        int moduleEnd = borderWidth + 1;
        while (moduleEnd < width && image.get(moduleEnd, borderWidth)) {
            moduleEnd++;
        }
        if (moduleEnd == width) {
            throw ReaderException.getInstance();
        }

        int moduleSize = moduleEnd - borderWidth;

        // And now find where the bottommost black module on the first column ends
        int columnEndOfSymbol = height - 1;
        while (columnEndOfSymbol >= 0 && !image.get(borderWidth, columnEndOfSymbol)) {
            columnEndOfSymbol--;
        }
        if (columnEndOfSymbol < 0) {
            throw ReaderException.getInstance();
        }
        columnEndOfSymbol++;

        // Make sure width of barcode is a multiple of module size
        if ((columnEndOfSymbol - borderWidth) % moduleSize != 0) {
            throw ReaderException.getInstance();
        }
        int dimension = (columnEndOfSymbol - borderWidth) / moduleSize;

        // Push in the "border" by half the module width so that we start
        // sampling in the middle of the module. Just in case the image is a
        // little off, this will help recover.
        borderWidth += moduleSize >> 1;

        int sampleDimension = borderWidth + (dimension - 1) * moduleSize;
        if (sampleDimension >= width || sampleDimension >= height) {
            throw ReaderException.getInstance();
        }

        // Now just read off the bits
        BitMatrix bits = new BitMatrix(dimension);
        for (int i = 0; i < dimension; i++) {
            int iOffset = borderWidth + i * moduleSize;
            for (int j = 0; j < dimension; j++) {
                if (image.get(borderWidth + j * moduleSize, iOffset)) {
                    bits.set(j, i);
                }
            }
        }
        return bits;
    }
}
