package com.proyecto.general.dao.espec;

import com.proyecto.general.bean.agraviadosBean;

public interface AgraviadosDaoEspec extends CrudEspec<agraviadosBean> {
	public agraviadosBean login(String usuario,String clave);
}
