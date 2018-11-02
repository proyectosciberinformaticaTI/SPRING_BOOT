package com.proyecto.general.dao.espec;

import java.util.List;
import java.util.Map;

public interface CrudEspec <T>{

  T traerPorCodigo(String codigo);
  
  List<T> traerPorNombre(String dni);
  
  void insertar(T bean);
  
  void actualizar(T bean);
  
  void eliminar(String codigo);
  
}


