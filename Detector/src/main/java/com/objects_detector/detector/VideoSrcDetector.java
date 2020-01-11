package com.objects_detector.detector;

import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VideoSrcDetector extends Detector {

    public VideoSrcDetector(String source) {
        super();
        capture = new VideoCapture(source);
    }

    @Override
    public boolean start() {
        if (!this.isActive) {

//            if (this.capture.isOpened()) {
                isActive = true;

                // grab a frame every 33 ms (30 frames/sec)
                Runnable frameGrabber = () -> handleFrame();

                timer = Executors.newSingleThreadScheduledExecutor();
                timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);

                return true;
//            }
//            else {
//                System.err.println("Failed to open the camera connection...");
//                return false;
//            }
        }
        else {
            try {
                isActive = false;
                timer.shutdown();
                timer.awaitTermination(330, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e) {
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }

            this.capture.release();
            return false;
        }
    }

    @Override
    public void stop() {

    }
}
