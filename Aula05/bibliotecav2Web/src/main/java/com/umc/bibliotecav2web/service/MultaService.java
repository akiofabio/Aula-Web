/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umc.bibliotecav2web.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.umc.bibliotecav2web.model.Emprestimo;
import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Multa;
import com.umc.bibliotecav2web.model.Pagamento;
import com.umc.bibliotecav2web.model.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
public class MultaService {
    String profMongo = "mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/";
    String myMongo = "mongodb+srv://web:123@cluster0.ahyoi.mongodb.net/";
    String dataBaseString = "biblioteca";
    String collectionString = "multas";
    
    final EmprestimoService emprestimoService = new EmprestimoService();
    
    public List<Multa> getBy(Document query) {
        List<Multa> listaMultas = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            MongoCollection<Document> collection = database.getCollection(collectionString);

            FindIterable<Document> documents;
            if(query.isEmpty()){
                documents = collection.find();
            }
            else{
                documents = collection.find(query);
            }
            
            for (Document document : documents) {
                ObjectId id = document.getObjectId("_id");
                String status = document.getString("status");
                Double valor = document.getDouble("valor");
                Date data = document.getDate("data");
                ObjectId emprestimoId = document.getObjectId("emprestimo");
                
                Document emprestimoQuery = new Document("_id",emprestimoId);
                Emprestimo Emprestimo = emprestimoService.getBy(emprestimoQuery).getFirst();
                
                Multa multa = new Multa(id.toString(),status, valor, data, Emprestimo);
                listaMultas.add(multa);
            }
        } catch (Exception e) {
        }
        return listaMultas;
    }

    public void newMulta(Multa multa) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            MongoCollection<Document> collection = database.getCollection(collectionString);
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return;
            }

            Document doc = new Document("status", multa.getStatus())
                    .append("valor", multa.getValor())
                    .append("data", multa.getData())
                    .append("emprestimo", new ObjectId(multa.getEmprestimo().getId()));
            collection.insertOne(doc);
        }
    }

    public void update(Multa multa) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            // Obtenha conexão ao banco de dados
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            // Obtenha a coleção de livros
            MongoCollection<Document> collection = database.getCollection(collectionString);
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return;
            }

            Document queryDoc = new Document("_id",new ObjectId(multa.getId()));
            Document doc = new Document("status", multa.getStatus())
                    .append("valor", multa.getValor())
                    .append("data", multa.getData())
                    .append("emprestimo", new ObjectId(multa.getEmprestimo().getId()));
            Document setDoc = new Document("$set", doc);
            collection.updateOne(queryDoc,setDoc);
        }
    }

    public void delete(String id) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            // Obtenha conexão ao banco de dados
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            // Obtenha a coleção de livros
            MongoCollection<Document> collection = database.getCollection(collectionString);
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return;
            }
            Document doc = new Document("_id", new ObjectId(id));
            collection.deleteOne(doc);
        }
    }

}

