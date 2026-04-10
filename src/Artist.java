public class Artist
{
    private String name;
    private String genre;

    public Artist(String name, String genre)
    {
        this.setName(name);
        this.setGenre(genre);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Artist name cannot be empty or null.");
        }
        this.name = name;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        if (genre == null || genre.isEmpty())
        {
            throw new IllegalArgumentException("Artist genre cannot be empty or null.");
        }
        this.genre = genre;
    }
}
