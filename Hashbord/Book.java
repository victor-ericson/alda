//Victor Ericson vier1798
package alda.hash;

/*
 * Denna klass ska förberedas för att kunna användas som nyckel i en hashtabell.
 * Du får göra nödvändiga ändringar även i klasserna MyString och ISBN10.
 *
 * Hashkoden ska räknas ut på ett effektivt sätt och följa de regler och
 * rekommendationer som finns för hur en hashkod ska konstrueras. Notera i en
 * kommentar i koden hur du har tänkt när du konstruerat din hashkod.
 */
public class Book {
    private MyString title;
    private MyString author;
    private ISBN10 isbn;
    private MyString content;
    private int price;

    public Book(String title, String author, String isbn, String content, int price) {
        this.title = new MyString(title);
        this.author = new MyString(author);
        this.isbn = new ISBN10(isbn);
        this.content = new MyString(content);
        this.price = price;
    }


    public MyString getTitle() {
        return title;
    }

    public MyString getAuthor() {
        return author;
    }

    public ISBN10 getIsbn() {
        return isbn;
    }

    public MyString getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /*
    Se ISBN10 för utförligt svar om val av hashCode,
    men hashCoden konstrueras i klassen ISBN10 då den enbart byggs upp med
    hjälp av ISBN-koden.
     */

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Book other)) {
            return false;
        }
        return this.isbn.equals(other.isbn);
    }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s Price: %d ISBN: %s length: %s", title, author, price, isbn, content.length());
    }

}
