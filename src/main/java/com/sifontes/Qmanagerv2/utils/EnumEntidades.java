package com.sifontes.Qmanagerv2.utils;

public enum EnumEntidades {

    PARTIDO{
        @Override
        public String retornarNombreEntidad() {
            return "Partido";
        }
    },
    EVENTO{
        @Override
        public String retornarNombreEntidad() {
            return "Evento";
        }
    },
    EQUIPO{
        @Override
        public String retornarNombreEntidad() {
            return "Equipo";
        }
    },
    POOL{
        @Override
        public String retornarNombreEntidad() {
            return "Pool";
        }
    },
    QUINIELA{
        @Override
        public String retornarNombreEntidad() {
            return "Quiniela";
        }
    };

    public abstract String retornarNombreEntidad();
}
