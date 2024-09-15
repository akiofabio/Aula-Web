/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umc.bibliotecav2web.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
public class ReservaService {
    String profMongo = "mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/";
    String myMongo = "mongodb+srv://web:123@cluster0.ahyoi.mongodb.net/";
    
    public List<Reserva> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                    
                    UsuarioService usuarioService = new UsuarioService();
                    Usuario usuarioTemp = usuarioService.getBy(usuario);
                    doc.append("usuario",  new ObjectId(usuario.getId()));
                }
            }
            if(reserva.getTitulo()!= null){
                doc.append("numeroCopiasDisponiveis", reserva.getNumeroCopiasDisponiveis());
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
                Reserva reservaAchado = new Reserva(id.toString(),titulo, autor, anoPublicacao, numeroCopiasDisponiveis);
                listaReservas.add(reservaAchado);
            }
        } catch (Exception e) {
        }
        return listaReservas;
    }
    
}
