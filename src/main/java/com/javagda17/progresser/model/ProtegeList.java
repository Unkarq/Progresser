package com.javagda17.progresser.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtegeList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameList;

    @OneToMany(mappedBy = "listaDoKtorejNaleze", fetch = FetchType.EAGER)
    private List<AppUser> protegeList = new ArrayList<>();

    @OneToOne
    private AppUser trener;
}

