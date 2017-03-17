package ObjectsDetector;

import org.opencv.core.Mat;

import ObjectsDetector.Utilities.DetectionResults;

public interface Tracker {
	
	String getName();
	
	String getDescription();

	DetectionResults handleFrame(Mat frame);

}
