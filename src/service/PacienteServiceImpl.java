package service;

import entities.HistoriaClinica;
import entities.Paciente;
import dao.PacienteDaoImpl;
import java.util.List;

public class PacienteServiceImpl {
    
    private final PacienteDaoImpl pacienteDao;

    private final HistoriaClinicaServiceImpl historiaClinicaService;

    public PacienteServiceImpl(PacienteDaoImpl pacienteDao, HistoriaClinicaServiceImpl historiaClinicaService) {
        this.pacienteDao = pacienteDao;
        this.historiaClinicaService = historiaClinicaService;
    }
    
    public List<Paciente> getAll(){
        return null;
    }
    
    public Paciente buscarPorID(String id){
        return null;
    }
    
    public Paciente buscarPorDni(String dni){
        return null;
    }
    
    public Paciente crearPaciente(Paciente paciente){
        return null;
    }
    
    public Paciente insertarPaciente(Paciente paciente){
        return null;
    }
    
    public Paciente actualizarPaciente(Paciente paciente){
        return null;
    }
    
    public HistoriaClinicaServiceImpl getHistoriaClinicaServer() {
        return this.historiaClinicaService;
    }
    
}
