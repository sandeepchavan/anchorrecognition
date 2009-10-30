package AnchorRecognition;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;

import javax.media.jai.*;
import javax.swing.JOptionPane;

public class TiffImageProcessing {

    private int intpageT;
    private String filename = "";
    private float angle;
    private PlanarImage tiffplanar = null;

    public TiffImageProcessing() {
        this.intpageT = 0;
        this.filename = "";
        this.angle = 0;
    }

    private void setInfor(String imageFile, int page, int newzoom, float newangle) {
        this.intpageT = page;
        this.filename = imageFile;
        this.angle = newangle;
    }

    public PlanarImage readImage(String imagefn, int intpage, int ctrlZoom, float ctrlAngle) {
        PlanarImage planarImg = null;
        ParameterBlock pb = null;
        try {
            if (!imagefn.trim().equalsIgnoreCase(this.filename) || this.intpageT != intpage) {
                this.filename = imagefn.trim();
                File file = new File(this.filename);
                SeekableStream s = new FileSeekableStream(file);
                pb = new ParameterBlock();
                pb.add(s);
                pb.add(new TIFFDecodeParam());
                pb.add(intpage);
                planarImg = JAI.create("TIFF", pb);
                tiffplanar = planarImg;
            } else {
                planarImg = tiffplanar;
            }
            if (ctrlZoom == 100 && ctrlAngle == 0) {
                setInfor(imagefn, intpage, ctrlZoom, ctrlAngle);
                return planarImg;
            } else {
                if (ctrlZoom != 100) {
                    pb = new ParameterBlock();
                    pb.addSource(planarImg);
                    pb.add((float) ctrlZoom / 100);
                    pb.add((float) ctrlZoom / 100);
                    pb.add(0.0F);
                    pb.add(0.0F);
                    pb.add(new InterpolationNearest());
                    planarImg = JAI.create("scale", pb, null);
                }
                if (ctrlAngle != 0) {
                    float fDelta = Math.abs((float) (planarImg.getWidth()) -
                            (float) (planarImg.getHeight()));
                    fDelta /= 2;

                    pb = new ParameterBlock();
                    pb.addSource(planarImg);
                    pb.add((float) planarImg.getWidth() / 2);
                    pb.add((float) planarImg.getHeight() / 2);
                    pb.add((float) (ctrlAngle * (Math.PI / 180.0F)));
                    pb.add(new InterpolationNearest());
                    planarImg = JAI.create("Rotate", pb, null);
                    //good rotate
                    pb = new ParameterBlock();
                    pb.addSource(planarImg); // The source image
                    if (ctrlAngle % 180 == 90 || ctrlAngle % 180 == -90) {
                        pb.add(-fDelta); // The x translation
                        pb.add(fDelta); // The y translation
                    } else {
                        pb.add(0F); // The x translation
                        pb.add(0F); // The y translation
                    }
                    pb.add(Interpolation.getInstance(Interpolation.INTERP_BILINEAR)); // The interpolation
                    //good rotate
                    // Create the translate operation
                    planarImg = JAI.create("translate", pb, null);
                }
                setInfor(imagefn, intpage, ctrlZoom, ctrlAngle);
                return planarImg;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot read image: " + ex.getMessage());
            return null;
        }
    }

    public PlanarImage readImage_1(String imagefn, int intpage, int ctrlZoom, float ctrlAngle) {
        PlanarImage planarImg = null;
        ParameterBlock pb = null;
        try {
            if (!imagefn.trim().equalsIgnoreCase(this.filename) || this.intpageT != intpage) {
                this.filename = imagefn.trim();
                File file = new File(this.filename);
                SeekableStream s = new FileSeekableStream(file);
                pb = new ParameterBlock();
                pb.add(s);
                pb.add(new TIFFDecodeParam());
                pb.add(intpage);
                planarImg = JAI.create("TIFF", pb);
                tiffplanar = planarImg;
            } else {
                planarImg = tiffplanar;
            }
            if (ctrlZoom == 100 && ctrlAngle == 0) {
                setInfor(imagefn, intpage, ctrlZoom, ctrlAngle);
                return planarImg;
            } else {
                if (ctrlZoom != 100) {
                    pb = new ParameterBlock();
                    pb.addSource(planarImg);
                    pb.add((float) ctrlZoom / 100);
                    pb.add((float) ctrlZoom / 100);
                    pb.add(0.0F);
                    pb.add(0.0F);
                    pb.add(new InterpolationNearest());
                    planarImg = JAI.create("scale", pb, null);
                }
                if (ctrlAngle != 0) {
                    float fDelta = Math.abs((float) (planarImg.getWidth()) -
                            (float) (planarImg.getHeight()));
                    fDelta /= 2;

                    pb = new ParameterBlock();
                    pb.addSource(planarImg);
                    pb.add((float) planarImg.getWidth() / 2);
                    pb.add((float) planarImg.getHeight() / 2);
                    pb.add((float) (ctrlAngle * (Math.PI / 180.0F)));
                    pb.add(new InterpolationNearest());
                    planarImg = JAI.create("Rotate", pb, null);
                    //good rotate
                    pb = new ParameterBlock();
                    pb.addSource(planarImg); // The source image
                    if (ctrlAngle % 180 == 90 || ctrlAngle % 180 == -90) {
                        pb.add(-fDelta); // The x translation
                        pb.add(fDelta); // The y translation
                    } else {
                        pb.add(0F); // The x translation
                        pb.add(0F); // The y translation
                    }
                    //pb.add(Interpolation.getInstance(Interpolation.INTERP_BILINEAR)); // The interpolation
                    //good rotate
                    // Create the translate operation
                    planarImg = JAI.create("translate", pb, null);
                }
                setInfor(imagefn, intpage, ctrlZoom, ctrlAngle);
                return planarImg;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot read image: " + ex.getMessage());
            return null;
        }
    }
}
