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
import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.model.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
public class ReservaService {
    String profMongo = "mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/";
    String myMongo = "mongodb+srv://web:123@cluster0.ahyoi.mongodb.net/";
    
    final LivroService livroService = new LivroService();
    final UsuarioService usuarioService = new UsuarioService();
    
    public List<Reserva> getAll() {
        List<Reserva> listaReservas = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            MongoCollection<Document> collection = database.getCollection("reservas");

            System.out.println("T");
            
            FindIterable<Document> documents;
            documents = collection.find();
            System.out.println("T1");
            for (Document document : documents) {
                System.out.println("T2 - document = " + document);
                ObjectId id = document.getObjectId("_id");
                ObjectId usuarioId = document.getObjectId("usuario");
                List<ObjectId> livrosId = document.getList("livros", ObjectId.class);
                Date dataReserva = document.getDate("dataReserva");
                String status = document.getString("status");
                
                System.out.println("T2.2");
                
                Document queryUsuario = new Document("_id", usuarioId);
                Usuario usuario = usuarioService.getBy(queryUsuario).getFirst();
                
                System.out.println("T3 - usuario = " + usuario);

                List<Livro> livros = new ArrayList<>();
                for(ObjectId livroId : livrosId){
                    Document livroQuery = new Document("_id",livroId);
                    Livro livro = livroService.getBy(livroQuery).getFirst();
                    livros.add(livro);
                    System.out.println("T4 - Livro = " + livro);
                }
                
                Reserva reservaAchado = new Reserva(id.toString(),usuario,livros,dataReserva,status);
                listaReservas.add(reservaAchado);
            }
        } catch (Exception e) {
        }
        return listaReservas;
    }
    
    public List<Reserva> getBy(Document query) {
        List<Reserva> listaReservas = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            MongoCollection<Document> collection = database.getCollection("reservas");
            System.out.println("______________Teste - ResevaService - getBy()_______________");
            FindIterable<Document> documents;
            if(query.isEmpty()){
                documents = collection.find();
            }
            else{
                documents = collection.find(query);
            }
            System.out.println("t1");

            for (Document document : documents) {
                System.out.println("t2");
                ObjectId id = document.getObjectId("_id");
                String status = document.getString("status");
                Date dataReserva = document.getDate("dataReserva");
                ObjectId usuarioId = document.getObjectId("usuario");
                List<ObjectId> livrosId = document.getList("livros", ObjectId.class);
                System.out.println("t3");

                Document queryUsuario = new Document("_id", usuarioId);
                Usuario usuario = usuarioService.getBy(queryUsuario).getFirst();
                
                System.out.println("t4");

                List<Livro> livros = new ArrayList<>();
                for(ObjectId livroId : livrosId){
                    Document livroQuery = new Document("_id",livroId);
                    Livro livro = livroService.getBy(livroQuery).getFirst();
                    livros.add(livro);
                }
                System.out.println("t5");

                Reserva reservaAchado = new Reserva(id.toString(),usuario,livros,dataReserva,status);
                listaReservas.add(reservaAchado);
                System.out.println("t6");

            }
        } catch (Exception e) {
        }
        return listaReservas;
    }
    
    public boolean newReserva(Reserva reserva) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            MongoCollection<Document> collection = database.getCollection("reservas");
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return false;
            }
            
            List<ObjectId> livrosId = new ArrayList<>();
            for(Livro livro : reserva.getLivros()){
                livrosId.add(new ObjectId(livro.getId()));
            }
            
            Set<ObjectId> livrosIdSemDuplicada = new LinkedHashSet<>(livrosId);
            for(ObjectId livroId : livrosIdSemDuplicada){
                int count = Collections.frequency(livrosId, livroId);
                
                Document queryLivro = new Document("_id",livroId);
                Livro livroTemp = livroService.getBy(queryLivro).getFirst();
                
                if(livroTemp.getNumeroCopiasDisponiveis()<count){
                    return false;
                }
            }
            for(ObjectId livroId : livrosIdSemDuplicada){
                int count = Collections.frequency(livrosId, livroId);
                
                Document queryLivro = new Document("_id",livroId);
                Livro livroTemp = livroService.getBy(queryLivro).getFirst();
                
                livroTemp.setNumeroCopiasDisponiveis(livroTemp.getNumeroCopiasDisponiveis() - count);
                livroService.updateLivro(livroTemp);
            }
            
            Document doc = new Document("usuario", new ObjectId(reserva.getUsuario().getId()))
                    .append("livros",livrosId)
                    .append("dataReserva", reserva.getDataReserva())
                    .append("status", reserva.getStatus());
            collection.insertOne(doc);
            return true;
        }
    }

    public boolean updateReserva(Reserva reserva) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            MongoCollection<Document> collection = database.getCollection("reservas");
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return false;
            }
            Document queryDoc = new Document("_id",new ObjectId(reserva.getId()));
            Document documentReservaAntiga = collection.find(queryDoc).first();
            List<ObjectId> livrosAntigosId = documentReservaAntiga.getList("livros",ObjectId.class);
            List<ObjectId> livrosId = new ArrayList<>();
            for(Livro livro : reserva.getLivros()){
                livrosId.add(new ObjectId(livro.getId()));
            }
            
            List<ObjectId> livrosIdSemDuplicada = new ArrayList<>(new HashSet<>(livrosId)) ;
            for(ObjectId livroId : livrosIdSemDuplicada){
                int count = Collections.frequency(livrosId, livroId);
                int countAntigo = Collections.frequency(livrosAntigosId, livroId);
                int countTotal = count - countAntigo;
                
                Document livroQuery = new Document("_id",livroId);
                Livro livro = livroService.getBy(livroQuery).getFirst();
                if(countTotal>0 && livro.getNumeroCopiasDisponiveis()<countTotal){
                    return false;
                }
            }
            for(ObjectId livroId : livrosIdSemDuplicada){
                int count = Collections.frequency(livrosId, livroId);
                int countAntigo = Collections.frequency(livrosAntigosId, livroId);
                int countTotal = count - countAntigo;
                if(countTotal!=0){
                    Document livroQuery = new Document("_id",livroId);
                    Livro livro = livroService.getBy(livroQuery).getFirst();
                    livro.setNumeroCopiasDisponiveis(livro.getNumeroCopiasDisponiveis() - countTotal);
                    livroService.updateLivro(livro);
                }
            }
            Set<ObjectId> livrosAntigosIdSemDuplicada = new LinkedHashSet<>(livrosAntigosId);

            for(ObjectId livroAntigoId : livrosAntigosIdSemDuplicada){
                if(!livrosId.contains(livroAntigoId)){
                    int count = Collections.frequency(livrosAntigosId, livroAntigoId );
                    Document livroQuery = new Document("_id",livroAntigoId);
                    Livro livro = livroService.getBy(livroQuery).getFirst();
                    livro.setNumeroCopiasDisponiveis(livro.getNumeroCopiasDisponiveis() - count);
                    livroService.updateLivro(livro);
                }
            }
            Document doc = new Document("usuario", new ObjectId(reserva.getUsuario().getId()))
                    .append("livros",livrosId)
                    .append("dataReserva", reserva.getDataReserva())
                    .append("status", reserva.getStatus());
            
            Document setDoc = new Document("$set", doc);
            collection.updateOne(queryDoc,setDoc);
            return true;
        }
    }
    
    public void delete(String id) {
        try (MongoClient mongoClient = MongoClients.create(myMongo)) {
            // Obtenha conexão ao banco de dados
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            // Obtenha a coleção de livros
            MongoCollection<Document> collection = database.getCollection("reservas");
            if (collection == null) {
                System.out.println("A coleção não foi inicializada corretamente.");
                return;
            }
            Document doc = new Document("_id", new ObjectId(id));
            collection.deleteOne(doc);
        }
    }
}
