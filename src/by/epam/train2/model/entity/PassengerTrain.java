package by.epam.train2.model.entity;

import by.epam.train2.util.IdGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PassengerTrain{
    private int id;
    private String destination;
    private int[] departureTime;
    private int staffAmount;
    private List<Carriage> carriages;

    public PassengerTrain(String destination, int departureHour, int departureMinute, int staffAmount, int carriageCount) {
        id = IdGenerator.generateTrainId();
        setDestination(destination);
        setDepartureTime(departureHour, departureMinute);
        setStaffAmount(staffAmount);
        setCarriages(carriageCount);
    }

    public PassengerTrain(){
        id = IdGenerator.generateTrainId();
    }

    public PassengerTrain(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public int[] getDepartureTime() {
        return departureTime;
    }

    public int getDepartureHour(){
        return getDepartureTime()[0];
    }

    public int getDepartureMinute(){
        return getDepartureTime()[1];
    }

    public void setDepartureTime(int departureHour, int departureMinute) {
        if (departureHour >= 0 && departureHour <= 23 && departureMinute >= 0 && departureMinute < 60) {
            this.departureTime = new int[]{departureHour, departureMinute};
        } else {
            throw new IllegalArgumentException("illegal departure time");
        }
    }

    public List<Carriage> getCarriages() {
        return new ArrayList<>(carriages);
    }

    public void setCarriages(int carriageCount) {
        carriages = new ArrayList<>();
        for (int i = 0; i < carriageCount; i++) {
            if (i < carriageCount / 3) {
                carriages.add(new Carriage(i + 1, CarriageType.BUSINESS));
            } else {
                carriages.add( new Carriage(i + 1, CarriageType.ECONOM));
            }
        }

    }
    
    public void setCarriages(List<Carriage> carriages){
        this.carriages = carriages;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getStaffAmount() {
        return staffAmount;
    }

    public void setStaffAmount(int staffAmount) {
        this.staffAmount = staffAmount;
    }

    public PassengerTrain clone(){
        PassengerTrain train = new PassengerTrain(this.getId());
        train.setCarriages(this.getCarriages());
        train.setDepartureTime(this.getDepartureTime()[0], this.getDepartureTime()[1]);
        train.setDestination(this.getDestination());
        train.setStaffAmount(this.getStaffAmount());
        return train;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassengerTrain train = (PassengerTrain) o;

        if (getId() != train.getId()) return false;
        if (getStaffAmount() != train.getStaffAmount()) return false;
        if (getDestination() != null ? !getDestination().equals(train.getDestination()) : train.getDestination() != null)
            return false;
        if (!Arrays.equals(getDepartureTime(), train.getDepartureTime())) return false;
        return getCarriages() != null ? getCarriages().equals(train.getCarriages()) : train.getCarriages() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getDestination() != null ? getDestination().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getDepartureTime());
        result = 31 * result + getStaffAmount();
        result = 31 * result + (getCarriages() != null ? getCarriages().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PassengerTrain{");
        sb.append("destination='").append(destination).append('\'');
        sb.append(", departureTime=").append(departureTime[0]).append(":").append(departureTime[1]);
        sb.append(", staff amount=").append(staffAmount);
        sb.append('}');
        return sb.toString();
    }
}
