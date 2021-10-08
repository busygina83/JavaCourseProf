package application;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Aggregates.*;

import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class CrudDocument {

    MongoConn conn = new MongoConn();
    MongoDatabase database = conn.SetConn();
    double id = 0;

    public MongoCollection<Document> CreateColl (String nameColl) {
        //Creating a collection
        try {
            database.createCollection(nameColl);
        } catch (Exception e) {
            if (e.getMessage().contains("collection already exists")) {
            }
        }
        return database.getCollection(nameColl);
    }

    public double MaxId (String autoId) {
        MongoCollection<Document> collection = CreateColl("GpsEmulator");
        System.out.println("autoId: "+autoId);
        double maxValue = 0;
        try {
            maxValue = Objects.requireNonNull(collection.find(eq("autoId", autoId)).
                    sort(Sorts.descending("id")).first()).getDouble("id");
            System.out.println("maxValue1: "+maxValue);
        } catch (Exception e) {
            if (e.getMessage().contains("No message available")) {
                maxValue = 0;
                System.out.println("maxValue2: "+maxValue);
            }
        }
        System.out.println("maxValue3: " + maxValue);
        return maxValue;
    }

    public void InsertGps(Point point) {
        CreateColl("GpsEmulator");
        //Preparing a document
        Document document = new Document();
        id = id + 1;
        document.append("id", id);
        document.append("autoid", point.autoId);
        document.append("time", point.getDateFormat(point.time));
        document.append("lat", point.lat);
        document.append("lon", point.lon);
        document.append("azimuth", point.azimuth);
        document.append("azimuthdelta", point.azimuthDelta);
        document.append("duration", point.duration);
        document.append("speed", point.speed);
        document.append("speedup", point.speedUp);
        //Inserting the document into the collection
        database.getCollection("GpsEmulator").insertOne(document);
        System.out.println("Document inserted successfully");
    }
    
    
}
