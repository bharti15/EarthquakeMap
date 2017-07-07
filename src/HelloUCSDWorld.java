import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import java.util.ArrayList;
import java.util.List;
import parsing.ParseFeed;

/**
 * Hello World!
 * 
 * This is the basic stub to start creating interactive maps.
 */
public class HelloUCSDWorld extends PApplet {

	UnfoldingMap map;
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.atom";

	public void setup() {
		size(800, 600, OPENGL);

//		map = new UnfoldingMap(this, new Google.GoogleTerrainProvider());
//		map.zoomAndPanTo(14, new Location(32.881, -117.238)); // UCSD
//
//		MapUtils.createDefaultEventDispatcher(this, map);
		
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
//		Location valloc = new Location(-38.14f, -73.03f);
//		Location alaska = new Location(-49.14f, -74.03f);
//		Location sumatra = new Location(-50.14f, -75.03f);
//		Location japan = new Location(-61.14f, -76.03f);
//		Location kamchatka = new Location(-72.14f, -77.03f);
//		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
//
//		PointFeature valEq = new PointFeature(valloc);
//		PointFeature alaskaEq = new PointFeature(alaska);
//		PointFeature sumatraEq = new PointFeature(sumatra);
//		PointFeature japanEq = new PointFeature(japan);
//		PointFeature kamchatkaEq = new PointFeature(kamchatka);
//		valEq.addProperty("title", "Valdivia, Chile");
//		valEq.addProperty("magnitude", "9.5");
//		valEq.addProperty("date", "May 22, 1960");
//		valEq.addProperty("year", "1960");
//		
//		alaskaEq.addProperty("title", "Valdivia, Chile");
//		alaskaEq.addProperty("magnitude", "9.5");
//		alaskaEq.addProperty("date", "May 22, 1960");
//		alaskaEq.addProperty("year", "1960");
//		
//		sumatraEq.addProperty("title", "Valdivia, Chile");
//		sumatraEq.addProperty("magnitude", "9.5");
//		sumatraEq.addProperty("date", "May 22, 1960");
//		sumatraEq.addProperty("year", "1960");
//		
//		japanEq.addProperty("title", "Valdivia, Chile");
//		japanEq.addProperty("magnitude", "9.5");
//		japanEq.addProperty("date", "May 22, 1960");
//		japanEq.addProperty("year", "1960");
//		
//		kamchatkaEq.addProperty("title", "Valdivia, Chile");
//		kamchatkaEq.addProperty("magnitude", "9.5");
//		kamchatkaEq.addProperty("date", "May 22, 1960");
//		kamchatkaEq.addProperty("year", "1960");
		
//		bigEqs.add(valEq);
//		bigEqs.add(alaskaEq);
//		bigEqs.add(sumatraEq);
//		bigEqs.add(japanEq);
//		bigEqs.add(kamchatkaEq);
//		
//		Marker valMk = new SimplePointMarker(valloc, valEq.getProperties());
//		Marker alaskaMk = new SimplePointMarker(alaska, alaskaEq.getProperties());
//		Marker sumatraMk = new SimplePointMarker(sumatra, sumatraEq.getProperties());
//		Marker japanMk = new SimplePointMarker(japan, japanEq.getProperties());
//		Marker kamchatkaMk = new SimplePointMarker(kamchatka, kamchatkaEq.getProperties());
//		map.addMarker(valMk);
//		map.addMarker(alaskaMk);
//		map.addMarker(sumatraMk);
//		map.addMarker(japanMk);
//		map.addMarker(kamchatkaMk);
		
		List<PointFeature> bigEqs = ParseFeed.parseEarthquake(this, earthquakesURL);
//		for(int i=0; i<bigEqs.size(); i++){
//			System.out.println(bigEqs.get(i));
//		}
		List<Marker> markers = new ArrayList<Marker>();
		for(PointFeature eq: bigEqs){
			markers.add(new SimplePointMarker(eq.getLocation(),eq.getProperties()));
//			System.out.println(eq.getLocation()+" "+eq.getProperties()+" "+eq.getProperty("depth"));
		}
		map.addMarkers(markers);
		
		
//		if(earthquakes.size()>0){
//			PointFeature f = earthquakes.get(0);
//			System.out.println(f.getProperties());
//			Object magOb = f.getProperty("magnitude");
//			float mag = Float.parseFloat(magOb.toString());
//		}
		
		int yellow = color(225,225,0);
		int gray = color(150,150,150);
		for (Marker mk: markers){
			if ((float)mk.getProperty("depth")>5.0){
				mk.setColor(yellow);
			}
			else{
				mk.setColor(gray);
			}
		}
		
	}
	
//	private SimplePointMarker createMarker(PointFeature feature){
//		return new SimplePointMarker(feature.getLocation());
//	}

	public void draw() {
		background(10);
		map.draw();
		addKey();
	}
	
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
	
	}

}
