package Controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.descending;

public class Read extends MongoConnection {
    public static void allGames() throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            mygames.find().sort(descending("score")).forEach(System.out::println);
        }
    }
    public static void findGameByTitle(String title) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            if(mygames.find(new Document("title",title)).first() != null){
                System.out.println(mygames.find(new Document("title",title)).first());
            } else {
                System.out.println("Game not found!");
            }
        }
    }
    public static void findGamesByDev(String dev) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            if(mygames.find(in("developer",dev)).first() != null){
                mygames.find(in("developer",dev)).sort(descending("score")).forEach(System.out::println);
            } else {
                System.out.println("Developer not found!");
            }
        }
    }
    public static void allGamesScoreLessThan(double score) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            mygames.find(lt("score",score)).sort(descending("score")).forEach(System.out::println);
        }
    }
    public static void allGamesScoreGreaterThan(double score) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            mygames.find(gt("score",score)).sort(descending("score")).forEach(System.out::println);
        }
    }
    public static String findIdWithTitle(String title) throws IOException {
        try(MongoClient mongoClient = MongoClients.create(uriString())){
            MongoDatabase database = mongoClient.getDatabase("games");
            MongoCollection<Document> mygames = database.getCollection("mygames");

            StringBuilder sb;

            if(mygames.find(new Document("title",title)).first() != null){
                sb = new StringBuilder(mygames.find(new Document("title", title))
                        .projection(Projections.include("_id"))
                        .first().toString());
                sb.delete(0,14);
                sb.delete(24,26);
            } else {
                return "Game not found!";
            }

            return sb.toString();
        }
    }
}
