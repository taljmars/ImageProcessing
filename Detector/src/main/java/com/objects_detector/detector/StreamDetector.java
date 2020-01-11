package com.objects_detector.detector;

import com.objects_detector.Tracker;
import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StreamDetector extends Detector {

	public static final int DEFAULT_ID = -1;
	private int videoDeviceId = DEFAULT_ID;
	
	public StreamDetector(int deviceId) {
		super();
		videoDeviceId = deviceId;
		capture = new VideoCapture();
	}
	
	public StreamDetector(int deviceId, Tracker oTracker) {
		this(deviceId);
		tracker = oTracker;
	}
	
	/**
	 * set the device id to get the video input from
	 * 
	 * @param deviceId - of the camera source
	 */
	public void setDeviceId(int deviceId) {
		this.videoDeviceId = deviceId;
	}
	
	/**
	 * start grabbing images from the device. 
	 * Output might be modified if a tracker is defined.
	 * 
	 * @return true if open camera device succeeded, false otherwise
	 */
	@Override
	public boolean start() {
		if (videoDeviceId == -1) {
			System.err.println("Device Id wasn't initialized");
			return false;
		}
		if (!this.isActive) {
			this.capture.open(videoDeviceId);
			
			if (this.capture.isOpened()) {
				isActive = true;
				
				// grab a frame every 33 ms (30 frames/sec)
				Runnable frameGrabber = () -> handleFrame();
				
				timer = Executors.newSingleThreadScheduledExecutor();
				timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
				
				return true;
			}
			else {
				System.err.println("Failed to open the camera connection...");
				return false;
			}
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
	
	/**
	 * Stopping the video device in case it is activated. 
	 */
	@Override
	public void stop() {
		if (videoDeviceId == -1) {
			System.err.println("Device Id wasn't initialized");
			return;
		}
		if (!isActive)
			return;
		
		try {
			isActive = false;
			timer.shutdown();
			timer.awaitTermination(330, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e) {
			System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
		}
		
		this.capture.release();
	}
}
