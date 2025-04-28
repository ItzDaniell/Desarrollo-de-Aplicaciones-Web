package com.tecsup.pfr_crud_jakarta.model.daos.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.AdministradorDao;
import com.tecsup.pfr_crud_jakarta.model.daos.AlumnoDao;
import com.tecsup.pfr_crud_jakarta.model.daos.CursoDao;
import com.tecsup.pfr_crud_jakarta.util.Tipo;

public class DaoFactory {

    public static AdministradorDao getAdministradorDao(Tipo tipo){
        switch (tipo){
            case MEM:
                return new AdministradorDaoMemory();
            case PST:
                return new AdministradorDaoPreparedStatement();
            case CST:
                return new AdministradorDaoCallableStatement();
            default:
                return null;
        }
    }

    public static CursoDao getCursoDao(Tipo tipo){
        switch (tipo){
            case MEM:
                return new CursoDaoMemory();
            case PST:
                return new CursoDaoPreparedStatement();
            case CST:
                return new CursoDaoCallableStatement();
            default:
                return null;
        }
    }

    public static AlumnoDao getAlumnoDao(Tipo tipo){
        switch (tipo){
            case MEM:
                return new AlumnoDaoMemory();
            case PST:
                return new AlumnoDaoPreparedStatement();
            case CST:
                return new AlumnoDaoCallableStatement();
            default:
                return null;
        }
    }
}








