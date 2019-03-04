package com.jeeconf.hibernate.basics.nplusone;

import java.util.List;

import org.junit.Test;
import org.springframework.test.annotation.Commit;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.basics.BaseTest;
import com.jeeconf.hibernate.basics.entity.Client;

/**
 * Created by Igor Dmitriev / Mikalai Alimenkou on 5/18/16
 */
@DatabaseSetup("/data.xml")
public class NplusOneTest extends BaseTest {

	@Test
	public void nPlusOneIssue() {
		//noinspection unchecked
		List<Client> clients = getSession().createQuery("select c from Client c").list();
		clients.forEach(c -> c.getAccounts().size());
	}

	@Test
	public void extraLazy() {
		// add @LazyCollection(LazyCollectionOption.EXTRA) to Client accounts
		Client client = getSession().get(Client.class, 10);
		System.out.println("Size: " + client.getAccounts()); // 2
		System.out.println("First account id: " + client.getAccounts().get(0).getId());
	}

	@Test
	@Commit
	public void clearCollections() {
		Client client = getSession().get(Client.class, 10);
		client.getAccounts().clear();
	}
}
