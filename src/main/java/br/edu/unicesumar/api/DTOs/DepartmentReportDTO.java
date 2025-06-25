package br.edu.unicesumar.api.DTOs;

public class DepartmentReportDTO {

    private int totalEventos;
    private int totalInscricoes;

    public DepartmentReportDTO(int totalEvents, int totalRegistrations) {
        this.totalEventos = totalEvents;
        this.totalInscricoes = totalRegistrations;
    }

    public int getTotalEvents() {
        return totalEventos;
    }

    public void setTotalEvents(int totalEventos) {
        this.totalEventos = totalEventos;
    }

    public int getTotalRegistrations() {
        return totalInscricoes;
    }

    public void setTotalRegistrations(int totalInscricoes) {
        this.totalInscricoes = totalInscricoes;
    }
}
