//Victor Ericson vier1798
package alda.hash;

public class LinearProbingHashTable<T> extends ProbingHashTable<T> {

    /*
     * Denna metod ska skrivas klart. Den ska använda linjär sondering och hela tiden öka med ett.
     */

    /**
     * Linjär sonderingsmetod som sonderar genom att skapa och sedan leta efter ett hashvärde i tabellen.
     * Metoden loopar igenom tabellen och jämför hashvärdet med det aktuella värdet som sonderas.
     * Om det aktuella värdet är null eller samma som x så returneras hashvärdet (platsen för värdet är funnet).
     * Om den aktuella platsen är ockuperad av ett annat objekt så inkrementeras hashvärdet med 1 och vi fortsätter sondera.
     * Om hashvärdet är större än tabellstorleken så subtraheras tabellstorleken från hashvärdet,
     * och därmed returneras vi till första platsen i tabellen.
     *
     * @param x the item to search for.
     * @return the hash value.
     * @author Victor Ericson
     */

    @Override
    protected int findPos(T x) {
        int currentPos = myhash(x);
        while (continueProbing(currentPos, x)) {
            currentPos++;
            currentPos %= capacity();
        }
        return currentPos;
    }
}
