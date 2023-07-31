package alda.hash;

public class QuadraticProbingHashTable<AnyType> extends ProbingHashTable<AnyType> {

	@Override
	protected int findPos(AnyType x) {
		int offset = 1; //viktigt att denna är större än 0 annars kolliderar vi
		int currentPos = myhash(x);
		while (continueProbing(currentPos, x)) {
			currentPos += offset; // Compute ith probe
			offset += 2; // Korrekt, men kanske inte helt uppenbart. Se avsnitt 5.4.2.
			if (currentPos >= capacity())
				currentPos -= capacity();
		}

		return currentPos;
	}

}
