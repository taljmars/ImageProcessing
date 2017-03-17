package ObjectsDetector.Trackers.FakeTracker;

import org.opencv.core.Mat;

import ObjectsDetector.Tracker;
import ObjectsDetector.Utilities.DetectionResults;
import ObjectsDetector.Utilities.Utilities;
import javafx.scene.image.Image;

public class FakeTracker implements Tracker {
	
	public static String name = "Video Only";

	@Override
	public DetectionResults handleFrame(Mat frame) {
		Image img = Utilities.mat2Image(frame);
		DetectionResults detectionResults = new DetectionResults();
		detectionResults.setFinalImage(img);
		return detectionResults;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
