package ObjectsDetector.Trackers;

import ObjectsDetector.Trackers.ColorTracker.ColorTracker;
import ObjectsDetector.Trackers.ColorTrackerLockSingleObject.ColorTrackerLockSingleObject;
import ObjectsDetector.Trackers.FakeTracker.FakeTracker;
import ObjectsDetector.Trackers.MovementTracker.MovmentTracker;

public enum TrackersEnum {
	
	MOVEMENT_TRACKER(MovmentTracker.name),
	COLOR_TRACKER(ColorTracker.name),
	COLOR_TRACKER_SINGLE_OBJECT(ColorTrackerLockSingleObject.name),
	VIDEO_ONLY(FakeTracker.name);

	TrackersEnum(String name) {
		this.name = name;
	}
	
	public String name;
	
	public String toString() {
		return name;
	}
}
