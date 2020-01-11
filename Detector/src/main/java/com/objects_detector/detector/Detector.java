package com.objects_detector.detector;

import com.objects_detector.ObjectDetectorListener;
import com.objects_detector.Tracker;
import com.objects_detector.trackers.FakeTracker.FakeTracker;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

public abstract class Detector {

    protected Set<ObjectDetectorListener> listeners;
    protected boolean isActive = false;
    protected VideoCapture capture;
    protected Tracker tracker;
    protected ScheduledExecutorService timer;

    public Detector() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        capture = new VideoCapture();
        listeners = new HashSet<>();
        tracker = new FakeTracker();
    }

    /**
     * Check if video capture was started or not.
     *
     * @return true if capture started, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Modifying detector tracking method.
     * the default detector have no tracker and it will publish non-filtered images.
     * use this method to set new tracker, setting null to it will cancel the current
     * tracker and set not filter images.
     *
     * @param oTracker - a type of tracker
     */
    public void setTracker(Tracker oTracker) {
        tracker = oTracker;
    }

    abstract boolean start();

    abstract void stop();

    public void handleFrame() {
        Mat frame = new Mat();
        capture.read(frame);
        for (ObjectDetectorListener listener : listeners)
            listener.handleImageProcessResults(tracker.handleFrame(frame));
    }

    /**
     * Signed up to video capture listeners. an appropriate method will be invoked on image arrival.
     *
     * @param objectDetectorListener - listener to be added
     */
    public void addListener(ObjectDetectorListener objectDetectorListener) {
        listeners.add(objectDetectorListener);
    }

    /**
     * Remove a video capture listener.
     *
     * @param objectDetectorListener - listener to be removed
     */
    public void removeListener(ObjectDetectorListener objectDetectorListener) {
        listeners.remove(objectDetectorListener);
    }
}
