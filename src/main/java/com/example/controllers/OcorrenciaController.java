package com.example.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.example.dao.ManagerDao;
import com.example.model.Estudante;
import com.example.model.Ocorrencia;
import com.example.model.Professor;
import com.example.model.TipoOcorrencia;

@ManagedBean(name = "oController")
@SessionScoped
public class OcorrenciaController {
    private Ocorrencia ocorrencia;
    private Ocorrencia selecionada;

    private int codigoProfessor;
    private int matriculaEstudante;

    private ProfessorController pController = new ProfessorController();
    private EstudanteController eController = new EstudanteController();

    @PostConstruct
    public void init() {
        ocorrencia = new Ocorrencia();
    }

    public void criar() {
        Professor p = pController.buscaPorCodigo(this.codigoProfessor);
        Estudante e = eController.buscaPorMatricula(this.matriculaEstudante);

        this.ocorrencia.setProfessor(p);
        this.ocorrencia.setEstudante(e);

        ManagerDao.getCurrentInstance().insert(this.ocorrencia);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorrência registrada com sucesso!", null));

        this.ocorrencia = new Ocorrencia();
    }

    public List<Ocorrencia> listar() {
        String query = "SELECT o FROM Ocorrencia o";
        return ManagerDao.getCurrentInstance().read(query, Ocorrencia.class);
    }

    public List<Professor> listarProfessores() {
        System.out.println(TipoOcorrencia.values());
        return pController.listar();
    }

    public List<Estudante> listarEstudantes() {
        return eController.listar();
    }

    public TipoOcorrencia[] listarTipoOcorrencias() {
        return TipoOcorrencia.values();
    }

    public void selecionar(Ocorrencia o) {
        this.selecionada = o;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public int getCodigoProfessor() {
        return codigoProfessor;
    }

    public void setCodigoProfessor(int codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }

    public int getMatriculaEstudante() {
        return matriculaEstudante;
    }

    public void setMatriculaEstudante(int matriculaEstudante) {
        this.matriculaEstudante = matriculaEstudante;
    }

    public Ocorrencia getSelecionada() {
        return selecionada;
    }

    public void setSelecionada(Ocorrencia selecionada) {
        this.selecionada = selecionada;
    }
}
