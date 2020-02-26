/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import GUI.MapEvent;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 *
 * @author moetez
 */
public class GoogleMapsAPI {
    private JSObject doc;
    private EventHandler<MapEvent> onMapLatLngChanged;
    private WebEngine webEngine;
    private boolean ready;
    private WebView mapView;
    private double latitude, longitude;

    public GoogleMapsAPI(WebView mapView) {
        this.mapView=mapView;
    }
     
    public void initMap() {
        webEngine = mapView.getEngine();
        webEngine.load(getClass().getResource("map1.html").toExternalForm());
        ready = false;
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue,
                    final Worker.State oldState,
                    final Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    ready = true;
                }
            }
        });
       
        initCommunication();
              //switchHybrid();
       //switchTerrain();
        latitude=0;
        longitude=0;
    }

    private void initCommunication() {
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue,
                    final Worker.State oldState,
                    final Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    doc = (JSObject) webEngine.executeScript("window");
                    doc.setMember("app", GoogleMapsAPI.this);
                }
            }
        });
    }
 
    private void invokeJS(final String str) {
        if (ready) {
            doc.eval(str);
        } else {
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                public void changed(final ObservableValue<? extends Worker.State> observableValue,
                        final Worker.State oldState,
                        final Worker.State newState) {
                    if (newState == Worker.State.SUCCEEDED) {
                        doc.eval(str);
                    }
                }
            });
        }
    }

    public void setOnMapLatLngChanged(EventHandler<MapEvent> eventHandler) {
        onMapLatLngChanged = eventHandler;
    }

    public void handle(double lat, double lng) {
        latitude = lat;
        longitude = lng;
        System.out.println("latitude "+ lat);
        System.out.println("longitude "+ lng);
        if (onMapLatLngChanged != null) {
            MapEvent event = new MapEvent(this, lat, lng);
            onMapLatLngChanged.handle(event);
        }
    }

    public void setMarkerPosition(double lat, double lng) {
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setMarkerPosition(" + sLat + ", " + sLng + ")");
    }

    public void setMapCenter(double lat, double lng) {
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setMapCenter(" + sLat + ", " + sLng + ")");
    }

    public void switchSatellite() {
        invokeJS("switchSatellite()");
    }

    public void switchRoadmap() {
        invokeJS("switchRoadmap()");
    }

    public void switchHybrid() {
        invokeJS("switchHybrid()");
    }

    public void switchTerrain() {
        invokeJS("switchTerrain()");
    }

    public void startJumping() {
        invokeJS("startJumping()");
    }

    public void stopJumping() {
        invokeJS("stopJumping()");
    }

    public void setHeight(double h) {
        mapView.setPrefHeight(h);
    }

    public void setWidth(double w) {
        mapView.setPrefWidth(w);
    }

    public ReadOnlyDoubleProperty widthProperty() {
        return mapView.widthProperty();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
}