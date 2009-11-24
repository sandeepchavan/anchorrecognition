package StripeReader.core;

import java.util.Hashtable;

public final class Result {

  private final String text;
  private final byte[] rawBytes;
  private final ResultPoint[] resultPoints;
  private final BarcodeFormat format;
  private Hashtable resultMetadata;

  public Result(String text,
                byte[] rawBytes,
                ResultPoint[] resultPoints,
                BarcodeFormat format) {
    if (text == null && rawBytes == null) {
      throw new IllegalArgumentException("Text and bytes are null");
    }
    this.text = text;
    this.rawBytes = rawBytes;
    this.resultPoints = resultPoints;
    this.format = format;
    this.resultMetadata = null;
  }

  /**
   * @return raw text encoded by the barcode, if applicable, otherwise <code>null</code>
   */
  public String getText() {
    return text;
  }

  /**
   * @return raw bytes encoded by the barcode, if applicable, otherwise <code>null</code>
   */
  public byte[] getRawBytes() {
    return rawBytes;
  }

  /**
   * @return points related to the barcode in the image. These are typically points
   *         identifying finder patterns or the corners of the barcode. The exact meaning is
   *         specific to the type of barcode that was decoded.
   */
  public ResultPoint[] getResultPoints() {
    return resultPoints;
  }

  /**
   * @return {@link BarcodeFormat} representing the format of the barcode that was decoded
   */
  public BarcodeFormat getBarcodeFormat() {
    return format;
  }

  /**
   * @return {@link Hashtable} mapping {@link ResultMetadataType} keys to values. May be
   *   <code>null</code>. This contains optional metadata about what was detected about the barcode,
   *   like orientation.
   */
  public Hashtable getResultMetadata() {
    return resultMetadata;
  }

  public void putMetadata(ResultMetadataType type, Object value) {
    if (resultMetadata == null) {
      resultMetadata = new Hashtable(3);
    }
    resultMetadata.put(type, value);
  }

  public String toString() {
    if (text == null) {
      return "[" + rawBytes.length + " bytes]";
    } else {
      return text;
  }
  }

}
