package com.example.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.example.dao.ManagerDao;
import com.example.model.Estudante;

@ManagedBean(name = "estudanteController")
@SessionScoped
public class EstudanteController {
    private Estudante estudante;
    private Estudante estudanteSelecionado;

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
    
    public void remover() {
        ManagerDao.getCurrentInstance().delete(this.estudanteSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estudante " + this.estudanteSelecionado.getNome() + " excluído com sucesso!"));
    }
    
    public void atualizar() {
        ManagerDao.getCurrentInstance().update(this.estudanteSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudante atualizado com sucesso!", null));
    }

    public void selecionar(Estudante e) {
        this.estudanteSelecionado = e;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Estudante getEstudanteSelecionado() {
        return estudanteSelecionado;
    }

    public void setEstudanteSelecionado(Estudante estudanteSelecionado) {
        this.estudanteSelecionado = estudanteSelecionado;
    }
}
