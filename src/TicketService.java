import java.time.LocalDate;

public class TicketService
{
    private TourManager tourManager;

    public TicketService(TourManager tourManager)
    {
        this.setTourManager(tourManager);
    }

    public TourManager getTourManager()
    {
        return tourManager;
    }

    public void setTourManager(TourManager tourManager)
    {
        if (tourManager == null)
        {
            throw new IllegalArgumentException("TicketService cannot have tourManager = null.");
        }
        this.tourManager = tourManager;
    }

    // methods

    public void sellTickets(String artistName, String venueName, LocalDate date, int amountOfTickets)
    {
        Concert concert = this.getTourManager().getConcert(artistName, venueName, date);

        if (concert == null)
        {
            throw new IllegalArgumentException("Cannot sell ticket for nonexistent concert.");
        }

        if (concert.hasOccured())
        {
            throw new IllegalArgumentException("Cannot sell ticket for concert that already finished.");
        }

        if (concert.getAmountOfTicketsLeft() < amountOfTickets)
        {
            throw new IllegalArgumentException("Cannot sell ticket for concert with no tickets left.");
        }

        for (int i = 0; i < amountOfTickets; i++)
        {
            Ticket ticket = new Ticket(concert);
            concert.getSoldTickets().add(ticket);
        }
    }

    public int getTotalRevenueInEuro(String artistName)
    {
        return this.getTourManager().getTotalRevenueInEuros(artistName);
    }
}
