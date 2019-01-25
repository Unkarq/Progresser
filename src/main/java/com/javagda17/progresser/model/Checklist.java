//package com.javagda17.progresser.model;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Checklist {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String name;
//
//    @ManyToMany(mappedBy = "protegeSet", fetch = FetchType.EAGER)
//    private Set<AppUser> proteges;
//
//}
