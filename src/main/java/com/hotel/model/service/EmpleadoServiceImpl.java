package com.hotel.model.service;

import com.hotel.model.dao.EmpleadoDao;
import com.hotel.model.entity.Empleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoDao empleadoDao;
	
	@Override
	@Transactional(readOnly = true)
  public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
  public Empleado findEmpleado(Empleado empleado) {
		return empleadoDao.findById(empleado.getId()).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
  public void delete(Empleado empleado) {
		return empleadoDao.delete;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Empleado empleado) {
		return empleadoDao.save(empleado.getId());
	}
	
}
