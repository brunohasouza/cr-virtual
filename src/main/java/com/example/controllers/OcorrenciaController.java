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

    private int filtroProfessor;
    private int filtroEstudante;
    private TipoOcorrencia filtroTipo;

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
        String query = "SELECT o FROM Ocorrencia o WHERE 1=1";

        if (this.filtroProfessor != 0) {
            query += " AND o.professor.codigo = " + this.filtroProfessor;
        }
        
        if (this.filtroEstudante != 0) {
            query += " AND o.estudante.matricula = " + this.filtroEstudante;
        }

        if (this.filtroTipo != null) {
            query += " AND o.tipo = com.example.model.TipoOcorrencia." + this.filtroTipo.name();
        }

        return ManagerDao.getCurrentInstance().read(query, Ocorrencia.class);
    }

    public void limparFiltros() {
        this.filtroProfessor = 0;
        this.filtroEstudante = 0;
        this.filtroTipo = null;
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

    public int getFiltroProfessor() {
        return filtroProfessor;
    }

    public void setFiltroProfessor(int filtroProfessor) {
        this.filtroProfessor = filtroProfessor;
    }

    public int getFiltroEstudante() {
        return filtroEstudante;
    }

    public void setFiltroEstudante(int filtroEstudante) {
        this.filtroEstudante = filtroEstudante;
    }

    public TipoOcorrencia getFiltroTipo() {
        return filtroTipo;
    }

    public void setFiltroTipo(TipoOcorrencia filtroTipo) {
        this.filtroTipo = filtroTipo;
    }
}
