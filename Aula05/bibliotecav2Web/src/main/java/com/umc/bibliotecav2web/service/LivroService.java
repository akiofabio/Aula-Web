package com.umc.bibliotecav2web.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.umc.bibliotecav2web.model.Livro;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class LivroService {
        String profMongo = "mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/";
        String myMongo = "mongodb+srv://web:123@cluster0.ahyoi.mongodb.net/";

        public List<Livro> getAllLivros() {
            List<Livro> listaDeLivros = new ArrayList<>();
            // Conecte-se ao MongoDB
            try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
            // Obtenha conexão ao banco de dados
                MongoDatabase database = mongoClient.getDatabase("biblioteca");
                // Obtenha a coleção de livros
                MongoCollection<Document> collection = database.getCollection("livros");
                // Consultando todos os documentos na coleção
                FindIterable<Document> documents = collection.find();
                // Crianto objetos Livro de forma iterativa
                for (Document document : documents) {
                    ObjectId id = document.getObjectId("_id");
                    String titulo = document.getString("titulo");
                    String autor = document.getString("autor");
                    int anoPublicacao = document.getInteger("anoPublicacao");
                    int numeroCopiasDisponiveis = document.getInteger("numeroCopiasDisponiveis");
                    Livro livro = new Livro(id.toString(),titulo, autor, anoPublicacao, numeroCopiasDisponiveis);
                    listaDeLivros.add(livro);
                }
            } catch (Exception e) {
            }
            return listaDeLivros;
        }
        
        public List<Livro> getLivrosBy(Livro livro) {
            List<Livro> listaDeLivros = new ArrayList<>();
            try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
                MongoDatabase database = mongoClient.getDatabase("biblioteca");
                MongoCollection<Document> collection = database.getCollection("livros");
                
                Document doc = new Document();
                if(livro.getId()!= null){
                    doc.append("_id", new ObjectId(livro.getId()));
                }
                if(livro.getTitulo()!= null){
                    doc.append("titulo", livro.getTitulo());
                }
                if(livro.getTitulo()!= null){
                    doc.append("autor", livro.getAutor());
                }
                if(livro.getTitulo()!= null){
                    doc.append("anoPublicacao", livro.getAnoPublicacao());
                }
                if(livro.getTitulo()!= null){
                    doc.append("numeroCopiasDisponiveis", livro.getNumeroCopiasDisponiveis());
                }
                
                FindIterable<Document> documents;
                if(doc.isEmpty()){
                    documents = collection.find();
                }
                else{
                    documents = collection.find(doc);
                }
                // Crianto objetos Livro de forma iterativa
                for (Document document : documents) {
                    ObjectId id = document.getObjectId("_id");
                    String titulo = document.getString("titulo");
                    String autor = document.getString("autor");
                    int anoPublicacao = document.getInteger("anoPublicacao");
                    int numeroCopiasDisponiveis = document.getInteger("numeroCopiasDisponiveis");
                    Livro livroAchado = new Livro(id.toString(),titulo, autor, anoPublicacao, numeroCopiasDisponiveis);
                    listaDeLivros.add(livroAchado);
                }
            } catch (Exception e) {
            }
            return listaDeLivros;
        }
        
        public void newLivro(Livro livro) {
            try (MongoClient mongoClient = MongoClients.create(myMongo)) {
                // Obtenha conexão ao banco de dados
                MongoDatabase database = mongoClient.getDatabase("biblioteca");
                // Obtenha a coleção de livros
                MongoCollection<Document> collection = database.getCollection("livros");
                if (collection == null) {
                    System.out.println("A coleção não foi inicializada corretamente.");
                    return;
                }

                Document doc = new Document("titulo", livro.getTitulo())
                        .append("autor", livro.getAutor())
                        .append("anoPublicacao", livro.getAnoPublicacao())
                        .append("numeroCopiasDisponiveis", livro.getNumeroCopiasDisponiveis());
                collection.insertOne(doc);
            }
        }
        
        public void updateLivro(Livro livro) {
            try (MongoClient mongoClient = MongoClients.create(myMongo)) {
                // Obtenha conexão ao banco de dados
                MongoDatabase database = mongoClient.getDatabase("biblioteca");
                // Obtenha a coleção de livros
                MongoCollection<Document> collection = database.getCollection("livros");
                if (collection == null) {
                    System.out.println("A coleção não foi inicializada corretamente.");
                    return;
                }
                
                Document queryDoc = new Document("_id",new ObjectId(livro.getId()));
                Document doc = new Document("titulo", livro.getTitulo())
                        .append("autor", livro.getAutor())
                        .append("anoPublicacao", livro.getAnoPublicacao())
                        .append("numeroCopiasDisponiveis", livro.getNumeroCopiasDisponiveis());
                Document setDoc = new Document("$set", doc);
                collection.updateOne(queryDoc,setDoc);
            }
        }
        
        public void deleteLivro(String id) {
            try (MongoClient mongoClient = MongoClients.create(myMongo)) {
                // Obtenha conexão ao banco de dados
                MongoDatabase database = mongoClient.getDatabase("biblioteca");
                // Obtenha a coleção de livros
                MongoCollection<Document> collection = database.getCollection("livros");
                if (collection == null) {
                    System.out.println("A coleção não foi inicializada corretamente.");
                    return;
                }
                Document doc = new Document("_id", new ObjectId(id));
                collection.deleteOne(doc);
            }
        }
        
    }
