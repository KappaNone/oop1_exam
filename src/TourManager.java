import java.time.LocalDate;
import java.util.ArrayList;

public class TourManager
{
    private String name;
    private int maxAmountOfArtists;
    private ArrayList<Artist> artists;
    private ArrayList<Venue> venues;
    private ArrayList<Concert> concerts;

    public TourManager(String name, int maxAmountOfArtists)
    {
        this.setName(name);
        this.setMaxAmountOfArtists(maxAmountOfArtists);
        this.setArtists(new ArrayList<>());
        this.setVenues(new ArrayList<>());
        this.setConcerts(new ArrayList<>());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("TourManager name cannot be empty or null.");
        }
        this.name = name;
    }

    public int getMaxAmountOfArtists()
    {
        return maxAmountOfArtists;
    }

    public void setMaxAmountOfArtists(int maxAmountOfArtists)
    {
        if (maxAmountOfArtists <= 0)
        {
            throw new IllegalArgumentException("TourManager maxAmoutOfArtists have to be greater than 0.");
        }
        this.maxAmountOfArtists = maxAmountOfArtists;
    }

    public ArrayList<Artist> getArtists()
    {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists)
    {
        this.artists = artists;
    }

    public ArrayList<Venue> getVenues()
    {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues)
    {
        this.venues = venues;
    }

    public ArrayList<Concert> getConcerts()
    {
        return concerts;
    }

    public void setConcerts(ArrayList<Concert> concerts)
    {
        this.concerts = concerts;
    }

    // methods
    public ArrayList<Concert> getConcertsByArtist(ArrayList<Concert> concerts, Artist artist)
    {
        ArrayList<Concert> result = new ArrayList<>();
        for (Concert concert : this.getConcerts())
        {
            if (concert.getArtist() == artist)
            {
                result.add(concert);
            }
        }
        return result;
    }

    private ArrayList<Concert> getConcertsByVenue(ArrayList<Concert> concerts, Venue venue)
    {
        ArrayList<Concert> result = new ArrayList<>();
        for (Concert concert : this.getConcerts())
        {
            if (concert.getVenue() == venue)
            {
                result.add(concert);
            }
        }
        return result;
    }

    private ArrayList<Concert> getConcertsByDate(ArrayList<Concert> concerts, LocalDate date)
    {
        ArrayList<Concert> result = new ArrayList<>();
        for (Concert concert : this.getConcerts())
        {
            if (concert.getDate() == date)
            {
                result.add(concert);
            }
        }
        return result;
    }

    public Concert getConcert(Artist artist, Venue venue, LocalDate date)
    {
        ArrayList<Concert> results = this.getConcertsByArtist(this.getConcerts(), artist);
        results = this.getConcertsByVenue(results, venue);
        results = this.getConcertsByDate(results, date);

        if (results.isEmpty())
        {
            return null;
        }

        return results.getFirst();
    }

    public Concert getConcert(String artistName, String venueName, LocalDate date)
    {
        Artist artist = this.getArtistByName(artistName);
        Venue venue = this.getVenueByName(venueName);

        ArrayList<Concert> results = this.getConcertsByArtist(this.getConcerts(), artist);
        results = this.getConcertsByVenue(results, venue);
        results = this.getConcertsByDate(results, date);

        if (results.isEmpty())
        {
            return null;
        }

        return results.getFirst();
    }

    public Artist getArtistByName(String artistName)
    {
        for (Artist artist : this.getArtists())
        {
            if (artist.getName().equals(artistName))
            {
                return artist;
            }
        }

        return null;
    }

    public Venue getVenueByName(String venueName)
    {
        for (Venue venue : this.getVenues())
        {
            if (venue.getName().equals(venueName))
            {
                return venue;
            }
        }

        return null;
    }

    public void addArtist(Artist artist)
    {
        if (this.getArtists().size() >= this.getMaxAmountOfArtists())
        {
            throw new IllegalArgumentException("Maximum amount of artists has already been reached.");
        }
        this.getArtists().add(artist);
    }

    public void addVenue(Venue venue)
    {
        this.getVenues().add(venue);
    }

    public void addConcert(Concert concert)
    {
        this.getConcerts().add(concert);
    }

    public boolean scheduleConcert(String artistName, String venueName, LocalDate date)
    {
        Artist artist = this.getArtistByName(artistName);
        Venue venue = this.getVenueByName(venueName);

        if (artist == null)
        {
            throw new IllegalArgumentException("No artist with name: " + artistName + ".");
        }

        if (venue == null)
        {
            throw new IllegalArgumentException("No venue with name: " + venueName + ".");
        }

        Concert newConcert = new Concert(artist, venue, date);
        this.addConcert(newConcert);
        return true;
    }

    public void removeCancelledConcerts()
    {
        for (Concert concert : this.getConcerts())
        {
            if (concert.getSoldTickets().isEmpty())
            {
                this.getConcerts().remove(concert);
            }
        }
    }

    public int getTotalRevenueInEuros(String artistName) throws IllegalArgumentException
    {
        if (artistName == null || artistName.isEmpty())
        {
            throw new IllegalArgumentException("Artist name cannot be empty or null.");
        }

        Artist artist = this.getArtistByName(artistName);
        if (artist == null)
        {
            throw new IllegalArgumentException("Cannot find artist with name " + artistName + ".");
        }

        ArrayList<Concert> concerts = this.getConcertsByArtist(this.getConcerts(), artist);
        if (concerts.isEmpty())
        {
            throw new IllegalArgumentException("Artist with name " + artistName + " do not have any concerts yet.");
        }

        int result = 0;

        for (Concert concert : concerts)
        {
            result += concert.getRevenueInEuro();
        }

        return result;
    }
}
