//Victor Ericson vier1798
package alda.hash;

import java.util.Arrays;
import java.util.Objects;

public class ISBN10 {

    private char[] isbn;

    public ISBN10(String isbn) {
        if (isbn.length() != 10)
            throw new IllegalArgumentException("Wrong length, must be 10");
        if (!checkDigit(isbn))
            throw new IllegalArgumentException("Not a valid isbn 10");
        this.isbn = isbn.toCharArray();
    }

    /*
     * min implementation av en hashcode.
     * Den multiplicerar ett primtal med varje tecken i strängen och adderar det till resultatet. Detta görs för att få
     * bättre spridning i hashtabellen.
     * Jag har valt att använda mig av ISBN för att det i sig är en unik sträng, och lämpar sig därför
     * bra för att skapa en hashcode då vi gärna vill att hashcoden ska vara unik för ett givet objekt.
     * Den är även konsekvent och följer ett väldefinierat format.
     * Adderingen av ett primtal visar sig inte alltid vara det optimala, och det finns absolut
     * mer effektiva sätt att skapa hashkoder på än denna, men denna version är snabb och enkel att
     * implementera. Ha dock i åtanke att bara för att ISBN är unikt och att denna implementation
     * reducerar chansen för kollisioner, så är chansen för kollisioner absolut inte noll.
     */
    @Override
    public int hashCode() {
        int result = 0;
        for (char c : isbn) {
            //itererar över ASCII-värden för samtliga chars i vår ISBN-kod
            result = c + 31 * result;
        }
        return result;
    }

    //En helt "analog" equalsmetod istället för att använda Arrays.equals()
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ISBN10 otherISBN)) {
            return false;
        }
        if (this.isbn.length != otherISBN.isbn.length) {
            return false;
        }
        for (int i = 0; i < this.isbn.length; i++) {
            if (this.isbn[i] != otherISBN.isbn[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDigit(String isbn) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        }
        int checkDigit = (11 - (sum % 11)) % 11;

        return isbn.endsWith(checkDigit == 10 ? "X" : "" + checkDigit);
    }

    @Override
    public String toString() {
        return new String(isbn);
    }

}