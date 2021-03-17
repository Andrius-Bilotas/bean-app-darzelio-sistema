package lt2021.projektas.kindergarten.queue;

public enum AgeGroup {
	PRESCHOOL {
		@Override
		public String toString() {
			return "2-3m. grupė";
		}
	}, KINDERGARTEN {
		@Override
		public String toString() {
			return "3-6m. grupė";
		}
	};
}
