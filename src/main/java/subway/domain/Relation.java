package subway.domain;

public class Relation {

	private String lineName;
	private String stationName;
	private String opponentStationName;

	private int distanceWeight;
	private int timeWeight;

	public Relation(
		String lineName,
		String stationName,
		String opponentStationName,
		int distanceWeight,
		int timeWeight
	) {
		this.lineName = lineName;
		this.stationName = stationName;
		this.opponentStationName = opponentStationName;
		this.distanceWeight = distanceWeight;
		this.timeWeight = timeWeight;
	}

	public Relation(String stationName, String opponentStationName) {
		this.stationName = stationName;
		this.opponentStationName = opponentStationName;
	}

	public Relation(double distanceWeight, double timeWeight) {
		this.distanceWeight = (int) distanceWeight;
		this.timeWeight = (int) timeWeight;
	}

	public String getStationName() {
		return stationName;
	}

	public String getOpponentStationName() {
		return opponentStationName;
	}

	public int getDistanceWeight() {
		return distanceWeight;
	}

	public int getTimeWeight() {
		return timeWeight;
	}

	@Override
	public String toString() {
		return "Relation{" +
			"lineName='" + lineName + '\'' +
			", stationName='" + stationName + '\'' +
			", opponentStationName='" + opponentStationName + '\'' +
			", distanceWeight=" + distanceWeight +
			", timeWeight=" + timeWeight +
			'}';
	}
}
