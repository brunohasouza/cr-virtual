package com.example.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.example.dao.ManagerDao;
import com.example.model.Professor;

@ManagedBean(name="pController")
@SessionScoped
public class ProfessorController {
    private Professor professor;
    private Professor selecionado;

    @PostConstruct
    public void init(){
        professor = new Professor();
    }

    public void criar() {
        ManagerDao.getCurrentInstance().insert(this.professor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor adicionado com sucesso!", null));
        this.professor = new Professor();
    }
    
    public List<Professor> listar() {
        String query = "SELECT p FROM Professor p";
        
        return ManagerDao.getCurrentInstance().read(query, Professor.class);
    }
    
    public void remover() {
        ManagerDao.getCurrentInstance().delete(this.selecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Professor " + this.selecionado.getNome() + " excluído com sucesso!"));
    }
    
    public void atualizar() {
        ManagerDao.getCurrentInstance().update(this.selecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor atualizado com sucesso!", null));
    }

    public void selecionar(Professor p) {
        this.selecionado = p;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Professor selecionado) {
        this.selecionado = selecionado;
    }
    
}
