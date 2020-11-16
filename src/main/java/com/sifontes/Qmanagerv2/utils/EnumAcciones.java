package com.sifontes.Qmanagerv2.utils;

public enum EnumAcciones {

    SAVE{
        @Override
        public String retornarMensaje() {
            return " guardando ";
        }
    },
    DELETE{
        @Override
        public String retornarMensaje() {
            return " borrando ";
        }
    },
    EDIT {
        @Override
        public String retornarMensaje() {
            return " editando ";
        }
    };

    public abstract String retornarMensaje();
}
