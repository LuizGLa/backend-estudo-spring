package com.lcode.demo_park_api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ocorrencias")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ocorrencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "localizacao", nullable = false, length = 255)
    private String localizacao;

    @Column(name = "data_hora", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "tipo_ocorrencia_id", nullable = false)
    private TipoOcorrencia tipoOcorrencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "rua_id", nullable = false)
    private Rua rua;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

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
        if (!(o instanceof Ocorrencia))
            return false;
        Ocorrencia ocorrencia = (Ocorrencia) o;
        return getId().equals(ocorrencia.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
