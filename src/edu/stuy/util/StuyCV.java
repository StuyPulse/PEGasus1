package edu.stuy.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class StuyCV {

    /*
        Comparator for sorting MatOfPoints - sorts from highest area to lowest area
     */
    public static Comparator<MatOfPoint> contourAreaCompareDescending = new Comparator<MatOfPoint>() {
        public int compare(MatOfPoint first, MatOfPoint second) {

            double firstArea = contourArea(first);
            double secondArea = contourArea(second);

            double diff = firstArea - secondArea;

            return (int) diff;

        }
    };

    /*
    Comparator for sorting MatOfPoints - sorts from lowest to highest area
     */
    public static Comparator<MatOfPoint> contourAreaCompareAscending = new Comparator<MatOfPoint>() {
        public int compare(MatOfPoint first, MatOfPoint second) {

            double firstArea = contourArea(first);
            double secondArea = contourArea(second);

            double diff = secondArea - firstArea;

            return (int) diff;

        }
    };

    /***
     * Finds the area of a rectangle
     * @param rect - Rectangle that will be processed
     * @return - Area of the rectangle
     */
    public static double contourArea(Rect rect) {
        return rect.width * rect.height;
    }

    /***
     * Finds the area of a MatOfPoint
     * @param mop - MatOfPoint to be processed
     * @return - Area of the MatOfPoint
     */
    public static double contourArea(MatOfPoint mop) {
        return mop.width() * mop.height();
    }

    /***
     * Show feed from the camera
     * @param camera - VideoCapture that will be used
     * @param src - Source matrix that will be shown
     */
    public static void getFromFeed(VideoCapture camera, Mat src) {

        // If the camera is not found, then program will exit
        if (!camera.isOpened()) {
            System.out.println("VideoCapture device not detected.");
            throw new NullPointerException();
        }

        if (camera.grab()) {
            if (!camera.retrieve(src)) {
                System.out.println("Could not retrieve.");
                throw new NullPointerException();
            }
        }
    }

    /***
     * Shows a picture with a given path
     * @param path - Absolute path to the picture
     * @return - The picture
     */
    public static Mat picture(String path) {
        return Highgui.imread(path);
    }

    /***
     * Detects color
     * @param src - Source matrix to be processed
     * @param lowerBound - Lower scalar of colors for the range
     * @param upperBound - Upper scalar of colors for the range
     * @return - Processed matrix
     */
    public static Mat colorDetect(Mat src , Mat dst , Scalar lowerBound , Scalar upperBound) {

        // Use lower and upper bounds as declared above
        Core.inRange(src, lowerBound, upperBound, src);

        // Sequence of erode and dilate, and finally a threshold to reduce noise
        Imgproc.erode(src , src , Imgproc.getStructuringElement(Imgproc.MORPH_RECT , new Size(3,3)));
        Imgproc.dilate(src , src , Imgproc.getStructuringElement(Imgproc.MORPH_RECT , new Size(3,3)));
        Imgproc.dilate(src , src , Imgproc.getStructuringElement(Imgproc.MORPH_RECT , new Size(3,3)));
        Imgproc.erode(src , src , Imgproc.getStructuringElement(Imgproc.MORPH_RECT , new Size(3,3)));

        Imgproc.threshold(src, dst, 127, 255, Imgproc.THRESH_BINARY);

        return dst;
    }

    /***
     * Detects the edges in a matrix
     * @param src - Matrix to be processed
     * @return - Processed List of MatOfPoint
     */
    public static ArrayList<MatOfPoint> edgeDetect(Mat src){

        // Destination matrix for the processed image
        Mat edges = new Mat();
        // Returned ArrayList<MatOfPoint> for return
        ArrayList<MatOfPoint> contour = new ArrayList<MatOfPoint>();

        Imgproc.Canny(src, edges, 0, 100);
        // New Mat as the hierarchy that is being superimposed
        // If the hierarchy is the source, then the destination will return all white
        Imgproc.findContours(edges, contour, new Mat() , Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        return contour;
    }

    /***
     *  Function takes an ArrayList<MatOfPoint> and a double to compare the area of the
     *  ArrayList<MatOfPoint> and the inputed area
     * @param src - List of MatOfPoints to be processed
     * @param area - The lower threshold of valid areas. Use 0 for no threshold
     * @return - Processed matrix
     */
    public static ArrayList<MatOfPoint> iterator (ArrayList<MatOfPoint> src, double area) {

        // As long as the next element in src is not empty
        for (Iterator<MatOfPoint> iterator = src.iterator(); iterator.hasNext();){
            MatOfPoint temp =  (MatOfPoint) iterator.next();

            if (contourArea(temp) < area) {
                iterator.remove();
            }
        }

        return src;
    }

    /***
     * Detects number of polygons.
     * @param src - MatOfPoint2f to be processed
     * @param dst - Intermediate MatOfPoint2f
     * @param edges - Number of edges in the desired polygon
     * @param epsilon - How accurate the polygon detection will be (Should be small)
     * @return - Number of polygons with edges sides
     */
    public static int polygonDetect(MatOfPoint2f src, MatOfPoint2f dst, int edges, int epsilon) {

        List<MatOfPoint> contour = new ArrayList<MatOfPoint>();
        MatOfPoint contourMOP = new MatOfPoint();
        int polygonsFound = 0;

        for (int i = 0; i < contour.size(); i++) {
            Imgproc.approxPolyDP(src, dst, epsilon, true);
            dst.convertTo(contourMOP, CvType.CV_32SC2);
            List<Point> contourList = contourMOP.toList();
            if (contourList.size() == edges) {
                polygonsFound++;
            }
        }

        return polygonsFound;
    }

    /***
     * Detects motion
     * @param stream - Input VideoCapture that will be processed
     * @param timeout - The length of time in milliseconds to check for motion
     * @return True if motion is detected, false otherwise
     */
    public static boolean motionDetect(VideoCapture stream , double timeout) {

        Mat prev = new Mat();
        Mat src = new Mat();
        Mat compare = new Mat();
        double totalArea;

        double startTime = System.currentTimeMillis();

        getFromFeed(stream , src);

        while (System.currentTimeMillis() - startTime < timeout) {
            prev = src.clone();
            getFromFeed(stream , src);

            Core.bitwise_and(prev, src, compare);
            ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            contours = edgeDetect(compare);
            totalArea = 0.0;
            for (MatOfPoint mop : contours) {
                totalArea += contourArea(mop);
            }
            if (totalArea >= 15) {
                return true;
            }
        }
        return false;
    }

    /***
     * Detects motion
     * @param stream - Input VideoCapture that will be processed
     * @param timeout - The length of time in milliseconds to check for motion
     * @param threshold - The tolerance of motion allowed
     * @return True if motion is detected, false otherwise
     */
    public static boolean motionDetect(VideoCapture stream , double timeout , double threshold) {

        Mat prev = new Mat();
        Mat src = new Mat();
        Mat compare = new Mat();
        double totalArea;

        double startTime = System.currentTimeMillis();

        getFromFeed(stream , src);

        while (System.currentTimeMillis() - startTime < timeout) {
            prev = src.clone();
            getFromFeed(stream , src);

            Core.bitwise_and(prev, src, compare);
            ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            contours = edgeDetect(compare);
            totalArea = 0.0;
            for (MatOfPoint mop : contours) {
                totalArea += contourArea(mop);
            }
            if (totalArea >= threshold) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the total number
     * @param src - Source matrix to be processed
     * @param epsilon - How accurate the edge detection will be
     * @return Number of edges detected
     */
    public static ArrayList<Integer> getTotalEdges(Mat src, int epsilon) {

        ArrayList<Integer> totalEdges = new ArrayList<Integer>();
        ArrayList<MatOfPoint> contour = new ArrayList<MatOfPoint>();
        MatOfPoint2f contourMOP2f = new MatOfPoint2f();

        contour = edgeDetect(src);

        for (int i = 0; i < contour.size(); i++) {
            Imgproc.approxPolyDP(contourMOP2f, contourMOP2f, epsilon, true);
            List<Point> contourList = contourMOP2f.toList();
            totalEdges.add(contourList.size());
        }

        return totalEdges;
    }

    /**
     * Acquires the points of the largest rectangle in the source matrix
     * @param src - Source matrix to be processed
     * @param num - The 'num' largest rectangles to return
     * @return The top left point and lower right point of the largest rectangle
     */
    public static ArrayList<Rect> largestRectangles(Mat src , int num) {

        ArrayList<Rect> rects = new ArrayList<Rect>();
        ArrayList<MatOfPoint> contour = new ArrayList<MatOfPoint>();

        contour = edgeDetect(src);

        Collections.sort(contour , contourAreaCompareDescending);
        contour.add(contour.remove(0));

        for (int i = 0; i < num; i++) {
            MatOfPoint pts = new MatOfPoint(contour.get(i));
            rects.add(Imgproc.boundingRect(pts));
        }
        return rects;
    }

}