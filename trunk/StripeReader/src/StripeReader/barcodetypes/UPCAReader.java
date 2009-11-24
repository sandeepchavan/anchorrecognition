package StripeReader.barcodetypes;

import StripeReader.core.BarcodeFormat;
import StripeReader.core.ReaderException;
import StripeReader.core.Result;
import StripeReader.core.BinaryBitmap;
import StripeReader.common.BitArray;

import java.util.Hashtable;

public final class UPCAReader implements UPCEANReader {

    private final UPCEANReader ean13Reader = new EAN13Reader();

    public Result decodeRow(int rowNumber, BitArray row, int[] startGuardRange) throws ReaderException {
        return maybeReturnResult(ean13Reader.decodeRow(rowNumber, row, startGuardRange));
    }

    public Result decodeRow(int rowNumber, BitArray row, Hashtable hints) throws ReaderException {
        return maybeReturnResult(ean13Reader.decodeRow(rowNumber, row, hints));
    }

    public Result decode(BinaryBitmap image) throws ReaderException {
        return maybeReturnResult(ean13Reader.decode(image));
    }

    public Result decode(BinaryBitmap image, Hashtable hints) throws ReaderException {
        return maybeReturnResult(ean13Reader.decode(image, hints));
    }

    private static Result maybeReturnResult(Result result) throws ReaderException {
        String text = result.getText();
        if (text.charAt(0) == '0') {
            return new Result(text.substring(1), null, result.getResultPoints(), BarcodeFormat.UPC_A);
        } else {
            throw ReaderException.getInstance();
        }
    }
}
