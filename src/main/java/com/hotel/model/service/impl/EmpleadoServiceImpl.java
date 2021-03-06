package com.hotel.model.service.impl;

import com.hotel.model.dao.EmpleadoDao;
import com.hotel.model.entity.Empleado;
import java.util.List;

import com.hotel.model.entity.Tematica;
import com.hotel.model.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Empleado> findAll(Pageable pageable) {
        return empleadoDao.findAll(pageable);
    }

	@Override
	@Transactional(readOnly = true)
  public Empleado findById(Long id) {
		return empleadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
  	public void deleteById(Long id) {
		empleadoDao.deleteById(id);
	}
	
	
	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		return empleadoDao.save(empleado);
	}
	
}
