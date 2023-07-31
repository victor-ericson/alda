//Victor Ericson vier1798
package alda.hash;

public class DoubleHashingProbingHashTable<T> extends ProbingHashTable<T> {

    /*
     * Denna metod ska skrivas klart. Den ska använda bokens förslag på andra
     * hashalgoritm: f(i) = i * hash2(x), där hash2(x) = R - (x mod R) och R är
     * ett primtal mindre än tabellstorleken.
     */

    @Override
    protected int findPos(T x) {
        int offset;
        int currentPos = myhash(x);
        while (continueProbing(currentPos, x)) {
            //ny int som förflyttar oss längre fram i tabellen
            offset = smallerPrimeThanCapacity() - (myhash(x) % smallerPrimeThanCapacity());
            currentPos += offset;
            if (currentPos >= capacity()) {
                currentPos -= capacity();
            }
        }
        return currentPos;
    }

    /*
     * Denna metod ger ett primtal mindre än tabellens storlek. Detta primtal ska
     * användas i metoden ovan.
     */
    protected int smallerPrimeThanCapacity() {
        int n = capacity() - 2;
        while (!isPrime(n)) {
            n -= 2;
        }
        return n;
    }

}
