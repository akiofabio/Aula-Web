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
import com.umc.bibliotecav2web.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
public class UsuarioService {
    
    String profMongo = "mongodb+srv://alunosumc:alunosumc_2024@biblioteca.jjf94mj.mongodb.net/";
    String myMongo = "mongodb+srv://web:123@cluster0.ahyoi.mongodb.net/";
    
    public List<Usuario> getBy(Document query) {
        List<Usuario> listaUsuario = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(  myMongo)){ 
            MongoDatabase database = mongoClient.getDatabase("biblioteca");
            MongoCollection<Document> collection = database.getCollection("usuarios");

            FindIterable<Document> documents;
            if(query.isEmpty()){
                documents = collection.find();
            }
            else{
                documents = collection.find(query);
            }
            
            for (Document document : documents) {
                ObjectId id = document.getObjectId("_id");
                String nome = document.getString("nome");
                String numeroIdentificacao = document.getString("numeroIdentificacao");
                Usuario usuarioAchado = new Usuario(id.toString(),nome, numeroIdentificacao);
                listaUsuario.add(usuarioAchado);
            }
        } catch (Exception e) {
        }
        return listaUsuario;
    }
}
