import java.util.ArrayList;

/**
 * Created by Mitch on 4/27/2016.
 */
public class Player {

    private ArrayList<Territory> conqueredTerritories;
    private ArrayList<Continent> conqueredContinents;
    private String name;

    public Player(String name){
        this.name = name;
    }

    public void addTerritory(Territory territory){
        conqueredTerritories.add(territory);
    }

    public void addContinent(Continent continent){
        conqueredContinents.add(continent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
