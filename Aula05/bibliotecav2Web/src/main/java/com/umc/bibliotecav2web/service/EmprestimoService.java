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
public class EmprestimoService {
    String profMongo = "mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/";
    String myMongo = "mongodb+srv://web:123@cluster0.ahyoi.mongodb.net/";
    String dataBaseString = "biblioteca";
    String collectionString = "emprestimos";
    
    final LivroService livroService = new LivroService();
    final UsuarioService usuarioService = new UsuarioService();
    final MultaService multaService = new MultaService();
    
    public List<Emprestimo> getBy(Document query) {
        List<Emprestimo> listaEmprestimos = new ArrayList<>();
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
                Date dataInicio = document.getDate("dataInicio");
                Date dataDevolucao = document.getDate("dataDevolucao");
                ObjectId livroId = document.getObjectId("livro");
                ObjectId usuarioId = document.getObjectId("usuario");
                String statusDevolucao = document.getString("statusDevolucao");
                ObjectId multaId = document.getObjectId("multa");
                
                Document livroQuery = new Document("_id",livroId);
                Livro livro = livroService.getBy(livroQuery).getFirst();
                
                Document usuarioQuery = new Document("_id", usuarioId);
                Usuario usuario = usuarioService.getBy(usuarioQuery).getFirst();
                
                Document multaQuery = new Document("_id", multaId);
                List<Multa> multas = multaService.getBy(multaQuery);
                Multa multa = null;
                if(!multas.isEmpty()){
                     multa = multas.getFirst();
                }
                
                Emprestimo emprestimoAchado = new Emprestimo(id.toString(),dataInicio, dataDevolucao, livro, usuario, statusDevolucao,multa);
                listaEmprestimos.add(emprestimoAchado);
            }
        } catch (Exception e) {
        }
        return listaEmprestimos;
    }
   
    public void newEmprestimo(Emprestimo emprestimo){
        try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            MongoCollection<Document> collection = database.getCollection(collectionString);
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return;
            }

            Document doc = new Document("dataInicio", emprestimo.getDataInicio())
                    .append("dataDevolucao", emprestimo.getDataDevolucao())
                    .append("livro", new ObjectId(emprestimo.getLivro().getId()))
                    .append("usuario", new ObjectId(emprestimo.getUsuario().getId()))
                    .append("statusDevolucao", emprestimo.getStatusDevolucao());
            collection.insertOne(doc);
        } catch (Exception e) {
        }
    }
    
    public void update(Emprestimo emprestimo) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            // Obtenha conexão ao banco de dados
            MongoDatabase database = mongoClient.getDatabase(dataBaseString);
            // Obtenha a coleção de livros
            MongoCollection<Document> collection = database.getCollection(collectionString);
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return;
            }

            Document queryDoc = new Document("_id",new ObjectId(emprestimo.getId()));
            Document doc = new Document("dataInicio", emprestimo.getDataInicio())
                    .append("dataDevolucao", emprestimo.getDataDevolucao())
                    .append("livro", new ObjectId(emprestimo.getLivro().getId()))
                    .append("usuario", new ObjectId(emprestimo.getUsuario().getId()))
                    .append("statusDevolucao", emprestimo.getStatusDevolucao());
            if(emprestimo.getMulta() != null){
                doc.append("multa", new ObjectId(emprestimo.getMulta().getId()));
            }
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
