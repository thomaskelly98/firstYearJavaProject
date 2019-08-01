package part02;

public enum SupRegion {
	
	UNITED_KINGDOM {
		@Override
		String getRegionAsString() {
			return "United Kingdom";
		}
	}, EUROPE {
		@Override
		String getRegionAsString() {
			return "Europe";
		}
	}, OUTSIDE_EU {
		@Override
		String getRegionAsString() {
			return "Outside Europe";
		}
	};
	
	abstract String getRegionAsString();
}
