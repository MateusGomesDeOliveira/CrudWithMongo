package Controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Updates.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;


public class Update extends MongoConnection{
    public static void updateGame(String id, String title, double score) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            List<Bson> updates = Arrays.asList(set("title",title),set("score",score));

            try {
                ObjectId obj = new ObjectId(id);
                if(mygames.find(new Document("_id",obj)).first() != null){
                    System.out.println("Game Updated from this:");
                    System.out.println(mygames.find(new Document("_id",obj)).first());
                    mygames.updateOne(eq("_id",obj),updates);
                    System.out.println("To this:");
                    System.out.println(mygames.find(new Document("_id",obj)).first());
                } else {
                    System.out.println("Game not found!");
                }
            } catch (Exception e) {
                System.out.println("Not a valid ID!");
            }

        }
    }
    public static void updateGame(String id, String title, double score, String...developer) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            List<Bson> updates = Arrays.asList(set("title",title),set("score",score),set("developer",asList(developer)));

            try {
                ObjectId obj = new ObjectId(id);
                if(mygames.find(new Document("_id",obj)).first() != null){
                    System.out.println("Game Updated from this:");
                    System.out.println(mygames.find(new Document("_id",obj)).first());
                    mygames.updateOne(eq("_id",obj),updates);
                    System.out.println("To this:");
                    System.out.println(mygames.find(new Document("_id",obj)).first());
                } else {
                    System.out.println("Game not found!");
                }

            } catch (Exception e) {
                System.out.println("Not a valid ID!");
            }

        }
    }
}