package ObjectsDetector;

import ObjectsDetector.Utilities.DetectionResults;

public interface ObjectDetectorListener {
	
	public void handleImageProcessResults(DetectionResults frameProcessResult);
}
