package com.galiana_project.cl.galiana_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galiana_project.cl.galiana_project.model.Usuario;
import com.galiana_project.cl.galiana_project.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario usuarioToUpdate = usuarioRepository.findById(id).get();
        if (usuarioToUpdate != null) {
            usuarioToUpdate.setNombres(usuario.getNombres());
            usuarioToUpdate.setMail(usuario.getMail());
            usuarioToUpdate.setContraseña(usuario.getContraseña());
            usuarioToUpdate.setFechaNacimiento(usuario.getFechaNacimiento());
            return usuarioRepository.save(usuarioToUpdate);

        } else {
            return null;

        }
    }

    public Usuario patchUsuario(Long id, Usuario usuarioParcial) {
        Optional<Usuario> opcionalUsuario = usuarioRepository.findById(id);
        if (opcionalUsuario.isPresent()) {

            Usuario usuarioToUpdate = opcionalUsuario.get();

            if (usuarioParcial.getNombres() != null) {
                usuarioToUpdate.setNombres(usuarioParcial.getNombres());
            }

            if (usuarioParcial.getMail() != null) {
                usuarioToUpdate.setMail(usuarioParcial.getMail());
            }

            if (usuarioParcial.getContraseña() != null) {
                usuarioToUpdate.setContraseña(usuarioParcial.getContraseña());
            }

            if (usuarioParcial.getFechaNacimiento() != null) {
                usuarioToUpdate.setFechaNacimiento(usuarioParcial.getFechaNacimiento());
            }

            return usuarioRepository.save(usuarioToUpdate);

        } else {
            return null;

        }
    }
}
