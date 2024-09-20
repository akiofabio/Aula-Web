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
    
    public List<Reserva> getAll() {
        List<Reserva> listaReservas = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            MongoCollection<Document> collection = database.getCollection("reservas");

           
            FindIterable<Document> documents;
            documents = collection.find();
           
            // Crianto objetos Livro de forma iterativa
            for (Document document : documents) {
                ObjectId id = document.getObjectId("_id");
                String status = document.getString("status");
                Date dataReserva = document.getDate("dataReserva");
                
                UsuarioService usuarioService = new UsuarioService();
                Usuario usuarioTemp = new Usuario();
                usuarioTemp.setId(document.getObjectId("usuario").toString());
                Usuario usuario = usuarioService.getBy(usuarioTemp).getFirst();
                
                List<ObjectId> livrosId = document.getList("livros", ObjectId.class);
                List<Livro> livros = new ArrayList<>();
                for(ObjectId livroId : livrosId){
                    Livro livroTemp = new Livro();
                    livroTemp.setId(livroId.toString());
                    livros.add(livroService.getLivrosBy(livroTemp).getFirst());
                }
                
                Reserva reservaAchado = new Reserva(id.toString(),usuario,livros,dataReserva,status);
                listaReservas.add(reservaAchado);
            }
        } catch (Exception e) {
        }
        return listaReservas;
    }
    
    public List<Reserva> getBy(Reserva reserva) {
        List<Reserva> listaReservas = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            MongoCollection<Document> collection = database.getCollection("reservas");

            Document doc = new Document();
            if(reserva.getId()!= null){
                doc.append("_id", new ObjectId(reserva.getId()));
            }
            if(reserva.getStatus() != null){
                doc.append("status", reserva.getStatus());
            }
            if(reserva.getDataReserva()!= null){
                doc.append("dataReserva", reserva.getDataReserva());
            }
            if(reserva.getUsuario() != null){
                Usuario usuario = reserva.getUsuario();
                if(usuario.getId() != null ){
                    doc.append("usuario",  new ObjectId(usuario.getId()));
                }
                if(usuario.getNome() != null){
                   //Não implementado 
                }
                if(usuario.getNumeroIdentificacao() != null){
                   //Não implementado 
                }
            }
            if(reserva.getLivros()!= null){
                //Não implementado 
            }
            
            FindIterable<Document> documents;
            if(doc.isEmpty()){
                documents = collection.find();
            }
            else{
                documents = collection.find(doc);
            }
            for (Document document : documents) {
                ObjectId id = document.getObjectId("_id");
                String status = document.getString("status");
                Date dataReserva = document.getDate("dataReserva");
                
                UsuarioService usuarioService = new UsuarioService();
                Usuario usuarioTemp = new Usuario();
                usuarioTemp.setId(document.getObjectId("usuario").toString());
                Usuario usuario = usuarioService.getBy(usuarioTemp).getFirst();
                
                List<ObjectId> livrosId = document.getList("livros", ObjectId.class);
                List<Livro> livros = new ArrayList<>();
                for(ObjectId livroId : livrosId){
                    Livro livroTemp = new Livro();
                    livroTemp.setId(livroId.toString());
                    livros.add(livroService.getLivrosBy(livroTemp).getFirst());
                }
                
                Reserva reservaAchado = new Reserva(id.toString(),usuario,livros,dataReserva,status);
                listaReservas.add(reservaAchado);
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
            
            Set<Livro> livrosSemDuplicada = new LinkedHashSet<>(reserva.getLivros());
            for(Livro livro : livrosSemDuplicada){
                int count = Collections.frequency(livrosId, new ObjectId(livro.getId()) );
                Livro livroProcurar = new Livro();
                livroProcurar.setId(livro.getId());
                Livro livroTemp = livroService.getLivrosBy(livroProcurar).getFirst();
                if(livroTemp.getNumeroCopiasDisponiveis()<count){
                    return false;
                }
            }
            for(Livro livro : livrosSemDuplicada){
                int count = Collections.frequency(livrosId, new ObjectId(livro.getId()) );
                System.out.println("count= " + count);
                Livro livroProcurar = new Livro();
                livroProcurar.setId(livro.getId());
                Livro livroTemp = livroService.getLivrosBy(livroProcurar).getFirst();
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
            System.out.println(reserva.getId());
            Document queryDoc = new Document("_id",new ObjectId(reserva.getId()));
            Document documentReservaAntiga = collection.find(queryDoc).first();
            List<ObjectId> livrosAntigosId = documentReservaAntiga.getList("livros",ObjectId.class);
            System.out.println("livrosAntigosId: " + livrosAntigosId);
            List<ObjectId> livrosId = new ArrayList<>();
            for(Livro livro : reserva.getLivros()){
                livrosId.add(new ObjectId(livro.getId()));
            }
            
            Set<Livro> livrosSemDuplicada = new LinkedHashSet<>(reserva.getLivros());
            for(Livro livro : livrosSemDuplicada){
                int count = Collections.frequency(livrosId, new ObjectId(livro.getId()));
                int countAntigo = Collections.frequency(livrosAntigosId, new ObjectId(livro.getId()));
                int countTotal = count - countAntigo;
                Livro livroProcurar = new Livro();
                livroProcurar.setId(livro.getId());
                Livro livroTemp = livroService.getLivrosBy(livroProcurar).getFirst();
                if(countTotal>0 && livroTemp.getNumeroCopiasDisponiveis()<countTotal){
                    return false;
                }
            }
            for(Livro livro : livrosSemDuplicada){
                int count = Collections.frequency(livrosId, new ObjectId(livro.getId()) );
                int countAntigo = Collections.frequency(livrosAntigosId, new ObjectId(livro.getId()));
                int countTotal = count - countAntigo;
                if(countTotal!=0){
                    Livro livroProcurar = new Livro();
                    livroProcurar.setId(livro.getId());
                    Livro livroTemp = livroService.getLivrosBy(livroProcurar).getFirst();
                    livroTemp.setNumeroCopiasDisponiveis(livroTemp.getNumeroCopiasDisponiveis() - countTotal);
                    livroService.updateLivro(livroTemp);
                }
            }
            Set<ObjectId> livrosAntigosIdSemDuplicada = new LinkedHashSet<>(livrosAntigosId);

            for(ObjectId livroAntigoId : livrosAntigosIdSemDuplicada){
                if(!livrosId.contains(livroAntigoId)){
                    int count = Collections.frequency(livrosAntigosId, livroAntigoId );
                    Livro livroProcurar = new Livro();
                    livroProcurar.setId(livroAntigoId.toString());
                    Livro livroTemp = livroService.getLivrosBy(livroProcurar).getFirst();
                    livroTemp.setNumeroCopiasDisponiveis(livroTemp.getNumeroCopiasDisponiveis() + count);
                    livroService.updateLivro(livroTemp);
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
}
