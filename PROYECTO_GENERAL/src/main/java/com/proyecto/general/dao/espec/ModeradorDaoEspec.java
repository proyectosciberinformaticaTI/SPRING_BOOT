package com.proyecto.general.dao.espec;

import com.proyecto.general.bean.moderadorBean;

public interface ModeradorDaoEspec extends CrudEspec<moderadorBean> {
public moderadorBean login(String usuario,String clave);
}
