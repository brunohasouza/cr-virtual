package com.example.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.example.dao.ManagerDao;
import com.example.model.Estudante;

@ManagedBean(name = "eController")
@SessionScoped
public class EstudanteController {
    private Estudante estudante;
    private Estudante selecionado;

    @PostConstruct
    public void init() {
        estudante = new Estudante();
    }

    public void criar() {
        ManagerDao.getCurrentInstance().insert(this.estudante);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudante adicionado com sucesso!", null));
        this.estudante = new Estudante();
    }
    
    public List<Estudante> listar() {
        String query = "SELECT e FROM Estudante e";
        
        return ManagerDao.getCurrentInstance().read(query, Estudante.class);
    }

    public Estudante buscaPorMatricula(int matricula) {
        String query = "SELECT e FROM Estudante e WHERE e.matricula = " + matricula;
        List<Estudante> result = (List<Estudante>) ManagerDao.getCurrentInstance().read(query, Estudante.class);
        return result.isEmpty() ? null : result.get(0);
    }
    
    public void remover() {
        ManagerDao.getCurrentInstance().delete(this.selecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estudante " + this.selecionado.getNome() + " excluído com sucesso!"));
    }
    
    public void atualizar() {
        ManagerDao.getCurrentInstance().update(this.selecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudante atualizado com sucesso!", null));
    }

    public void selecionar(Estudante e) {
        this.selecionado = e;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Estudante getselecionado() {
        return selecionado;
    }

    public void setselecionado(Estudante selecionado) {
        this.selecionado = selecionado;
    }
}
