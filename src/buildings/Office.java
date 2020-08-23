package buildings;

import interfaces.Space;

public class Office implements Space {

    private double area;
    private int roomsAmount;
    private final static int DEF_AREA = 250;
    private final static int DEF_ROOMSAMOUNT = 1;

    public Office() {
        this.area = DEF_AREA;
        this.roomsAmount = DEF_ROOMSAMOUNT;
    }

    public Office(double area) {
        Space.checkArea(area);
        this.area = area;
        this.roomsAmount = DEF_ROOMSAMOUNT;
    }

    public Office(double area, int roomsAmount) {
        Space.checkArea(area);
        Space.checkRooms(roomsAmount);
        this.area = area;
        this.roomsAmount = roomsAmount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void setArea(double area) {
        Space.checkArea(area);
        this.area = area;
    }

    @Override
    public int getRoomsAmount() {
       return roomsAmount;
    }

    @Override
    public void setRoomsAmount(int roomsAmount) {
        Space.checkRooms(roomsAmount);
        this.roomsAmount = roomsAmount;
    }
}
