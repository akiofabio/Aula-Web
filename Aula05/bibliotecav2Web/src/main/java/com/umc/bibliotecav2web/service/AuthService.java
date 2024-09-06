package com.umc.bibliotecav2web.service;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class AuthService {
    public boolean authenticate(String username, String password) {
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/")) {
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            MongoCollection<Document> collection = database.getCollection("usuarios");
            
            // Query para encontrar o usuário com o username e password fornecidos
            Document query = new Document("username", username)
                                .append("password", password);
            
            // Procura pelo usuário no banco de dados
            Document user = collection.find(query).first();
            
            return user != null;
        }
    }
}
