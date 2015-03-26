package com.hdg.rjra.rdb.test.geo;

/**
 * Created by Administrator on 2015/3/25.
 */
public class AreaGeoResult {
    private GeoLocation location;
    private AddressComponent addressComponent;

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }
}
