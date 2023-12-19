package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "media_type")
    private String mediaType;

    @OneToOne
    private Users users;

    @OneToOne
    private Ad ad;

}
