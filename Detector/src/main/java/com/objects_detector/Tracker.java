package com.objects_detector;

import org.opencv.core.Mat;

import com.objects_detector.utilities.DetectionResults;

public interface Tracker {
	
	String getName();
	
	String getDescription();

	DetectionResults handleFrame(Mat frame);

}
