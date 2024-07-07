package com.lcode.demo_park_api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipos_ocorrencias")
@EntityListeners(AuditingEntityListener.class)
public class TipoOcorrencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", unique = true, nullable = false, length = 100)
    private String descricao;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @CreatedBy
    @Column(name = "criado_por")
    private String criadoPor;

    @LastModifiedBy
    @Column(name = "modificado_por")
    private String modificadoPor;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TipoOcorrencia))
            return false;
        TipoOcorrencia tipoOcorrencia = (TipoOcorrencia) o;
        return getId().equals(tipoOcorrencia.getId());
    }

}
