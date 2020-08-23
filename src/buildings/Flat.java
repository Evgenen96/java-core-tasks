package buildings;

import interfaces.Space;

public class Flat implements Space {

    private double area;
    private int rooms;
    private final static int DEF_ROOMCOUNT = 2;
    private final static int DEF_AREA = 50;

    public Flat() {
        this.area = DEF_AREA;
        this.rooms = DEF_ROOMCOUNT;
    }

    public Flat(double area) {
        Space.checkArea(area);
        this.area = area;
        this.rooms = DEF_ROOMCOUNT;
    }

    public Flat(double area, int roomsAmount) {
        Space.checkArea(area);
        Space.checkRooms(roomsAmount);
        this.area = area;
        this.rooms = roomsAmount;
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
        return rooms;
    }

    @Override
    public void setRoomsAmount(int roomsAmount) {
        Space.checkRooms(roomsAmount);
        this.rooms = roomsAmount;
    }


}
