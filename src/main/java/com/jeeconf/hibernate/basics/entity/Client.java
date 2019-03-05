package com.jeeconf.hibernate.basics.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Igor Dmitriev / Mikalai Alimenkou on 4/29/16
 */
@Entity
@Getter
@Setter
//@Immutable
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_client")
	private Integer id;

	private String name;
	private int age;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Account> accounts = new ArrayList<>();
}
