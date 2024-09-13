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

public class LivroService {

        public List<Livro> getAllLivros() {
            List<Livro> listaDeLivros = new ArrayList<>();
            // Conecte-se ao MongoDB
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/")) {
                // Obtenha conexão ao banco de dados
                MongoDatabase database = mongoClient.getDatabase("biblioteca");
                // Obtenha a coleção de livros
                MongoCollection<Document> collection = database.getCollection("livros");
                // Consultando todos os documentos na coleção
                FindIterable<Document> documents = collection.find();
                // Crianto objetos Livro de forma iterativa
                for (Document document : documents) {
                    int id = document.getInteger("_id");
                    String titulo = document.getString("titulo");
                    String autor = document.getString("autor");
                    int anoPublicacao = document.getInteger("anoPublicacao");
                    int numeroCopiasDisponiveis = document.getInteger("numeroCopiasDisponiveis");
                    Livro livro = new Livro(id,titulo, autor, anoPublicacao, numeroCopiasDisponiveis);
                    listaDeLivros.add(livro);
                }
            } catch (Exception e) {
            }
            return listaDeLivros;
        }

        public void newLivro(Livro livro) {
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/")) {
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
        
         public void deleteLivro(int id) {
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/")) {
                // Obtenha conexão ao banco de dados
                MongoDatabase database = mongoClient.getDatabase("biblioteca");
                // Obtenha a coleção de livros
                MongoCollection<Document> collection = database.getCollection("livros");
                if (collection == null) {
                    System.out.println("A coleção não foi inicializada corretamente.");
                    return;
                }
                collection.
                Document doc = new Document("titulo", livro.getTitulo())
                              .append("autor", livro.getAutor())
                              .append("anoPublicacao", livro.getAnoPublicacao())
                              .append("numeroCopiasDisponiveis", livro.getNumeroCopiasDisponiveis());
                collection.insertOne(doc);
            }
        }
    
    }
