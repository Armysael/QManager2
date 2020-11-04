package com.sifontes.Qmanagerv2.dto;

public class InfoQuinielaPartidoDto {

    private long partidoId;
    private int resultadoPropuesto;

    public InfoQuinielaPartidoDto() {
    }

    public InfoQuinielaPartidoDto(long partidoId, int resultadoPropuesto) {
        this.partidoId = partidoId;
        this.resultadoPropuesto = resultadoPropuesto;
    }

    public long getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(long partidoId) {
        this.partidoId = partidoId;
    }

    public void setResultadoPropuesto(int resultadoPropuesto) {
        this.resultadoPropuesto = resultadoPropuesto;
    }

    public int getResultadoPropuesto() {
        return resultadoPropuesto;
    }
}
