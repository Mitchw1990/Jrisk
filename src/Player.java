import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mitch on 4/27/2016.
 */
public class Player {

    private ArrayList<Territory> conqueredTerritories;
    private ArrayList<Continent> conqueredContinents;
    private String name;
    private ArrayList<Die> playerDice;
    private Territory currentTerritory;
    private Territory currentTerritoryToAttack;
    private Territory currentTerritoryFortifyTo;

    public Player(String name){
        currentTerritory = null;
        currentTerritoryToAttack = null;
        currentTerritoryFortifyTo = null;
        this.name = name;
        conqueredContinents = new ArrayList<Continent>();
        conqueredTerritories = new ArrayList<Territory>();
        playerDice = new ArrayList<Die>();
        playerDice.add(new Die());
        playerDice.add(new Die());
        playerDice.add(new Die());
    }


    public int[]  rollDice(int numberOfDice){

        int[] results = new int[numberOfDice];

        if(numberOfDice > 0 && numberOfDice <= 3){
            for (int i = 0; i < numberOfDice; i++){
                playerDice.get(i).roll();
                results[i] = playerDice.get(i).getFaceValue();
            }
        }else
            System.out.println("Invalid number of dice.  Must be integer between 1 and 3");
        Arrays.sort(results);
        return results;
    }

    public void addTerritory(Territory territory){
        conqueredTerritories.add(territory);
        territory.setCurrentOccupant(this);
    }

    public void addContinent(Continent continent){
        conqueredContinents.add(continent);
        continent.setRuler(this);
    }

    public void removeTerritory(Territory territory){
        conqueredTerritories.remove(territory);
    }

    public void removeContinent(Continent continent){
        conqueredContinents.remove(continent);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Die> getPlayerDice() {
        return playerDice;
    }

    public Territory getCurrentTerritory() {
        return currentTerritory;
    }

    public void setCurrentTerritory(Territory currentTerritory) {
        this.currentTerritory = currentTerritory;
    }

    public Territory getCurrentTerritoryToAttack() {
        return currentTerritoryToAttack;
    }

    public void setCurrentTerritoryToAttack(Territory currentTerritoryToAttack) {
        this.currentTerritoryToAttack = currentTerritoryToAttack;
    }

    public Territory getCurrentTerritoryFortifyTo() {
        return currentTerritoryFortifyTo;
    }

    public void setCurrentTerritoryFortifyTo(Territory currentTerritoryFortifyTo) {
        this.currentTerritoryFortifyTo = currentTerritoryFortifyTo;
    }

    public void resetCurrentTerritoryFortifyTo() {
        currentTerritoryFortifyTo = null;
    }

    public void resetCurrentTerritoryToAttack(){
        currentTerritoryToAttack = null;
    }

    public void resetPlayerDice(){
        for(Die die : playerDice){
            die.setFaceValue(0);
        }
    }

    public void resetCurrentTerritory(){
        currentTerritory = null;
    }

    public void updateTroopLevel(Territory territory, int troops){
        int index = conqueredTerritories.indexOf(territory);
        conqueredTerritories.get(index).updateTroopCount(troops);
    }

    public ArrayList<Territory> getConqueredTerritories() {
        return conqueredTerritories;
    }

    public ArrayList<Continent> getConqueredContinents() {
        return conqueredContinents;
    }

    public boolean ownsTerritory(Territory territory){
        return conqueredTerritories.contains(territory);
    }

    public boolean ownsContinent(Continent continent){
        return conqueredContinents.contains(continent);
    }

    public int getNumberOfTerritories(){
        return conqueredTerritories.size();
    }

    public int getNumberOfArmiesToRecieve(){
        int armies = getNumberOfTerritories()/ 3;
        if(armies < 3){
            armies = 3;
        }

        for(Continent continent : conqueredContinents){
            armies += continent.getBonus();
        }
        return armies;
    }

    public void resetAllSelections(){
        resetCurrentTerritoryFortifyTo();
        resetCurrentTerritoryToAttack();
        resetCurrentTerritory();
    }

    public void setConqueredTerritories(Territory[] territories) {
        conqueredTerritories.addAll(Arrays.asList(territories));
        for(Territory t : conqueredTerritories){
            t.setCurrentOccupant(this);
        }
    }

    public void setConqueredContinents(Continent[] continents) {
        conqueredContinents.addAll(Arrays.asList(continents));
        for(Continent c : conqueredContinents){
            c.setRuler(this);
        }
    }

    public void setTerritoryColors(){
        for(Territory t : conqueredTerritories){
            t.setColorStandard();
        }
    }


    public void setBanner(ImageView banner) {
        banner.setFitHeight(130);
        banner.setFitWidth(240);
        banner.setLayoutX(1211);
        banner.setLayoutY(26);

        if(name == "Baratheon"){
            Image img = new Image("House Baratheon.png");
            banner.setImage(img);
        }else if(name == "Greyjoy") {
            Image img = new Image("House Greyjoy.png");
            banner.setImage(img);
        }else if(name == "Lannister") {
            Image img = new Image("House Lannister.png");
            banner.setImage(img);
        }else if(name == "Martell") {
            Image img = new Image("House Martell.png");
            banner.setImage(img);
        }else if(name == "Targaryen") {
            Image img = new Image("House Targaryen.png");
            banner.setImage(img);
        }else{
            Image img = new Image("House Tully.png");
            banner.setImage(img);
        }
    }



}

