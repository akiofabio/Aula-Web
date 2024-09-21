/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aulaweb.r1parcial.service;

import com.aulaweb.r1parcial.model.Aluno;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
public class AlunoService {
    //String de conexão ao MongoDB
    final String codigoMongoDB = "mongodb+srv://web:123@cluster0.qd5ua.mongodb.net/";
    
    //Metodo para salvar um novo aluno no BD
    public void adicionarAluno(Aluno aluno){
        //Tenta conectar ao DB
        try (MongoClient mongoClient = MongoClients.create(  codigoMongoDB)){
            //Obtem a conexão ao banco de dados gestaoAluno
            MongoDatabase database = mongoClient.getDatabase("gestaoAluno");
            //Obtem a coleção de alunos
            MongoCollection<Document> collection = database.getCollection("alunos");
            //Verifica se a conexão foi iniciada com sucesso
            if (collection == null) {
                    System.out.println("A coleção não foi inicializada corretamente.");
                    return;
            }
            //Cria um documento que paria a chave(nome da coluna no BD) com os atributos a serem salvos
            Document doc = new Document("nome", aluno.getNome())
                        .append("email", aluno.getEmail())
                        .append("curso", aluno.getCurso())
                        .append("anoDeIngresso", aluno.getAnoDeIngresso());
            //Insere os dados do doc no DB
            collection.insertOne(doc);
        }catch (Exception e) {
            //Trata dos erros ocorridos
        }
    }
    
    //Metodo que retorna a lista com todos os aluno no BD
    public ArrayList<Aluno> listarAlunos(){
        //Inicia um ArrayList em branco
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        
        //Tenta conectar ao DB
        try (MongoClient mongoClient = MongoClients.create(  codigoMongoDB)){
            //Obtem a conexão ao banco de dados gestaoAluno
            MongoDatabase database = mongoClient.getDatabase("gestaoAluno");
            //Obtem a coleção de alunos
            MongoCollection<Document> collection = database.getCollection("alunos");
            //Consulta todos os alunos na coleção "alunos"
            FindIterable<Document> documents = collection.find();
            
            //Instancia os objetos Alunos de forma iterativa para todos os alunos encontrados
            for (Document document : documents) {
                //Pega o ObjectId(hexadecimal) com a chave "_id" e o Transforma para int e atribui ao id
                int id = Integer.parseInt(document.getObjectId("_id").toString(), 16);
                //Pega o String com a chave "nome" atribui a String nome
                String nome = document.getString("nome");
                //Pega o String com a chave "email" atribui a String email
                String email = document.getString("email");
                //Pega o String com a chave "curso" atribui a String curso
                String curso = document.getString("curso");
                //Pega o int com a chave "anoDeIngresso" atribui ao int anoDeIngresso
                int anoDeIngresso = document.getInteger("anoDeIngresso");
                //Instancia um novo object Aluno com os atributos definidos acima
                Aluno aluno = new Aluno(id,nome, email, curso, anoDeIngresso);
                //Adiciona o aluno criado acima a lista de alunos que retornara do metodo
                listaAlunos.add(aluno);
            }
        }catch (Exception e) {
            //Trata dos erros ocorridos
        }
        return listaAlunos;
    }
    
    //Metodo que retorna o aluno com o id especifico do BD ou null se não achar
    public Aluno buscarAluno(int id){
        //Inicia o aluno como null caso não ache o aluno
        Aluno aluno = null;
        //Tenta conectar ao DB
        try (MongoClient mongoClient = MongoClients.create(  codigoMongoDB)){
            //Obtem a conexão ao banco de dados gestaoAluno
            MongoDatabase database = mongoClient.getDatabase("gestaoAluno");
            //Obtem a coleção de alunos
            MongoCollection<Document> collection = database.getCollection("alunos");
            //Cria o documento com a query para achar o aluno pelo id
            Document query = new Document("_id",id);
            //consulta pelo aluno com o id especifico e pega o primeiro, pois como o _id é unico deveria ter apenas um aluno com aquele id
            Document document = collection.find(query).first();
            
            //Instancia o objeto Aluno 
            //Pega o ObjectId(hexadecimal) com a chave "_id" e o Transforma para int e atribui ao id
            id = Integer.parseInt(document.getObjectId("_id").toString(), 16);
            //Pega o String com a chave "nome" atribui a String nome
            String nome = document.getString("nome");
            //Pega o String com a chave "email" atribui a String email
            String email = document.getString("email");
            //Pega o String com a chave "curso" atribui a String curso
            String curso = document.getString("curso");
            //Pega o int com a chave "anoDeIngresso" atribui ao int anoDeIngresso
            int anoDeIngresso = document.getInteger("anoDeIngresso");
            //Instancia um novo object Aluno com os atributos definidos acima
            aluno = new Aluno(id,nome, email, curso, anoDeIngresso);
        }catch (Exception e) {
            //Trata dos erros ocorridos
        }
        return aluno;
    }
    
    //Metodo para atualizar um aluno no BD
    public void atualizarAluno(Aluno aluno){
        //Tenta conectar ao DB
        try (MongoClient mongoClient = MongoClients.create(  codigoMongoDB)){
            //Obtem a conexão ao banco de dados gestaoAluno
            MongoDatabase database = mongoClient.getDatabase("gestaoAluno");
            //Obtem a coleção de alunos
            MongoCollection<Document> collection = database.getCollection("alunos");
            //Verifica se a conexão foi iniciada com sucesso
            if (collection == null) {
                    System.out.println("A coleção não foi inicializada corretamente.");
                    return;
            }
            
            //Cria o documento com a query para achar o aluno que vai ser atualizado pelo id
            Document query = new Document("_id",aluno.getId());
            //Cria um documento que paria a chave(nome da coluna no BD) com os atributos a serem atualizado
            Document doc = new Document("nome", aluno.getNome())
                        .append("email", aluno.getEmail())
                        .append("curso", aluno.getCurso())
                        .append("anoDeIngresso", aluno.getAnoDeIngresso());
            //Cria um documento que especifica quais atributos serão atualizados
            Document setDoc = new Document("$set", doc);
            //Atualiza os dados do doc no DB
            collection.updateOne(query,setDoc);
        }catch (Exception e) {
            //Trata dos erros ocorridos
        }
    }
    
    //Metodo que deleta o aluno com o id especificado do DB
    public void deletarAluno(int id){
        //Tenta conectar ao DB
        try (MongoClient mongoClient = MongoClients.create(  codigoMongoDB)){
            //Obtem a conexão ao banco de dados gestaoAluno
            MongoDatabase database = mongoClient.getDatabase("gestaoAluno");
            //Obtem a coleção de alunos
            MongoCollection<Document> collection = database.getCollection("alunos");
            //Cria o documento com a query para achar o aluno pelo id
            Document query = new Document("_id",id);
            //consulta pelo aluno com o id especifico e pega o primeiro, pois como o _id é unico deveria ter apenas um aluno com aquele id
            Document document = collection.find(query).first();
            
        }catch (Exception e) {
            //Trata dos erros ocorridos
        }
    }
}
