package service;

import java.util.*;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Cidadao;
import model.Usuario;

@Path("/{parameter: cidadaos}")
public class CidadaoService {

    /* Lista todas os cidadaos */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidadao> listaTodos() {
        EntityManager bd = util.JpaUtil.getEntityManager();
        ArrayList<Cidadao> cidadaos;
        String sql = "SELECT c FROM Cidadao c";
        Query q = bd.createQuery(sql);
        cidadaos = (ArrayList<Cidadao>) q.getResultList();
        bd.close();
        return cidadaos;
    }

    /* Lista cidadao por cpf */
    @Path("{cpf}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cidadao listaPeloId(@PathParam("cpf") String cpf) {
        EntityManager bd = util.JpaUtil.getEntityManager();
        ArrayList<Cidadao> cidadaos;
        Cidadao cidadao = null;
        String sql = "SELECT c FROM Cidadao c WHERE c.cpf = :cpf";
        Query query = bd.createQuery(sql, Cidadao.class);
        query.setParameter("cpf", cpf);
        cidadaos = (ArrayList<Cidadao>) query.getResultList();
        for (Cidadao linha : cidadaos) {
            cidadao = new Cidadao(linha.getCpf(), linha.getNome(), linha.getEmail(), linha.getNascimento(), linha.getEndereco(), linha.getBairro(), linha.getComplemento(), linha.getCep(), linha.getTelefone(), linha.getCelular(), linha.getUsuario());
        }
        bd.close();
        return cidadao;
    }

    /* Deleta cidadao */
    @Path("{cpf}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("cpf") String cpf) {
        EntityManager bd = util.JpaUtil.getEntityManager();
        try {
            //localizando o registro a ser removido
            Cidadao atividade = bd.find(Cidadao.class, cpf);
            bd.getTransaction().begin();
            bd.remove(atividade); //Hibernate efetua o delete
            bd.getTransaction().commit();
            return Response.status(Response.Status.OK).
                    entity("true").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).
                    build();
        } finally {
            bd.close();
        }
    }

//    /* Insere novo Cidadao */
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response incluir(Cidadao cidadao) {
//        EntityManager bd = util.JpaUtil.getEntityManager();
//        try {
//            bd.getTransaction().begin();
//            bd.persist(cidadao); //Hibernate gera o insert
//            bd.getTransaction().commit();
//            return Response.status(Response.Status.OK).
//                    entity("true").build();
//        } catch (Exception e) {
//            return Response.serverError().
//                    entity(e.getMessage()).build();
//        } finally {
//            bd.close();
//        }
//    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response incluir(Cidadao cidadao) {
        EntityManager bd = util.JpaUtil.getEntityManager();
                Usuario u = new Usuario("abc", "hue");
        cidadao.setUsuario(u);
        try {
            bd.getTransaction().begin();
            bd.persist(u);
            bd.getTransaction().commit();

            bd.getTransaction().begin();
            bd.persist(cidadao); //Hibernate gera o insert
            bd.getTransaction().commit();
            return Response.status(Response.Status.OK).
                    entity("true").build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).build();
        } finally {
            bd.close();
        }
    }
    
    /* Altera Cidadao */
    @Path("{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Cidadao cidadao) {
        EntityManager bd = util.JpaUtil.getEntityManager();
        try {
            bd.getTransaction().begin();
            cidadao = bd.merge(cidadao); //Hibernate gera o update
            bd.getTransaction().commit();
            return Response.status(Response.Status.OK)
                    .entity("true").build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).build();
        } finally {
            bd.close();
        }
    }
}
