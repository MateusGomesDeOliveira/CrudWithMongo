package Controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class Delete extends MongoConnection{
    public static void deleteGame(String id) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            try {
                ObjectId obj = new ObjectId(id);
                if(mygames.find(new Document("_id",obj)).first() != null){
                    System.out.println("Game deleted successfully:");
                    System.out.println(mygames.find(new Document("_id",obj)).first());
                    mygames.deleteOne(eq("_id",obj));
                } else {
                    System.out.println("Game not found!");
                }
            } catch (Exception e) {
                System.out.println("Not a valid ID!");
            }

        }
    }
}
