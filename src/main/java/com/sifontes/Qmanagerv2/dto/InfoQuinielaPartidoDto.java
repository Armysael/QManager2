package com.sifontes.Qmanagerv2.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(partidoId).
                        append(resultadoPropuesto).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InfoQuinielaPartidoDto))
            return false;
        if (obj == this)
            return true;

        InfoQuinielaPartidoDto rhs = (InfoQuinielaPartidoDto) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(partidoId, rhs.partidoId).
                        append(resultadoPropuesto, rhs.resultadoPropuesto).
                        isEquals();
    }
}
