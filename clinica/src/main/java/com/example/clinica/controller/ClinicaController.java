package com.example.clinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.clinica.model.AtencionMedica;
import com.example.clinica.model.HistorialMedico;
import com.example.clinica.model.Paciente;
import com.example.clinica.service.AtencionMedicaService;
import com.example.clinica.service.HistorialMedicoService;
import com.example.clinica.service.PacienteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/clinica")
public class ClinicaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaController.class);

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private HistorialMedicoService historialMedicoService;

    @Autowired
    private AtencionMedicaService atencionMedicaService;

    @GetMapping("/pacientes")
    public ResponseEntity<CollectionModel<EntityModel<Paciente>>> buscarTodosLosPacientes() {
        LOGGER.info("Consultando todos los pacientes");
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();
        List<EntityModel<Paciente>> pacientesResources = pacientes.stream()
                .map(p -> EntityModel.of(p,
                        linkTo(methodOn(ClinicaController.class).buscarPacientePorId(p.getId())).withSelfRel(),
                        linkTo(methodOn(ClinicaController.class).buscarTodosLosPacientes()).withRel("pacientes")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(pacientesResources,
                linkTo(methodOn(ClinicaController.class).buscarTodosLosPacientes()).withSelfRel()));
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<EntityModel<Paciente>> buscarPacientePorId(@PathVariable Long id) {
        LOGGER.info("Consultando paciente por ID: {}", id);
        Optional<Paciente> paciente = pacienteService.buscarPacientePorId(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(EntityModel.of(paciente.get(),
                    linkTo(methodOn(ClinicaController.class).buscarPacientePorId(id)).withSelfRel(),
                    linkTo(methodOn(ClinicaController.class).buscarTodosLosPacientes()).withRel("pacientes")));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado con ID: " + id);
        }
    }

    @GetMapping("/historiales-medicos")
    public ResponseEntity<CollectionModel<EntityModel<HistorialMedico>>> buscarTodosLosHistorialesMedicos() {
        LOGGER.info("Consultando todos los historiales médicos");
        List<HistorialMedico> historialesMedicos = historialMedicoService.buscarTodosLosHistorialesMedicos();
        List<EntityModel<HistorialMedico>> historialesMedicosResources = historialesMedicos.stream()
                .map(h -> EntityModel.of(h,
                        linkTo(methodOn(ClinicaController.class).buscarHistorialMedicoPorId(h.getId())).withSelfRel(),
                        linkTo(methodOn(ClinicaController.class).buscarTodosLosHistorialesMedicos()).withRel("historiales-medicos")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(historialesMedicosResources,
                linkTo(methodOn(ClinicaController.class).buscarTodosLosHistorialesMedicos()).withSelfRel()));
    }

    @GetMapping("/historiales-medicos/{id}")
    public ResponseEntity<EntityModel<HistorialMedico>> buscarHistorialMedicoPorId(@PathVariable Long id) {
        LOGGER.info("Consultando historial médico por ID: {}", id);
        Optional<HistorialMedico> historialMedico = historialMedicoService.buscarHistorialMedicoPorId(id);
        if (historialMedico.isPresent()) {
            return ResponseEntity.ok(EntityModel.of(historialMedico.get(),
                    linkTo(methodOn(ClinicaController.class).buscarHistorialMedicoPorId(id)).withSelfRel(),
                    linkTo(methodOn(ClinicaController.class).buscarTodosLosHistorialesMedicos()).withRel("historiales-medicos")));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historial médico no encontrado con ID: " + id);
        }
    }

    @GetMapping("/atenciones-medicas")
    public ResponseEntity<CollectionModel<EntityModel<AtencionMedica>>> buscarTodasLasAtencionesMedicas() {
        LOGGER.info("Consultando todas las atenciones médicas");
        List<AtencionMedica> atencionesMedicas = atencionMedicaService.buscarTodasLasAtencionesMedicas();
        List<EntityModel<AtencionMedica>> atencionesMedicasResources = atencionesMedicas.stream()
                .map(a -> EntityModel.of(a,
                        linkTo(methodOn(ClinicaController.class).buscarAtencionMedicaPorId(a.getId())).withSelfRel(),
                        linkTo(methodOn(ClinicaController.class).buscarTodasLasAtencionesMedicas()).withRel("atenciones-medicas")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(atencionesMedicasResources,
                linkTo(methodOn(ClinicaController.class).buscarTodasLasAtencionesMedicas()).withSelfRel()));
    }

    @GetMapping("/atenciones-medicas/{id}")
    public ResponseEntity<EntityModel<AtencionMedica>> buscarAtencionMedicaPorId(@PathVariable Long id) {
        LOGGER.info("Consultando atención médica por ID: {}", id);
        Optional<AtencionMedica> atencionMedica = atencionMedicaService.buscarAtencionMedicaPorId(id);
        if (atencionMedica.isPresent()) {
            return ResponseEntity.ok(EntityModel.of(atencionMedica.get(),
                    linkTo(methodOn(ClinicaController.class).buscarAtencionMedicaPorId(id)).withSelfRel(),
                    linkTo(methodOn(ClinicaController.class).buscarTodasLasAtencionesMedicas()).withRel("atenciones-medicas")));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atención médica no encontrada con ID: " + id);
        }
    }

    @PostMapping("/pacientes/{pacienteId}/historiales-medicos")
    public ResponseEntity<EntityModel<HistorialMedico>> crearHistorialMedico(@PathVariable Long pacienteId, @Validated @RequestBody HistorialMedico historialMedico) {
        LOGGER.info("Creando nuevo historial médico para el paciente con ID: {}", pacienteId);
        Optional<Paciente> pacienteOptional = pacienteService.buscarPacientePorId(pacienteId);
        if (pacienteOptional.isPresent()) {
            historialMedico.setPaciente(pacienteOptional.get());
            HistorialMedico nuevoHistorialMedico = historialMedicoService.guardarHistorialMedico(historialMedico);
            EntityModel<HistorialMedico> resource = EntityModel.of(nuevoHistorialMedico,
                    linkTo(methodOn(ClinicaController.class).buscarHistorialMedicoPorId(nuevoHistorialMedico.getId())).withSelfRel());
            return ResponseEntity.created(resource.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resource);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado con ID: " + pacienteId);
        }
    }

    @PostMapping("/historiales-medicos/{historialId}/atenciones-medicas")
    public ResponseEntity<EntityModel<AtencionMedica>> crearAtencionMedica(@PathVariable Long historialId, @Validated @RequestBody AtencionMedica atencionMedica) {
        LOGGER.info("Creando nueva atención médica para el historial médico con ID: {}", historialId);
        Optional<HistorialMedico> historialOptional = historialMedicoService.buscarHistorialMedicoPorId(historialId);
        if (historialOptional.isPresent()) {
            atencionMedica.setHistorialMedico(historialOptional.get());
            AtencionMedica nuevaAtencionMedica = atencionMedicaService.guardarAtencionMedica(atencionMedica);
            EntityModel<AtencionMedica> resource = EntityModel.of(nuevaAtencionMedica,
                    linkTo(methodOn(ClinicaController.class).buscarAtencionMedicaPorId(nuevaAtencionMedica.getId())).withSelfRel());
            return ResponseEntity.created(resource.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resource);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historial médico no encontrado con ID: " + historialId);
        }
    }

}
