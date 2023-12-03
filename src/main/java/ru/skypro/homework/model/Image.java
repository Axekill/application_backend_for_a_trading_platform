package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name="media_size")
    private  String mediaType;


}
