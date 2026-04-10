import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TicketServiceTest
{
    public LocalDate date;
    public TourManager tourManager;
    public TicketService ticketService;


    @org.junit.Before
    public void before()
    {
        Artist artist = new Artist("Bob", "Blues");
        Venue venue = new Venue("Arena", "Riga", 5);
        LocalDate date = LocalDate.now().plusMonths(1);
        Concert concert = new Concert(artist, venue, date);
        TourManager manager = new TourManager("On Tour", 2);
        manager.addArtist(artist);
        manager.addVenue(venue);
        manager.addConcert(concert);

        TicketService ticketService = new TicketService(manager);


        this.date = date;
        this.tourManager = manager;
        this.ticketService = ticketService;
    }

    @Test
    public void getTotalRevenueInEuroHappyPath()
    {
        this.ticketService.sellTickets("Bob", "Arena", this.date, 5);
        int revenue = this.ticketService.getTotalRevenueInEuro("Bob");
        assertEquals(250, revenue);
    }

    @Test
    public void getTotalRevenueInEuroNullArgument()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            this.ticketService.getTotalRevenueInEuro(null);
        });
    }

    @Test
    public void getTotalRevenueInEuroNoArtistWithSuchName()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            this.ticketService.getTotalRevenueInEuro("Bobby");
        });
    }

    @Test
    public void getTotalRevenueInEuroEmptyStringArgument()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            this.ticketService.getTotalRevenueInEuro("");
        });
    }

    @Test
    public void getTotalRevenueInEuroArtistDoNotHaveAnyConcerts()
    {
        Artist artist = new Artist("Anton", "RNB");
        this.tourManager.addArtist(artist);
        assertThrows(IllegalArgumentException.class, () ->
        {
            this.ticketService.getTotalRevenueInEuro("Anton");
        });
    }

    @Test
    public void getTotalRevenueInEuroConcertDoNotHaveSoldTickets()
    {

        Artist artist = new Artist("Anton", "RNB");
        this.tourManager.addArtist(artist);
        Concert concert = new Concert(artist, this.tourManager.getVenueByName("Arena"), LocalDate.now().plusMonths(2));
        this.tourManager.addConcert(concert);

        int revenue = this.ticketService.getTotalRevenueInEuro("Anton");
        assertEquals(0, revenue);
    }
}