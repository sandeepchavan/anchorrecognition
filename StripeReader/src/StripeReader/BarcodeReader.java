/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StripeReader;

/**
 *
 * @author Mr.Da
 */
import StripeReader.core.BinaryBitmap;
import StripeReader.core.DecodeHintType;
import StripeReader.core.LuminanceSource;
import StripeReader.core.MultiFormatReader;
import StripeReader.core.ReaderException;
import StripeReader.core.Result;
import StripeReader.core.BarcodeFormat;
import StripeReader.common.GlobalHistogramBinarizer;

import StripeReader.core.BufferedImageLuminanceSource;
import StripeReader.core.ParsedResult;
import StripeReader.core.ResultParser;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.util.Hashtable;

import javax.media.jai.*;
import java.net.URISyntaxException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BarcodeReader {

    private Hashtable<DecodeHintType, Object> buildHints() {
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(3);
        Vector<BarcodeFormat> vector = new Vector<BarcodeFormat>(8);
        vector.addElement(BarcodeFormat.UPC_A);
        vector.addElement(BarcodeFormat.UPC_E);
        vector.addElement(BarcodeFormat.EAN_13);
        vector.addElement(BarcodeFormat.EAN_8);
        vector.addElement(BarcodeFormat.CODE_39);
        vector.addElement(BarcodeFormat.CODE_128);
        vector.addElement(BarcodeFormat.ITF);
        vector.addElement(BarcodeFormat.QR_CODE);
        vector.addElement(BarcodeFormat.DATAMATRIX);
        vector.addElement(BarcodeFormat.PDF417);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        return hints;
    }

    public Result readBarcode(String args) {
        Result result = null;
        Hashtable<DecodeHintType, Object> hints = buildHints();
        try {
            result = decodeOneArgument(args, hints);
        } catch (IOException ex) {
            Logger.getLogger(BarcodeReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(BarcodeReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private Result decodeOneArgument(String argument, Hashtable<DecodeHintType, Object> hints) throws IOException, URISyntaxException {

        File inputFile = new File(argument);
        Result result = null;

        if (inputFile.exists()) {
            result = decode(inputFile.toURI(), hints);
        } else {
            result = decode(new URI(argument), hints);
        }
        return result;
    }

    private Result decode(URI uri, Hashtable<DecodeHintType, Object> hints) throws IOException {
        BufferedImage image = null;
        try {
            PlanarImage pi = JAI.create("URL", uri.toURL());
            image = pi.getAsBufferedImage();
        } catch (IllegalArgumentException iae) {
            throw new FileNotFoundException("Resource not found: " + uri);
        }
        if (image == null) {
            System.err.println(uri.toString() + ": Could not load image");
            return null;
        }
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap, hints);
            ParsedResult parsedResult = ResultParser.parseResult(result);
            /*System.out.println(uri.toString() + " (format: " + result.getBarcodeFormat() +
                    ", type: " + parsedResult.getType() + "):\nRaw result:\n" + result.getText() +
                    "\nParsed result:\n" + parsedResult.getDisplayResult());*/
            return result;
        } catch (ReaderException e) {
            System.out.println(uri.toString() + ": No barcode found");
            return null;
        }
    }
}
