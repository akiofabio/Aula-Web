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
import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Multa;
import com.umc.bibliotecav2web.model.Pagamento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
public class PagamentoService {
    String profMongo = "mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/";
    String myMongo = "mongodb+srv://web:123@cluster0.ahyoi.mongodb.net/";
    String dataBaseString = "biblioteca";
    String collectionString = "multas";
    
    final MultaService multaService = new MultaService();
    public List<Pagamento> getAll() {
        List<Pagamento> lista = new ArrayList<>();
        // Conecte-se ao MongoDB
        try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
        // Obtenha conexão ao banco de dados
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            // Obtenha a coleção de livros
            MongoCollection<Document> collection = database.getCollection(collectionString);
            // Consultando todos os documentos na coleção
            FindIterable<Document> documents = collection.find();
            // Crianto objetos Livro de forma iterativa
            for (Document document : documents) {
                ObjectId id = document.getObjectId("_id");
                Double valor = document.getDouble("valor");
                Date data = document.getDate("data");
                String meioPagamento = document.getString("meioPagamento");
                ObjectId multIid = document.getObjectId("multa");
                
                Document emprestimoQuery = new Document("_id",multIid);
                Multa multa = multaService.getBy(emprestimoQuery).getFirst();
                
                Pagamento pagamento = new Pagamento(id.toString(), valor, data, meioPagamento, multa);
                lista.add(pagamento);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Pagamento> getBy(Document query) {
        List<Pagamento> lista = new ArrayList<>();
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
            // Crianto objetos Livro de forma iterativa
            for (Document document : documents) {
                ObjectId id = document.getObjectId("_id");
                Double valor = document.getDouble("valor");
                Date data = document.getDate("data");
                String meio = document.getString("meio");
                ObjectId multIid = document.getObjectId("multa");
                
                Document emprestimoQuery = new Document("_id",multIid);
                Multa multa = multaService.getBy(emprestimoQuery).getFirst();
                
                Pagamento pagamento = new Pagamento(id.toString(), valor, data, meio, multa);
                lista.add(pagamento);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public void newPagamento(Pagamento pagamento) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            // Obtenha conexão ao banco de dados
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            // Obtenha a coleção de livros
            MongoCollection<Document> collection = database.getCollection(collectionString);
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return;
            }

           Document doc = new Document("valor", pagamento.getValor())
                    .append("data", pagamento.getData())
                    .append("meio", pagamento.getMeio());
            collection.insertOne(doc);
        }
    }

    public void update(Pagamento pagamento) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            // Obtenha conexão ao banco de dados
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            // Obtenha a coleção de livros
            MongoCollection<Document> collection = database.getCollection(collectionString);
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return;
            }

            Document queryDoc = new Document("_id",new ObjectId(pagamento.getId()));
            Document doc = new Document("valor", pagamento.getValor())
                    .append("data", pagamento.getData())
                    .append("meio", pagamento.getMeio());
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
