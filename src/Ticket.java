import java.time.LocalDate;

public class Ticket
{
    private String code;

    public Ticket(Concert concert)
    {
        this.setCode(createCode(concert));
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String createCode(Concert concert)
    {
        String separator = " - ";
        StringBuilder sb = new StringBuilder();

        sb.append(concert.getArtist().getName().trim());
        sb.append(separator);
        sb.append(concert.getVenue().getName().trim());
        sb.append(separator);
        sb.append(concert.getDate());

        return sb.toString();
    }
}
