package com.edu.infnet.tp1.repositories.organizacao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.infnet.tp1.models.organizacao.UsuarioRole;
import com.edu.infnet.tp1.models.organizacao.UsuarioRoleId;

public interface UsuarioRoleRepository extends  JpaRepository<UsuarioRole, UsuarioRoleId>{

}
