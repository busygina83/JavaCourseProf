package application;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConn {
    MongoDatabase SetConn() {
        //Creating a MongoDB client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Connecting to the database
        MongoDatabase database = mongo.getDatabase("test");
        return database;
    }
}
