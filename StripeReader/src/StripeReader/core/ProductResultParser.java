package StripeReader.core;

import StripeReader.barcodetypes.UPCEReader;

final class ProductResultParser extends ResultParser {

    private ProductResultParser() {
    }

    // Treat all UPC and EAN variants as UPCs, in the sense that they are all product barcodes.
    public static ProductParsedResult parse(Result result) {
        BarcodeFormat format = result.getBarcodeFormat();
        if (!(BarcodeFormat.UPC_A.equals(format) || BarcodeFormat.UPC_E.equals(format) ||
                BarcodeFormat.EAN_8.equals(format) || BarcodeFormat.EAN_13.equals(format))) {
            return null;
        }
        // Really neither of these should happen:
        String rawText = result.getText();
        if (rawText == null) {
            return null;
        }

        int length = rawText.length();
        for (int x = 0; x < length; x++) {
            char c = rawText.charAt(x);
            if (c < '0' || c > '9') {
                return null;
            }
        }
        // Not actually checking the checksum again here

        String normalizedProductID;
        // Expand UPC-E for purposes of searching
        if (BarcodeFormat.UPC_E.equals(format)) {
            normalizedProductID = UPCEReader.convertUPCEtoUPCA(rawText);
        } else {
            normalizedProductID = rawText;
        }

        return new ProductParsedResult(rawText, normalizedProductID);
    }
}
