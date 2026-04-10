import java.time.LocalDate;
import java.util.ArrayList;

public class Concert
{
    private final int DEFAULT_TICKET_PRICE_IN_EUROS = 50;
    private Artist artist;
    private Venue venue;
    private LocalDate date;
    private ArrayList<Ticket> soldTickets;
    private int priceInEuro;

    public Concert(Artist artist, Venue venue, LocalDate date)
    {
        this.setArtist(artist);
        this.setVenue(venue);
        this.setDate(date);
        this.setPriceInEuro(DEFAULT_TICKET_PRICE_IN_EUROS);
        this.soldTickets = new ArrayList<Ticket>();
    }

    public Artist getArtist()
    {
        return artist;
    }

    public ArrayList<Ticket> getSoldTickets()
    {
        return soldTickets;
    }

    public void setSoldTickets(ArrayList<Ticket> soldTickets)
    {
        this.soldTickets = soldTickets;
    }

    public void setArtist(Artist artist)
    {
        this.artist = artist;
    }

    public Venue getVenue()
    {
        return venue;
    }

    public void setVenue(Venue venue)
    {
        this.venue = venue;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public int getPriceInEuro()
    {
        return priceInEuro;
    }

    public void setPriceInEuro(int priceInEuro)
    {
        if (priceInEuro <= 0)
        {
            throw new IllegalArgumentException("Concert price have to be greater than 0.");
        }
        this.priceInEuro = priceInEuro;
    }

    public int getRevenueInEuro()
    {
        return this.getSoldTickets().size() * this.getPriceInEuro();
    }

    public int getAmountOfTicketsLeft()
    {
        return this.getVenue().getMaxCapacity() - this.getSoldTickets().size();
    }

    public boolean hasOccured()
    {
        return LocalDate.now().isAfter(this.getDate());
    }
}
