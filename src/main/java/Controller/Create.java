package Controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

import static java.util.Arrays.asList;

public class Create extends MongoConnection{
    public static void createGame(String title, double score) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            if(mygames.find(new Document("title",title)).first() == null){
                Document game = new Document("_id", new ObjectId());
                game.append("title",title)
                        .append("score",score);
                mygames.insertOne(game);

                System.out.println("Game inserted successfully:");
                System.out.println(game.toJson());
            } else {
                System.out.println("Game already in the collection!");
            }
        }
    }
    public static void createGame(String title, double score, String...developer) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            if(mygames.find(new Document("title",title)).first() == null){
                Document game = new Document("_id", new ObjectId());
                game.append("title",title)
                        .append("score",score)
                        .append("developer", asList(developer));
                mygames.insertOne(game);

                System.out.println("Game inserted successfully:");
                System.out.println(game.toJson());
            } else {
                System.out.println("Game already in the collection!");
            }
        }
    }
}
