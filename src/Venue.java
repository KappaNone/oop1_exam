public class Venue
{
    private String name;
    private String city;
    private int maxCapacity;

    public Venue(String name, String city, int maxCapacity)
    {
        this.setName(name);
        this.setCity(city);
        this.setMaxCapacity(maxCapacity);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Venue name cannot be empty or null.");
        }
        this.name = name;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        if (city == null || city.isEmpty())
        {
            throw new IllegalArgumentException("Venue city cannot be empty or null.");
        }
        this.city = city;
    }

    public int getMaxCapacity()
    {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity)
    {
        if (maxCapacity <= 0)
        {
            throw new IllegalArgumentException("Venue maxCapacity have to be greater than 0.");
        }
        this.maxCapacity = maxCapacity;
    }
}
