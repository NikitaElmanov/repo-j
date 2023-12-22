package ru.qr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Table(name = "qr_code")
@FieldDefaults(level = PRIVATE)
public class QRCode {
    @Id
    @Column(name = "id", nullable = false)
    UUID id = UUID.randomUUID();

    Integer size;
}
